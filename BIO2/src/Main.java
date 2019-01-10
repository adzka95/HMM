public class Main {
    public static void main(String[] args) {

        Sequence sequence = new Sequence(10, 0.5);
        Printer.printSequence(sequence.getSequence());
        Printer.printBoolean(sequence.getThrowIsCheated());
        Aposteriori aposteriori = new Aposteriori(sequence);
        boolean[] aPrediction = aposteriori.predict();
        Printer.printBoolean(aPrediction);
        Validator.validate(sequence.getThrowIsCheated(), aPrediction);
    }
}
