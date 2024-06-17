/**
 * @author Francisco Luiz Maian do Nascimento - nUSP: 14570890
 * @author Gustavo Cardozo de Moraes Moreira - nUSP: 5244057
 * @author Matheus Rodrigues do Santos - nUSP: 11212866
 */
// Declaração da classe Field que implementa a interface Cell
public class Field implements Cell {
    // Variáveis de instância privadas que representam o estado da célula
    private int value; // Valor da célula, que pode representar o número de bombas ao redor
    private boolean open; // Indica se a célula está aberta
    private boolean flag; // Indica se a célula está marcada com uma bandeira

    /**
     * Constructs a Field object with a specified value.
     *
     * @param value The initial value of the cell.
     */

    // Construtor da classe Field que recebe um valor como parâmetro
    public Field(int value) {
        this.value = value; // Inicializa o valor da célula
        open = false; // Inicializa a célula como fechada
        flag = false; // Inicializa a célula sem bandeira
    }

    /**
     * Checks if the cell is a bomb.
     *
     * @return Always returns false, as this class represents a field cell, not a bomb.
     */

    // Método que verifica se a célula é uma bomba
    public boolean isBomb() {
        return false; // Sempre retorna false, pois esta classe representa uma célula de campo, não uma bomba
    }

    /**
     * Checks if the cell is active.
     *
     * @return Always returns false, as a field cell is not an active bomb.
     */

    // Método que verifica se a célula está ativa
    public boolean isActive() {
        return false; // Retorna false, pois a célula de campo não é uma bomba ativa
    }

    /**
     * Returns the value of the cell.
     *
     * @return The current value of the cell.
     */

    // Método que retorna o valor da célula
    public int getValue() {
        return value; // Retorna o valor da célula
    }

    /**
     * Changes the status of the cell based on the number of special bombs around it.
     *
     * @param specialBombsAround The number of special bombs surrounding this cell.
     */

    // Método que muda o status da célula baseado no número de bombas especiais ao redor
    public void changeStatus(int specialBombsAround) {
        value += specialBombsAround; // Adiciona o número de bombas especiais ao redor ao valor da célula
    }

    /**
     * Checks if the cell is open.
     *
     * @return The open status of the cell.
     */

    // Método que verifica se a célula está aberta
    public boolean isOpen() {
        return open; // Retorna o valor da variável open
    }

    /**
     * Opens the cell, changing its status to open.
     */

    // Método que abre a célula
    public void open() {
        open = true; // Define a célula como aberta
    }

    /**
     * Toggles the flag state of the cell.
     */

    // Método que alterna o estado da bandeira da célula
    public void flag() {
        flag = !flag; // Alterna o valor da variável flag (true para false e vice-versa)
    }

    /**
     * Checks if the cell is flagged.
     *
     * @return The flag status of the cell.
     */

    // Método que verifica se a célula está marcada com uma bandeira
    public boolean isFlagged() {
        return flag; // Retorna o valor da variável flag
    }
}
