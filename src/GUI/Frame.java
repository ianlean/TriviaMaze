package GUI;



import TriviaMaze.*;
import TriviaMaze.Question.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.lang.Character;
import java.sql.SQLException;

public class Frame extends JFrame
{
    private final static int WIDTH = 2000;
    private final static int HEIGHT = 2000;

    private final static int MAZE_SIZE = 8;
//    private final TriviaMaze myMaze = new TriviaMaze(MAZE_SIZE);

    TextPanel textBoxes;

    static final Controller myController;

    static {
        try {
            myController = new Controller(MAZE_SIZE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // private JPanel myPanel =new JPanel();

    /** the menu bar for the GUI */
    private final JMenuBar myMenuBar ;

    /** the new game menu item */
    private JMenuItem myNewMenuItem;

    /** the save menu item */
    private JMenuItem mySaveMenuItem;

    /** the load menu item */
    private JMenuItem myLoadMenuItem;

    /** the exit menu item */
    private JMenuItem myExitMenuItem;

    /** the volume menu item*/
    private JMenuItem myVolumeMenuItem;

    /** the theme menu item*/
    private JMenuItem myThemeMenuItem;

    /** the about menu item*/
    private JMenuItem myAboutMenuItem;

    /** the instruction menu item*/
    private JMenuItem myInstructionMenuItem;

    static Maze mazeView;

    static {
        try {
            mazeView = new Maze(myController.getGameMaze());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
    private final JButton Red=new JButton("Red");
    private final JButton Yellow=new JButton("Yellow");
    private final JButton Green=new JButton("Green");

    static String myCur;

    public Frame() throws IOException, SQLException {

        this.setTitle("Maze Game");
        final ImageIcon uwImage = new ImageIcon(new ImageIcon(getClass().getResource("/GUIPictures/w.gif"))
                .getImage().getScaledInstance(60,40,Image.SCALE_DEFAULT));
        this.setIconImage(uwImage.getImage());
        this.setSize(WIDTH, HEIGHT);
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //creat menu bar
        myMenuBar=createMenuBar();
        this.add(myMenuBar);
        this.setJMenuBar(myMenuBar);

        // add room panel
        RoomPanel roomView = new RoomPanel();
        this.add(roomView);
        roomView.setLocation(0, 0);
        this.add(mazeView);
        mazeView.setLocation(500, 0);
        JPanel buttonPanel = createButtonPanel(myController);
        this.add(buttonPanel);
        buttonPanel.setBounds(1000,0, 300, 100);
        textBoxes = new TextPanel();
        this.add(textBoxes);
        textBoxes.setBounds(500, 500, 800, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.pack();
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


        mySaveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                FileDialog fd = new FileDialog(new JFrame(), "Save Game", FileDialog.SAVE);
                fd.setVisible(true);
                if (fd.getFile() == null) return;
                String fileName = fd.getFile();
                File file = new File(fd.getDirectory(), fileName);
                file.setWritable(true);
                try {
                    ObjectOutputStream out = new ObjectOutputStream(
                            new BufferedOutputStream(new FileOutputStream(file)));
                    out.writeObject(mazeView.getCharacter());
                    //out.writeObject(TriviaMaze.getCharacterSpot());
                    //out.writeObject(mazeView.getCharacterSpot());
                    out.close();

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(e);
                    return;
                }
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

        myLoadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog fd = new FileDialog(new JFrame(), "Load Game", FileDialog.LOAD);
                fd.setVisible(true);
                if (fd.getFile() == null) return;
                String fileName = fd.getFile();
                File file = new File(fd.getDirectory(), fileName);
                try{
                    ObjectInputStream in=new ObjectInputStream
                            (new BufferedInputStream(new FileInputStream(file)));
//                    Room characterSpot=(Room) in.readObject();
//                    TriviaMaze.setCharacterSpot(characterSpot);
                    Character character=(Character) in.readObject();
                       mazeView.setCharacter(character);
//                    Room characterSpot=(Room) in.readObject();
//                    mazeView.setCharacterSpot(characterSpot);
                } catch (ClassNotFoundException | IOException ex) {
                    ex.printStackTrace();
                    return;
                }

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

        myExitMenuItem.addActionListener(e -> System.exit(0));
    }

    /**
     * create the option Menu
     * it can adjust the game volume and theme(background)
     * @return the Option menu
     */
    private JMenu createOptionsMenu(){
        final JMenu myOptionsMenu=new JMenu("Options");
        myOptionsMenu.setMnemonic(KeyEvent.VK_O);



        myVolumeMenuItem = new JMenuItem("Volume");
        myVolumeMenuItem.setMnemonic(KeyEvent.VK_V);
        myOptionsMenu.add(myVolumeMenuItem);

        //volume slider
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
        myThemeMenuItem = new JMenuItem("Theme");
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

    private JPanel createButtonPanel(final Controller theController) {
        JPanel buttonPanel = new JPanel();
        JButton up = new JButton("Up");
        JButton down = new JButton("Down");
        JButton left = new JButton("Left");
        JButton right = new JButton("Right");
        styleButtons(up);
        styleButtons(down);
        styleButtons(left);
        styleButtons(right);
        up.addActionListener(e -> {
            Room r = theController.findRoom("n");
            if (r.getMyQuestion() != null && r.getMyStatus() == Cell.RoomStatus.LOCKED) {
                textBoxes.addText(r.getMyQuestion().getQuestion());
                textBoxes.currentAnswer = r.getMyQuestion().getCorrectAnswer();
                myCur = "n";
            } else if (r.getMyStatus() == Cell.RoomStatus.UNLOCKED) {
                myController.askDirection("n");
                Frame.mazeView.decrementY();
                Frame.mazeView.repaint();
            } else {
                textBoxes.addText("This door is sealed.");
            }
        });
        down.addActionListener(e -> {
            Room r = theController.findRoom("s");
            if (r.getMyQuestion() != null && r.getMyStatus() == Cell.RoomStatus.LOCKED) {
                textBoxes.addText(r.getMyQuestion().getQuestion());
                textBoxes.currentAnswer = r.getMyQuestion().getCorrectAnswer();
                myCur = "s";
            } else if (r.getMyStatus() == Cell.RoomStatus.UNLOCKED) {
                myController.askDirection("s");
                Frame.mazeView.incrementY();
                Frame.mazeView.repaint();
            } else {
                textBoxes.addText("This door is sealed.");
            }
        });
        right.addActionListener(e -> {
            Room r = theController.findRoom("e");
            if (r.getMyQuestion() != null && r.getMyStatus() == Cell.RoomStatus.LOCKED) {
                textBoxes.addText(r.getMyQuestion().getQuestion());
                textBoxes.currentAnswer = r.getMyQuestion().getCorrectAnswer();
                myCur = "e";
            } else if (r.getMyStatus() == Cell.RoomStatus.UNLOCKED) {
                myController.askDirection("e");
                Frame.mazeView.incrementX();
                Frame.mazeView.repaint();
            } else {
                textBoxes.addText("This door is sealed.");
            }
        });
        left.addActionListener(e -> {
            Room r = theController.findRoom("w");
            if (r.getMyQuestion() != null && r.getMyStatus() == Cell.RoomStatus.LOCKED) {
                textBoxes.addText(r.getMyQuestion().getQuestion());
                textBoxes.currentAnswer = r.getMyQuestion().getCorrectAnswer();
                myCur = "w";
            } else if (r.getMyStatus() == Cell.RoomStatus.UNLOCKED) {
                myController.askDirection("w");
                Frame.mazeView.decrementX();
                Frame.mazeView.repaint();
            } else {
                textBoxes.addText("This door is sealed.");
            }
        });
        buttonPanel.add(up);
        buttonPanel.add(down);
        buttonPanel.add(left);
        buttonPanel.add(right);
        buttonPanel.setSize(100, 100);
        return buttonPanel;
    }

    private static void styleButtons(final JButton theButton) {
        theButton.setBackground(new Color(131, 39, 145));
        theButton.setForeground(Color.BLACK);
        theButton.setFont(new Font("Tahoma", Font.BOLD, 12));
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