package com.leet;

import java.awt.event.TextEvent;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//import com.leet.ReadWriteLockDemo.myCache;

//保证  写 原子性    读高并发
class myCache{
    private volatile HashMap<String,Object> hashMap = new HashMap<>();//  因为  hashmap更改了以后需要第一时间告诉每个线程   所以需要有可见性可见顶
//    private Lock lock = new ReentrantLock();
    private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    public void put(String key,Object value){
//        lock.lock();
        rwlock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t 正在写入:"+key);
            TimeUnit.SECONDS.sleep(1);
            hashMap.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成:");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwlock.writeLock().unlock();
        }



    }

    public void get(String key){
        rwlock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t 开始读取数据:");
            TimeUnit.MICROSECONDS.sleep(300);
            Object res = hashMap.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取数据完成:"+res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwlock.readLock().unlock();
        }

    }
}

public class Main {

    public static void main(String[] args) {

//        int[] preorder = new int[]{1,2,4,5,7,3,6,8};
//        int[] inorder = new int[]{4,2,7,5,1,3,8,6};
//
//        BuildTreeByPre_Inorder.TreeNode res = BuildTreeByPre_Inorder.buildTree(preorder, inorder);
//        return;
        PopulatingNextRightPoint.Node node1 = new PopulatingNextRightPoint.Node(1);
        PopulatingNextRightPoint.Node node2 = new PopulatingNextRightPoint.Node(2);
        PopulatingNextRightPoint.Node node3 = new PopulatingNextRightPoint.Node(3);
        PopulatingNextRightPoint.Node node4 = new PopulatingNextRightPoint.Node(4);
        PopulatingNextRightPoint.Node node5 = new PopulatingNextRightPoint.Node(5);
        PopulatingNextRightPoint.Node node6 = new PopulatingNextRightPoint.Node(6);
        PopulatingNextRightPoint.Node node7 = new PopulatingNextRightPoint.Node(7);
//        PopulatingNextRightPoint.Node node8 = new PopulatingNextRightPoint.Node(8);
//        PopulatingNextRightPoint.Node node9 = new PopulatingNextRightPoint.Node(9);
//        PopulatingNextRightPoint.Node node10 = new PopulatingNextRightPoint.Node(10);
//        PopulatingNextRightPoint.Node node11 = new PopulatingNextRightPoint.Node(11);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
//        node1.right = node9;
//        node2.left = node3;
//        node3.left = node4;
//        node3.right = node5;
//        node2.right = node6;
//        node6.left = node7;
//        node6.right = node8;
//        node9.left = node10;
//        node9.right = node11;

        PopulatingNextRightPoint.connect(node1);
//        node1.left = node2;
//        node1.left = node2;
        return;

    }

    public static class SumRoottoLeaf{

//          Definition for a binary tree node.
        public class TreeNode {
              int val;
              TreeNode left;
              TreeNode right;
              TreeNode(int x) { val = x; }
        }

        public int sumNumbers(TreeNode root) {
            return 0;
        }
    }

    public static class Po{
        static class Node {
            public int val;
            public Node left;
            public Node right;
            public Node next;

            public Node() {}

            public Node(int _val) {
                val = _val;
            }

            public Node(int _val, Node _left, Node _right, Node _next) {
                val = _val;
                left = _left;
                right = _right;
                next = _next;
            }
        }
        public static Node connect(Node root) {
            if(root==null) return root;
            if(root.left!=null&&root.right!=null) root.left.next = root.right;
            if(root.right!=null) root.right.next = getNext(root.next);
            if(root.left!=null&&root.right==null) root.left.next = getNext(root.next);
            connect(root.right);
            connect(root.left);
            return root;
        }
        public static Node getNext(Node node){
            if(node==null)return null;
            if(node.left!=null)return node.left;
            if(node.right!=null)return node.right;
            return getNext(node.next);
        }

        public static void helper(Queue<Node> silbs){
            Node prenode = null,node = null;
            Queue<Node> temqueues = new LinkedList<>();
            if(silbs.isEmpty())return;
            if(!silbs.isEmpty())
                prenode = silbs.poll();
            if(prenode.left!=null)
                temqueues.add(prenode.left);
            if(prenode.right!=null)
                temqueues.add(prenode.right);

            while (!silbs.isEmpty()){
                node = silbs.poll();
                if(node==null)
                    continue;
                if(node.left!=null)
                    temqueues.add(node.left);
                if(node.right!=null)
                    temqueues.add(node.right);
                prenode.next = node;
                prenode = node;
            }
            if(prenode!=null)
                prenode.next = null;
            helper(temqueues);
        }//1ms  69%

    }//0ms 100%

