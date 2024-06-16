
public class Bomb implements Cell{
    private boolean open;
    private boolean flag;

    public Bomb() {
        open = false;
        flag = false;
    }

    public boolean isBomb() {
        return true;
    }

    public boolean isActive() {
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

    public void flag() {
        flag = !flag;
    }

    public boolean isFlagged() {
        return flag;
    }

    public void changeStatus(int specialBombsAround) {
        ; //A bomba normal nao muda de status
    }
}
