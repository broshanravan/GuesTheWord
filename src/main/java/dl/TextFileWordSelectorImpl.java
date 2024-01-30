package dl;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class TextFileWordSelectorImpl implements TextFileWordSelector {

    private long getNumberOfLines(String fileNamePath) {
        Path path = Paths.get(fileNamePath);
        long lineCounts = 0;
        try {
            lineCounts = Files.lines(path).count();
            Integer.parseInt(String.valueOf(lineCounts));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineCounts;
    }


    public String selectRandomLine(String fileNamePath)  {

        String randomLine = "";
        long linesCount = getNumberOfLines(fileNamePath);
        long rand = new Random().nextLong(1,linesCount);
        try {
            FileInputStream inputStream = new FileInputStream(fileNamePath);
            BufferedReader bufferedStream = new BufferedReader(new InputStreamReader(inputStream));
            for (int i = 0; i < rand; ++i)
                bufferedStream.readLine();
            randomLine = bufferedStream.readLine();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        return randomLine;

    }


    public List<String> selectRandomCollectionFromFile(String fileName){
        String randomLine = this.selectRandomLine(fileName);
        String[] splitedLineArray = randomLine.split(" ");
        List<String> spittledLineList = new ArrayList<>() ;
        Collections.addAll(spittledLineList, splitedLineArray);
        return spittledLineList;

    }

    public static void main(String[] args){

        String fileNamePath = "reference.txt";
        TextFileWordSelector textFileWordSelector = new TextFileWordSelectorImpl();
        String line = textFileWordSelector.selectRandomLine(fileNamePath);
        System.out.println(line);

    }
}
