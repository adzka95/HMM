public class Viterbi {
    Sequence sequence;
    private double[][] v;
    private boolean [] throwIsCheated;
    private int l;

    public Viterbi(Sequence sequence){
        this.sequence = sequence;
        this.l = sequence.getL();
        this.v = new double[2][this.l];
        this.throwIsCheated = new boolean[this.l];
    }

    public boolean[] predict(){
        double fairDiceProp = sequence.getFairDiceStartPropability();
        v[Dice.FAIR.getIndex()][0] = fairDiceProp;
        v[Dice.LOADED.getIndex()][0] = 1 - fairDiceProp;
        double[] changeDiceProp = sequence.getChangeDiceProbability();
        double[][] allProb = sequence.getAllPropability();
        int [] observation = sequence.getSequence();

        for(int i =1; i<l; i++){
            double fair = v[Dice.FAIR.getIndex()][i-1] * changeDiceProp[Dice.FAIR.getIndex()];
            double loaded = v[Dice.LOADED.getIndex()][i-1] * changeDiceProp[Dice.LOADED.getIndex()];
            for(int k=0; k<2; k++) {
                if (fair > loaded) {
                    v[k][i] = allProb[k][observation[i]-1] *fair;
                }
                else {
                    v[k][i] = allProb[k][observation[i]-1] *loaded;
                    throwIsCheated[i] = true;
                }
            }
        }
        return throwIsCheated;
    }
}
