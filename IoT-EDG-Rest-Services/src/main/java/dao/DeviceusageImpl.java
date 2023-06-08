/** 
 * This class was automatically generated  
 * using a Merode XML model and Apache Velocity
 * 
 * Merode Code Generator 2.0
 * @author MERODE Team-members
 */

package dao;

import java.util.HashSet;
import java.util.Set;

/**
 * @hibernate.subclass
 *    discriminator-value="DeviceusageImpl"
 */
public class DeviceusageImpl extends Deviceusage {

	private String livingDependents = "";

	// constructor
    public DeviceusageImpl() {
    }

    // ---------------- business methods  ----------------------
    // concrete business methods that were declared
    // abstract in class Deviceusage ...

	//--- o/c ---  
    public void mecrdeviceusage(java.lang.String Name) {
        MerodeLogger.logln("Executing Deviceusage.mecrdeviceusage (O/C)");
        setName (Name);
    }
	//--- o/m --- 
    public void devicedeployment(java.lang.String Name) {
        MerodeLogger.logln("Executing Deviceusage.devicedeployment (O/M)");
        setName (Name);
    }  
    


		//--- o/e ---
	public void meenddeviceusage() {
        MerodeLogger.logln("Executing Deviceusage.meenddeviceusage() (O/E)");
    }
		//--- o/e ---
	public void deviceundeployment() {
        MerodeLogger.logln("Executing Deviceusage.deviceundeployment() (O/E)");
    }
	
		//--- o/dpnd ---
    public void mecrdeviceresult() {
        MerodeLogger.logln("Executing Deviceusage.mecrdeviceresult() (A/M)");
    }

		//--- o/dpnd ---
    public void meenddeviceresult() {
        MerodeLogger.logln("Executing Deviceusage.meenddeviceresult() (A/M)");
    }

		//--- o/dpnd ---
    public void mecrpropertyoutcome() {
        MerodeLogger.logln("Executing Deviceusage.mecrpropertyoutcome() (A/M)");
    }

		//--- o/dpnd ---
    public void meendpropertyoutcome() {
        MerodeLogger.logln("Executing Deviceusage.meendpropertyoutcome() (A/M)");
    }

	

    // ---------- precondition of business methods  -----------
    
	//--- o/e --- 
    public void check_mecrdeviceusage() throws MerodeException {
        MerodeLogger.log("Checking Deviceusage.mecrdeviceusage...");
        MerodeLogger.logln("passed");
    }
		//--- o/m --- 
        public void check_devicedeployment() throws MerodeException {
        MerodeLogger.log("Checking Deviceusage.devicedeployment...");
        MerodeLogger.logln("passed");
    }

	//--- o/e ---
    public void check_meenddeviceusage() throws MerodeException {
        MerodeLogger.log("Checking Deviceusage.meenddeviceusage()...");

        if(hasLivingDependents()){
            throw new MerodeException ("Precondition violation in ending method \"meenddeviceusage()\" in class Deviceusage:\nObject has living dependents " + livingDependents);
        }
        MerodeLogger.logln("passed");
    }
   
	//--- o/e ---
    public void check_deviceundeployment() throws MerodeException {
        MerodeLogger.log("Checking Deviceusage.deviceundeployment()...");

        if(hasLivingDependents()){
            throw new MerodeException ("Precondition violation in ending method \"deviceundeployment()\" in class Deviceusage:\nObject has living dependents " + livingDependents);
        }
        MerodeLogger.logln("passed");
    }
   

	//--- o/dpnds ---
    public void check_mecrdeviceresult() throws MerodeException {
        MerodeLogger.log("Checking Deviceusage.mecrdeviceresult()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_meenddeviceresult() throws MerodeException {
        MerodeLogger.log("Checking Deviceusage.meenddeviceresult()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_mecrpropertyoutcome() throws MerodeException {
        MerodeLogger.log("Checking Deviceusage.mecrpropertyoutcome()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_meendpropertyoutcome() throws MerodeException {
        MerodeLogger.log("Checking Deviceusage.meendpropertyoutcome()...");
        MerodeLogger.logln("passed");
    }

    


    // -------------------------------------------------------
    
    private boolean hasLivingDependents() {
    	Set <String> dependents = new HashSet();

        java.util.Collection col_propertyoutcome = getPropertyoutcome();
        if (col_propertyoutcome != null){
        	if (!col_propertyoutcome.isEmpty()){
		        java.util.Iterator i_propertyoutcome = col_propertyoutcome.iterator();
		        while (i_propertyoutcome.hasNext()) {
		            dao.Propertyoutcome obj_propertyoutcome = (dao.Propertyoutcome)i_propertyoutcome.next();
		            if (!obj_propertyoutcome.getState().isFinalState()){
		            	dependents.add("Propertyoutcome");
		            }
		        }        	
        	}
        }

        for (String s : dependents){
            livingDependents = "".equals(livingDependents) ? s
        			: livingDependents + ", " + s;       	
        }
        return dependents.size() > 0;
    }
 
 
    // ---------- precondition of inherited business methods  -----------
    




    // ---------------- inherited business methods  ----------------------
    



	     
}
