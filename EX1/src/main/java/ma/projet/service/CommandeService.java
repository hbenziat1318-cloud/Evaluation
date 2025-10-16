package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.classes.Commande;
import ma.projet.classes.LigneCommandeProduit;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ma.projet.util.HibernateUtil;
import java.util.List;

public class CommandeService implements IDao<Commande> {

    @Override
    public boolean create(Commande o) {
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
    public Commande getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Commande.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Commande> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Commande> query = session.createQuery("from Commande", Commande.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void afficherProduitsCommande(int commandeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Commande commande = session.get(Commande.class, commandeId);
            if (commande != null) {
                System.out.println("\nCommande : " + commande.getId() + " Date : " + commande.getDate());
                System.out.println("Liste des produits :");
                System.out.println("Référence\tPrix\tQuantité");

                for (LigneCommandeProduit ligne : commande.getLignesCommande()) {
                    System.out.println(ligne.getProduit().getReference() + "\t\t" +
                            ligne.getProduit().getPrix() + " DH\t" +
                            ligne.getQuantite());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}