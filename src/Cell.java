public interface Cell {

    public boolean isBomb();
    public int getValue();
    public boolean isOpen();
    public void open();
    public void flag();
    public boolean isFlagged();
    public boolean isActive();
    public void changeStatus(int specialBombsAround);
}