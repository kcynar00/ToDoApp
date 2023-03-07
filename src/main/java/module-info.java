module com.apk.todo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.datatransfer;
    requires com.jfoenix;

    opens com.apk.todo to javafx.fxml;
    exports com.apk.todo;
}