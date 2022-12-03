module com.example.gridgamefinal {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gridgamefinal to javafx.fxml;
    exports com.example.gridgamefinal;
}
