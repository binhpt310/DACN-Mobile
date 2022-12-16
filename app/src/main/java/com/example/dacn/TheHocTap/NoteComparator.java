package com.example.dacn.TheHocTap;


import com.example.dacn.TheHocTap.Model.Note;

import java.util.Comparator;

public class NoteComparator implements Comparator<Note> {
    public int compare(Note a, Note b){
        return a.getTitle().toLowerCase().compareTo(b.getTitle().toLowerCase());
    }
}

