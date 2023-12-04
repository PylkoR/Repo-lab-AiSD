package pl.edu.pw.ee.aisd2023zbonus;

public class AiSD2023ZBonus {
    public int countPalindroms(String text){
        if (text == null || text.length() < 1){
            throw new RuntimeException("String wejściowy jest pusty!");
        }
        if (text.length() >= 9_999){
            return -1;
        }
        int n = 0;

        for ( int i = 0; i < text.length(); i++){
            for (int j = i + 1; j <= text.length(); j++) {

                String subText = text.substring(i, j);
                if (subText.length() > 1 && isPalindrome(subText)) {
                    n++;
                }
            }
        }

        if (n >= 99_999){
            return -1;
        }else {
            return n;
        }
    }

    private boolean isPalindrome(String subText){
        StringBuilder subTextReversed = new StringBuilder();
        subTextReversed.append(subText).reverse();

        return subTextReversed.toString().equals(subText);
    }
}
