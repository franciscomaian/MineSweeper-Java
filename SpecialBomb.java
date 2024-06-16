public class SpecialBomb implements Cell{
    public boolean active;
    private boolean open;
    private boolean flag;

    public SpecialBomb() {
        active = true;
        open = false;
        flag = false;
    }

    public boolean isBomb() {
        return true;
    }

    public int getValue() {
        return -2;
    }

    public void changeStatus(int specialBombsAround) {
        if (specialBombsAround < 0)
            active = false;
        else
            active = true;
    }

    public boolean isActive() {
        return active;
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