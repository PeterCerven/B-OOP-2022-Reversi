package sk.stuba.fei.uim.oop.board;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {

    @Getter @Setter
    private State state;
    @Setter
    private boolean highlight;
    @Getter @Setter
    private boolean playable;

    public Tile() {
        this.state = State.EMPTY;
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.ORANGE);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.state.equals(State.EMPTY)) {
            if (this.playable) {
                if (this.highlight) {
                    g.setColor(Color.GREEN);
                    this.highlight = false;
                } else {
                    g.setColor(Color.GRAY);
                }
                ((Graphics2D) g).setStroke(new BasicStroke(3));
                g.drawOval((int) (0 + this.getWidth() * 0.05), (int) (0 + this.getHeight() * 0.05),
                        (int) (this.getWidth() * 0.9), (int) (this.getHeight() * 0.9));
                g.setColor(Color.BLACK);
                ((Graphics2D) g).setStroke(new BasicStroke(1));
            }
            return;
        }
        if (this.state.equals(State.WHITE)) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.BLACK);
        }
        g.fillOval((int) (0 + this.getWidth() * 0.05), (int) (0 + this.getHeight() * 0.05),
                (int) (this.getWidth() * 0.9), (int) (this.getHeight() * 0.9));
    }
}
