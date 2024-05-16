import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


class Solution {
    public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>(); // creating a hash map
        for (int i = 0; i < nums.length; i++) {// iterating through the array
            int remainder = target - nums[i];
            if (map.containsKey(remainder)) {
                return new int[] { map.get(remainder), i };
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[] {};// returning null
    }
}
        