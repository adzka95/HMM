import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

    private float[] probability;
    private float changeDiceProbability;
    private int numberOfSides = 6;

    public FileReader(String path){
        probability  = new float[numberOfSides];
        readDataFromFile(path);
    }

    private void readDataFromFile(String path) {
        try {
            Scanner input = new Scanner(new File(path));
            for(int i=0; i<numberOfSides; i++){
                if(input.hasNextLine()){
                    String[] s = input.nextLine().split("/");
                    float a = Float.parseFloat(s[0]);
                    float b = Float.parseFloat(s[1]);
                    probability[i] = a/b;
                }
            }
            if(input.hasNextLine()){
                changeDiceProbability = Float.parseFloat(input.nextLine());
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public float[] getProbability() {
        return probability;
    }

    public float getChangeDiceProbability() {
        return changeDiceProbability;
    }

    public int getNumberOfSides() {
        return numberOfSides;
    }
}
