module com.oumarket.quanlychuoisieuthibanle {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.oumarket.quanlychuoisieuthibanle to javafx.fxml;
    exports com.oumarket.quanlychuoisieuthibanle;
    exports com.oumarket.pojo;
}
