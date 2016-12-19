package by.academy.it.dao;

import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.Flight;

import java.util.List;

public interface FlightDao extends Dao<Flight> {

    List<Flight> getAll() throws DaoException;

    List<Flight> getAll(int recordsPerPage, int currentPage) throws DaoException;

    List<Flight> getAll(int recordsPerPage, int currentPage, String flightDate) throws DaoException;

    Long getAmount() throws DaoException;

    Long getAmount(String flightDate) throws DaoException;


}