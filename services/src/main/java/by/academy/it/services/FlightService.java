package by.academy.it.services;

import by.academy.it.entity.Flight;

import java.util.List;

public interface FlightService extends Service<Flight> {

    List<Flight> getAll(int recordsPerPage, int currentPage);

    List<Flight> getAll(int recordsPerPage, int currentPage, String flightDate);

    int getNumberOfPages(int recordsPerPage);

    int getNumberOfPages(int recordsPerPage, String flightDate);
}
