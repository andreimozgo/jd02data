package by.academy.it.controllers;

import by.academy.it.entity.Extra;
import by.academy.it.entity.Flight;
import by.academy.it.entity.Ticket;
import by.academy.it.manager.ConfigurationManager;
import by.academy.it.services.ExtraService;
import by.academy.it.services.FlightService;
import by.academy.it.services.TicketService;
import by.academy.it.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private FlightService flightService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private ExtraService extraService;
    @Autowired
    private UserService userService;

    final Logger LOG = Logger.getLogger(ClientController.class);
    private final int defaultRecordsPerPage = 3;
    private final int defaultCurrentPage = 1;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        LOG.info("Entered in index()");
        String page = ConfigurationManager.getProperty("path.page.user.reject");
        return page;
    }

    @RequestMapping(value = "/user")
    public String getClientPage(HttpServletRequest request) {
        LOG.info("Entered in getClientPage()");
        String page;
        int currentPage;
        int recordsPerPage;
        String flightDate;
        List<Flight> flights;

        int userId = getUserIdByPrincipal();
        //gets all user tickets
        List<Ticket> tickets = ticketService.getAllByUser(userId);
        request.setAttribute("tickets", tickets);
        //gets all flights with pagination
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
        int numberOfPages;
        if (request.getParameter("flightDate") != null) {
            flightDate = request.getParameter("flightDate");
            LOG.info("FlightDate= " + flightDate);
            flights = flightService.getAll(recordsPerPage, currentPage, flightDate);
            numberOfPages = flightService.getNumberOfPages(recordsPerPage, flightDate);
        } else {
            flights = flightService.getAll(recordsPerPage, currentPage);
            numberOfPages = flightService.getNumberOfPages(recordsPerPage);
        }
        //sets all data in request
        request.setAttribute("flights", flights);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        page = ConfigurationManager.getProperty("path.page.user");
        LOG.info("Got path.page.user");
        return page;
    }

    @RequestMapping(value = "/recalculate", method = RequestMethod.POST)
    public String recalculateTicket(HttpServletRequest request) {
        String page;
        //adds extra cost to ticket cost
        int ticketId = Integer.parseInt(request.getParameter("ticket_id"));
        Ticket ticket = ticketService.findEntityById(ticketId);
        int cost = ticket.getCost();

        if (Integer.parseInt(request.getParameter("baggage")) == 1) {
            Extra extra = extraService.findEntityById(1);
            cost += extra.getCost();

        }
        if (Integer.parseInt(request.getParameter("priorityregistration")) == 1) {
            Extra extra = extraService.findEntityById(2);
            cost += extra.getCost();
        }

        if (Integer.parseInt(request.getParameter("priorityboarding")) == 1) {
            Extra extra = extraService.findEntityById(3);
            cost += extra.getCost();
        }

        ticket.setCost(cost);
        ticketService.createOrUpdate(ticket);
        LOG.info("Ticket recalculated successfully");

        page = getClientPage(request);
        return page;
    }


    @RequestMapping(value = "/payticket", method = RequestMethod.POST)
    public String payTicket(HttpServletRequest request) {
        String page;
        //pays ticket
        int ticketId = Integer.parseInt(request.getParameter("ticket_id"));
        ticketService.payTicket(ticketId);
        LOG.info("Ticket payed successfully");

        page = getClientPage(request);
        return page;
    }

    @RequestMapping(value = "/buyticket", method = RequestMethod.POST)
    public String buyTicket(HttpServletRequest request) {
        String page;
        //buys ticket
        int flightId = Integer.parseInt(request.getParameter("flight_id"));
        int userId = getUserIdByPrincipal();
        Flight flight = flightService.findEntityById(flightId);
        int cost = flight.getCost();
        Ticket ticket = new Ticket(0, flight, userId, cost, 0);
        ticketService.createOrUpdate(ticket);
        LOG.info("User bought ticket successfully");

        page = getClientPage(request);
        return page;
    }

    private int getUserIdByPrincipal() {
        String login;
        int userId = 0;
        //gets user Id from spring security
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            login = ((UserDetails) principal).getUsername();
            userId = userService.getUserId(login);
        }
        return userId;
    }
}
