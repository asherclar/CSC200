package week1.quadraticLab;

public class QuadraticTester {

    public static void main(String[] args) {

        Quadratic eq1 = new Quadratic(1, -4, 4);
        System.out.println(eq1);
        System.out.println("\tVertex = " + eq1.getVertex() );
        System.out.println("\tNumber of roots = " + eq1.getNumOfRealRoots() );
        System.out.println("\t\troot 1 = " + eq1.getRoot1() );
        System.out.println("\t\troot 2 = " + eq1.getRoot2() );

        System.out.println();

        Quadratic eq2 = new Quadratic(3, 5, 4);
        System.out.println(eq2);
        System.out.println("\tVertex = " + eq2.getVertex() );
        System.out.println("\tNumber of roots = " + eq2.getNumOfRealRoots() );

        System.out.println("\t\troot 1 = " + eq2.getRoot1() );
        System.out.println("\t\troot 2 = " + eq2.getRoot2() );
    }
}

/*
Sample run:
wk1_review.Quadratic  y = 1.0x^2 + -4.0x + 4.0
	Vertex = wk1_review.My2dPoint = (2.0, 0.0)
	Number of roots = 1

wk1_review.Quadratic  y = 3.0x^2 + 5.0x + 4.0
	Vertex = wk1_review.My2dPoint = (-7.5, 135.25)
	Number of roots = 0
 */
