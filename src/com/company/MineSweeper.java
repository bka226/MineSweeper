package com.company;


/* *****************
 Name: Binod Katwal
 Date: 10/6/2017
 Program: 1
 Purpose:  To understand GUI java and create a game of Minesweeper.
 ******************* */

//Library
import com.sun.deploy.panel.ExceptionListDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.swing.JOptionPane;


// Class MineSweeper contains working MineSweeper game
public class MineSweeper extends JFrame implements ActionListener {

    private JMenu game;  // Declaration of variables
    private  JMenuItem New;
    private JMenuItem Exit;
    private JMenu Help;
    private JMenuItem viewHelp;
    private JMenuItem aboutGame;
    private JButton smile;
    private JTextField text,Field, footer;
    private JPanel layout;
    private JLabel text1;
    private Container grid = new Container();
    private int NumberOfMine, savedMine;
    private int width,height,levelOfDifficulty;
    private int widthbutton, rowFinal,heightbutton,ColumnFinal;
    private int startTime =0;
    JPanel panelb = new JPanel(); //added just now
    Timer startTimer;
    private JPanel header;
    JButton[][] buttons= new JButton[heightbutton][widthbutton];
    int [][] counts = new int[heightbutton][widthbutton];
    


    //Full code of the Game
    public MineSweeper()
    {
        super("MineSweeper "); //Display the Title
        displayMenuBar(); // display menu
        displayGUI(1,0,0,0); // Grinds, GUI
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    

    //The function  contains all level of difficulties
    public void displayGUI(int difficulty,  int getValue1,int getValue, int getMines)
    {
        if (difficulty == 1) // first board, beginner
        {
            //board size and mines declared
            width = 400;
            height = 500;
            widthbutton = 5;
            heightbutton = 5;
            NumberOfMine = 7;
        }
        else if (difficulty == 2) // second board, intermediate
        {
            width = 550;
            height = 650;
            widthbutton = 8;
            heightbutton = 8;
            NumberOfMine = 15;
        }
        else if (difficulty == 3) //third board, expert
        {
            width = 600;
            height = 700;
            widthbutton = 10;
            heightbutton = 10;
            NumberOfMine= 30;
        }
        else if (difficulty == 4) {  //fourth board, user can  decide height and weight of their own and play
            width = ( getValue1 * 50); //size of board depend on the user input in the custom field
            height = ( getValue * 25);
            widthbutton = getValue;
            heightbutton= getValue1;
            NumberOfMine = getMines;

        }
        //adding value
        rowFinal = widthbutton;
        ColumnFinal = heightbutton;
        savedMine = NumberOfMine;
        setSize(width, height); // size of the board based on user choices
        setResizable(false); // user can't resize the game board
        buttons = new JButton[heightbutton][widthbutton]; //buttons
        counts = new int[heightbutton][widthbutton]; // counts

        //Dipslay smile image om the header button
        ImageIcon picture = new ImageIcon("/Users/binodkatwal/Desktop/MineSweeper/out/production/MineSweeper/com/smile.jpg");
        smile = new JButton("Smile",picture);
        smile.setSize(70,50);
        //Mine display
        JTextField text = new JTextField(" 000 "); //declared
        text.setEditable(false);
        text.setForeground(Color.red);
        text.setBackground(Color.black); //color of JFrame
        text.setText("0" + NumberOfMine);
        text.addActionListener(this);
        //TIMER TEXT
        JTextField text1 = new JTextField(" 000 "); //declared
        text1.setEditable(false);
        text1.setForeground(Color.red);
        text1.setBackground(Color.BLACK); //color of JFrame
        

        //timer function
        //display timer
        startTimer = new javax.swing.Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text1.setText("00" + startTime);
                startTime++;
                
            }
            
        });

        //GRID OF THE Board
        grid.setLayout(new GridLayout(0,heightbutton)); // grid added center of the board
        for (int x = 0; x < buttons.length; x++)
        {
            for (int y =0; y < buttons[0].length; y++){
                buttons[x][y]= new JButton();
                buttons[x][y].addActionListener(this);
                grid.add(buttons[x][y]);
            }
        }

        grid.setBackground(Color.DARK_GRAY);
        grid.revalidate();
        grid.repaint();
        
        //Create new header panel which lies in the north of the panel
        JPanel header = new JPanel();
        header.setBackground(Color.DARK_GRAY); // about header
        header.setSize(100,400);
        header.add(text,BorderLayout.EAST);
        smile.addActionListener(this); // add listener
        header.add(smile,BorderLayout.CENTER);
        header.add(text1,BorderLayout.WEST);

        header.revalidate();
        header.repaint();
        footer = new JTextField(" Bombs "); // displays at the buttom of the program
        footer.setForeground(Color.RED);
        footer.setBackground(Color.black); // about footer
        footer.setEditable(false);
        footer.revalidate();
        footer.repaint();
        JPanel footer1 = new JPanel(); // footer new panal, added to south of the board
        footer1.setBackground(Color.DARK_GRAY);
        footer1.add(footer, BorderLayout.CENTER);
        getContentPane().removeAll();
        
        
        randomMine();
        getContentPane().add(header,BorderLayout.NORTH);
        getContentPane().add(grid, BorderLayout.CENTER);
        getContentPane().add(footer1, BorderLayout.SOUTH);
        setBackground(Color.GRAY); //color of JFrame

        setVisible(true); //Display layout so we can view
        
        
    }
    
    
    
    
    
    
    
    //Display the Menu bar includes Games, and help options
    
    private void displayMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        //Menu
        game = new JMenu("Game");   //game menu
        menuBar.add(game);
        Help = new JMenu("Help");  //Help Menu
        menuBar.add(Help);
        //Menu Items
        New = new JMenuItem("New Game"); // new game
        game.add(New);
        JMenuItem beginner = new JMenuItem("Beginner"); //level of difficulty
        JMenuItem intermediate = new JMenuItem("Intermediate");
        JMenuItem expert = new JMenuItem("Expert");
        JMenuItem custom = new JMenuItem("Custom"); // custom field
        game.add(beginner);
        game.add(intermediate);
        game.add(expert);
        game.add(custom);
        Exit=  new JMenuItem("Exit");
        viewHelp = new JMenuItem("View Help");
        aboutGame = new JMenuItem("About Game ");
        setJMenuBar(menuBar);
        
        Exit.addActionListener(new ActionListener() {  //exit listener
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        New.addActionListener(new ActionListener() { // new listner
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int x = 0; x < buttons.length; x++) {
                    for (int y = 0; y < buttons[0].length; y++) {
                        startTime=0;
                        startTimer.stop();
                        buttons[x][y].setEnabled(true);
                        buttons[x][y].setText("");
                        
                    }
                }
                randomMine();
                
            }
        });
        viewHelp.addActionListener(new ActionListener() {  //Provide information if view help
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You are presented with a board of squares. \n Some squares contain mines (bombs), others don't. If you click on a square containing a bomb,\n you lose. If you manage to click all the squares (without clicking on any bombs) you win.\n" +
                        "Clicking a square which doesn't have a bomb reveals the number of neighbouring squares containing bombs.\nUse this information plus some guess work to avoid the bombs.\n" +
                        "To open a square, point at the square and click on it. To mark a square you think is a bomb, point and \nright-click (or hover with the mouse and press Space).\n ");
            }
        });
        
        aboutGame.addActionListener(new ActionListener() { // display the message of the creator of the game
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Created BY:\nBinod Katwal");
            }
        });
        
        
        
        
        beginner.addActionListener(new ActionListener() { // beginner game
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.removeAll();
                displayGUI(1,0,0,0); // grid of the game
                grid.revalidate();
                grid.repaint();
                beginner.setSelected(true);
                levelOfDifficulty =1;
                footer.removeAll();
                
            }
        });
        
        intermediate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                grid.removeAll();
                
                displayGUI(2,0,0,0); // 2 grid of the game
                
                grid.revalidate();
                //header.revalidate();
                //header.repaint();
                grid.repaint();
                intermediate.setSelected(true);
                levelOfDifficulty =2;
            }
        });
        
        expert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.removeAll();
                displayGUI(3,0,0,0); // 3 grid of the game
                grid.revalidate();
                grid.repaint();
                intermediate.setSelected(true);
                levelOfDifficulty =3;
                
            }
        });
        
        custom.addActionListener(new ActionListener() {   // field custom
            @Override
            public void actionPerformed(ActionEvent e) {

                new CustomField();
                grid.removeAll();
                grid.revalidate();
                grid.repaint();
                custom.setSelected(true);
                levelOfDifficulty = 4;
            }
        });
        game.add(Exit);
        Help.add(viewHelp); // add to the jmenuBar
        Help.add(aboutGame);
    }

    // New Class is Declared here " Custom Field"
    // The class CustomField basically display all the Custom  informations and asked user to input info so that
    // Custom will perform the user selected grids
    public class CustomField extends JFrame implements ActionListener {
        private int getHeight, getWidth, getMines; // declared variables

        public CustomField() { // Main functions called to customs
            super("Custom Field");
            input();
            displayBoard(this);

        }
        // Display the board
        private void displayBoard(CustomField field) {
            field.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            field.setSize(400, 360); // board size
            field.setResizable(false); //can't minimize
            field.getContentPane().setBackground(Color.BLACK);
            field.setLayout(null);
            field.setLocationRelativeTo(null);
            field.setVisible(true); //make available


        }

        //IT Basically added informations to the Custom Firled
        private void input() {

            JTextField width = new JTextField("Width :"); //display width
            width.setBounds(20, 20, 90, 50); //bounds
            width.setBackground(Color.BLUE);
            width.setForeground(Color.RED);
            width.setEditable(false);
            add(width);
            //width.addActionListener(this);

            JTextField height = new JTextField("Height :"); //display height
            height.setBounds(20, 90, 90, 50);
            height.setBackground(Color.BLUE);
            height.setForeground(Color.RED);
            height.setEditable(false);
            add(height);

            JTextField Mines = new JTextField("  Mines:");  //display mines
            Mines.setBounds(20, 160, 90, 50);
            Mines.setBackground(Color.BLUE);
            Mines.setForeground(Color.RED);
            Mines.setEditable(false);
            add(Mines);
            JTextField ansWidth = new JTextField(); //user inputs
            JTextField ansHeight = new JTextField();
            JTextField ansMines = new JTextField();
            ansWidth.setBounds(160, 20, 100, 50); //bounds
            ansHeight.setBounds(160, 90, 100, 50);
            ansMines.setBounds(160, 160, 100, 50);
            add(ansWidth);

            add(ansHeight);
            add(ansMines);

            JButton okey = new JButton("OK");  //display okey button
            okey.setBounds(60, 260, 80, 50);
            add(okey);

            okey.addActionListener(new ActionListener() {        //if user tended to press okey button, perform below
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        getWidth = Integer.parseInt(ansWidth.getText());
                        getHeight = Integer.parseInt(ansHeight.getText());
                        getMines = Integer.parseInt(ansMines.getText());

                        displayGUI(4, getWidth, getHeight, getMines); // gets grind of user input
                        dispose(); // close the custom field

                    } catch (Exception any) {
                        //display message if inputs are not integers
                        JOptionPane.showMessageDialog(null, "Invalid,Please input Numbers only!");
                    }
                }

            });

            JButton cancel = new JButton("Cancel"); //display cancel
            cancel.setBounds(160, 260, 80, 50);
            add(cancel);
            cancel.addActionListener(new ActionListener() { // if user want to cancel the field
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose(); // close the custom field while clicking cancel
                }
            });


        }

        //Not in use yet
        public void actionPerformed(ActionEvent e) { }

    } //end of custom field




