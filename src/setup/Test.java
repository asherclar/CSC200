package setup;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Test extends Application {

    @Override
    public void start(Stage stage) {
        Button btn = new Button("OK");
        Scene scene = new Scene(btn, 250, 250);
        btn.setOnAction(e -> {
            stage.close();
        });
        stage.setScene(scene);
        stage.setTitle("Test");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}