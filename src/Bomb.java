public class Bomb implements Cell{

    public Bomb() {

    }

    public boolean isBomb() {
        return true;
    }

    public int getValue() {
        return -1;
    }
}
