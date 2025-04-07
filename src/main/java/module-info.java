module com.example.sudokuminiproyecto2fpoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sudokuminiproyecto2fpoe to javafx.fxml;
    opens com.example.sudokuminiproyecto2fpoe.controller to javafx.fxml;
    exports com.example.sudokuminiproyecto2fpoe;
}