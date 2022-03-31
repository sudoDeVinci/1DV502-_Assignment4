package assignment4;

/**
 * Boat subclass used to define canoes
 */
public class Canoe extends Boat {
  public Canoe(String[] values){
    super(values);
  }

  @Override
  public String toString() {
    return ("BOAT:" + this.name + ":canoe:" + this.length);
  }

  @Override
  public String detailDisplay() {
    String str = "NAME: " + this. name + "\tTYPE: Canoe\tLENGTH/m: " + this.length;
    return str;
  }
}
