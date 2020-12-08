package com.leet;

import java.util.ArrayList;

public class shudumain {
    int[][] array1,array2,array3,array4,array5;
    public void testmain(){

            number_shudu numberShudu1 = new number_shudu();
            number_shudu numberShudu2 = new number_shudu();
            number_shudu numberShudu3 = new number_shudu();
            number_shudu numberShudu4 = new number_shudu();



            init();//初始化整个大数独

            // 左上角 a
            numberShudu1.array = numberShudu1.copy(array1);
            numberShudu1.resolve(numberShudu1.find(numberShudu1.array)[0], numberShudu1.find(numberShudu1.array)[1], numberShudu1.array);
            int o1 = numberShudu1.possible.size();
            ArrayList<int[][]> possible1 = numberShudu1.possible;
            ArrayList<Integer> o2 = new ArrayList<>();
            System.out.println("左上角数独有" + o1 + "种可能");
            // a-a1
            for (int k = 0; k < o1; k++) {
                int[][] p = possible1.get(k);
                here: for (int i = 0; i <= 2; i++) {
                    for (int j = 3; j <= 8; j++) {
                        for (int m = 6; m <= 8; m++) {
                            if (array5[i][j] == p[i + 6][m] || array5[j][i] == p[m][i + 6]) {
                                o2.add(k);
                                break here;
                            }
                        }
                    }
                }
            }
            for (int i = (o2.size() - 1); i > 0; i--) {
                possible1.remove(o2.get(i).intValue());
            }
            o1 = possible1.size();
            System.out.println("减去部分错误可能后有" + o1 + "种可能");

            // 右上角 b
            numberShudu2.array = numberShudu2.copy(array2);
            numberShudu2.resolve(numberShudu2.find(numberShudu2.array)[0], numberShudu2.find(numberShudu2.array)[1], numberShudu2.array);
            int j1 = numberShudu2.possible.size();
            ArrayList<int[][]> possible2 = numberShudu2.possible;
            System.out.println("右上角数独有" + j1 + "种可能");
            // b-b1
            ArrayList<Integer> j2 = new ArrayList<>();
            for (int k = 0; k < j1; k++) {
                int[][] p = possible2.get(k);
                here: for (int i = 0; i <= 2; i++) {
                    for (int j = 0; j <= 5; j++) {
                        for (int m = 0; m <= 2; m++) {
                            if (array5[i][j] == p[i + 6][m] || array5[j + 3][i + 6] == p[m + 6][i]) {
                                j2.add(k);
                                break here;
                            }
                        }
                    }
                }
            }
            for (int i = (j2.size() - 1); i > 0; i--) {
                possible2.remove(j2.get(i).intValue());
            }
            j1 = possible2.size();
            System.out.println("减去部分错误可能后有" + j1 + "种可能");

            // 左下角 c
            numberShudu3.array = numberShudu3.copy(array3);
            numberShudu3.resolve(numberShudu3.find(numberShudu3.array)[0], numberShudu3.find(numberShudu3.array)[1], numberShudu3.array);
            int b1 = numberShudu3.possible.size();
            ArrayList<int[][]> possible3 = numberShudu3.possible;
            System.out.println("左下角数独有" + b1 + "种可能");
            // c-c1
            ArrayList<Integer> b2 = new ArrayList<>();
            for (int k = 0; k < b1; k++) {
                int[][] p = possible3.get(k);
                here: for (int i = 0; i <= 2; i++) {
                    for (int j = 3; j <= 8; j++) {
                        for (int m = 6; m <= 8; m++) {
                            if (array5[i + 6][j] == p[i][m] || array5[j - 3][i] == p[m - 6][i + 6]) {
                                b2.add(k);
                                break here;
                            }
                        }
                    }
                }
            }
            for (int i = (b2.size() - 1); i > 0; i--) {
                possible3.remove(b2.get(i).intValue());
            }
            b1 = possible3.size();
            System.out.println("减去部分错误可能后有" + b1 + "种可能");

            // 右下角 d
            numberShudu4.array = numberShudu4.copy(array4);
            numberShudu4.resolve(numberShudu4.find(numberShudu4.array)[0], numberShudu4.find(numberShudu4.array)[1], numberShudu4.array);
            int k1 = numberShudu4.possible.size();
            ArrayList<int[][]> possible4 = numberShudu4.possible;
            System.out.println("右下角数独有" + k1 + "种可能");
            // d-d1
            ArrayList<Integer> k2 = new ArrayList<>();
            for (int k = 0; k < k1; k++) {
                int[][] p = possible4.get(k);
                here: for (int i = 0; i <= 2; i++) {
                    for (int j = 3; j <= 8; j++) {
                        for (int m = 6; m <= 8; m++) {
                            if (array5[i + 6][j - 3] == p[i][m - 6] || array5[j - 3][i + 6] == p[m - 6][i]) {
                                k2.add(k);
                                break here;
                            }
                        }
                    }
                }
            }
            for (int i = (k2.size() - 1); i > 0; i--) {
                possible4.remove(k2.get(i).intValue());
            }
            k1 = possible4.size();
            System.out.println("减去部分错误可能后有" + k1 + "种可能");

            // (a-a1)*(b-b1)*(c-c1)*(d-d1)
        Judge(numberShudu1, numberShudu2, numberShudu3, numberShudu4, o1, possible1, j1, possible2, b1, possible3, k1, possible4);
    }

