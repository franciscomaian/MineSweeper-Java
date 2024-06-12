import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MineSweeper ms = new MineSweeper(5);
        Scanner in = new Scanner(System.in);
        String input, vetor[];
        int x;
        int y;
        boolean flag;

        System.out.printf(ms.toString());

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

            System.out.print(ms.toString());
        }

        System.out.print("Game Over");

    }
}