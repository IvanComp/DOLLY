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
 *    discriminator-value="PlatformStateExists"
 */
public class PlatformStateExists extends PlatformState {

    public static PlatformStateExists getObject (org.hibernate.Session sess) throws org.hibernate.HibernateException {
	    PlatformStateExists state = null;
	    // Search in database
	    java.util.Collection states = findStateByName (sess, "exists");
	    if ( states != null && !states.isEmpty() )
	        state = (PlatformStateExists)states.iterator().next();
	    if ( state == null ) {
	        state = new PlatformStateExists();
	        // Save in database
	        sess.save (state);
	    }
	    return state;
    }

    private static java.util.Collection findStateByName (org.hibernate.Session sess, java.lang.String statename)
        throws org.hibernate.HibernateException {
    
        org.hibernate.Query q = sess.createQuery("from dao.PlatformState as c where c.name = ?");
    	q.setString (0, statename);
        return q.list();
    }
    
    protected java.lang.String getStateName() {
    	return "exists";
    }

    protected java.lang.String getStateId() {
    	return "146";
    }

    public boolean isInitialState() {
    	return false;
    }

    public boolean isFinalState() {
    	return false;
    }


		
    public void check_meendplatform() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking PlatformState.meendplatform...passed");
    }

		
    public void check_devicedeployment() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking PlatformState.devicedeployment...passed");
    }

		
    public void check_deviceundeployment() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking PlatformState.deviceundeployment...passed");
    }

		
    public void check_mecrdevice() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking PlatformState.mecrdevice...passed");
    }

		
    public void check_mecrdeviceresult() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking PlatformState.mecrdeviceresult...passed");
    }

		
    public void check_mecrdeviceusage() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking PlatformState.mecrdeviceusage...passed");
    }

		
    public void check_mecroutcome() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking PlatformState.mecroutcome...passed");
    }

		
    public void check_mecrpropertyoutcome() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking PlatformState.mecrpropertyoutcome...passed");
    }

		
    public void check_meenddevice() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking PlatformState.meenddevice...passed");
    }

		
    public void check_meenddeviceresult() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking PlatformState.meenddeviceresult...passed");
    }

		
    public void check_meenddeviceusage() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking PlatformState.meenddeviceusage...passed");
    }

		
    public void check_meendoutcome() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking PlatformState.meendoutcome...passed");
    }

		
    public void check_meendpropertyoutcome() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking PlatformState.meendpropertyoutcome...passed");
    }

	
	
    public void meendplatform (org.hibernate.Session sess, Platform object) throws org.hibernate.HibernateException {
        PlatformStateEnded state = PlatformStateEnded.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 143 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 153");
        object.setState(state);
    }

	
    public void devicedeployment (org.hibernate.Session sess, Platform object) throws org.hibernate.HibernateException {
        PlatformStateExists state = PlatformStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 143 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 156");
        object.setState(state);
    }

	
    public void deviceundeployment (org.hibernate.Session sess, Platform object) throws org.hibernate.HibernateException {
        PlatformStateExists state = PlatformStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 143 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 158");
        object.setState(state);
    }

	
    public void mecrdevice (org.hibernate.Session sess, Platform object) throws org.hibernate.HibernateException {
        PlatformStateExists state = PlatformStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 143 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 160");
        object.setState(state);
    }

	
    public void mecrdeviceresult (org.hibernate.Session sess, Platform object) throws org.hibernate.HibernateException {
        PlatformStateExists state = PlatformStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 143 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 162");
        object.setState(state);
    }

	
    public void mecrdeviceusage (org.hibernate.Session sess, Platform object) throws org.hibernate.HibernateException {
        PlatformStateExists state = PlatformStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 143 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 166");
        object.setState(state);
    }

	
    public void mecroutcome (org.hibernate.Session sess, Platform object) throws org.hibernate.HibernateException {
        PlatformStateExists state = PlatformStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 143 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 168");
        object.setState(state);
    }

	
    public void mecrpropertyoutcome (org.hibernate.Session sess, Platform object) throws org.hibernate.HibernateException {
        PlatformStateExists state = PlatformStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 143 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 170");
        object.setState(state);
    }

	
    public void meenddevice (org.hibernate.Session sess, Platform object) throws org.hibernate.HibernateException {
        PlatformStateExists state = PlatformStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 143 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 174");
        object.setState(state);
    }

	
    public void meenddeviceresult (org.hibernate.Session sess, Platform object) throws org.hibernate.HibernateException {
        PlatformStateExists state = PlatformStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 143 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 176");
        object.setState(state);
    }

	
    public void meenddeviceusage (org.hibernate.Session sess, Platform object) throws org.hibernate.HibernateException {
        PlatformStateExists state = PlatformStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 143 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 180");
        object.setState(state);
    }

	
    public void meendoutcome (org.hibernate.Session sess, Platform object) throws org.hibernate.HibernateException {
        PlatformStateExists state = PlatformStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 143 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 182");
        object.setState(state);
    }

	
    public void meendpropertyoutcome (org.hibernate.Session sess, Platform object) throws org.hibernate.HibernateException {
        PlatformStateExists state = PlatformStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 143 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 184");
        object.setState(state);
    }

	
}