// this function will reandomize the grid so that we can reset every time we reset the game
    public void randomMine()
    {
        //initializise of random pairs
        
        ArrayList<Integer>  checkList = new ArrayList<Integer>(); // declared check list
        
            for (int x = 0; x< counts.length; x++)
            {
                for (int y =0; y < counts[0].length; y++)
                {
                    checkList.add(x*100+y); // check list
                }
             }

            counts = new int[heightbutton][widthbutton]; //grid  in counts

            for (int val =0; val < savedMine; val++ ) // Loop will place given mines in different buttons
            {
                int option =(int)(Math.random()* checkList.size()); // random
                counts[checkList.get(option) /100 ][checkList.get(option) %100 ] =NumberOfMine;
                checkList.remove(option);
            }

            for (int val= 0; val < counts.length; val++)//Initialize
            {
                for (int val1 =0; val1 < counts[0].length; val1++) {
                    if (counts[val][val1] != NumberOfMine)
                    {
                        int neighborcount = 0; // declare variable
                        //will look for left
                        if (val > 0 && val1 >0 && counts[val-1][val1-1]== NumberOfMine){//go over neighbor
                        neighborcount++; // increment
                    }
                    // will look for up
                    if (val1>0 && counts[val][val1-1]== NumberOfMine) {
                        neighborcount++;
                    }
                    counts[val][val1]= neighborcount;
                }
            }
        }
        
    }


    //This function will actually checks 0's, if found, it'll display zeros so user wont be wasting lot of time
    
    public void displayzeros(ArrayList<Integer> displayInt) {

        if (displayInt.size() == 0) {
            return;
        } else {
            //Position of X and Y
            int x = displayInt.get(0) / 100;
            int y = displayInt.get(0) % 100;
            displayInt.remove(0);


            if (x > 0 && y > 0 && buttons[x - 1][y - 1].isEnabled()) { //up left, check for zero
                buttons[x - 1][y - 1].setText(counts[x - 1][y - 1] + " ");
                buttons[x - 1][y - 1].setEnabled(false);
                if (counts[x - 1][y - 1] == 0) {
                    displayInt.add((x - 1) * 100 + (y - 1));
                }
            }
            // right, checks for zero
            if (y > 0 && buttons[x][y - 1].isEnabled()) {
                buttons[x][y - 1].setText(counts[x][y - 1] + " ");
                buttons[x][y - 1].setEnabled(false);
                if (counts[x][y - 1] == 0) {
                    displayInt.add((x) * 100 + (y - 1));
                }
                }
            }
            displayzeros(displayInt); // call

    }

    // This function will displays the game message after user gets an WIN
    public void winGame(){
        boolean won = true;
        for (int x = 0; x < counts.length; x++) { // if counts
            for (int y = 0; y < counts[0].length; y++) {
                if (counts[x][y] != NumberOfMine && buttons[x][y].isEnabled() == true){ //lose the game
                    won =false;
                }
                
            }
        }
        if (won == true){ // win the game
            JOptionPane.showMessageDialog(null," You Won!"); //Display
        }
        
    }

    // if user clicks the mines, users will lost the game
    public void gamelost(){

        for (int x = 0; x < buttons.length; x++) { // if buttons
            for (int y = 0; y < buttons[0].length; y++) {
                if (buttons[x][y].isEnabled()){ // if enable

                    if(counts[x][y] != NumberOfMine) { // allow user to click until not clicked Mine

                        buttons[x][y].setText( counts[x][y] + "");
                        buttons[x][y].setEnabled(false);

                    }
                    else{ // if not mine or not integer then

                        buttons[x][y].setText("Bomb");
                        buttons[x][y].setEnabled(false);

                    }
                }
            }
        }
    }



    // This function is the major part of the function will allow user to do everything user will see on screen
    // Resets, call functions as well as connect with each other.
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(smile)){ // if User click reset smile face, timer will reset
            //reset the board
            startTime=0; //reset
            startTimer.stop(); //stop time

            for (int x = 0; x < buttons.length; x++) {  //grab and randamize the program
                for (int y = 0; y < buttons[0].length; y++) {

                    buttons[x][y].setEnabled(true);
                    buttons[x][y].setText("");

                }
            }
            randomMine(); //call the function
        }

        else
        {  // if the clicks the buttons then  below code will be perform
            for (int x = 0; x < buttons.length; x++) {
                for (int y = 0; y < buttons[0].length; y++) {

                    if (e.getSource().equals(buttons[x][y])){  // if clicked buttons
                        startTimer.start();

                        if (counts[x][y] == NumberOfMine) // if mine(bomb) then

                        {
                            startTimer.stop(); //stop
                            buttons[x][y].setForeground(Color.RED);
                            buttons[x][y].setText(counts[x][y]+ "");
                            gamelost(); //call the gamelost function


                            JOptionPane.showMessageDialog(null, "YOU LOST!"); // message

                        }else if  (counts[x][y] == 0) // while in the game if counts equal 0, will perform below
                        {
                            ArrayList<Integer> displayInt = new ArrayList<Integer>();
                            buttons[x][y].setText(counts[x][y]+ "");
                            buttons[x][y].setEnabled(false);
                            displayInt.add(x*100+y);
                            displayzeros(displayInt); //call the function
                            winGame(); // call the win function



                        }
                        else
                        buttons[x][y].setText(counts[x][y]+ ""); //Else
                        buttons[x][y].setEnabled(false);
                    }

                }
            }
        }

    }
    
    // Main Function of the program which execute every single code written above
    public static void main(String[] args)
    {
        
        new MineSweeper(); // Call the function
        
    }
    
}



