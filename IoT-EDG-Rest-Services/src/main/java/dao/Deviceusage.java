/** 
 * This class was automatically generated  
 * using a Merode XML model and Apache Velocity
 * 
 * Merode Code Generator 2.0
 * @author MERODE Team-members
 */

package dao;

import java.util.HashSet;
import java.util.Iterator;



/**
 * 
 *
 * @hibernate.class
 *     table="HIB_DEVICEUSAGE"
 * @hibernate.discriminator
 *     column="class"
 * 
 *
 */

public abstract class Deviceusage 
    implements java.io.Serializable {
    
    // --------------- state --------------------------

    /**
     * @hibernate.many-to-one
     *     column="DEVICEUSAGE_STATE_FK"
     *     class="dao.DeviceusageState"
     */
    public DeviceusageState getState () {
        return this.state;
    }
    
    public void setState (DeviceusageState state){
        this.state = state;
    }

    protected DeviceusageState state;

    // --------------- attributes ---------------------
    private java.lang.String name;
    /**
     * 
     *
     * @hibernate.property
     *     column="NAME"
     *     type="java.lang.String"
     *
     * @hibernate.column
     *     name="NAME"
     *     sql-type="VARCHAR(256)"
     */
    public java.lang.String getName(){
        return this.name;
    }

    public void setName(java.lang.String name){
        this.name = name;
    }
    private java.lang.String id;

    /**
     * 
     *
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

    public void setId(java.lang.String id){
        this.id = id;
    }


    // ------------- relations ------------------

	  
   /**
    * 
    *
    * @hibernate.many-to-one
    *     column="FEATUREOFINTEREST_FK"
    *     class="dao.Featureofinterest"
    */
    public dao.Featureofinterest getFeatureofinterest() {
        return this.featureofinterest;
    }
    
    public void setFeatureofinterest(dao.Featureofinterest featureofinterest){
        this.featureofinterest = featureofinterest;
    }

    private dao.Featureofinterest featureofinterest;
	  
   /**
    * 
    *
    * @hibernate.many-to-one
    *     column="DEVICE_FK"
    *     class="dao.Device"
    */
    public dao.Device getDevice() {
        return this.device;
    }
    
    public void setDevice(dao.Device device){
        this.device = device;
    }

    private dao.Device device;
	/**
     * 
     *
     * @hibernate.set
     *     role="propertyoutcome"
     *     lazy="false"
     * @hibernate.collection-key
     *     column="DEVICEUSAGE_FK"
     * @hibernate.collection-one-to-many
     *     class="dao.Propertyoutcome"
     */
    public java.util.Collection getPropertyoutcome(){
        return this.propertyoutcome;
    }

    protected void setPropertyoutcome(java.util.Collection propertyoutcome){
        this.propertyoutcome = propertyoutcome;
    }

    private java.util.Collection propertyoutcome;
    public void attachPropertyoutcome (dao.Propertyoutcome object) {
        this.propertyoutcome.add(object);
    }
	// ---------- precondition of business methods  -----------
	// --- o/c ---
    public abstract void check_mecrdeviceusage() throws MerodeException;

	// --- o/m --- 
    public abstract void check_devicedeployment() throws MerodeException;
	// --- o/e --- 
    public abstract void check_meenddeviceusage() throws MerodeException;
	// --- o/e --- 
    public abstract void check_deviceundeployment() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_mecrdeviceresult() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_meenddeviceresult() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_mecrpropertyoutcome() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_meendpropertyoutcome() throws MerodeException;

    // ---------------- business methods  ----------------------


	/**
     *  --- o/c --- 
     */
	public abstract void mecrdeviceusage(java.lang.String Name)
    	throws MerodeException;


	/**
     *  --- o/m ---
     */
	public abstract void devicedeployment(java.lang.String Name)
    	throws MerodeException;

	
/**
     *  --- o/e ---
     */
    public abstract void meenddeviceusage()
        throws MerodeException;
	
/**
     *  --- o/e ---
     */
    public abstract void deviceundeployment()
        throws MerodeException;
	
		
   /**
    * --- o/dpnds ---
    */
    public abstract void mecrdeviceresult()
        throws MerodeException;	

		
   /**
    * --- o/dpnds ---
    */
    public abstract void meenddeviceresult()
        throws MerodeException;	

		
   /**
    * --- o/dpnds ---
    */
    public abstract void mecrpropertyoutcome()
        throws MerodeException;	

		
   /**
    * --- o/dpnds ---
    */
    public abstract void meendpropertyoutcome()
        throws MerodeException;	

	
    /**
     * checking if an object is consistent 
     * against mandatory relationship(s)
     */
    public String getMandatoryInconsistency() {
		java.util.Set<String> inconsistentDpnds = new HashSet();
		String commaSeparated = "";
		Iterator it;
		//boolean isConsistent = false;

		for (String s : inconsistentDpnds){
			commaSeparated += 
				"".equals(commaSeparated) ?
					s : "," + s ;
		}

		return commaSeparated;
	}

}
