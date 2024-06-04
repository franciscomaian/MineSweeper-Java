public class SpecialBomb implements Cell{
    public boolean active;
    private boolean open;

    public SpecialBomb() {
        active = true;
        open = false;
    }

    public boolean isBomb() {
        return true;
    }

    public int getValue() {
        return -2;
    }

    public void changeStatus() {
        active = !active;
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
}
