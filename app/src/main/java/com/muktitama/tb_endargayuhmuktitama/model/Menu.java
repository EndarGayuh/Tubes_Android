package com.muktitama.tb_endargayuhmuktitama.model;

import com.google.gson.annotations.SerializedName;

public class Menu {

    @SerializedName("id_makanan")
    private String id_makanan;
    @SerializedName("nama_makanan")
    private String nama_makanan;
    @SerializedName("harga")
    private String harga;

    public Menu(String id_makanan, String nama_makanan, String harga) {
        this.id_makanan = id_makanan;
        this.nama_makanan = nama_makanan;
        this.harga = harga;
    }

    public String getId_makanan() {
        return id_makanan;
    }

    public void setId_makanan(String id_makanan) {
        this.id_makanan = id_makanan;
    }

    public String getNama_makanan() {
        return nama_makanan;
    }

    public void setNama_makanan(String nama_makanan) {
        this.nama_makanan = nama_makanan;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
