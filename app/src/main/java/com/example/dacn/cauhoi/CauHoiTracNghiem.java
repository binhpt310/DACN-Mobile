package com.example.dacn.cauhoi;

import java.util.List;

public class CauHoiTracNghiem {
    private String Question,a,b,c,d,anw;

    public CauHoiTracNghiem(String question, String a, String b, String c, String d, String anw) {
        Question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.anw = anw;
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
}
