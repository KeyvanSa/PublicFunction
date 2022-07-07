package ir.ebookline.functions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    PublicFunctions publicFunctions ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       publicFunctions = new PublicFunctions(this);

    }

    public void showToast(View v)
    {
        publicFunctions.showToast("text to toast show");
    }

    public void showAlertDialog(View v)
    {
        publicFunctions.showAlertDialog(R.drawable.ic_launcher_background,"title","message");
    }

    public void textToClipBoard(View v)
    {
        publicFunctions.textToClipBoard("sample text");
        publicFunctions.showToast("Text Copied...");
    }

    public void shareText(View v)
    {
        publicFunctions.shareText("sample share text");
    }

    public void isAppInstalled(View v)
    {
        publicFunctions.showToast(""+publicFunctions.isAppInstalled("ir.ebookline.functions"));
    }

    public void ScreenSize(View v)
    {
        publicFunctions.showToast("Your Device Height is:"+publicFunctions.screenSize(0)+"\n and Your Device Width is:"+publicFunctions.screenSize(1));
    }

    public void getRandomString(View v)
    {
        Button b= (Button) v;
        b.setText("Random Text is :"+publicFunctions.getRandomString(5));
    }

    public void encodeText(View v)
    {
        String strRandomString = publicFunctions.getRandomString(5);
        String strEncryptedString = publicFunctions.base64Encode(strRandomString);
        publicFunctions.showToast("Encrypted Text Is:\n"+strEncryptedString
                +"\n and main Text Is :\n"+publicFunctions.base64Decode(strEncryptedString));
    }

}