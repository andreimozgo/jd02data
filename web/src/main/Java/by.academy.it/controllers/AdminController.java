package by.academy.it.controllers;

import by.academy.it.entity.Flight;
import by.academy.it.entity.Ticket;
import by.academy.it.manager.ConfigurationManager;
import by.academy.it.services.FlightService;
import by.academy.it.services.TicketService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private FlightService flightService;
    @Autowired
    private TicketService ticketService;
    final Logger LOG = Logger.getLogger(AdminController.class);
    private final int defaultRecordsPerPage = 3;
    private final int defaultCurrentPage = 1;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        String page = ConfigurationManager.getProperty("path.page.main.reject");
        return page;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getAdminPage(HttpServletRequest request) {
        String page;
        int currentPage;
        int recordsPerPage;
        List<Ticket> tickets = ticketService.getAll();
        request.setAttribute("tickets", tickets);
        //used for pagination
        if (request.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
        } else {
            recordsPerPage = defaultRecordsPerPage;
        }

        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.valueOf(request.getParameter("currentPage"));
        } else {
            currentPage = defaultCurrentPage;
        }
        //gets all flights with pagination
        List<Flight> flights = flightService.getAll(recordsPerPage, currentPage);
        int numberOfPages = flightService.getNumberOfPages(recordsPerPage);
        request.setAttribute("flights", flights);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }

    @RequestMapping(value = "/addflight", method = RequestMethod.POST)
    public String addFlight(HttpServletRequest request) {
        String page;
        //adds flight
        String date = request.getParameter("flightDate");
        int seats = Integer.parseInt(request.getParameter("seats"));
        int cost = Integer.parseInt(request.getParameter("cost"));
        byte upCost = Byte.parseByte(request.getParameter("upCost"));
        Flight flight = new Flight(null, date, seats, cost, upCost);
        flightService.createOrUpdate(flight);
        LOG.info("Flight added successfully");

        page = getAdminPage(request);
        return page;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET, params = "flight_id")
    public String deleteFlight(HttpServletRequest request, @RequestParam(value = "flight_id") int flightId) {
        String page;
        //deletes flight
        flightService.delete(flightId);
        LOG.info("Flight deleted successfully");

        page = getAdminPage(request);
        return page;
    }
}
