package GUI;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Frame extends JFrame
{
    private final static int WIDTH = 1000;
    private final static int HEIGHT = 1000;

    // the menu bar for the GUI
    private final JMenuBar myMenuBar ;

    // the new game menu item
    private JMenuItem myNewMenuItem;

    // the save menu item
    private JMenuItem mySaveMenuItem;

    // the load menu item
    private JMenuItem myLoadMenuItem;

    // the exit menu item
    private JMenuItem myExitMenuItem;

    // the volume menu item
    private JMenuItem myVolumeMenuItem;

    // the about menu item
    private JMenuItem myAboutMenuItem;

    // the instruction menu item
    private JMenuItem myInstructionMenuItem;




    public Frame() throws IOException
    {
        this.setTitle("Maze Game");
        final ImageIcon uwImage = new ImageIcon(new ImageIcon(getClass().getResource("/GUIPictures/w.gif"))
                .getImage().getScaledInstance(60,40,Image.SCALE_DEFAULT));
        this.setIconImage(uwImage.getImage());
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //creat menu bar
        myMenuBar=createMenuBar();
        this.add(myMenuBar);
        this.setJMenuBar(myMenuBar);

        // add room panel
        this.add(new RoomPanel());


        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

    }

    /**
     * create the menu bar for the game GUI
     * @return the menu bar
     */
    private JMenuBar createMenuBar() {
        JMenuBar myMenuBar = new JMenuBar();
        myMenuBar.add(createFileMenu());
        myMenuBar.add(createOptionsMenu());
        myMenuBar.add(createHelpMenu());
        return myMenuBar;
    }



    /**
     * create the File Menu, including new, save, load,and exit game
     * @return the file menu
     */
    private JMenu createFileMenu(){
        final JMenu myFileMenu=new JMenu("File");
        myFileMenu.setMnemonic(KeyEvent.VK_F);

        createNewMenuItem();
        myFileMenu.add(myNewMenuItem);
        myFileMenu.addSeparator();

        createSaveMenuItem();
        myFileMenu.add(mySaveMenuItem);
        myFileMenu.addSeparator();

        createLoadMenuItem();
        myFileMenu.add(myLoadMenuItem);
        myFileMenu.addSeparator();

        createExitMenuItem();
        myFileMenu.add(myExitMenuItem);


        return myFileMenu;

    }

    /**
     * a method to create new game menu item
     * new game menu item allows the user to start a new game
     */
    private void createNewMenuItem() {
        myNewMenuItem=new JMenuItem("New Game");
        myNewMenuItem.setMnemonic(KeyEvent.VK_N);
        myNewMenuItem.setEnabled(true);

        //new game mouse listener, haven't done
        myNewMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    /**
     *  a method to create save game menu item
     *  save menu item allows the user to save the current game state
     *
     */
    private void createSaveMenuItem() {
        mySaveMenuItem=new JMenuItem("Save Game");
        mySaveMenuItem.setMnemonic(KeyEvent.VK_S);
        mySaveMenuItem.setEnabled(true);

        //save mouse listener, haven't done
        mySaveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    /**
     * a method to create load game menu item
     * load menu item allows the user to load the previous game state
     */
    private void createLoadMenuItem() {
        myLoadMenuItem=new JMenuItem("Load Game");
        myLoadMenuItem.setMnemonic(KeyEvent.VK_L);
        myLoadMenuItem.setEnabled(true);

        //load mouse listener, haven't done
        myLoadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }



    /**
     * a method to create exit menu item
     * exit menu item allows the user to exit the game
     */
    private void createExitMenuItem() {
        myExitMenuItem=new JMenuItem("Exit");
        myExitMenuItem.setMnemonic(KeyEvent.VK_E);
        myExitMenuItem.setEnabled(true);

        myExitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });
    }

    /**
     * create the option Menu, it can adjust the game volume
     * @return the Option menu
     */
    private JMenu createOptionsMenu(){
        final JMenu myOptionsMenu=new JMenu("Options");
        myOptionsMenu.setMnemonic(KeyEvent.VK_O);

        createVolumeMenuItem();
        myOptionsMenu.add(myVolumeMenuItem);

        return myOptionsMenu;

    }

    /**
     * a method to create volume menu item
     * this menu item can use a slider to adjust the game volume
     */
    private void createVolumeMenuItem() {
        myVolumeMenuItem=new JMenuItem(" Adjust Volume");
        myVolumeMenuItem.setMnemonic(KeyEvent.VK_V);

        myVolumeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    /**
     * create the Help Menu, including About and Instruction
     * @return the Help menu
     */
    private JMenu createHelpMenu(){
        final JMenu myHelpMenu=new JMenu("Help");
        myHelpMenu.setMnemonic(KeyEvent.VK_H);

        createAboutMenuItem();
        myHelpMenu.add(myAboutMenuItem);
        myHelpMenu.addSeparator();

        createInstructionMenuItem();
        myHelpMenu.add(myInstructionMenuItem);

        return myHelpMenu;

    }



    /**
     * a method to create about menu item
     * this menu item shows the information about this maze game
     */
    private void createAboutMenuItem() {
        myAboutMenuItem=new JMenuItem("About");
        myAboutMenuItem.setMnemonic(KeyEvent.VK_A);

        myAboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final ImageIcon uwImage = new ImageIcon(new ImageIcon(getClass().getResource("/GUIPictures/w.gif"))
                        .getImage().getScaledInstance(60,40,Image.SCALE_DEFAULT));

               JOptionPane.showMessageDialog(null,"<Trivia Maze Game> by \n" +
                        "Ian McLean \nKevin Yang \nQinyu Tao","About",JOptionPane.INFORMATION_MESSAGE,uwImage);

            }
        });


    }

    /**
     * a method to create instruction menu item
     * this menu item shows the instruction for this maze game
     */
    private void createInstructionMenuItem() {
        myInstructionMenuItem=new JMenuItem("Instruction");
        myInstructionMenuItem.setMnemonic(KeyEvent.VK_I);

        myInstructionMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final ImageIcon uwImage = new ImageIcon(new ImageIcon(getClass().getResource("/GUIPictures/w.gif"))
                        .getImage().getScaledInstance(60,40,Image.SCALE_DEFAULT));
                JOptionPane.showMessageDialog(null,"<Game Instruction> \n ",
                        "Instruction",JOptionPane.INFORMATION_MESSAGE,uwImage);
            }
        });


    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
        {
            try {
                new Frame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}