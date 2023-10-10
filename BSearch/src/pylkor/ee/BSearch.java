package pylkor.ee;

public class BSearch {
    public int bSearch(int[] nums, int toFind){
        if(nums == null){
            throw new RuntimeException("nums coś tam");
        }
        int p = 0;
        int k = nums.length - 1;

        while(p<=k){
            int m = p + (k-p)/2;

            if(toFind < nums[m])
                k = m-1;

            else if(toFind > nums[m])
                p = m+1;
            else return m;
        }

        return -1;
    }
}