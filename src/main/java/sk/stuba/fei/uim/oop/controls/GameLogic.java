package sk.stuba.fei.uim.oop.controls;

import lombok.Getter;
import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.board.State;
import sk.stuba.fei.uim.oop.board.Tile;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;

public class GameLogic extends UniversalAdapter {

    public static final int INITIAL_BOARD_SIZE = 6;
    private JFrame mainGame;
    private Board currentBoard;
    @Getter
    private JLabel label;
    @Getter
    private JLabel boardSizeLabel;
    private int currentBoardSize;

    public GameLogic(JFrame mainGame) {
        this.mainGame = mainGame;
        this.currentBoardSize = INITIAL_BOARD_SIZE;
        this.initializeNewBoard(this.currentBoardSize);
        this.mainGame.add(this.currentBoard);
        this.label = new JLabel();
        this.boardSizeLabel = new JLabel();
        this.updateNameLabel();
        this.updateBoardSizeLabel();
    }

    private void updateNameLabel() {
        this.label.setText("PLAYER: " + State.BLACK + " is PLAYING");
        this.mainGame.revalidate();
        this.mainGame.repaint();
    }

    private void updateBoardSizeLabel() {
        this.boardSizeLabel.setText("CURRENT BOARD SIZE: " + this.currentBoardSize);
        this.mainGame.revalidate();
        this.mainGame.repaint();
    }

    private void gameRestart() {
        this.mainGame.remove(this.currentBoard);
        this.initializeNewBoard(this.currentBoardSize);
        this.mainGame.add(this.currentBoard);
        this.updateNameLabel();
    }

    private void initializeNewBoard(int dimension) {
        this.currentBoard = new Board(dimension);
        this.currentBoard.addMouseMotionListener(this);
        this.currentBoard.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.gameRestart();
        this.mainGame.revalidate();
        this.mainGame.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Component current = this.currentBoard.getComponentAt(e.getX(), e.getY());
        if (!(current instanceof Tile)) {
            return;
        }
        ((Tile) current).setHighlight(true);
        this.currentBoard.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Component current = this.currentBoard.getComponentAt(e.getX(), e.getY());
        if (!(current instanceof Tile)) {
            return;
        }
        if (((Tile) current).getState().equals(State.EMPTY) && ((Tile) current).isPlayable()) {
            this.currentBoard.findTile((Tile) current, State.BLACK);
            this.swapPlayer();
        }
    }

    private void swapPlayer() {
        if (!this.checkWin()) {
            this.simulateRandomPlay();
            this.checkWin();
            ArrayList<Tile> playable = this.currentBoard.checkPlayable(State.BLACK);
            if (playable.size() == 0) {
                this.swapPlayer();
            }
        }
    }

    private void simulateRandomPlay() {
        ArrayList<Tile> playable = this.currentBoard.checkPlayable(State.WHITE);
        if (playable.size() > 0) {
            playable.sort(Comparator.comparingInt(Tile::getNumberOfCaptures).reversed());
            this.currentBoard.findTile(playable.get(0), State.WHITE);
        }
    }

    private boolean checkWin() {
        ArrayList<Tile> playableBlack = this.currentBoard.checkPlayable(State.BLACK);
        ArrayList<Tile> playableWhite = this.currentBoard.checkPlayable(State.WHITE);
        if (playableBlack.size() == 0 && playableWhite.size() == 0) {
            int black = this.currentBoard.count(State.BLACK);
            int white = this.currentBoard.count(State.WHITE);
            if (black > white) {
                this.label.setText(State.BLACK + " WINS! Black: " + black + ", White: " + white);
            } else if (black < white) {
                this.label.setText(State.WHITE + " WINS! Black: " + black + ", White: " + white);
            }  else  {
                this.label.setText("Its TIE!");
            }
        }
        return playableBlack.size() == 0 && playableWhite.size() == 0;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.currentBoardSize = ((JSlider) e.getSource()).getValue();
        this.updateBoardSizeLabel();
        this.gameRestart();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                this.gameRestart();
                break;
            case KeyEvent.VK_ESCAPE:
                this.mainGame.dispose();
        }
    }

}
