package com.saeedodak.application;

import java.io.IOException;

import com.saeedodak.application.view.GetMessageController;
import com.saeedodak.application.view.InformationController;
import com.saeedodak.application.view.OverviewController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Diffie-Hellman Key Exchange");
        initRootLayout();
        showOverview();
    }

    public boolean showGetMessage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/GetMessage.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Input Message");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            GetMessageController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();
            return controller.isOkClicked();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showInformation() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Information.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Information");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            InformationController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            dialogStage.setMaximized(true);
            dialogStage.showAndWait();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void showOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Overview.fxml"));
            AnchorPane Overview = (AnchorPane) loader.load();

            rootLayout.setCenter(Overview);

            OverviewController controller = loader.getController();
            controller.setMain(this);

        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}