package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.classes.EmployeeTache;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ma.projet.util.HibernateUtil;
import java.util.List;

public class EmployeeTacheService implements IDao<EmployeeTache> {

    @Override
    public boolean create(EmployeeTache o) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(o);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public EmployeeTache getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(EmployeeTache.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<EmployeeTache> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<EmployeeTache> query = session.createQuery("from EmployeeTache", EmployeeTache.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
