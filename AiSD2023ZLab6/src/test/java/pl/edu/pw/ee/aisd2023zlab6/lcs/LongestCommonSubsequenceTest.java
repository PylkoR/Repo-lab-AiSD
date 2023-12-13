package pl.edu.pw.ee.aisd2023zlab6.lcs;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LongestCommonSubsequenceTest {
    LongestCommonSubsequence lcs;

    @Before
    public void setUp() {
        lcs = new LongestCommonSubsequence();
    }

    @Test
    public void should_CorrectlyFindSubsequence() {
        //given
        String expected = "IKOA";

        //when
        String sub = lcs.findLcs("MIKOŁAJ", "NIKOLA");

        //then
        assertEquals(expected, sub);
    }

    @Test
    public void should_CorrectlyFindSubsequence2() {
        //given
        String expected = "ACEK";

        //when
        String sub = lcs.findLcs("JACEK", "GACEK");

        //then
        assertEquals(expected, sub);
    }

    @Test
    public void should_CorrectlyFindSubsequence3() {
        //given
        String expected = "RONA";

        //when
        String sub = lcs.findLcs("MAKARONA", "BIEDRONKA");

        //then
        assertEquals(expected, sub);
    }
}