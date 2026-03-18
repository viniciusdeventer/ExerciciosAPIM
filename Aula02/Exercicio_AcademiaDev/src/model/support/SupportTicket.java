package model.support;

import model.user.User;
import util.IdGenerator;

public class SupportTicket {

    private final int id;
    private final String title;
    private final String message;
    private final User user;

    public SupportTicket(User user, String title, String message) {

        if (user == null)
            throw new IllegalArgumentException("User cannot be null");

        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Title cannot be empty");

        if (message == null || message.isBlank())
            throw new IllegalArgumentException("Message cannot be empty");

        this.id = IdGenerator.generate(SupportTicket.class);
        this.user = user;
        this.title = title;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

}