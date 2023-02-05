package com.example.dacn.cauhoi;

import java.util.List;

public class Result {
    String email,sub,type,time,code,socauchualam,socaudung,socausai;
    List<CauHoiTracNghiem> done;

    public Result(String email, String sub, String type, String time, String code, String socauchualam, String socaudung, String socausai, List<CauHoiTracNghiem> done) {
        this.email = email;
        this.sub = sub;
        this.type = type;
        this.time = time;
        this.code = code;
        this.socauchualam = socauchualam;
        this.socaudung = socaudung;
        this.socausai = socausai;
        this.done = done;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<CauHoiTracNghiem> getDone() {
        return done;
    }

    public void setDone(List<CauHoiTracNghiem> done) {
        this.done = done;
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

    @Override
    public String toString() {
        return "Result{" +
                "email='" + email + '\'' +
                ", sub='" + sub + '\'' +
                ", type='" + type + '\'' +
                ", time='" + time + '\'' +
                ", code='" + code + '\'' +
                ", socauchualam='" + socauchualam + '\'' +
                ", socaudung='" + socaudung + '\'' +
                ", socausai='" + socausai + '\'' +
                ", done=" + done +
                '}';
    }
}
