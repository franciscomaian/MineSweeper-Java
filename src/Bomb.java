public class Bomb implements Cell{
    public boolean isSpecial = false;

    //Only uses the status if the bomb is a special bomb
    public boolean status = false;

    public Bomb(boolean special) {
        isSpecial = special;
        if (isSpecial)
            status = true;
    }

    public boolean isBomb() {
        return true;
    }

    public int getValue() {
        if (isSpecial)
            return -2;

        return -1;
    }
}
