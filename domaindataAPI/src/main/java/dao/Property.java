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
 *     table="HIB_PROPERTY"
 * @hibernate.discriminator
 *     column="class"
 * 
 *
 */

public abstract class Property 
    implements java.io.Serializable {
    
    // --------------- state --------------------------

    /**
     * @hibernate.many-to-one
     *     column="PROPERTY_STATE_FK"
     *     class="dao.PropertyState"
     */
    public PropertyState getState () {
        return this.state;
    }
    
    public void setState (PropertyState state){
        this.state = state;
    }

    protected PropertyState state;

    // --------------- attributes ---------------------
    private java.lang.String featureofinterestname;
    /**
     * 
     *
     * @hibernate.property
     *     column="FEATUREOFINTERESTNAME"
     *     type="java.lang.String"
     *
     * @hibernate.column
     *     name="FEATUREOFINTERESTNAME"
     *     sql-type="VARCHAR(256)"
     */
    public java.lang.String getFeatureofinterestname(){
        return this.featureofinterestname;
    }

    public void setFeatureofinterestname(java.lang.String featureofinterestname){
        this.featureofinterestname = featureofinterestname;
    }
    private java.lang.String description;
    /**
     * 
     *
     * @hibernate.property
     *     column="DESCRIPTION"
     *     type="java.lang.String"
     *
     * @hibernate.column
     *     name="DESCRIPTION"
     *     sql-type="VARCHAR(256)"
     */
    public java.lang.String getDescription(){
        return this.description;
    }

    public void setDescription(java.lang.String description){
        this.description = description;
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
     * @hibernate.set
     *     role="deviceusage"
     *     lazy="false"
     * @hibernate.collection-key
     *     column="PROPERTY_FK"
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
    public abstract void check_mecrproperty() throws MerodeException;
	// --- o/e --- 
    public abstract void check_meendproperty() throws MerodeException;
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
	public abstract void mecrproperty( java.lang.String Featureofinterestname,
		java.lang.String Description)
    	throws MerodeException;


	
/**
     *  --- o/e ---
     */
    public abstract void meendproperty()
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
