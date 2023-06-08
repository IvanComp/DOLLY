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
 *    discriminator-value="PropertyoutcomeImpl"
 */
public class PropertyoutcomeImpl extends Propertyoutcome {

	private String livingDependents = "";

	// constructor
    public PropertyoutcomeImpl() {
    }

    // ---------------- business methods  ----------------------
    // concrete business methods that were declared
    // abstract in class Propertyoutcome ...

	//--- o/c ---  
    public void mecrpropertyoutcome(java.lang.String Name) {
        MerodeLogger.logln("Executing Propertyoutcome.mecrpropertyoutcome (O/C)");
        setName (Name);
    }


		//--- o/e ---
	public void meendpropertyoutcome() {
        MerodeLogger.logln("Executing Propertyoutcome.meendpropertyoutcome() (O/E)");
    }
	
		//--- o/dpnd ---
    public void mecrdeviceresult() {
        MerodeLogger.logln("Executing Propertyoutcome.mecrdeviceresult() (A/M)");
    }

		//--- o/dpnd ---
    public void meenddeviceresult() {
        MerodeLogger.logln("Executing Propertyoutcome.meenddeviceresult() (A/M)");
    }

	

    // ---------- precondition of business methods  -----------
    
	//--- o/e --- 
    public void check_mecrpropertyoutcome() throws MerodeException {
        MerodeLogger.log("Checking Propertyoutcome.mecrpropertyoutcome...");
        MerodeLogger.logln("passed");
    }

	//--- o/e ---
    public void check_meendpropertyoutcome() throws MerodeException {
        MerodeLogger.log("Checking Propertyoutcome.meendpropertyoutcome()...");

        if(hasLivingDependents()){
            throw new MerodeException ("Precondition violation in ending method \"meendpropertyoutcome()\" in class Propertyoutcome:\nObject has living dependents " + livingDependents);
        }
        MerodeLogger.logln("passed");
    }
   

	//--- o/dpnds ---
    public void check_mecrdeviceresult() throws MerodeException {
        MerodeLogger.log("Checking Propertyoutcome.mecrdeviceresult()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_meenddeviceresult() throws MerodeException {
        MerodeLogger.log("Checking Propertyoutcome.meenddeviceresult()...");
        MerodeLogger.logln("passed");
    }

    


    // -------------------------------------------------------
    
    private boolean hasLivingDependents() {
    	Set <String> dependents = new HashSet();

        java.util.Collection col_deviceresult = getDeviceresult();
        if (col_deviceresult != null){
        	if (!col_deviceresult.isEmpty()){
		        java.util.Iterator i_deviceresult = col_deviceresult.iterator();
		        while (i_deviceresult.hasNext()) {
		            dao.Deviceresult obj_deviceresult = (dao.Deviceresult)i_deviceresult.next();
		            if (!obj_deviceresult.getState().isFinalState()){
		            	dependents.add("Deviceresult");
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
