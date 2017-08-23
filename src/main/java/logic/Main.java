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
    public void singUp(String name, String lastName, String email, String login, String password, String type) throws SQLException
    {
        DbConnection db = new DbConnection();
        String boolowska = "";

        String columns = "Imie, Nazwisko, Login, Haslo, Email" + boolowska;
        String value = "";

        switch(type)
        {
            case "Użytkownik":
                Uzytkownik uzytkownik = new Uzytkownik(name, lastName, email, login, password);
                userList.add(uzytkownik);

                value = "'" + name + "','" + lastName + "','" + email + "','" + login + "','" + password + "','" + email + "','" + "1";
                break;
            case "Sprzedawca":
                Sprzedawca sprzedawca = new Sprzedawca(name, lastName, email, login, password);
                userList.add(sprzedawca);

                value = "'" + name + "','" + lastName + "','" + email + "','" + login + "','" + password + "','" + email + "','" + "0";
                break;
            case "Kieroniwk":
                Kierownik kierownik = new Kierownik(name, lastName, email, login, password);
                userList.add(kierownik);

                value = "'" + name + "','" + lastName + "','" + email + "','" + login + "','" + password + "','" + email + "','" + "0";
                break;
            case "Administrator":
                Administrator administrator = new Administrator(name, lastName, email, login, password);
                userList.add(administrator);

                value = "'" + name + "','" + lastName + "','" + email + "','" + login + "','" + password + "','" + email + "','" + "0";
                break;
            default:
                return;
        }

           db.Insert("uzytkownik",columns,value);
            //db.Select("");
    }

    public boolean logIn(String login, String password) throws SQLException
    {
        DbConnection db = new DbConnection();

        db.Select("","uzytkownik","Login = '" + login + "' AND Haslo = '" + password +"'");

        return false;

    }

}
