package assignment4;

import java.util.*;

public class Member{
    private String id;
    private String name;
    private String email;
    private ArrayList<Boat> boatList = new ArrayList<Boat>();

    public Member(String name, String email, String id){
        this.name = name;
        if (email == null){
            email = "";
        }
        this.email = email;
        this.id = id;
    }

    public String detailDisplay() {
        String str;
        if (email.equals("")){
            str = "| NAME: " + this.name + "\tEMAIL: N/A"  + "\tUSER ID: " + this.id;
        } else {
            str = "| NAME: " + this.name + "\tEMAIL: " +  this.email + "\tUSER ID: " + this.id;
        }
        return str;
    }

    public ArrayList<Boat> getBoats() {
        ArrayList<Boat> retBoat = new ArrayList<Boat>(this.boatList);
        return retBoat;
    }

    public String toString() {
        return ("MEMBER:" + this.name + ":" + this.email + ":" + this.id);
    }

    public void addBoat(Boat boat) {
        boatList.add(boat);
    }

    public void removeBoat(Boat b) {
        boatList.remove(b);
    }

    public String getName() {
        String retname = this.name;
        return retname;
    }

    public String getEmail() {
        String retEmail = email;
        return retEmail;
    }

    public String getId() {
        String retId = getId();
        return retId;
    }
}
