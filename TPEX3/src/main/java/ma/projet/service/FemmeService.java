package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.classes.Femme;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ma.projet.util.HibernateUtil;
import java.time.LocalDate;
import java.util.List;

public class FemmeService implements IDao<Femme> {

    @Override
    public boolean create(Femme o) {
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
    public Femme getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Femme.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Femme> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Femme> query = session.createQuery("from Femme", Femme.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getNombreEnfantsEntreDates(int femmeId, LocalDate dateDebut, LocalDate dateFin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Integer> query = session.createNativeQuery(
                    "SELECT SUM(m.nbr_enfants) FROM mariages m " +
                            "WHERE m.femme_id = :femmeId " +
                            "AND m.date_debut BETWEEN :dateDebut AND :dateFin", Integer.class);
            query.setParameter("femmeId", femmeId);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);

            Integer result = query.getSingleResult();
            return result != null ? result : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Femme> getFemmesMarieesDeuxFoisOuPlus() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Femme> query = session.createNamedQuery("Femme.findFemmesMarieesDeuxFoisOuPlus", Femme.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Femme getFemmeLaPlusAgee() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Femme> query = session.createQuery(
                    "from Femme f order by f.dateNaissance asc", Femme.class);
            query.setMaxResults(1);
            List<Femme> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}