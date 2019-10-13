package com.saeedodak.application.view;

import com.saeedodak.application.Project;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class InformationController {

	@FXML
    private TextArea prime;
    @FXML
    private TextArea alpha;
    @FXML
    private TextArea pu_a;
    @FXML
    private TextArea pv_a;
    @FXML
    private TextArea pu_b;
    @FXML
    private TextArea pv_b;
    @FXML
    private TextArea comm_key;

    private Stage dialogStage;

    private void showInfo() {
    	prime.setWrapText(true);
    	alpha.setWrapText(true);
    	pu_a.setWrapText(true);
    	pv_a.setWrapText(true);
    	pu_b.setWrapText(true);
    	pv_b.setWrapText(true);
    	comm_key.setWrapText(true);
    	prime.setText(Project.DH_A.Prime.toString());
    	alpha.setText(Project.DH_A.alpha.toString());
    	pu_a.setText(Project.DH_A.getPublicKey());
    	pv_a.setText(Project.DH_A.getPrivateKey());
    	pu_b.setText(Project.DH_B.getPublicKey());
    	pv_b.setText(Project.DH_B.getPrivateKey());
    	comm_key.setText(Project.DH_A.commonKey.toString());
    }

    @FXML
    private void initialize() {
    	showInfo();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleOK() {
        dialogStage.close();
    }

}