package logic;

import logic.Main;

import java.sql.SQLException;

public class Uzytkownik extends User
{
    int ID;

    public Uzytkownik(int ID, String name, String lastName, String email, String password, String address, String phoneNumber, String idNumber, int typKonta)
    {
        super(name, lastName, email, password, address, phoneNumber, idNumber, typKonta);
        this.ID =ID;
    }

    public int getID() {   return ID;    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
