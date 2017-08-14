package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DbConnection
{
    private Connection connection;
    private Statement statement;

    private String cm = "";
    private String server = "";
    private String port = "80";
    private String database = "toloto";
    private String login = "toloto";
    private String password = "LysiaCzek";

    public void Initialize()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://178.62.84.129:80/toloto", login, password);
            statement = connection.createStatement();
        }
        catch(Exception ex)
        {
            System.out.println("Error : " + ex);
        }
    }

    public void Insert(String into, String column, String value)
    {
        try {
            Initialize();

            cm = "INSERT INTO " + into + " (" + column + ") VALUES (" + value + ")";
            statement.executeUpdate(cm);

            statement.close();
            connection.close();
        }
        catch(SQLException ex)
        {
            System.out.println("Error : " + ex);
        }
    }

    public void Update(String update, String set, String where)
    {
        try
        {
            Initialize();
            cm = "UPDATE " + update + " SET " + set + " WHERE " + where;

            statement.executeUpdate(cm);

            connection.close();
        }
        catch(SQLException ex)
        {
            System.out.println("Error : " + ex);
        }
    }

    public void Delete(String from, String where, String delete)
    {
        try
        {
            Initialize();
            cm = "DELETE " + delete + " FROM " + from + " WHERE " + where;

            statement.executeUpdate(cm);
        }
        catch(SQLException ex)
        {
            System.out.println("Error : " + ex);
        }
    }
}
