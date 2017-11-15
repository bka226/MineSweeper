package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.Timer;


public class Board extends JFrame  {

    public Board(){
        super ("Boombs Specifications");
        displayBoard(this);

    }
     private void displayBoard( Board board){
        board.setSize(530, 400);
        board.getContentPane().setBackground(Color.LIGHT_GRAY);
        board.setResizable(false);

        board.setVisible(true);

     }

    public static void main(String[] args)
    {

        new Board(); // Call the function

    }

}