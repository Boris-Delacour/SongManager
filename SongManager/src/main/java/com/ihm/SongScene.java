package com.ihm;

import com.controller.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.*;

import static com.controller.FileAccess.*;

public class SongScene {

    public static TableView<Song> tableSong = new TableView<>();

    public static Scene initScene() throws IOException {
        BorderPane bp = new BorderPane();
        bp.setPrefSize(800, 500);

        GridPane top = new GridPane();

        TextField searchSong = new TextField();
        searchSong.setPromptText("Recherche...");
        searchSong.setMaxSize(300, 25);

        Button bSearch = new Button("Rechercher");
        bSearch.setMinSize(200, 25);
        bSearch.setOnAction(actionEvent -> {
            try { tableSong.setItems(getSearchedSong(searchSong.getText())); }
            catch (IOException e) { e.printStackTrace(); }
        });

        top.add(searchSong, 0, 0);
        top.getColumnConstraints().add(0, new ColumnConstraints(400));
        top.add(bSearch, 1, 0);

        bp.setTop(top);
        BorderPane.setMargin(top, new Insets(5,5,5,5));

        TableColumn<Song, String> colSong = new TableColumn<>("Chanson");
        colSong.setCellValueFactory(p -> p.getValue().titleProperty());
        TableColumn<Song, String> colAlbum = new TableColumn<>("Album");
        colAlbum.setCellValueFactory(p -> p.getValue().albumProperty());
        TableColumn<Song, String> colSinger = new TableColumn<>("Chanteur");
        colSinger.setCellValueFactory(p -> p.getValue().singerProperty());

        tableSong.getColumns().addAll(colSong, colAlbum, colSinger);
        tableSong.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableSong.setItems(FileAccess.getAllSong());

        bp.setCenter(tableSong);
        BorderPane.setMargin(tableSong, new Insets(0,5,5,5));

        GridPane bot = new GridPane();
        Button bf = new Button("Changer de répertoire de musique");
        bf.setAlignment(Pos.BASELINE_RIGHT);
        bf.setOnAction(actionEvent -> {
            changeDirectory();
            try { tableSong.setItems(FileAccess.getAllSong()); }
            catch (IOException e) { e.printStackTrace(); }
        });

        bot.add(bf, 1, 0);
        bot.setAlignment(Pos.BASELINE_RIGHT);

        bp.setBottom(bot);
        BorderPane.setMargin(bot, new Insets(0,5,5,5));

        return new Scene(bp);
    }
}
