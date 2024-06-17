import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// Declaração da classe MineSweeperGUI que herda de JFrame para criar uma interface gráfica
public class MineSweeperGUI extends JFrame {

    // Variáveis de instância
    private MineSweeper game; // Instância do jogo MineSweeper
    private JButton[][] buttons; // Matriz de botões para representar as células do jogo
    private int size; // Tamanho da grade do jogo
    private long startTime; // Tempo de início do jogo
    private JRadioButton flagButton; // Botão de rádio para marcar células com bandeira
    private JRadioButton openButton; // Botão de rádio para abrir células
    private int difficulty = 0; // Nível de dificuldade do jogo

    // Construtor da classe MineSweeperGUI
    public MineSweeperGUI() {
        // Configuração da janela principal
        setTitle("Minesweeper");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza a janela
        setUndecorated(true); // Remove a decoração padrão da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de título
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLACK);
        titlePanel.setPreferredSize(new Dimension(getWidth(), 100));

        // Título do jogo
        JLabel gameTitle = new JLabel("\uD83D\uDCA3 Minesweeper \uD83D\uDCA3", JLabel.CENTER);
        gameTitle.setFont(new Font("Segoe UI Emoji", Font.BOLD, 48));
        gameTitle.setForeground(Color.YELLOW);
        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gameTitle.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        titlePanel.add(gameTitle);
        add(titlePanel, BorderLayout.NORTH);

        // Painel de seleção de dificuldade
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.LIGHT_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Label para selecionar a dificuldade
        JLabel label = new JLabel("Select Difficulty", JLabel.CENTER);
        label.setFont(new Font("Verdana", Font.BOLD, 36));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(label, gbc);

        // Botão de dificuldade fácil
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

        // Botão de dificuldade média
        JButton mediumButton = new JButton("Medium");
        mediumButton.setFont(new Font("Verdana", Font.BOLD, 24));
        mediumButton.setBackground(new Color(173, 216, 230));
        mediumButton.setFocusPainted(false);
        mediumButton.setPreferredSize(new Dimension(200, 50));
        mediumButton.addActionListener(e -> startGame(1));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(mediumButton, gbc);

        // Botão de dificuldade difícil
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

    /**
     * Method to start the game with the selected difficulty.
     *
     * @param difficulty The difficulty level of the game.
     */

    // Método para iniciar o jogo com a dificuldade selecionada
    private void startGame(int difficulty) {
        this.difficulty = difficulty;

        // Define o tamanho da grade com base na dificuldade
        if (difficulty == 0)
            size = 5; // Fácil
        else if (difficulty == 1)
            size = 8; // Médio
        else
            size = 15; // Difícil

        game = new MineSweeper(size); // Cria uma nova instância do jogo com o tamanho definido
        buttons = new JButton[size][size]; // Inicializa a matriz de botões
        startTime = System.currentTimeMillis(); // Registra o tempo de início do jogo
        initializeGameBoard(); // Inicializa o tabuleiro do jogo
    }

    // Método para inicializar o tabuleiro do jogo
    private void initializeGameBoard() {
        getContentPane().removeAll(); // Remove todos os componentes atuais do container
        setLayout(new BorderLayout());

        // Painel superior com botões de ação
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        topPanel.setBackground(Color.PINK);
        topPanel.setForeground(Color.PINK);

        flagButton = new JRadioButton("Flag");
        flagButton.setFont(new Font("Verdana", Font.BOLD, 24));
        flagButton.setBackground(Color.PINK);

        openButton = new JRadioButton("Open", true); // Default selected
        openButton.setFont(new Font("Verdana", Font.BOLD, 24));
        openButton.setBackground(Color.PINK);

        ButtonGroup group = new ButtonGroup(); // Grupo de botões para garantir que apenas um seja selecionado
        group.add(flagButton);
        group.add(openButton);

        topPanel.add(flagButton);
        topPanel.add(openButton);

        add(topPanel, BorderLayout.NORTH);

        // Painel do jogo com a grade de botões
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
        revalidate(); // Revalida o layout
        repaint(); // Requer uma repintura do container
    }

    // Método para reiniciar o jogo
    private void restartGame() {
        game = new MineSweeper(size); // Reinicia o jogo com o mesmo tamanho
        startTime = System.currentTimeMillis(); // Registra o tempo de início do jogo
        getContentPane().removeAll(); // Remove todos os componentes atuais do container
        initializeGameBoard(); // Inicializa o tabuleiro do jogo
        revalidate(); // Revalida o layout
        repaint(); // Requer uma repintura do container
    }

