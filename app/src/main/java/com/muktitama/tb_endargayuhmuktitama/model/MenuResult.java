package com.muktitama.tb_endargayuhmuktitama.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MenuResult {

    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Menu> listDataMenu;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Menu> getListDataMenu() {
        return listDataMenu;
    }

    public void setListDataMenu(List<Menu> listDataMenu) {
        this.listDataMenu = listDataMenu;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
