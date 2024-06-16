import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MineSweeperGUI extends JFrame {

    private MineSweeper game;
    private JButton[][] buttons;
    private int size;
    private long startTime;
    private JRadioButton flagButton;
    private JRadioButton openButton;
    private int difficulty = 0;

    public MineSweeperGUI() {
        setTitle("Minesweeper");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLACK);
        titlePanel.setPreferredSize(new Dimension(getWidth(), 100));

        JLabel gameTitle = new JLabel("\uD83D\uDCA3 Minesweeper \uD83D\uDCA3", JLabel.CENTER);
        gameTitle.setFont(new Font("Segoe UI Emoji", Font.BOLD, 48));
        gameTitle.setForeground(Color.YELLOW);
        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gameTitle.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        titlePanel.add(gameTitle);
        add(titlePanel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.LIGHT_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel label = new JLabel("Select Difficulty", JLabel.CENTER);
        label.setFont(new Font("Verdana", Font.BOLD, 36));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(label, gbc);

        JButton easyButton = new JButton("Easy");
        easyButton.setFont(new Font("Verdana", Font.BOLD, 24));
        easyButton.setBackground(new Color(144, 238, 144));
        easyButton.setFocusPainted(false);
        easyButton.setPreferredSize(new Dimension(200, 50));
        easyButton.addActionListener(e -> startGame(0));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(easyButton, gbc);

        JButton mediumButton = new JButton("Medium");
        mediumButton.setFont(new Font("Verdana", Font.BOLD, 24));
        mediumButton.setBackground(new Color(173, 216, 230));
        mediumButton.setFocusPainted(false);
        mediumButton.setPreferredSize(new Dimension(200, 50));
        mediumButton.addActionListener(e -> startGame(1));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(mediumButton, gbc);

        JButton hardButton = new JButton("Hard");
        hardButton.setFont(new Font("Verdana", Font.BOLD, 24));
        hardButton.setBackground(new Color(255, 182, 193));
        hardButton.setFocusPainted(false);
        hardButton.setPreferredSize(new Dimension(200, 50));
        hardButton.addActionListener(e -> startGame(2));
        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(hardButton, gbc);

        add(panel, BorderLayout.CENTER);
    }

    private void startGame(int difficulty) {
        this.difficulty = difficulty;

        if (difficulty == 0)
            size = 5;
        else
            if (difficulty == 1)
                size = 8;
            else
                size = 15;
        game = new MineSweeper(size);
        buttons = new JButton[size][size];
        startTime = System.currentTimeMillis();
        initializeGameBoard();
    }

    private void initializeGameBoard() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        topPanel.setBackground(Color.PINK);
        topPanel.setForeground(Color.PINK);

        flagButton = new JRadioButton("Flag");
        flagButton.setFont(new Font("Verdana", Font.BOLD, 24));
        flagButton.setBackground(Color.PINK);

        openButton = new JRadioButton("Open", true);  // Default selected
        openButton.setFont(new Font("Verdana", Font.BOLD, 24));
        openButton.setBackground(Color.PINK);

        ButtonGroup group = new ButtonGroup();
        group.add(flagButton);
        group.add(openButton);

        topPanel.add(flagButton);
        topPanel.add(openButton);

        add(topPanel, BorderLayout.NORTH);

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(size, size));
        gamePanel.setBackground(Color.BLACK);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
                buttons[i][j].setPreferredSize(new Dimension(75, 75));
                buttons[i][j].setActionCommand(i + " " + j);
                buttons[i][j].addActionListener(new ClickListener());
                gamePanel.add(buttons[i][j]);
            }
        }

        add(gamePanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void restartGame() {
        game = new MineSweeper(size); // Restart the game with the same size
        startTime = System.currentTimeMillis();
        getContentPane().removeAll();
        initializeGameBoard();
        revalidate();
        repaint();
    }

    private void updateScreen() {
        char[][] grid = game.actualGrid();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j].setText(String.valueOf(grid[i][j]));
                if (grid[i][j] == 'B') {
                    buttons[i][j].setBackground(Color.RED);
                    buttons[i][j].setText("\uD83D\uDCA3Bomb");
                } else if (grid[i][j] == 'D') {
                    buttons[i][j].setBackground(Color.GREEN);
                    buttons[i][j].setText("Def✨");
                } else if (grid[i][j] == 'F') {
                    buttons[i][j].setBackground(Color.YELLOW);
                    buttons[i][j].setText("\uD83D\uDEA9");
                } else {
                    buttons[i][j].setBackground(Color.LIGHT_GRAY);
                }
            }
        }
    }

    private class ClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            String[] parts = command.split(" ");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);

            if (flagButton.isSelected()) {
                game.flagCell(x, y);
            } else {
                game.openCell(x, y, true);
            }

            updateScreen();
            //Fim de jogo
            if (game.isGameOver()) {
                long timeTaken = (System.currentTimeMillis() - startTime) / 1000;
                int choice = 0;

                File scoreboard;
                if (difficulty == 0)
                    scoreboard = new File("src/Scoreboard/easy.txt");
                else
                    if (difficulty == 1)
                        scoreboard = new File("src/Scoreboard/medium.txt");
                    else
                        scoreboard = new File("src/Scoreboard/hard.txt");

                try {
                    String[] score = new String[10];
                    Scanner reader = new Scanner(scoreboard);
                    for (int i = 0; reader.hasNextLine(); i++)
                        score[i] = reader.nextLine();

                    if (game.hasWon()) {
                        String print = "";
                        boolean inSB = false;
                        int totalPrints = 0;
                        for (int i = 0; totalPrints < 10 && score[i] != null; i++)
                            if (!inSB && score[i].compareTo(Long.toString(timeTaken)) > 0) {
                                print += timeTaken + " seconds\n";
                                inSB = true;
                                totalPrints++;
                                i--;
                            } else {
                                print += score[i] + "\n";
                                totalPrints++;
                            }

                        FileWriter write = new FileWriter(scoreboard, false);
                        write.write(print);
                        write.close();

                        choice = JOptionPane.showConfirmDialog(null, "🎉 Congratulations! You won in " + timeTaken + " seconds!\nScoreboard:\n" + print + "Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {
                            restartGame();
                        } else {
                            System.exit(0);
                        }
                    } else {
                        String print = "";
                        for (int i = 0; i < 10 && score[i] != null; i++)
                            print += score[i] + "\n";

                        choice = JOptionPane.showConfirmDialog(null, "❌ Game Over! Time: " + timeTaken + " seconds\nScoreboard:\n" + print + "Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {
                            restartGame();
                        } else {
                            System.exit(0);
                        }
                    }

                reader.close();
                } catch (IOException err) {
                    System.out.println("File not found");
                    System.exit(0);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MineSweeperGUI().setVisible(true));
    }
}
