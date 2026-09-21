package week2.GraphicsLab;

import week1.quadraticLab.My2dPoint;

/**
 * @author Will McLaughlin
 * @version 1.28.25
 * <p>
 * Used is used to represent a quadratic equation in the form of f(x) = ax^2 + bx + c.
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
    public double getA() { return a; } public double getB() { return b; } public double getC() { return c; }
    //BONUS ...
    public int getNumOfRationalRoots(){
        if( this.discriminant < 0 ){
            return 0;
        }

        else if( this.discriminant == 0 ){
            return 1;
        }

        //rational if the discriminant has a square-root of that is  "nice"
        //      ex.   4 has a square-root of 2, if discriminant is 4, roots are rational
        //      ex.   3 has a squre-root, but it is irrational (decimal never ends, or repeats)
        //   to check, we will take the square of the square-root and see if it REALLY close to original value
        double sqrt = Math.sqrt( this.discriminant);
        sqrt = Math.round( sqrt * 10000)/10000.0;  //truncate to 4 decimal places
        double diff = Math.abs(sqrt*sqrt - discriminant);

        double epsilon = 0.00001;
        if( diff < epsilon ){
            return 2;
        }

        return 0;
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

        //return "wk1_review.quadritics.Quadratic  y = " + a + "x^2 + " + b + "x + " +  c;
        return "y = " + a + "x^2 + " + b + "x + " +  c;
    }

    public String getAllData() {
        String data = this.toString();

        data += "\n\tNumber of Real roots: " + this.getNumOfRealRoots();
        data += "\n\tNumber of Rational roots: " + this.getNumOfRationalRoots();
        data += "\n\tNumber of Imaginary roots: " + this.getNumOfImaginaryRoots();
        data += "\n\n";
        data += getRoots();

        return data;
    }

    private String getRoots() {
        if( this.discriminant < 0 ){
            return "\tImaginary Roots";
        }

        else if( this.discriminant == 0 ){
            return "\troot:\t" + getRoot1();
        }
        else {
            return "\troots:\t" + getRoot1() + ", " + getRoot2();
        }
    }
}
