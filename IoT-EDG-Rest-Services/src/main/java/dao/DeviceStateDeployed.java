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
 *    discriminator-value="DeviceStateDeployed"
 */
public class DeviceStateDeployed extends DeviceState {

    public static DeviceStateDeployed getObject (org.hibernate.Session sess) throws org.hibernate.HibernateException {
	    DeviceStateDeployed state = null;
	    // Search in database
	    java.util.Collection states = findStateByName (sess, "deployed");
	    if ( states != null && !states.isEmpty() )
	        state = (DeviceStateDeployed)states.iterator().next();
	    if ( state == null ) {
	        state = new DeviceStateDeployed();
	        // Save in database
	        sess.save (state);
	    }
	    return state;
    }

    private static java.util.Collection findStateByName (org.hibernate.Session sess, java.lang.String statename)
        throws org.hibernate.HibernateException {
    
        org.hibernate.Query q = sess.createQuery("from dao.DeviceState as c where c.name = ?");
    	q.setString (0, statename);
        return q.list();
    }
    
    protected java.lang.String getStateName() {
    	return "deployed";
    }

    protected java.lang.String getStateId() {
    	return "44";
    }

    public boolean isInitialState() {
    	return false;
    }

    public boolean isFinalState() {
    	return false;
    }


		
    public void check_meenddevice() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking DeviceState.meenddevice...passed");
    }

		
    public void check_deviceundeployment() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking DeviceState.deviceundeployment...passed");
    }

		
    public void check_mecrdeviceresult() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking DeviceState.mecrdeviceresult...passed");
    }

		
    public void check_meenddeviceresult() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking DeviceState.meenddeviceresult...passed");
    }

	
	
    public void meenddevice (org.hibernate.Session sess, Device object) throws org.hibernate.HibernateException {
        DeviceStateEnded state = DeviceStateEnded.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 1 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 11");
        object.setState(state);
    }

	
    public void deviceundeployment (org.hibernate.Session sess, Device object) throws org.hibernate.HibernateException {
        DeviceStateExists state = DeviceStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 1 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 16");
        object.setState(state);
    }

	
    public void mecrdeviceresult (org.hibernate.Session sess, Device object) throws org.hibernate.HibernateException {
        DeviceStateDeployed state = DeviceStateDeployed.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 1 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 18");
        object.setState(state);
    }

	
    public void meenddeviceresult (org.hibernate.Session sess, Device object) throws org.hibernate.HibernateException {
        DeviceStateDeployed state = DeviceStateDeployed.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 1 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 30");
        object.setState(state);
    }

	
}
