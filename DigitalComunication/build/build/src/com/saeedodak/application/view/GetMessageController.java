package com.saeedodak.application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class GetMessageController {
	@FXML
    private TextArea textArea;

    private Stage dialogStage;

    public static  String text;

    private boolean okClicked = false;

    @FXML
    private void initialize() {}

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
    	this.dialogStage = dialogStage;
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if(isInputValid()) {
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";
    	text = textArea.getText();
    	if(text == null || text.compareTo("") == 0) {
    		errorMessage = "Input is empty!";
        }
        if(errorMessage.length() == 0) return true;
        else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please enter a text to send.");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

}