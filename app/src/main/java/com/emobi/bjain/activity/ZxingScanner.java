package com.emobi.bjain.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ZxingScanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private final String TAG=getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);
    }
    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.d(TAG, rawResult.getText()); // Prints scan results
        Log.d(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result","ok");
        returnIntent.putExtra("code", rawResult.getText().toString());
        returnIntent.putExtra("format",rawResult.getBarcodeFormat().toString());
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
        // If you would like to resume scanning, call this method below:
//        mScannerView.resumeCameraPreview(this);

    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result","cancel");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
