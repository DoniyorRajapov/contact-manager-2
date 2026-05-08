package group.contactmanager2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class TableRepository {
    @Autowired
    private static Connection connection;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(){
        String sql ="create table if not exists contact_manager " +
                "(name varchar(15) not null, " +
                "surname varchar(15), " +
                "phone varchar(12) unique not null)";
        jdbcTemplate.update(sql);
    }
}