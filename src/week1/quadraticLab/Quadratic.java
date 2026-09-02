package week1.quadraticLab;

/**
 * @author Asher Clar
 * @version 1.28.25
 *
 * Represents a quadratic in the form of f(x) = ax^2 + bx + c.
 */
public class Quadratic {

    //data fields (instance data)
    private double a;
    private double b;
    private double c;

    private double discriminant;

    //constructors

    public Quadratic(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;

        this.discriminant = b*b - 4 * a * c;
    }


    //methods

    public double getDiscriminant() {
        return discriminant;
    }

    public int getNumOfRealRoots(){
        if( this.discriminant < 0 ){
            return 0;
        }

        else if( this.discriminant == 0 ){
            return 1;
        }
        return 2;
    }

    //BONUS ...
    public int getNumOfRationalRoots(){
        if( this.discriminant < 0 ){
            return 0;
        }

        else if( this.discriminant == 0 ){
            return 1;
        }


        return 2;
    }


    public int getNumOfImaginaryRoots(){
        if( this.discriminant < 0 ){
            return 2;
        }

        return 0;
    }

    public double getRoot1(){
        return (-1*b + Math.sqrt( this.discriminant) ) / (2 * a);
    }

    public double getRoot2(){
        if( this.discriminant < 0 ) {
            throw new ArithmeticException("Roots are imaginary");
        }
      //  System.out.println("\nRoot 2 method: " + (-1*b - Math.sqrt( this.discriminant) ) / (2 * a) + "\n\n");
        return (-1*b - Math.sqrt( this.discriminant) ) / (2 * a);
    }

    public My2dPoint getVertex(){

        double x = -1*b / 2*a;
        double y = a*x*x + b*x + c;

        return new My2dPoint( x, y);

    }

    @Override
    public String toString() {
        return "wk1_review.Quadratic  y = " + a + "x^2 + " + b + "x + " +  c;
    }
}
