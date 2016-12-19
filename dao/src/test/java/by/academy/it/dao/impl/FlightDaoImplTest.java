package by.academy.it.dao.impl;

import by.academy.it.dao.FlightDao;
import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.Flight;
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
@Transactional
public class FlightDaoImplTest extends Assert {


    @Autowired
    FlightDao flightDao;

    @Test
    public void testCreate() throws DaoException {
        Flight flight = new Flight(1, "2016-01-01", 20, 10, (byte) 1);
        flightDao.create(flight);
        Flight readFlight = flightDao.findEntityById(1);
        assertNotNull(readFlight);
        assertEquals(flight, readFlight);
    }

    @Test
    public void testDelete() throws DaoException {
        Flight flight = new Flight(2, "2016-01-01", 20, 10, (byte) 1);
        flightDao.create(flight);
        flightDao.delete(2);
        Flight deletedFlight = flightDao.findEntityById(2);
        assertNull(deletedFlight);
    }

    @Test
    public void testFindEntityById() throws DaoException {
        Flight flight = new Flight(3, "2016-11-22", 30, 10, (byte) 0);
        flightDao.create(flight);
        Flight readflight = flightDao.findEntityById(3);
        assertNotNull(readflight);
        assertEquals(flight, readflight);
    }

    @Test
    public void testGetAmount() throws DaoException {
        long amount = flightDao.getAmount();
        assertEquals(0, amount);
    }

    @Test
    public void testGetAmountDate() throws DaoException {
        String date;
        Flight flight = new Flight(null, "2016-11-22", 30, 10, (byte) 0);
        flightDao.create(flight);
        date = "2016-11-22";
        long amount = flightDao.getAmount(date);
        assertEquals(1, amount);
    }

    @Test
    public void testGetAll() throws DaoException {
        List<Flight> flights = flightDao.getAll();
        assertEquals(0, flights.size());
    }

}