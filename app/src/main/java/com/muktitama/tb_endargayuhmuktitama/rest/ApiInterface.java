package com.muktitama.tb_endargayuhmuktitama.rest;

import com.muktitama.tb_endargayuhmuktitama.model.MenuResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("insertMakanan.php")
    Call<MenuResult> daftar(@Field("id_makanan") String id_makanan,
                            @Field("nama_makanan") String nama_makanan,
                            @Field("harga") String harga);

    @GET("viewMakanan.php")
    Call<MenuResult> view();

    @FormUrlEncoded
    @POST("updateMakanan.php")
    Call<MenuResult> ubah(@Field("id_makanan") String id_makanan,
                          @Field("nama_makanan") String nama_makanan,
                          @Field("harga") String harga);


    @FormUrlEncoded
    @POST("deleteMakanan.php")
    Call<MenuResult> hapus(@Field("id_makanan") String id_makanan);

    @FormUrlEncoded
    @POST("searchMakanan.php")
    Call<MenuResult> search(@Field("searchMakanan") String search);

    @FormUrlEncoded
    @POST("insertPembeli.php")
    Call<MenuResult> input(@Field("NamaPelayan") String NamaPelayan,
                            @Field("namaMenu") String namaMenu,
                            @Field("harga") String harga,
                            @Field("nomorMeja") String nomorMeja);

}
