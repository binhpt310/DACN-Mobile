package com.example.dacn.cauhoi;

public class CauTraLoi {
    private String content;
    private Boolean isTrue;

    public CauTraLoi(String content, Boolean isTrue) {
        this.content = content;
        this.isTrue = isTrue;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getTrue() {
        return isTrue;
    }

    public void setTrue(Boolean aTrue) {
        isTrue = aTrue;
    }
}
