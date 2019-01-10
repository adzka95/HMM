public class Aposteriori {
    private Sequence sequence;
    private int l;
    private double[][] f;
    private double[][] b;
    private double p;
    private boolean[] prediction;

    public Aposteriori(Sequence sequence){
        this.sequence = sequence;
        this.l = sequence.getL();
        this.f = new double[2][this.l];
        this.b = new double[2][this.l];
        this.prediction = new boolean[l];
    }

    public boolean[] predict(){
        forward();
        backward();
        for(int i=0; i<l; i++){
            if((f[Dice.FAIR.getIndex()][i]*b[Dice.FAIR.getIndex()][i])
                    <(f[Dice.LOADED.getIndex()][i]*b[Dice.LOADED.getIndex()][i])){
                prediction[i]=true;
            }
        }
        return prediction;
    }

    private void forward(){
        double fairDiceProp = sequence.getFairDiceStartPropability();
        double[][] allProp = sequence.getAllPropability();
        int [] observation = sequence.getSequence();
        double[] changeDiceProp = sequence.getChangeDiceProbability();
        f[Dice.FAIR.getIndex()][0] = fairDiceProp;
        f[Dice.LOADED.getIndex()][0] = 1 - fairDiceProp;
        for(int i = 1; i <l; i++){
            double sum = f[Dice.FAIR.getIndex()][i-1] * changeDiceProp[Dice.FAIR.getIndex()] +
                    f[Dice.LOADED.getIndex()][i-1] * changeDiceProp[Dice.LOADED.getIndex()];
            for(int k=0; k<2; k++){
                f[k][i] =  allProp[k][observation[i]-1]* sum;
            }
        }
        p = f[Dice.FAIR.getIndex()][l-1] + f[Dice.LOADED.getIndex()][l-1];
    }

    private void backward(){
        double[] changeDiceProp = sequence.getChangeDiceProbability();
        double[][] allProp = sequence.getAllPropability();
        int [] observation = sequence.getSequence();
        b[Dice.FAIR.getIndex()][l-1] = 1;
        b[Dice.LOADED.getIndex()][l-1] = 1;
        for(int i = l-2; i>0; i--){
            for(int k=0; k<2; k++){
                //TODO check this!
                b[k][i] = changeDiceProp[Dice.FAIR.getIndex()] * allProp[Dice.FAIR.getIndex()][observation[i+1]-1]* b[Dice.FAIR.getIndex()][i+1]+
                       changeDiceProp[Dice.LOADED.getIndex()] * allProp[Dice.LOADED.getIndex()][observation[i+1]-1]* b[Dice.LOADED.getIndex()][i+1];

            }
        }
    }
}
