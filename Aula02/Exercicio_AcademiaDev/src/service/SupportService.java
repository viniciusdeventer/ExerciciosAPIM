package service;

import java.util.ArrayDeque;
import java.util.Queue;

import model.support.SupportTicket;
import model.user.Admin;
import model.user.User;

public class SupportService {

    private final Queue<SupportTicket> tickets = new ArrayDeque<>();

    public void openTicket(User user, String title, String message) {

        SupportTicket ticket = new SupportTicket(user, title, message);

        tickets.add(ticket);
    }

    public SupportTicket processNextTicket(User admin) {

        if (!(admin instanceof Admin))
            throw new IllegalStateException("Only admins can process tickets");

        if (tickets.isEmpty())
            return null;

        return tickets.poll();
    }

    public int getQueueSize() {
        return tickets.size();
    }

}