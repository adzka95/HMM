public class Main {
    public static void main(String[] args) {

        Sequence sequence = new Sequence(50, 0.5);
        Printer.printSequence(sequence.getSequence());
        Printer.printBoolean(sequence.getThrowIsCheated(), "Dice");
        Viterbi viterbi = new Viterbi(sequence);
        boolean[] vPrediction = viterbi.predict();
        Printer.printBoolean(vPrediction, "Viter");
        Aposteriori aposteriori = new Aposteriori(sequence);
        boolean[] aPrediction = aposteriori.predict();
        Printer.printBoolean(aPrediction, "Apost");


        Validator.validate(sequence.getThrowIsCheated(), vPrediction, "Viterby");
        Validator.validate(sequence.getThrowIsCheated(), aPrediction, "Aposteriori");
    }
}
