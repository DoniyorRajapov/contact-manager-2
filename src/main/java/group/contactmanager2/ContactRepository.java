package group.contactmanager2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository{
    public static final int DUPLICATE_PHONE = -23505;
    public static final int NULL_PHONE = -23502;

    public int add(Contact contact){
        Connection connection = TableRepository.getConnection();
        String sql = "insert into contact_manager(name,surname,phone) values (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,contact.getName());
            preparedStatement.setString(2,contact.getSurname());
            preparedStatement.setString(3,contact.getPhone());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) return DUPLICATE_PHONE;
            if ("23502".equals(e.getSQLState()))return NULL_PHONE;
            else return -1;
        }
    }

    public List<Contact> list() {
        List<Contact> list = new ArrayList<>();
        Connection connection = TableRepository.getConnection();
        String sql = "select * from contact_manager ORDER BY name ASC";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()){
                Contact contact = new Contact();
                contact.setName(resultSet.getString("name"));
                contact.setSurname(resultSet.getString("surname"));
                contact.setPhone(resultSet.getString("phone"));
                list.add(contact);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(String phone) {
        Connection connection = TableRepository.getConnection();
        String sql = "delete from contact_manager where phone=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,phone);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Contact> search(String query) {
        List<Contact> list = new ArrayList<>();
        Connection connection = TableRepository.getConnection();
        String sql = "SELECT * FROM contact_manager WHERE LOWER(name) LIKE ? OR LOWER(surname) LIKE ? OR LOWER(phone) LIKE ? ORDER BY LOWER(name) ASC ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            String q ="%"+query.toLowerCase()+"%";
            preparedStatement.setString(1,q);
            preparedStatement.setString(2,q);
            preparedStatement.setString(3,q);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    Contact contact = new Contact();
                    contact.setName(resultSet.getString("name"));
                    contact.setSurname(resultSet.getString("surname"));
                    contact.setPhone(resultSet.getString("phone"));
                    list.add(contact);
                }
                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}