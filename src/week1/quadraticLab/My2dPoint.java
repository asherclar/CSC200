package week1.quadraticLab;

public class My2dPoint {

    private double x;
    private double y;


    public My2dPoint() {
    }

    public My2dPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "wk1_review.My2dPoint = " +
                "(" + x +
                ", " + y +
                ')';
    }
}
