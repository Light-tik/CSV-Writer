package org.writer.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для указания, что поле следует включать в CSV-файл.
 * Используется для создания имени столбца в итоговом файле.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CSVField {

    /**
     * Имя столбца в CSV-файле.
     *
     * @return строка с заголовком столбца
     */
    String name();
}
