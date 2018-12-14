package com.muktitama.tb_endargayuhmuktitama;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.muktitama.tb_endargayuhmuktitama.Adapter.MakananAdapter;
import com.muktitama.tb_endargayuhmuktitama.Adapter.MenuUserAdapter;
import com.muktitama.tb_endargayuhmuktitama.LoginActivity.LoginActivity;
import com.muktitama.tb_endargayuhmuktitama.MenuMakananActivity.MenuMakananAdminActivity;
import com.muktitama.tb_endargayuhmuktitama.model.Menu;
import com.muktitama.tb_endargayuhmuktitama.model.MenuResult;
import com.muktitama.tb_endargayuhmuktitama.rest.ApiClient;
import com.muktitama.tb_endargayuhmuktitama.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btIns;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static MainActivity ma;

    Button buttonkelogin;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ma=this;
        refresh();

        mContext = getApplicationContext();
        Intent mIntent = getIntent();

        buttonkelogin = (Button) findViewById(R.id.buttonkelogin);
        buttonkelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void refresh() {
        Call<MenuResult> menuCall = mApiInterface.view();
        menuCall.enqueue(new Callback<MenuResult>() {
            @Override
            public void onResponse(Call<MenuResult> call, Response<MenuResult>
                    response) {
                List<Menu> MenuList = response.body().getListDataMenu();
                Log.d("Retrofit Get", "Jumlah Menu Makanan: " +
                        String.valueOf(MenuList.size()));
                mAdapter = new MenuUserAdapter(MenuList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<MenuResult> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