    public static  class PopulatingNextRightPoint{
        static class Node {
            public int val;
            public Node left;
            public Node right;
            public Node next;

            public Node() {}

            public Node(int _val) {
                val = _val;
            }

            public Node(int _val, Node _left, Node _right, Node _next) {
                val = _val;
                left = _left;
                right = _right;
                next = _next;
            }
        }
        public static Node connect(Node root) {
            if(root==null)
                return root;
            root.next = null;
            Queue<Node> silbs = new LinkedList<>();
//            if(root.left!=null)
//                silbs.add(root.left);
//            if(root.right!=null)
//                silbs.add(root.right);
            addElement(silbs,root);
            helper(silbs);
            return root;
        }
        public static void helper(Queue<Node> silbs){

         if(silbs.isEmpty())return;
         Node prenode = null,node = null;
         Queue<Node> temqueues = new LinkedList<>();
         if(!silbs.isEmpty())
             prenode = silbs.poll();
         addElement(temqueues,prenode);
         while (!silbs.isEmpty()){
            node = silbs.poll();
            if(node==null)
                continue;
            addElement(temqueues,node);
            prenode.next = node;
            prenode = node;
         }
         if(prenode!=null)
            prenode.next = null;
         helper(temqueues);
        }

        public static void addElement(Queue<Node> queue,Node node){
            if(node.left==null)
                return ;
            queue.add(node.left);
            queue.add(node.right);
        }// 1ms

        public static void helperII(Node root){
            if(root==null || root.left==null)
                return;
            //保证这个节点肯定有两个子节点
            root.left.next = root.right;//因为是完美二叉树   每个父节点都有两个子节点
            if(root.next!=null)
                root.right.next = root.next.left;
            helperII(root.left);
            helperII(root.right);
        }//新思路   0ms
    }//116

    public static class FlattenBinaryTreeToLinked{

//          Definition for a binary tree node.
          public static class TreeNode {
              int val;
              TreeNode left;
              TreeNode right;
              TreeNode() {}
              TreeNode(int val) { this.val = val; }
              TreeNode(int val, TreeNode left, TreeNode right) {
                  this.val = val;
                  this.left = left;
                  this.right = right;
              }
         }

        public static void flatten(TreeNode root) {
            helper(root);
        }
        public static TreeNode helper(TreeNode root){
              if(root.right==null&&root.left==null)
                  return root;


              TreeNode q = root.right;

              if(root.left!=null){
                  root.right = root.left;
//                  TreeNode temres = helper(root.left);
                  TreeNode temres = helper(helper(root.left));
//                  temres = helper(temres);

                  temres.right = q;
                  root.left = null;
              }
              q = root.right;
              return helper(q);
        }
    }//0ms 100%

    public static class PathSumII{

//          Definition for a binary tree node.
        public static class TreeNode {
              int val;
              TreeNode left;
              TreeNode right;
              TreeNode(int x) { val = x; }
        }

        public static List<List<Integer>> res = new ArrayList<>();
        public static List<Integer> seqr = new ArrayList<>();

        public static List<List<Integer>> pathSum(TreeNode root, int sum) {
                if(root==null)
                    return res;
                seqr.add(root.val);
                helper(root,sum-root.val);
                return res;
        }
        public static void helper(TreeNode root, int sum){
            if(sum==0&&root.left==null && root.right==null)
                res.add(new ArrayList<>(seqr));
            if(root.left != null){
                seqr.add(root.left.val);
                helper(root.left,sum-root.left.val);
                seqr.remove(seqr.size()-1);
            }
            if(root.right != null){
                seqr.add(root.right.val);
                helper(root.right,sum-root.right.val);
                seqr.remove(seqr.size()-1);
            }
        }
    }//1ms  100%

    public static class BuildTreeByPre_Inorder{

//          Definition for a binary tree node.
          public static class TreeNode {
              int val;
              TreeNode left;
              TreeNode right;
              TreeNode(int x) { val = x; }
          }

