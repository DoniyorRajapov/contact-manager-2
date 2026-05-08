package group.contactmanager2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class ContactRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int add(Contact contact){
        String sql = "insert into contact_manager(name,surname,phone) values (?,?,?)";
         return jdbcTemplate.update(sql,contact.getName(),contact.getSurname(),contact.getPhone());
    }

    public List<Contact> list() {
        String sql = "select * from contact_manager ORDER BY name ASC";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Contact.class));
    }

    public int delete(String phone) {
        String sql = "delete from contact_manager where phone=?";
        return jdbcTemplate.update(sql, phone);
    }

    public List<Contact> search(String query) {
        String sql = "SELECT * FROM contact_manager WHERE LOWER(name) LIKE ? OR LOWER(surname) LIKE ? OR LOWER(phone) LIKE ? ORDER BY LOWER(name) ASC ";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Contact.class),query,query,query);
    }
}