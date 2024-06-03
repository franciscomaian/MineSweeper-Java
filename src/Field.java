public class Field implements Cell{
    private int value;

    public Field(int value) {
        this.value = value;
    }

    public boolean isBomb() {
        return false;
    }
    public int getValue() {
        return value;
    }

    public void changeValue(int specialBombs) {
        value += specialBombs;
    }
}
