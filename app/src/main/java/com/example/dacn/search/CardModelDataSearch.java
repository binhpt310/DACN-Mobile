package com.example.dacn.search;

public class CardModelDataSearch {
    private String question_search;
    private String answer_search;
    private String[] review;
    private String[] exam;


    public CardModelDataSearch(String question_search, String answer_search, String[] review, String[] exam) {
        this.question_search = question_search;
        this.answer_search = answer_search;
        this.review = review;
        this.exam = exam;
    }
    public String getQuestion_search() {
        return question_search;
    }

    public void setQuestion_search(String question_search) {
        this.question_search = question_search;
    }

    public String getAnswer_search() {
        return answer_search;
    }

    public void setAnswer_search(String answer_search) {
        this.answer_search = answer_search;
    }

    public String[] getReviewList_search() {
        return review;
    }

    public void setReviewList_search(String[] review) {
        this.review = review;
    }

    public String[] getExamList() {
        return exam;
    }

    public void setExamList(String[] exam) {
        this.exam = exam;
    }
}

