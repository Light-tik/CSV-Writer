package org.writer;

import org.writer.model.Person;
import org.writer.model.Student;
import org.writer.util.TestDataFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        CSVWritable writer = new CSVWritable();
        List<Person> people = TestDataFactory.generateFakePeople(10);
        List<Student> students = TestDataFactory.generateFakeStudents(10);

        writer.writeToFile(students, "students.csv");
        System.out.print("Students saved to students.csv\n");

        writer.writeToFile(people, "people.csv");
        System.out.println("People saved to people.csv");
    }
}
