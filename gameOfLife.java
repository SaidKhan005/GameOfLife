//Dependancies: Picture.jave
//compilation:  javac gameOfLife.java
//Running on cmd: java gameOfLife (int number if itterations, intial pattern c/r/j/d)


import java.awt.Color;
import java.util.Random;

class Sim134 {
    private char var;
    int columns;
    int rows;
    Random rand = new Random();
    int randomValue;
    int[][] board;
    private int magnification;
    private Picture pic;

    public Sim134(char var1) {
        this.var = var1;
        this.columns = 80;
        this.rows = 80;
        this.magnification = 10;
        this.board = new int[this.columns][this.rows];
        this.pic = new Picture(80 * this.magnification, 80 * this.magnification);
        this.init(var1);
    }

    void init(char var1) {
        int var3;
        if (var1 != 'r' && var1 != 'R') {
            int var4;
            int[][] var5;
            if (var1 == 'j' || var1 == 'J') {
                var5 = new int[10][10];
                this.initJamBoard(var5);

                for(var3 = 0; var3 < 10; ++var3) {
                    for(var4 = 0; var4 < 10; ++var4) {
                        this.board[var3][var4] = var5[var3][var4];
                    }
                }
            } else if (var1 == 'd' || var1 == 'D') {
                var5 = new int[20][20];
                this.initDartGlider(var5);

                for(var3 = 0; var3 < 20; ++var3) {
                    for(var4 = 0; var4 < 20; ++var4) {
                        this.board[var3][var4] = var5[var3][var4];
                    }
                }
            } else if (var1 == 'c' || var1 == 'C') {
                var5 = new int[80][80];
                this.initConwayPattern(var5);

                for(var3 = 0; var3 < 80; ++var3) {
                    for(var4 = 0; var4 < 80; ++var4) {
                        this.board[var3][var4] = var5[var3][var4];
                    }
                }
            }
        } else {
            for(int var2 = 1; var2 < this.columns - 1; ++var2) {
                for(var3 = 1; var3 < this.rows - 1; ++var3) {
                    this.randomValue = this.rand.nextInt(2);
                    this.board[var2][var3] = this.randomValue;
                }
            }
        }

    }

