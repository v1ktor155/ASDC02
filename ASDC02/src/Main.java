package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        SingletonWorkerClass singletonWorker = SingletonWorkerClass.getInstance();
        ArrayList<Student> studentArrayList = singletonWorker.readFromCSVFile();
        singletonWorker.showStudentArrayList(studentArrayList);

        Student student = new Student(studentArrayList.get(0));
        try {
            Student student1 = student.clone();
            System.out.println(student1);
            System.out.println(student1.equals(student));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        Student copyStudent = student.copy();
        System.out.println(copyStudent.equals(student));


    }


}