package com.satreci.battleShipGame;

public class User {
    public void attack(Board userBoard, String input, char[][] userHiddenBoard) {
        int row = Character.getNumericValue(input.charAt(0)) - 10;
        int column = Character.getNumericValue(input.charAt(1));

        if (userBoard.getBoard()[row][column] != '0') {
            userBoard.hit(row, column);
            System.out.println(">>유저 공격 성공");
        } else {
            System.out.println(">>유저 공격 실패");
        }
        userHiddenBoard[row][column] = userBoard.getBoard()[row][column];
    }
}
