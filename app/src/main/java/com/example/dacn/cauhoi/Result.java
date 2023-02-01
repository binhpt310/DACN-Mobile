package com.example.dacn.cauhoi;

import java.util.List;

public class Result {
    String email,sub,type,time,code;
    List<CauHoiTracNghiem> done;

    public Result(String email, String sub, String type, String time, String code, List<CauHoiTracNghiem> done) {
        this.email = email;
        this.sub = sub;
        this.type = type;
        this.time = time;
        this.code = code;
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

    @Override
    public String toString() {
        return "Result{" +
                "email='" + email + '\'' +
                ", sub='" + sub + '\'' +
                ", type='" + type + '\'' +
                ", time='" + time + '\'' +
                ", made='" + code + '\'' +
                ", done=" + done +
                '}';
    }
}
