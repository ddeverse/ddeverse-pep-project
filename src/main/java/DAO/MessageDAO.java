package DAO;

import Model.Account;
import Model.Message;
import Util.ConnectionUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    /**
     * TODO: retrieve all messages from the message table.
     * @return all messages.
     */
    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM message;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int mess_id = rs.getInt("message_id");
                int post_id = rs.getInt("posted_by");
                String text = rs.getString("message_text");
                Long epoch = rs.getLong("time_posted_epoch");
                Message message = new Message(mess_id, post_id, text, epoch);
                messages.add(message);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }

    /**
     * TODO: create a new message.
     * message_text should not be blank, and must be under 255 characters
     * posted_by refers to a real person, new message gets put into message table.
     */
    public Message newMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String checkSql = "SELECT * FROM account WHERE account_id = ?;";
            PreparedStatement checkStatement = connection.prepareStatement(checkSql);
            checkStatement.setInt(1, message.getPosted_by());
            ResultSet crs = checkStatement.executeQuery();
            if (!crs.next()) {
                return null;
            }

            long epochTime = 0;
            if (message.getTime_posted_epoch() > 0) 
            {
                epochTime = message.getTime_posted_epoch();
            } else {
                epochTime = System.currentTimeMillis() / 1000;
            }
        
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());
            
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                int generated_message_id = (int) rs.getLong(1);
                return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(), epochTime);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * TODO: retrieve a message by given ID.
     * @return all messages.
     */
    public Message getMessageById(int id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM message WHERE message_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int mess_id = rs.getInt("message_id");
                int post_id = rs.getInt("posted_by");
                String text = rs.getString("message_text");
                Long epoch = rs.getLong("time_posted_epoch");
                return new Message(mess_id, post_id, text, epoch);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
