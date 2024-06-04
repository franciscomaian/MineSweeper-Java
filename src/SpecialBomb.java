public class SpecialBomb implements Cell{
    public boolean active;

    public SpecialBomb() {
        active = true;
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
}
