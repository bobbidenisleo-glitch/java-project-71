package hexlet.code;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
    
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        // TODO: Реализовать сравнение файлов
        return String.format(
            "Comparison of files is not implemented yet.%n" +
            "File 1: %s%nFile 2: %s%nFormat: %s", 
            filePath1, filePath2, format
        );
    }
}
