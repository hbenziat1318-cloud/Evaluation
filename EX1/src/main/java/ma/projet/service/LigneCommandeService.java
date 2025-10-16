package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.classes.LigneCommandeProduit;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ma.projet.util.HibernateUtil;
import java.util.List;

public class LigneCommandeService implements IDao<LigneCommandeProduit> {

    @Override
    public boolean create(LigneCommandeProduit o) {
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
    public LigneCommandeProduit getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(LigneCommandeProduit.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<LigneCommandeProduit> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<LigneCommandeProduit> query = session.createQuery("from LigneCommandeProduit", LigneCommandeProduit.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
