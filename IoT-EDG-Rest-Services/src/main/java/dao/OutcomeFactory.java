/** 
 * This class was automatically generated  
 * using a Merode XML model and Apache Velocity
 * 
 * Merode Code Generator 2.0
 * @author MERODE Team-members
 */

package dao;

/**
 * Factory class.
 * Is able to find and create objects of type Outcome.
 *
 */
public abstract class OutcomeFactory {

   // ---------------- create method --------------------
   
   /**
    * Creates a(n) Outcome object.
    *
    * @return Outcome the created object
    */
    public static Outcome create(org.hibernate.Session session) throws org.hibernate.HibernateException {
        Outcome object = new OutcomeImpl();
        OutcomeState.setInitialState(session, object);
        return object;
    }


    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds Outcome object by its primary key.
     * In Hibernate, this is just a call to load().
     *
     */
    public static Outcome findByPrimaryKey (org.hibernate.Session sess, java.lang.String id)
        throws org.hibernate.HibernateException {
        Outcome object = (Outcome) sess.load(OutcomeImpl.class, id);
        return object;
    }

    public static java.util.Collection getAllObjects (org.hibernate.Session sess)
        throws org.hibernate.HibernateException {
    
        org.hibernate.Query q = sess.createQuery("from dao.Outcome");
        return q.list();
    }

    /**
     *
     * Finds Outcome object(s) using a query.
     *
     */
    public static java.util.Collection findByName (org.hibernate.Session sess, java.lang.String Name)
        throws org.hibernate.HibernateException {
    
        org.hibernate.Query q = sess.createQuery("from dao.Outcome as c where c.Name = ?");
    	q.setString (0, Name);
        return q.list();
    }
}
