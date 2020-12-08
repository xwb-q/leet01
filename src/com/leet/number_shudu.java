package com.leet;

import java.util.ArrayList;

public class number_shudu {

    ArrayList<int[][]> possible = new ArrayList<>();
    int[][] array = new int[9][9];

    // 解数独  找到  q 的第e 行  第f列可以填写的数字
    public void resolve(int e, int f, int[][] q) {

        //d会根据maybe的判断结果而被初始化
        int[] d = maybe(e, f, q);

        if (d[0] != -1)// 说明有可能
        {
            for (int g = 0; g < d.length; g++) {
                int[][] xin = copy(q);
                xin[e][f] = d[g];
                int[] m = next(e, f, xin);

                if (m[0] == -1) {
                    // 导出数组
                    if (panduan(xin)) {
                        possible.add(xin);
                    }
                } else {
                    resolve(m[0], m[1], xin);
                }
            }
        }

    }


    //硬拷贝
    public int[][] copy(int[][] q) {
        int[][] p = new int[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                p[i][j] = q[i][j];
            }
        }
        return p;
    }

    //输出答案
    public void printprint(int[][] q) {
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                System.out.print(q[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    //列出格子内可能的值
    public int[] maybe(int i, int j, int[][] array) {
        ArrayList<Integer> a = new ArrayList<>();
        for (int o = 1; o <= 9; o++) {
            a.add(o);
        }
        // 行列
        for (int q = 0; q <= 8; q++) {
            if (a.indexOf(array[q][j]) != -1) {
                a.remove(a.indexOf(array[q][j]));
            }
            if (a.indexOf(array[i][q]) != -1) {
                a.remove(a.indexOf(array[i][q]));
            }
        }

        // 小九宫格
        int ti = i / 3;
        int tj = j / 3;
        for (int g = 0; g < 3; g++) {
            for (int h = 0; h < 3; h++) {
                if (a.indexOf(array[ti * 3 + g][tj * 3 + h]) != -1) {
                    a.remove(a.indexOf(array[ti * 3 + g][tj * 3 + h]));
                }
            }
        }

        int[] b = { -1, -1, };
        if (a.size() != 0) {
            int[] c = new int[a.size()];
            for (int g = 1; g <= a.size(); g++) {
                c[g - 1] = a.get(g - 1);
            }
            return c;
        } else {
            return b;
        }

    }

    //下一个位置
    public int[] next(int a, int b, int[][] arr) {
        int[] c = new int[2];
        c[0] = a;
        c[1] = b;
        if (b < 8) {
            c[1] = b + 1;
        } else {
            c[0] = a + 1;
            c[1] = 0;
        }
        if (c[0] >= 9) {
            c[0] = -1;
            return c;
        } else if (arr[c[0]][c[1]] == 0) {
            return c;
        } else {
            c = next(c[0], c[1], arr);
            return c;
        }
    }

    //找到第一个空位
    public int[] find(int[][] arr) {
        int[] a = { 8, 8 };
        for (int i = 8; i >= 0; i--) {
            for (int j = 8; j >= 0; j--) {
                if (arr[i][j] == 0) {
                    a[0] = i;
                    a[1] = j;
                }
            }
        }
        return a;
    }

    //判断单个位置是否符合规则
    public boolean xjiance(int i, int j, int[][] arr) {
        boolean a = true;
        // 判断该行该列是否有重复数字
        for (int q = 0; q < i; q++) {
            a = (arr[q][j] == arr[i][j] ? false : true);
            if (a == false) {
                return a;
            }
        }
        for (int q = 8; q > i; q--) {
            a = (arr[q][j] == arr[i][j] ? false : true);
            if (a == false) {
                return a;
            }
        }
        for (int q = 0; q < j; q++) {
            a = (arr[i][q] == arr[i][j] ? false : true);
            if (a == false) {
                return a;
            }
        }
        for (int q = 8; q > j; q--) {
            a = (arr[i][q] == arr[i][j] ? false : true);
            if (a == false) {
                return a;
            }
        }
        return a;
    }

    //判断一个结果是否没错
    public boolean panduan(int[][] arr) {
        boolean a = true;
        here: for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                a = xjiance(i, j, arr);
                if (a == false) {
                    break here;
                }
            }
        }
        return a;
    }
}

