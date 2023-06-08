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
 *     table="HIB_PROPERTYOUTCOME"
 * @hibernate.discriminator
 *     column="class"
 * 
 *
 */

public abstract class Propertyoutcome 
    implements java.io.Serializable {
    
    // --------------- state --------------------------

    /**
     * @hibernate.many-to-one
     *     column="PROPERTYOUTCOME_STATE_FK"
     *     class="dao.PropertyoutcomeState"
     */
    public PropertyoutcomeState getState () {
        return this.state;
    }
    
    public void setState (PropertyoutcomeState state){
        this.state = state;
    }

    protected PropertyoutcomeState state;

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
    *     column="OUTCOME_FK"
    *     class="dao.Outcome"
    */
    public dao.Outcome getOutcome() {
        return this.outcome;
    }
    
    public void setOutcome(dao.Outcome outcome){
        this.outcome = outcome;
    }

    private dao.Outcome outcome;
	  
   /**
    * 
    *
    * @hibernate.many-to-one
    *     column="PROPERTY_FK"
    *     class="dao.Property"
    */
    public dao.Property getProperty() {
        return this.property;
    }
    
    public void setProperty(dao.Property property){
        this.property = property;
    }

    private dao.Property property;
	  
   /**
    * 
    *
    * @hibernate.many-to-one
    *     column="DEVICEUSAGE_FK"
    *     class="dao.Deviceusage"
    */
    public dao.Deviceusage getDeviceusage() {
        return this.deviceusage;
    }
    
    public void setDeviceusage(dao.Deviceusage deviceusage){
        this.deviceusage = deviceusage;
    }

    private dao.Deviceusage deviceusage;
	/**
     * 
     *
     * @hibernate.set
     *     role="deviceresult"
     *     lazy="false"
     * @hibernate.collection-key
     *     column="PROPERTYOUTCOME_FK"
     * @hibernate.collection-one-to-many
     *     class="dao.Deviceresult"
     */
    public java.util.Collection getDeviceresult(){
        return this.deviceresult;
    }

    protected void setDeviceresult(java.util.Collection deviceresult){
        this.deviceresult = deviceresult;
    }

    private java.util.Collection deviceresult;
    public void attachDeviceresult (dao.Deviceresult object) {
        this.deviceresult.add(object);
    }
	// ---------- precondition of business methods  -----------
	// --- o/c ---
    public abstract void check_mecrpropertyoutcome() throws MerodeException;
	// --- o/e --- 
    public abstract void check_meendpropertyoutcome() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_mecrdeviceresult() throws MerodeException;
	// --- o/dpnds --- 
    public abstract void check_meenddeviceresult() throws MerodeException;

    // ---------------- business methods  ----------------------


	/**
     *  --- o/c --- 
     */
	public abstract void mecrpropertyoutcome(java.lang.String Name)
    	throws MerodeException;


	
/**
     *  --- o/e ---
     */
    public abstract void meendpropertyoutcome()
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
