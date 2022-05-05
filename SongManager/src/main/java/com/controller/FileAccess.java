package com.controller;

import javafx.collections.*;
import javafx.stage.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import static com.application.Main.*;

public class FileAccess {
    private static String path = System.getProperty("user.home") + "\\Music";

    public static ObservableList<Song> getAllSong() throws IOException {
        ArrayList<Song> listSong = new ArrayList<>();

        DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(path));
        for (Path p : stream) {
            if (Files.isDirectory(p)) {
                String singer = p.getFileName().toString();
                String path2 = path + "\\" + p.getFileName();
                DirectoryStream<Path> stream2 = Files.newDirectoryStream(Paths.get(path2));
                for (Path p2 : stream2) {
                    if (Files.isDirectory(p2)) {
                        String album = p2.getFileName().toString();
                        String path3 = path2 + "\\" + p2.getFileName();
                        DirectoryStream<Path> stream3 = Files.newDirectoryStream(Paths.get(path3));
                        for (Path p3 : stream3) {
                            if (!Files.isDirectory(p3) && p3.toString().endsWith(".mp3")) {
                                listSong.add(new Song(p3.getFileName().toString(), album, singer));
                            }
                        }
                    }
                }
            }
        }

        return FXCollections.observableList(listSong);
    }

    public static ObservableList<Song> getSearchedSong(String filter) throws IOException {
        ArrayList<Song> listSong = new ArrayList<>();

        DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(path));
        for (Path p : stream) {
            if (Files.isDirectory(p)) {
                String singer = p.getFileName().toString();
                String path2 = path + "\\" + p.getFileName();
                DirectoryStream<Path> stream2 = Files.newDirectoryStream(Paths.get(path2));
                for (Path p2 : stream2) {
                    if (Files.isDirectory(p2)) {
                        String album = p2.getFileName().toString();
                        String path3 = path2 + "\\" + p2.getFileName();
                        DirectoryStream<Path> stream3 = Files.newDirectoryStream(Paths.get(path3));
                        for (Path p3 : stream3) {
                            if (!Files.isDirectory(p3) && p3.toString().endsWith(".mp3") && p3.getFileName().toString().contains(filter)) {
                                listSong.add(new Song(p3.getFileName().toString(), album, singer));
                            }
                        }
                    }
                }
            }
        }

        return FXCollections.observableList(listSong);
    }

    public static void changeDirectory() {
        DirectoryChooser dc = new DirectoryChooser();
        File sd = dc.showDialog(st);

        if(sd != null) { path = sd.getAbsolutePath(); }
    }
}
