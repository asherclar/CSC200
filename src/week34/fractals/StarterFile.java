package week34.fractals;

// Java program to create a line with starting
// and ending coordinates passed as arguments
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.Group;

public class StarterFile extends Application {
	Group group;

	// launch the application
	public void start(Stage stage)
	{
		
		// set title for the stage
		stage.setTitle("creating line");

		// create a line
		Line line = new Line(0.0f, 0.0f, 0.0f, 0.0f); //UNUSED

		// create a Group
		group = new Group();

				
		draw(6, 100,100,300,300);
		draw(6, 300,300,100,100);
		

		draw(5, 300,100,100,300);
		draw(5, 100,300,300,100);

		// create a scene
		Scene scene = new Scene(group, 500, 400);

		// set the scene
		stage.setScene(scene);

		stage.show();
	}
	
	
	private void draw(int iteration, double x1, double y1, double x2, double y2)
	   {

	      if (iteration == 1)
	      {
	    	  Line line = new Line(x1, y1, x2, y2 );
	    	  this.group.getChildren().add( line );
	    	  
	      }
		 else  // iteration > 0
	      {
	         /*
	         double angle = 60 * Math.PI / 180; // 60 degrees
	         double dx = (x2 - x1) / 3;
	         double dy = (y2 - y1) / 3;
	         double x3 = x1 + dx;
	         double y3 = y1 + dy;
	         double x4 = x2 - dx;
	         double y4 = y2 - dy;
	         double x5 = x3 + dx * Math.cos(angle) + dy * Math.sin(angle);
	         double y5 = y3 + dy * Math.cos(angle) - dx * Math.sin(angle);
	         draw(g2, iteration - 1, x1, y1, x3, y3 );
	         draw(g2, iteration - 1, x3, y3, x5, y5);
	         draw(g2, iteration - 1, x5, y5, x4, y4);
	         draw(g2, iteration - 1, x4, y4, x2, y2);
	         */
    		 double deltaX = x2 - x1;
			 double deltaY = y2 - y1;

			 double ax = x1 + (int)(deltaX / 3);
			 double ay = y1 + (int)(deltaY / 3);

			 double bx = x1 + (int)(deltaX * 2 / 3);
			 double by = y1 + (int)(deltaY * 2/ 3);

			 double cx = (ax + bx) / 2 - (Math.sqrt(3) / 2) * (ay - by);
			 double cy = (ay + by) / 2 - (Math.sqrt(3) / 2) * (bx - ax);

			 // Recursively display snow flakes on lines
			 draw( iteration - 1, x1, y1, ax, ay);
			 draw(iteration - 1, ax, ay, cx, cy);
			 draw(iteration - 1, cx, cy, bx, by);
			 draw(iteration - 1, bx, by, x2, y2);
	      }
	   }
	
	
	

	// Main Method
	public static void main(String args[])
	{
		// launch the application
		launch(args);
	}
}
