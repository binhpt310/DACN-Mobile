package com.example.dacn.Bo_de_thi;

import java.io.Serializable;

public class BoDe implements Serializable {
    private String Code;

    public BoDe(String Code){
        this.Code = Code;
    }
    public String getCode() {
        return Code;
    }
    public void setCode() {
        this.Code = Code;
    }
}
