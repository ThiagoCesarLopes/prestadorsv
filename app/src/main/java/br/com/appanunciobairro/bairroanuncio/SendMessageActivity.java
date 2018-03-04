package br.com.appanunciobairro.bairroanuncio;

import java.io.File;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SendMessageActivity extends Activity {

    private static final String LOG_TAG = "EmailLauncherActivity";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Send a simple plain text email (no contents supplied)
    public void sendSimpleEmail(View button) {
        try {
            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            startActivity(emailIntent);
        } catch (Exception e) {
            Log.e(LOG_TAG, "sendSimpleEmail() failed to start activity.", e);
            Toast.makeText(this, "No handler", Toast.LENGTH_LONG).show();
        }
    }

    public void sendPlainTextEmail(View button) {
        try {
            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

            String aEmailList[] = { getResources().getString(
                    R.string.email_address) };
            String aEmailCCList[] = { getResources().getString(
                    R.string.email_address_cc) };
            String aEmailBCCList[] = { getResources().getString(
                    R.string.email_address_bcc) };

            emailIntent
                    .putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);
            emailIntent.putExtra(android.content.Intent.EXTRA_CC, aEmailCCList);
            emailIntent.putExtra(android.content.Intent.EXTRA_BCC,
                    aEmailBCCList);
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                    getResources().getString(R.string.email_subject));

            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                    getResources().getString(R.string.email_message));

            startActivity(emailIntent);
        } catch (Exception e) {
            Log.e(LOG_TAG, "sendPlainTextEmail() failed to start activity.", e);
            Toast.makeText(this, "No handler", Toast.LENGTH_LONG).show();
        }
    }

    public void chooseEmail(View button) {

        try {
            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            startActivity(Intent.createChooser(emailIntent,
                    "Send your email in:"));
        } catch (Exception e) {
            Log.e(LOG_TAG, "chooseEmail() failed to start activity.", e);
            Toast.makeText(this, "No handler", Toast.LENGTH_LONG).show();
        }
    }

    public void sendPictureMessage(View button) {
        try {
            Intent picMessageIntent = new Intent(
                    android.content.Intent.ACTION_SEND);
            picMessageIntent.setType("image/jpeg");
            File downloadedPic = new File(
                    Environment
                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    "q.jpeg");

            picMessageIntent.putExtra(Intent.EXTRA_STREAM, Uri
                    .fromFile(downloadedPic));

            startActivity(Intent.createChooser(picMessageIntent, getResources()
                    .getString(R.string.chooser_pic)));
        } catch (Exception e) {
            Log.e(LOG_TAG, "sendPictureMessage() failed to start activity.", e);
            Toast.makeText(this, "No handler", Toast.LENGTH_LONG).show();
        }
    }

    public void launchForm(View button) {

        Intent launchFormActivity = new Intent(this, FormActivity.class);
        startActivity(launchFormActivity);

    }

}