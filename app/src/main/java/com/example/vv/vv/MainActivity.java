package com.example.vv.vv;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Button scanBtn,scanBtn1;
    private TextView resultsTxt,resultsTxt1;


/** Called when the activity is first created. */
    ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanBtn = (Button) findViewById(R.id.button);
        resultsTxt = (TextView) findViewById(R.id.scan_results);


        scanBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                resultsTxt.setText("Scanning...");


                Intent inte = new Intent ("com.google.zxing.client.android.SCAN");


                try {
                    startActivityForResult(inte, 0);
                } catch (ActivityNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();

                    new AlertDialog.Builder(v.getContext())
                            .setTitle("WARNING:")
                            .setMessage("You don't have Barcode Scanner  installed. Please install it.")
                            .setCancelable(false)
                            .setNeutralButton("Install it now", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                    Uri uri = Uri.parse("market://search?q=pname:com.google.zxing.client.android");
                                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                                }
                            })
                            .show();
                }

            }
        });
        scanBtn1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                resultsTxt1.setText("Scanning...");


                Intent inte = new Intent ("com.google.zxing.client.android.SCAN");


                try {
                    startActivityForResult(inte, 1);
                } catch (ActivityNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();

                    new AlertDialog.Builder(v.getContext())
                            .setTitle("WARNING:")
                            .setMessage("You don't have Barcode Scanner  installed. Please install it.")
                            .setCancelable(false)
                            .setNeutralButton("Install it now", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                    Uri uri = Uri.parse("market://search?q=pname:com.google.zxing.client.android");
                                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                                }
                            })
                            .show();
                }

            }
        });

    }

    /*Here is where we come back after the Barcode Scanner is done*/
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // contents contains whatever the code was
                String contents = intent.getStringExtra("SCAN_RESULT");

                // Format contains the type of code i.e. UPC, EAN, QRCode etc...
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

                // Handle successful scan. In this example I just put the results into the TextView
                resultsTxt1.setText(format + "\n" + contents);
            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel. If the user presses 'back' before a code is scanned.
                resultsTxt1.setText("Canceled");
            }
        }
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                // contents contains whatever the code was
                String contents = intent.getStringExtra("SCAN_RESULT");

                // Format contains the type of code i.e. UPC, EAN, QRCode etc...
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

                // Handle successful scan. In this example I just put the results into the TextView
                resultsTxt.setText(format + "\n" + contents);
            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel. If the user presses 'back' before a code is scanned.
                resultsTxt.setText("Canceled");
            }
        }
    }
}