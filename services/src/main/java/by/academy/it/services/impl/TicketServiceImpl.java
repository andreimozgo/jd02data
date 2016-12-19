package by.academy.it.services.impl;

import by.academy.it.dao.TicketDao;
import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.Ticket;
import by.academy.it.services.AbstractService;
import by.academy.it.services.TicketService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TicketServiceImpl extends AbstractService<Ticket> implements TicketService {
    final Logger LOG = Logger.getLogger(TicketServiceImpl.class);
    private TicketDao ticketDao;
    private final int ticketPayed = 1;

    @Autowired
    public TicketServiceImpl(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public List<Ticket> getAllByUser(int userId) {
        List<Ticket> tickets = null;
        try {
            tickets = ticketDao.getAllByUser(userId);
        } catch (DaoException e) {
            LOG.error("Error get all tickets: ", e);
        }
        return tickets;
    }

    public List<Ticket> getAll() {
        List<Ticket> tickets = null;
        try {
            tickets = ticketDao.getAll();
        } catch (DaoException e) {
            LOG.error("Error get all tickets: ", e);
        }
        return tickets;
    }

    public void createOrUpdate(Ticket ticket) {
        try {
            ticketDao.create(ticket);
        } catch (DaoException e) {
            LOG.error("Error create or update ticket: ", e);
        }
    }

    public Ticket findEntityById(Integer id) {
        Ticket ticket = null;
        try {
            ticket = ticketDao.findEntityById(id);
        } catch (DaoException e) {
            LOG.error("Error find ticket: ", e);
        }
        return ticket;
    }

    public void delete(Integer id) {
        try {
            ticketDao.delete(id);
        } catch (DaoException e) {
            LOG.error("Error delete ticket: ", e);
        }
    }

    public void payTicket(Integer ticketId) {
        try {
            Ticket ticket = ticketDao.findEntityById(ticketId);
            ticket.setPaid(ticketPayed);
            ticketDao.create(ticket);
        } catch (DaoException e) {
            LOG.error("Error pay ticket: ", e);
        }
    }
}
