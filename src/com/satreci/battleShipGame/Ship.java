package com.satreci.battleShipGame;

public class Ship {

    private char name;
    private int x;
    private int y;
    private int direction;
    private int length;
    private int life;

    public Ship(int x, int y, int direction, int length, char c) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.length = length;
        this.life = length;
        this.name = c;
        Board.setDirectionShips(this.x, this.y, this.direction, this.length, name);
    }

    public void attacked() {
        this.life--;
        if (life == 0) {
        }
    }
}
