package by.academy.it.dao.impl;

import by.academy.it.dao.ExtraDao;
import by.academy.it.entity.Extra;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExtraDaoImpl extends BaseDao<Extra> implements ExtraDao {
    final Logger LOG = Logger.getLogger(ExtraDaoImpl.class);
    private Query query;

    @Autowired
    public ExtraDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
