module com.example.javafxslidesdemo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.javafxslidesdemo to javafx.fxml;
    exports com.example.javafxslidesdemo;
}