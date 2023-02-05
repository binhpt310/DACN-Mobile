package com.example.dacn.search;

import java.util.ArrayList;
import java.util.List;

public class CardModelDataSearch {
    private String Question,anw;
    private ArrayList<String> exam,review;

    public CardModelDataSearch(String Question,String anw, ArrayList<String> exam,ArrayList<String> review){
        this.Question = Question;
        this.anw = anw;
        this.exam = exam;
        this.review = review;
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

    public List<String> getExam() {
        return exam;
    }

    public void setExam(ArrayList<String> exam) {
        this.exam = exam;
    }

    public List<String> getReview() {
        return review;
    }

    public void setReview(ArrayList<String> review) {
        this.review = review;
    }
}

