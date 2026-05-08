package group.contactmanager2;

import java.sql.*;

public class TableRepository {
    private static Connection connection;

    public void create(){
        Connection con = getConnection();
        try (Statement statement = con.createStatement()){
            String sql ="create table if not exists contact_manager " +
                    "(name varchar(15) not null, " +
                    "surname varchar(15), " +
                    "phone varchar(12) unique not null)";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection(){
        try {
            if(connection==null)
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contact","user","user");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}