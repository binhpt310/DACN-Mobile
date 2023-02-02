package com.example.dacn.Lich_su_lam_bai;

import java.io.Serializable;

public class History implements Serializable {

    private String code,socauchualam,socaudung,socausai,time;

    public History(String code, String socauchualam, String socaudung, String socausai, String time) {
        this.code = code;
        this.socauchualam = socauchualam;
        this.socaudung = socaudung;
        this.socausai = socausai;
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSocauchualam() {
        return socauchualam;
    }

    public void setSocauchualam(String socauchualam) {
        this.socauchualam = socauchualam;
    }

    public String getSocaudung() {
        return socaudung;
    }

    public void setSocaudung(String socaudung) {
        this.socaudung = socaudung;
    }

    public String getSocausai() {
        return socausai;
    }

    public void setSocausai(String socausai) {
        this.socausai = socausai;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
