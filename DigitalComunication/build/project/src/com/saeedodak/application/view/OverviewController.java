package com.saeedodak.application.view;

import com.saeedodak.application.Main;
import com.saeedodak.application.Project;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;

public class OverviewController {

    @FXML
    private TextArea plain;
    @FXML
    private TextArea encrypted;
    @FXML
    private TextArea dectypted;

    boolean isKeyMaked = false;

    private Main main;

    /**
     * Fills all text fields to show details.
     * If the specified message is null, all text fields are cleared.
     * @param Plain text
     * @param cypher text
     * @param decrypted text
     */
    private void showDetails(String Plain, String Encrypted, String Decrypted) {
        if(Plain != null && Encrypted != null && Decrypted != null) {
            plain.setText(Plain);
            encrypted.setText(Encrypted);
            dectypted.setText(Decrypted);
        }
        else {
        	plain.setText("");
            encrypted.setText("");
            dectypted.setText("");
        }
    }

    /**
     * Called when the user clicks on the Exit button.
     */
    @FXML
    private void handleExit() {
    	System.exit(0);
    }

    /**
     * Called when the user clicks on the Send button.
     */
    @FXML
    private void handleSend() {
    	if(isKeyMaked == false) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Key Exchange Error");
            alert.setHeaderText("Key Exchange is not OK.");
            alert.setContentText("Click Key Exchange First!");
            alert.showAndWait();
    		return;
    	}
    	boolean okClicked = main.showGetMessage();
    	if(okClicked) {
    		Project.send(GetMessageController.text);
    		showDetails(Project.plain_str, Project.cipher_str, Project.decrypted_str);
    	}
	}

	/**
     * Called when the user clicks on the Key Exchange button.
     */
    @FXML
    private void handleKeyExchange() {
    	Project.genrator();
    	isKeyMaked = true;
    	main.showInformation();
    }

	/**
     * Called when the user clicks on the info.
     */
    @FXML
    private void handleInfo() {
    	if(isKeyMaked == false) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Key Exchange Error");
            alert.setHeaderText("Key Exchange is not OK.");
            alert.setContentText("Click Key Exchange First!");
            alert.showAndWait();
    		return;
    	}
    	main.showInformation();
    }

    public OverviewController() {}

    @FXML
    private void initialize() {
    	plain.setWrapText(true);
    	encrypted.setWrapText(true);
    	dectypted.setWrapText(true);
    }

    public void setMain(Main main) {
        this.main = main;
    }

}