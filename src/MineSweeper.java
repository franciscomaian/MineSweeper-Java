public class MineSweeper {
    private Cell[][] grid;
    private int totalBombs;

    public MineSweeper(int size) {
        grid = new Cell[size][size];
        totalBombs = size*size/5; //The minesweeper has 20% of cells filled with bombs

        for (int i = 0; i < totalBombs; i++) {
            //Criar as bombas
        }
    }
}
