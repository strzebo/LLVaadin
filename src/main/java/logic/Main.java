package logic;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main
{
    //static
    private static ArrayList<User> userList = new ArrayList<>();
    private static User currentUser;

    //get
    public ArrayList<User> getUserList() {   return userList;    }

    public User getCurrentUser() {  return currentUser;    }

    public ArrayList<Sprzedawca> getSprzedawcakList()
    {
        ArrayList<Sprzedawca> sprzedawcaList = new ArrayList<>();

        for(User sprzedawca : userList)
        {
            if (sprzedawca instanceof Sprzedawca)
                sprzedawcaList.add((Sprzedawca) sprzedawca);
        }

        return sprzedawcaList;
    }

    public ArrayList<Uzytkownik> getUzytkownikList()
    {
        ArrayList<Uzytkownik> uzytkownikList = new ArrayList<>();

        for(User uzytkownik : userList)
        {
            if (uzytkownik instanceof Uzytkownik)
                uzytkownikList.add((Uzytkownik) uzytkownik);
        }

        return uzytkownikList;
    }


    //rejestracja -> dodać zapytanie SQL
    public void singUp(String name, String lastName, String email, String password, String address, String phoneNumber, String idNumber) throws SQLException
    {
        DbConnection db = new DbConnection();
        //String boolowska = "";

        String columns = "Imie, Nazwisko, Email, Haslo, Adres, Telefon, NrDokumentu";
        String value;

        Uzytkownik uzytkownik = new Uzytkownik(name, lastName, email, password, address, phoneNumber, idNumber);
        userList.add(uzytkownik);

        value = "'" + name + "', '" + lastName + "', '" + email + "', '" + password + "', '" + address + "', '" + phoneNumber + "','" + idNumber + "'";

        db.Insert("uzytkownik",columns,value);
    }

    public boolean logIn(String email, String password) throws SQLException
    {
        DbConnection db = new DbConnection();

        db.Select("","uzytkownik","Login = '" + email + "' AND Haslo = '" + password +"'");

        return false;

    }

}
