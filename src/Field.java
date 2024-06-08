public class Field implements Cell{
    private int value;
    private boolean open;
    private boolean flag;

    public Field(int value) {
        this.value = value;
        open = false;
        flag = false;
    }

    public boolean isBomb() {
        return false;
    }

    public boolean isSpecial() {
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

    public void flag() {
        flag = !flag;
    }

    public boolean isFlagged() {
        return flag;
    }
}
