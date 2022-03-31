package assignment4;
import java.io.*;
import java.util.*;

public class MemberRegistry {

    private ArrayList<Member> mList = new ArrayList<Member>();

    // Method to read in all member data and populate Member Registry instance 
    public int readIn(File file, BoatFactory boatFactory) {

        // Try to read Member data.
        try {
            Scanner scn = new Scanner(file);
            // In a populated file, the first line should always be a member. 
            
            if ((scn.hasNextLine()) == false) {
                System.out.println("File is empty");
                scn.close();
                return 1;
            }
            String line = scn.nextLine();
            String[] values = line.split(":");

            // If first line is not member data then break try and return 1.
            if (values[0].equals("MEMBER") == false) {
                System.out.println("First entry is not Member data.\nPlease check data file format.");
                scn.close();
                return 2;
            }

            Member currentMember = new Member(values[1], values[2], values[3]);
            addMember(currentMember);

            while(scn.hasNext()) {
                line = scn.nextLine();
                values = line.split(":");

                if (values[0].equals("MEMBER")) {
                currentMember = new Member(values[1], values[2], values[3]);
                //System.out.println(currentMember.getName());
                addMember(currentMember);
                
                } else if (values[0].equals("BOAT")) {
                Boat currentBoat = boatFactory.getInstance(values);
                //System.out.println("└" + currentBoat.toString());
                currentMember.addBoat(currentBoat);
                }
            }
            // If data read correctly, return 0.
            scn.close();
            return 0;
        } catch(FileNotFoundException e) {
            System.out.println("No file found, creating."); return 1;
        }
    }

    
    public String stringRep(){
        StringBuilder str = new StringBuilder();
        for (Member m : mList) {
           str.append(m.toString() + "\n");
           for (Boat b : m.getBoats()) {
               str.append(b.toString() + "\n");
           }
        }

        String outstring = str.toString();
        return outstring;
    }

    
    public void addMember(Member m){
        this.mList.add(m);
    }

    
    public void removeMember(Member m){
        this.mList.remove(m);
    }

    
    public boolean verifyId(String id) {

        for (Member m : mList) {
            if (id.equals(m.getId())) {
                return false;
            }
        }
        return true;
    }

    
    public boolean verifyEmail(String email) {
        boolean emailValidity;

        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        emailValidity = email.matches(regex);
        if (emailValidity == false) {
            System.out.println("\nPlease check email format and try again\n");
            return false;
        }


        for (Member m : mList) {
            if (email.equals(m.getEmail())) {
                System.out.println("\nThis email is already in use by another member.\n");
                return false;
            }
        }
        return true;
    }

    public String listMembers() {
        StringBuilder str = new StringBuilder();

        if (mList.size() == 0) {
            return "- No Members currently exist.";
        }

        for (int i = 0, j = 1; i < mList.size(); i++, j++) {
           str.append("| [" + j + "] " + mList.get(i).getName() + "\n");
        }
        String outstring = str.toString();
        return outstring;
    }

    public String listBoats(int index) {
        try {
            Member m = mList.get(index-1);
            System.out.println("| BOATS OF: " + m.getName() + "\n" + "├─────────────────────────────");
            
            try{
                ArrayList<Boat> boats = m.getBoats();
                if (boats.size() == 0) {
                    System.out.println("|  └ No boats listed");
                    return null;
                }
                StringBuilder str = new StringBuilder();

                for (int i = 0, j = 1; i < boats.size(); i++, j++) {
                    str.append("|  └ [" + j + "] " + boats.get(i).getName()+"\n");
                }

                String outstring = str.toString();
                return outstring;
            } catch (NullPointerException e) {
                System.out.println("- No boats listed");
                return null;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Chosen member does not exist, please try again");
            return null;}
    }

    public Member getMember(int index) {
        try {
            Member m = mList.get(index-1);
            return m;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Chosen member does not exist, please try again");
            return null;}
    }

    public String createId() {
        Random rand = new Random();

        String [] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        StringBuilder str = new StringBuilder();

        while (true) {

            for (int i = 0; i < 6; i++) {
                int idchar = rand.nextInt(letters.length);
                str.append(letters[idchar]);
            }

            String stringOut = str.toString();

            if (verifyId(stringOut) == true) {
                return stringOut;
            } else {
                str = null;
            }
        }
       
    }

    public void savefile(File file, MemberRegistry mReg) {
        try{
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            } else {
        
            }
        
            FileWriter fwriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bwriter = new BufferedWriter(fwriter);
            bwriter.write(mReg.stringRep());
            bwriter.close();
        
            System.out.println("\nChanges Saved.");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}