package assignment4;

import java.util.Scanner;

public class Display {
  public void viewMembers(MemberRegistry mReg) {
    System.out.println("\n┌───────────────────────┐\n|     MEMBER LIST\t│\n├───────────────────────┐");
    System.out.println(mReg.listMembers());
    System.out.println("└───────────────────────┘");
  }

  public void registryOptionsMenu() {
    System.out.println("┌───────────────────────┐\n|     REGISTRY OPTIONS\t│\n├───────────────────────┐");
    System.out.println("| [1] List all Members\n| [2] Select Member\n| [3] Create Member\n| [4] QUIT");
    System.out.println("└───────────────────────┘");
    System.out.println("Input the number of the menu option you want below.");

  }

  public void memberOptionsMenu() {
    System.out.println("┌───────────────────────┐\n|     MEMBER OPTIONS\t│\n├───────────────────────┐");
    System.out.println("│ [1] Delete Member\n│ [2] Add Boat\n│ [3] Select Boat\n│ [4] Go Back");
    System.out.println("└───────────────────────┘");
    System.out.println("Input the number of the menu option you want below.");
  }

  public void boatOptionsMenu() {
    System.out.println("┌───────────────────────┐\n|     BOAT OPTIONS\t│\n├───────────────────────┐");
    System.out.println("│ [1] Delete Boat\n│ [2] Go Back");
    System.out.println("└───────────────────────┘");
  }


  public String inputPrompt(String message, Scanner scn) {
    System.out.print(message);
    return scn.nextLine().strip();
}

  public void viewBoats(MemberRegistry mReg, int mIndex) {
    System.out.println("├──────────────────────────────────────────────────────────────────────");
    String boats = mReg.listBoats(mIndex);
    if (boats != null) {
      System.out.println(boats);
    }
    System.out.println("└───────────────────────┘");
  }


  public void viewMemberDetail(MemberRegistry mReg, int mIndex) {
    System.out.println("┌───────────────────────┐\n|     MEMBER VIEW\t│\n├───────────────────────┘");
    System.out.println(mReg.getMember(mIndex).detailDisplay());
  }


  public void viewBoatDetail(int mIndex, int bIndex, MemberRegistry mreg, BoatFactory boatFactory) {
    System.out.println("\n------BOAT DETAILS-------");
    Member currentMem = mreg.getMember(mIndex);
    if (currentMem != null) {
      try{
        Boat boat = currentMem.getBoats().get(bIndex-1);
        System.out.println(boat.detailDisplay());
      } catch (IndexOutOfBoundsException e) {
        System.out.println("No boat of that number exists for " + currentMem.getName() + "\n");
      }
    }
  }

  public void makeBoat(int index, MemberRegistry mreg, Scanner scn, BoatFactory boatFactory) {
    System.out.println("\n------INSERTING BOAT-------");
    Member currentMem = mreg.getMember(index);
    if (currentMem != null) {
      Boat newBoat = boatFactory.createBoat(scn);
      currentMem.addBoat(newBoat);
    }
  }

}
