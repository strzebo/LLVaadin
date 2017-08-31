package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    private static int userID = 0;
    private static int userType = 0;

    public static int getUserID() { return userID;  }
    public static int getUserType()  {   return userType;    }

    public static void setUserID(int userID)    {   Main.userID = userID;   }
    public static void setUserType(int userType) {   Main.userType = userType;   }


    public void singUp(String name, String lastName, String email, String password, String address, String phoneNumber, String idNumber) throws SQLException
    {
        DbConnection db = new DbConnection();

        String columns = "Imie, Nazwisko, Email, Haslo, Adres, Telefon, NrDokumentu, TypKonta";
        String value;
        int typKonta = 1;

        //Uzytkownik uzytkownik = new Uzytkownik(name, lastName, email, password, address, phoneNumber, idNumber);

        value = "'" + name + "', '" + lastName + "', '" + email + "', '" + password + "', '" + address + "', '" + phoneNumber + "','" + idNumber + "','" + typKonta + "'";
        db.Insert("uzytkownik",columns,value);
    }

    public boolean logIn(String email, String password) throws SQLException
    {
        DbConnection db = new DbConnection();
        boolean zalogowany = false;

        ResultSet rs = db.Result("ID, TypKonta","uzytkownik","Email = '" + email + "' AND Haslo = '" + password +"'");

        while(rs.next())
        {
            if(rs.getInt("ID") > 0)
            {
                setUserID(rs.getInt("ID"));
                setUserType(rs.getInt("TypKonta"));

                zalogowany = true;
            }
        }
        db.Close();

        return zalogowany;
    }

    public List<Loty> getLotyList() throws SQLException
    {
        DbConnection db = new DbConnection();
        List<Loty> lotyList = new ArrayList<>();

        ResultSet resultSet = db.Result("","lot","");

        while(resultSet.next())
        {
            lotyList.add
                    (
                            new Loty
                                    (
                                            resultSet.getInt("lot.ID"),
                                            resultSet.getString("LotniskoStartowe"),
                                            resultSet.getString("LotniskoDocelowe"),
                                            resultSet.getDate("DataOdlotu"),
                                            resultSet.getDate("DataPrzylotu"),
                                            resultSet.getTime("Odlot"),
                                            resultSet.getTime("Przylot")
                                    )
                    );
        }
        db.Close();

        return  lotyList;
    }

    public void updateData(int ID, String name, String lastName, String email, String password, String address, String phoneNumber, String idNumber, int userType) throws SQLException
    {
        DbConnection db = new DbConnection();

        String set = "Imie = '" + name + "', Nazwisko = '" + lastName + "', Email = '" + email + "', Haslo = '" + password + "', Adres = '" + address + "', Telefon = '" + phoneNumber + "', NrDokumentu = '" + idNumber + "', TypKonta = '" + userType + "'";
        String where = "ID = '" + ID + "'";

        db.Update("uzytkownik",set,where);
    }

    public void rezerwacja(int IDLotu, int IDKlient, String stanRezerwacji) throws SQLException
    {
        DbConnection db = new DbConnection();

        String columns = "IDLotu, IDKlient, StanRezerwacji";
        String value = "'" + IDLotu + "', '" + IDKlient + "', '" + stanRezerwacji + "'";

        db.Insert("rezerwacja",columns,value);
    }

    public List<Rezerwacje> getRezerwacjeList(int IDKlienta, String stanRezerwacji) throws SQLException
    {
        DbConnection db = new DbConnection();
        List<Rezerwacje> rezerwacjeList = new ArrayList<>();
        String select = "ID, IDLotu, IDKlient, StanRezerwacji";
        String where;

        if(stanRezerwacji != "")
        {
            where = "IDKlient = " + IDKlienta + " AND StanRezerwacji = '" + stanRezerwacji + "'";
        }
        else
        {
            where = "IDKlient = " + IDKlienta;
        }

        ResultSet resultSet = db.Result(select,"rezerwacja",where);

        while(resultSet.next())
        {
            rezerwacjeList.add
                    (
                            new Rezerwacje
                                    (
                                            resultSet.getInt("rezerwacja.ID"),
                                            resultSet.getInt("IDLotu"),
                                            resultSet.getInt("IDKlient"),
                                            resultSet.getString("StanRezerwacji")
                                    )
                    );
        }
        db.Close();

        return  rezerwacjeList;
    }

    public void zmianaStanuRezerwacji(int IDLotu, String stanRezerwacji) throws SQLException
    {
        DbConnection db = new DbConnection();

        String set = "StanRezerwacji = '" + stanRezerwacji + "'";
        String where = "ID = '" + IDLotu + "'";

        db.Update("rezerwacja",set,where);
    }

}
