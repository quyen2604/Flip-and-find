import javax.swing.*;
import java.awt.*;

class GameView extends JFrame {
    JButton[][] buttons;
    JLabel scoreLabel, timeLabel;
    JPanel boardPanel;

    public GameView(int rows, int cols) {
        setTitle("Game Lật Hình");
        setLayout(null);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(20, 20, 200, 30);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(scoreLabel);

        timeLabel = new JLabel("Time: 30");
        timeLabel.setBounds(600, 20, 200, 30);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(timeLabel);

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(rows, cols));
        boardPanel.setBounds(100, 100, 600, 400);
        add(boardPanel);

        buttons = new JButton[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.LIGHT_GRAY);
                boardPanel.add(buttons[i][j]);
            }
        }

        setVisible(true);
    }

    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    public void updateTime(int time) {
        timeLabel.setText("Time: " + time);
    }
}
