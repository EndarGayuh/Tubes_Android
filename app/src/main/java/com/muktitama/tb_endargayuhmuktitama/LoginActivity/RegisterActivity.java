package com.muktitama.tb_endargayuhmuktitama.LoginActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.muktitama.tb_endargayuhmuktitama.R;
import com.muktitama.tb_endargayuhmuktitama.rest.ApiClient;
import com.muktitama.tb_endargayuhmuktitama.rest.ApiInterfaceLogin;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.etNama)
    EditText etNama;

    @BindView(R.id.etEmail)
    EditText etEmail;

    @BindView(R.id.etPassword)
    EditText etPassword;

    @BindView(R.id.btnRegister)
    Button btnRegister;

    ProgressDialog loading;

    Context mContext;
    ApiInterfaceLogin mApiInterfaceLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();

        ButterKnife.bind(this);
        mContext = this;
//        mApiService = UtilsApi.getAPIService();
        mApiInterfaceLogin = ApiClient.getClient().create(ApiInterfaceLogin.class);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestRegister();
            }
        });
    }

    private void requestRegister() {
    etNama = (EditText) findViewById(R.id.etNama);
    etPassword = (EditText) findViewById(R.id.etPassword);
    etEmail = (EditText) findViewById(R.id.etEmail);

    mApiInterfaceLogin.registerRequest(etNama.getText().toString(),
            etEmail.getText().toString(),
            etPassword.getText().toString())
            .enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
                        Log.i("debug", "onResponse: BERHASIL");
                        loading.dismiss();
                        try {
                            JSONObject jsonRESULTS = new JSONObject(response.body().string());

                            if (jsonRESULTS.getString("error").equals("false")){
                                Toast.makeText(mContext, "BERHASIL REGISTRASI", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(mContext, LoginActivity.class));

                            }
                            else {
                                String error_message = jsonRESULTS.getString("error_msg");

                                Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.i("debug", "onResponse: GA BERHASIL");
                        loading.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                    Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                }
            });
}
}