    private void Judge(number_shudu numberShudu1, number_shudu numberShudu2, number_shudu numberShudu3, number_shudu numberShudu4, int o1, ArrayList<int[][]> possible1, int j1, ArrayList<int[][]> possible2, int b1, ArrayList<int[][]> possible3, int k1, ArrayList<int[][]> possible4) {
        for (int o = 0; o <= (o1 - 1); o++) {
            for (int j = 0; j <= (j1 - 1); j++) {
                for (int b = 0; b <= (b1 - 1); b++) {
                    for (int k = 0; k <= (k1 - 1); k++) {
                        array5[0][0] = possible1.get(o)[6][6];
                        array5[0][1] = possible1.get(o)[6][7];
                        array5[0][2] = possible1.get(o)[6][8];
                        array5[1][0] = possible1.get(o)[7][6];
                        array5[1][1] = possible1.get(o)[7][7];
                        array5[1][2] = possible1.get(o)[7][8];
                        array5[2][0] = possible1.get(o)[8][6];
                        array5[2][1] = possible1.get(o)[8][7];
                        array5[2][2] = possible1.get(o)[8][8];

                        array5[0][6] = possible2.get(j)[6][0];
                        array5[0][7] = possible2.get(j)[6][1];
                        array5[0][8] = possible2.get(j)[6][2];
                        array5[1][6] = possible2.get(j)[7][0];
                        array5[1][7] = possible2.get(j)[7][1];
                        array5[1][8] = possible2.get(j)[7][2];
                        array5[2][6] = possible2.get(j)[8][0];
                        array5[2][7] = possible2.get(j)[8][1];
                        array5[2][8] = possible2.get(j)[8][2];

                        array5[6][0] = possible3.get(b)[0][6];
                        array5[6][1] = possible3.get(b)[0][7];
                        array5[6][2] = possible3.get(b)[0][8];
                        array5[7][0] = possible3.get(b)[1][6];
                        array5[7][1] = possible3.get(b)[1][7];
                        array5[7][2] = possible3.get(b)[1][8];
                        array5[8][0] = possible3.get(b)[2][6];
                        array5[8][1] = possible3.get(b)[2][7];
                        array5[8][2] = possible3.get(b)[2][8];

                        array5[6][6] = possible4.get(k)[0][0];
                        array5[6][7] = possible4.get(k)[0][1];
                        array5[6][8] = possible4.get(k)[0][2];
                        array5[7][6] = possible4.get(k)[1][0];
                        array5[7][7] = possible4.get(k)[1][1];
                        array5[7][8] = possible4.get(k)[1][2];
                        array5[8][6] = possible4.get(k)[2][0];
                        array5[8][7] = possible4.get(k)[2][1];
                        array5[8][8] = possible4.get(k)[2][2];
                        number_shudu sdn = new number_shudu();
                        sdn.array = array5;
                        sdn.resolve(sdn.find(sdn.array)[0], sdn.find(sdn.array)[1], sdn.array);
                        if (sdn.possible.size() >= 1) {
                            System.out.println("左上角");
                            numberShudu1.printprint(possible1.get(o));
                            System.out.println("右上角");
                            numberShudu2.printprint(possible2.get(j));
                            System.out.println("左下角");
                            numberShudu3.printprint(possible3.get(b));
                            System.out.println("右下角");
                            numberShudu4.printprint(possible4.get(k));
                            for (int i = 0; i < sdn.possible.size(); i++) {
                                System.out.println("中间");
                                sdn.printprint(sdn.possible.get(i));
                            }

                        }
                    }
                }
            }
        }
    }


