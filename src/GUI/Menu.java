package GUI;

import TriviaMaze.Controller;
import TriviaMaze.TriviaMaze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.sql.SQLException;
import java.util.Objects;

public class Menu extends JMenuBar implements Serializable
{

    private final static int MAZE_SIZE = 8;

    static Controller myController ;
    static
    {
        try
        {
            myController = new Controller(MAZE_SIZE);
        }
        catch (SQLException theE)
        {
            throw new RuntimeException(theE);
        }
    }

    static MazePanel mazeView;
    static
    {
        try
        {
            mazeView = new MazePanel(myController.getGameMaze());
        }
        catch (IOException theE)
        {
            throw new RuntimeException(theE);
        }
    }

    static TriviaMaze myMaze;

    /** the new game menu item */
    private JMenuItem myNewMenuItem;

    /** the save menu item */
    private JMenuItem mySaveMenuItem;

    /** the load menu item */
    private JMenuItem myLoadMenuItem;

    /** the exit menu item */
    private JMenuItem myExitMenuItem;

    /** the about menu item*/
    private JMenuItem myAboutMenuItem;

    /** the instruction menu item*/
    private JMenuItem myInstructionMenuItem;

    /** volume slider minimum */
    private static final int VOLUME_MINIMUM=0;

    /** volume slider maximum */
    private static final int VOLUME_MAXIMUM=100;

    /** volume slider major spacing */
    private static final int MAJOR_SPACING = 25;

    /** volume slider minor spacing */
    private static final int MINOR_SPACING = 5;

    /** volume slider initial value position. */
    private static final int VOLUME_INITIAL = 50;

    /** theme choice button */
    private final JButton Red = new JButton("Red");
    private final JButton Yellow = new JButton("Yellow");
    private final JButton Green = new JButton("Green");



    /**
     * create the menu bar for the game GUI
     * @return the menu bar
     */
    public Menu()
    {
        this.add(createFileMenu());
        this.add(createOptionsMenu());
        this.add(createHelpMenu());
    }

    /**
     * create the File Menu, including new, save, load,and exit game
     *
     * @return the file menu
     */
    private JMenu createFileMenu()
    {
        final JMenu myFileMenu=new JMenu("File");
        myFileMenu.setMnemonic(KeyEvent.VK_F);

        createNewMenuItem();
        myFileMenu.add(this.myNewMenuItem);
        myFileMenu.addSeparator();

        createSaveMenuItem();
        myFileMenu.add(this.mySaveMenuItem);
        myFileMenu.addSeparator();

        createLoadMenuItem();
        myFileMenu.add(this.myLoadMenuItem);
        myFileMenu.addSeparator();

        createExitMenuItem();
        myFileMenu.add(this.myExitMenuItem);

        return myFileMenu;
    }

    /**
     * a method to create new game menu item
     * new game menu item allows the user to start a new game
     */
    private void createNewMenuItem() {
        this.myNewMenuItem=new JMenuItem("New Game");
        this.myNewMenuItem.setMnemonic(KeyEvent.VK_N);
        this.myNewMenuItem.setEnabled(true);

        this.myNewMenuItem.addActionListener(e -> {});
    }

    /**
     *  a method to create save game menu item
     *  save menu item allows the user to save the current game state
     *
     */
    private void createSaveMenuItem()
    {
        this.mySaveMenuItem=new JMenuItem("Save Game");
        this.mySaveMenuItem.setMnemonic(KeyEvent.VK_S);
        this.mySaveMenuItem.setEnabled(true);

        // haven't finished
        this.mySaveMenuItem.addActionListener(event ->
        {
            FileDialog fd = new FileDialog(new JFrame(), "Save Game", FileDialog.SAVE);
            fd.setVisible(true);
            if (fd.getFile() == null) return;
            String fileName = fd.getFile();
            try
            {
                File f = new File(fd.getDirectory(), fileName);
                f.setWritable(true);
                FileOutputStream file = new FileOutputStream(f);
                ObjectOutputStream out =new ObjectOutputStream(file);

                out.writeObject(myMaze.getSaveLocation());


                out.close();
                file.close();
            }
            catch (IOException theE)
            {
                theE.printStackTrace();
            }
        });
    }

    /**
     * a method to create load game menu item
     * load menu item allows the user to load the previous game state
     */
    private void createLoadMenuItem()
    {
        this.myLoadMenuItem=new JMenuItem("Load Game");
        this.myLoadMenuItem.setMnemonic(KeyEvent.VK_L);
        this.myLoadMenuItem.setEnabled(true);

        this.myLoadMenuItem.addActionListener(e ->
        {
            FileDialog fd = new FileDialog(new JFrame(), "Load Game", FileDialog.LOAD);
            fd.setVisible(true);
            if (fd.getFile() == null) return;
            try
            {
                File f = new File(fd.getDirectory(), fd.getFile());
                FileInputStream file=new FileInputStream(f);
                ObjectInputStream in=new ObjectInputStream(file);
                int[] saveLocation=(int[]) in.readObject();

                myMaze.setSaveLocation(saveLocation);
                in.close();
                file.close();

            } catch (ClassNotFoundException | IOException ex)
            {
                ex.printStackTrace();
            }

        });
    }

