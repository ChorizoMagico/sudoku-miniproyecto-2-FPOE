package com.example.sudokuminiproyecto2fpoe.view;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class HomeStageAdapter extends Stage implements iHomeStage{

    protected static HomeStage instance;
    protected Scene scene;
    protected FXMLLoader fxmlLoader;

    @Override
    public void initStage() {
        setTitle("Sudoku Intergaláctico");
        setResizable(false);
        getIcons().add(
                new Image(String.valueOf(getClass().getResource("/com/example/sudokuminiproyecto2fpoe/favicon.png"))));
        setScene(scene);
        show();
    }

    public static HomeStage getInstance() throws IOException {
        if(instance == null){
            instance = new HomeStage();
        }
        return instance;
    }
}
