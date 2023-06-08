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
 *    discriminator-value="DeviceStateExists"
 */
public class DeviceStateExists extends DeviceState {

    public static DeviceStateExists getObject (org.hibernate.Session sess) throws org.hibernate.HibernateException {
	    DeviceStateExists state = null;
	    // Search in database
	    java.util.Collection states = findStateByName (sess, "exists");
	    if ( states != null && !states.isEmpty() )
	        state = (DeviceStateExists)states.iterator().next();
	    if ( state == null ) {
	        state = new DeviceStateExists();
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
    	return "exists";
    }

    protected java.lang.String getStateId() {
    	return "45";
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

		
    public void check_mecrdeviceusage() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking DeviceState.mecrdeviceusage...passed");
    }

		
    public void check_mecroutcome() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking DeviceState.mecroutcome...passed");
    }

		
    public void check_meendoutcome() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking DeviceState.meendoutcome...passed");
    }

		
    public void check_meenddeviceusage() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking DeviceState.meenddeviceusage...passed");
    }

		
    public void check_mecrpropertyoutcome() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking DeviceState.mecrpropertyoutcome...passed");
    }

		
    public void check_meendpropertyoutcome() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking DeviceState.meendpropertyoutcome...passed");
    }

		
    public void check_devicedeployment() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking DeviceState.devicedeployment...passed");
    }

	
	
    public void meenddevice (org.hibernate.Session sess, Device object) throws org.hibernate.HibernateException {
        DeviceStateEnded state = DeviceStateEnded.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 1 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 11");
        object.setState(state);
    }

	
    public void mecrdeviceusage (org.hibernate.Session sess, Device object) throws org.hibernate.HibernateException {
        DeviceStateExists state = DeviceStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 1 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 22");
        object.setState(state);
    }

	
    public void mecroutcome (org.hibernate.Session sess, Device object) throws org.hibernate.HibernateException {
        DeviceStateExists state = DeviceStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 1 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 24");
        object.setState(state);
    }

	
    public void meendoutcome (org.hibernate.Session sess, Device object) throws org.hibernate.HibernateException {
        DeviceStateExists state = DeviceStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 1 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 36");
        object.setState(state);
    }

	
    public void meenddeviceusage (org.hibernate.Session sess, Device object) throws org.hibernate.HibernateException {
        DeviceStateExists state = DeviceStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 1 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 34");
        object.setState(state);
    }

	
    public void mecrpropertyoutcome (org.hibernate.Session sess, Device object) throws org.hibernate.HibernateException {
        DeviceStateExists state = DeviceStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 1 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 28");
        object.setState(state);
    }

	
    public void meendpropertyoutcome (org.hibernate.Session sess, Device object) throws org.hibernate.HibernateException {
        DeviceStateExists state = DeviceStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 1 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 40");
        object.setState(state);
    }

	
    public void devicedeployment (org.hibernate.Session sess, Device object) throws org.hibernate.HibernateException {
        DeviceStateDeployed state = DeviceStateDeployed.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 1 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 14");
        object.setState(state);
    }

	
}
