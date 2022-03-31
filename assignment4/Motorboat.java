package assignment4;

public class Motorboat extends Boat{
    private int engine;
    
    public Motorboat(String[] values) {
        super(values);
        this.engine = Integer.valueOf(values[4]);
    }

    @Override
    public String toString() {
        return ("BOAT:" + this.name + ":motorboat:" + this.length + ":" + this.engine);
    }

    @Override
    public String detailDisplay() {
        String str = "NAME: " + this. name + "\tTYPE: MotorBoat\tLENGTH/m: " + this.length + "\tENGINE PWR/hp: " + this.engine;
        return str;
    }

    public int getEngine() {
        return this.engine;
    }

    public void setName(int engine) {
        this.engine = engine;
    }
}
