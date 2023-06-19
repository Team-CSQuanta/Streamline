module com.csquanta.streamline {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.csquanta.streamline to javafx.fxml;
    exports com.csquanta.streamline;

}