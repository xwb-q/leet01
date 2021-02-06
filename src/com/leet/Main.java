package com.leet;

import com.leet.test.A;
import com.leet.test.C;
import com.sun.beans.editors.ShortEditor;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
    private int count = 1; // A 1    B 2   C 3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    public void myprint(){
        lock.lock();
        try{
            while (count != 1)
            {
                System.out.println(count+Thread.currentThread().getName());
                c1.await();
            }
            for(int i = 1;i <= 5*count;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            if(count==1){
                count = 2;
                c2.signal();
            }
            else if(count == 2){
                count = 3;
                c3.signal();
            } else {
                count = 1;
                c1.signal();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void print5(){
        lock.lock();
        try{

            while (count!=1){
                c1.await();
            }
            for(int i = 1;i<=5;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            count = 2;
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void print10(){
        lock.lock();
        try{

            while (count!=2){
                c2.await();
            }
            for(int i = 1;i<=10;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            count = 3;
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void print15(){
        lock.lock();
        try{

            while (count!=3){
                c3.await();
            }
            for(int i = 1;i<=15;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            count = 1;
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class Main {

    public static void main(String[] args) {
//        3[a]2[bc]6[avb3[dfg2[2[ag]d]as]fa]
//        String str = "a"+"b";//2210
//        String str2 = "ab";//2211
//        System.out.println(str==str2);
//        String str3 = "c";//2210
//        String str4 = str3+"d";//2211
//        System.out.println(str4=="cd");//false
//        String s = "11";

        String s1 = new String("1")+new String("1");
//        s1.intern();
        String s = "1";

//        System.out.println(s1==s);
//        String res = DecodeString.decodeString("3[a]2[bc]6[av2[2[ag]d]fa]");
        return;
    }

    /*
    玩家由字符 'C' （代表猫）和 'M' （代表老鼠）表示。
    地板由字符 '.' 表示，玩家可以通过这个格子。
    墙用字符 '#' 表示，玩家不能通过这个格子。
    食物用字符 'F' 表示，玩家可以通过这个格子。
    字符 'C' ， 'M' 和 'F' 在 grid 中都只会出现一次。

    如果猫跟老鼠处在相同的位置，那么猫获胜。
    如果猫先到达食物，那么猫获胜。
    如果老鼠先到达食物，那么老鼠获胜。
    如果老鼠不能在 1000 次操作以内到达食物，那么猫获胜。
    */
    public static class CanMouseWin{
        public static class Point{
            int x;//x 行
            int y;// y 列
            public Point(int x,int y){
                this.x = x;
                this.y = y;
            }
        }
        public static boolean canMouseWin(String[] grid, int catJump, int mouseJump) {

            char[][] cs = getGridcs(grid);
            Point cat = find('C',cs);
            Point mouse = find('M',cs);
            Point food = find('F',cs);
            return true;
        }

        private static boolean canWin(char[][] cs,Point cat,Point mouse){
                return true;
        }
        private static void move(char[][] cs,Point point){
            if(point.x!=0 && cs[point.x-1][point.y]!='#')
                point.x--;
        }

        private static char[][] getGridcs(String[] grid) {
            int m = grid.length;
            int n = grid[0].length();
            char[][] cs = new char[m][n];
            for(int i = 0;i<m;i++){
                cs[i] = grid[i].toCharArray();
            }
            return cs;
        }

        private static Point find(char tag,char[][] cs){
            for(int i = 0;i<cs.length;i++)
                for(int j = 0;j < cs[0].length;j++)
                    if(cs[i][j]==tag)
                        return new Point(i,j);
                    return null;
        }

    }

    public static class DecodeString{
//        public static String res = "";
//        public static Queue<Character> queue = new LinkedList<>();

        public static int startindex =0;

        public static String decodeString(String s) {
            char[] cs = s.toCharArray();
            StringBuilder res = new StringBuilder();
            while (startindex<cs.length){
                if((cs[startindex] >= 'a'&&cs[startindex]<='z')||(cs[startindex] >= 'A'&&cs[startindex]<='Z')){
                    res.append(cs[startindex]) ;
                }
                else if((cs[startindex] > '0'&&cs[startindex]<='9')){
                    res.append(helperII(cs,"")) ;
                }
                startindex++;
            }
            return res.toString();
        }

        //进来条件一定是  遇到    [
        public static String helperII(char[] cs,String res){
            StringBuilder tem = new StringBuilder();
            char c = cs[startindex];
            while (c!=']'){
                if((c>'0'&&c<='9')){
                    int count = findNum(cs);
                    startindex ++;
                    String str = helperII(cs,"");
                    while (count>0){
                        tem .append(str);
                        count--;
                    }
                    if(startindex+1==cs.length)
                        break;
                    c = cs[++startindex];
                    continue;
                }else if((c>='a'&&c<='z')||(c>='A'&&c<='Z')){
                    tem.append(c) ;
                }
                if(startindex+1==cs.length)
                    break;
                c = cs[++startindex];
            }
            res += tem.toString();
            return res;
        }
        public static int findNum(char[] cs){
            char c = cs[startindex];
            StringBuilder str = new StringBuilder();
            while (c!='['){
                str .append(c);
                c = cs[++startindex];
            }
            return Integer.parseInt(str.toString());
        }
    }//%84

    public static class IntegerReplacement{
        public static int integerReplacement(int n) {
//            if(n==2147483647)
//                return helper(n-1,0);
            return helperII(n,0);
        }
        public static int helper(int n,int count){
            if(n==1)
                return count;
            if((n & 1)==1) {//奇数
                int plus1 = helper(n + 1, count + 1);
                int sub1 = helper(n-1,count+1);
                return plus1 < sub1 ? plus1 : sub1;
            }else
                return helper(n>>1,count+1);
        }//2147483647  栈溢出
        public static int helperII(int n,int count){
            if((n&(n-1))==0){//2 ^ n     01000000000
                while (n!=1){
                    n >>= 1;
                    count++;
                }
                return count;
            }
            while ((n&1)==0){
                n >>= 1;
                count++;
            }
            if(n==3)
                return count+2;
            int copy = n;
            int arg = 0;
            while ((copy&1)==1){
                copy >>= 1;
                arg++;
            }
            if(copy==0)
                return helperII(n+1,count+1);
            if(arg>=3)
                return helperII(n+1,count+1);

            copy >>= 1;
            int arg2 = 0;
            while ((copy&1)==1){
                copy >>= 1;
                arg2++;
            }
            if(arg2>0)
                return helperII(n+1,count+1);
            return helperII(n-1,count+1);
        }//100%
    }//100%

    public static class LastRemaining{
        public static int lastRemaining(int n) {
            List<Integer> nums = new LinkedList<>();
            int i = 1;
            while (i<=n)
                nums.add(i++);

            boolean flag = true;
            while (true) {
                if(nums.size()==1)
                    return nums.get(0);
                i = 0;
                if(!flag && nums.size()%2==0)
                    i = 1;
                for(;i<nums.size();i++){
                    nums.remove(i);
                }
                flag = !flag;
            }
        }//太慢了
    }

    //竞赛
    //1
    public static class CountGoodRectangles{
        public static int countGoodRectangles(int[][] rectangles) {
            int count = 0;
            HashMap<Integer,Integer> map = new HashMap<>();
            int maxlen = -1;
            int edge = 0;
            for(int i = 0;i < rectangles.length;i++){
                 edge = rectangles[i][0] < rectangles[i][1] ? rectangles[i][0] : rectangles[i][1];
                 if(maxlen < edge)
                     maxlen = edge;
                 if(! map.containsKey(edge) ){
                     map.put(edge,1);
                 }else
                     map.put(edge,map.get(edge)+1);
            }
            return map.get(maxlen);
        }
    }

    //2  [20,10,6,24,15,5,4,30]
    public static class TupleSameProduct{
        public static int tupleSameProduct(int[] nums) {
            int count = 0;
            int a = 0,b = 0, c = 0,d = 0;
            for(int i = 0;i<nums.length-3;i++)
                for(int j = i+1;j < nums.length-2;j++)
                    for(int k = j+1;k < nums.length-1;k++)
                        for(int l = k+1; l< nums.length;l++){
                            a = nums[i];
                            b = nums[j];
                            c = nums[k];
                            d = nums[l];
                            if(a*b == c*d || a*c==b*d || a*d==b*c)
                                count++;
                        }
            return count*8;
        }

        public static int tupleSameProductII(int[] nums){
            Arrays.sort(nums);
            int count = 0;
            for(int i = 0;i<nums.length-3;i++)
                for(int j = nums.length-1;j> i+2;j--)
                    for(int l = i+1 ; l<j ; l++)
                        for(int r = j-1;r>l;r--){
                            if(i==l)
                                continue;
                            if(nums[i]* nums[r] == nums[l]*nums[j] || nums[i]*nums[j] == nums[l]* nums[r])
                                count++;
                            else if(nums[i]* nums[r] < nums[l]*nums[j])
                                i++;

                        }
            return count*8;
        }

        public static int tupleSameProductIII(int[] nums){
            Arrays.sort(nums);
            int count = 0;
            HashMap<Integer,Integer> map = new HashMap<>();
            for(int i = 0;i<nums.length-2;i++)
                for(int j = nums.length-1;j>i;j--)
                    for(int k = i+1;k<j;k++){
                        if( (nums[i]*nums[j]) % nums[k] == 0){
                            if(map.get(nums[k])!=null &&map.get(nums[k]) ==((nums[i]*nums[j]) / nums[k])){
                                if(nums[i]!=map.get(nums[k]) && nums[j]!=map.get(nums[k]) && nums[k] != map.get(nums[k]) )
                                    count++;
                                continue;
                            }
                            if((nums[i]*nums[j]) / nums[k] != nums[k])
                                map.put((nums[i]*nums[j]) / nums[k],nums[k]);
                        }
                        else if( (nums[i]*nums[k]) % nums[j] == 0){
                            if(map.get(nums[j])!=null && map.get(nums[j]) ==((nums[i]*nums[k]) / nums[j])){
                                if(nums[i]!=map.get(nums[j]) && nums[j]!=map.get(nums[j]) && nums[k] != map.get(nums[j]))
                                    count++;
                                continue;
                            }
                            if((nums[i]*nums[k]) / nums[j] != nums[j])
                                map.put((nums[i]*nums[k]) / nums[j],nums[j]);
                        }
                    }
            return count*8;
        }

        public static int tupleSameProductIV(int[] nums){
            HashMap<Integer,Integer> map = new HashMap<>();
            for(int i = 0;i < nums.length-1;i++)
                for(int j  =i+1;j<nums.length;j++){
                    map.put(nums[i]*nums[j],map.get(nums[i]*nums[j])==null? 1 : map.get(nums[i]*nums[j])+1);
                }
            int res = 0;
            Set<Integer> keys = map.keySet();
            int n = 0;
            for(Integer key : keys){
                if(map.get(key)<2)
                    continue;
                n = map.get(key);
                res += (n*(n-1)) / 2;
            }
            return res*8;
        }
    }



    //end

    public static class FindCircleNum{
        static int count  =0;
        public static int findCircleNum(int[][] isConnected) {
            int length = isConnected.length;
            boolean[] visited = new boolean[length];
            for(int i = 0;i < length;i++){
                if(!visited[i]){
                    find(isConnected,visited,length,i);
                    count++;
                }
            }
            return count;
        }
        public static void find(int[][] isConnected,boolean[] visited,int length,int i){
            for(int j = 0;j < length;j++){
                if(isConnected[i][j]==1 && !visited[j]){
                    visited[j] = true;
                    find(isConnected,visited,length,j);
                }
            }
        }
    }//547 9ms


    //思路一  过于缓慢
    public static class CalcEquation{
        //顶点域
        static class Graph{
            String data;//顶点域   存被除数
            GraphNode firstarc;//指向边节点
            Graph next;//指向下一个顶点域
        }
        static class GraphNode{
            String graphname;//被除数的值
            String adjvex;//临接点域  除数
            double res; //  商
            GraphNode next;//指向下一个临接点域
        }
        static HashSet<String> path = new HashSet<>();

        public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
                Graph graph = new Graph();
                int i = 0;
                GraphNode tnode= new GraphNode();
                //建立图
                for(List<String> equation:equations){
                    String s1 = equation.get(0);
                    String s2 = equation.get(1);

                    GraphNode node = new GraphNode();
                    node.graphname = s1;
                    node.adjvex = s2;
                    node.res = values[i++];
                    findSet(graph, s1, node);


                    node = new GraphNode();
                    node.graphname = s2;
                    node.adjvex = s1;
                    node.res = 1.0d / values[i-1];
                    findSet(graph,s2,node);

                }
                double[] res = new double[queries.size()];

                for(i = 0;i<queries.size();i++){
                    path = new HashSet<>();
                    path.add(queries.get(i).get(0));
                    res[i] = findRes(graph,queries.get(i).get(0),queries.get(i).get(1));
                }
                return res;
        }

        private static void findSet(Graph graph, String s1, GraphNode node) {
            GraphNode tnode;
            Graph p = graph;
            Graph g = findGraph(graph, s1);
            if(g==null){
                g = new Graph();
                g.data = s1;
                g.firstarc = node;
                while (p.next!=null)
                    p = p.next;
                p.next = g;
            }else{
                tnode = g.firstarc;
                if(tnode==null){
                    g.firstarc = node;
                }else{
                    while (tnode.next!=null)
                        tnode = tnode.next;
                    tnode.next = node;
                }
            }

            p = graph;
            HashSet<String> set = new HashSet<>();
            while (p!=null){
                GraphNode node1 = p.firstarc;
                while (node1!=null){
                    if(node1.adjvex.equals(node.graphname) && !set.contains(node.adjvex) ){
                        tnode = new GraphNode();
                        tnode.graphname = node1.graphname;
                        tnode.adjvex = node.adjvex;
                        tnode.res = node1.res * node.res;
                        tnode.next = node1.next;
                        node1.next = tnode;
                    }
                    set.add(node1.adjvex);
                    node1 = node1.next;
                }
                p = p.next;
            }
        }

        public static Double findRes(Graph g, String s1, String s2){

            if(findGraph(g,s1)==null || findGraph(g,s2)==null)
                return -1d;

            if(s1.equals(s2))
                return 1d;

            Graph p = g;
            while ( p.next!=null && !s1.equals(p.data) )
                    p = p.next;
            if( s1.equals(p.data) ){
                GraphNode node = p.firstarc;
                if(node!=null && !s2.equals(node.adjvex)){
                    while (node.next!=null){
                        if(s2.equals(node.next.adjvex))
                            return node.next.res;
                        node = node.next;
                    }
                    //此时找到了 s1  到没从s1的记录里面找到 s2 node.next=null
                    //通过传递性  寻找res
                    Double res =  find(g,p,s1,s2);
                    //如果不为空，则说明可以传递过来，如果还是为空，就说明无法通过传递得到  也就是求不出来
                    if(res!=null){
                        GraphNode node1 = new GraphNode();
                        node1.adjvex = s2;
                        node1.res = res;
                        //头插法
                        node1.next = p.firstarc;
                        p.firstarc = node1;
                        return res;
                    }
                }else if(node==null)
                    return -1d;
                else if(s2.equals(node.adjvex))
                    return node.res;
            }
            return -1d;
        }

        /**
         *
         * @param g 图
         * @param p 图  包含s1值的 图的头节点
         * @param s1  被除数（已经找到）
         * @param s2  除数 （暂时未找到）
         * @return
         */
        public static Double find(Graph g,Graph p,String s1,String s2){

            GraphNode node = p.firstarc;
            while (node!=null){
                if(!node.adjvex.equals(s1)) {
                    Graph graph = findGraph(g, node.adjvex);
                    if (graph != null && !path.contains(node.adjvex) ) {

                        Double res = findRes(g, node.adjvex, s2);
                        if (res != null)
                            return node.res * res;
                    }
                    node = node.next;
                }
            }
            return null;
        }

        /**
         *
         * @param g  全图
         * @param s1  索要寻找的头节点
         * @return
         */
        public static Graph findGraph(Graph g, String s1){
            Graph p = g;
            while (p!=null && !s1.equals(p.data))
                p = p.next;
            return p==null?null:p;
        }

    }//399

    public static class CalcEquationII{
        static class Node{
            String data;//保存自己
            Node parent;//保存父亲节点   被除数
            List<Node> children = new ArrayList<>();//保存孩子节点  除数

            List<Double> answers = new ArrayList<>();//保存到相应孩子的答案
        }

        public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            double[] res = new double[queries.size()];

            Node node = new Node();

            for(int i = 0;i < equations.size();i++){
                String s1 = equations.get(i).get(0);
                String s2 = equations.get(i).get(1);
                createGraph(node,s1,s2,values[i]);
            }
            for(int i = 0;i < queries.size();i++){
                String s1 = queries.get(i).get(0);
                String s2 = queries.get(i).get(1);
                double answer = findAnswer(node, s1, s2);
                if(answer==-1d)
                    res[i] = 1.0d / findAnswer(node,s2,s1);
                else
                    res[i] = answer;
            }
            return res;
        }

        public static double findAnswer(Node node,String s1,String s2){
            node = find(node,s1);
            if(node==null)
                return -1d;
            if(s1.equals(s2))
                return 1d;
            return findRes(node, s2);
        }

        public static void createGraph(Node node,String s1,String s2,double res){
            Node endnode;
            Node node2 = find(node, s2);
            if(node2==null){
                endnode = new Node();
                endnode.data = s2;
            }else
                endnode = node2;


            Node node1 = find(node, s1);
            //s1是第一次出现
            if(node1==null){

                node1 = new Node();
                node1.data = s1;

                node.children.add(node1);//根节点的孩子
//
//                return;
            }


            //s1不是第一次出现    递归将  endnode节点放在各个祖先的孩子节点上
            connect(node1,endnode,res);

        }

        /**
         * 递归将孩子连接到每个  祖先上
         * @param root
         * @param child
         */
        public static void connect(Node root,Node child,double res){
            if(root==null||child==null)
                return;
            Node copy = new Node();

            copy.data = child.data;
            copy.parent = root;
            copy.children = child.children;
            copy.answers = child.answers;

            root.children.add(copy);
            root.answers.add(res);
            if(root.parent!=null)
                connect(root.parent,child,res*root.parent.answers.get(root.parent.children.indexOf(root)));

            List<Node> children = child.children;
            if(children!=null)
                for(int i = 0;i < children.size();i++){
                    root.children.add(children.get(i));
                    root.answers.add(res*child.answers.get(i));
                }
//                for(Node connectChild : children){
//                    root.children.add(connectChild);
//                    root.answers.add()
//                    connect(root.parent,connectChild);
//                }

        }

        //遍历 根node   找被除数   找不到  返回空
        public static Node find(Node node,String s1){
            if( s1.equals(node.data) )
                return node;
            List<Node> children = node.children;
            Node node1 = null;
            for(Node child : children){
                node1 = find(child,s1);
                if(node1!=null)
                    return node1;
            }
            return null;
        }


        /**
         * 根据  s1节点  寻找是否有 s2子节点
         * @param node
         * @param s2
         * @return
         */
        public static double findRes(Node node,String s2){
            List<Node> children = node.children;
            for(int i =  0; i < children.size();i++)
                if( s2.equals(children.get(i).data) )
                    return node.answers.get(i);
                return -1d;
        }

    }//399

    public static class CalcEquationIII{
        static class UnionFind {
            int id;
            int[] parents;
            double[] weights;

            public UnionFind(int n){
                parents = new int[n];
                weights = new double[n];
                //初始化每个节点的父亲都是自己
                for(int i = 0;i < n;i++){
                    parents[i] = i;
                    weights[i] = 1.0d;
                }
            }

            //将要合并的两棵树的高度最多为2
            public void union(int id1,int id2,double res){
                int parent1 = find(id1);
                int parent2 = find(id2);
                if(parent1==parent2)
                    return;
                else{
                    parents[parent1] = parent2;
                    weights[parent1] = weights[id2]*res / weights[id1];
                }
            }

            public double isConnect(int id1,int id2){
                int parent1 = find(id1);
                int parent2 = find(id2);
                if(parent1==parent2)
                    return weights[id1] / weights[id2];
                return -1.0d;
            }

            private int find(int id){
                //路径压缩    细节
                if(id!=parents[id]){
                    int origin = parents[id];
                    parents[id] = find(parents[id]);
                    weights[id] = weights[id] * weights[origin];
                }
                //这里不能写 return id!!!
                return parents[id];
            }

        }
        public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

            int equationsSize = equations.size();
            UnionFind unionfind = new UnionFind(equationsSize*2);
            int id = 0;
            HashMap<String,Integer> map = new HashMap<>();
            for(int i = 0;i < equationsSize;i++){
                String s1 = equations.get(i).get(0);
                String s2 = equations.get(i).get(1);
                if(!map.containsKey(s1)){
                    map.put(s1,id);
                    id++;
                }
                if(!map.containsKey(s2)){
                    map.put(s2,id);
                    id++;
                }
                unionfind.union(map.get(s1),map.get(s2),values[i]);
            }
            int queriesSize = queries.size();
            double[] res = new double[queriesSize];
            for(int i = 0; i < queriesSize;i++){
                String s1 = queries.get(i).get(0);
                String s2 = queries.get(i).get(1);
                Integer id1 = map.get(s1);
                Integer id2 = map.get(s2);
                if(id1==null || id2==null)
                    res[i] = -1.0d;
                else{
                    res[i] = unionfind.isConnect(id1,id2);
                }
            }
            return res;
        }
    }//399 路劲压缩   1ms  99%

    public static class CalcEquationIV{
        static HashSet<Integer> set;
        public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            int equationsSize = equations.size();
            double[][] vals = new double[2*equationsSize][equationsSize*2];
            int id = 0;
            HashMap<String,Integer> map = new HashMap<>();
            Integer x,y;
            for(int i = 0;i<equationsSize;i++) {
                String s1 = equations.get(i).get(0);
                String s2 = equations.get(i).get(1);
                if(!map.containsKey(s1)){
                    map.put(s1,id);
                    id++;
                }
                if(!map.containsKey(s2)){
                    map.put(s2,id);
                    id++;
                }
                x = map.get(s1);
                y = map.get(s2);
                vals[x][y] = values[i];//     x/y
                vals[y][x] = 1.0d/values[i];
            }

            int queriesSize = queries.size();
            double[] res = new double[queriesSize];
            for(int i = 0;i < queriesSize;i++){
                String s1 = queries.get(i).get(0);
                String s2 = queries.get(i).get(1);
                x = map.get(s1);
                y = map.get(s2);
                if(x==null||y==null)
                    res[i] = -1.0d;
                else if(x==y)
                    res[i] = 1.0d;
                else{
                    set = new HashSet<>();
                    if(x < y)
                        res[i] = find(vals,x,y);
                    else
                        res[i] = 1.0d/find(vals,y,x);
                }
            }
            return res;
        }
        public static double find(double[][] vals,int x,int y){
            if(vals[x][y]!=0)
                return vals[x][y];
            for(int i = 0;i< vals.length;i++){
                if(i!=x&&i!=y&&vals[x][i]!=0&&!set.contains(i)){
                    set.add(i);
                    double t =  find(vals,i,y);
                    if(t!=-1.0d)
                        return t*vals[x][i];
//                    set.remove(i);
                }
            }
            return -1.0d;
        }

    }//399 二维矩阵解法  1ms  99%

    public static class RemoveInvalidParentheses{

        public List<String> res = new ArrayList<>();
        public List<Character> seqr = new ArrayList<>();
        public Stack<Character> stack = new Stack<>();
        public List<String> removeInvalidParentheses(String s) {
            if(s.isEmpty()||"".equals(s))
                return res;
            helper(s);
            return res;
        }

        public void helper(String s){
            if(s.length()==0)
                return;
            char[] cs = s.toCharArray();
            for(char c:cs){
                if(c=='('){
                    stack.push(c);
                    seqr.add(c);
                }else if(c==')'){
                    if(stack.peek()=='('){
                        stack.pop();
                        seqr.add(c);
                    }
                }
            }
        }

    }//301  删除无效括号  未完待续


    public static class CountDigitOne{
        public static int countDigitOne(int n) {
            int res = 0;
            for(int i = 0;i <= n;i++)
                res += countOne(i);
            return res;
        }
        public static int countOne(int n){
            char[] cs = (n+"").toCharArray();
            int count = 0;
            for(char c : cs)
                if(c=='1')
                    count++;
            return count;
        }
    }//未完待续


}
