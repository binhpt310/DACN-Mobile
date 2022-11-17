package com.example.dacn.hoanthanhbai;

public class NdungCardModel {
    /*String cauhoi,cauchon,dapan;

    public NdungCardModel(String cauhoi, String cauchon, String dapan) {
        this.cauhoi = cauhoi;
        this.cauchon = cauchon;
        this.dapan = dapan;
    }

    public String getCauhoi() {
        return cauhoi;
    }

    public void setCauhoi(String cauhoi) {
        this.cauhoi = cauhoi;
    }

    public String getCauchon() {
        return cauchon;
    }

    public void setCauchon(String cauchon) {
        this.cauchon = cauchon;
    }

    public String getDapan() {
        return dapan;
    }

    public void setDapan(String dapan) {
        this.dapan = dapan;
    }*/

    String Question,a,anw;

    public NdungCardModel(String question, String a, String anw) {
        Question = question;
        this.a = a;
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

    public String getAnw() {
        return anw;
    }

    public void setAnw(String anw) {
        this.anw = anw;
    }
}
