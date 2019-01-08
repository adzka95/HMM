import java.util.Random;

public class Sequence {
    private int l;
    private int[] sequence;
    private boolean [] throwIsCheated;
    private float[][] allPropabilityRange;
    private float[] changeDiceProbability;
    private int numberOfSides;

    public Sequence(int l){
        this.l = l;
        this.sequence = new int[l];
        this.throwIsCheated = new boolean[l];
        changeDiceProbability = new float[2];
        GenerateSequence();
    }

    private void GenerateSequence(){
        fillPropabilityData();
        int diceIndex=0;
        Random generator = new Random();
        for(int j =0; j<l; j++){
            int changeDice = generator.nextInt(100);
            if(changeDice<changeDiceProbability[diceIndex]*100){
                diceIndex = (diceIndex+1)%2;
            }
            if(diceIndex==Dice.LOADED.getIndex()){
                throwIsCheated[j] =true;
            }
            int randomThrow = generator.nextInt(100);
            for(int intex=0; intex<numberOfSides; intex++){
                if(randomThrow<allPropabilityRange[diceIndex][intex]){
                    sequence[j]= intex+1;
                    break;
                }
            }
        }
    }

    private void fillPropabilityData(){
        FileReader fairProp = new FileReader("src/fairProbability.txt");
        float[] fairProbability = fairProp.getProbability();
        changeDiceProbability[Dice.FAIR.getIndex()]= fairProp.getChangeDiceProbability();

        FileReader loadedProp = new FileReader("src/loadedProbability.txt");
        float[] loadedProbability = loadedProp.getProbability();
        changeDiceProbability[Dice.LOADED.getIndex()]= loadedProp.getChangeDiceProbability();

        numberOfSides = fairProp.getNumberOfSides();

        allPropabilityRange = new float[2][numberOfSides];
        allPropabilityRange[Dice.FAIR.getIndex()][0] = fairProbability[0]*100;
        for(int j = 1; j<numberOfSides; j++){
            allPropabilityRange[Dice.FAIR.getIndex()][j]= allPropabilityRange[Dice.FAIR.getIndex()][j-1]+ fairProbability[j]*100;
        }
        allPropabilityRange[Dice.LOADED.getIndex()][0] = loadedProbability[0]*100;
        for(int j = 1; j<numberOfSides; j++){
            allPropabilityRange[Dice.LOADED.getIndex()][j]= allPropabilityRange[Dice.LOADED.getIndex()][j-1]+ loadedProbability[j]*100;
        }
    }

    public void printSequence(){
        for(int i=0; i<l; i++) {
            System.out.print(sequence[i] + ", ");
        }
        System.out.println();
    }

    public void printThrowIsCheat(){
        for(int i=0; i<l; i++) {
            if(throwIsCheated[i]){
                System.out.print("L, ");
            }
            else {
                System.out.print("F, ");
            }
        }
        System.out.println();
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int[] getSequence() {
        return sequence;
    }

    public void setSequence(int[] sequence) {
        this.sequence = sequence;
    }

    public boolean[] getThrowIsCheated() {
        return throwIsCheated;
    }

    public void setThrowIsCheated(boolean[] throwIsNotCheated) {
        this.throwIsCheated = throwIsNotCheated;
    }
}
