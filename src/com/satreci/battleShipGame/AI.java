package com.satreci.battleShipGame;

public class AI {
    private int[] positions = new int[100];

    public AI() {
        for (int i = 0; i < positions.length; i++) {
            positions[i] = (int) (Math.random() * 100);
            for (int j = 0; j < i; j++) {
                if (positions[i] == positions[j]) {
                    i--;
                    break;
                }
            }
        }
    }

    public int getX(int cnt) {
        int position = positions[cnt];
        int x = (position - (position % 10)) / 10;
        return x;
    }

    public int getY(int cnt) {
        int position = positions[cnt];
        int y = position % 10;
        return y;
    }

    public void attack(Board AIBoard, int cnt, char[][] AIHiddenBoard) {
        int AIx = getX(cnt);
        int AIy = getY(cnt);

        if (AIBoard.getBoard()[AIx][AIy] != '0') {
            AIBoard.hit(AIy, AIy);
            System.out.println(">>AI 공격 성공 (" + AIx + "," + AIy + ")");
        } else {
            System.out.println(">>AI 공격 실패 (" + AIx + "," + AIy + ")");
        }
        AIHiddenBoard[AIx][AIy] = AIBoard.getBoard()[AIx][AIy];
    }
}