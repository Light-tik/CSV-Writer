package org.writer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.writer.annotation.CSVField;

import java.util.List;

/**
 * Модель данных для представления студента.
 */
@Data
@Builder
@AllArgsConstructor
public class Student {

    @CSVField(name = "Name")
    private String name;

    @CSVField(name = "Scores")
    private List<String> score;
}
