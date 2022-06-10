import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Producer
 */
public class HelloWorldExample {
    private static SessionFactory factory;
//    private static ServiceRegistry registry;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String m = Math.random()+" message";
//        System.out.println("Enter a message: ");
//        m = in.nextLine();
        try{
            factory = new Configuration().configure().buildSessionFactory();
//            Configuration conf = new Configuration().configure("hibernate.cfg.xml");
//            registry = new StandardServiceRegistryBuilder().applySettings(
//            conf.getProperties()).build();
//            conf.addAnnotatedClass(Message.class);
//            factory = conf.buildSessionFactory(registry);
        } catch (Throwable ex){
            System.err.println("Failed to create session factory object"+ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;
        Short msgId = null;
        try{
            tx=session.beginTransaction();
            Message msg = new Message(m);
            msgId = (Short) session.save(msg);
            List messages = session.createQuery("FROM Message").list();
            for(Iterator iterator = messages.iterator(); iterator.hasNext();){
                Message message = (Message)iterator.next();
                System.out.println("message: "+message.getMessage());
            }
            tx.commit();
        }catch(HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
        factory.close();
//    StandardServiceRegistryBuilder.destroy(registry);
    }
    
}
