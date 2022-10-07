package com.example.dacn.Bo_de_thi;

public class Bo_de_thi_model {
    String so_thu_tu_bo_de_thi;
    int img_mon;

    public Bo_de_thi_model(String so_thu_tu_bo_de_thi, int img_mon) {
        this.so_thu_tu_bo_de_thi = so_thu_tu_bo_de_thi;
        this.img_mon = img_mon;
    }

    public String getSo_thu_tu_bo_de_thi() {
        return so_thu_tu_bo_de_thi;
    }

    public int getImg_mon() {
        return img_mon;
    }
}
