package pl.edu.pw.ee.aisd2023zbonus;

public class Main {
    public static void main(String[] args) {
        String text = "KOKOO";
        String test = "KOKON";
        String test2 = "OOKOO";
        String testNull = "BBB";
        String test3 = "";
        String test4 = "";
        for (int i = 0; i < 9_999; i++) {
            test3 += "A";
        }
        for (int i = 0; i < 1_998; i++) {
            test4 += "A";
        }

        AiSD2023ZBonus palindromCounter = new AiSD2023ZBonus();
        int n = palindromCounter.countPalindroms(text);
        int k = palindromCounter.countPalindroms(test);
        int l = palindromCounter.countPalindroms(test2);
        int m = palindromCounter.countPalindroms(testNull);
        int j = palindromCounter.countPalindroms(test3);
        int h = palindromCounter.countPalindroms(test4);
        System.out.println(n);
        System.out.println(k);
        System.out.println(l);
        System.out.println(m);
        System.out.println(j);
        System.out.println(h);
    }
}
