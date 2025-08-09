package org.writer;

import java.util.List;

/**
 * Интерфейс для классов, реализующих возможность записи данных в файл.
 */
public interface Writable {

    /**
     * Записывает список объектов в указанный файл.
     *
     * @param data список объектов для записи
     * @param fileName имя файла, в который будут записаны данные
     */
    void writeToFile(List<?> data, String fileName);
}
