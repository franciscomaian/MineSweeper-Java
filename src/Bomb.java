// Declaração da classe Bomb que implementa a interface Cell
public class Bomb implements Cell {
    // Variáveis de instância privadas que representam o estado da célula
    private boolean open; // Indica se a célula está aberta
    private boolean flag; // Indica se a célula está marcada com uma bandeira

    // Construtor da classe Bomb
    public Bomb() {
        open = false; // Inicializa a célula como fechada
        flag = false; // Inicializa a célula sem bandeira
    }

    // Método que indica se a célula é uma bomba
    public boolean isBomb() {
        return true; // Sempre retorna true, pois esta classe representa uma bomba
    }

    // Método que indica se a célula está ativa
    public boolean isActive() {
        return true; // Retorna true, o que pode significar que a bomba está presente e ativa
    }

    // Método que retorna o valor da célula
    public int getValue() {
        return -1; // Retorna -1, que pode ser usado para indicar que esta célula é uma bomba
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

    // Método que muda o status da célula, mas não faz nada para uma bomba normal
    public void changeStatus(int specialBombsAround) {
        ; // Bomba normal não muda de status, então este método é vazio
    }
}
