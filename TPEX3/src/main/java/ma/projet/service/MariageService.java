package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.classes.Mariage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ma.projet.util.HibernateUtil;
import java.util.List;

public class MariageService implements IDao<Mariage> {

    @Override
    public boolean create(Mariage o) {
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
    public Mariage getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Mariage.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Mariage> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Mariage> query = session.createQuery("from Mariage", Mariage.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}