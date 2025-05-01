package Service;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Account;
import Model.Message;
import java.util.ArrayList;
import java.util.List;

public class MessageService {
    private MessageDAO messageDAO;

    public MessageService()
    {
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }


    /**
     * TODO: Use the MessageDAO to retrieve all messages.
     *
     * @return all messages
     */
    public List<Message> getAllMessages() { 
        return messageDAO.getAllMessages();
    }

    /**
     * TODO: Use MessageDAO to create a message
     * 
     * @param Message the name of the message
     * @return the new message if successfull
     */
    public Message newMessage(Message message)
    {
        String text = message.getMessage_text();
        boolean notEmpty = (text != null && !text.trim().isEmpty());
        boolean validLength = (text != null && text.length() <= 255);
    
        if (notEmpty && validLength) {
            return messageDAO.newMessage(message);
        } else {
            return null;
        }
    }

    /**
     * TODO: Use the MessageDAO to retrieve a specified message.
     *
     * @return specific message
     */
    public Message getMessageById(int id) { 
        return messageDAO.getMessageById(id);
    }

}
