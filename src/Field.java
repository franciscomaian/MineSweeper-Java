// Declaração da classe Field que implementa a interface Cell
public class Field implements Cell {
    // Variáveis de instância privadas que representam o estado da célula
    private int value; // Valor da célula, que pode representar o número de bombas ao redor
    private boolean open; // Indica se a célula está aberta
    private boolean flag; // Indica se a célula está marcada com uma bandeira

    // Construtor da classe Field que recebe um valor como parâmetro
    public Field(int value) {
        this.value = value; // Inicializa o valor da célula
        open = false; // Inicializa a célula como fechada
        flag = false; // Inicializa a célula sem bandeira
    }

    // Método que verifica se a célula é uma bomba
    public boolean isBomb() {
        return false; // Sempre retorna false, pois esta classe representa uma célula de campo, não uma bomba
    }

    // Método que verifica se a célula está ativa
    public boolean isActive() {
        return false; // Retorna false, pois a célula de campo não é uma bomba ativa
    }

    // Método que retorna o valor da célula
    public int getValue() {
        return value; // Retorna o valor da célula
    }

    // Método que muda o status da célula baseado no número de bombas especiais ao redor
    public void changeStatus(int specialBombsAround) {
        value += specialBombsAround; // Adiciona o número de bombas especiais ao redor ao valor da célula
    }

    // Método que verifica se a célula está aberta
    public boolean isOpen() {
        return open; // Retorna o valor da variável open
    }

    // Método que abre a célula
    public void open() {
        open = true; // Define a célula como aberta
    }

    // Método que alterna o estado da bandeira da célula
    public void flag() {
        flag = !flag; // Alterna o valor da variável flag (true para false e vice-versa)
    }

    // Método que verifica se a célula está marcada com uma bandeira
    public boolean isFlagged() {
        return flag; // Retorna o valor da variável flag
    }
}
