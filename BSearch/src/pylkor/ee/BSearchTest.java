package pylkor.ee;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BSearchTest {

    public BSearch bSearch;
    @Before
    public void beforeTest() {
        bSearch = new BSearch();
    }

    @Test
    public void testBSearch() {

        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        assertEquals(0, bSearch.bSearch(nums, 1));
        assertEquals(4, bSearch.bSearch(nums, 5));
        assertEquals(9, bSearch.bSearch(nums, 10));

        assertEquals(-1, bSearch.bSearch(nums, 0));
        assertEquals(-1, bSearch.bSearch(nums, 11));

    }

}