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
 *    discriminator-value="FeatureofinterestImpl"
 */
public class FeatureofinterestImpl extends Featureofinterest {

	private String livingDependents = "";

	// constructor
    public FeatureofinterestImpl() {
    }

    // ---------------- business methods  ----------------------
    // concrete business methods that were declared
    // abstract in class Featureofinterest ...

	//--- o/c ---  
    public void mecrfeatureofinterest(java.lang.String Name) {
        MerodeLogger.logln("Executing Featureofinterest.mecrfeatureofinterest (O/C)");
        setName (Name);
    }


		//--- o/e ---
	public void meendfeatureofinterest() {
        MerodeLogger.logln("Executing Featureofinterest.meendfeatureofinterest() (O/E)");
    }
	
		//--- o/dpnd ---
    public void mecrdeviceresult() {
        MerodeLogger.logln("Executing Featureofinterest.mecrdeviceresult() (A/M)");
    }

		//--- o/dpnd ---
    public void meenddeviceresult() {
        MerodeLogger.logln("Executing Featureofinterest.meenddeviceresult() (A/M)");
    }

		//--- o/dpnd ---
    public void mecrpropertyoutcome() {
        MerodeLogger.logln("Executing Featureofinterest.mecrpropertyoutcome() (A/M)");
    }

		//--- o/dpnd ---
    public void meendpropertyoutcome() {
        MerodeLogger.logln("Executing Featureofinterest.meendpropertyoutcome() (A/M)");
    }

		//--- o/dpnd ---
    public void mecrproperty() {
        MerodeLogger.logln("Executing Featureofinterest.mecrproperty() (A/M)");
    }

		//--- o/dpnd ---
    public void meendproperty() {
        MerodeLogger.logln("Executing Featureofinterest.meendproperty() (A/M)");
    }

		//--- o/dpnd ---
    public void mecrdeviceusage() {
        MerodeLogger.logln("Executing Featureofinterest.mecrdeviceusage() (A/M)");
    }

		//--- o/dpnd ---
    public void meenddeviceusage() {
        MerodeLogger.logln("Executing Featureofinterest.meenddeviceusage() (A/M)");
    }

		//--- o/dpnd ---
    public void deviceundeployment() {
        MerodeLogger.logln("Executing Featureofinterest.deviceundeployment() (A/M)");
    }

		//--- o/dpnd ---
    public void devicedeployment() {
        MerodeLogger.logln("Executing Featureofinterest.devicedeployment() (A/M)");
    }

	

    // ---------- precondition of business methods  -----------
    
	//--- o/e --- 
    public void check_mecrfeatureofinterest() throws MerodeException {
        MerodeLogger.log("Checking Featureofinterest.mecrfeatureofinterest...");
        MerodeLogger.logln("passed");
    }

	//--- o/e ---
    public void check_meendfeatureofinterest() throws MerodeException {
        MerodeLogger.log("Checking Featureofinterest.meendfeatureofinterest()...");

        if(hasLivingDependents()){
            throw new MerodeException ("Precondition violation in ending method \"meendfeatureofinterest()\" in class Featureofinterest:\nObject has living dependents " + livingDependents);
        }
        MerodeLogger.logln("passed");
    }
   

	//--- o/dpnds ---
    public void check_mecrdeviceresult() throws MerodeException {
        MerodeLogger.log("Checking Featureofinterest.mecrdeviceresult()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_meenddeviceresult() throws MerodeException {
        MerodeLogger.log("Checking Featureofinterest.meenddeviceresult()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_mecrpropertyoutcome() throws MerodeException {
        MerodeLogger.log("Checking Featureofinterest.mecrpropertyoutcome()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_meendpropertyoutcome() throws MerodeException {
        MerodeLogger.log("Checking Featureofinterest.meendpropertyoutcome()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_mecrproperty() throws MerodeException {
        MerodeLogger.log("Checking Featureofinterest.mecrproperty()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_meendproperty() throws MerodeException {
        MerodeLogger.log("Checking Featureofinterest.meendproperty()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_mecrdeviceusage() throws MerodeException {
        MerodeLogger.log("Checking Featureofinterest.mecrdeviceusage()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_meenddeviceusage() throws MerodeException {
        MerodeLogger.log("Checking Featureofinterest.meenddeviceusage()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_deviceundeployment() throws MerodeException {
        MerodeLogger.log("Checking Featureofinterest.deviceundeployment()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_devicedeployment() throws MerodeException {
        MerodeLogger.log("Checking Featureofinterest.devicedeployment()...");
        MerodeLogger.logln("passed");
    }

    


    // -------------------------------------------------------
    
    private boolean hasLivingDependents() {
    	Set <String> dependents = new HashSet();

        java.util.Collection col_property = getProperty();
        if (col_property != null){
        	if (!col_property.isEmpty()){
		        java.util.Iterator i_property = col_property.iterator();
		        while (i_property.hasNext()) {
		            dao.Property obj_property = (dao.Property)i_property.next();
		            if (!obj_property.getState().isFinalState()){
		            	dependents.add("Property");
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
