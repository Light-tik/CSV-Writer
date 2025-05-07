package org.writer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.writer.model.Person;
import org.writer.model.Student;
import org.writer.util.TestDataFactory;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class CSVWritableTest {

    private final CSVWritable csvWriter = new CSVWritable();
    private final String testFilePerson = "test_people.csv";
    private final String testFileStudent = "test_student.csv";

    @AfterEach
    public void cleanup() throws IOException {
        Files.deleteIfExists(new File(testFilePerson).toPath());
        Files.deleteIfExists(new File(testFileStudent).toPath());
    }

    @Test
    public void testWriteToFilePeople_Success() throws IOException {
        List<Person> people = TestDataFactory.generateFakePeople(2);
        csvWriter.writeToFile(people, testFilePerson);

        File file = new File(testFilePerson);
        assertTrue(file.exists());

        List<String> lines = Files.readAllLines(file.toPath());
        assertEquals(3, lines.size());
        assertTrue(lines.get(0).contains("First Name"));
    }

    @Test
    public void testWriteToFileStudent_Success() throws IOException {
        List<Student> students = TestDataFactory.generateFakeStudents(2);

        csvWriter.writeToFile(students, testFileStudent);
        File file = new File(testFileStudent);
        assertTrue(file.exists());

        List<String> lines = Files.readAllLines(file.toPath());
        assertEquals(3, lines.size());
        assertTrue(lines.get(0).contains("Name"));
    }

    @Test
    public void testWriteToFile_ThrowsOnEmptyList() {
        List<Person> emptyList = List.of();

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                csvWriter.writeToFile(emptyList, testFilePerson)
        );

        assertTrue(exception.getMessage().contains("Datalist is null or empty"));
    }

    @Test
    public void testWriteToFile_ThrowsIfNoAnnotatedFields() {
        class Dummy {
            String name = "NoAnnotation";
        }

        List<Dummy> list = List.of(new Dummy());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                csvWriter.writeToFile(list, testFilePerson)
        );

        assertTrue(exception.getMessage().contains("No fields"));
    }
}
