/** 
 * This class was automatically generated  
 * using a Merode XML model and Apache Velocity
 * 
 * Merode Code Generator 2.0
 * @author MERODE Team-members
 */
package dao;
import java.time.*;


/**
 * @hibernate.subclass
 *    discriminator-value="PropertyoutcomeStateAllocated"
 */
public class PropertyoutcomeStateAllocated extends PropertyoutcomeState {

    public static PropertyoutcomeStateAllocated getObject (org.hibernate.Session sess) throws org.hibernate.HibernateException {
	    PropertyoutcomeStateAllocated state = null;
	    // Search in database
	    java.util.Collection states = findStateByName (sess, "allocated");
	    if ( states != null && !states.isEmpty() )
	        state = (PropertyoutcomeStateAllocated)states.iterator().next();
	    if ( state == null ) {
	        state = new PropertyoutcomeStateAllocated();
	        // Save in database
	        sess.save (state);
	    }
	    return state;
    }

    private static java.util.Collection findStateByName (org.hibernate.Session sess, java.lang.String statename)
        throws org.hibernate.HibernateException {
    
        org.hibernate.Query q = sess.createQuery("from dao.PropertyoutcomeState as c where c.name = ?");
    	q.setString (0, statename);
        return q.list();
    }
    
    protected java.lang.String getStateName() {
    	return "allocated";
    }

    protected java.lang.String getStateId() {
    	return "265";
    }

    public boolean isInitialState() {
    	return true;
    }

    public boolean isFinalState() {
    	return false;
    }


		
    public void check_mecrpropertyoutcome() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking PropertyoutcomeState.mecrpropertyoutcome...passed");
    }

	
	
    public void mecrpropertyoutcome (org.hibernate.Session sess, Propertyoutcome object) throws org.hibernate.HibernateException {
        PropertyoutcomeStateExists state = PropertyoutcomeStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 250 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 270");
        object.setState(state);
    }

	
}
