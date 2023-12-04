package pl.edu.pw.ee.aisd2023zbonus;

public class Main {
    public static void main(String[] args){
        String text = "KOKOO";

        AiSD2023ZBonus palindromCounter = new AiSD2023ZBonus();
        int n = palindromCounter.countPalindroms(text);

        System.out.println(n);
    }
}
