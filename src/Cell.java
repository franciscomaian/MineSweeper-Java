// Declaração da interface Cell
public interface Cell {
    // Método que verifica se a célula é uma bomba
    public boolean isBomb();

    // Método que retorna o valor da célula
    public int getValue();

    // Método que verifica se a célula está aberta
    public boolean isOpen();

    // Método que abre a célula
    public void open();

    // Método que alterna o estado da bandeira da célula
    public void flag();

    // Método que verifica se a célula está marcada com uma bandeira
    public boolean isFlagged();

    // Método que verifica se a célula está ativa
    public boolean isActive();

    // Método que muda o status da célula baseado no número de bombas especiais ao redor
    public void changeStatus(int specialBombsAround);
}
