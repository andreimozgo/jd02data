package by.academy.it.dao.impl;

import by.academy.it.dao.TicketDao;
import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.Flight;
import by.academy.it.entity.Ticket;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional()
public class TicketDaoImplTest extends Assert {

    @Autowired
    TicketDao ticketDao;

    Flight flight = new Flight(1, "2016-01-01", 20, 10, (byte) 1);

    @Test
    public void testCreate() throws DaoException {
        Ticket ticket = new Ticket(1, flight, 1, 10, (byte) 0);
        ticketDao.create(ticket);
        Ticket readTicket = ticketDao.findEntityById(1);
        assertNotNull(readTicket);
        assertEquals(ticket, readTicket);
    }

    @Test
    public void testDelete() throws DaoException {
        Ticket ticket = new Ticket(2, flight, 1, 10, (byte) 1);
        ticketDao.create(ticket);
        ticketDao.delete(2);
        Ticket deletedTicket = ticketDao.findEntityById(2);
        assertNull(deletedTicket);
    }

    @Test
    public void testFindEntityById() throws DaoException {
        Ticket ticket = new Ticket(3, flight, 30, 10, (byte) 0);
        ticketDao.create(ticket);
        Ticket readticket = ticketDao.findEntityById(3);
        assertNotNull(readticket);
        assertEquals(ticket, readticket);
    }

    @Test
    public void testGetAllByUser() throws DaoException {
        List<Ticket> tickets = ticketDao.getAllByUser(1);
        assertEquals(0, tickets.size());
    }

    @Test
    public void testGetAll() throws DaoException {
        List<Ticket> tickets = ticketDao.getAll();
        assertEquals(0, tickets.size());
    }
}