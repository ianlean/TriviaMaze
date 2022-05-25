package GUI;

import TriviaMaze.Cell;
import TriviaMaze.Room;

import javax.swing.*;

import java.awt.*;

import static GUI.Frame.*;

public class ButtonPanel extends JPanel {

    public ButtonPanel() {
        JButton up = new JButton("Up");
        JButton down = new JButton("Down");
        JButton left = new JButton("Left");
        JButton right = new JButton("Right");
        styleButtons(up);
        styleButtons(down);
        styleButtons(left);
        styleButtons(right);
        up.addActionListener(e -> {
            Room r = Frame.myController.findRoom("n");
            if (r.getMyQuestion() != null && r.getMyStatus() == Cell.RoomStatus.LOCKED)
            {
                myTextBoxes.addText(r.getMyQuestion().getQuestion());
                myTextBoxes.myCurrentAnswer = r.getMyQuestion().getCorrectAnswer();
                myCur = "n";
            }
            else if (r.getMyStatus() == Cell.RoomStatus.UNLOCKED) {
                myController.askDirection("n");
                mazeView.decrementY();
                mazeView.repaint();
            } else {
                myTextBoxes.addText("This door is sealed.");
                myCur = null;
            }
        });
        down.addActionListener(e -> {
            Room r = Frame.myController.findRoom("s");
            if (r.getMyQuestion() != null && r.getMyStatus() == Cell.RoomStatus.LOCKED) {
                myTextBoxes.addText(r.getMyQuestion().getQuestion());
                myTextBoxes.myCurrentAnswer = r.getMyQuestion().getCorrectAnswer();
                myCur = "s";
            } else if (r.getMyStatus() == Cell.RoomStatus.UNLOCKED) {
                myController.askDirection("s");
                Frame.mazeView.incrementY();
                Frame.mazeView.repaint();
            } else {
                myTextBoxes.addText("This door is sealed.");
                myCur = null;
            }
        });
        right.addActionListener(e -> {
            Room r = Frame.myController.findRoom("e");
            if (r.getMyQuestion() != null && r.getMyStatus() == Cell.RoomStatus.LOCKED) {
                myTextBoxes.addText(r.getMyQuestion().getQuestion());
                myTextBoxes.myCurrentAnswer = r.getMyQuestion().getCorrectAnswer();
                myCur = "e";
            } else if (r.getMyStatus() == Cell.RoomStatus.UNLOCKED) {
                myController.askDirection("e");
                Frame.mazeView.incrementX();
                Frame.mazeView.repaint();
            } else {
                myTextBoxes.addText("This door is sealed.");
                myCur = null;
            }
        });
        left.addActionListener(e -> {
            Room r = Frame.myController.findRoom("w");
            if (r.getMyQuestion() != null && r.getMyStatus() == Cell.RoomStatus.LOCKED) {
                myTextBoxes.addText(r.getMyQuestion().getQuestion());
                myTextBoxes.myCurrentAnswer = r.getMyQuestion().getCorrectAnswer();
                myCur = "w";
            } else if (r.getMyStatus() == Cell.RoomStatus.UNLOCKED) {
                myController.askDirection("w");
                Frame.mazeView.decrementX();
                Frame.mazeView.repaint();
            } else {
                myTextBoxes.addText("This door is sealed.");
                myCur = null;
            }
        });
        this.add(up);
        this.add(down);
        this.add(left);
        this.add(right);
    }

    private static void styleButtons(final JButton theButton)
    {
        theButton.setBackground(new Color(131, 39, 145));
        theButton.setForeground(Color.BLACK);
        theButton.setFont(new Font("Tahoma", Font.BOLD, 12));
    }
}