    public void init(){
             //0即为空白
            //左上角
            array1= new int[][]{{0, 0, 6, 0, 0, 0, 1, 0, 0},
                                {0, 9, 0, 4, 0, 0, 0, 5, 0},
                                {1, 0, 0, 0, 8, 0, 0, 0, 7},
                                {0, 3, 0, 9, 0, 0, 0, 0, 0},
                                {0, 0, 2, 0, 0, 0, 4, 0, 0},
                                {0, 0, 0, 0, 0, 6, 0, 3, 0},
                                {2, 0, 0, 0, 9, 0, 0, 0, 5},
                                {0, 1, 0, 0, 0, 7, 0, 9, 0},
                                {0, 0, 5, 0, 0, 0, 3, 0, 0}};
            //右上角
            array2= new int[][]{{0, 0, 7, 0, 0, 0, 8, 0, 0},
                                {0, 5, 0, 0, 0, 9, 0, 1, 0},
                                {8, 0, 0, 0, 3, 0, 0, 0, 4},
                                {0, 0, 0, 0, 0, 1, 0, 2, 0},
                                {0, 0, 4, 0, 0, 0, 5, 0, 0},
                                {0, 6, 0, 9, 0, 0, 0, 0, 0},
                                {9, 0, 0, 0, 4, 0, 0, 0, 8},
                                {0, 4, 0, 3, 0, 0, 0, 7, 0},
                                {0, 0, 6, 0, 0, 0, 9, 0, 0}};
            //左下角
            array3= new int[][]{{0, 0, 8, 0, 0, 0, 5, 0, 0},
                                {0, 5, 0, 0, 0, 6, 0, 7, 0},
                                {7, 0, 0, 0, 1, 0, 0, 0, 4},
                                {0, 0, 0, 0, 0, 5, 0, 2, 0},
                                {0, 0, 7, 0, 0, 0, 1, 0, 0},
                                {0, 3, 0, 2, 0, 0, 0, 0, 0},
                                {6, 0, 0, 0, 7, 0, 0, 0, 9},
                                {0, 1, 0, 6, 0, 0, 0, 3, 0},
                                {0, 0, 9, 0, 0, 0, 4, 0, 0}};
            //右下角
            array4= new int[][]{{0, 0, 4, 0, 0, 0, 8, 0, 0},
                                {0, 9, 0, 5, 0, 0, 0, 7, 0},
                                {5, 0, 0, 0, 8, 0, 0, 0, 6},
                                {0, 8, 0, 6, 0, 0, 0, 0, 0},
                                {0, 0, 9, 0, 0, 0, 7, 0, 0},
                                {0, 0, 0, 0, 0, 9, 0, 1, 0},
                                {1, 0, 0, 0, 3, 0, 0, 0, 5},
                                {0, 5, 0, 0, 0, 1, 0, 2, 0},
                                {0, 0, 3, 0, 0, 0, 9, 0, 0}};
            //中间
            array5= new int[][]{{0, 0, 5, 0, 0, 0, 9, 0, 0},
                                {0, 9, 0, 0, 0, 0, 0, 4, 0},
                                {3, 0, 0, 0, 4, 0, 0, 0, 6},
                                {0, 0, 0, 6, 0, 4, 0, 0, 0},
                                {0, 0, 2, 0, 0, 0, 4, 0, 0},
                                {0, 0, 0, 2, 0, 5, 0, 0, 0},
                                {5, 0, 0, 0, 9, 0, 0, 0, 4},
                                {0, 7, 0, 0, 0, 0, 0, 9, 0},
                                {0, 0, 4, 0, 0, 0, 5, 0, 0}};
        }

}
