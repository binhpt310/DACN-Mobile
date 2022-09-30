package com.example.dacn.model;

public class Bo_de_thi_model {
    String so_thu_tu_bo_de_thi;
    String ten_bo_de_thi;

    public Bo_de_thi_model(String so_thu_tu_bo_de_thi, String ten_bo_de_thi) {
        this.so_thu_tu_bo_de_thi = so_thu_tu_bo_de_thi;
        this.ten_bo_de_thi = ten_bo_de_thi;

    }
    public String getSo_thu_tu_bo_de_thi() {
        return so_thu_tu_bo_de_thi;
    }

    public String getTen_bo_de_thi() {
        return ten_bo_de_thi;
    }
}
