package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXApplication extends Application {
    public FXApplication() {}

    /**
     * The main entry point for the JavaFX application.
     *
     * @param primaryStage the primary stage for the application
     * @throws Exception if an exception occurs during application startup
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/managerPanel.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root, 770, 560);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Manager Panel");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * The method called when the application is stopped.
     *
     * @throws Exception if an exception occurs during application stop
     */
    @Override
    public void stop() throws Exception {
        super.stop();
        Client client = new Client();
        client.stopConnection();
    }
}
