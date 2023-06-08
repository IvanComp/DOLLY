/** 
 * This class was automatically generated  
 * using a Merode XML model and Apache Velocity
 * 
 * Merode Code Generator 2.0
 * @author MERODE Team-members
 */

package dao;
import java.time.*;

import validation.ErrorValidator;
/**
 * @hibernate.class
 *     table="HIB_PROPERTY_STATE"
 * @hibernate.discriminator
 *     column="class"
 */
public abstract class PropertyState 
	implements java.io.Serializable {

    /**
     * @hibernate.id
     *     generator-class="uuid.hex"
     *     column="ID"
     *     type="java.lang.String"
     *
     * @hibernate.column
     *     name="ID"
     *     sql-type="VARCHAR(256)"
     */
    public java.lang.String getId()
    {
        return this.id;
    }

    public void setId(java.lang.String id)
    {
        this.id = id;
    }

    private java.lang.String id;

    /**
     * @hibernate.property
     *     column="NAME"
     *     type="java.lang.String"
     *
     * @hibernate.column
     *     name="NAME"
     *     sql-type="VARCHAR(256)"
     */
    public java.lang.String getName()
    {
        return getStateName();
    }

    protected abstract java.lang.String getStateId();

    public void setName(java.lang.String name)
    {
    // do nothing
    }

    protected abstract java.lang.String getStateName();
    
    public abstract boolean isInitialState();
    public abstract boolean isFinalState();

    // Put the object in the "allocated" state, i.e. the actual initial state
    public static void setInitialState (org.hibernate.Session sess, Property object) throws org.hibernate.HibernateException {
        PropertyStateAllocated state = PropertyStateAllocated.getObject(sess);
        object.setState(state);
    }
    // Control operations to check whether an event may occur.
    // By default an exception is thrown; it is taken away in
    // a subclass for all appropriate methods.
    public void check_mecrproperty() throws MerodeException {
        MerodeLogger.logln ("Checking PropertyState.mecrproperty...");
        throw new MerodeException("[" + Instant.now() + "]" + " Can't execute event 289 with name MEcrProperty for object type 187 named Property in state " + getStateId() + " called " + getStateName());
    }
    public void check_meendproperty() throws MerodeException {
        MerodeLogger.logln ("Checking PropertyState.meendproperty...");
        throw new MerodeException ("[" + Instant.now() + "]" + " Can't execute event 290 with name MEendProperty for object type 187 named Property in state " + getStateId() + " called " + getStateName());
    }
    public void check_mecrdeviceresult() throws MerodeException {
        MerodeLogger.logln ("Checking PropertyState.mecrdeviceresult...");
        throw new MerodeException("[" + Instant.now() + "]" + " Can't execute event 283 with name MEcrDeviceResult for object type 187 named Property in state " + getStateId() + " called " + getStateName());
    }
    public void check_meenddeviceresult() throws MerodeException {
        MerodeLogger.logln ("Checking PropertyState.meenddeviceresult...");
        throw new MerodeException("[" + Instant.now() + "]" + " Can't execute event 284 with name MEendDeviceResult for object type 187 named Property in state " + getStateId() + " called " + getStateName());
    }
    public void check_mecrpropertyoutcome() throws MerodeException {
        MerodeLogger.logln ("Checking PropertyState.mecrpropertyoutcome...");
        throw new MerodeException("[" + Instant.now() + "]" + " Can't execute event 295 with name MEcrPropertyOutcome for object type 187 named Property in state " + getStateId() + " called " + getStateName());
    }
    public void check_meendpropertyoutcome() throws MerodeException {
        MerodeLogger.logln ("Checking PropertyState.meendpropertyoutcome...");
        throw new MerodeException("[" + Instant.now() + "]" + " Can't execute event 296 with name MEendPropertyOutcome for object type 187 named Property in state " + getStateId() + " called " + getStateName());
    }

    // Put the object in the correct state.
    public void mecrproperty (org.hibernate.Session sess, Property object) throws org.hibernate.HibernateException{
	}
    public void meendproperty (org.hibernate.Session sess, Property object) throws org.hibernate.HibernateException{
    }
    public void mecrdeviceresult (org.hibernate.Session sess, Property object) throws org.hibernate.HibernateException{
    }
    public void meenddeviceresult (org.hibernate.Session sess, Property object) throws org.hibernate.HibernateException{
    }
    public void mecrpropertyoutcome (org.hibernate.Session sess, Property object) throws org.hibernate.HibernateException{
    }
    public void meendpropertyoutcome (org.hibernate.Session sess, Property object) throws org.hibernate.HibernateException{
    }


}
