package com.muktitama.tb_endargayuhmuktitama.MenuMakananActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.muktitama.tb_endargayuhmuktitama.HomeAdmin;
import com.muktitama.tb_endargayuhmuktitama.LoginActivity.LoginActivity;
import com.muktitama.tb_endargayuhmuktitama.R;
import com.muktitama.tb_endargayuhmuktitama.model.MenuResult;
import com.muktitama.tb_endargayuhmuktitama.rest.ApiClient;
import com.muktitama.tb_endargayuhmuktitama.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMakananActivity extends AppCompatActivity {

    EditText edtId, edtNama, edtHarga;
    Button btUpdate, btDelete, btBack;
    ApiInterface mApiInterface;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_makanan);

        edtId = (EditText) findViewById(R.id.edtId);
        edtNama = (EditText) findViewById(R.id.edtNama);
        edtHarga = (EditText) findViewById(R.id.edtHarga);

        Intent mIntent = getIntent();
        mContext = getApplicationContext();

        edtId.setTag(edtId.getKeyListener());
        edtId.setKeyListener(null);

        edtId.setText(mIntent.getStringExtra("Id_makanan"));
        edtNama.setText(mIntent.getStringExtra("Nama"));
        edtHarga.setText(mIntent.getStringExtra("Harga"));

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btUpdate = (Button) findViewById(R.id.btUpdate2);
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<MenuResult> updateMenuCall = mApiInterface.ubah(
                        edtId.getText().toString(),
                        edtNama.getText().toString(),
                        edtHarga.getText().toString());
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

        btDelete = (Button) findViewById(R.id.btDelete2);
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtId.getText().toString().trim().isEmpty()==false){
                    Call<MenuResult> deleteMenu = mApiInterface.hapus(edtId.getText().toString());
                    deleteMenu.enqueue(new Callback<MenuResult>() {
                        @Override
                        public void onResponse(Call<MenuResult> call, Response<MenuResult> response) {
//                            MainActivity.ma.refresh();
//                            finish();
                            Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<MenuResult> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });

        btBack = (Button) findViewById(R.id.btBackGo);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, HomeAdmin.class);
                startActivity(intent);
            }
        });
    }
}