        public static TreeNode buildTree(int[] preorder, int[] inorder) {

            TreeNode root = null;

            int len = preorder.length;

            ArrayList<Integer> pre = new ArrayList<>();
            ArrayList<Integer> inor = new ArrayList<>();

            if(len==0)
                return root;

//            for(int i = 0;i < len;i++){
//                pre.add(preorder[i]);
//                inor.add(inorder[i]);
//            }
//            return helper(pre,inor,root);
//            return helper(preorder,inorder,0,len-1,0,len-1,root);
            return root;
        }
        public static TreeNode helperI(List<Integer> pre,List<Integer> inor,TreeNode root){

            int len = pre.size();
            List<Integer> leftrees = null;
            List<Integer> righttrees = null;

            int val = pre.get(0);
            root = new TreeNode(val);
            int valindex = inor.indexOf(val);

            leftrees = inor.subList(0, valindex);
            righttrees = inor.subList(valindex + 1, inor.size());


            if (leftrees.size() == 1)
                root.left = new TreeNode(leftrees.get(0));
            else if (leftrees.size() > 1)
                root.left = helperI(pre.subList(1, len - righttrees.size()), leftrees, root.left);

            if (righttrees.size() == 1)
                root.right = new TreeNode(righttrees.get(0));
            else if (righttrees.size() > 1)
                root.right = helperI(pre.subList(leftrees.size() + 1, len), righttrees, root.right);
            return root;
        }//26ms  利用  List 实现类

        public static TreeNode helperII(int[] pre,int[] inor,int preL,int preR,int inorL,int inorR,TreeNode root){
              int len = preR - preL;
              int val = pre[preL];
              root = new TreeNode(val);
              int valindex = inorL;
              while (valindex<=inorR){
                  if(inor[valindex]==val)
                      break;
                  valindex++;
              }
              if(valindex-inorL == 1)
                  root.left = new TreeNode(inor[inorL]);
              else if(valindex-inorL > 1)
                  root.left = helperII(pre,inor,preL+1 , preR , inorL ,valindex-1,root.left);


              if(inorR - valindex == 1)
                  root.right = new TreeNode(inor[inorR]);
              else if(inorR - valindex > 1)
                  root.right = helperII(pre,inor,preL+1+(valindex-inorL),preR,valindex+1,inorR,root.right);
//            +(valindex-inorL)

              return root;
        }//4ms  直接计算 index

//        public static TreeNode helperIII()
    }

    public static class BinaryTreeLevelZigzag{

