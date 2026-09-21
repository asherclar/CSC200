package week2.GraphicsLab;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class QuadraticsGUI extends Application {


    private TextField tfA = new TextField();
    private TextField tfB = new TextField();
    private TextField tfC = new TextField();

    private Label lblOutput = new Label("");

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        VBox mainPane = new VBox();
        mainPane.setSpacing(10);

        Label text1 = new Label("      Enter the coefficeints for your Quadratic");
        mainPane.getChildren().add( text1);

        Node equationPane = getEquationPane();
        mainPane.getChildren().add( equationPane);



        StackPane buttonContainer = new StackPane();
        buttonContainer.setPadding( new Insets(20,0,20,0) );
        Button btCalc = new Button("CALCULATE");
        btCalc.setOnAction( e -> updateOutput(e) );
        buttonContainer.getChildren().add(btCalc);

        mainPane.getChildren().add( buttonContainer);
        mainPane.getChildren().add( this.lblOutput );

        Scene scene = new Scene(mainPane, 280, 450);
        primaryStage.setTitle("My Quadratic Solver"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    private void updateOutput(ActionEvent e) {

        String temp = lblOutput.getText();
        String output =  checkCoefficents();

        double a = Double.parseDouble( tfA.getText());
        double b = Double.parseDouble( tfB.getText());
        double c = Double.parseDouble( tfC.getText());

        Quadratic eq = new Quadratic(a,b,c);

        output += eq.getAllData();

        this.lblOutput.setText(output + "\n\n" + temp);
    }

    private String checkCoefficents() {

        String data = "";

        String strA = tfA.getText();
        //https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
        if( strA.matches("-?\\d+(\\.\\d+)?")) { //if it is a number
            //good ... no action needed
        }else{
            data += "ERROR with your x^2 coefficient ... setting it to 1\n";
            this.tfA.setText("1");
        }

        String strB = tfB.getText();
        if( isNumeric( strB) ){
            //good ... no action needed
        } else{
            data += "ERROR with your x coefficient ... setting it to 1\n";
            this.tfB.setText("1");

        }

        String strC = tfC.getText();
        if( isNumeric( strC) ) {
            //good ... no action needed
        }else{
            data += "ERROR with your constant coeeficient ... setting it to 1\n";
            this.tfC.setText("1");
        }

        return data;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Node getEquationPane() {

        HBox eq = new HBox();

        tfA.setPrefColumnCount(3);
        tfB.setPrefColumnCount(3);
        tfC.setPrefColumnCount(3);

        eq.getChildren().addAll( new Label("  y = "), tfA ,new Label(" x^2 + "),
                tfB ,new Label(" X + "), tfC  );

        return eq;
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