    // Método para atualizar a tela do jogo
    private void updateScreen() {
        char[][] grid = game.actualGrid(); // Obtém a grade atual do jogo
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j].setText(String.valueOf(grid[i][j])); // Atualiza o texto do botão com o valor da célula
                if (grid[i][j] == 'B') {
                    buttons[i][j].setBackground(Color.RED); // Define a cor de fundo para vermelho se for uma bomba
                    buttons[i][j].setText("\uD83D\uDCA3Bomb"); // Define o texto do botão para "Bomb"
                } else if (grid[i][j] == 'D') {
                    buttons[i][j].setBackground(Color.GREEN); // Define a cor de fundo para verde se for uma bomba desativada
                    buttons[i][j].setText("Def✨"); // Define o texto do botão para "Def"
                } else if (grid[i][j] == 'F') {
                    buttons[i][j].setBackground(Color.YELLOW); // Define a cor de fundo para amarelo se estiver marcada com bandeira
                    buttons[i][j].setText("\uD83D\uDEA9"); // Define o texto do botão para a bandeira
                } else {
                    buttons[i][j].setBackground(Color.LIGHT_GRAY); // Define a cor de fundo para cinza claro para outras células
                }
            }
        }
    }

    // Classe interna para lidar com cliques nos botões
    private class ClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand(); // Obtém o comando de ação do botão clicado
            String[] parts = command.split(" ");
            int x = Integer.parseInt(parts[0]); // Coordenada x da célula
            int y = Integer.parseInt(parts[1]); // Coordenada y da célula

            if (flagButton.isSelected()) {
                game.flagCell(x, y); // Marca a célula com bandeira
            } else {
                game.openCell(x, y, true); // Abre a célula
            }

            updateScreen(); // Atualiza a tela

            // Verifica se o jogo terminou
            if (game.isGameOver()) {
                long timeTaken = (System.currentTimeMillis() - startTime) / 1000; // Tempo total do jogo em segundos
                int choice = 0;

                // Determina o arquivo de scoreboard com base na dificuldade
                File scoreboard;
                if (difficulty == 0)
                    scoreboard = new File("src/Scoreboard/easy.txt");
                else if (difficulty == 1)
                    scoreboard = new File("src/Scoreboard/medium.txt");
                else
                    scoreboard = new File("src/Scoreboard/hard.txt");

                try {
                    // Lê o scoreboard atual
                    String[] score = new String[10];
                    Scanner reader = new Scanner(scoreboard);
                    for (int i = 0; reader.hasNextLine(); i++)
                        score[i] = reader.nextLine();

                    if (game.hasWon()) {
                        // Se o jogador ganhou, atualiza o scoreboard
                        String print = "";
                        boolean inSB = false;
                        int totalPrints = 0;
                        for (int i = 0; totalPrints < 10 && score[i] != null; i++) {
                            if (!inSB && score[i].compareTo(Long.toString(timeTaken)) > 0) {
                                print += timeTaken + " seconds\n"; // Adiciona o novo tempo ao scoreboard
                                inSB = true;
                                totalPrints++;
                                i--;
                            } else {
                                print += score[i] + "\n";
                                totalPrints++;
                            }
                        }

                        // Escreve o novo scoreboard no arquivo
                        FileWriter write = new FileWriter(scoreboard, false);
                        write.write(print);
                        write.close();

                        // Mostra mensagem de vitória e pergunta se o jogador quer jogar novamente
                        choice = JOptionPane.showConfirmDialog(null, "🎉 Congratulations! You won in " + timeTaken + " seconds!\nScoreboard:\n" + print + "Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {
                            restartGame(); // Reinicia o jogo
                        } else {
                            System.exit(0); // Encerra o jogo
                        }
                    } else {
                        // Se o jogador perdeu, apenas mostra o scoreboard
                        String print = "";
                        for (int i = 0; i < 10 && score[i] != null; i++)
                            print += score[i] + "\n";

                        choice = JOptionPane.showConfirmDialog(null, "❌ Game Over! Time: " + timeTaken + " seconds\nScoreboard:\n" + print + "Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {
                            restartGame(); // Reinicia o jogo
                        } else {
                            System.exit(0); // Encerra o jogo
                        }
                    }

                    reader.close();
                } catch (IOException err) {
                    System.out.println("File not found"); // Mensagem de erro se o arquivo não for encontrado
                    System.exit(0); // Encerra o jogo
                }
            }
        }
    }

    // Método principal para iniciar a aplicação GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MineSweeperGUI().setVisible(true)); // Inicia a GUI na thread de despacho de eventos do Swing
    }
}
