package com.muktitama.tb_endargayuhmuktitama;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.muktitama.tb_endargayuhmuktitama.model.MenuResult;
import com.muktitama.tb_endargayuhmuktitama.rest.ApiClient;
import com.muktitama.tb_endargayuhmuktitama.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPembelian extends AppCompatActivity {

    EditText etNamaPelayan;
    TextView namamakanan,harga,nomormeja;
    Button submit;

    ApiInterface mApiInterface;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembelian);


        etNamaPelayan = (EditText) findViewById(R.id.etNamaPelayan);
        namamakanan = (TextView) findViewById(R.id.namamakanan);
        harga = (TextView) findViewById(R.id.harga);
        nomormeja= (TextView) findViewById(R.id.nomormeja);

        Intent mIntent = getIntent();
        mContext = getApplicationContext();

        namamakanan.setText(mIntent.getStringExtra("Nama"));
        harga.setText(mIntent.getStringExtra("Harga"));
        nomormeja.setText(mIntent.getStringExtra("nomor"));

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);


        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<MenuResult> updateMenuCall = mApiInterface.input(
                        etNamaPelayan.getText().toString(),
                        namamakanan.getText().toString(),
                        harga.getText().toString(),
                        nomormeja.getText().toString());

                updateMenuCall.enqueue(new Callback<MenuResult>() {
                    @Override
                    public void onResponse(Call<MenuResult> call, Response<MenuResult> response) {
//                        MainActivity.ma.refresh();
//                        finish();
                        Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<MenuResult> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}
