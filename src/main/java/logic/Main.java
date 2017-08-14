package logic;

import java.util.ArrayList;

public class Main
{
    //static
    private static ArrayList<User> userList = new ArrayList<>();
    private static User currentUser;

    //get
    public ArrayList<User> getUserList() {   return userList;    }

    public User getCurrentUser() {  return this.currentUser;    }

    public ArrayList<Pracownik> getPracownikList()
    {
        ArrayList<Pracownik> pracownikList = new ArrayList<>();

        for(User pracownik : userList)
        {
            if (pracownik instanceof Pracownik)
                pracownikList.add((Pracownik) pracownik);
        }

        return pracownikList;
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
    public void singUp(String name, String lastName, String email, String login, String password, String type)
    {
        DbConnection db = new DbConnection();
        String boolowska = "";

        String columns = "Imie, Nazwisko, Login, Haslo, Email" + boolowska;
        String value = "";

        if(type.equals("Użytkownik"))
        {
            Uzytkownik uzytkownik = new Uzytkownik(name, lastName, email, login, password);
            userList.add(uzytkownik);

            value = "'" + name + "','" + lastName + "','" + email + "','" + login + "','" + password + "','" + email + "','" + "1";
        }
        else
        {
            Pracownik pracownik = new Pracownik(name, lastName, email, login, password);
            userList.add(pracownik);

            value = "'" + name + "','" + lastName + "','" + email + "','" + login + "','" + password + "','" + email + "','" + "0";
        }

        try
        {
            db.Insert("uzytkownik",columns,value);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
