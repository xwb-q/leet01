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

    public static class IsPowerOfTwo{
        public static boolean isPowerOfTwo(int n) {
            return ( n&(n-1) ) == 0 ;
        }
    }

    public static class NPreorder{
        class Node {
            public int val;
            public List<Node> children;

            public Node() {}

            public Node(int _val) {
                val = _val;
            }

            public Node(int _val, List<Node> _children) {
                val = _val;
                children = _children;
            }
        }
        public List<Integer> res = new ArrayList<>();
        public List<Integer> preorder(Node root) {
            if(root==null)
                return res;
            helper(root);
            return res;
        }
        public void helper(Node root){
            if(root==null)
                return;
            res.add(root.val);
            for(Node child : root.children)
                helper(child);
        }//递归版本

        public void helperII(Node root){
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            Node p = null;
            while (!stack.isEmpty()){
                p = stack.pop();
                if(p==null)
                    continue;
                res.add(p.val);
                for(int i = p.children.size()-1;i>=0;i--)
                    stack.push(p.children.get(i));
            }
        }//迭代
    }


    /**
     *三元组 (i, j, k) ，如果 nums1[i]2 == nums2[j] * nums2[k] 其中 0 <= i < nums1.length 且 0 <= j < k < nums2.length
     * 三元组 (i, j, k) ，如果 nums2[i]2 == nums1[j] * nums1[k] 其中 0 <= i < nums2.length 且 0 <= j < k < nums1.length
     */
    public static class NumTriplets{
        public static int numTriplets(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            return helper(nums1,nums2)+helper(nums2,nums1);

        }
        public static int helper(int[] nums1,int[] nums2){
            int sum = 0;
            int pretag = -1;
            int presum = 0;
            for(int i = 0;i<nums1.length;i++){
                if(nums1[i]==pretag){
                    sum += presum;
                    continue;
                }
                presum = 0;
                pretag = nums1[i];
                int j = 0,k = nums2.length-1;
                long tag = nums1[i];
                tag *= tag;

                while (j<k){
                    long tag1 = nums2[j];
                    tag1 = tag1 * nums2[k];
                    if(tag>tag1)
                        j++;
                    else if(tag<tag1)
                        k--;
                    else{
                        presum++;
                        if(nums2[j]==nums2[k]){
                            presum = presum-1+( (k-j+1)*(k-j) ) /2;
                            break;
                        }
                        int jj = 1;
                        while (j+jj<k&&nums2[j]==nums2[j+jj]){
                            jj++;
                            presum++;
                        }
//                        j += jj-1);
                        int kk = 1;
                        while (j<k-kk&&nums2[k]==nums2[k-kk]){
                            kk++;
                            presum++;
                        }
//                        k -=(kk-1);
//                        j += jj;
                        k--;
                        j++;
                    }
                }
                sum += presum;
            }
            return sum;
        }
    }//1577   6ms 92.07


    public static class LexicalOrder{
        static class Trie{
            public TrieNode root;

            class TrieNode{
                int num;
                TrieNode[] children = new TrieNode[10];
                //                boolean isLast = false;
                public TrieNode(int num){
                    this.num = num;
                }
                public void insert(int num){
                    char[] cs = (num+"").toCharArray();
                    int i = 0;
                    TrieNode p =root;
                    for(char c:cs){
                        i = c-'0';
                        TrieNode child = p.children[i];
                        if(child==null){
                            child = new TrieNode(i);
                            p.children[i] = child;
                        }
                        p = child;
                    }
//                    p.isLast = true;
                }

                public List<Integer> getNum(){
                    TrieNode p = root;
                    List<Integer> res = new ArrayList<>();
                    for(TrieNode child:root.children)
                        if(child!=null)
                            dfs(res,child,0);
                    return res;
                }
                public void dfs(List<Integer> res,TrieNode root,int num){
                    num = num * 10 + root.num;
//                    if(root.isLast)
                    res.add(num);

                    for(TrieNode child : root.children){
                        if(child!=null)
                            dfs(res,child,num);
                    }
                }
            }

            public Trie(){
                root = new TrieNode(-1);
            }
        }//字典树  12ms   18%
        public static List<Integer> lexicalOrder(int n) {
            Trie root = new Trie();
            while (n>0)
                root.root.insert(n--);
            return root.root.getNum();
        }
        public static List<Integer> lexicalOrderII(int n){
            List<Integer> res = new ArrayList<>();
            for(int i = 1;i< (n > 9?9:n);i++){
                res.add(i);
                helper(res,n,i*10);
            }
            return res;
        }//2ms 99%
        public static void helper(List<Integer> res,int n,int base){
            for(int i = 0;i<10;i++){
                if(base+i <= n){
                    res.add(base+i);
                    helper(res,n,(base+i)*10 );
                }
                else
                    return;
            }
        }

    }//2ms  99%

    public static class MissingNumber{
        public int missingNumber(int[] nums) {
            int res = 0;
            int len = nums.length;
            for(int num : nums)
                res += num;
            int tag = (len * (len-1))/2;
            int i = res - tag;
            return nums[nums.length-i];
        }
    }

    public static class Num{
        public int sumNums(int n) {
            int sum = n;
            boolean flag = (n>0 && (sum += sumNums(sum-1) ) >0 );
            return sum;
        }
    }

    public static class RangeBitWiseAnd{
        public static int rangeBitwiseAnd(int m, int n) {
            int res = m++;
            while (m>=0&&m<=n){
                if(res==0)
                    return 0;
                res &= m;
                m++;
            }
            return res;
        }
    }

    public static class MajorityElemente{
        public int majorityElement(int[] nums) {

            int count = 0;
            int res = 0;
            for(int num : nums){
                if(count==0){
                    res = num;
                    count = 1;
                }else if(res == num)
                    count++;
                else
                    count--;
            }
            return res;
        }//摩尔投票法
    }//1ms  99%

    public static class MajorityElement{
        public List<Integer> majorityElement(int[] nums) {
            HashMap<Integer,Integer> map = new HashMap<>();
            Integer count = 0;
            int len = nums.length;
            List<Integer> res = new ArrayList<>();
            for(int n:nums){
                if(res.contains(n))
                    continue;
                count = map.get(n);
                if(count!=null && count+1>len/3)
                    res.add(n);
                map.put(n,count==null?1:count+1);
            }
            if(len<3)
                for(int n:nums)
                    if(!res.contains(n))
                        res.add(n);
            return res;
        }//13ms 22.28%
        public static List<Integer> majorityElementII(int[] nums){
            int res1 = 0;
            int count1 = 0;
            List<Integer> res = new ArrayList<>();
            int res2 = 0;
            int count2 = 0;
            for(int num:nums){
                if(count1!=0&&res1==num){
                    count1++;
                    continue;
                }

                if(count2!=0&&res2==num){
                    count2++;
                    continue;
                }

                if(count1==0){
                    res1 = num;
                    count1 = 1;
                    continue;
                }
                if(count2==0){
                    res2 = num;
                    count2 = 1;
                    continue;
                }
                count1--;
                count2--;
            }
            count1=0;
            count2=0;
            for(int num:nums){
                if(num==res1)
                    count1++;
                else if(num==res2)
                    count2++;
            }
            if(count1>nums.length/3)
                res.add(res1);
            if(count2>nums.length/3)
                res.add(res2);
            return res;
        }//摩尔投票法
    }

    public static class BasicCalculator{
        public static int calculate(String s) {
            char[] cs = s.toCharArray();
            Stack<Integer> stack = new Stack<>();
            Stack<Integer> opts = new Stack<>();

            int val = -1;
            for(char c:cs){
                if(c==' ')
                    continue;
                else if(c=='*'||c=='/'){
                    if(val!=-1){
                        stack.push(val);
                        val = -1;
                    }
                    while (!opts.isEmpty()&&(opts.peek()==-6||opts.peek()==-1))
                        stack.push(opts.pop());
                    opts.push(c-'0');
                }else if(c=='+'||c=='-'){
                    if(val!=-1){
                        stack.push(val);
                        val=-1;
                    }
                    while (!opts.isEmpty())
                        stack.push(opts.pop());
                    opts.push(c-'0');
                }
                else{
                    if(val==-1)
                        val =c-'0';
                    else
                        val = val*10+(c-'0');
                }
//                    stack.push(c);
            }
            stack.push(val);
            while (!opts.isEmpty())
                stack.push(opts.pop());
            return calRes(stack);
        }//31ms  18%
        public static int calRes(Stack<Integer> stack){
            Stack<Integer> restack = new Stack<>();
            while (!stack.isEmpty())
                restack.push(stack.pop());
            int k = 0;
            while (!restack.isEmpty()){
                k = restack.pop();
                if(k<0){
                    switch (k){
                        case -5:
                            stack.push( plus(stack.pop(),stack.pop()) );
                            break;// +
                        case -3:
                            stack.push(reduce(stack.pop(),stack.pop()));
                            break;// -
                        case -6:
                            stack.push(multiplication(stack.pop(),stack.pop()));
                            break;// *
                        case -1:
                            stack.push(divide(stack.pop(),stack.pop()));
                            break;// /
                    }
                }else
                    stack.push(k);
            }
            return stack.pop();
        }
        public static int plus(int val1,int val2){
            return val1+val2;
        }
        public static int reduce(int val1,int val2){
            return val2-val1;
        }
        public static int multiplication(int val1,int val2){
            return val1*val2;
        }
        public static int divide(int val1,int val2){
            return val2/val1;
        }

        public static int calculateII(String s){
            char[] cs = s.toCharArray();
            Stack<Integer> numstack = new Stack<>();
            Stack<Character> opts = new Stack<>();
            int val = -1;
            for(char c:cs){
                if(c==' ')
                    continue;
                else if(c=='+'||c=='-'){
                    if(val!=-1){
                        numstack.push(val);
                        val=-1;
                    }
                    if(opts.isEmpty()){
                        opts.push(c);
                        continue;
                    }
                    else if(opts.peek()=='*'){
                        numstack.push( multiplication(numstack.pop(),numstack.pop()) );
                        opts.pop();
                    }
                    else if(opts.peek()=='/'){
                        numstack.push(divide(numstack.pop(),numstack.pop()));
                        opts.pop();
                    }else if(c=='+'&& opts.peek()=='-'){
                        numstack.push(reduce(numstack.pop(),numstack.pop()));
                        opts.pop();
                    }else if(c=='-'&& opts.peek()=='+'){
                        numstack.push(plus(numstack.pop(),numstack.pop()));
                        opts.pop();
                    }else if(c=='-'&& opts.peek()=='-'){
                        numstack.push(reduce(numstack.pop(),numstack.pop()));
                        opts.pop();
                    }
                    opts.push(c);
                }
                else if(c=='*'){
                    if(val!=-1){
                        numstack.push(val);
                        val = -1;
                    }
                    if(opts.isEmpty()){
                        opts.push(c);
                        continue;
                    }
                    else if(opts.peek()=='/'){
                        numstack.push(divide(numstack.pop(),numstack.pop()));
                        opts.pop();
                    }
                    opts.push(c);
                }
                else if(c=='/'){
                    if(val!=-1){
                        numstack.push(val);
                        val = -1;
                    }
                    if(opts.isEmpty()){
                        opts.push(c);
                        continue;
                    }
                    else if(opts.peek()=='*'){
                        numstack.push(multiplication(numstack.pop(),numstack.pop()));
                        opts.pop();
                    }
                    else if(opts.peek()=='/'){
                        numstack.push(divide(numstack.pop(),numstack.pop()));
                        opts.pop();
                    }
                    opts.push(c);
                }
                else {
                    if(val==-1)
                        val = c-'0';
                    else val = val*10 + (c-'0');
                }
            }
            numstack.push(val);
            while (!numstack.isEmpty()){
                if(opts.isEmpty())
                    break;
                char c = opts.pop();
                switch (c){
                    case '+':
                        numstack.push( plus(numstack.pop(),numstack.pop()) );
                        break;
                    case '-':
                        numstack.push( reduce(numstack.pop(),numstack.pop()) );
                        break;
                    case '*':
                        numstack.push( multiplication(numstack.pop(),numstack.pop()) );
                        break;
                    case '/':
                        numstack.push( divide(numstack.pop(),numstack.pop()) );
                        break;
                }
            }
            return numstack.pop();
        }//直接计算中缀表达式   不行!!!

//        public static int calculateIII(String s){
//
//        }
    }

    public static class InvertBinaryTree{

        //          Definition for a binary tree node.
        public static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) { val = x; }
        }

        public static TreeNode invertTree(TreeNode root) {
            if(root==null)
                return root;
            exchangeL_R(root);
            invertTree(root.left);
            invertTree(root.right);
            return root;
        }

        public static void exchangeL_R(TreeNode root){
            TreeNode left = root.left;
            root.left = root.right;
            root.right = left;
        }

    }

    public static class CountTreeNode{

        //          Definition for a binary tree node.
        static int nodeCount = 0;
        public static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) { val = x; }
        }
        public static int countNodes(TreeNode root) {
            if(root==null)
                return 0;
            int deep = getDeep(root);//最深层
            nodeCount = getNodeCount(deep-1);
            getExtraNode(deep,root,1);

            return nodeCount;
        }

        public static int getDeep(TreeNode root){
            int deep = 0;
            TreeNode p = root;
            while (p!=null){
                deep++;
                p = p.left;
            }
            return deep;
        }

        public static int getNodeCount(int deep){
            int n = 2;
            while (deep>1){
                n *= 2;
                deep--;
            }
            return n-1;
        }

        public static void getExtraNode(int deep,TreeNode root,int curdeep){
            if(root==null)
                return;
            if(curdeep==deep-1){
                if(root.left!=null)nodeCount++;
                if(root.right!=null)nodeCount++;
                else
                    return;
            }else{
                getExtraNode(deep,root.left,curdeep+1);
                getExtraNode(deep,root.right,curdeep+1);
            }
        }
    }//0ms 100%

    public static class Calculator{
        public static int calculate(String s) {
            s = s.trim();
            char[] cs = s.toCharArray();
            Queue<Integer> queue = new LinkedList<>();
            int val = -1;
            for(char c:cs){
                if(c==' ')
                    continue;
                else if(c=='+'||c=='-'||c=='('||c==')'){
                    if(val!=-1)
                        queue.add(val);
                    queue.add(c-'0');
                    val = -1;
                }else if(val==-1){
                    val = c-'0';
                }else{
                    val = val*10 + (c-'0');
                }
            }
            if(val!=-1)
                queue.add(val);
            //上面将前缀表达式   存储到 Int型的队列中，  解决多位数  运算
            Stack<Integer> stack = new Stack<>();
            calOrder(queue,stack);
            return stack.pop();
//            queue = trans(queue);//将中缀转后缀
//            return calRes(queue);
        }
        public static void inorderTransAfter(char[] cs,Stack<Character> stack,Queue<Character> queue){
            for(char c:cs){
                if(c=='+'||c=='-')
                    if(stack.isEmpty()||stack.peek()=='(')
                        stack.push(c);
                    else {
                        while (!stack.isEmpty()&&stack.peek()!='(')
                            queue.add(stack.pop());
                        stack.push(c);
                    }
                else if(c=='(')
                    stack.push(c);
                else if(c==' ')
                    continue;
                else if(c==')'){
                    while (stack.peek()!='(')
                        queue.add(stack.pop());
                    stack.pop();
                }
                else{
                    queue.add(c);
                }
            }
            while (!stack.isEmpty())
                queue.add(stack.pop());
        }
        public static int calAfterval(Queue<Character> queue){
            Stack<Integer> nums = new Stack<>();
            while (!queue.isEmpty()){
                Character c = queue.poll();
                if(c=='+'){
                    int val1 = nums.pop();
                    int val2 = nums.pop();
                    nums.push(val1+val2);
                }else if(c=='-'){
                    int val1 = nums.pop();
                    int val2 = nums.pop();
                    nums.push(val2-val1);
                }else
                    nums.push(c-'0');
            }
            return nums.pop();
        }//一位数计算  ，多位数就会出错

        public static Queue<Integer> trans(Queue<Integer> queue){
            Queue<Integer> str = new LinkedList<>();
            Stack<Integer> opts = new Stack<>();
            int c = -1;
            while (!queue.isEmpty()){
                c = queue.poll();
                if(c==-8)//  (
                    opts.push(c);
                else if(c==-7){// )
                    while (opts.peek()!=-8)
                        str.add(opts.pop());
                    opts.pop();
                }else if(c==-5||c==-3){// +  -
                    if(opts.isEmpty()||opts.peek()==-8)// (
                        opts.push(c);
                    else{
                        while (!opts.isEmpty()&&opts.peek()!=-8)
                            str.add(opts.pop());
                        opts.push(c);
                    }
                }else
                    str.add(c);
            }
            while (!opts.isEmpty())
                str.add(opts.pop());
            return str;
        }//中缀转后缀
        public static int calRes(Queue<Integer> queue){
            Stack<Integer> stack = new Stack<>();
            int val1 = 0;
            int val2 = 0;

            while (!queue.isEmpty()){
                int c = queue.poll();
                if(c == -5){
                    val1 = stack.pop();
                    val2 = stack.pop();
                    stack.push(val1+val2);
                }else if(c==-3){
                    val1 = stack.pop();
                    val2 = stack.pop();
                    stack.push(val2-val1);
                }else
                    stack.push(c);
            }
            return stack.pop();
        }//55ms

        public static void calOrder(Queue<Integer> queue,Stack<Integer> stack){

            int c = 0;
            int val2 = 0;
            int val1 = 0;
            while (!queue.isEmpty()){
                c = queue.poll();
                if(c==-7)// )
                    return;
                else if(c==-8)// (
                    calOrder(queue,stack);
                else if(c==-5){// +
                    val2 = queue.poll();
                    if(val2==-8){
                        calOrder(queue,stack);
                        val2 = stack.pop();
                    }
                    val1 = stack.pop();
                    stack.push(val1+val2);
                }else if(c==-3){
                    val2 = queue.poll();
                    if(val2==-8){
                        calOrder(queue,stack);
                        val2 = stack.pop();
                    }
                    val1 = stack.pop();
                    stack.push(val1-val2);
                }else
                    stack.push(c);
            }
        }//直接计算中缀表达式的值
    }

    public static class FindKthLargestNum{
        static int flag = 0;
        public static int findKthLargest(int[] nums, int k) {
            quickSort(nums,0,nums.length-1,k);
            return nums[nums.length-k];
        }
        public static void quickSort(int[] nums,int left,int right,int k){

            if(left>=right)
                return;
            int pivo = partion(nums,left,right);
            if(pivo+k==nums.length)
                flag = 1;
            if(flag==0)
                quickSort(nums,left,pivo-1,k);
            if(flag==0)
                quickSort(nums,pivo+1,right,k);

        }
        public static int partion(int[] nums,int low,int hig){
            int tem = nums[low];
            while (low<hig){
                while (low<hig && nums[hig]>=tem) --hig;
                nums[low] = nums[hig];
                while (low<hig && nums[low]<=tem)++low;
                nums[hig] = nums[low];
            }
            nums[low] = tem;
            return low;
        }
    }// 2ms  90%

    public static class PreTree{
        class Trie {
            public TrieNode root;
            class TrieNode{
                TrieNode[] children = new TrieNode[26];
                boolean isLast = false;
//                int data = 0;
            }

            /** Initialize your data structure here. */
            public Trie() {
                root = new TrieNode();
            }

            /** Inserts a word into the trie. */
            public void insert(String word) {
                char[] cs = word.toCharArray();
                TrieNode p = root;
                for(char c:cs){
                    TrieNode child =  p.children[c-'a'];

                    if(child==null) {
                        child = new TrieNode();
//                        child.data = c - 'a';
                        p.children[c - 'a'] = child;
                    }
                    p = child;
                }
                p.isLast = true;
            }

            /** Returns if the word is in the trie. */
            public boolean search(String word) {
                char[] cs = word.toCharArray();
                TrieNode p = root;
                TrieNode child = null;
                for(char c:cs){
                    child = p.children[c-'a'];
                    if(child==null)
                        return false;
                    p = child;
                }
                return p.isLast;
            }

            /** Returns if there is any word in the trie that starts with the given prefix. */
            public boolean startsWith(String prefix) {
                char[] cs = prefix.toCharArray();
                TrieNode p = root;
                TrieNode child = null;
                for(char c:cs){
                    child = p.children[c-'a'];
                    if(child==null)
                        return false;
                    p = child;
                }
                return true;
            }
        }
    }//字典树   前缀树

    public static class BinaryTreeRightView{


        //          Definition for a binary tree node.
        public static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) { val = x; }
        }


        public static List<Integer> res = new ArrayList<>();

        public static List<Integer> rightSideView(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();
            Queue<TreeNode> prequeue = new LinkedList<>();
            TreeNode p = null;
            if(root==null)
                return res;
            prequeue.add(root);
            queue.addAll(prequeue);

            while (!queue.isEmpty()){
                res.add(prequeue.peek().val);
                queue.clear();
                while (!prequeue.isEmpty()){
                    p = prequeue.poll();
                    if(p.right!=null)
                        queue.add(p.right);
                    if(p.left!=null)
                        queue.add(p.left);
                }
                prequeue.addAll(queue);
            }
            return res;
        }//2ms
    }//未完待续

    public static class CycleList{

        //          Definition for singly-linked list.
        public static class ListNode {
            int val;
            ListNode next;
            ListNode(int x) {
                val = x;
                next = null;
            }
        }

        public static ListNode detectCycle(ListNode head) {

            if(head==null || head.next==null)
                return null;
            HashMap<ListNode,Integer> hashMap = new HashMap<>();
            int i = 0;
            while (head!=null){
                if (hashMap.containsKey(head))
                    return head;
                hashMap.put(head,i++);
                head = head.next;
            }
            return null;
        }//5ms 22.57%

        /**当有圈的时候
         * 当慢指针  与快指针相遇的时候   假设 慢指针走了  L1 的长度     此时   快指针走了  2*L1 的长度
         * 快指针   比 慢指针 多走了  n圈  即   2*L1 - L1 = L1 = n * L ( L:圈的长度     L1:从头节点到相遇节点的距离 )
         * 从相遇节点  开始  再继续走  L1的距离，依然会回到相遇节点       并且从  头节点开始走  L1的距离，也会到相遇节点
         * 假设第一个 入圈 节点  到相遇节点的距离  是 B（这个我们不知道具体是多少）
         * 从头节点走 L1-B 的距离  就正好是入圈的第一个节点       从相遇节点  走 L1-B 的距离 也正好是入圈的第一个节点
         * 但是 B 我们无法得知，但是如果从头节点 与 相遇节点 同时开始走，第一个相遇的节点肯定是入圈的节点，因为  头节点到相遇节点的距离和相遇节点出发回到相遇节点的距离是一样的，所以肯定会相遇
         * 距离，就类似于 一个   人  字
         * 从 人 字 的两边出发，到上面的距离如果一样远，那么同步出发，那么肯定会在交点相遇
         * @param head
         * @return
         */
        public static ListNode detectCycleII(ListNode head){
            if(head==null || head.next==null)
                return null;
            ListNode slow = hasCycle(head);
            if(slow!=null){
                ListNode q = head;
                while (q!=slow){
                    q = q.next;
                    slow = slow.next;
                }
                return slow;
            }
            return null;
        }//0ms 100%
        public static ListNode hasCycle(ListNode head){
            ListNode fast = head;
            ListNode slow = head;
            while (fast!=null&&fast.next!=null){
                fast = fast.next.next;
                slow = slow.next;
                if(slow==fast)
                    return slow;
            }
            return null;
        }

    }//0ms

    public static class OrderTree{

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

        public static List<Integer> res = new ArrayList<>();

        public static List<Integer> preorderTraversal(TreeNode root) {
            helper(root);
            return res;
        }
        public static void helper(TreeNode root){
            if(root==null)
                return;
            res.add(root.val);
            helper(root.left);
            helper(root.right);
        }

        public static List<Integer> postorderTraversal(TreeNode root) {
            helperII(root);
            return res;
        }
        public static void helperII(TreeNode root){
            if(root==null)
                return;
            helperII(root.left);
            helperII(root.right);
            res.add(root.val);
        }
    }

    public static class SortLinkedList{

        //          Definition for singly-linked list.
        public static class ListNode {
            int val;
            ListNode next;
            ListNode() {}
            ListNode(int val) { this.val = val; }
            ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        }

        public static ListNode sortList(ListNode head) {
            if(head==null)
                return null;
            ListNode q = new ListNode(-1);
            q.next = head;
            ListNode pre = q;
            ListNode w = pre.next;

            ListNode p = head.next;
            w.next = null;
            while (p!=null){
                pre = q;
                w = pre.next;
                while (w.next!=null){
                    if(w.val<p.val){
                        pre = w;
                        w = w.next;
                    }else{
                        ListNode node = p.next;
                        p.next = w;
                        pre.next = p;
                        p = node;
                        break;
                    }
                }
                if(w.next==null)
                    if(w.val<p.val){
                        w.next = p;
                        w = p;
                        p = p.next;
                        w.next = null;
                    }else{
                        ListNode node = p.next;
                        p.next = w;
                        pre.next = p;
                        w.next = null;
                        p = node;
                    }
            }
            return q.next;
        }//时间复杂度太高   直接插入排序  O(n^2)

        public static ListNode sortListII(ListNode head){
            if(head==null || head.next==null)
                return head;
            ListNode fast = head;
            ListNode slow = head;
            while (fast.next!=null &&fast.next.next!=null){
                fast = fast.next.next;
                slow = slow.next;
            }

            ListNode mid = slow.next;
            slow.next = null;
            ListNode left = sortListII(head);
            //mid 递归
            ListNode right = sortListII(mid);
//            return mergeSort(left,right);
            return  concat(left,right);
        }//二路归并排序    快慢指针

        public static ListNode mergeSort(ListNode left,ListNode right){
            if(left==null)
                return right;
            if(right==null)
                return left;
            if(left.val<right.val){
                left.next = mergeSort(left.next,right);
                return left;
            }else{
                right.next = mergeSort(left,right.next);
                return right;
            }
        }//二合一

        public static ListNode merge(ListNode head,int tagsize){
            int size = 1;
            int i = 0;
            ListNode p1 = head;
            ListNode p2 = head.next;
            ListNode root = null;
            while (head!=null){

                p1 = cutList(head,size);
                while (i<size)
                    head = head.next;

                p2 = cutList(head,size);
                while (i<size && head!=null)
                    head = head.next;

                p1 = concat(p1,p2);
                size <<= 1;

//                if(size<tagsize)

            }
            return root;
        }
        public static ListNode cutList(ListNode head,int size){
            ListNode root = head;
            ListNode p = head;
            while (size>1){
                p = p.next;
                size--;
            }
            p.next  =null;
            return root;
        }//断链   切出来规定大小的节点
        public static ListNode concat(ListNode root1,ListNode root2){
            ListNode root = null;
            //寻找二路归并的头节点
            if(root1.val<root2.val){
                root = root1;
                root1 = root1.next;
            }else{
                root = root2;
                root2 = root2.next;
            }
            //工作节点
            ListNode p = root;
            while (root1!=null && root2!=null){
                if(root1.val<root2.val){
                    p.next = root1;
                    root1 = root1.next;
                }else{
                    p.next = root2;
                    root2 = root2.next;
                }
                p = p.next;
            }
            //连接上 最后剩下的有序链表
            p.next = root1==null ? root2 : root1;
            //返回头节点
            return root;
        }//二路合一
    }//6ms 82.13%

    public static class ReverPolishNotation{
        public static int evalRPN(String[] tokens) {
            if(tokens.length==0)
                return 0;
            Stack<Integer> nums = new Stack<>();
            for(String s:tokens)
                if(  "+".equals(s) ){
                    int val1 = nums.pop();
                    int val2 = nums.pop();
                    nums.push(val1+val2);
                }
                else if(  "-".equals(s) ){
                    int val1 = nums.pop();
                    int val2 = nums.pop();
                    nums.push(val1-val2);
                }
                else if(  "*".equals(s) ){
                    int val1 = nums.pop();
                    int val2 = nums.pop();
                    nums.push(val1*val2);
                }
                else if(  "/".equals(s) ){
                    int val1 = nums.pop();
                    int val2 = nums.pop();
                    nums.push(val2/val1);
                }
                else
                    nums.push(Integer.parseInt(s));


            return nums.pop();

        }//7ms   59%

        public static int evalRPNII(String[] tokens){
            int[] nums = new int[tokens.length];
            int size = 0;
            for(String s:tokens)
                if(  "+".equals(s) ){
                    int val1 = nums[--size];
                    int val2 = nums[--size];
                    nums[size++] = val1+val2;
                }
                else if(  "-".equals(s) ){
                    int val1 = nums[--size];
                    int val2 = nums[--size];
                    nums[size++] = val2-val1;
                }
                else if(  "*".equals(s) ){
                    int val1 = nums[--size];
                    int val2 = nums[--size];
                    nums[size++] = val1*val2;
                }
                else if(  "/".equals(s) ){
                    int val1 = nums[--size];
                    int val2 = nums[--size];
                    nums[size++] = val2/val1;
                }
                else
                    nums[size++] = Integer.parseInt(s);
            return nums[--size];
        }//3ms  99.52%
    }//3ms 99.56%

    public static class WordsBreak{
        public static boolean wordBreak(String s, List<String> wordDict) {
            char[] cs = s.toCharArray();

            return helper(cs,0,wordDict);
        }
        public static boolean helper(char[] cs,int startindex,List<String> wordDict){
            if(startindex==cs.length)
                return true;
            for(int i = 0;i < wordDict.size();i++){
                String  str = wordDict.get(i);
                if(judge(cs,startindex,str))
                    if(helper(cs,startindex+str.length(),wordDict))
                        return true;
            }
            return false;
        }
        public static boolean judge(char[] cs,int startindex,String str){
            char[] cw = str.toCharArray();
            for(int i = 0;i < cw.length;i++){
                if(startindex+i>= cs.length || cs[startindex+i]!=cw[i])
                    return false;
            }
            return true;
        }//遇到特殊例子会发生深度递归，会很慢

        /**
         * 字典树方法
         */
        public static boolean wordBreak(){
            return true;
        }

    }


    /**
     * 解题思路：
     *遍历二维矩阵的四条边 ， 发现  ‘O’ 以后，进行深度优先遍历，将所有遍历到的  'O' 变为   '-' ，然后再将二维矩阵中 所有的 'O'变为  'X'  因为此时的'O'就是被包围了
     * 再将剩下的'-'变为'O'，
     *
     * 对未受到保护的字符的轰炸
     *
     */
    public static class SurroundRegonII{
        public static void solve(char[][] board) {

            if(board.length==0 || board[0].length==0)
                return;

//            boolean[][] flag = new boolean[board.length][board[0].length];
            int i = board.length-1;
            int j = 0;

            while (j < board[0].length){
                if(board[0][j]=='O')
                    dfs(board,0,j);
                if(board[i][j]=='O')
                    dfs(board,i,j);
                j++;
            }
            i = 0;
            j = board[0].length-1;
            while (i<board.length){
                if(board[i][0]=='O')
                    dfs(board,i,0);
                if(board[i][j]=='O')
                    dfs(board,i,j);
                i++;
            }
            i = 1;
            while (i<board.length){
                j = 1;
                while (j<board[0].length){
                    if(board[i][j]=='O')
                        board[i][j] = 'X';
                    else if(board[i][j]=='-')
                        board[i][j] = 'O';
                    j++;
                }
                i++;
            }
        }
        public static void dfs(char[][] board,int i,int j){
            if(i<0||j<0||i>board.length-1||j>board[0].length-1||board[i][j]=='X'||board[i][j]=='-')
                return;
            board[i][j] = '-';
            dfs(board,i-1,j);
            dfs(board,i+1,j);
            dfs(board,i,j-1);
            dfs(board,i,j+1);
        }
    }//2ms

    public static class SurroundRegon{
        static boolean[][] path;
        public static void solve(char[][] board) {
            if(board.length==0 || board[0].length==0)
                return;
            boolean[][] flag = new boolean[board.length][board[0].length];
            int i = board.length-1;
            int j = 0;
            path = new boolean[flag.length][flag[0].length];
//            int direction = 0;//方向位     1 上    2下    3 左   4 右

            while (j < board[0].length){
                if(board[0][j]=='O')
                    flag[0][j] = true;
                if(board[i][j]=='O')
                    flag[i][j] = true;
                j++;
            }
            i = 0;
            j = board[0].length-1;
            while (i<board.length){
                if(board[i][0]=='O')
                    flag[i][0] = true;
                if(board[i][j]=='O')
                    flag[i][j] = true;
                i++;
            }
            i = 1;
            while (i<board.length-1){
                j = 1;
                while (j<board[0].length-1){
                    if(board[i][j]=='O' && !flag[i][j]){
                        judge(board,flag,i,j,4);
                        path[i][j] = false;
                    }
                    j++;
                }
                i++;
            }
        }
        public static void judge(char[][] board,boolean[][] flag,int i,int j,int direction){

            if(flag[i-1][j] || flag[i][j-1] || flag[i+1][j] || flag[i][j+1]){
                flag[i][j] = true;
                changeFtoT(board,i,j,flag);
            }
            else if(search(board, flag, i, j, direction)){
                flag[i][j] = true;
                changeFtoT(board,i,j,flag);
            }
            else{
                board[i][j] = 'X';
                changeOtoX(board,i,j);
            }

        }
        public static boolean search(char[][] board,boolean[][] flag,int i,int j,int direction){

            if(path[i][j])
                return false;
            path[i][j] = true;

            if( flag[i-1][j] || flag[i][j-1] || flag[i+1][j] || flag[i][j+1])
                return true;
            if( direction!=2 && board[i-1][j]=='O' && !flag[i-1][j]) {//上
                if (search(board, flag, i - 1, j, 1)) {
                    path[i - 1][j] = false;
                    return true;
                }
            }
            if( direction!=4 && board[i][j-1]=='O' && !flag[i][j-1]) {//左
                if (search(board, flag, i, j - 1, 3)){
                    path[i][j-1] = false;
                    return true;
                }

            }
            if( direction!=1 && board[i+1][j]=='O' && !flag[i+1][j]) {//下
                if (search(board, flag, i + 1, j, 2)){
                    path[i+1][j] = false;
                    return true;
                }
            }
            if( direction!=3 && board[i][j+1]=='O' && !flag[i][j+1]) {//右
                if (search(board, flag, i, j + 1, 4)){
                    path[i][j+1] = false;
                    return true;
                }
            }
            path[i][j] = false;
            return false;
        }
        public static void changeOtoX(char[][] board,int i,int j){
            if(board[i-1][j]=='O'){
                board[i-1][j]='X';
                if(i-1!=0)
                    changeOtoX(board,i-1,j);
            }
            if(board[i][j-1]=='O'){
                board[i][j-1]='X';
                if(j-1!=0)
                    changeOtoX(board,i,j-1);
            }
            if(board[i+1][j]=='O'){
                board[i+1][j]='X';
                if(i+1!=board.length-1)
                    changeOtoX(board,i+1,j);
            }
            if(board[i][j+1]=='O'){
                board[i][j+1]='X';
                if(j+1!=board[0].length-1)
                    changeOtoX(board,i,j+1);
            }
        }

        public static void changeFtoT(char[][] board,int i,int j,boolean[][] flag){
            if(board[i-1][j]=='O' && !flag[i-1][j]){
                flag[i-1][j] = true;
                if(i-1!=0)
                    changeFtoT(board,i-1,j,flag);
            }

            if(board[i+1][j]=='O' && !flag[i+1][j]){
                flag[i+1][j] = true;
                if(i+1!=board.length-1)
                    changeFtoT(board,i+1,j,flag);
            }

            if(board[i][j-1]=='O' && !flag[i][j-1]){
                flag[i][j-1] = true;
                if(j-1!=0)
                    changeFtoT(board,i,j-1,flag);
            }

            if(board[i][j+1]=='O' && !flag[i][j+1]){
                flag[i][j+1] = true;
                if(j+1!=board[0].length-1)
                    changeFtoT(board,i,j+1,flag);
            }
        }
    }//算法时间复杂度过慢

    public static class SumPath{
        //                        Definition for a binary tree node.
        public static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) { val = x; }
        }

        public static int sumNumbers(TreeNode root) {

            if(root==null)
                return 0;

            return bfs(root,0);

        }

        public static int bfs(TreeNode root,int res){
            int levelres = 0;
            if(root.left==null&&root.right==null)
                return root.val+res*10;
            if(root.left!=null)
                levelres += bfs(root.left,res*10+root.val);
            if(root.right!=null)
                levelres += bfs(root.right,res*10+root.val);
            return levelres;
        }

        public static int getMultiple(int num){
            int res = 1;

            while (num>1)
                res *= 10;

            return res;
        }
    }//0ms

    public static class SortedListToBinaryTree{

        //          Definition for singly-linked list.
        public static class ListNode {
            int val;
            ListNode next;
            ListNode() {}
            ListNode(int val) { this.val = val; }
            ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        }


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

        public static TreeNode sortedListToBST(ListNode head) {

            TreeNode root = null;

            if(head==null)
                return null;
            if(head.next==null)
                return new TreeNode(head.val);

            root = helper(head);

            return root;

        }
        //第一个要加入树   第二个不用加入树
        public static TreeNode helper(ListNode head){
            if(head==null)
                return null;
            if(head.next==null)
                return new TreeNode(head.val);
            ListNode fast = head;
            ListNode slow = head;
            ListNode preslow = head;

            TreeNode root = null;

            while (fast!=null){
                if(fast.next==null)
                    break;
                fast = fast.next.next;
                preslow = slow;
                slow = slow.next;
            }

            root = new TreeNode(slow.val);
            ListNode next = slow.next;//记录整个右子树的 链表的  头节点
            preslow.next = null;//在slow的前面一个节点进行 将链表切割

            root.left = helper(head);
            root.right = helper(next);
            return root;
        }

    }//快慢指针   将有序链表转换为平衡二叉搜索树   0ms

}
