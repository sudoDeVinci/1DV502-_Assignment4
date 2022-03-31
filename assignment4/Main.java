package assignment4;
import java.util.*;
import java.io.*;


public class Main {
  public static void main(String[] args) {
    // Initialize the display class 
    Display dis = new Display();

    //Initialize a Boat Factory to use 
    BoatFactory boatFactory = new BoatFactory();

    // Initialize scanner and path
    Scanner scn = new Scanner(System.in);
    String path =  "app\\src\\main\\java\\assignment4\\register.data";
    File file = new File(path);

    // Rather than directly accessing member data, it is done through the Member Registry
    MemberRegistry mReg = new MemberRegistry();

    // Registry object is populated with data
    int readStatus = mReg.readIn(file, boatFactory);
    if (readStatus == 2) {
      System.exit(1);
    }

    // Index of both Member & Boat selected
    int mIndex;
    int bIndex;

    // Current member and boat being worked with
    Member currentMember;
    Boat currentBoat;

    // String selection
    String selection;

    mainloop : while (true) {
      dis.registryOptionsMenu();
      selection = dis.inputPrompt("\n>> Option Number: ", scn);

      switch(selection) {
        // List all current Members
        case "1":
          dis.viewMembers(mReg);
          break;

        // Select a specific Member
        case "2":
          if (mReg.getMember(1) == null) {
            System.out.println("\nUnable to select Member, as none currently exist.\n");
            break;
          }
          getMemberIndex : while(true) {

            System.out.print("\n>> Member Number: ");
            String mSelect = scn.nextLine();
            try{
              mIndex = Integer.parseInt(mSelect);
              currentMember = mReg.getMember(mIndex);
              if (currentMember != null) {
                break getMemberIndex;
              }
            } catch (NumberFormatException e) {
              System.out.println("\nInput not a valid number. Retry.");
            }
          }

          dis.viewMemberDetail(mReg, mIndex);
          dis.viewBoats(mReg, mIndex);

          // New loop for member options
          memberOptionsLoop : while(true) {
            dis.memberOptionsMenu(); 
            selection = dis.inputPrompt("\n>> Option Number: ", scn);

            switch(selection) {
              // Delete selected Member and go back.
              case "1":
                mReg.removeMember(currentMember);
                break memberOptionsLoop;

              // Add boat to the selected member and keep looping.
              case "2":
                currentBoat = boatFactory.createBoat(scn);
                currentMember.addBoat(currentBoat);
                dis.viewBoats(mReg, mIndex);
                break;

              // Select boat of current member, give option to delete, keep looping after
              case "3":
                if (currentMember.getBoats().size() == 0) {
                  System.out.println("\nThis Member has no boats.");
                  break;
                }

                System.out.println("Input the number of the boat you wish to view");

                getBoatIndex : while(true) {
                  String bSelect = dis.inputPrompt("\n>> Boat Number: ", scn);
                  try{
                    bIndex = Integer.parseInt(bSelect);
                    currentBoat = currentMember.getBoats().get(bIndex-1);
                    if (currentBoat != null) {
                      break getBoatIndex;
                    }
                  } catch (NumberFormatException e) {
                    System.out.println("\nInput not a valid number. Retry.");
                  } catch (IndexOutOfBoundsException e) {
                    System.out.println("\nNo boat with that number.");
                  }
                }

                dis.viewBoatDetail(mIndex, bIndex, mReg, boatFactory);

                boatOptionsLoop : while (true) {
                  dis.boatOptionsMenu();
                  selection = dis.inputPrompt("\n>> Option Number: ", scn);

                  switch(selection) {
                    case "1":
                      currentMember.removeBoat(currentBoat);
                      System.out.println("\nBoat removed successfully\n");
                      dis.viewBoats(mReg, mIndex);
                      break boatOptionsLoop;
                    
                    case "2":
                      break boatOptionsLoop;

                    default:
                      System.out.println("\nPlease select a valid choice.");
                      break;
                  }
                }

                break;
              
              // Go back to main loop
              case "4":
                break memberOptionsLoop;
              
              default:
              System.out.println("\nPlease select a valid choice.");
              break;
            }
          }

          break;
        // Create a member then continue loop
        case "3":
          String newName = dis.inputPrompt("\n>> New Member Name: ", scn);
          String opEmail = dis.inputPrompt("\n>> Optional Email Address: ", scn);

          if (opEmail.equals("")) {
            //do nothing
          } else {
            while (mReg.verifyEmail(opEmail) == false) {
              System.out.print("\nOptional Email Address: ");
              opEmail = scn.nextLine().strip();
            }
          }
          
          String currentId = mReg.createId();
          
          Member newMem = new Member(newName, opEmail, currentId);
          mReg.addMember(newMem);
          dis.viewMembers(mReg);
        
          break;

        // Quit program
        case "4":
        scn.close();
        mReg.savefile(file, mReg);
          break mainloop;
        
        default:
          System.out.println("Please select a valid choice.");
          break;
      }
    }
  }
}
