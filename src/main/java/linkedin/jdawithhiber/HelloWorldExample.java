package linkedin.jdawithhiber;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HelloWorldExample {
    private static SessionFactory factory;

    public static void main(String[] args) {
        initFactory();

        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            saveMessage(session);
            readAllMessages(session);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }

        factory.close();
    }

    private static void readAllMessages(Session session) {
        List messages = session.createQuery("FROM linkedin.jdawithhiber.Message").list();
        for (Iterator iterator = messages.iterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }
    }

    private static void saveMessage(Session session) {
        Scanner in = new Scanner(System.in);
        String textNewMessage = "";
        System.out.println("Enter a message: ");
        textNewMessage = in.nextLine();
        Message msg = new Message(textNewMessage);
        session.save(msg);
    }

    private static void initFactory() {
        try {
            factory = new Configuration()
                    .configure("linkedin/jdawithhiber/hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create session factory object" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