    private void initConwayPattern(int[][] var1) {
        for(int var2 = 0; var2 < 80; ++var2) {
            for(int var3 = 0; var3 < 80; ++var3) {
                var1[var2][var3] = 0;
            }
        }

        this.setPattern(var1, new int[][]{{1, 1, 1}, {1, 0, 0}, {1, 0, 0}, {1, 0, 0}, {1, 1, 1}}, 1, 1);
        this.setPattern(var1, new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 0, 1}, {1, 0, 1}, {1, 1, 1}}, 1, 6);
        this.setPattern(var1, new int[][]{{1, 0, 1}, {1, 1, 1}, {1, 1, 1}, {1, 0, 1}, {1, 0, 1}}, 1, 11);
        this.setPattern(var1, new int[][]{{1, 0, 0, 0, 1}, {1, 0, 0, 0, 1}, {1, 0, 1, 0, 1}, {1, 1, 0, 1, 1}, {1, 0, 0, 0, 1}}, 1, 16);
        this.setPattern(var1, new int[][]{{0, 1, 0}, {1, 0, 1}, {1, 1, 1}, {1, 0, 1}, {1, 0, 1}}, 1, 22);
        this.setPattern(var1, new int[][]{{1, 0, 1}, {1, 0, 1}, {0, 1, 0}, {0, 1, 0}, {0, 1, 0}}, 1, 27);
        this.setPattern(var1, new int[][]{{1, 1, 1}, {1, 0, 0}, {1, 1, 1}, {0, 0, 1}, {1, 1, 1}}, 1, 32);
        this.setPattern(var1, new int[][]{{0, 1, 1, 1}, {1, 0, 0, 0}, {1, 0, 1, 1}, {1, 0, 0, 1}, {0, 1, 1, 1}}, 8, 1);
        this.setPattern(var1, new int[][]{{0, 1, 0}, {1, 0, 1}, {1, 1, 1}, {1, 0, 1}, {1, 0, 1}}, 8, 6);
        this.setPattern(var1, new int[][]{{1, 0, 0, 0, 1}, {1, 1, 0, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 1}}, 8, 11);
        this.setPattern(var1, new int[][]{{1, 1, 1}, {1, 0, 0}, {1, 1, 1}, {1, 0, 0}, {1, 1, 1}}, 8, 17);
        this.setPattern(var1, new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 0, 1}, {1, 0, 1}, {1, 1, 1}}, 8, 22);
        this.setPattern(var1, new int[][]{{1, 1, 1}, {1, 0, 0}, {1, 1, 1}, {1, 0, 0}, {1, 0, 0}}, 8, 27);
        this.setPattern(var1, new int[][]{{1, 0, 0}, {1, 0, 0}, {1, 0, 0}, {1, 0, 0}, {1, 1, 1}}, 8, 32);
        this.setPattern(var1, new int[][]{{1, 1, 1}, {0, 1, 0}, {0, 1, 0}, {0, 1, 0}, {1, 1, 1}}, 8, 37);
        this.setPattern(var1, new int[][]{{1, 1, 1}, {1, 0, 0}, {1, 1, 1}, {1, 0, 0}, {1, 0, 0}}, 8, 41);
        this.setPattern(var1, new int[][]{{1, 1, 1}, {1, 0, 0}, {1, 1, 1}, {1, 0, 0}, {1, 1, 1}}, 8, 46);
    }

    private void setPattern(int[][] var1, int[][] var2, int var3, int var4) {
        for(int var5 = 0; var5 < var2.length; ++var5) {
            for(int var6 = 0; var6 < var2[0].length; ++var6) {
                var1[var3 + var5][var4 + var6] = var2[var5][var6];
            }
        }

    }

    private int[][] initJamBoard(int[][] var1) {
        for(int var2 = 0; var2 < 10; ++var2) {
            for(int var3 = 0; var3 < 10; ++var3) {
                var1[var2][var3] = 0;
            }
        }

        var1[1][4] = 1;
        var1[1][5] = 1;
        var1[2][3] = 1;
        var1[2][6] = 1;
        var1[3][1] = 1;
        var1[3][4] = 1;
        var1[3][6] = 1;
        var1[4][1] = 1;
        var1[4][5] = 1;
        var1[5][1] = 1;
        var1[6][4] = 1;
        var1[7][2] = 1;
        var1[7][3] = 1;
        return var1;
    }

    private int[][] initDartGlider(int[][] var1) {
        for(int var2 = 0; var2 < 20; ++var2) {
            for(int var3 = 0; var3 < 20; ++var3) {
                var1[var2][var3] = 0;
            }
        }

        var1[1][8] = 1;
        var1[2][7] = 1;
        var1[2][9] = 1;
        var1[3][6] = 1;
        var1[3][10] = 1;
        var1[4][7] = 1;
        var1[4][8] = 1;
        var1[4][9] = 1;
        var1[6][5] = 1;
        var1[6][6] = 1;
        var1[6][10] = 1;
        var1[6][11] = 1;
        var1[7][3] = 1;
        var1[7][7] = 1;
        var1[7][9] = 1;
        var1[7][13] = 1;
        var1[8][2] = 1;
        var1[8][3] = 1;
        var1[8][7] = 1;
        var1[8][9] = 1;
        var1[8][13] = 1;
        var1[8][14] = 1;
        var1[9][1] = 1;
        var1[9][7] = 1;
        var1[9][9] = 1;
        var1[9][15] = 1;
        var1[10][2] = 1;
        var1[10][4] = 1;
        var1[10][5] = 1;
        var1[10][7] = 1;
        var1[10][9] = 1;
        var1[10][11] = 1;
        var1[10][12] = 1;
        var1[10][14] = 1;
        return var1;
    }

    void generate() {
        int[][] var1 = new int[this.columns][this.rows];

        for(int var2 = 0; var2 < this.columns; ++var2) {
            for(int var3 = 0; var3 < this.rows; ++var3) {
                int var4 = 0;

                for(int var5 = -1; var5 <= 1; ++var5) {
                    for(int var6 = -1; var6 <= 1; ++var6) {
                        var4 += this.board[(var2 + var5 + this.columns) % this.columns][(var3 + var6 + this.rows) % this.rows];
                    }
                }

                var4 -= this.board[var2][var3];
                if (this.board[var2][var3] == 1 && var4 < 2) {
                    var1[var2][var3] = 0;
                } else if (this.board[var2][var3] == 1 && var4 > 3) {
                    var1[var2][var3] = 0;
                } else if (this.board[var2][var3] == 0 && var4 == 3) {
                    var1[var2][var3] = 1;
                } else {
                    var1[var2][var3] = this.board[var2][var3];
                }
            }
        }

        this.board = var1;
    }

    public void display() {
        for(int var1 = 0; var1 < this.columns; ++var1) {
            for(int var2 = 0; var2 < this.rows; ++var2) {
                for(int var3 = 0; var3 < this.magnification; ++var3) {
                    for(int var4 = 0; var4 < this.magnification; ++var4) {
                        if (this.board[var1][var2] == 1) {
                            this.pic.set(var2 * this.magnification + var3, var1 * this.magnification + var4, Color.black);
                        } else {
                            this.pic.set(var2 * this.magnification + var3, var1 * this.magnification + var4, Color.white);
                        }
                    }
                }
            }
        }

        this.pic.show();
    }

    public static void main(String[] var0) {
        int var1 = Integer.parseInt(var0[0]);
        char var2 = var0[1].charAt(0);
        Sim134 var3 = new Sim134(var2);
        var3.display();

        try {
            Thread.sleep(3000L);
        } catch (Exception var7) {
        }

        for(int var4 = 0; var4 < var1; ++var4) {
            try {
                Thread.sleep(400L);
            } catch (Exception var6) {
            }

            var3.generate();
            var3.display();
        }

    }
}
