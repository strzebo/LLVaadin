package logic;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        int typKonta = 0;

        if (idNumber.startsWith("2S"))
            typKonta = 2;
        else if (idNumber.startsWith("3K"))
            typKonta = 3;
        else if (idNumber.startsWith("4A"))
            typKonta = 4;
        else
            typKonta = 1;



        Uzytkownik uzytkownik = new Uzytkownik(name, lastName, email, password, address, phoneNumber, idNumber);

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

}
