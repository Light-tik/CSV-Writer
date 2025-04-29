package org.writer.util;

import net.datafaker.Faker;
import org.writer.model.Months;
import org.writer.model.Person;
import org.writer.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TestDataFactory {

    private static final Faker faker = new Faker(new Locale("en"));

    public static List<Person> generateFakePeople(int count) {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            people.add(Person.builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .yearOfBirth(faker.number().numberBetween(1950, 2025))
                    .monthOfBirth(Months.values()[faker.number().numberBetween(1, Months.values().length)])
                    .dayOfBirth(faker.number().numberBetween(1, 28))
                    .build());
        }
        return people;
    }

    public static List<Student> generateFakeStudents(int count) {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<String> scores = List.of(
                    String.valueOf(faker.number().numberBetween(1, 100)),
                    String.valueOf(faker.number().numberBetween(1, 100)),
                    String.valueOf(faker.number().numberBetween(1, 100)),
                    String.valueOf(faker.number().numberBetween(1, 100))
            );

            students.add(Student.builder()
                    .name(faker.name().fullName())
                    .score(scores)
                    .build());
        }
        return students;
    }
}
