package ir.ebookline.functions;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class PublicFunctions {

    Context con;

    public PublicFunctions (Context c)
    {
        this.con = c;
    }

    public void showToast(String text)
    {
        Toast.makeText(con, text, Toast.LENGTH_SHORT).show();
    }

    public void showAlertDialog (int icon, String title,String message)
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(con );
        alertDialog.setTitle(title);
        alertDialog.setMessage( message );
        alertDialog.setIcon( icon );
        alertDialog.setPositiveButton( "Ok" , new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });
    }

    public void textToClipBoard (String text)
    {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) con.getSystemService(con.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) con.getSystemService(con.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);
        }
    }

    public void shareText (String text)
    {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text );
        shareIntent.putExtra(Intent.EXTRA_SUBJECT , "ShareText" );
    }

    @SuppressLint("MissingPermission")
    public boolean isNetworkConnected()
    {
        ConnectivityManager cm = (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);
         NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null)
            return true;
        else
            return false;
    }

    public boolean isAppInstalled(String packageName)
    {
        PackageManager pm = con.getPackageManager();
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public int screenSize ( int i )
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)con).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        if (i == 0)
            return height;
        else
            return width;
    }

    public void setGradientDrawable(View v, int startColor , int endColor , int borderColor)
    {
        GradientDrawable shape = new GradientDrawable(
                GradientDrawable.Orientation.BOTTOM_TOP, new int[]{endColor, startColor});
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadii(new float[]{8, 8, 8, 8, 0, 0, 0, 0});
        shape.setStroke(3, borderColor);
        v.setBackgroundDrawable(shape);
    }


    public String getRandomString(int stringLength)
    {
        String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(stringLength);
        for(int i=0;i<stringLength;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }

    public long getCurrentTimeMillis()
    {
        return java.lang.System.currentTimeMillis();
    }


    public String getDisplayableTime(long delta) {
        long difference = 0;
        long mDate = getCurrentTimeMillis();
        if (mDate > delta) {
            difference = mDate - delta;
            final long seconds = difference / 1000;
            final long minutes = seconds / 60;
            final long hours = minutes / 60;
            final long days = hours / 24;
            final long months = days / 31;
            final long years = days / 365;

            if (seconds < 60) {
                return "a few seconds ago";
            } else if (seconds < 120) {
                return "a minute ago";
            } else if (seconds < 2700) // 45 * 60
            {
                return minutes + " minutes ago";
            } else if (seconds < 5400) // 90 * 60
            {
                return "an hour ago";
            } else if (seconds < 86400) // 24 * 60 * 60
            {
                return hours + " hours ago";
            } else if (seconds < 172800) // 48 * 60 * 60
            {
                return "yesterday";
            } else if (seconds < 2592000) // 30 * 24 * 60 * 60
            {
                return days + " days ago";
            } else if (seconds < 31104000) // 12 * 30 * 24 * 60 * 60
            {
                return months <= 1 ? "a month ago" : months + " months ago";
            } else {
                return years <= 1 ? "a year ago" : years + " years ago";
            }
        }
        return null;
    }

}
