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
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
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
        alertDialog.create().show();
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
        con.startActivity(shareIntent);
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


    public int screenSize (int i)
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


    public String base64Decode(String text)
    {
        byte[] data = Base64.decode(text, Base64.DEFAULT);
        try {
            String txt = new String(data, StandardCharsets.UTF_8);
            return txt;
        } catch (Exception e) {
            return "Error in :\n "+e.toString();
        }
    }

    public String base64Encode(String text)
    {
        try {
            byte[] data = text.getBytes(StandardCharsets.UTF_8);
            String base64 = Base64.encodeToString(data, Base64.DEFAULT);
            return base64;
        } catch (Exception e) {
            return "Error In :\n "+e.toString();
        }
    }


}
