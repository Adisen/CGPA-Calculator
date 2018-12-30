package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Student israel;
        israel = new Student();

        israel.introduction();

        israel.getGPA();

        israel.getCGPA();

    }
}


class Student {
    private Scanner input = new Scanner(System.in);
    private float tgp; //total grade cumulative points
    private float tnu; //total number of points
    private float CGPA;
    private float ctnu; //Cummulative total number of units
    private float ctgp; //Cummulative total grade point
    private int numOfSemesters;
    private int i = 1; //Counter inside the getGPA functionCGPA
    private int currentYear = 0;

    //This is a method to get the basic info about the number of years the student is expected to use in school
    //Then the number of semesters is automatically calculated
    void introduction(){
        System.out.print("Welcome, how  many years is your course of study? ");
        int yearsOfStudy = input.nextInt();
        numOfSemesters = 2 * yearsOfStudy;
    }

    //This method get the number of courses that the student is offering in a semester and then calculates the gpa from there
    private float calculateGPA(){

        System.out.print("Please input the number of courses offered: ");
        int course_number = input.nextInt();

        int counter = 0;
        int course_units[] = new int[course_number];
        char course_grades[] = new char[course_number];
        int course_points[] = new int[course_number];
        tgp = 0;
        tnu = 0;

        while (counter < course_number) {
            //Enter all the course units
            System.out.print("Please enter the course unit: ");
            course_units[counter] = input.nextInt();

            //Enter all the course grades
            System.out.print("Please enter the course grade: ");
            course_grades[counter] = input.next().charAt(0);

            if (course_grades[counter] == 'a' || course_grades[counter] == 'A') {
                course_points[counter] = 5;
            } else if (course_grades[counter] == 'b' || course_grades[counter] == 'B') {
                course_points[counter] = 4;
            } else if (course_grades[counter] == 'c' || course_grades[counter] == 'C') {
                course_points[counter] = 3;
            } else if (course_grades[counter] == 'd' || course_grades[counter] == 'D') {
                course_points[counter] = 2;
            } else if (course_grades[counter] == 'e' || course_grades[counter] == 'E') {
                course_points[counter] = 1;
            } else if (course_grades[counter] == 'f' || course_grades[counter] == 'F') {
                course_points[counter] = 0;
            } else {
                System.out.println("Enter a valid grade");
            }
            counter++;
        }

//        System.out.println(Arrays.toString(course_units));
//        System.out.println(course_number);
//        System.out.println(Arrays.toString(course_points));
//        System.out.println(course_grades);

        for (int i = 0; i < course_number; i++) {
            tnu += course_units[i];
            tgp += course_units[i] * course_points[i];
        }
        return tgp / tnu;
    }

    //This method from the info calculated in the calculateGPA method calculates the CGPA
    private void calculateCGPA(){
        CGPA = ctgp / ctnu;
    }

    //Given the number of years, this method is used to calculate the GPA and CGPA for each semester
    void getGPA() {
        while (i <= numOfSemesters){
            System.out.print("Do you want to calculate your next GPA and CGPA? (true for yes and false for no): ");
            boolean proceedToNextYear = input.nextBoolean();

            if (proceedToNextYear){
                if (i % 2 != 0){
                    currentYear++;
                }
                System.out.println("Your Part "+ currentYear +" and semester "+ i +" is: " + calculateGPA());

                ctnu += tnu;
                ctgp += tgp;
                i++;
                getGPA();
            } else {
                break;
            }
        }
    }

    //This method is used to get the CGPA
    void getCGPA(){
        calculateCGPA();
        System.out.printf("Your current CGPA is: %s%n", CGPA);
    }
}