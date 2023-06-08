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
 *    discriminator-value="PropertyoutcomeStateExists"
 */
public class PropertyoutcomeStateExists extends PropertyoutcomeState {

    public static PropertyoutcomeStateExists getObject (org.hibernate.Session sess) throws org.hibernate.HibernateException {
	    PropertyoutcomeStateExists state = null;
	    // Search in database
	    java.util.Collection states = findStateByName (sess, "exists");
	    if ( states != null && !states.isEmpty() )
	        state = (PropertyoutcomeStateExists)states.iterator().next();
	    if ( state == null ) {
	        state = new PropertyoutcomeStateExists();
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
    	return "exists";
    }

    protected java.lang.String getStateId() {
    	return "266";
    }

    public boolean isInitialState() {
    	return false;
    }

    public boolean isFinalState() {
    	return false;
    }


		
    public void check_meendpropertyoutcome() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking PropertyoutcomeState.meendpropertyoutcome...passed");
    }

		
    public void check_mecrdeviceresult() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking PropertyoutcomeState.mecrdeviceresult...passed");
    }

		
    public void check_meenddeviceresult() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking PropertyoutcomeState.meenddeviceresult...passed");
    }

	
	
    public void meendpropertyoutcome (org.hibernate.Session sess, Propertyoutcome object) throws org.hibernate.HibernateException {
        PropertyoutcomeStateEnded state = PropertyoutcomeStateEnded.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 250 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 273");
        object.setState(state);
    }

	
    public void mecrdeviceresult (org.hibernate.Session sess, Propertyoutcome object) throws org.hibernate.HibernateException {
        PropertyoutcomeStateExists state = PropertyoutcomeStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 250 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 276");
        object.setState(state);
    }

	
    public void meenddeviceresult (org.hibernate.Session sess, Propertyoutcome object) throws org.hibernate.HibernateException {
        PropertyoutcomeStateExists state = PropertyoutcomeStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 250 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 278");
        object.setState(state);
    }

	
}
