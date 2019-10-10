package com.yujunquan.studylife.test.othertest;

import java.util.HashMap;
import java.util.Map;

public class kaishi {
    public static int twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap();
        //int []a=new int[2];
        for (int i = 0; i < nums.length; i++) {
            map.put(target - nums[i], i);
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) == true && i != map.get(nums[i])) {
                //  a[0]=i<map.get(nums[i])?i:map.get(nums[i]);
                // a[1]=i>map.get(nums[i])?i:map.get(nums[i]);
                count++;
                continue;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] num = {-1, 2, 4, 5, -2, 1, 1, 2};
        int result = twoSum(num, 0);
        System.out.println(result);
    }
}
