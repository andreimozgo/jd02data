package by.academy.it.services;

import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.Flight;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("/test-service-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class FlightServicesImplTest extends Assert {

    @Autowired
    private FlightService flightService;


    @Test
    public void testCreate() throws DaoException {
        Flight flight = new Flight(null, "2016-01-01", 20, 10, (byte) 1);
        flightService.createOrUpdate(flight);
        Flight readFlight = flightService.findEntityById(flight.getId());
        assertNotNull(readFlight);
        assertEquals(flight, readFlight);
        flightService.delete(flight.getId());
    }

    @Test
    public void testDelete() throws DaoException {
        Flight flight = new Flight(null, "2016-01-01", 20, 10, (byte) 1);
        flightService.createOrUpdate(flight);
        flightService.delete(flight.getId());
        Flight deletedFlight = flightService.findEntityById(flight.getId());
        assertNull(deletedFlight);
    }

    @Test
    public void testFindEntityById() throws DaoException {
        Flight flight = new Flight(null, "2016-11-22", 30, 10, (byte) 0);
        flightService.createOrUpdate(flight);
        Flight readFlight = flightService.findEntityById(flight.getId());
        assertNotNull(readFlight);
        assertEquals(flight, readFlight);
        flightService.delete(flight.getId());
    }

    @Test
    public void testGetNumberOfPages() throws DaoException {
        Flight flight = new Flight(null, "2016-11-22", 30, 10, (byte) 0);
        flightService.createOrUpdate(flight);
        int recordsPerPage = 3;
        int numberOfPages = flightService.getNumberOfPages(recordsPerPage);
        assertNotNull(numberOfPages);
        assertEquals(1, numberOfPages);
        flightService.delete(flight.getId());
    }
}
