package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", 
        mixinStandardHelpOptions = true, 
        version = "1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    
    @Parameters(index = "0", description = "path to first file")
    private String filePath1;
    
    @Parameters(index = "1", description = "path to second file")
    private String filePath2;
    
    @Option(names = {"-f", "--format"}, 
            description = "output format [default: stylish]")
    private String format = "stylish";
    
    @Override
    public Integer call() throws Exception {
        // Здесь будет вызов Differ.generate()
        String diff = Differ.generate(filePath1, filePath2, format);
        System.out.println(diff);
        return 0;
    }
    
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
