module laatikkopeli {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens laatikkopeli to javafx.fxml;
    opens laatikkopeli.controllers to javafx.fxml;
    exports laatikkopeli;
    exports laatikkopeli.controllers;
}