package com.example.dacn.cauhoi;

import java.util.List;

public class CauHoiTracNghiem {
    private String Question,a,b,c,d,anw,cauhoidachon,dungsai;

    public CauHoiTracNghiem(String question, String a, String b, String c, String d, String anw, String cauhoidachon, String dungsai) {
        Question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.anw = anw;
        this.cauhoidachon = cauhoidachon;
        this.dungsai = dungsai;
    }

    public CauHoiTracNghiem(String question, String anw, String cauhoidachon) {
        Question = question;
        this.anw = anw;
        this.cauhoidachon = cauhoidachon;
    }

    public String getCauhoidachon() {
        return cauhoidachon;
    }

    public void setCauhoidachon(String cauhoidachon) {
        this.cauhoidachon = cauhoidachon;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getAnw() {
        return anw;
    }

    public void setAnw(String anw) {
        this.anw = anw;
    }

    public String getDungsai() {
        return dungsai;
    }

    public void setDungsai(String dungsai) {
        this.dungsai = dungsai;
    }
}
