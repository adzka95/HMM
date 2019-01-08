public class Main {
    public static void main(String[] args) {

        Sequence sequence = new Sequence(5);
        sequence.printSequence();
        sequence.printThrowIsCheat();
        boolean[] prediction = new boolean[]{true,false,false,true, true};
        Validator.validate(sequence.getThrowIsCheated(), prediction);
    }
}
