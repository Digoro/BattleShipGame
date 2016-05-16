package com.satreci.battleShipGame;

public class Board {
    private int columns;
    private int rows;
    private int numberOfShips;
    private int shipLife;
    private static char[][] board;
    public final Ship[] myShips = new Ship[6];

    public Board(int width, int height, int numberOfShips) {
        this.columns = width;
        this.rows = height;
        this.numberOfShips = numberOfShips;
        generateBoard();
        generateShipPositions();
    }

    public void displayBoard() {
        System.out.print("    ");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            System.out.print(Character.toString((char) ('A' + i)) + "   ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void generateShipPositions() {
        for (int i = 0; i < this.numberOfShips; i++) {
            int x = generateX();
            int y = generateY();
            int direction = generateDirection();
            int length;
            if (i == 0 || i == 1 || i == 2) {
                length = 2;
            } else if (i == 3 || i == 4) {
                length = 3;
            } else {
                length = 4;
            }

            if (isEmpty(x, y, direction, length)) {
                this.shipLife += length;
                Ship ship = new Ship(x, y, direction, length, (char) ('A' + i));
                myShips[i] = ship;
            } else {
                i--;
            }
        }

    }

    public static void setDirectionShips(int x, int y, int direction, int length, char c) {
        for (int i = 0; i < length; i++) {
            if (direction == 1) {
                board[x - i][y] = c;
            } else if (direction == 2) {
                board[x][y + i] = c;
            } else if (direction == 3) {
                board[x + i][y] = c;
            } else if (direction == 4) {
                board[x][y - i] = c;
            }
        }
    }

    private boolean isEmpty(int x, int y, int direction, int length) {
        try {
            for (int i = 0; i < length; i++) {
                if (direction == 1) {
                    if (board[x - i][y] != '0') {
                        return false;
                    }
                } else if (direction == 2) {
                    if (board[x][y + i] != '0') {
                        return false;
                    }
                } else if (direction == 3) {
                    if (board[x + i][y] != '0') {
                        return false;
                    }
                } else if (direction == 4) {
                    if (board[x][y - i] != '0') {
                        return false;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    private int generateDirection() {
        return (int) (Math.random() * 4) + 1;
    }

    private int generateX() {
        return (int) (Math.random() * this.columns);
    }

    private int generateY() {
        return (int) (Math.random() * this.rows);
    }

    private void generateBoard() {
        board = new char[this.columns][this.rows];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '0';
            }
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public int getShipLifes() {
        return this.shipLife;
    }

    public void hit(int row, int column) {
        if (this.board[row][column] == 'A') {
            myShips[0].attacked();
        } else if (this.board[row][column] == 'B') {
            myShips[1].attacked();
        } else if (this.board[row][column] == 'C') {
            myShips[2].attacked();
        } else if (this.board[row][column] == 'D') {
            myShips[3].attacked();
        } else if (this.board[row][column] == 'E') {
            myShips[4].attacked();
        } else if (this.board[row][column] == 'F') {
            myShips[5].attacked();
        }
        this.shipLife--;
    }
}
