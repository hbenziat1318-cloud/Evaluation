package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.classes.Homme;
import ma.projet.classes.Mariage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ma.projet.util.HibernateUtil;
import java.time.LocalDate;
import java.util.List;

public class HommeService implements IDao<Homme> {

    @Override
    public boolean create(Homme o) {
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
    public Homme getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Homme.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Homme> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Homme> query = session.createQuery("from Homme", Homme.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void afficherEpousesEntreDates(int hommeId, LocalDate dateDebut, LocalDate dateFin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Mariage> query = session.createQuery(
                    "from Mariage m where m.homme.id = :hommeId " +
                            "and m.dateDebut between :dateDebut and :dateFin", Mariage.class);
            query.setParameter("hommeId", hommeId);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);

            List<Mariage> mariages = query.getResultList();

            if (!mariages.isEmpty()) {
                System.out.println("Épouses de l'homme ID " + hommeId + " entre " + dateDebut + " et " + dateFin + ":");
                for (Mariage mariage : mariages) {
                    System.out.println("  - " + mariage.getFemme().getNom() + " " + mariage.getFemme().getPrenom() +
                            " (Date début: " + mariage.getDateDebut() + ")");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getNombreHommesMariesQuatreFemmesEntreDates(LocalDate dateDebut, LocalDate dateFin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery(
                    "select count(distinct m.homme.id) from Mariage m " +
                            "where m.dateDebut between :dateDebut and :dateFin " +
                            "group by m.homme.id " +
                            "having count(m.femme.id) >= 4", Long.class);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);

            List<Long> result = query.getResultList();
            return result.isEmpty() ? 0 : result.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void afficherMariagesAvecDetails(int hommeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Homme homme = session.get(Homme.class, hommeId);
            if (homme != null) {
                System.out.println("\nNom : " + homme.getNom() + " " + homme.getPrenom());

                System.out.println("Mariages En Cours :");
                int countEnCours = 1;
                for (Mariage mariage : homme.getMariages()) {
                    if (mariage.getDateFin() == null) {
                        System.out.println(countEnCours + ". Femme : " + mariage.getFemme().getNom() + " " +
                                mariage.getFemme().getPrenom() +
                                " Date Début : " + mariage.getDateDebut() +
                                " Nbr Enfants : " + mariage.getNbrEnfants());
                        countEnCours++;
                    }
                }

                System.out.println("Mariages échoués :");
                int countEchoues = 1;
                for (Mariage mariage : homme.getMariages()) {
                    if (mariage.getDateFin() != null) {
                        System.out.println(countEchoues + ". Femme : " + mariage.getFemme().getNom() + " " +
                                mariage.getFemme().getPrenom() +
                                " Date Début : " + mariage.getDateDebut() +
                                " Date Fin : " + mariage.getDateFin() +
                                " Nbr Enfants : " + mariage.getNbrEnfants());
                        countEchoues++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}