package week34.fractals;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


/**
 * Draws a fractal tree
 * @author Asher Clar
 * @version 9.20.26
 */
public class FractalTree extends Application {
    Pane pane;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Fractal Tree");
        pane = new Pane();
        pane.setMinHeight(300);
        pane.setMinWidth(250);
        HBox inputs = new HBox();
        TextField depthInput = new TextField();
        depthInput.setText("4");
        TextField angleInput = new TextField();
        angleInput.setText("15");
        inputs.getChildren().addAll(
                new Label("Depth: "), depthInput, new Label(" Angle offset: "), angleInput
            );
        Pane spacer = new Pane();
        spacer.setMinHeight(50);
        VBox vbox = new VBox(pane, spacer, inputs);

        depthInput.setOnAction(e -> {
            pane.getChildren().clear();
            draw(0, Math.toRadians(Double.parseDouble(angleInput.getText())), 250, 300, 0, Integer.parseInt(depthInput.getText()));
        });
        angleInput.setOnAction(e -> {
            pane.getChildren().clear();
            draw(0, Math.toRadians(Double.parseDouble(angleInput.getText())), 250, 300, 0, Integer.parseInt(depthInput.getText()));
        });

        Scene scene = new Scene(vbox, 500, 400);
        stage.setScene(scene);
        stage.show();
    }

    public void draw(int depth, double angle, double x, double y, double angleOffset, int maxDepth){

        if(depth==maxDepth) return;
        double length = 100*(Math.pow(.5, depth));
        double endX = x + length*Math.sin(angleOffset);
        double endY = y - length*Math.cos(angleOffset);
        Line line = new Line(x, y, endX, endY);
        line.setStroke(Color.BLACK);
        pane.getChildren().add(line);
        draw(depth+1, angle, endX, endY, angleOffset+angle, maxDepth);
        draw(depth+1, angle, endX, endY, angleOffset-angle, maxDepth);
    }

    public static void main(String[] args){
        launch(args);
    }
}
