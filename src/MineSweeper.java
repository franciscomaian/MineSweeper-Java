import java.lang.Math;

// Declaração da classe MineSweeper
public class MineSweeper {
    // Constantes da classe
    public final double BOMB_PERCENTAGE = 15 / 100.0; // Percentual de células que são bombas
    public final int SPECIAL_BOMBS = 2; // Número de bombas especiais
    public final int ROUNDS_CHANGE_STATUS = 3; // Número de rodadas para mudar o status das bombas especiais

    // Variáveis de instância
    private Cell[][] grid; // Grade de células do jogo
    private int totalBombs; // Total de bombas normais no jogo
    private int size; // Tamanho da grade do jogo
    private boolean gameOver; // Indica se o jogo terminou
    private int opened; // Número de células abertas

    // Variáveis para gerenciar bombas especiais
    private int counter; // Contador de rodadas
    private boolean bombsActive; // Indica se as bombas especiais estão ativas
    private int[][] localSpecialBombs; // Localização das bombas especiais

    // Construtor da classe MineSweeper
    public MineSweeper(int size) {
        opened = 0;
        gameOver = false;
        this.size = size;
        grid = new Cell[size][size];
        totalBombs = (int) (size * size * BOMB_PERCENTAGE);

        // Configurando as variáveis das bombas especiais
        counter = 0;
        bombsActive = true;
        localSpecialBombs = new int[SPECIAL_BOMBS][2];

        // Adicionando as bombas normais
        for (int i = 0; i < totalBombs; i++) {
            int x = (int) (size * Math.random());
            int y = (int) (size * Math.random());

            if (grid[x][y] != null) // Verifica se a célula já contém uma bomba
                i--;
            else
                grid[x][y] = new Bomb();
        }

        // Adicionando as bombas especiais
        for (int i = 0; i < SPECIAL_BOMBS; i++) {
            int x = (int) (size * Math.random());
            int y = (int) (size * Math.random());

            if (grid[x][y] != null) // Verifica se a célula já contém uma bomba
                i--;
            else {
                grid[x][y] = new SpecialBomb();
                localSpecialBombs[i][0] = x;
                localSpecialBombs[i][1] = y;
            }
        }

        // Definindo os números dos campos sem bombas
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                int cont = 0;
                if (grid[x][y] == null) {
                    // Olhando quantas bombas tem ao redor
                    for (int i = x - 1; i <= x + 1; i++) {
                        for (int j = y - 1; j <= y + 1; j++) {
                            if (i >= 0 && j >= 0 && i < size && j < size)
                                if (grid[i][j] != null && grid[i][j].isBomb())
                                    cont++;
                        }
                    }
                    grid[x][y] = new Field(cont); // Define o valor da célula com o número de bombas ao redor
                }
            }
        }
    }

    // Método que verifica se o jogo terminou
    public boolean isGameOver() {
        return gameOver;
    }

    // Método que retorna a grade atual do jogo para exibição
    public char[][] actualGrid() {
        char[][] ret = new char[size][size];
        for (int x = 0; x < size; x++)
            for (int y = 0; y < size; y++)
                if (grid[x][y].isOpen())
                    if (grid[x][y].isBomb())
                        if (grid[x][y].isActive())
                            ret[x][y] = 'B'; // Bomba ativa
                        else
                            ret[x][y] = 'D'; // Bomba desativada
                    else
                        ret[x][y] = (char)(grid[x][y].getValue() + '0'); // Valor da célula
                else
                if (grid[x][y].isFlagged())
                    ret[x][y] = 'F'; // Célula marcada com bandeira
                else
                    ret[x][y] = ' '; // Célula não aberta e não marcada

        return ret;
    }

    // Método para abrir uma célula
    public void openCell(int x, int y, boolean isPlayer) {
        if (!grid[x][y].isOpen() && !grid[x][y].isFlagged()) {
            grid[x][y].open();
            opened++;

            if (grid[x][y].isActive() || opened == ((size * size) - totalBombs))
                gameOver = true;

            if (isPlayer) {
                counter++;

                if (counter >= ROUNDS_CHANGE_STATUS) {
                    counter = 0;

                    for (int bombs = 0; bombs < SPECIAL_BOMBS; bombs++) {
                        int xBomb = localSpecialBombs[bombs][0];
                        int yBomb = localSpecialBombs[bombs][1];

                        if (!grid[xBomb][yBomb].isOpen())
                            for (int i = xBomb - 1; i <= xBomb + 1; i++)
                                if (i >= 0 && i < size)
                                    for (int j = yBomb - 1; j <= yBomb + 1; j++)
                                        if (j >= 0 && j < size)
                                            if (!grid[i][j].isBomb() || !grid[i][j].isOpen())
                                                if (bombsActive)
                                                    grid[i][j].changeStatus(-1);
                                                else
                                                    grid[i][j].changeStatus(1);
                    }

                    bombsActive = !bombsActive;

                }
            }

            if (grid[x][y].getValue() == 0)
                for (int i = x - 1; i <= x + 1; i++)
                    if (i >= 0 && i < size)
                        for (int j = y - 1; j <= y + 1; j++)
                            if (j >= 0 && j < size)
                                if (!grid[i][j].isBomb())
                                    openCell(i, j, false);

        }
    }

    // Método para marcar uma célula com bandeira
    public void flagCell(int x, int y) {
        if (!grid[x][y].isOpen())
            grid[x][y].flag();
    }

    // Método que verifica se o jogador venceu o jogo
    public boolean hasWon() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (grid[x][y].isOpen() && grid[x][y].isActive()) {
                    return false;
                }
            }
        }
        return true;
    }

}
