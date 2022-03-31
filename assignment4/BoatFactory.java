package assignment4;

import java.util.*;

/**
 * Factory class for creating boat instances.
 */
public class BoatFactory {

  /**
   * Creates bot instances based on values passed.
   */
  public Boat getInstance(String[] values) {
    // Return Boat instances based on type
    if (values[2].equals("sailboat")) {
      return new Sailboat(values);
    } else if (values[2].equals("canoe")) {
      return new Canoe(values);
    } else if (values[2].equals("motorsailer")) {
      return new Motorsailer(values);
    } else if (values[2].equals("motorboat")) {
      return new Motorboat(values);
    } else return null;
  }   

  /**
   * Check if given String input is integer.
   * Loop repeats with custom message until int is given.
   */
  private String checkStringtoInt (String messagePrompt, Scanner scn) {
    String checkedVar;
    while (true) {
      try { 
        System.out.print(messagePrompt);
        checkedVar = scn.nextLine();
        Integer.parseInt(checkedVar); 
        return checkedVar;
      } catch (NumberFormatException e) { 
        System.out.println("Check input and retry.\n");
        continue; 
      } catch (NullPointerException e) {
        System.out.println("Check input and retry.\n");
        continue;
      }
    }
  }

  /**
   * Prompt for details based on boat type.
   * Create boat insatnces from given details.
   */
  public Boat createBoat(Scanner scn) {
    System.out.print("\n>> Boat Name: ");
    String name = scn.nextLine().toLowerCase();
    String length = checkStringtoInt(">> Boat Length/m: ", scn);
    System.out.print(">> Boat Type: ");
    String type = scn.nextLine().toLowerCase();
    


    // The first index of each value array is taken up by "BOAT"
    // This could've been taken care of, however, it makes little differece when passing values  
    if (type.equals("canoe")) {
      String[] values = {"BOAT",name,type,length};
      Boat b = getInstance(values);
      return b;
        
    } else if (type.equals("sailboat")) {
      String depth = checkStringtoInt("Boat Depth/m: ", scn);

      String[] values = {"BOAT", name, type, length, depth};
      Boat b = getInstance(values);
      return b;

    } else if (type.equals("motorboat")) {
      String engine = checkStringtoInt("Engine Power/hp: ", scn);
      String[] values = {"BOAT", name, type, length, engine};

      Boat b = getInstance(values);
      return b;

    } else if (type.equals("motorsailer")) {
      String engine = checkStringtoInt("Engine Power/hp: ", scn);
      String depth = checkStringtoInt("Boat Depth/m: ", scn);

      String[] values = {"BOAT", name, type, length, depth,engine};
      Boat b = getInstance(values);
      return b;
        
    } else {
      System.out.println("Invalid boat type.");
      return null;
    }
  }
}