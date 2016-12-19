package by.academy.it.dao;

import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.Ticket;

import java.util.List;

public interface TicketDao extends Dao<Ticket> {

    List<Ticket> getAllByUser(int userId) throws DaoException;

    List<Ticket> getAll() throws DaoException;
}
