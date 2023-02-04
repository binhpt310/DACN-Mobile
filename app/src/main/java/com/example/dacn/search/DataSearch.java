package com.example.dacn.search;

import java.util.ArrayList;
import java.util.List;

public class DataSearch {
    private String Question,anw,exam,review;
    private List<String> listexam, listreview;

    public DataSearch(String question, String anw, String exam, String review, List<String> listexam, List<String> listreview) {
        Question = question;
        this.anw = anw;
        this.exam = exam;
        this.review = review;
        this.listexam = listexam;
        this.listreview = listreview;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnw() {
        return anw;
    }

    public void setAnw(String anw) {
        this.anw = anw;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public List<String> getListexam() {
        return listexam;
    }

    public void setListexam(List<String> listexam) {
        this.listexam = listexam;
    }

    public List<String> getListreview() {
        return listreview;
    }

    public void setListreview(List<String> listreview) {
        this.listreview = listreview;
    }
}

