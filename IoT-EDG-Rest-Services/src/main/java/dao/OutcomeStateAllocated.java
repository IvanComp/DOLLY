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
 *    discriminator-value="OutcomeStateAllocated"
 */
public class OutcomeStateAllocated extends OutcomeState {

    public static OutcomeStateAllocated getObject (org.hibernate.Session sess) throws org.hibernate.HibernateException {
	    OutcomeStateAllocated state = null;
	    // Search in database
	    java.util.Collection states = findStateByName (sess, "allocated");
	    if ( states != null && !states.isEmpty() )
	        state = (OutcomeStateAllocated)states.iterator().next();
	    if ( state == null ) {
	        state = new OutcomeStateAllocated();
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
    	return "allocated";
    }

    protected java.lang.String getStateId() {
    	return "72";
    }

    public boolean isInitialState() {
    	return true;
    }

    public boolean isFinalState() {
    	return false;
    }


		
    public void check_mecroutcome() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking OutcomeState.mecroutcome...passed");
    }

	
	
    public void mecroutcome (org.hibernate.Session sess, Outcome object) throws org.hibernate.HibernateException {
        OutcomeStateExists state = OutcomeStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 70 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 77");
        object.setState(state);
    }

	
}
