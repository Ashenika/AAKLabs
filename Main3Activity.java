package com.example.main.smarttoothdetectorapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Main3Activity extends AppCompatActivity {
    ImageButton imgButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        imgButton =(ImageButton)findViewById(R.id.imageButton3);
        imgButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.i("clicks","You Clicked B1");
                Intent i=new Intent(
                        Main3Activity.this,
                        Main4Activity.class);
                startActivity(i);

            }
        });
    }
}
