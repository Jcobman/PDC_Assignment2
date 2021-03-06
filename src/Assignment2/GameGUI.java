/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment2;

import com.sun.jndi.ldap.Connection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicBorders;

/**
 *
 * @author jcobm
 */
public class GameGUI extends JPanel implements ActionListener
{

    //private final Timer timer;
    public JFrame window;
    public JButton attack, magic, run, newGame, loadGame, saveYes, saveNo, quitYes, quitNo, next;
    public JLabel playerName, playerHP, playerMP, title, saveLabel, quitLabel;
    public JTextArea textArea;
    public JPanel titlePanel, savePanel, quitPanel, hpPanel, mpPanel, namePanel;
    public Font labelFont = new Font("Arial", Font.PLAIN, 36);
    public Font btnFont = new Font("Arial", Font.PLAIN, 24);
    public Player player;
    public Enemy enemy;
    public PlayerBattle pBattle;
    public EnemyBattle eBattle;
    public Database db;

    public GameGUI()
    {
        //Creates the GUI window
        initWindow();
        //Creates all of the Buttons
        initButton();
        //Creates the pannels for the window 
        initPanel();
        
        //Setting up variables 
        pBattle = new PlayerBattle();
        eBattle = new EnemyBattle();
        db = new Database();
        db.checkTables();
        player = new Player("");
        enemy = new Enemy();
        
        //Making the window visiable
        window.setVisible(true);
    }

