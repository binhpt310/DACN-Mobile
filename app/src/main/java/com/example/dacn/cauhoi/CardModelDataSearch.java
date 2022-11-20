package com.example.dacn.cauhoi;

public class CardModelDataSearch {
    private String question_search;
    private String answer_search;


    public CardModelDataSearch(String question_search, String answer_search) {
        this.question_search = question_search;
        this.answer_search = answer_search;
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

}

