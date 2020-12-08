package com.leet;

import com.leet.Homework.RyabkoTree;
import com.leet.Homework.RyabkoTreeI;
import com.leet.Homework.SlowPrefixStack;
import com.leet.SpinLockDemo.SpinLockDemo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import netscape.security.UserTarget;
import org.omg.PortableInterceptor.INACTIVE;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread( () ->{
            spinLockDemo.myLock();

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unLock();
        },"AAA").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread( () ->{
            spinLockDemo.myLock();

            spinLockDemo.unLock();
        },"BBB").start();

        return;
    }



    public static class WiggleSubsqquence{

        public static int wiggleMaxLengthII(int[] nums) {

            int n = nums.length;
            if (n < 2) {
                return n;
            }
            int up = 1;
            int down = 1;
            for (int i = 1; i < n; i++) {
                if (nums[i] > nums[i - 1]) {
                    up = down + 1;
                }
                if (nums[i] < nums[i - 1]) {
                    down = up + 1;
                }
            }
            return Math.max(up, down);
        }//it

        public static int wiggleMaxLength(int[] nums){
            int len = nums.length;

            if(len < 2)
                return len;

            int[] updp = new int[len];
            int[] downdp = new int[len];

            downdp[0] = 1;
            updp[0] = 1;

            for(int i = 1 ; i < len ; i++){
                if ( nums[i] < nums[i-1] ) {
                    downdp[i] = Math.max( downdp[i-1] , updp[i-1]+1 );
                } else  if ( nums[i] > nums[i-1] ) {
                    updp[i] = Math.max( updp[i-1] , downdp[i-1]+1 );
                }
                else{
                    updp[i] = updp[i-1];
                    downdp[i] = downdp[i-1];
                }
            }
            return Math.max(downdp[len-1],updp[len-1]);
        }

    }//动态规划    两个dp 问题   一个记录上升沿   一个记录下降沿

    public static class CombinationSumIV{
        static List<ArrayList> res = new ArrayList<>();
        public static int combinationSum4(int[] nums, int target) {
                if(nums.length==0)
                    return 0;
                boolean[] visited = new boolean[nums.length];
                ArrayList<Integer> seqr = new ArrayList<>();
                Arrays.sort(nums);

//                helper(nums,seqr,visited,target);
                return helper(nums,target);

        }

        public static void helperII(int[] nums,ArrayList<Integer> seqr,boolean[] visited,int target){
            visited = new boolean[nums.length];
            if(target<0)
                return;

            if(target==0){
                System.out.println(seqr.toString());
                res.add(new ArrayList(seqr));
                return;
            }


            for(int i = 0;i<nums.length;i++){
                    if(!visited[i]){
                        visited[i] = true;
                        seqr.add(nums[i]);
                        helperII(nums,seqr,visited,target-nums[i]);
                        seqr.remove(seqr.size()-1);
                        visited[i] = false;
                    }
            }
        }//  单纯递归   会有很多相同问题得求解  单纯递归会 超出时间

        public static int helper(int[] nums,int target){
            int[] dp = new int[target+1];

            dp[0] = 1;     //  (1,3,4)    dp[7] =  (1 + dp[6] )    + ( 3 + dp[4] )   + ( 4 + dp[3] )  dp[0] = 1  表示  前面的数字正好等于  target
//            dp[6]   =    1 + dp[5]       3 + dp[3]    4 + dp[2]
//            dp[5]   =    1 + dp[4]       3 + dp[2]    4 + dp[1]
//            dp[4]   =    1 + dp[3]        -------------------
//            dp[3]   =
//            dp[2]   =
//            dp[1]   =     1 + dp[0]
//            dp[0] = 0

            for(int i = 1; i<=target;i++){
                for(int num:nums)
                    if(num<=i)
                        dp[i] = dp[i] + dp[i-num];
            }
            return dp[target];
        }// 想法  很巧妙     自底向上

    }//377  动态问题

    public static class CanIWin{

        static int[] dp = new int[ 300 + 1 ];//  1 winner     0  failer      -1 initial

        public static boolean canIWin(int maxChoosableInteger, int desiredTotal) {

                    for(int i = 0;i < dp.length;i++)
                        dp[i] = -1;

                    for(int i = 0;i < Math.min(maxChoosableInteger+1,desiredTotal+1);i++)
                        dp[i] = 1;

                    boolean[] visited = new boolean[ maxChoosableInteger + 1 ];

                    int[] nums = new int[ maxChoosableInteger + 1 ];

                    for(int i = 0;i < maxChoosableInteger+1;i++)
                        nums[i] = i;


                    return helper(nums,visited,desiredTotal,dp);
        }

        public static boolean helper(int[] nums,boolean[] visited,int res,int[] dp ){
            if(res<=0)
                return true;
            if(dp[res]!=-1)
                return dp[res]==1 ? true : false;

            for(int i = nums.length-1 ; i > 0;i--){

                if(!visited[i]){

                    visited[i] = true;



//                    boolean result =  helper(nums,visited,res-i,dp);
//
//                    if(result){
//                        dp[res] = 0;
//                        return false;
//                    }

                    visited[i] = false;
                }
            }
            dp[res] = 0;
            return false;
        }

    }//464  待完成

    public static class IsSubsequence{

        public static boolean isSubsequence(String s, String t) {
          char[] childs = s.toCharArray();

          char[] cs = t.toCharArray();

          if(childs.length==0)
              return true;
          if(cs.length==0&&childs.length!=0)
              return false;

          int index = 0;
          int i = 0;

          for(;index < cs.length;){
              if(childs[i]==cs[index++]){
                  i++;
                  if(i==childs.length)
                      return true;
              }
          }
          return false;
        }// 1ms 击败78%
    }//392

    public static class IsNumber{
        public static boolean isNumber(String s) {
                char[] cs = s.toCharArray();
                int index = 0;
                boolean have_e = false;
                boolean e_sign = false;
                boolean have_point = false;
                boolean e_num = false;
                int end = cs.length-1;

                while ( index<cs.length && cs[index]==' ')
                    index++;
                while (end>0&& cs[end]==' ')
                    end--;
                end++;

                if(index == cs.length)
                    return false;
                if(cs[index]=='-'||cs[index]=='+')
                    index++;

                if((!s.contains("1")&&!s.contains("2")&&!s.contains("3")&&!s.contains("4")&&!s.contains("5")&&!s.contains("6")&&!s.contains("7")&&!s.contains("8")&&!s.contains("9")&&!s.contains("0")))
                    return false;
//|| ( (Math.abs(copy.indexOf(".")-copy.indexOf("e"))==1)&&copy.indexOf(".")!=-1&&copy.indexOf("e")!=-1 )
//            &&copy.length()==1
                String copy = s.trim();
                if(copy.lastIndexOf("e")==copy.length()-1||(copy.indexOf(".")==0)||copy.indexOf("e")==0 )//不能以小数点和e结尾
                    return false;
//            System.out.println(s.indexOf(".")-s.indexOf("e"));
//                cs = s.toCharArray();
//                index = 0;
                while ( index < end ){
//                    System.out.println(index!=cs.length-1);
                    if( !have_e && cs[index]=='e'){
                        have_e = true;
                    }else if(have_e && cs[index]=='e')
                        return false;
                    else if(have_e && cs[index]=='.')
                        return false;
                    else if(have_e && !e_sign &&cs[index]=='-')
                        e_sign = true;
                    else if(have_e && e_sign &&cs[index]=='-')
                        return false;
                    else if(have_e && e_sign &&cs[index]=='+')
                        return false;
                    else if(have_e && e_sign &&cs[index]=='.')
                        return false;
//                    else if(have_point && cs[index]==' ')
//                        return false;
                    else if(cs[index]==' ')
                        return false;
                    else if(!have_point&&cs[index]=='.')
                        have_point = true;
                    else if(have_point&&cs[index]=='.')
                        return false;
                    else if(cs[index]>'9'||cs[index]<'0')
                            return false;
                    else if(have_e && cs[index]<='9'&&cs[index]>='0')
                        e_num = true;

                    index++;
                }
                if(have_e&&!e_num)
                    return false;
                return true;
        }
    }

    public static class SortColor{
        public static void sortColorsII(int[] nums) {
            Arrays.sort(nums);
            for(int n:nums)
                System.out.print(n+" ");
        }

        public static void sortColorsIII(int[] nums) {//  0 - red     1 - white   2 - blue
            int red = 0,white = 0,blue = 0,tem = 0,index = 0;
            int len = nums.length;
            if(len<2)
                return;
            while ( index < len ){
                if(nums[index]==0)
                    red++;
                else if(nums[index]==1)
                    white++;
                else
                    blue++;
                index++;
            }
            index=0;
            if(red!=0)
            while (index<red)
                nums[index++] = 0;

            if(white!=0)
            while (index<red+white)
                nums[index++] = 1;

            if(blue!=0)
            while (index<len)
                nums[index++] = 2;
            return;
        }//扫描了两遍  空间是常数空间     还可以优化为扫描一遍   空间是常数空间


        public static void sortColorsIV(int[] nums) {//  0 - red     1 - white   2 - blue
                int p0 = 0,p1 = 0, p2 = 0;
                int index = 0;
                while (index < nums.length){
                    if(nums[index] == 0){
                        swap(nums,p0,index);
                        if(p0<p1)
                            swap(nums,index,p1);
                        p0++;
                        p1++;
                    }else if(nums[index]==1){
                        swap(nums,index,p1);
                        p1++;
                    }
                    index++;
                }
                return ;
        }

        public static void sortColors(int[] nums) {
            int red = 0,len = nums.length,blue = len - 1;
            int index = 0;
            while (index<=blue){
                if(nums[index]==2){
                    swap(nums,index,blue--);
                    continue;
                }

                if(nums[index]==0){
                    swap(nums,index++,red++);
                    continue;
                }
                index++;
            }
            return ;
        }//败给了双指针？？？无法理解

        public static void swap(int[] nums,int j,int k){
            int temp = nums[j];
            nums[j] = nums[k];
            nums[k] = temp;
        }

    }



}

