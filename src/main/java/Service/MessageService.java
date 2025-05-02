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

    /**
     * TODO: Use the MessageDAO to delete a specified message.
     *
     * @return specific message
     */
    public Message deleteMessage(int id) { 
        return messageDAO.deleteMessage(id);
    }

    /**
     * TODO: Use MessageDAO to udpate a message
     * 
     * @param Message the message
     * @return the updated message if successfull
     */
    public Message updateMessage(Message updatedMessage)
    {
        messageDAO.updateMessage(updatedMessage);
        return messageDAO.getMessageById(updatedMessage.getMessage_id());
    }

    /**
     * TODO: Use the MessageDAO to retrieve a specified message.
     *
     * @return specific message
     */
    public List<Message> getAllMessagesById(int id) { 
        return messageDAO.getAllMessagesById(id);
    }

}
