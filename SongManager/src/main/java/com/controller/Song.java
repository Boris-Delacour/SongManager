package com.controller;

import javafx.beans.property.*;

public class Song {
    public StringProperty title = new SimpleStringProperty();
    public StringProperty album = new SimpleStringProperty();
    public StringProperty singer = new SimpleStringProperty();

    public Song(String t, String a, String s) {
        this.title.setValue(t);
        this.album.setValue(a);
        this.singer.setValue(s);
    }

    public StringProperty titleProperty() { return title; }
    public StringProperty albumProperty() { return album; }
    public StringProperty singerProperty() { return singer; }

    public String toString() {
        return title.getValue() + " dans l'album " + album.getValue() + " chanter par " + singer.getValue();
    }
}
