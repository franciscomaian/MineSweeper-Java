// Declaração da classe SpecialBomb que implementa a interface Cell
public class SpecialBomb implements Cell {
    // Variáveis de instância
    public boolean active; // Indica se a bomba especial está ativa
    private boolean open; // Indica se a célula está aberta
    private boolean flag; // Indica se a célula está marcada com uma bandeira

    // Construtor da classe SpecialBomb
    public SpecialBomb() {
        active = true; // Inicializa a bomba especial como ativa
        open = false; // Inicializa a célula como fechada
        flag = false; // Inicializa a célula sem bandeira
    }

    // Método que verifica se a célula é uma bomba
    public boolean isBomb() {
        return true; // Retorna true, pois esta classe representa uma bomba
    }

    // Método que retorna o valor da célula
    public int getValue() {
        return -2; // Retorna -2 para indicar que esta célula é uma bomba especial
    }

    // Método que muda o status da bomba especial baseado no número de bombas especiais ao redor
    public void changeStatus(int specialBombsAround) {
        if (specialBombsAround < 0)
            active = false; // Desativa a bomba se o número de bombas especiais ao redor for negativo
        else
            active = true; // Ativa a bomba se o número de bombas especiais ao redor for não negativo
    }

    // Método que verifica se a célula está ativa
    public boolean isActive() {
        return active; // Retorna o valor da variável active
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
