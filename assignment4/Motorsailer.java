package assignment4;

/**
 * Boat suclass for defining motorsailer behaviour.
 */
public class Motorsailer extends Boat{
  private int depth;
  private Integer engine;
  
  public Motorsailer(String[] values) {
    super(values);
    this.depth = Integer.valueOf(values[4]);
    this.engine = Integer.valueOf(values[5]);
  }

  @Override
  public String toString() {
    return ("BOAT:" + this.name + ":motorsailer:" + this.length + ":" + this.depth + ":" + this.engine);
  }

  @Override
  public String detailDisplay() {
    String str = "NAME: " + this. name + "\tTYPE: MotorSailer\tLENGTH/m: " + this.length + "\tDEPTH/m: " + this.depth + "\tENGINE PWR/hp: " + this.engine;
    return str;
  }

  public float getDepth() {
    return this.depth;
  }

  public void setDepth(int depth) {
    this.depth = depth;
  }

  public int getEngine() {
    return this.engine;
  }

  public void setName(int engine) {
    this.engine = engine;
  }
}
