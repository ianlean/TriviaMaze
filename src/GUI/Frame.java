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

public class Frame extends JFrame implements Serializable
{
    private final static int WIDTH = 2000;
    private final static int HEIGHT = 2000;

    final static int MAZE_SIZE = 8;
    static TextPanel myTextBoxes;

    static Controller myController;
    static {
        try {
            myController = new Controller(MAZE_SIZE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    static MazePanel mazeView;
    static {
        try {
            mazeView = new MazePanel(myController.getGameMaze());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static String myCur;


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
     * frame GUI constructor
     * @throws IOException
     * @throws SQLException
     */
    public Frame() throws IOException, SQLException {

        this.setTitle("Maze Game");
        final ImageIcon uwImage = new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/GUIPictures/w.gif")))
                .getImage().getScaledInstance(60, 40, Image.SCALE_DEFAULT));
        this.setIconImage(uwImage.getImage());
        this.setSize(WIDTH, HEIGHT);
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //add menu bar
        JMenuBar myMenuBar=createMenuBar();
        this.add(myMenuBar);
        this.setJMenuBar(myMenuBar);

        // add room panel
//        RoomPanel roomView = new RoomPanel();
//        this.add(roomView);
//        roomView.setLocation(0, 0);

        //add maze Panel
        this.add(mazeView);
        mazeView.setLocation(0, 0);

        //add button panel
        JPanel buttonPanel = new ButtonPanel();
        this.add(buttonPanel);
        buttonPanel.setBounds(500, 0, 500, 100);

        //add question/answer panel
        myTextBoxes = new TextPanel();
        this.add(myTextBoxes);
        myTextBoxes.setBounds(500, 100, 800, 300);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.pack();
    }

    /**
     * a method to create JMenuBar
     * @return JMenuBar
     */
    private JMenuBar createMenuBar() {
        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createOptionsMenu());
        menuBar.add(createHelpMenu());
        return menuBar;
    }

    /**
     * a method to create file JMenu
     * it has new game, save game, load game, and exit game functions
     * @return file JMenu
     */
    private JMenu createFileMenu()
    {
        JMenu myFileMenu=new JMenu("File");
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
     * a method to create new game menu item in file JMenu
     * new game menu item allows the user to start a new game
     */
    private void createNewMenuItem() {
        myNewMenuItem=new JMenuItem("New Game");
        myNewMenuItem.setMnemonic(KeyEvent.VK_N);
        myNewMenuItem.setEnabled(true);
        myNewMenuItem.addActionListener(e -> {});  // haven't done
    }

    /**
     *  a method to create save game menu item in file JMenu
     *  save menu item allows the user to save the current game state
     */
    private void createSaveMenuItem()
    {
        mySaveMenuItem=new JMenuItem("Save Game");
        mySaveMenuItem.setMnemonic(KeyEvent.VK_S);
        mySaveMenuItem.setEnabled(true);
        mySaveMenuItem.addActionListener(event -> saveActionListener());
    }

    /**
     * actionListen for save menuitem
     */
    public void saveActionListener(){
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
            out.writeObject(myController.getGameMaze());
            out.close();
            file.close();
        }
        catch (IOException theE)
        {
            theE.printStackTrace();
        }
    }

    /**
     * a method to create load game menu item in file JMenu
     * load menu item allows the user to load the previous game state
     */
    private void createLoadMenuItem()
    {
       myLoadMenuItem=new JMenuItem("Load Game");
       myLoadMenuItem.setMnemonic(KeyEvent.VK_L);
       myLoadMenuItem.setEnabled(true);
       myLoadMenuItem.addActionListener(e -> loadActionListener());
    }


    /**
     * actionListen for load menuitem
     */
    public void loadActionListener(){
        FileDialog fd = new FileDialog(new JFrame(), "Load Game", FileDialog.LOAD);
        fd.setVisible(true);
        if (fd.getFile() == null) return;
        try
        {
            File f = new File(fd.getDirectory(), fd.getFile());
            FileInputStream file=new FileInputStream(f);
            ObjectInputStream in=new ObjectInputStream(file);
            TriviaMaze maze= (TriviaMaze)in.readObject();
            myController.setMyGameMaze(maze);
            this.repaint();
            this.validate();
            this.repaint();
            in.close();
            file.close();
        } catch (ClassNotFoundException | IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * a method to create exit menu item in file JMenu
     * exit menu item allows the user to exit the game
     */
    private void createExitMenuItem()
    {
       myExitMenuItem=new JMenuItem("Exit");
       myExitMenuItem.setMnemonic(KeyEvent.VK_E);
       myExitMenuItem.setEnabled(true);
       myExitMenuItem.addActionListener(e -> System.exit(0));
    }

    /**
     * create the option JMenu
     * it has Volume and Theme two menuitems
     * it can adjust the game volume and theme(background)
     * @return the Options menu
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
     * create the Help JMenu
     * including About and Instruction two menuitems
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
     * a method to create about menu item in About JMenu
     * this menu item shows the information about this maze game
     */
    private void createAboutMenuItem()
    {
        myAboutMenuItem=new JMenuItem("About");
        myAboutMenuItem.setMnemonic(KeyEvent.VK_A);
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
     * a method to create instruction menu item in About JMenu
     * this menu item shows the instruction for this maze game
     */
    private void createInstructionMenuItem()
    {
        myInstructionMenuItem=new JMenuItem("Instruction");
        myInstructionMenuItem.setMnemonic(KeyEvent.VK_I);
        myInstructionMenuItem.addActionListener(new ActionListener()
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

    public MazePanel getPan() {
        return mazeView;
    }

    public TextPanel getMyTextBoxes() {
        return myTextBoxes;
    }

    public Controller getController() {
        return myController;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
        {
            try {
                new Frame();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        });
    }
}