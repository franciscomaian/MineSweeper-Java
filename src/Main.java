import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MineSweeper ms = new MineSweeper(5);
        Scanner in = new Scanner(System.in);
        int x;
        int y;

        System.out.printf(ms.toString());

        while (!ms.isGameOver()) {
            x = in.nextInt();
            y = in.nextInt();

            ms.openCell(x, y);

            System.out.print(ms.toString());
        }

        System.out.print("Game Over");

    }
}