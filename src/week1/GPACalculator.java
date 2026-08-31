// Asher Clar, 8/31/2026, Prompt for classes and calculate GPA

package week1;

public class GPACalculator {
    public String[] courseNames;
    public double[] credits;
    public double[] grades;

    public GPACalculator(String[] courseNames, double[] credits, double[] grades){
        this.courseNames = courseNames;
        this.credits = credits;
        this.grades = grades;
    }

    double sumArray(double[] arr) {
        double sum = 0;
        for(int i = 0; i<arr.length; i++){
            sum += arr[i];
        }
        return sum;
    }

    double calcGPA(double[] credits, double[] grades) {
        double[] qualPoints = new double[credits.length];
        for(int i = 0; i<credits.length; i++){
            qualPoints[i]=credits[i]*grades[i];
        }
        return (sumArray(credits)!=0)?sumArray(qualPoints)/sumArray(credits):0;
    }

    void classPrompt(int index){
        if(index<courseNames.length&& index>=0){
            courseNames[index]= IO.readln("Class Name "+index+": ");
            credits[index]= Double.parseDouble(IO.readln("Credits for class "+index+": "));
            grades[index] = Double.parseDouble(IO.readln("Grade for class "+index+": "));
        }
    }

    public static void main(String[] args){
        int courseCount = Integer.parseInt(IO.readln("How many courses? "));

        String[] classnames = new String[courseCount];
        double[] credits = new double[courseCount];
        double[] grades = new double[courseCount];
        GPACalculator calc = new GPACalculator(classnames, credits, grades);

        for (int i = 0; i<courseCount; i++){
            calc.classPrompt(i);
        }

        IO.println("GPA is: "+calc.calcGPA(calc.credits,calc.grades));
    }
}
