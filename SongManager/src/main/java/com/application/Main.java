package com.application;

import javafx.application.*;
import javafx.stage.*;

import java.io.*;

import static com.ihm.SongScene.*;

public class Main extends Application {
    public static Stage st;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage ps) throws IOException {
        st = ps;
        st.setTitle("Gestionnaire de chanson");
        st.setScene(initScene());
        st.sizeToScene();
        st.show();
    }
}
