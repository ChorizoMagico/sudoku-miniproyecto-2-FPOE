package com.example.sudokuminiproyecto2fpoe.view;

import com.example.sudokuminiproyecto2fpoe.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class HomeStage extends HomeStageAdapter {

    public HomeStage() throws IOException{
        fxmlLoader = new FXMLLoader(
                Main.class.getResource("home-view.fxml")
        );
        Parent root = fxmlLoader.load();
        scene = new Scene(root);
        initStage();
    }


}
