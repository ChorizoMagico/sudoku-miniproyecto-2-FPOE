package com.example.sudokuminiproyecto2fpoe.controller;

import com.example.sudokuminiproyecto2fpoe.model.Checks;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;



import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;


public class HomeController {

    @FXML
    private Label errorLabel;

    @FXML
    private Button helpButton;

    @FXML
    private GridPane sudokuGrid;

    private ArrayList<ArrayList<TextField>> board;
    private Checks checks;
    private Checks.Positions positions;

    @FXML
    private void initialize() {
        initializeBoardCells();
        checks = new Checks(board);
        positions = checks.new Positions();
        positions.fillAllBlocks();
    }

    private void initializeBoardCells() {
        board = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j < 6; j++) {
                TextField field = new TextField();
                field.setStyle(
                        "-fx-background-color: transparent;" +
                                "-fx-border-color: #A8DADC;" +        // Azul claro para los bordes
                                "-fx-pref-width: 100;" +
                                "-fx-pref-height: 100;" +
                                "-fx-text-fill: #1D3557;" +            // Azul oscuro para el texto (casi negro)
                                "-fx-font-weight: bold;"               // Texto más grueso
                );

                // Agregar contorno más resaltado (blanco)
                DropShadow textShadow = new DropShadow();
                textShadow.setColor(Color.WHITE);  // Contorno blanco
                textShadow.setRadius(3);           // Aumentar el tamaño del contorno
                textShadow.setOffsetX(2);         // Aumentar desplazamiento en X
                textShadow.setOffsetY(2);         // Aumentar desplazamiento en Y

                field.setEffect(textShadow);      // Aplicar el efecto

                field.setAlignment(Pos.CENTER);

                board.get(i).add(field);
                handleTextFields(i, j);
                sudokuGrid.add(field, j, i);
            }
        }
    }

    private void handleTextFields(int row, int col) {
        board.get(row).get(col).textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.isEmpty()) {
                    board.get(row).get(col).setStyle(
                            "-fx-background-color: transparent;" +
                                    "-fx-border-color: #A8DADC;" +       // Azul claro para los bordes
                                    "-fx-pref-width: 100;" +
                                    "-fx-pref-height: 100;" +
                                    "-fx-text-fill: #1D3557;" +           // Azul oscuro para el texto
                                    "-fx-font-weight: bold;"              // Texto más grueso
                    );
                    // Agregar contorno blanco más resaltado
                    DropShadow textShadow = new DropShadow();
                    textShadow.setColor(Color.WHITE);
                    textShadow.setRadius(3);
                    textShadow.setOffsetX(2);
                    textShadow.setOffsetY(2);

                    board.get(row).get(col).setEffect(textShadow); // Aplicar el efecto
                }
                else{
                    if (!newValue.matches("[1-6]?")) {
                        board.get(row).get(col).setText(oldValue);
                    }
                    else{
                        if(!checks.allChecks(row, col, newValue)){

                            board.get(row).get(col).setStyle(
                                    "-fx-background-color: transparent;" +
                                            "-fx-border-color: #E63946;" +       // Coral (rojo alerta)
                                            "-fx-pref-width: 100;" +
                                            "-fx-pref-height: 100;" +
                                            "-fx-text-fill: #1D3557;" +           // Azul oscuro para el texto
                                            "-fx-font-weight: bold;"             // Texto más grueso
                            );
                            // Agregar contorno blanco más resaltado
                            DropShadow textShadow = new DropShadow();
                            textShadow.setColor(Color.WHITE);
                            textShadow.setRadius(3);
                            textShadow.setOffsetX(2);
                            textShadow.setOffsetY(2);

                            board.get(row).get(col).setEffect(textShadow); // Aplicar el efecto
                        } else {
                            // Cambiar color del texto a negro cuando el número sea válido
                            board.get(row).get(col).setStyle(
                                    "-fx-background-color: transparent;" +
                                            "-fx-border-color: #2A9D8F;" +       // Verde tropical para el borde
                                            "-fx-pref-width: 100;" +
                                            "-fx-pref-height: 100;" +
                                            "-fx-text-fill: #000000;" +           // Texto negro
                                            "-fx-font-weight: bold;"             // Texto más grueso
                            );
                            // Agregar contorno blanco más resaltado
                            DropShadow textShadow = new DropShadow();
                            textShadow.setColor(Color.WHITE);
                            textShadow.setRadius(3);
                            textShadow.setOffsetX(2);
                            textShadow.setOffsetY(2);

                            board.get(row).get(col).setEffect(textShadow); // Aplicar el efecto
                            board.get(row).get(col).setDisable(true);
                            positions.clearPositions(row, col);
                            checks.setCounter();
                        }
                    }
                    errorLabel.setText("Aviso: " + checks.isGameOver());
                    }
            }
        });
    }

    @FXML
    void handleHelp(ActionEvent event) {
        positions.helpFunctionality();
        System.out.println(checks.counter);
    }
}