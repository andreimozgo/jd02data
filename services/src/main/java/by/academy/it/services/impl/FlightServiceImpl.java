package by.academy.it.services.impl;

import by.academy.it.dao.FlightDao;
import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.Flight;
import by.academy.it.services.AbstractService;
import by.academy.it.services.FlightService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class FlightServiceImpl extends AbstractService<Flight> implements FlightService {
    final Logger LOG = Logger.getLogger(FlightServiceImpl.class);
    private FlightDao flightDao;
    private final int defaultNumberOfPages = 1;

    @Autowired
    public FlightServiceImpl(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    public List<Flight> getAll(int recordsPerPage, int currentPage) {
        List<Flight> flights = null;
        try {
            flights = flightDao.getAll(recordsPerPage, currentPage);
        } catch (DaoException e) {
            LOG.error("Error get all flights: ", e);
        }
        return flights;
    }

    public List<Flight> getAll(int recordsPerPage, int currentPage, String flightDate) {
        List<Flight> flights = null;
        try {
            flights = flightDao.getAll(recordsPerPage, currentPage, flightDate);
        } catch (DaoException e) {
            LOG.error("Error get all flights: ", e);
        }
        return flights;
    }

    public void createOrUpdate(Flight flight) {
        try {
            flightDao.create(flight);
        } catch (DaoException e) {
            LOG.error("Error create or update flight: ", e);
        }
    }

    public void delete(Integer id) {
        try {
            flightDao.delete(id);
        } catch (DaoException e) {
            LOG.error("Error delete flight: ", e);
        }
    }

    public Flight findEntityById(Integer id) {
        Flight flight = null;
        try {
            flight = flightDao.findEntityById(id);
        } catch (DaoException e) {
            LOG.error("Error find flight: ", e);
        }
        return flight;
    }

    public int getNumberOfPages(int recordsPerPage) {
        int numberOfPages = defaultNumberOfPages;
        try {
            Long numberOfRecords = flightDao.getAmount();
            numberOfPages = Math.round(numberOfRecords / recordsPerPage);
            if ((numberOfRecords % recordsPerPage) > 0) numberOfPages++;
            LOG.info("Count of flight pages: " + numberOfPages);
        } catch (DaoException e) {
            LOG.error("Error getting count of flight pages", e);
        }
        return numberOfPages;
    }

    public int getNumberOfPages(int recordsPerPage, String flightDate) {
        int numberOfPages = defaultNumberOfPages;
        try {
            Long numberOfRecords = flightDao.getAmount(flightDate);
            numberOfPages = Math.round(numberOfRecords / recordsPerPage);
            if ((numberOfRecords % recordsPerPage) > 0) numberOfPages++;
            LOG.info("Count of flight pages: " + numberOfPages);
        } catch (DaoException e) {
            LOG.error("Error getting count of flight pages", e);
        }
        return numberOfPages;
    }
}

