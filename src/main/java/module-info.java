module com.csquanta.streamline {
    requires javafx.controls;
    requires javafx.fxml;
    opens com.csquanta.streamline to javafx.fxml;
    exports com.csquanta.streamline;
    opens com.csquanta.streamline.Controllers;
    exports com.csquanta.streamline.Controllers;
    requires java.sql;
    requires io.github.willena.sqlitejdbc;
    requires AnimateFX;
    requires atlantafx.base;
    requires javafx.media;


}

