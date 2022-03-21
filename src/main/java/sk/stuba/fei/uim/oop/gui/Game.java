package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.controls.GameLogic;

import javax.swing.*;
import java.awt.*;

public class Game {

    public Game() {
        JFrame frame = new JFrame("Reversi!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,726);
        frame.getContentPane().setBackground(Color.ORANGE);
        frame.setResizable(false);

        GameLogic logic = new GameLogic(frame);

        JPanel sideMenu = new JPanel();
        sideMenu.setBackground(Color.LIGHT_GRAY);
        JButton buttonRestart = new JButton("RESTART");
        buttonRestart.addActionListener(logic);
        buttonRestart.setFocusable(false);

        sideMenu.setLayout(new GridLayout(1, 2));
        sideMenu.add(logic.getLabel());
        sideMenu.add(buttonRestart);
        frame.add(sideMenu, BorderLayout.PAGE_START);

        frame.setVisible(true);
    }
}
