package hexlet.code.parsers;

public class ParserFactory {
    
    public static Parser getParser(String filePath) {
        String extension = getFileExtension(filePath).toLowerCase();
        
        switch (extension) {
            case "json":
                return new JsonParser();
            case "yml":
            case "yaml":
                return new YamlParser();
            default:
                throw new IllegalArgumentException("Unsupported file format: " + extension);
        }
    }
    
    private static String getFileExtension(String filePath) {
        int dotIndex = filePath.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < filePath.length() - 1) {
            return filePath.substring(dotIndex + 1);
        }
        throw new IllegalArgumentException("Cannot determine file extension: " + filePath);
    }
}
