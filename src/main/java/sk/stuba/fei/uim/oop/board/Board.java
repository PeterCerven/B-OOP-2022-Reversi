package sk.stuba.fei.uim.oop.board;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Board extends JPanel {

    private Tile[][] board;

    public Board(int dimension) {
        this.initializeBoard(dimension);
        this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        this.setBackground(Color.YELLOW);
        this.checkPlayable(State.BLACK);
    }

    private void initializeBoard(int dimension) {
        this.board = new Tile[dimension][dimension];
        this.setLayout(new GridLayout(dimension, dimension));
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.board[i][j] = new Tile();
                this.add(this.board[i][j]);
            }
        }
        this.board[dimension / 2][dimension / 2].setState(State.BLACK);
        this.board[dimension / 2][dimension / 2 - 1].setState(State.WHITE);
        this.board[dimension / 2 - 1][dimension / 2].setState(State.WHITE);
        this.board[dimension / 2 - 1][dimension / 2 - 1].setState(State.BLACK);
    }

    public ArrayList<Tile> checkPlayable(State state) {
        ArrayList<Tile> playable = new ArrayList<>();
        for (int x = 0; x < this.board.length; x++) {
            for (int y = 0; y < this.board.length; y++) {
                this.board[x][y].setPlayable(false);
                boolean bool = this.checkNeighbours(x, y, state, false);
                if (bool) {
                    playable.add(this.board[x][y]);
                }
            }
        }
        this.repaint();
        return playable;
    }

    private boolean checkNeighbours(int x, int y, State state, boolean placeDown) {
        boolean isPlayable = false;
        if (!this.board[x][y].getState().equals(State.EMPTY)) {
            return false;
        }
        this.board[x][y].setNumberOfCaptures(0);
        for (Direction d : Direction.values()) {
            int checkX = x + d.getX();
            int checkY = y + d.getY();
            if (checkX >= 0 && checkY >= 0 && checkX < this.board.length && checkY < this.board.length) {
                if (this.board[checkX][checkY].getState().equals(State.WHITE.equals(state) ? State.BLACK : State.WHITE)) {
                    for (int dist = 1; dist < this.board.length; dist++) {
                        int distCheckX = x + dist * d.getX();
                        int distCheckY = y + dist * d.getY();
                        if (distCheckX < 0 || distCheckY < 0 || distCheckX >= this.board.length || distCheckY >= this.board.length) {
                            continue;
                        }
                        if (this.board[distCheckX][distCheckY].getState().equals(state)) {
                            this.board[x][y].setPlayable(true);
                            this.board[x][y].setNumberOfCaptures(this.board[x][y].getNumberOfCaptures() + dist - 1);
                            isPlayable = true;
                            if (placeDown) {
                                for (int newDist = 1; newDist <= dist; newDist++) {
                                    int newDistCheckX = x + newDist * d.getX();
                                    int newDistCheckY = y + newDist * d.getY();
                                    this.board[newDistCheckX][newDistCheckY].setState(state);
                                }
                                this.board[x][y].setState(state);
                            }
                        }
                    }
                }
            }
        }
        return isPlayable;
    }

    public void findTile(Tile tile, State state) {
        for (int x = 0; x < this.board.length; x++) {
            for (int y = 0; y < this.board.length; y++) {
                if (Objects.equals(this.board[x][y], tile)) {
                    this.checkNeighbours(x, y, state, true);
                    break;
                }
            }
        }
    }

    public int count(State state) {
        int count = 0;
        for (int x = 0; x < this.board.length; x++) {
            for (int y = 0; y < this.board.length; y++) {
                if (this.board[x][y].getState().equals(state)) {
                    count++;
                }
            }
        }
        return count;
    }
}
