import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MineSweeper ms = new MineSweeper(5);
        Scanner in = new Scanner(System.in);
        String input, vetor[];
        int x;
        int y;
        boolean flag;
        char[][] getGrid;

        getGrid = ms.actualGrid();

        for (int i = 0; i < getGrid.length; i++) {
            for (int j = 0; j < getGrid[0].length; j++)
                System.out.print("[" + getGrid[i][j] + "]");

            System.out.println();
        }

        while (!ms.isGameOver()) {
            input = in.nextLine();
            vetor = input.split(" ");
            x = Integer.parseInt(vetor[0]);
            y = Integer.parseInt(vetor[1]);

            flag = vetor[2].equals("f");

            if (flag)
                ms.flagCell(x, y);
            else
                ms.openCell(x, y, true);

            getGrid = ms.actualGrid();

            for (int i = 0; i < getGrid.length; i++) {
                for (int j = 0; j < getGrid[0].length; j++)
                    System.out.print("[" + getGrid[i][j] + "]");

                System.out.println();
            }
        }

        System.out.print("Game Over");

    }
}