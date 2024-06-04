public class Bomb implements Cell{
    private boolean open;

    public Bomb() {
        open = false;
    }

    public boolean isBomb() {
        return true;
    }

    public int getValue() {
        return -1;
    }

    public boolean isOpen() {
        return open;
    }

    public void open() {
        open = true;
    }
}
