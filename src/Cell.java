/**
 * Interface representing a cell in a game grid.
 *
 * <p>This interface provides methods to interact with the cell, such as checking if it is a bomb,
 * getting its value, checking if it is open or flagged, and changing its status based on special bombs around.
 */
public interface Cell {
    /**
     * Checks if the cell is a bomb.
     *
     * @return true if the cell is a bomb, false otherwise.
     */
    // Método que verifica se a célula é uma bomba
    public boolean isBomb();

    /**
     * Returns the value of the cell.
     *
     * @return the value of the cell.
     */
    // Método que retorna o valor da célula
    public int getValue();

    /**
     * Checks if the cell is open.
     *
     * @return true if the cell is open, false otherwise.
     */
    // Método que verifica se a célula está aberta
    public boolean isOpen();

    /**
     * Opens the cell.
     */
    // Método que abre a célula
    public void open();

    /**
     * Toggles the flag state of the cell.
     */
    // Método que alterna o estado da bandeira da célula
    public void flag();

    /**
     * Checks if the cell is flagged.
     *
     * @return true if the cell is flagged, false otherwise.
     */
    // Método que verifica se a célula está marcada com uma bandeira
    public boolean isFlagged();

    /**
     * Checks if the cell is active.
     *
     * @return true if the cell is active, false otherwise.
     */
    // Método que verifica se a célula está ativa
    public boolean isActive();

    /**
     * Changes the status of the cell based on the number of special bombs around.
     *
     * @param specialBombsAround the number of special bombs around.
     */

    // Método que muda o status da célula baseado no número de bombas especiais ao redor
    public void changeStatus(int specialBombsAround);
}
