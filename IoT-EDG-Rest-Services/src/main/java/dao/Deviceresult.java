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
 *     table="HIB_DEVICERESULT"
 * @hibernate.discriminator
 *     column="class"
 * 
 *
 */

public abstract class Deviceresult 
    implements java.io.Serializable {
    
    // --------------- state --------------------------

    /**
     * @hibernate.many-to-one
     *     column="DEVICERESULT_STATE_FK"
     *     class="dao.DeviceresultState"
     */
    public DeviceresultState getState () {
        return this.state;
    }
    
    public void setState (DeviceresultState state){
        this.state = state;
    }

    protected DeviceresultState state;

    // --------------- attributes ---------------------
    private java.lang.String value;
    /**
     * 
     *
     * @hibernate.property
     *     column="VALUE"
     *     type="java.lang.String"
     *
     * @hibernate.column
     *     name="VALUE"
     *     sql-type="VARCHAR(256)"
     */
    public java.lang.String getValue(){
        return this.value;
    }

    public void setValue(java.lang.String value){
        this.value = value;
    }
    private java.lang.String unit;
    /**
     * 
     *
     * @hibernate.property
     *     column="UNIT"
     *     type="java.lang.String"
     *
     * @hibernate.column
     *     name="UNIT"
     *     sql-type="VARCHAR(256)"
     */
    public java.lang.String getUnit(){
        return this.unit;
    }

    public void setUnit(java.lang.String unit){
        this.unit = unit;
    }
    private java.lang.String producedby;
    /**
     * 
     *
     * @hibernate.property
     *     column="PRODUCEDBY"
     *     type="java.lang.String"
     *
     * @hibernate.column
     *     name="PRODUCEDBY"
     *     sql-type="VARCHAR(256)"
     */
    public java.lang.String getProducedby(){
        return this.producedby;
    }

    public void setProducedby(java.lang.String producedby){
        this.producedby = producedby;
    }
    private java.lang.String observedproperty;
    /**
     * 
     *
     * @hibernate.property
     *     column="OBSERVEDPROPERTY"
     *     type="java.lang.String"
     *
     * @hibernate.column
     *     name="OBSERVEDPROPERTY"
     *     sql-type="VARCHAR(256)"
     */
    public java.lang.String getObservedproperty(){
        return this.observedproperty;
    }

    public void setObservedproperty(java.lang.String observedproperty){
        this.observedproperty = observedproperty;
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
    private java.lang.String endtime;
    /**
     * 
     *
     * @hibernate.property
     *     column="ENDTIME"
     *     type="java.lang.String"
     *
     * @hibernate.column
     *     name="ENDTIME"
     *     sql-type="VARCHAR(256)"
     */
    public java.lang.String getEndtime(){
        return this.endtime;
    }

    public void setEndtime(java.lang.String endtime){
        this.endtime = endtime;
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
	// ---------- precondition of business methods  -----------
	// --- o/c ---
    public abstract void check_mecrdeviceresult() throws MerodeException;
	// --- o/e --- 
    public abstract void check_meenddeviceresult() throws MerodeException;

    // ---------------- business methods  ----------------------


	/**
     *  --- o/c --- 
     */
	public abstract void mecrdeviceresult( java.lang.String Value,
		 java.lang.String Unit,
		 java.lang.String Producedby,
		 java.lang.String Observedproperty,
		 java.lang.String Starttime,
		java.lang.String Endtime)
    	throws MerodeException;


	
/**
     *  --- o/e ---
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
