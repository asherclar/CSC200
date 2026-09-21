package week2.GraphicsLab;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import week1.quadraticLab.My2dPoint;

public class QuadraticGUI extends Application {

    private static final double GRAPH_WIDTH = 800;
    private static final double GRAPH_HEIGHT = 600;

    private Pane graphPane;

    @Override
    public void start(Stage stage) {
        TextField aField = new TextField();
        TextField bField = new TextField();
        TextField cField = new TextField();

        TextField xMinField = new TextField("-10");
        TextField xMaxField = new TextField("10");
        TextField yMinField = new TextField("-10");
        TextField yMaxField = new TextField("10");

        for (TextField field : new TextField[]{
                aField, bField, cField,
                xMinField, xMaxField, yMinField, yMaxField}) {
            field.setPrefWidth(70);
        }

        Button graphButton = new Button("Graph");

        GridPane controls = new GridPane();
        controls.setHgap(8);
        controls.setVgap(8);
        controls.setPadding(new Insets(10));
        controls.setAlignment(Pos.CENTER);

        controls.add(new Label("a:"), 0, 0);
        controls.add(aField, 1, 0);
        controls.add(new Label("b:"), 2, 0);
        controls.add(bField, 3, 0);
        controls.add(new Label("c:"), 4, 0);
        controls.add(cField, 5, 0);

        controls.add(new Label("x min:"), 0, 1);
        controls.add(xMinField, 1, 1);
        controls.add(new Label("x max:"), 2, 1);
        controls.add(xMaxField, 3, 1);
        controls.add(new Label("y min:"), 4, 1);
        controls.add(yMinField, 5, 1);
        controls.add(new Label("y max:"), 6, 1);
        controls.add(yMaxField, 7, 1);

        controls.add(graphButton, 8, 0, 1, 2);

        graphPane = new Pane();
        graphPane.setPrefSize(GRAPH_WIDTH, GRAPH_HEIGHT);
        graphPane.setStyle("-fx-background-color: white;");

        Label information = new Label(
                "Enter coefficients and graph limits, then click Graph.");
        information.setWrapText(true);
        information.setPadding(new Insets(8));

        BorderPane root = new BorderPane();
        root.setTop(controls);
        root.setCenter(graphPane);
        root.setBottom(information);

        graphButton.setOnAction(event -> {
            try {
                double a = Double.parseDouble(aField.getText());
                double b = Double.parseDouble(bField.getText());
                double c = Double.parseDouble(cField.getText());

                double xMin = Double.parseDouble(xMinField.getText());
                double xMax = Double.parseDouble(xMaxField.getText());
                double yMin = Double.parseDouble(yMinField.getText());
                double yMax = Double.parseDouble(yMaxField.getText());

                if (a == 0) {
                    information.setText(
                            "Error: a cannot be 0. This is not a quadratic.");
                    return;
                }

                if (xMin >= xMax || yMin >= yMax) {
                    information.setText(
                            "Error: minimum values must be less than maximum values.");
                    return;
                }

                Quadratic quadratic = new Quadratic(a, b, c);
                drawGraph(quadratic, xMin, xMax, yMin, yMax);
                information.setText(createInformation(quadratic));

            } catch (NumberFormatException e) {
                information.setText("Error: please enter valid numbers.");
            }
        });

        Scene scene = new Scene(root, GRAPH_WIDTH, GRAPH_HEIGHT + 115);
        stage.setTitle("Quadratic Grapher");
        stage.setScene(scene);
        stage.show();
    }

    private void drawGraph(Quadratic quadratic,
                           double xMin, double xMax,
                           double yMin, double yMax) {

        graphPane.getChildren().clear();

        drawGrid(xMin, xMax, yMin, yMax);
        drawAxes(xMin, xMax, yMin, yMax);
        drawQuadratic(quadratic, xMin, xMax, yMin, yMax);

        My2dPoint vertex = quadratic.getVertex();

        drawPoint(vertex.getX(), vertex.getY(),
                xMin, xMax, yMin, yMax,
                "Vertex (" + format(vertex.getX()) + ", "
                        + format(vertex.getY()) + ")", 8, -12);

        double c = quadratic.getC();

        drawPoint(0, c, xMin, xMax, yMin, yMax,
                "Y-int (0, " + format(c) + ")",
                8, 18);

        if (quadratic.getDiscriminant() >= 0) {
            double root1 = quadratic.getRoot1();

            drawPoint(root1, 0, xMin, xMax, yMin, yMax,
                    "X-int (" + format(root1) + ", 0)",
                    8, -12);

            if (quadratic.getNumOfRealRoots() == 2) {
                double root2 = quadratic.getRoot2();

                drawPoint(root2, 0, xMin, xMax, yMin, yMax,
                        "X-int (" + format(root2) + ", 0)",
                        8, 25);
            }
        }
    }

