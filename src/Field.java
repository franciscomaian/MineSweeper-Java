public class Field implements Cell{
    private int value;
    private boolean open;

    public Field(int value) {
        this.value = value;
        open = false;
    }

    public boolean isBomb() {
        return false;
    }
    public int getValue() {
        return value;
    }

    public void changeValue(int specialBombsAround) {
        value += specialBombsAround;
    }

    public boolean isOpen() {
        return open;
    }

    public void open() {
        open = true;
    }
}
