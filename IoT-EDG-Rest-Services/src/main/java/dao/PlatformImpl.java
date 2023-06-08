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
 *    discriminator-value="PlatformImpl"
 */
public class PlatformImpl extends Platform {

	private String livingDependents = "";

	// constructor
    public PlatformImpl() {
    }

    // ---------------- business methods  ----------------------
    // concrete business methods that were declared
    // abstract in class Platform ...

	//--- o/c ---  
    public void mecrplatform(java.lang.String Name) {
        MerodeLogger.logln("Executing Platform.mecrplatform (O/C)");
        setName (Name);
    }


		//--- o/e ---
	public void meendplatform() {
        MerodeLogger.logln("Executing Platform.meendplatform() (O/E)");
    }
	
		//--- o/dpnd ---
    public void mecrdeviceresult() {
        MerodeLogger.logln("Executing Platform.mecrdeviceresult() (A/M)");
    }

		//--- o/dpnd ---
    public void meenddeviceresult() {
        MerodeLogger.logln("Executing Platform.meenddeviceresult() (A/M)");
    }

		//--- o/dpnd ---
    public void mecrdevice() {
        MerodeLogger.logln("Executing Platform.mecrdevice() (A/M)");
    }

		//--- o/dpnd ---
    public void meenddevice() {
        MerodeLogger.logln("Executing Platform.meenddevice() (A/M)");
    }

		//--- o/dpnd ---
    public void mecroutcome() {
        MerodeLogger.logln("Executing Platform.mecroutcome() (A/M)");
    }

		//--- o/dpnd ---
    public void meendoutcome() {
        MerodeLogger.logln("Executing Platform.meendoutcome() (A/M)");
    }

		//--- o/dpnd ---
    public void mecrpropertyoutcome() {
        MerodeLogger.logln("Executing Platform.mecrpropertyoutcome() (A/M)");
    }

		//--- o/dpnd ---
    public void meendpropertyoutcome() {
        MerodeLogger.logln("Executing Platform.meendpropertyoutcome() (A/M)");
    }

		//--- o/dpnd ---
    public void mecrdeviceusage() {
        MerodeLogger.logln("Executing Platform.mecrdeviceusage() (A/M)");
    }

		//--- o/dpnd ---
    public void meenddeviceusage() {
        MerodeLogger.logln("Executing Platform.meenddeviceusage() (A/M)");
    }

		//--- o/dpnd ---
    public void deviceundeployment() {
        MerodeLogger.logln("Executing Platform.deviceundeployment() (A/M)");
    }

		//--- o/dpnd ---
    public void devicedeployment() {
        MerodeLogger.logln("Executing Platform.devicedeployment() (A/M)");
    }

	

    // ---------- precondition of business methods  -----------
    
	//--- o/e --- 
    public void check_mecrplatform() throws MerodeException {
        MerodeLogger.log("Checking Platform.mecrplatform...");
        MerodeLogger.logln("passed");
    }

	//--- o/e ---
    public void check_meendplatform() throws MerodeException {
        MerodeLogger.log("Checking Platform.meendplatform()...");

        if(hasLivingDependents()){
            throw new MerodeException ("Precondition violation in ending method \"meendplatform()\" in class Platform:\nObject has living dependents " + livingDependents);
        }
        MerodeLogger.logln("passed");
    }
   

	//--- o/dpnds ---
    public void check_mecrdeviceresult() throws MerodeException {
        MerodeLogger.log("Checking Platform.mecrdeviceresult()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_meenddeviceresult() throws MerodeException {
        MerodeLogger.log("Checking Platform.meenddeviceresult()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_mecrdevice() throws MerodeException {
        MerodeLogger.log("Checking Platform.mecrdevice()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_meenddevice() throws MerodeException {
        MerodeLogger.log("Checking Platform.meenddevice()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_mecroutcome() throws MerodeException {
        MerodeLogger.log("Checking Platform.mecroutcome()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_meendoutcome() throws MerodeException {
        MerodeLogger.log("Checking Platform.meendoutcome()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_mecrpropertyoutcome() throws MerodeException {
        MerodeLogger.log("Checking Platform.mecrpropertyoutcome()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_meendpropertyoutcome() throws MerodeException {
        MerodeLogger.log("Checking Platform.meendpropertyoutcome()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_mecrdeviceusage() throws MerodeException {
        MerodeLogger.log("Checking Platform.mecrdeviceusage()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_meenddeviceusage() throws MerodeException {
        MerodeLogger.log("Checking Platform.meenddeviceusage()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_deviceundeployment() throws MerodeException {
        MerodeLogger.log("Checking Platform.deviceundeployment()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_devicedeployment() throws MerodeException {
        MerodeLogger.log("Checking Platform.devicedeployment()...");
        MerodeLogger.logln("passed");
    }

    


    // -------------------------------------------------------
    
    private boolean hasLivingDependents() {
    	Set <String> dependents = new HashSet();

        java.util.Collection col_device = getDevice();
        if (col_device != null){
        	if (!col_device.isEmpty()){
		        java.util.Iterator i_device = col_device.iterator();
		        while (i_device.hasNext()) {
		            dao.Device obj_device = (dao.Device)i_device.next();
		            if (!obj_device.getState().isFinalState()){
		            	dependents.add("Device");
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
