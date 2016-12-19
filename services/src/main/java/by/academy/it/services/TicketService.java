package by.academy.it.services;

import by.academy.it.entity.Ticket;

import java.util.List;

public interface TicketService extends Service<Ticket> {
    List<Ticket> getAllByUser(int userId);

    List<Ticket> getAll();

    void payTicket(Integer ticketId);
}
