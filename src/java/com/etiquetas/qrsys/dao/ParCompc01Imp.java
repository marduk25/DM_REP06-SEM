package com.etiquetas.qrsys.dao;

import com.etiquetas.qrsys.s.model.ParCompc01;
import com.etiquetas.qrsys.util.HibernateUtilSae;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class ParCompc01Imp implements ParCompc01Dao {

    @Override
    @SuppressWarnings("null")
    public void saveParCompc01(ParCompc01 parcompc) {
        Session session = null;
        try {
            session = HibernateUtilSae.getSessionfactory().openSession();
            session.beginTransaction();
            session.save(parcompc);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
    }

}
