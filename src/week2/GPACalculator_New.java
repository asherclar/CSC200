package week2;

import java.util.Scanner;


/**
 * Prompts user for information on their courses and calculates their GPA
 * @author Asher Clar
 * @version 9.8.26
 */
public class GPACalculator_New {
    private static Scanner in= new Scanner(System.in);

    public static int getCourseCount() {
        System.out.print("How many courses? ");
        int count = in.nextInt();
        in.nextLine();
        return count;
    }

    public static void main(String[] args){
        int COURSE_COUNT = getCourseCount();
        Course[] courses = new Course[COURSE_COUNT];
        for(int i = 0; i<COURSE_COUNT; i++) {
            courses[i] = new Course(in);
        }
        System.out.println();
        System.out.print("Total GPA is: ");
        double totalQPs = 0;
        int totalCredits = 0;
        for(int i = 0; i<COURSE_COUNT; i++) {
            totalQPs += courses[i].getQPs();
            totalCredits += courses[i].getCredits();
        }
        System.out.print(totalQPs / totalCredits);
    }
}

class Course {
    String name;
    int credits;
    double GPA;

    public Course(Scanner in) {
        System.out.print("Name of class: ");
        this.name = in.nextLine();
        System.out.print("Number of credits: ");
        this.credits = in.nextInt();
        in.nextLine();
        System.out.print("GPA of class: ");
        this.GPA = in.nextDouble();
        in.nextLine();
        System.out.println();
    }

    public int getCredits() {
        return credits;
    }

    public double getQPs() {
        return GPA * credits;
    }
}

/*

How many courses? 3
Name of class: CSC 115
Number of credits: 3
GPA of class: 3

Name of class: CSC 190
Number of credits: 4
GPA of class: 3.7

Name of class: CSC 200
Number of credits: 4
GPA of class: 4


Total GPA is: 3.6181818181818177

 */

/*
How many courses? 4
Name of class: Calc III
Number of credits: 4
GPA of class: 4

Name of class: AP Biology
Number of credits: 8
GPA of class: 3.7

Name of class: AP Statistics
Number of credits: 3
GPA of class: 4

Name of class: AP English Literature
Number of credits: 3
GPA of class: 2.7


Total GPA is: 3.6500000000000004
 */