package com.example.keng.rockscissorpaper;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "meddelande";
    public static Socket socket;
    protected static String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");

        final EditText ipInput = (EditText) findViewById(R.id.ipInput);
        final EditText nameInput = (EditText) findViewById(R.id.nameInput);

        //Layout for EditText
        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,r.getDisplayMetrics());
        ipInput.setWidth(px);
        nameInput.setWidth(px);


        Button loginButton = (Button) findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request.ip = String.valueOf(ipInput.getText());
                username = String.valueOf(nameInput.getText());


                if (Request.ip.length() == 0) {
                    Request.ip = "localhost";
                }

                if (username.length() <= 1){
                    popupAlert("Minimum username size is two characters");
                } else{
                    try {
                        Request.send("connect\n" + username);
                        startActivity(new Intent(MainActivity.this,GameActivity.class));
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }



    public void popupAlert(String msg){
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }



    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }



    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }



   @Override
   protected void onStop() {
       super.onStop();
       Log.i(TAG, "onStop");
   }



    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }



    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }

}
