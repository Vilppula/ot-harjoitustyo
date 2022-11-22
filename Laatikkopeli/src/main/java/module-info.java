module laatikkopeli {
    requires javafx.controls;
    requires javafx.fxml;

    opens laatikkopeli to javafx.fxml;
    exports laatikkopeli;
}
