package com.example.keng.rockscissorpaper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Buttons
        Button rockButton = (Button) findViewById(R.id.rockButton);
        Button scissorButton = (Button) findViewById(R.id.scissorButton);
        Button paperButton = (Button) findViewById(R.id.paperButton);
        Button updateButton = (Button) findViewById(R.id.updateButton);

        //Listener

        rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMsg("weapon\n" + MainActivity.username + "\nrock");
            }
        });

        scissorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMsg("weapon\n" + MainActivity.username + "\nscissor");
            }
        });


        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               sendMsg("weapon\n" + MainActivity.username + "\npaper");
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            sendMsg("update");
        }
        });

    }

    private void sendMsg(String msg){
        try {
            Request.send(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
