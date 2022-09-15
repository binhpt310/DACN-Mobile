package com.example.dacn.cauhoi;

import java.util.List;

public class CauHoiTracNghiem {
    private int _id;
    private String Question;
    private List<CauTraLoi> Answer;

    public CauHoiTracNghiem(int _id, String question, List<CauTraLoi> answer) {
        this._id = _id;
        Question = question;
        Answer = answer;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public List<CauTraLoi> getAnswer() {
        return Answer;
    }

    public void setAnswer(List<CauTraLoi> answer) {
        Answer = answer;
    }
}
