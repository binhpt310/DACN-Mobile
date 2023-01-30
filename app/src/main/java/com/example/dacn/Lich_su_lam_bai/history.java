package com.example.dacn.Lich_su_lam_bai;

public class history {

    private String heading;
    private int Rtrue;
    private int Rfalse;
    public history(String heading, int Rtrue, int Rfalse) {
        this.heading = heading;
        this.Rtrue = Rtrue;
        this.Rfalse = Rfalse;

    }

    public String getHeading(){
        return heading;
    }
    public void setHeading(String heading){
        this.heading = heading;
    }

    public int getTrue(){
        return Rtrue;
    }
    public void setTrue(int Rtrue){
        this.Rtrue = Rtrue;
    }

    public int getFalse(){
        return Rfalse;
    }
    public void setFalse(int Rfalse){
        this.Rfalse = Rfalse;
    }


}
