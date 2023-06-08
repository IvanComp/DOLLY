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
 *    discriminator-value="DeviceusageStatePlanned"
 */
public class DeviceusageStatePlanned extends DeviceusageState {

    public static DeviceusageStatePlanned getObject (org.hibernate.Session sess) throws org.hibernate.HibernateException {
	    DeviceusageStatePlanned state = null;
	    // Search in database
	    java.util.Collection states = findStateByName (sess, "planned");
	    if ( states != null && !states.isEmpty() )
	        state = (DeviceusageStatePlanned)states.iterator().next();
	    if ( state == null ) {
	        state = new DeviceusageStatePlanned();
	        // Save in database
	        sess.save (state);
	    }
	    return state;
    }

    private static java.util.Collection findStateByName (org.hibernate.Session sess, java.lang.String statename)
        throws org.hibernate.HibernateException {
    
        org.hibernate.Query q = sess.createQuery("from dao.DeviceusageState as c where c.name = ?");
    	q.setString (0, statename);
        return q.list();
    }
    
    protected java.lang.String getStateName() {
    	return "planned";
    }

    protected java.lang.String getStateId() {
    	return "233";
    }

    public boolean isInitialState() {
    	return false;
    }

    public boolean isFinalState() {
    	return false;
    }


		
    public void check_meenddeviceusage() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking DeviceusageState.meenddeviceusage...passed");
    }

		
    public void check_mecrpropertyoutcome() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking DeviceusageState.mecrpropertyoutcome...passed");
    }

		
    public void check_meendpropertyoutcome() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking DeviceusageState.meendpropertyoutcome...passed");
    }

		
    public void check_devicedeployment() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking DeviceusageState.devicedeployment...passed");
    }

	
	
    public void meenddeviceusage (org.hibernate.Session sess, Deviceusage object) throws org.hibernate.HibernateException {
        DeviceusageStateEnded state = DeviceusageStateEnded.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 207 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 219");
        object.setState(state);
    }

	
    public void mecrpropertyoutcome (org.hibernate.Session sess, Deviceusage object) throws org.hibernate.HibernateException {
        DeviceusageStatePlanned state = DeviceusageStatePlanned.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 207 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 226");
        object.setState(state);
    }

	
    public void meendpropertyoutcome (org.hibernate.Session sess, Deviceusage object) throws org.hibernate.HibernateException {
        DeviceusageStatePlanned state = DeviceusageStatePlanned.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 207 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 230");
        object.setState(state);
    }

	
    public void devicedeployment (org.hibernate.Session sess, Deviceusage object) throws org.hibernate.HibernateException {
        DeviceusageStateStarted state = DeviceusageStateStarted.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 207 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 222");
        object.setState(state);
    }

	
}
