package com.example.dacn.hoanthanhbai;

public class NdungCardModel {

    String Questions,Selected,aws,check;

    public NdungCardModel(String questions, String selected, String aws, String check) {
        Questions = questions;
        Selected = selected;
        this.aws = aws;
        this.check = check;
    }

    public String getQuestions() {
        return Questions;
    }

    public void setQuestions(String questions) {
        Questions = questions;
    }

    public String getSelected() {
        return Selected;
    }

    public void setSelected(String selected) {
        Selected = selected;
    }

    public String getAws() {
        return aws;
    }

    public void setAws(String aws) {
        this.aws = aws;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }
}
