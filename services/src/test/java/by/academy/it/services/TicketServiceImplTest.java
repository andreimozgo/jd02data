package by.academy.it.services;

import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.Flight;
import by.academy.it.entity.Ticket;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration("/test-service-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TicketServiceImplTest extends Assert {
    @Autowired
    TicketService ticketService;
    @Autowired
    private FlightService flightService;


    @Test
    public void testCreate() throws DaoException {
        Flight flight = new Flight(null, "2016-01-01", 20, 10, (byte) 1);
        Ticket ticket = new Ticket(0, flight, 1, 10, (byte) 0);
        flightService.createOrUpdate(flight);

        ticketService.createOrUpdate(ticket);
        Ticket readTicket = ticketService.findEntityById(ticket.getId());
        assertNotNull(readTicket);
        assertEquals(ticket.getCost(), readTicket.getCost());
        assertEquals(ticket.getPaid(), readTicket.getPaid());
        assertEquals(ticket.getFlight(), readTicket.getFlight());
        assertEquals(ticket.getUserId(), readTicket.getUserId());
        ticketService.delete(ticket.getId());
        flightService.delete(flight.getId());
    }

    @Test
    public void testDelete() throws DaoException {
        Flight flight = new Flight(null, "2016-01-01", 20, 10, (byte) 1);
        Ticket ticket = new Ticket(0, flight, 1, 10, (byte) 1);
        flightService.createOrUpdate(flight);
        ticketService.createOrUpdate(ticket);
        System.out.println("ticket.getId()= " + ticket.getId());
        ticketService.delete(ticket.getId());
        System.out.println("ticket.getId()= " + ticket.getId());
        Ticket deletedTicket = ticketService.findEntityById(ticket.getId());
        assertNull(deletedTicket);
        flightService.delete(flight.getId());
    }

    @Test
    public void testFindEntityById() throws DaoException {
        Flight flight = new Flight(null, "2016-01-01", 20, 10, (byte) 1);
        flightService.createOrUpdate(flight);
        Ticket ticket = new Ticket(0, flight, 30, 10, (byte) 0);
        ticketService.createOrUpdate(ticket);
        Ticket readTicket = ticketService.findEntityById(ticket.getId());
        assertNotNull(readTicket);
        assertEquals(ticket.getCost(), readTicket.getCost());
        assertEquals(ticket.getPaid(), readTicket.getPaid());
        assertEquals(ticket.getFlight(), readTicket.getFlight());
        assertEquals(ticket.getUserId(), readTicket.getUserId());
        ticketService.delete(ticket.getId());
        flightService.delete(flight.getId());
    }

    @Test
    public void testGetAll() throws DaoException {
        Flight flight = new Flight(null, "2016-01-01", 20, 10, (byte) 1);
        flightService.createOrUpdate(flight);
        Ticket ticket = new Ticket(0, flight, 30, 10, (byte) 0);
        ticketService.createOrUpdate(ticket);
        List<Ticket> tickets = ticketService.getAll();
        assertNotNull(tickets);
        assertEquals(1, tickets.size());
        ticketService.delete(ticket.getId());
        flightService.delete(flight.getId());
    }

/*    @Test
    public void testPayTicket() throws DaoException {
        Flight flight = new Flight(null, "2016-01-01", 20, 10, (byte) 1);
        flightService.createOrUpdate(flight);
        Ticket ticket = new Ticket(0, flight, 30, 10, (byte) 0);
        ticketService.createOrUpdate(ticket);
        ticketService.payTicket(ticket.getId());
        int payed = ticket.getPaid();
        assertNotNull(payed);
        assertEquals(1, ticket.getPaid());
        ticketService.delete(ticket.getId());
        flightService.delete(flight.getId());
    }*/
}
