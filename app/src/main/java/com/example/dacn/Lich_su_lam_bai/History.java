package com.example.dacn.Lich_su_lam_bai;

import java.io.Serializable;

public class History implements Serializable {

    private String heading;
    private int Rtrue;
    private int Rfalse;
    private int Rnull;

    public History(String heading) {
        this.heading = heading;
    }

    public History(String heading, int Rtrue, int Rfalse) {
        this.heading = heading;
        this.Rtrue = Rtrue;
        this.Rfalse = Rfalse;
    }

    public History(String heading, int rtrue, int rfalse, int rnull) {
        this.heading = heading;
        Rtrue = rtrue;
        Rfalse = rfalse;
        Rnull = rnull;
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

    public int getRnull() {
        return Rnull;
    }

    public void setRnull(int rnull) {
        Rnull = rnull;
    }
}
