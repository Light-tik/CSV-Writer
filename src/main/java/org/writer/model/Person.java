package org.writer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.writer.annotation.CSVField;

/**
 * Модель данных для представления одного человека.
 */
@Data
@Builder
@AllArgsConstructor
public class Person {

    @CSVField(name = "First Name")
    private String firstName;

    @CSVField(name = "Last Name")
    private String lastName;

    @CSVField(name = "Day of Birth")
    private int dayOfBirth;

    @CSVField(name = "Month of Birth")
    private Months monthOfBirth;

    @CSVField(name = "Year of Birth")
    private int yearOfBirth;
}
