package assignment4;

/**
 * Abstract class to define behaviour for boat subclasses.
 */
public abstract class Boat {
  protected String name;
  protected int length;

  public abstract String toString();

  public abstract String detailDisplay(); 

  public Boat(String[] values) {
    this.length = Integer.valueOf(values[3]);
    this.name = values[1];
  }

  public int getLength() {
    int retLength = this.length;
    return retLength;
  }

  public String getName() {
    String retName = this.name;
    return retName;
  }
}
