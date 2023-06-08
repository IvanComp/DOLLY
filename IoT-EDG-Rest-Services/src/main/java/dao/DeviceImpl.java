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
 *    discriminator-value="DeviceImpl"
 */
public class DeviceImpl extends Device {

	private String livingDependents = "";

	// constructor
    public DeviceImpl() {
    }

    // ---------------- business methods  ----------------------
    // concrete business methods that were declared
    // abstract in class Device ...

	//--- o/c ---  
    public void mecrdevice(java.lang.String Name) {
        MerodeLogger.logln("Executing Device.mecrdevice (O/C)");
        setName (Name);
    }


		//--- o/e ---
	public void meenddevice() {
        MerodeLogger.logln("Executing Device.meenddevice() (O/E)");
    }
	
		//--- o/dpnd ---
    public void mecrdeviceresult() {
        MerodeLogger.logln("Executing Device.mecrdeviceresult() (A/M)");
    }

		//--- o/dpnd ---
    public void meenddeviceresult() {
        MerodeLogger.logln("Executing Device.meenddeviceresult() (A/M)");
    }

		//--- o/dpnd ---
    public void mecroutcome() {
        MerodeLogger.logln("Executing Device.mecroutcome() (A/M)");
    }

		//--- o/dpnd ---
    public void meendoutcome() {
        MerodeLogger.logln("Executing Device.meendoutcome() (A/M)");
    }

		//--- o/dpnd ---
    public void mecrpropertyoutcome() {
        MerodeLogger.logln("Executing Device.mecrpropertyoutcome() (A/M)");
    }

		//--- o/dpnd ---
    public void meendpropertyoutcome() {
        MerodeLogger.logln("Executing Device.meendpropertyoutcome() (A/M)");
    }

		//--- o/dpnd ---
    public void mecrdeviceusage() {
        MerodeLogger.logln("Executing Device.mecrdeviceusage() (A/M)");
    }

		//--- o/dpnd ---
    public void meenddeviceusage() {
        MerodeLogger.logln("Executing Device.meenddeviceusage() (A/M)");
    }

		//--- o/dpnd ---
    public void deviceundeployment() {
        MerodeLogger.logln("Executing Device.deviceundeployment() (A/M)");
    }

		//--- o/dpnd ---
    public void devicedeployment() {
        MerodeLogger.logln("Executing Device.devicedeployment() (A/M)");
    }

	

    // ---------- precondition of business methods  -----------
    
	//--- o/e --- 
    public void check_mecrdevice() throws MerodeException {
        MerodeLogger.log("Checking Device.mecrdevice...");
        MerodeLogger.logln("passed");
    }

	//--- o/e ---
    public void check_meenddevice() throws MerodeException {
        MerodeLogger.log("Checking Device.meenddevice()...");

        if(hasLivingDependents()){
            throw new MerodeException ("Precondition violation in ending method \"meenddevice()\" in class Device:\nObject has living dependents " + livingDependents);
        }
        MerodeLogger.logln("passed");
    }
   

	//--- o/dpnds ---
    public void check_mecrdeviceresult() throws MerodeException {
        MerodeLogger.log("Checking Device.mecrdeviceresult()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_meenddeviceresult() throws MerodeException {
        MerodeLogger.log("Checking Device.meenddeviceresult()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_mecroutcome() throws MerodeException {
        MerodeLogger.log("Checking Device.mecroutcome()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_meendoutcome() throws MerodeException {
        MerodeLogger.log("Checking Device.meendoutcome()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_mecrpropertyoutcome() throws MerodeException {
        MerodeLogger.log("Checking Device.mecrpropertyoutcome()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_meendpropertyoutcome() throws MerodeException {
        MerodeLogger.log("Checking Device.meendpropertyoutcome()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_mecrdeviceusage() throws MerodeException {
        MerodeLogger.log("Checking Device.mecrdeviceusage()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_meenddeviceusage() throws MerodeException {
        MerodeLogger.log("Checking Device.meenddeviceusage()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_deviceundeployment() throws MerodeException {
        MerodeLogger.log("Checking Device.deviceundeployment()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_devicedeployment() throws MerodeException {
        MerodeLogger.log("Checking Device.devicedeployment()...");
        MerodeLogger.logln("passed");
    }

    


    // -------------------------------------------------------
    
    private boolean hasLivingDependents() {
    	Set <String> dependents = new HashSet();

        java.util.Collection col_outcome = getOutcome();
        if (col_outcome != null){
        	if (!col_outcome.isEmpty()){
		        java.util.Iterator i_outcome = col_outcome.iterator();
		        while (i_outcome.hasNext()) {
		            dao.Outcome obj_outcome = (dao.Outcome)i_outcome.next();
		            if (!obj_outcome.getState().isFinalState()){
		            	dependents.add("Outcome");
		            }
		        }        	
        	}
        }


        java.util.Collection col_deviceusage = getDeviceusage();
        if (col_deviceusage != null){
        	if (!col_deviceusage.isEmpty()){
		        java.util.Iterator i_deviceusage = col_deviceusage.iterator();
		        while (i_deviceusage.hasNext()) {
		            dao.Deviceusage obj_deviceusage = (dao.Deviceusage)i_deviceusage.next();
		            if (!obj_deviceusage.getState().isFinalState()){
		            	dependents.add("Deviceusage");
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
