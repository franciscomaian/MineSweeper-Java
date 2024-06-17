/**
 * Class representing a Bomb cell in a Minesweeper game.
 * Implements the Cell interface.
 */
public class Bomb implements Cell {
    // Variáveis de instância privadas que representam o estado da célula
    /**
     * Indicates if the cell is open.
     */
    private boolean open; // Indica se a célula está aberta

    /**
     * Indicates if the cell is flagged.
     */
    private boolean flag; // Indica se a célula está marcada com uma bandeira

    /**
     * Constructor for the Bomb class.
     * Initializes the cell as closed and unflagged.
     */
    public Bomb() {
        open = false; // Inicializa a célula como fechada
        flag = false; // Inicializa a célula sem bandeira
    }

    /**
     * Indicates if the cell is a bomb.
     * @return true, since this class represents a bomb
     */
    public boolean isBomb() {
        return true; // Sempre retorna true, pois esta classe representa uma bomba
    }

    /**
     * Indicates if the cell is active.
     * @return true, which can mean the bomb is present and active
     */
    public boolean isActive() {
        return true; // Retorna true, o que pode significar que a bomba está presente e ativa
    }

    /**
     * Returns the value of the cell.
     * @return -1, which can be used to indicate that this cell is a bomb
     */
    public int getValue() {
        return -1; // Retorna -1, que pode ser usado para indicar que esta célula é uma bomba
    }

    /**
     * Checks if the cell is open.
     * @return the value of the open variable
     */
    public boolean isOpen() {
        return open; // Retorna o valor da variável open
    }

    /**
     * Opens the cell.
     * Sets the cell as open.
     */
    public void open() {
        open = true; // Define a célula como aberta
    }

    /**
     * Toggles the flag state of the cell.
     * Changes the flag value (true to false and vice-versa).
     */
    public void flag() {
        flag = !flag; // Alterna o valor da variável flag (true para false e vice-versa)
    }

    /**
     * Checks if the cell is flagged.
     * @return the value of the flag variable
     */
    public boolean isFlagged() {
        return flag; // Retorna o valor da variável flag
    }

    /**
     * Changes the status of the cell, but does nothing for a normal bomb.
     * @param specialBombsAround the number of special bombs around this cell
     */
    public void changeStatus(int specialBombsAround) {
        // Bomba normal não muda de status, então este método é vazio
    }
}
