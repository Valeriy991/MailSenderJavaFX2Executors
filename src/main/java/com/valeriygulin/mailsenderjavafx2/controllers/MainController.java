package com.valeriygulin.mailsenderjavafx2.controllers;

import com.valeriygulin.mailsenderjavafx2.App;
import com.valeriygulin.mailsenderjavafx2.model.Mail;
import com.valeriygulin.mailsenderjavafx2.model.MailType;
import com.valeriygulin.mailsenderjavafx2.model.RegionType;
import com.valeriygulin.mailsenderjavafx2.service.MailService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.List;

public class MainController {
    public TextField textFieldTo;
    public TextField textFieldText;
    private MailService mailService;

    @FXML
    public void initialize() {
        mailService = new MailService();
    }

    @FXML
    public void buttonSendMessages(ActionEvent actionEvent) {
        String to = this.textFieldTo.getText();
        String[] split = to.split("; ");
        List<String> listTo = Arrays.stream(split).toList();
        if (split.length == 0) {
            App.showAlert("Error!", "Write field \"to\"!", Alert.AlertType.ERROR);
            return;
        }
        String text = this.textFieldText.getText();
        if (text.isEmpty()) {
            App.showAlert("Error!", "Write field \"text\"!", Alert.AlertType.ERROR);
            return;
        }
        this.mailService.addMail(new Mail(listTo, text));
        //App.showAlert("Info!", "Mail has been sent to addresses!", Alert.AlertType.INFORMATION);
    }

    @FXML
    public void buttonCleanAllFields(ActionEvent actionEvent) {
        this.textFieldTo.clear();
        this.textFieldText.clear();
        //App.showAlert("Info!", "Fields has been cleaned!", Alert.AlertType.CONFIRMATION);
    }

    @FXML
    public void buttonGenerateAll(ActionEvent actionEvent) {
        this.textFieldTo.clear();
        this.textFieldText.clear();
        int quantity = (int) (Math.random() * 10) + 1;
        StringBuilder addresses = new StringBuilder();
        for (int i = 0; i < quantity; i++) {
            int random = (int) (Math.random() * 10) + 5;
            for (int j = 0; j < random; j++) {
                int random2 = (int) (Math.random() * 2) + 1;
                if (random2 == 1) {
                    addresses.append((char) ('a' + (char) (Math.random() * ('z' - 'a' + 1))));
                } else {
                    addresses.append((char) ('0' + (char) (Math.random() * ('9' - '0' + 1))));
                }
            }
            MailType[] mailTypes = MailType.values();
            int mailTypeRandom = (int) (Math.random() * mailTypes.length);
            RegionType[] regionTypes = RegionType.values();
            int regionTypeRandom = (int) (Math.random() * regionTypes.length);
            addresses.append("@").append(mailTypes[mailTypeRandom]).append(".").
                    append(regionTypes[regionTypeRandom]).append("; ");
        }
        int textLength = (int) (Math.random() * 30) + 10;
        StringBuilder resBuilder = new StringBuilder();
        for (int i = 0; i < textLength; i++) {
            int randomChar = (int) (Math.random() * 4) + 1;
            if (randomChar == 1 || randomChar == 2 || randomChar == 3) {
                char chars = (char) ('a' + (char) (Math.random() * ('z' - 'a' + 1)));
                resBuilder.append(chars);
            } else {
                char digits = (char) ('0' + (char) (Math.random() * ('9' - '0' + 1)));
                resBuilder.append(digits);
            }
        }
        String addressesString = addresses.toString();
        String text = resBuilder.toString();
        this.textFieldTo.setText(addressesString);
        this.textFieldText.setText(text);
    }
}
