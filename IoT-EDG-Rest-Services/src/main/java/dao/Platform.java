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
 *     table="HIB_PLATFORM"
 * @hibernate.discriminator
 *     column="class"
 * 
 *
 */

public abstract class Platform 
    implements java.io.Serializable {
    
    // --------------- state --------------------------

    /**
     * @hibernate.many-to-one
     *     column="PLATFORM_STATE_FK"
     *     class="dao.PlatformState"
     */
    public PlatformState getState () {
        return this.state;
    }
    
    public void setState (PlatformState state){
        this.state = state;
    }

    protected PlatformState state;

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
     * @hibernate.set
     *     role="device"
     *     lazy="false"
     * @hibernate.collection-key
     *     column="PLATFORM_FK"
     * @hibernate.collection-one-to-many
     *     class="dao.Device"
     */
    public java.util.Collection getDevice(){
        return this.device;
    }

    protected void setDevice(java.util.Collection device){
        this.device = device;
    }

    private java.util.Collection device;
    public void attachDevice (dao.Device object) {
        this.device.add(object);
    }
	// ---------- precondition of business methods  -----------
	// --- o/c ---
    public abstract void check_mecrplatform() throws MerodeException;
	// --- o/e --- 
    public abstract void check_meendplatform() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_mecrdeviceresult() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_meenddeviceresult() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_mecrdevice() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_meenddevice() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_mecroutcome() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_meendoutcome() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_mecrpropertyoutcome() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_meendpropertyoutcome() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_mecrdeviceusage() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_meenddeviceusage() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_deviceundeployment() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_devicedeployment() throws MerodeException;

    // ---------------- business methods  ----------------------


	/**
     *  --- o/c --- 
     */
	public abstract void mecrplatform(java.lang.String Name)
    	throws MerodeException;


	
/**
     *  --- o/e ---
     */
    public abstract void meendplatform()
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
    public abstract void mecrdevice()
        throws MerodeException;	

		
   /**
    * --- o/dpnds ---
    */
    public abstract void meenddevice()
        throws MerodeException;	

		
   /**
    * --- o/dpnds ---
    */
    public abstract void mecroutcome()
        throws MerodeException;	

		
   /**
    * --- o/dpnds ---
    */
    public abstract void meendoutcome()
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
    * --- o/dpnds ---
    */
    public abstract void mecrdeviceusage()
        throws MerodeException;	

		
   /**
    * --- o/dpnds ---
    */
    public abstract void meenddeviceusage()
        throws MerodeException;	

		
   /**
    * --- o/dpnds ---
    */
    public abstract void deviceundeployment()
        throws MerodeException;	

		
   /**
    * --- o/dpnds ---
    */
    public abstract void devicedeployment()
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
