package com.muktitama.tb_endargayuhmuktitama;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.muktitama.tb_endargayuhmuktitama.LoginActivity.LoginActivity;

public class AwalActivity extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);

        Button btnAdmin = findViewById(R.id.btnAdmin);
        Button btnUser= findViewById(R.id.btnUser);
        mContext = getApplicationContext();

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intAdmin = new Intent(mContext, LoginActivity.class);
                startActivity(intAdmin);
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intUser = new Intent(mContext, MainActivity.class);
                startActivity(intUser);
            }
        });
    }
}
