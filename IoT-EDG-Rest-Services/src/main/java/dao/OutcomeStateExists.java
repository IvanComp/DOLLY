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
 *    discriminator-value="OutcomeStateExists"
 */
public class OutcomeStateExists extends OutcomeState {

    public static OutcomeStateExists getObject (org.hibernate.Session sess) throws org.hibernate.HibernateException {
	    OutcomeStateExists state = null;
	    // Search in database
	    java.util.Collection states = findStateByName (sess, "exists");
	    if ( states != null && !states.isEmpty() )
	        state = (OutcomeStateExists)states.iterator().next();
	    if ( state == null ) {
	        state = new OutcomeStateExists();
	        // Save in database
	        sess.save (state);
	    }
	    return state;
    }

    private static java.util.Collection findStateByName (org.hibernate.Session sess, java.lang.String statename)
        throws org.hibernate.HibernateException {
    
        org.hibernate.Query q = sess.createQuery("from dao.OutcomeState as c where c.name = ?");
    	q.setString (0, statename);
        return q.list();
    }
    
    protected java.lang.String getStateName() {
    	return "exists";
    }

    protected java.lang.String getStateId() {
    	return "73";
    }

    public boolean isInitialState() {
    	return false;
    }

    public boolean isFinalState() {
    	return false;
    }


		
    public void check_meendoutcome() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking OutcomeState.meendoutcome...passed");
    }

		
    public void check_mecrdeviceresult() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking OutcomeState.mecrdeviceresult...passed");
    }

		
    public void check_mecrpropertyoutcome() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking OutcomeState.mecrpropertyoutcome...passed");
    }

		
    public void check_meenddeviceresult() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking OutcomeState.meenddeviceresult...passed");
    }

		
    public void check_meendpropertyoutcome() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking OutcomeState.meendpropertyoutcome...passed");
    }

	
	
    public void meendoutcome (org.hibernate.Session sess, Outcome object) throws org.hibernate.HibernateException {
        OutcomeStateEnded state = OutcomeStateEnded.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 70 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 80");
        object.setState(state);
    }

	
    public void mecrdeviceresult (org.hibernate.Session sess, Outcome object) throws org.hibernate.HibernateException {
        OutcomeStateExists state = OutcomeStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 70 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 83");
        object.setState(state);
    }

	
    public void mecrpropertyoutcome (org.hibernate.Session sess, Outcome object) throws org.hibernate.HibernateException {
        OutcomeStateExists state = OutcomeStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 70 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 85");
        object.setState(state);
    }

	
    public void meenddeviceresult (org.hibernate.Session sess, Outcome object) throws org.hibernate.HibernateException {
        OutcomeStateExists state = OutcomeStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 70 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 87");
        object.setState(state);
    }

	
    public void meendpropertyoutcome (org.hibernate.Session sess, Outcome object) throws org.hibernate.HibernateException {
        OutcomeStateExists state = OutcomeStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 70 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 89");
        object.setState(state);
    }

	
}
