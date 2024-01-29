package Controller;
import model.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DepartmentController {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        //addDept(factory, session);
        //findDept(factory,session,3);
        //updateDept(session,2);
        deleteDept(session,6);
    }

    public static void addDept(SessionFactory factory, Session session) {
        Transaction transaction = session.beginTransaction();
        Department dOne = new Department();
        dOne.setName("UX");
        dOne.setState("CA");

        Department dTwo = new Department();
        dTwo.setName("Security");
        dTwo.setState("NY");

        Department dThree = new Department("Finance","MN");
        Department dFour = new Department("Marketing","WI");

        session.persist(dOne);
        session.persist(dTwo);
        session.persist(dThree);
        session.persist(dFour);

        transaction.commit();
        System.out.println("successfully saved");
        factory.close();
        session.close();

    }

    public static void findDept(SessionFactory factory, Session session, int did) {
        Transaction tx = session.beginTransaction();

        Department d = session.get(Department.class, did);
        System.out.println("Name: " +d.getName());
        System.out.println("State: " + d.getState());

        tx.commit();
        factory.close();
        session.close();
    }

    public static void updateDept(Session session, int did) {
        Transaction tx = session.beginTransaction();
        Department a = new Department();
        a.setDid(did);
        a.setName("HR");
        a.setState("NY");
        session.merge(a);
        session.getTransaction().commit();
        session.close();
    }

    public static void deleteDept(Session session, int did) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Transaction tx = session.beginTransaction();
        Department d = new Department();
        d.setDid(did);
        session.remove(d);
        tx.commit();
        session.close();
        factory.close();
    }

}

