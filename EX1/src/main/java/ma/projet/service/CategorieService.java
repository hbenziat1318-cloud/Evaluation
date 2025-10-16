package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.classes.Categorie;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ma.projet.util.HibernateUtil;
import java.util.List;

public class CategorieService implements IDao<Categorie> {

    @Override
    public boolean create(Categorie o) {
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
    public Categorie getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Categorie.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Categorie> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Categorie> query = session.createQuery("from Categorie", Categorie.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