    private void drawGrid(double xMin, double xMax,
                          double yMin, double yMax) {

        // Choose a readable spacing instead of labeling every integer.
        double xTick = chooseTickSpacing(xMax - xMin);
        double yTick = chooseTickSpacing(yMax - yMin);

        double firstX = Math.ceil(xMin / xTick) * xTick;

        for (double x = firstX; x <= xMax + xTick * 0.001; x += xTick) {
            double screenX = toScreenX(x, xMin, xMax);

            Line line = new Line(screenX, 0, screenX, GRAPH_HEIGHT);
            line.setStroke(Color.LIGHTGRAY);
            graphPane.getChildren().add(line);

            Text number = new Text(screenX + 2,
                    GRAPH_HEIGHT - 5, format(x));
            graphPane.getChildren().add(number);
        }

        double firstY = Math.ceil(yMin / yTick) * yTick;

        for (double y = firstY; y <= yMax + yTick * 0.001; y += yTick) {
            double screenY = toScreenY(y, yMin, yMax);

            Line line = new Line(0, screenY,
                    GRAPH_WIDTH, screenY);
            line.setStroke(Color.LIGHTGRAY);
            graphPane.getChildren().add(line);

            Text number = new Text(5, screenY - 2, format(y));
            graphPane.getChildren().add(number);
        }
    }

    private double chooseTickSpacing(double range) {
        // Aim for approximately 8-12 labels across the graph.
        double roughSpacing = range / 10.0;

        double magnitude =
                Math.pow(10, Math.floor(Math.log10(roughSpacing)));

        double normalized = roughSpacing / magnitude;

        if (normalized <= 1) {
            return magnitude;
        } else if (normalized <= 2) {
            return 2 * magnitude;
        } else if (normalized <= 5) {
            return 5 * magnitude;
        } else {
            return 10 * magnitude;
        }
    }

    private void drawAxes(double xMin, double xMax,
                          double yMin, double yMax) {

        if (yMin <= 0 && yMax >= 0) {
            double screenY = toScreenY(0, yMin, yMax);

            Line xAxis = new Line(0, screenY,
                    GRAPH_WIDTH, screenY);
            xAxis.setStroke(Color.BLACK);
            xAxis.setStrokeWidth(2);
            graphPane.getChildren().add(xAxis);

            graphPane.getChildren().add(
                    new Text(GRAPH_WIDTH - 15, screenY - 6, "x"));
        }

        if (xMin <= 0 && xMax >= 0) {
            double screenX = toScreenX(0, xMin, xMax);

            Line yAxis = new Line(screenX, 0,
                    screenX, GRAPH_HEIGHT);
            yAxis.setStroke(Color.BLACK);
            yAxis.setStrokeWidth(2);
            graphPane.getChildren().add(yAxis);

            graphPane.getChildren().add(
                    new Text(screenX + 5, 15, "y"));
        }
    }

    private void drawQuadratic(Quadratic quadratic,
                               double xMin, double xMax,
                               double yMin, double yMax) {

        double a = quadratic.getA();
        double b = quadratic.getB();
        double c = quadratic.getC();

        double previousX = xMin;
        double previousY = a * previousX * previousX
                + b * previousX + c;

        double step = (xMax - xMin) / 1000.0;

        for (double x = xMin + step; x <= xMax; x += step) {
            double y = a * x * x + b * x + c;

            Line segment = new Line(
                    toScreenX(previousX, xMin, xMax),
                    toScreenY(previousY, yMin, yMax),
                    toScreenX(x, xMin, xMax),
                    toScreenY(y, yMin, yMax));

            segment.setStroke(Color.BLUE);
            segment.setStrokeWidth(2);
            graphPane.getChildren().add(segment);

            previousX = x;
            previousY = y;
        }
    }

    private void drawPoint(double x, double y,
                           double xMin, double xMax,
                           double yMin, double yMax,
                           String labelText,
                           double labelOffsetX,
                           double labelOffsetY) {

        // Don't display a point that is outside the user's viewing window.
        if (x < xMin || x > xMax || y < yMin || y > yMax) {
            return;
        }

        double screenX = toScreenX(x, xMin, xMax);
        double screenY = toScreenY(y, yMin, yMax);

        Circle point = new Circle(screenX, screenY, 5);
        point.setFill(Color.RED);
        graphPane.getChildren().add(point);

        Text label = new Text(screenX + labelOffsetX,
                screenY + labelOffsetY, labelText);
        graphPane.getChildren().add(label);
    }

    private double toScreenX(double x,
                             double xMin, double xMax) {
        return (x - xMin) / (xMax - xMin) * GRAPH_WIDTH;
    }

    private double toScreenY(double y,
                             double yMin, double yMax) {
        return GRAPH_HEIGHT
                - (y - yMin) / (yMax - yMin) * GRAPH_HEIGHT;
    }

    private String createInformation(Quadratic quadratic) {
        StringBuilder result = new StringBuilder();

        result.append(quadratic.toString());

        My2dPoint vertex = quadratic.getVertex();

        result.append("    Vertex: (")
                .append(format(vertex.getX()))
                .append(", ")
                .append(format(vertex.getY()))
                .append(")");

        result.append("    Discriminant: ")
                .append(format(quadratic.getDiscriminant()));

        result.append("    Real roots: ")
                .append(quadratic.getNumOfRealRoots());

        if (quadratic.getDiscriminant() < 0) {
            result.append("    No real x-intercepts");
        } else {
            result.append("    X-intercepts: (")
                    .append(format(quadratic.getRoot1()))
                    .append(", 0)");

            if (quadratic.getNumOfRealRoots() == 2) {
                result.append(", (")
                        .append(format(quadratic.getRoot2()))
                        .append(", 0)");
            }
        }

        return result.toString();
    }

    private String format(double number) {
        if (Math.abs(number - Math.round(number)) < 0.00001) {
            return String.format("%.0f", number);
        }
        return String.format("%.2f", number);
    }

    public static void main(String[] args) {
        launch();
    }
}
