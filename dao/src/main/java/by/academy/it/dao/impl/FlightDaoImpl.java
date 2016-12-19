package by.academy.it.dao.impl;

import by.academy.it.dao.FlightDao;
import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.Flight;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlightDaoImpl extends BaseDao<Flight> implements FlightDao {
    final Logger LOG = Logger.getLogger(FlightDaoImpl.class);
    private final String GET_ALL_FLIGHTS = "FROM Flight";
    private final String GET_ALL_FLIGHTS_BY_DATE = "FROM Flight F WHERE F.date=:flightDate";

    @Autowired
    private FlightDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List getAll() throws DaoException {
        List<Flight> flights;
        try {
            Query query = getSession().createQuery(GET_ALL_FLIGHTS);
            flights = query.list();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return flights;
    }

    public List getAll(int recordsPerPage, int currentPage) throws DaoException {
        List<Flight> flights;
        try {
            Query query = getSession().createQuery(GET_ALL_FLIGHTS);
            query.setFirstResult((currentPage - 1) * recordsPerPage);
            query.setMaxResults(recordsPerPage);
            query.setCacheable(true);
            query.setCacheMode(CacheMode.NORMAL);
            flights = query.list();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return flights;
    }

    public List getAll(int recordsPerPage, int currentPage, String flightDate) throws DaoException {
        List<Flight> flights;
        try {
            LOG.info("FlightDate= " + flightDate);
            Query query = getSession().createQuery(GET_ALL_FLIGHTS_BY_DATE);
            query.setParameter("flightDate", flightDate);
            query.setFirstResult((currentPage - 1) * recordsPerPage);
            query.setMaxResults(recordsPerPage);
            query.setCacheable(true);
            query.setCacheMode(CacheMode.NORMAL);
            flights = query.list();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return flights;
    }

    public Long getAmount() throws DaoException {
        Long amount;
        try {
            Criteria criteria = getSession().createCriteria(Flight.class);
            criteria.setProjection(Projections.rowCount());
            amount = (Long) criteria.uniqueResult();
            LOG.info("Amount of flights: " + amount);
        } catch (HibernateException e) {
            LOG.error("Unable to get number of flights. Error in DAO");
            throw new DaoException(e);
        }
        return amount;
    }

    public Long getAmount(String flightDate) throws DaoException {
        Long amount;
        try {
            Criteria criteria = getSession().createCriteria(Flight.class);
            criteria.add(Restrictions.eq("date", flightDate));
            criteria.setProjection(Projections.rowCount());
            amount = (Long) criteria.uniqueResult();
            LOG.info("Amount of flights: " + amount);
        } catch (HibernateException e) {
            LOG.error("Unable to get number of flights. Error in DAO");
            throw new DaoException(e);
        }
        return amount;
    }
}
