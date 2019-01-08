public class Validator {
    public static void validate(boolean[] target, boolean [] pretiction){
        int numberOfErrors = 0;
        for(int i=0; i<target.length; i++){
            if(target[i]!=pretiction[i]){
                numberOfErrors++;
            }
        }
        System.out.println("Ilość błędów: " + numberOfErrors);
        int numberOfGoodPredictions = target.length - numberOfErrors;
        System.out.println("Ilość poprawnych predykcji: " + numberOfGoodPredictions);
        float correctness = (((float)numberOfGoodPredictions)/((float)target.length) )* 100;
        System.out.println("Poprawność predykcji: " + correctness +" %");
    }
}
