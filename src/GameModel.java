class GameModel {
    int[][] matrix;
    boolean[][] revealed;
    int rows, cols;
    int level;
    int score;
    int timeRemaining;

    public GameModel(int level, int rows, int cols, int initialScore) {
        this.level = level;
        this.rows = rows;
        this.cols = cols;
        this.score = initialScore;
        this.timeRemaining = 30 * (level + 1); // Thời gian tăng theo level
        initializeMatrix();
    }

    private void initializeMatrix() {
        matrix = new int[rows][cols];
        revealed = new boolean[rows][cols];
        int[] icons = new int[rows * cols];
        for (int i = 0; i < icons.length / 2; i++) {
            icons[i] = icons[i + icons.length / 2] = i + 1;
        }
        shuffleArray(icons);
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = icons[index++];
            }
        }
    }

    private void shuffleArray(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public boolean checkMatch(int x1, int y1, int x2, int y2) {
        return matrix[x1][y1] == matrix[x2][y2];
    }
}