        public static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) { val = x; }
        }

        static List<List<Integer>> res = new ArrayList<>();
        static List<Integer> temres = new ArrayList<>();

        static Stack<TreeNode> prenode = new Stack<>();
        static Stack<TreeNode> node = new Stack<>();
        static boolean LR = false;
        public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {

            if(root==null)
                return res;
            prenode.push(root);
            helper(0);

            return res;
        }

        /**
         * 采用LR记录方向  2ms   13.1%
         */
        public static void helper(){
            if(prenode.size()==0)
                return;

            for(TreeNode node:prenode)
                temres.add(node.val);

            res.add(new ArrayList<>(temres));
            temres.clear();

            int size = prenode.size();
            for(int i = 0;i<size;i++)
                addnode(prenode.pop(),LR);
            LR = !LR;
            prenode.addAll(node);
            node.clear();
            helper();
        }
        public static void addnode(TreeNode node1,boolean LR){
            if(LR){
                if(node1.left!=null)
                    node.push(node1.left);
                if(node1.right!=null)
                    node.push(node1.right);
            }else{
                if(node1.right!=null)
                    node.push(node1.right);
                if(node1.left!=null)
                    node.push(node1.left);
            }
        }

        public static void helper(int level){
            if(prenode.size()==0)
                return;
            for(TreeNode node1:prenode)
                temres.add(node1.val);
            res.add(new ArrayList<>(temres));
            temres.clear();

            /**
             * 偶数层  从左到右
             */
            TreeNode root = null;
            int len = prenode.size();
            if((level&1) ==1) {  //true 偶数
                for(int i = 0;i<len;i++){
                     root = prenode.pop();
                     if(root.left!=null)
                        node.push(root.left);

                    if(root.right!=null)
                        node.push(root.right);
                }
            }else{
                for(int i = 0;i<len;i++){
                    root = prenode.pop();
                    if(root.right!=null)
                        node.push(root.right);
                    if(root.left!=null)
                        node.push(root.left);
                }
            }
            prenode.addAll(node);
            node.clear();
            helper(level+1);
        }// 2ms
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
    }//待续

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

    }//0ms 100%

    public static class SubSet{
        static List<Integer> sqer = new ArrayList<>();
        static List<List<Integer>> res = new ArrayList<>();
        public static List<List<Integer>> subsets(int[] nums) {

            int targetsize = 0;

            for(int i = 0;i <= nums.length;i++)
                helper(nums,0,i);

            return res;
        }
        public static void helper(int[] nums,int index,int tagetsize){
            if(sqer.size()==tagetsize){
                res.add(new ArrayList<>(sqer));
                return;
            }
            for(int i = index;i < nums.length;i++){
                sqer.add(nums[i]);
                helper(nums,i+1,tagetsize);
                sqer.remove(sqer.size()-1);
            }
        }
    }//1ms   95%

    public static class Combinations{



        public static List<List<Integer>> combine(int n, int k) {

            List<List<Integer>> res = new ArrayList<>();
            List<Integer> seqr = new ArrayList<>();

            int[] nums = new int[n];
            for(int i = 0 ; i < n ; i++)
                nums[i] = i+1;

            helper(nums,k,0,res,seqr);

            return res;
        }

        public static void helper(int[] nums,int targetsize,int index,List<List<Integer>> res,List<Integer> seqr){

            if(seqr.size()==targetsize){
                res.add(new ArrayList<>(seqr));
                return;
            }

            for(int i = index ;i <= nums.length - (targetsize-seqr.size());i++){//  最多只能从 n-targetsize开始取  并且 还能取得 targetsize - seqr.size()这么多位

                seqr.add(nums[i]);

                helper(nums,targetsize,i+1,res,seqr);

                seqr.remove(seqr.size()-1);
            }
        }//23ms   41%

    }

    public static class RemoveDup{
        public static int removeDuplicates(int[] nums) {
                int p = 0,q = 0,res = 0;

                int count = 0;

                while (q<nums.length){

                    if(nums[p]==nums[q])
                        count++;

                    else{
                        res = getRes(nums, p, res, count);
                        p = q;
                        count = 1;

                    }
                    q++;
                }
            res = getRes(nums, p, res, count);

            return res;
        }

        private static int getRes(int[] nums, int p, int res, int count) {
            if (count >= 2) {
                nums[res] = nums[p];
                nums[res + 1] = nums[p];
                res += 2;
            } else
                nums[res++] = nums[p];
            return res;
        }
    }//1ms  84%

    public static class PartitionList{

          public static class ListNode {
              int val;
              ListNode next;
              ListNode(int x) { val = x; }
          }

        public static ListNode partitionII(ListNode head, int x) {
              ListNode p = head,pre = head,res = null;
              ArrayList<ListNode> nodes = new ArrayList<>();

              if (head!=null){

                  if(pre.val >= x ){
                      nodes.add(pre);
                      pre = pre.next;
                      while (pre!=null&& pre.val >= x)
                          pre = pre.next;
                  }
              }else
                  return head;
              res = pre;
              if(pre==null)
                  return head;
                p = pre.next;


              while (p!=null){

                if(p.val < x ){
                    pre.next = p;
                    pre = p;
                }else {
                    nodes.add(p);
                    p = p.next;
                    while (p!=null && p.val >=x )
                        p = p.next;
                    continue;
                }
                p = p.next;
              }

              for(int i = 0;i < nodes.size();i++){
                  p = nodes.get(i);

                  pre.next = p ;
                  while (p!=null && p.val >= x){
                      pre = p;
                      p = p.next;
                  }
              }
              pre.next = null;
              return res;
        }//1ms    18%

        /**
         * 思路二
         *  双指针
         *      通过  pre 系列指针记录比x小的所有val节点
         *           after系列指针记录不小于x的所有val节点
         *           最后将pre的尾巴的next指针指向  pre的头部
         *      然后返回 头部指针
         * @param head
         * @param x
         * @return
         */
        public static ListNode partition(ListNode head, int x){
              if(head==null)
                  return head;

              ListNode pre_head = head,pre_tail = null;
              ListNode after_head = head,after_tail = null;

              while (after_head!=null && after_head.val < x)
                  after_head = after_head.next;

              while(pre_head!=null && pre_head.val >= x)
                  pre_head = pre_head.next;

              //after_head.val 大于等于 x      null
            if(after_head==null||pre_head==null)
                return head;

            after_tail = after_head;
            pre_tail = pre_head;

            ListNode p = head;
            while (p!=null){
                if( p == after_head || p == after_tail || p == pre_head || p == pre_tail){
                     p = p.next;
                     continue;
                }
                if(p.val < x){
                    pre_tail.next = p;
                    pre_tail = p;
                }else{
                    after_tail.next = p;
                    after_tail = p;
                }
                p = p.next;
            }
            pre_tail.next = after_head;
            after_tail.next = null;
            return pre_head;
        }//0ms   100%

    }

    public static class GrayCode{
        //格雷编码     i^(i>>1)    i  异或  i右移 一位

        /**
         * 小知识：
         *      格雷编码：在一组数的二进制编码中，任意两个相邻的编码，有且仅有一位二进制数不同，这种编码称为二进制编码
         *
         *      一个数  与  自己右移 一位     做异或运算       就可得到相邻的格雷码
         *
         * @param n
         * @return
         */

        public static List<Integer> grayCode(int n) {
            List<Integer> res = new ArrayList<>();

            for(int i = 0 ; i < 1<<n ; i++){
                res.add(i ^ (i>>1));
            }
            return res;
        }

//        public static void helper(char[] cs,int zeroindex){
//                flag = false;
//                res.add(Integer.parseInt(new String(cs),2));
//                for(int i = zeroindex;i >= 0;i--){
//                    cs[i] =  flag ==false? '1' : '0';
//                    helper(cs,i-1);
//                    cs[i] = cs[i]=='1' ? '0' : '1';
//                }
//                flag = true;
//        }
    }//格雷编码   自己  与  自己右移一位  做异或

    public static class ReverseLink{

//          Definition for singly-linked list.
          public static class ListNode {
              int val;
              ListNode next;
              ListNode(int x) { val = x; }
          }

        public static ListNode reverseBetween(ListNode head, int m, int n) {
            if(head==null)
                return head;
            ListNode pre = head,t = head,p = null,tail = head;

            for(int i = 1;i < m-1;i++)
                pre = pre.next;

            if(m==1)
                t = head;
            else
                t = pre.next;
            if(t==null)
                return head;

            p = t.next;

            for(int i = 1;i <n ;i++)
                tail = tail.next;

            while (t!=tail){
                t.next = tail.next;
                tail.next = t;
                t = p;
                p = p.next;
            }
            if(m!=1){
                pre.next = t;
                return head;
            }
            return tail;

        }
    }//0ms  100%

    public static class NumTrees{
        static Integer[] dp;
        public static int numTrees(int n) {
//            int[] dp = new int[n];
//            boolean[] visited = new boolean[n];
//            dp[0] = 1;
//            if(n==0)
//                return 1;
//
//            visited[0] = true;
            dp = new Integer[n+1];
            dp[0] = 1;
            dp[1] = 1;
//            System.out.println(dp[2]);
            return f(n);
        }
        public static int f( int n){

            if(dp[n]!=null)
                return dp[n];

            int res = 0;
            for(int i = 0;i<n;i++){
                if(dp[i]==null)
                    dp[i] = f(i);
                if(dp[n-1-i]==null)
                    dp[n-1-i] = f(n-1-i);
                res = res + dp[i]*dp[n-1-i];
            }
            return res;
        }
    }// 0ms  100%

    public static class BinaryTreeLevelOrder{

//          Definition for a binary tree node.
          public static class TreeNode {
              int val;
              TreeNode left;
              TreeNode right;
              TreeNode(int x) { val = x; }
          }

        static List<List<Integer>> res = new ArrayList<>();
        static List<Integer> temres = new ArrayList<>();
        static List<TreeNode> seqr = new ArrayList<>();
        static List<TreeNode> preseqr = new ArrayList<>();

        public static List<List<Integer>> levelOrder(TreeNode root) {

            if(root==null)
                return res;

            preseqr.add(root);

            helper();

            return res;
        }
        public static void helper(){


            if(preseqr.size()==0)
                return;

            for(TreeNode node:preseqr)
                    temres.add(node.val);

            res.add(new ArrayList<>(temres));
            temres.clear();

            for(TreeNode node:preseqr){
                if(node.left!=null)
                    seqr.add(node.left);
                if(node.right!=null)
                    seqr.add(node.right);
            }
            if(seqr.size()==0)
                return;
            preseqr.clear();
            preseqr.addAll(seqr);
            seqr.clear();
            helper();

        }
    }//1ms 93%



}


