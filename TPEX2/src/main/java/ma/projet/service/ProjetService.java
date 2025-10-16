package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ma.projet.util.HibernateUtil;
import java.util.List;

public class ProjetService implements IDao<Projet> {

    @Override
    public boolean create(Projet o) {
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
    public Projet getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Projet.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Projet> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Projet> query = session.createQuery("from Projet", Projet.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Tache> getTachesPlanifieesByProjet(int projetId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Tache> query = session.createQuery(
                    "from Tache t where t.projet.id = :projetId " +
                            "and t.dateDebutPlanifie is not null " +
                            "and t.dateFinPlanifie is not null", Tache.class);
            query.setParameter("projetId", projetId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void afficherTachesRealiseesAvecDetails(int projetId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Projet projet = session.get(Projet.class, projetId);
            if (projet != null) {
                System.out.println("\nProjet : " + projet.getId() +
                        " Nom : " + projet.getNom() +
                        " Date début : " + projet.getDateDebut());

                Query<Tache> query = session.createQuery(
                        "from Tache t where t.projet.id = :projetId " +
                                "and t.dateDebutReelle is not null " +
                                "and t.dateFinReelle is not null", Tache.class);
                query.setParameter("projetId", projetId);

                List<Tache> taches = query.getResultList();

                System.out.println("Liste des tâches :");
                System.out.println("Num\tNom\tDate Début Réelle\tDate Fin Réelle");
                for (Tache tache : taches) {
                    System.out.println(tache.getId() + "\t" +
                            tache.getNom() + "\t" +
                            tache.getDateDebutReelle() + "\t" +
                            tache.getDateFinReelle());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}