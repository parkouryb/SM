package Project.Controller;

import Project.Hibernate.HibernateUtil;
import Project.Object.Account;
import Project.Object.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccountController {
    private static SessionFactory factory = HibernateUtil.getSessionFactory();

    public static Account login(Account account) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Account res = null;
        try {
            transaction = session.beginTransaction();

            List<Account> list = session.createCriteria(Account.class).list();
            Set<Account> accounts = new HashSet<>(list);

            for (Account account1: accounts) {
                // lay tat ca thong tin account trong db roi search
                // xem co tai khoan nao trung khong
                if (account.getUsername().equals(account1.getUsername())
                        && account.getPassword().equals(account1.getPassword())) {
                    res = account1;
                    break;
                }
            }

            transaction.commit();
        } catch(HibernateException hibernateExeption) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return res;
    }

    public static int deleteAccount(Account account) {
        Student student = account.getStudent();
        if (student != null) {
            StudentController.updateAccount(student);
        }
        Session session = factory.openSession();
        Transaction transaction = null;
        int flag= 0;
        try {
            transaction = session.beginTransaction();
            session.delete(account);
            transaction.commit();
        } catch(HibernateException hibernateExeption) {
            if (transaction != null) {
                transaction.rollback();
            }
            flag = -1;
        } finally {
            session.close();
        }
        return flag;
    }

    public static int changePassword(Account account, String new_password) {
        Session session = factory.openSession();
        Transaction transaction = null;
        int flag = 0;
        try {
            transaction = session.beginTransaction();

            account.setPassword(new_password);
            session.update(account);

            transaction.commit();
        } catch(HibernateException hibernateExeption) {
            if (transaction != null) {
                transaction.rollback();
            }
            flag = -1;
        } finally {
            session.close();
        }
        return flag;
    }

    public static int createAccount(Account account) {
        Session session = factory.openSession();
        Transaction transaction = null;
        int flag = 0;
        try {
            transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
        } catch(HibernateException hibernateExeption) {
            if (transaction != null) {
                transaction.rollback();
            }
            flag = -1;
        } finally {
            session.close();
        }
        return flag;
    }

    public static int addInfo(Account account, Student student) {
        // them thong tin hoc sinh vao tai khoan
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            account.setStudent(student);
            session.update(account);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("[ERROR]");
            session.getTransaction().rollback();
            session.close();
            return -1;
        }
        session.close();
        return 0;
    }
}
