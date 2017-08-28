package logic;

import java.sql.*;


public class DbConnection
{
    private Connection connection;
    private Statement statement;
    private String cm = "";

    private void Initialize()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://165.227.148.102:3306/toloto", "toloto", "ToLotoWysoko$4");
            statement = connection.createStatement();
        }
        catch(Exception ex)
        {
            System.out.println("Error : " + ex);
        }
    }

    /*
    public void Select(String select, String from, String where) throws SQLException
    {
        Initialize();

        if(select.isEmpty())
            select = "*";

        if(where.isEmpty())
            where = "1";

        cm = "SELECT " + select + " FROM " + from + " WHERE " + where;
        statement.execute(cm);
        //ResultSet rs = statement.executeQuery(cm);

        this.Close();
    }
    */

    public ResultSet Result(String select, String from, String where) throws SQLException
    {
        Initialize();

        if(select.isEmpty())
            select = "*";

        if(where.isEmpty())
            where = "1";

        cm = "SELECT " + select + " FROM " + from + " WHERE " + where;

        return statement.executeQuery(cm);
    }

    public void Insert(String into, String column, String value)
    {
        try {
            Initialize();

            cm = "INSERT INTO " + into + " (" + column + ") VALUES (" + value + ")";
            statement.executeUpdate(cm);

            statement.close();
            this.Close();
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

            this.Close();
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

    public void Close() throws SQLException
    {
        connection.close();
    }
}