    public void initWindow()
    {
        //Creating the main window
        window = new JFrame("Monster Slayer");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);
    }

    public void initButton() //Sets up the  buttons
    {
        attack = new JButton("ATTACK");
        magic = new JButton("MAGIC");
        run = new JButton("RUN");
        newGame = new JButton("NEW GAME");
        loadGame = new JButton("LOAD GAME");
        saveYes = new JButton("YES");
        saveNo = new JButton("NO");
        quitYes = new JButton("YES");
        quitNo = new JButton("NO");
        next = new JButton(">");

        //Make the buttons have actions when clicked
        attack.addActionListener(this);
        magic.addActionListener(this);
        run.addActionListener(this);
        newGame.addActionListener(this);
        loadGame.addActionListener(this);
        saveYes.addActionListener(this);
        saveNo.addActionListener(this);
        quitYes.addActionListener(this);
        quitNo.addActionListener(this);
        next.addActionListener(this);

        newGame.setBackground(Color.DARK_GRAY);
        newGame.setBorder(new LineBorder(Color.CYAN, 5));
        newGame.setFont(btnFont);
        newGame.setForeground(Color.WHITE);
        newGame.setBounds(150, 400, 200, 75);

        loadGame.setBackground(Color.DARK_GRAY);
        loadGame.setBorder(new LineBorder(Color.CYAN, 5));
        loadGame.setFont(btnFont);
        loadGame.setForeground(Color.WHITE);
        loadGame.setBounds(450, 400, 200, 75);

        saveYes.setBackground(Color.DARK_GRAY);
        saveYes.setBorder(new LineBorder(Color.CYAN, 5));
        saveYes.setFont(btnFont);
        saveYes.setForeground(Color.WHITE);
        saveYes.setBounds(150, 400, 200, 75);

        saveNo.setBackground(Color.DARK_GRAY);
        saveNo.setBorder(new LineBorder(Color.CYAN, 5));
        saveNo.setFont(btnFont);
        saveNo.setForeground(Color.WHITE);
        saveNo.setBounds(450, 400, 200, 75);

        quitYes.setBackground(Color.DARK_GRAY);
        quitYes.setBorder(new LineBorder(Color.CYAN, 5));
        quitYes.setFont(btnFont);
        quitYes.setForeground(Color.WHITE);
        quitYes.setBounds(150, 400, 200, 75);

        quitNo.setBackground(Color.DARK_GRAY);
        quitNo.setBorder(new LineBorder(Color.CYAN, 5));
        quitNo.setFont(btnFont);
        quitNo.setForeground(Color.WHITE);
        quitNo.setBounds(450, 400, 200, 75);

        attack.setBackground(Color.DARK_GRAY);
        attack.setBorder(new LineBorder(Color.CYAN, 5));
        attack.setFont(btnFont);
        attack.setForeground(Color.WHITE);
        attack.setBounds(50, 400, 200, 75);

        magic.setBackground(Color.DARK_GRAY);
        magic.setBorder(new LineBorder(Color.CYAN, 5));
        magic.setFont(btnFont);
        magic.setForeground(Color.WHITE);
        magic.setBounds(300, 400, 200, 75);

        run.setBackground(Color.DARK_GRAY);
        run.setBorder(new LineBorder(Color.CYAN, 5));
        run.setFont(btnFont);
        run.setForeground(Color.WHITE);
        run.setBounds(550, 400, 200, 75);

        next.setBackground(Color.DARK_GRAY);
        next.setBorder(new LineBorder(Color.CYAN, 5));
        next.setFont(btnFont);
        next.setForeground(Color.WHITE);
        next.setBounds(300, 400, 200, 75);

        //Adds the buttons to the window
        window.add(newGame);
        window.add(loadGame);
        window.add(attack);
        window.add(magic);
        window.add(run);
        window.add(next);
    }

    public void initPanel() //Creates all of the panels
    {
        title = new JLabel("MONSTER SLAYER");
        title.setFont(labelFont);
        title.setForeground(Color.WHITE);

        saveLabel = new JLabel("Do you wish to save?");
        saveLabel.setForeground(Color.WHITE);
        saveLabel.setFont(labelFont);

        quitLabel = new JLabel("Do you wish to Quit?");
        quitLabel.setForeground(Color.WHITE);
        quitLabel.setFont(labelFont);

        titlePanel = new JPanel();
        titlePanel.setBounds(150, 100, 500, 100);
        titlePanel.setBackground(Color.DARK_GRAY);
        titlePanel.setLayout(new GridBagLayout());
        titlePanel.add(title);

        savePanel = new JPanel();
        savePanel.setBounds(200, 200, 400, 50);
        savePanel.setBackground(Color.BLACK);
        savePanel.setLayout(new GridBagLayout());
        savePanel.add(saveLabel);

        quitPanel = new JPanel();
        quitPanel.setBounds(200, 200, 400, 50);
        quitPanel.setBackground(Color.BLACK);
        quitPanel.setLayout(new GridBagLayout());
        quitPanel.add(quitLabel);
        
        playerName = new JLabel("");
        playerName.setForeground(Color.ORANGE);
        playerName.setFont(btnFont);

        playerHP = new JLabel("HP: ");
        playerHP.setForeground(Color.WHITE);
        playerHP.setFont(btnFont);

        playerMP = new JLabel("MP: ");
        playerMP.setForeground(Color.WHITE);
        playerMP.setFont(btnFont);

        namePanel = new JPanel();
        namePanel.setBounds(200, 10, 400, 50);
        namePanel.setBackground(Color.BLACK);
        namePanel.setLayout(new GridBagLayout());
        namePanel.add(playerName);

        hpPanel = new JPanel();
        hpPanel.setBounds(30, 200, 100, 50);
        hpPanel.setBackground(Color.DARK_GRAY);
        hpPanel.add(playerHP);

        mpPanel = new JPanel();
        mpPanel.setBounds(650, 200, 100, 50);
        mpPanel.setBackground(Color.DARK_GRAY);
        mpPanel.add(playerMP);

        textArea = new JTextArea(10, 10);
        textArea.setText("");
        textArea.setBounds(140, 100, 500, 270);
        textArea.setBackground(Color.DARK_GRAY);
        textArea.setForeground(Color.WHITE);

        //Sets text to display on the text area
        PrintStream ps = new PrintStream(new TextOutputGUI(textArea));
        System.setOut(ps);

        //Adds the panels to the window
        window.add(titlePanel);
        window.add(saveYes);
        window.add(saveNo);
        window.add(savePanel);
        window.add(quitYes);
        window.add(quitNo);
        window.add(quitPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();

        //When Attack is pressed
        if (source == attack)
        {           
            textArea.setText("");
            pBattle.playerAttack(this); //Players turn
            if (this.checkEnemyHealth() == true) //Checks if enemy's health is 0
            {
                eBattle.enemyBattle(this); //Enemy has their turn
            }
            playerHP.setText("HP: " + player.getHp()); //Refreshs the HP on screen           
            this.checkPlayerHealth(); //Checks to see if the players health drops to 0                   
        }
        
        //When the Magic button is pressed
        if (source == magic)
        {
            
            textArea.setText("");
            pBattle.playerMagic(this); //Players turn
            playerMP.setText("MP: " + player.getMp()); //Refreshs the MP on screen  
            if (this.checkEnemyHealth() == true) //Checks if enemy's health is 0
            {
                eBattle.enemyBattle(this); //Enemy has their turn
            }
            playerHP.setText("HP: " + player.getHp()); //Refreshs the HP on screen  
            this.checkPlayerHealth(); //Checks to see if the players health drops to 0                             
        }
        
        //When the Run button is pressed
        if (source == run)
        {
            textArea.setText("");
            if (pBattle.playerRun() == true) //Run was sucessful
            {
                this.generateEnemy(); //Creates a random enemy
            }
            else //Run failed
            {
                eBattle.enemyBattle(this); //Enemy has their turn
                playerHP.setText("HP: " + player.getHp());//Refreshs the HP on screen                      
                this.checkPlayerHealth(); //Checks to see if the players health drops to 0                            
            }
        }

        //When the New game button is pressed
        if (source == newGame)
        {
            String name;
            boolean valid = false;
            do
            {
                //Creates a window to enter a name
                name = JOptionPane.showInputDialog(window, "Please enter your hero's name", "");
                try
                {
                    if (name.isEmpty()) //Checks if there was nothing entered
                    {
                        JOptionPane.showMessageDialog(window, "Please enter a name", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    else
                    {
                        valid = true;
                    }
                }
                catch (NullPointerException ex)
                {
                    valid = true;
                }

            }
            while (valid != true); //Loop to take it back to the enter name window
            
            if (name != null) //Fail safe for cancel button pressed
            {
                player = new Player(name); //Creates a new player
                this.battleStart(); //Goes to the battle screen
            }

        }

        //When the Load game button is pressed
        if (source == loadGame)
        {
            String name;
            boolean valid = false;
            
            do
            {
                //Creates a window to enter a name to load
                name = JOptionPane.showInputDialog(window, "Please enter your hero's name to Load", "");
                try
                {
                    if (name.isEmpty()) //Checks if there was nothing entered
                    {
                        JOptionPane.showMessageDialog(window, "Please enter a name", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    else
                    {
                        valid = true;
                    }
                }

                catch (NullPointerException ex)
                {
                    valid = true;
                }
            }
            while (valid != true); //Loop to take it back to the enter name window
            
            if (name != null) //Fail safe for cancel button pressed
            {
                player = new Player("");
                Load load = new Load(); //Loads player
                try
                {
                    if (load.loadGame(name, player, db))
                    {
                        this.battleStart(); //Goes to the battle screen if load is successful
                    }
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(GameGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        
        //When the Yes button is pressed
        if (source == saveYes)
        {
            Save save = new Save(); //Saves the game
            save.saveGame(this.player, this.db);
            this.quitScreen(); //Goes to the quit screen
        }

        //When the No button is pressed
        if (source == saveNo)
        {
            this.quitScreen(); //Goes to the quit screen
        }

        //When the Yes button is pressed
        if (source == quitYes)
        {
            //Makes a popup window to confirm quit
            if (JOptionPane.showConfirmDialog(window, "Are you sure you want to quit?", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) //Yes option
            {
                System.exit(0); //Closes the window
            }
            else //No option
            {
                this.battleStart(); //Goes to the battle screen
            }
        }

        //When the No button is pressed
        if (source == quitNo)
        {
            this.battleStart(); //Goes to the battle screen
        }

        //When the > button is pressed
        if (source == next)
        {
            this.saveScreen(); //Goes to the save screen
        }
    }

    public void titleScreen()
    {
        //Turns off all the other screens
        quitPanel.setVisible(false);
        quitYes.setVisible(false);
        quitNo.setVisible(false);
        savePanel.setVisible(false);
        saveYes.setVisible(false);
        saveNo.setVisible(false);
        attack.setVisible(false);
        magic.setVisible(false);
        run.setVisible(false);
        next.setVisible(false);

        //Turns on the title screen
        titlePanel.setVisible(true);
        newGame.setVisible(true);
        loadGame.setVisible(true);
    }

    public void battleScreen()
    {
        //Turns off the title screen and save screen
        titlePanel.setVisible(false);
        newGame.setVisible(false);
        loadGame.setVisible(false);
        quitPanel.setVisible(false);
        quitYes.setVisible(false);
        quitNo.setVisible(false);
        savePanel.setVisible(false);
        saveYes.setVisible(false);
        saveNo.setVisible(false);
        next.setVisible(false);

        //Sets up the text for the panels
        playerName.setText(player.getName());       
        playerHP.setText("HP: " + player.getHp());       
        playerMP.setText("MP: " + player.getMp());
        textArea.setText("");
        
        window.add(textArea);
        window.add(namePanel);
        window.add(hpPanel);
        window.add(mpPanel);

        //Turns on the battle screen
        textArea.setVisible(true);
        attack.setVisible(true);
        magic.setVisible(true);
        run.setVisible(true);
        hpPanel.setVisible(true);
        mpPanel.setVisible(true);
        namePanel.setVisible(true);
    }

    public void saveScreen()
    {
        //Turns off the previous screen 
        next.setVisible(false);
        attack.setVisible(false);
        magic.setVisible(false);
        run.setVisible(false);
        textArea.setVisible(false);
        hpPanel.setVisible(false);
        mpPanel.setVisible(false);
        namePanel.setVisible(false);

        //Turns on the save screen
        savePanel.setVisible(true);
        saveYes.setVisible(true);
        saveNo.setVisible(true);
    }

    public void quitScreen()
    {
        //Turns off the previous screen
        savePanel.setVisible(false);
        saveYes.setVisible(false);
        saveNo.setVisible(false);

        //Turns on the Panels and Buttons 
        quitPanel.setVisible(true);
        quitYes.setVisible(true);
        quitNo.setVisible(true);
    }

    public void battleStart()
    {
        //Starts up the battle screen
        this.battleScreen();
        //Creates a random enemy
        this.generateEnemy();
    }

    public void generateEnemy()
    {
        //Randomly gets a number to decide what enemy to use
        Random rand = new Random();
        int enemyChosen = rand.nextInt(4) + 1;

        //Creates a enemy depending on what number was chosen 
        switch (enemyChosen)
        {
            case 1:
            {
                this.enemy = new Orc(db);
                break;
            }
            case 2:
            {
                this.enemy = new Wizard(db);
                break;
            }
            case 3:
            {
                this.enemy = new Giant(db);
                break;
            }
            case 4:
            {
                this.enemy = new Elf(db);
                break;
            }
        }
        try
        {
            //Writes the enemy's details to the database
            this.enemy.writeEnemy(db);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(GameGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Displays what enemy is chosen
        System.out.println("A " + this.enemy.getName() + " appears!!\n");
        System.out.println("What do you do?");
    }

    public boolean checkPlayerHealth()
    {
        //Checks if the plays health has reached 0
        if (player.getHp() <= 0)
        {
            player.setHp(0);
            playerHP = new JLabel("HP: 0");
            //Displays a game over window
            JOptionPane.showMessageDialog(window, "GAME OVER\nTry again next time!\nYour score was "+player.getScore(), "GAME OVER", JOptionPane.WARNING_MESSAGE);
            //Closes the window
            System.exit(0);
            return false;
        }
        else
        {
            return true;
        }
    }

    
    public boolean checkEnemyHealth()
    {
        //Checks if the enemy's health has fallen to 0
        if (enemy.getHp() <= 0)
        {
            this.battleEnd();
            return false;
        }
        else
        {
            return true;
        }
    }

    public void battleEnd()
    {
        //Turning off buttons not needed
        attack.setVisible(false);
        magic.setVisible(false);
        run.setVisible(false);
        
        //turns on next button
        next.setVisible(true);

        System.out.println("The Monster is defeted!");

        //Incresses the Players EXP 
        this.player.setExp(this.player.getExp() + this.enemy.getExpGain());
        System.out.println("CONGRATULATIONS!!!!\n");
        System.out.println("You gained " + this.enemy.getExpGain() + " EXP!\n");//Displays the EXP gained

        //Checks to see if the player has enough EXP to Level up
        if (this.player.getExp() >= this.player.getLvl() * 100)
        {
            this.player.levelUp();//Player levels up
        }
        else
        {
            //Advises how much how much EXP is left before Level up
            System.out.println("You have " + ((this.player.getLvl() * 100) - this.player.getExp()) + " EXP before next level up");

        }
        //Incresses the players score by 1
        player.setScore(player.getScore() + 1);
    }

    public static void main(String[] args)
    {
        //Creates and sets up the GUI
        GameGUI gui = new GameGUI();
        //Take you to the title screen
        gui.titleScreen();

    }
}
