package com.leet;

import java.math.BigInteger;
import java.util.*;

public class Test {

    public static class Leijia_306{
        public boolean isAdditiveNumber(String num) {
            int len = num.length();
            if(len < 3)
                return false;
            return true;
        }
    }

    public static class CloestThreeSum_tag{
        public static int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);

            int cloest = nums[0]+nums[1]+nums[2];
            for(int l = 0;l<nums.length-2;l++){//  0 1 2    ……     len-3  len-2  len-1     最外层移动   最左边的
                int m = l + 1,r = nums.length-1;
                while (m < r ){
                    int threesum = nums[l] + nums[m] + nums[r];
                    if(Math.abs(threesum-target) < Math.abs(cloest-target)){
                        cloest = threesum;
                    }
                    if(threesum == target)
                        return cloest;
                    if(threesum > cloest)
                        r--;
                    else
                        m++;//先l不动，  l      m       r
                }
            }
            return cloest;
        }
    }



}
