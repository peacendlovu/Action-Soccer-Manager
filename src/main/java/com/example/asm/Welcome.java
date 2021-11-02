package com.example.asm;

import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



public class Welcome extends AppCompatActivity
{
    Button createButton;
    Button displayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        final TextView textView = (TextView)findViewById(R.id.Leaderboard);

        createButton = (Button)findViewById(R.id.create);
        displayButton = (Button)findViewById(R.id.leaderboard);

        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Welcome.this, leaderboard_activity.class);
                startActivity(intent);

            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome.this, create_game.class);
                startActivity(intent);
            }
        });

    }





}
