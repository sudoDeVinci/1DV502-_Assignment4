package assignment4;

/**
 * Boat subclass for defining behaviour for sailboats
 */
public class Sailboat extends Boat{
private int depth;

public Sailboat(String[] values) {
  super(values);
  this.depth = Integer.valueOf(values[4]);
}

@Override
public String toString() {
  return ("BOAT:" + this.name + ":sailboat:" + this.length + ":" + this.depth);
}

@Override
public String detailDisplay() {
  String str = "NAME: " + this. name + "\tTYPE: Sailboat\tLENGTH/m: " + this.length + "\tDEPTH/m: " + this.depth;
  return str;
}

public int getDepth() {
  return this.depth;
}

public void setName(int depth) {
  this.depth = depth;
}

}
