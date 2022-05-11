package TriviaMaze;
import java.sql.*;
import java.util.HashMap;

import TriviaMaze.Question.Question;
import TriviaMaze.Question.TrueFalseType;
import org.sqlite.SQLiteDataSource;


public class Database
{
    private static Database myInstance = new Database();
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private final SQLiteDataSource dataSource = new SQLiteDataSource();

    Database()
    {
        try
        {
            connect();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
    public static Database getInstance()
    {
        return myInstance;
    }

    public void connect() throws SQLException
    {
        dataSource.setUrl("jdbc:sqlite:trivia.db");
        conn = dataSource.getConnection();
    }
    public int getRandomID()
    {
        int id = 0;
        try
        {
            stmt = this.conn.createStatement();
            rs = this.stmt.executeQuery("SELECT * FROM truefalse ORDER BY RANDOM() LIMIT 1;");
            rs.getString("question");
            while (rs.next())
            {
                id = rs.getInt("answer");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return id;
    }
    public Question getTrueFalseQuestion()
    {
        Question question;
        String qBody = null;
        String answer = null;
        try
        {
            stmt = this.conn.createStatement();
            rs = this.stmt.executeQuery("SELECT * FROM truefalse ORDER BY RANDOM() LIMIT 1;");
             qBody = rs.getString("question");
             answer = rs.getString("correctbool");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return new TrueFalseType(qBody, answer);
    }
    public int getMultipleChoice()
    {
        return 0;
    }

}
