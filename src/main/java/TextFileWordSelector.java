import java.util.List;

public interface TextFileWordSelector {
    public String selectRandomLine(String fileNamePath);
    List<String> selectRandomCollectionFromFile(String fileName);
}
