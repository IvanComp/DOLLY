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
 *     table="HIB_REGISTEREDDEVICE"
 * @hibernate.discriminator
 *     column="class"
 * 
 *
 */

public abstract class Registereddevice 
    implements java.io.Serializable {
    
    // --------------- state --------------------------

    /**
     * @hibernate.many-to-one
     *     column="REGISTEREDDEVICE_STATE_FK"
     *     class="dao.RegistereddeviceState"
     */
    public RegistereddeviceState getState () {
        return this.state;
    }
    
    public void setState (RegistereddeviceState state){
        this.state = state;
    }

    protected RegistereddeviceState state;

    // --------------- attributes ---------------------
    private java.lang.String devicename;
    /**
     * 
     *
     * @hibernate.property
     *     column="DEVICENAME"
     *     type="java.lang.String"
     *
     * @hibernate.column
     *     name="DEVICENAME"
     *     sql-type="VARCHAR(256)"
     */
    public java.lang.String getDevicename(){
        return this.devicename;
    }

    public void setDevicename(java.lang.String devicename){
        this.devicename = devicename;
    }
    private java.lang.String platformname;
    /**
     * 
     *
     * @hibernate.property
     *     column="PLATFORMNAME"
     *     type="java.lang.String"
     *
     * @hibernate.column
     *     name="PLATFORMNAME"
     *     sql-type="VARCHAR(256)"
     */
    public java.lang.String getPlatformname(){
        return this.platformname;
    }

    public void setPlatformname(java.lang.String platformname){
        this.platformname = platformname;
    }
    private java.lang.String starttime;
    /**
     * 
     *
     * @hibernate.property
     *     column="STARTTIME"
     *     type="java.lang.String"
     *
     * @hibernate.column
     *     name="STARTTIME"
     *     sql-type="VARCHAR(256)"
     */
    public java.lang.String getStarttime(){
        return this.starttime;
    }

    public void setStarttime(java.lang.String starttime){
        this.starttime = starttime;
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
    *     column="PLATFORM_FK"
    *     class="dao.Platform"
    */
    public dao.Platform getPlatform() {
        return this.platform;
    }
    
    public void setPlatform(dao.Platform platform){
        this.platform = platform;
    }

    private dao.Platform platform;
	  
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
     *     role="deviceusage"
     *     lazy="false"
     * @hibernate.collection-key
     *     column="REGISTEREDDEVICE_FK"
     * @hibernate.collection-one-to-many
     *     class="dao.Deviceusage"
     */
    public java.util.Collection getDeviceusage(){
        return this.deviceusage;
    }

    protected void setDeviceusage(java.util.Collection deviceusage){
        this.deviceusage = deviceusage;
    }

    private java.util.Collection deviceusage;
    public void attachDeviceusage (dao.Deviceusage object) {
        this.deviceusage.add(object);
    }
	// ---------- precondition of business methods  -----------
	// --- o/c ---
    public abstract void check_mecrregistereddevice() throws MerodeException;
	// --- o/e --- 
    public abstract void check_meendregistereddevice() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_mecrdeviceresult() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_meenddeviceresult() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_mecrdeviceusage() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_meenddeviceusage() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_deviceundeployment() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_devicedeployment() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_mesetready() throws MerodeException;

    // ---------------- business methods  ----------------------


	/**
     *  --- o/c --- 
     */
	public abstract void mecrregistereddevice( java.lang.String Devicename,
		 java.lang.String Platformname,
		java.lang.String Starttime)
    	throws MerodeException;


	
/**
     *  --- o/e ---
     */
    public abstract void meendregistereddevice()
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
    * --- o/dpnds ---
    */
    public abstract void mesetready()
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
