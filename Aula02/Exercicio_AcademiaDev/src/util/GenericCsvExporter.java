package util;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class GenericCsvExporter {

    public static <T> String export(List<T> data, List<String> columns) {

        if (data == null || data.isEmpty())
            return "";

        String header = String.join(",", columns);

        String rows = data.stream()
                .map(obj -> columns.stream()
                        .map(col -> getFieldValue(obj, col))
                        .collect(Collectors.joining(",")))
                .collect(Collectors.joining("\n"));

        return header + "\n" + rows;
    }

    private static String getFieldValue(Object obj, String fieldName) {

        try {

            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);

            Object value = field.get(obj);

            return value == null ? "" : value.toString();

        } catch (Exception e) {

            return "";
        }
    }

}