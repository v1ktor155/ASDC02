package org.example;

import java.util.Objects;

public class Student implements Cloneable {
    private int id;
    private String name;
    private String surname;
    private int faculty;
    private int yearOfBirth;
    private int yearOfIntroduction;

    public Student(int id, String name, String surname, int yearOfBirth, int yearOfIntroduction, int faculty) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.faculty = faculty;
        this.yearOfBirth = yearOfBirth;
        this.yearOfIntroduction = yearOfIntroduction;
    }

    public Student(Student student) {
        this.id = student.id;
        this.name = student.name;
        this.surname = student.surname;
        this.faculty = student.faculty;
        this.yearOfBirth = student.yearOfBirth;
        this.yearOfIntroduction = student.yearOfIntroduction;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getFaculty() {
        return faculty;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public int getYearOfIntroduction() {
        return yearOfIntroduction;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return id == student.id && faculty == student.faculty && yearOfBirth ==
                student.yearOfBirth && yearOfIntroduction == student.yearOfIntroduction
                && name.equals(student.name) && surname.equals(student.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, faculty, yearOfBirth, yearOfIntroduction);
    }

    @Override
    public Student clone() throws CloneNotSupportedException {
        return (Student) super.clone();
    }

    public Student copy() {
        return new Student(this.id, this.name, this.surname,
                this.yearOfBirth, this.yearOfIntroduction, this.faculty);
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", faculty=" + faculty +
                ", yearOfBirth=" + yearOfBirth +
                ", yearOfIntroduction=" + yearOfIntroduction +
                '}';
    }
}