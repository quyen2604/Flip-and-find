import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GameController {
    GameModel model;
    GameView view;
    Timer timer;
    int firstX = -1, firstY = -1;

    public GameController(int level) {
        int rows = 3 + level; // Tăng kích thước theo level
        int cols = 4 + level;
        model = new GameModel(level, rows, cols, 0);
        view = new GameView(rows, cols);

        setupListeners();
        startTimer();
    }

    private void setupListeners() {
        for (int i = 0; i < model.rows; i++) {
            for (int j = 0; j < model.cols; j++) {
                int x = i, y = j;
                view.buttons[i][j].addActionListener(e -> handleButtonClick(x, y));
            }
        }
    }

    private void handleButtonClick(int x, int y) {
        if (model.revealed[x][y]) return;

        view.buttons[x][y].setText(String.valueOf(model.matrix[x][y]));
        model.revealed[x][y] = true;

        if (firstX == -1) {
            firstX = x;
            firstY = y;
        } else {
            if (model.checkMatch(firstX, firstY, x, y)) {
                model.score += 100;
            } else {
                Timer flipBackTimer = new Timer(500, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        view.buttons[firstX][firstY].setText("");
                        view.buttons[x][y].setText("");
                        model.revealed[firstX][firstY] = false;
                        model.revealed[x][y] = false;
                        firstX = -1;
                        firstY = -1;
                        ((Timer) e.getSource()).stop();
                    }
                });
                flipBackTimer.setRepeats(false);
                flipBackTimer.start();
            }
            view.updateScore(model.score);
            firstX = firstY = -1;
        }
    }

    private void startTimer() {
        timer = new Timer(1000, e -> {
            model.timeRemaining--;
            view.updateTime(model.timeRemaining);
            if (model.timeRemaining <= 0) {
                timer.stop();
                JOptionPane.showMessageDialog(view, "Time's up! Your score: " + model.score);
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        new GameController(0);
    }
}
