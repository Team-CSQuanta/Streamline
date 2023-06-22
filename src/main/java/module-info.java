module com.csquanta.streamline {
    requires javafx.controls;
    requires javafx.fxml;
    requires google.api.client;
    requires com.google.api.client;
    requires AnimateFX;
    requires com.google.api.client.json.jackson2;
    opens com.csquanta.streamline to javafx.fxml;
    exports com.csquanta.streamline;
    opens com.csquanta.streamline.Controllers;
    exports com.csquanta.streamline.Controllers;
    requires java.sql;
    requires io.github.willena.sqlitejdbc;

}

