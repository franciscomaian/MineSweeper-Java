import java.lang.Math;

public class MineSweeper {
    public final double BOMB_PERCENTAGE = 15/100.0;
    public final int SPECIAL_BOMBS = 2;
    public final int ROUNDS_CHANGE_STATUS = 3;

    private Cell[][] grid;
    private int totalBombs;
    private int size;
    private boolean gameOver;
    private int opened;

    //Setar as bombas especiais
    private int counter;
    private boolean bombsActive;
    private int[][] localSpecialBombs;

    public MineSweeper(int size) {
        opened = 0;
        gameOver = false;
        this.size = size;
        grid = new Cell[size][size];
        totalBombs = (int) (size*size*BOMB_PERCENTAGE);

        //Configurando as variaveis das bombas especiais
        counter = 0;
        bombsActive = true;
        localSpecialBombs = new int[SPECIAL_BOMBS][2];

        //Adicioando as bombas normais
        for (int i = 0; i < totalBombs; i++) {
            int x = (int) (size*Math.random());
            int y = (int) (size*Math.random());

            if (grid[x][y] != null)
                i--;
            else
                grid[x][y] = new Bomb();
        }

        //Adicionando as bombas especiais
        for (int i = 0; i < SPECIAL_BOMBS; i++) {
            int x = (int) (size*Math.random());
            int y = (int) (size*Math.random());

            if (grid[x][y] != null)
                i--;
            else {
                grid[x][y] = new SpecialBomb();
                localSpecialBombs[i][0] = x;
                localSpecialBombs[i][1] = y;
            }
        }

        //Definindo os numeros dos campos sem bombas
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                int cont = 0;
                if (grid[x][y] == null) {
                    //Olhando quantas bombas tem ao redor
                    for (int i = x - 1; i <= x + 1; i++) {
                        for (int j = y - 1; j <= y + 1; j++) {
                            if (i >= 0 && j >= 0 && i < size && j < size)
                                if (grid[i][j] != null && grid[i][j].isBomb())
                                    cont++;
                        }
                    }

                    grid[x][y] = new Field(cont);
                }
            }
        }

    }

    public boolean isGameOver() {
        return gameOver;
    }

    public char[][] actualGrid() {
        char[][] ret = new char[size][size];
        for (int x = 0; x < size; x++)
            for (int y = 0; y < size; y++)
                if (grid[x][y].isOpen())
                    if (grid[x][y].isBomb())
                        if (grid[x][y].isActive())
                            ret[x][y] = 'B';
                        else
                            ret[x][y] = 'D';
                    else
                        ret[x][y] = (char)(grid[x][y].getValue() + '0');
                else
                    if (grid[x][y].isFlagged())
                        ret[x][y] = 'F';
                    else
                        ret[x][y] = ' ';

        return ret;
    }

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

    public void flagCell(int x, int y) {
        if (!grid[x][y].isOpen())
            grid[x][y].flag();
    }

}
