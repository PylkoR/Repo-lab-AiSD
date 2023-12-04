package pl.edu.pw.ee.aisd2023zbonus;

public class AiSD2023ZBonus {
    public int countPalindroms(String text){
        if (text.length() >= 9_999){
            return -1;
        }
        int n = 0;

        for ( int i = 0; i < text.length(); i++){
            for (int j = i + 1; j <= text.length(); j++) {

                String substring = text.substring(i, j);
                if (substring.length() > 1 && isPalindrom(substring)) {
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

    private boolean isPalindrom(String subText){
        int firstId = 0;
        int lastId = subText.length() - 1;

        while (firstId < lastId) {
            if (subText.charAt(firstId) != subText.charAt(lastId)) {
                return false;
            }
            firstId++;
            lastId--;
        }
        return true;
    }
}
