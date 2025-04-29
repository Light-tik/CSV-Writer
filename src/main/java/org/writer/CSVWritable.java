package org.writer;

import org.writer.annotation.CSVField;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CSVWritable implements Writable{
    @Override
    public void writeToFile(List<?> data, String fileName) {

        if (data == null || data.isEmpty()){
            throw new IllegalArgumentException("Datalist is null or empty");
        }

        Class<?> clazz = data.get(0).getClass();
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(CSVField.class))
                .toList();

        if (fields.isEmpty()){
            throw new IllegalArgumentException("No fields in class " + clazz.getName() + " with @CSVField");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            String header = fields.stream()
                    .map(field -> field.getAnnotation(CSVField.class).name())
                    .collect(Collectors.joining(","));
            writer.write(header);
            writer.newLine();

            for (Object o : data){
                String value = fields.stream()
                        .map(field -> {
                            try {
                                field.setAccessible(true);
                                Object v = field.get(o);
                                return serializeFieldValue(v);
                            } catch (IllegalAccessException e){
                                throw new RuntimeException("Unable to access field " + field.getName() + " of class " + clazz.getName(), e);
                            }
                        })
                        .collect(Collectors.joining(","));
                writer.write(value);
                writer.newLine();
            }
        } catch (IOException e){
            throw new RuntimeException("Unable to write to file " + fileName, e);
        }
    }

    private String serializeFieldValue(Object value){
        if (value == null){
            throw new IllegalArgumentException("value is null or empty");
        }
        if (value instanceof List<?> list){
            return list.stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining(","));
        }
        return value.toString();
    }
}
