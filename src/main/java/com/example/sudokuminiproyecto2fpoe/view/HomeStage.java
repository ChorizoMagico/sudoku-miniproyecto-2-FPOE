package com.example.sudokuminiproyecto2fpoe.view;

import com.example.sudokuminiproyecto2fpoe.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeStage extends Stage {
    private static HomeStage instance;
    Scene scene;



    public HomeStage() throws IOException{
        FXMLLoader fmlLoader = new FXMLLoader(
                Main.class.getResource("home-view.fxml")
        );
        Parent root = fmlLoader.load();
        scene = new Scene(root);
        initStage();
    }

    private void initStage() {
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
