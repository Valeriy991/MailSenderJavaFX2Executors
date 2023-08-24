module com.valeriygulin.mailsenderjavafx2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.valeriygulin.mailsenderjavafx2 to javafx.fxml;
    exports com.valeriygulin.mailsenderjavafx2;
    exports com.valeriygulin.mailsenderjavafx2.controllers;
}