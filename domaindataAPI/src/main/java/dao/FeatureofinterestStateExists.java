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
 *    discriminator-value="FeatureofinterestStateExists"
 */
public class FeatureofinterestStateExists extends FeatureofinterestState {

    public static FeatureofinterestStateExists getObject (org.hibernate.Session sess) throws org.hibernate.HibernateException {
	    FeatureofinterestStateExists state = null;
	    // Search in database
	    java.util.Collection states = findStateByName (sess, "exists");
	    if ( states != null && !states.isEmpty() )
	        state = (FeatureofinterestStateExists)states.iterator().next();
	    if ( state == null ) {
	        state = new FeatureofinterestStateExists();
	        // Save in database
	        sess.save (state);
	    }
	    return state;
    }

    private static java.util.Collection findStateByName (org.hibernate.Session sess, java.lang.String statename)
        throws org.hibernate.HibernateException {
    
        org.hibernate.Query q = sess.createQuery("from dao.FeatureofinterestState as c where c.name = ?");
    	q.setString (0, statename);
        return q.list();
    }
    
    protected java.lang.String getStateName() {
    	return "exists";
    }

    protected java.lang.String getStateId() {
    	return "102";
    }

    public boolean isInitialState() {
    	return false;
    }

    public boolean isFinalState() {
    	return false;
    }


		
    public void check_meendfeatureofinterest() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking FeatureofinterestState.meendfeatureofinterest...passed");
    }

		
    public void check_devicedeployment() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking FeatureofinterestState.devicedeployment...passed");
    }

		
    public void check_deviceundeployment() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking FeatureofinterestState.deviceundeployment...passed");
    }

		
    public void check_mecrdeviceresult() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking FeatureofinterestState.mecrdeviceresult...passed");
    }

		
    public void check_mecrdeviceusage() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking FeatureofinterestState.mecrdeviceusage...passed");
    }

		
    public void check_mecrplatformdeployment() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking FeatureofinterestState.mecrplatformdeployment...passed");
    }

		
    public void check_mecrproperty() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking FeatureofinterestState.mecrproperty...passed");
    }

		
    public void check_meenddeviceresult() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking FeatureofinterestState.meenddeviceresult...passed");
    }

		
    public void check_meenddeviceusage() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking FeatureofinterestState.meenddeviceusage...passed");
    }

		
    public void check_meendplatformdeployment() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking FeatureofinterestState.meendplatformdeployment...passed");
    }

		
    public void check_meendproperty() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking FeatureofinterestState.meendproperty...passed");
    }

		
    public void check_mesetready() {
        // No exception is thrown anymore...
        MerodeLogger.logln ("Checking FeatureofinterestState.mesetready...passed");
    }

	
	
    public void meendfeatureofinterest (org.hibernate.Session sess, Featureofinterest object) throws org.hibernate.HibernateException {
        FeatureofinterestStateEnded state = FeatureofinterestStateEnded.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 97 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 109");
        object.setState(state);
    }

	
    public void devicedeployment (org.hibernate.Session sess, Featureofinterest object) throws org.hibernate.HibernateException {
        FeatureofinterestStateExists state = FeatureofinterestStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 97 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 112");
        object.setState(state);
    }

	
    public void deviceundeployment (org.hibernate.Session sess, Featureofinterest object) throws org.hibernate.HibernateException {
        FeatureofinterestStateExists state = FeatureofinterestStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 97 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 116");
        object.setState(state);
    }

	
    public void mecrdeviceresult (org.hibernate.Session sess, Featureofinterest object) throws org.hibernate.HibernateException {
        FeatureofinterestStateExists state = FeatureofinterestStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 97 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 120");
        object.setState(state);
    }

	
    public void mecrdeviceusage (org.hibernate.Session sess, Featureofinterest object) throws org.hibernate.HibernateException {
        FeatureofinterestStateExists state = FeatureofinterestStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 97 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 124");
        object.setState(state);
    }

	
    public void mecrplatformdeployment (org.hibernate.Session sess, Featureofinterest object) throws org.hibernate.HibernateException {
        FeatureofinterestStateExists state = FeatureofinterestStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 97 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 128");
        object.setState(state);
    }

	
    public void mecrproperty (org.hibernate.Session sess, Featureofinterest object) throws org.hibernate.HibernateException {
        FeatureofinterestStateExists state = FeatureofinterestStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 97 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 130");
        object.setState(state);
    }

	
    public void meenddeviceresult (org.hibernate.Session sess, Featureofinterest object) throws org.hibernate.HibernateException {
        FeatureofinterestStateExists state = FeatureofinterestStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 97 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 132");
        object.setState(state);
    }

	
    public void meenddeviceusage (org.hibernate.Session sess, Featureofinterest object) throws org.hibernate.HibernateException {
        FeatureofinterestStateExists state = FeatureofinterestStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 97 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 136");
        object.setState(state);
    }

	
    public void meendplatformdeployment (org.hibernate.Session sess, Featureofinterest object) throws org.hibernate.HibernateException {
        FeatureofinterestStateExists state = FeatureofinterestStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 97 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 140");
        object.setState(state);
    }

	
    public void meendproperty (org.hibernate.Session sess, Featureofinterest object) throws org.hibernate.HibernateException {
        FeatureofinterestStateExists state = FeatureofinterestStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 97 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 142");
        object.setState(state);
    }

	
    public void mesetready (org.hibernate.Session sess, Featureofinterest object) throws org.hibernate.HibernateException {
        FeatureofinterestStateExists state = FeatureofinterestStateExists.getObject(sess);
        MerodeLogger.logln ("[" + Instant.now() + "]" + " Object " + object.getId() + " of object type 97 changed from state " + getStateId() + " to state " + state.getStateId() + " using method 144");
        object.setState(state);
    }

	
}
