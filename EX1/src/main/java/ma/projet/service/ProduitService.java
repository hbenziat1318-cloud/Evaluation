package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.classes.Produit;
import ma.projet.classes.Categorie;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ma.projet.util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

public class ProduitService implements IDao<Produit> {

    @Override
    public boolean create(Produit o) {
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
    public Produit getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Produit.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Produit> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Produit> query = session.createQuery("from Produit", Produit.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Produit> getProduitsByCategorie(Categorie categorie) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Produit> query = session.createQuery(
                    "from Produit p where p.categorie = :categorie", Produit.class);
            query.setParameter("categorie", categorie);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Produit> getProduitsCommandesBetweenDates(LocalDate dateDebut, LocalDate dateFin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Produit> query = session.createQuery(
                    "select distinct l.produit from LigneCommandeProduit l " +
                            "where l.commande.date between :dateDebut and :dateFin", Produit.class);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Produit> getProduitsPrixSuperieurA100() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Produit> query = session.createNamedQuery("Produit.findByPrixGreaterThan", Produit.class);
            query.setParameter("prix", 100.0f);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}