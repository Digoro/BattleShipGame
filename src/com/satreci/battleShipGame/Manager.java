package com.satreci.battleShipGame;

import java.util.Scanner;

public class Manager {
    public static final int WIDTH = 10, HEIGHT = 10, NUMBEROFSHIPS = 6;
    private static char[][] userHiddenBoard, AIHiddenBoard;
    private boolean endOfGame;
    private int cnt = 0;
    Scanner s = new Scanner(System.in);
    AI ai = new AI();
    User user = new User();

    public static void main(String args[]) {
        new Manager();
    }

    public Manager() {
        System.out.println("배틀쉽게임을 시작합니다");
        Board userBoard = new Board(WIDTH, HEIGHT, NUMBEROFSHIPS);
        Board AIBoard = new Board(WIDTH, HEIGHT, NUMBEROFSHIPS);
        generateHiddenBoard();

        while (!endOfGame) {
            displayHiddenBoard();
            boolean wrong = true;
            String input = null;

            while (wrong) {
                System.out.print("공격[A-Z][0-9]: ");
                input = s.nextLine();
                if (input.length() == 2 && input.charAt(0) >= 'a' && input.charAt(0) <= ('a' + this.WIDTH) && input.charAt(1) >= '0' && input.charAt(1) <= '9') {
                    wrong = false;
                }
                if (wrong)
                    System.out.println("잘못된 입력입니다.");
            }
            user.attack(userBoard, input, userHiddenBoard);
            ai.attack(AIBoard, cnt, AIHiddenBoard);

            isGameOver(userBoard, AIBoard);
            cnt++;
        }
        System.out.println("유저 보드");
        userBoard.displayBoard();
        System.out.println("AI 보드");
        AIBoard.displayBoard();
    }

    private void isGameOver(Board userBoard, Board AIBoard) {
        if (userBoard.getShipLifes() == 0) {
            endOfGame = true;
            System.out.println("당신이 이겼습니다.");
        } else if (AIBoard.getShipLifes() == 0) {
            endOfGame = true;
            System.out.println("AI가 이겼습니다.");
        }
    }


    private void displayHiddenBoard() {
        System.out.print("\n  ");
        for (int i = 0; i < userHiddenBoard.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < userHiddenBoard.length; i++) {
            System.out.print(Character.toString((char) ('A' + i)) + " ");
            for (int j = 0; j < userHiddenBoard[i].length; j++) {
                System.out.print(userHiddenBoard[i][j] + " ");
            }
            System.out.println();
        }

        System.out.print("  ");
        for (int i = 0; i < AIHiddenBoard.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < AIHiddenBoard.length; i++) {
            System.out.print(Character.toString((char) ('A' + i)) + " ");
            for (int j = 0; j < AIHiddenBoard[i].length; j++) {
                System.out.print(AIHiddenBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    private void generateHiddenBoard() {
        userHiddenBoard = new char[this.WIDTH][this.HEIGHT];
        AIHiddenBoard = new char[this.WIDTH][this.HEIGHT];
        for (int i = 0; i < userHiddenBoard.length; i++) {
            for (int j = 0; j < userHiddenBoard[i].length; j++) {
                userHiddenBoard[i][j] = '-';
                AIHiddenBoard[i][j] = '-';
            }
        }
    }
}
