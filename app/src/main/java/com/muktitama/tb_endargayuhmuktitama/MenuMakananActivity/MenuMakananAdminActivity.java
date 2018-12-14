package com.muktitama.tb_endargayuhmuktitama.MenuMakananActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.muktitama.tb_endargayuhmuktitama.Adapter.MakananAdapter;
import com.muktitama.tb_endargayuhmuktitama.R;
import com.muktitama.tb_endargayuhmuktitama.model.Menu;
import com.muktitama.tb_endargayuhmuktitama.model.MenuResult;
import com.muktitama.tb_endargayuhmuktitama.rest.ApiClient;
import com.muktitama.tb_endargayuhmuktitama.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuMakananAdminActivity extends AppCompatActivity {

    Button btIns;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static MenuMakananAdminActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_makanan_admin);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ma=this;
        refresh();
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
                mAdapter = new MakananAdapter(MenuList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<MenuResult> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
