package by.academy.it.services.impl;

import by.academy.it.dao.ExtraDao;
import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.Extra;
import by.academy.it.services.AbstractService;
import by.academy.it.services.ExtraService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ExtraServiceImpl extends AbstractService<Extra> implements ExtraService {
    final Logger LOG = Logger.getLogger(ExtraServiceImpl.class);
    private ExtraDao extraDao;

    @Autowired
    public ExtraServiceImpl(ExtraDao extraDao) {
        this.extraDao = extraDao;
    }

    public void createOrUpdate(Extra extra) {

    }

    public Extra findEntityById(Integer id) {
        Extra extra = null;
        try {
            extra = extraDao.findEntityById(id);
        } catch (DaoException e) {
            LOG.error("Error find Extra: ", e);
        }
        return extra;
    }

    public void delete(Integer id) {
    }
}
