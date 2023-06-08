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
 * Is able to find and create objects of type Propertyoutcome.
 *
 */
public abstract class PropertyoutcomeFactory {

   // ---------------- create method --------------------
   
   /**
    * Creates a(n) Propertyoutcome object.
    *
    * @return Propertyoutcome the created object
    */
    public static Propertyoutcome create(org.hibernate.Session session) throws org.hibernate.HibernateException {
        Propertyoutcome object = new PropertyoutcomeImpl();
        PropertyoutcomeState.setInitialState(session, object);
        return object;
    }


    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds Propertyoutcome object by its primary key.
     * In Hibernate, this is just a call to load().
     *
     */
    public static Propertyoutcome findByPrimaryKey (org.hibernate.Session sess, java.lang.String id)
        throws org.hibernate.HibernateException {
        Propertyoutcome object = (Propertyoutcome) sess.load(PropertyoutcomeImpl.class, id);
        return object;
    }

    public static java.util.Collection getAllObjects (org.hibernate.Session sess)
        throws org.hibernate.HibernateException {
    
        org.hibernate.Query q = sess.createQuery("from dao.Propertyoutcome");
        return q.list();
    }

    /**
     *
     * Finds Propertyoutcome object(s) using a query.
     *
     */
    public static java.util.Collection findByName (org.hibernate.Session sess, java.lang.String Name)
        throws org.hibernate.HibernateException {
    
        org.hibernate.Query q = sess.createQuery("from dao.Propertyoutcome as c where c.Name = ?");
    	q.setString (0, Name);
        return q.list();
    }
}