    /**
     * a method to create exit menu item
     * exit menu item allows the user to exit the game
     */
    private void createExitMenuItem()
    {
        this.myExitMenuItem=new JMenuItem("Exit");
        this.myExitMenuItem.setMnemonic(KeyEvent.VK_E);
        this.myExitMenuItem.setEnabled(true);
        this.myExitMenuItem.addActionListener(e -> System.exit(0));
    }

    /**
     * create the option Menu
     * it can adjust the game volume and theme(background)
     * @return the Option menu
     */
    private JMenu createOptionsMenu()
    {
        final JMenu myOptionsMenu=new JMenu("Options");
        myOptionsMenu.setMnemonic(KeyEvent.VK_O);

        JMenuItem myVolumeMenuItem = new JMenuItem("Volume");
        myVolumeMenuItem.setMnemonic(KeyEvent.VK_V);
        myOptionsMenu.add(myVolumeMenuItem);

        final JSlider volumeSlider = new JSlider(SwingConstants.HORIZONTAL,
                VOLUME_MINIMUM, VOLUME_MAXIMUM, VOLUME_INITIAL);

        volumeSlider.setMajorTickSpacing(MAJOR_SPACING);
        volumeSlider.setMinorTickSpacing(MINOR_SPACING);
        volumeSlider.setPaintLabels(true);
        volumeSlider.setPaintTicks(true);
        myOptionsMenu.add(volumeSlider);
        myOptionsMenu.addSeparator();
        // haven't finished
        volumeSlider.addChangeListener(theEvent -> {
            final JSlider volume = (JSlider) theEvent.getSource();
            if (!volume.getValueIsAdjusting()) {
                final int volumeValue = volume.getValue();
            }
        });

        // theme menu item to change the background
        JMenuItem myThemeMenuItem = new JMenuItem("Theme");
        myThemeMenuItem.setMnemonic(KeyEvent.VK_T);
        myOptionsMenu.add(myThemeMenuItem);

        myOptionsMenu.add(Red);
        myOptionsMenu.add(Yellow);
        myOptionsMenu.add(Green);
        Red.addActionListener(e -> setBackgroundRed());
        Yellow.addActionListener(e -> setBackgroundYellow());
        Green.addActionListener(e -> setBackgroundGreen());


        return myOptionsMenu;

    }

    private void setBackgroundRed() {
        this.setBackground(Color.RED);
    }
    private void setBackgroundYellow() {
        this.setBackground(Color.YELLOW);
    }
    private void setBackgroundGreen() {
        this.setBackground(Color.GREEN);
    }

    /**
     * create the Help Menu, including About and Instruction
     * @return the Help menu
     */
    private JMenu createHelpMenu(){
        final JMenu myHelpMenu=new JMenu("Help");
        myHelpMenu.setMnemonic(KeyEvent.VK_H);

        createAboutMenuItem();
        myHelpMenu.add(this.myAboutMenuItem);
        myHelpMenu.addSeparator();

        createInstructionMenuItem();
        myHelpMenu.add(this.myInstructionMenuItem);

        return myHelpMenu;

    }

    /**
     * a method to create about menu item
     * this menu item shows the information about this maze game
     */
    private void createAboutMenuItem()
    {
        this.myAboutMenuItem=new JMenuItem("About");
        this.myAboutMenuItem.setMnemonic(KeyEvent.VK_A);

        myAboutMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                final ImageIcon uwImage = new ImageIcon
                        (new ImageIcon(Objects.requireNonNull(getClass().getResource("/GUIPictures/w.gif")))
                        .getImage().getScaledInstance(60,40,Image.SCALE_DEFAULT));
                JOptionPane.showMessageDialog(null, """
                        <Trivia Maze Game> by\s
                        Ian McLean\s
                        Kevin Yang\s
                        Qinyu Tao""","About",JOptionPane.INFORMATION_MESSAGE,uwImage);

            }
        });


    }

    /**
     * a method to create instruction menu item
     * this menu item shows the instruction for this maze game
     */
    private void createInstructionMenuItem()
    {
        this.myInstructionMenuItem=new JMenuItem("Instruction");
        this.myInstructionMenuItem.setMnemonic(KeyEvent.VK_I);

        this.myInstructionMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                final ImageIcon uwImage = new ImageIcon
                        (new ImageIcon(Objects.requireNonNull(getClass().getResource("/GUIPictures/w.gif")))
                        .getImage().getScaledInstance(60,40,Image.SCALE_DEFAULT));
                JOptionPane.showMessageDialog(null,"<Game Instruction> \n ",
                        "Instruction",JOptionPane.INFORMATION_MESSAGE,uwImage);
            }
        });
    }
}