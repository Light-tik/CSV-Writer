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

/**
 * Модульные тесты для класса {@link CSVWritable}
 */
public class CSVWritableTest {

    private final CSVWritable csvWriter = new CSVWritable();

    private final String testFilePerson = "test_people.csv";

    private final String testFileStudent = "test_student.csv";

    /**
     * Удаляет временные файлы после каждого теста.
     */
    @AfterEach
    public void cleanup() throws IOException {
        Files.deleteIfExists(new File(testFilePerson).toPath());
        Files.deleteIfExists(new File(testFileStudent).toPath());
    }

    /**
     * Проверяет успешную запись списка Person в CSV-файл.
     * Ожидается, что файл создаётся и содержит правильные заголовки.
     */
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

    /**
     * Проверяет успешную запись списка Student в CSV-файл.
     * Ожидается, что файл создаётся и содержит правильные заголовки.
     */
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

    /**
     * Проверяет, что метод выбрасывает исключение при передаче пустого списка.
     */
    @Test
    public void testWriteToFile_ThrowsOnEmptyList() {
        List<Person> emptyList = List.of();

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                csvWriter.writeToFile(emptyList, testFilePerson)
        );

        assertTrue(exception.getMessage().contains("Datalist is null or empty"));
    }

    /**
     * Проверяет, что метод выбрасывает исключение, если в классе нет полей с аннотацией @CSVField.
     */
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
