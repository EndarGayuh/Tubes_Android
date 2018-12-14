package com.muktitama.tb_endargayuhmuktitama;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.muktitama.tb_endargayuhmuktitama.LoginActivity.LoginActivity;
import com.muktitama.tb_endargayuhmuktitama.MenuMakananActivity.InsertMakananActivity;
import com.muktitama.tb_endargayuhmuktitama.MenuMakananActivity.MenuMakananAdminActivity;
import com.muktitama.tb_endargayuhmuktitama.rest.ApiClient;
import com.muktitama.tb_endargayuhmuktitama.rest.ApiInterface;
import com.muktitama.tb_endargayuhmuktitama.rest.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdmin extends AppCompatActivity {

    @BindView(R.id.tvResultNama)
    TextView tvResultNama;

    @BindView(R.id.tvResultEmailAdmin)
    TextView tvResultEmailAdmin;

    @BindView(R.id.btnLogout)
    Button btnLogout;

    @BindView(R.id.btCekMenu)
    Button btCekmenu;

    @BindView(R.id.btInputMenu)
    Button btInputMenu;

    SharedPrefManager sharedPrefManager;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        ButterKnife.bind(this);
        sharedPrefManager = new SharedPrefManager(this);

        tvResultNama = (TextView) findViewById(R.id.tvResultNama);
        tvResultNama.setText(sharedPrefManager.getSPNama());

        tvResultEmailAdmin = (TextView) findViewById(R.id.tvResultEmailAdmin);
        tvResultEmailAdmin.setText(sharedPrefManager.getSPEmail());
        tvResultEmailAdmin.setText();

        mContext = getApplicationContext();
        Intent mIntent = getIntent();

        btCekmenu = (Button) findViewById(R.id.btCekMenu);
        btCekmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MenuMakananAdminActivity.class);
                startActivity(intent);
            }
        });


        btInputMenu = (Button) findViewById(R.id.btInputMenu);
        btInputMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, InsertMakananActivity.class);
                startActivity(intent);
            }
        });


        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                startActivity(new Intent(HomeAdmin.this, MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

    }
}
