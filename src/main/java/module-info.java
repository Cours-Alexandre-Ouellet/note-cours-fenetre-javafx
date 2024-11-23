module edu.cegepvicto.notescoursfenetrejavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.cegepvicto.notescoursfenetrejavafx to javafx.fxml;
    exports edu.cegepvicto.notescoursfenetrejavafx;
}