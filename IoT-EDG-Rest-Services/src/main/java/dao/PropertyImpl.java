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
 *    discriminator-value="PropertyImpl"
 */
public class PropertyImpl extends Property {

	private String livingDependents = "";

	// constructor
    public PropertyImpl() {
    }

    // ---------------- business methods  ----------------------
    // concrete business methods that were declared
    // abstract in class Property ...

	//--- o/c ---  
    public void mecrproperty(java.lang.String Name) {
        MerodeLogger.logln("Executing Property.mecrproperty (O/C)");
        setName (Name);
    }


		//--- o/e ---
	public void meendproperty() {
        MerodeLogger.logln("Executing Property.meendproperty() (O/E)");
    }
	
		//--- o/dpnd ---
    public void mecrdeviceresult() {
        MerodeLogger.logln("Executing Property.mecrdeviceresult() (A/M)");
    }

		//--- o/dpnd ---
    public void meenddeviceresult() {
        MerodeLogger.logln("Executing Property.meenddeviceresult() (A/M)");
    }

		//--- o/dpnd ---
    public void mecrpropertyoutcome() {
        MerodeLogger.logln("Executing Property.mecrpropertyoutcome() (A/M)");
    }

		//--- o/dpnd ---
    public void meendpropertyoutcome() {
        MerodeLogger.logln("Executing Property.meendpropertyoutcome() (A/M)");
    }

	

    // ---------- precondition of business methods  -----------
    
	//--- o/e --- 
    public void check_mecrproperty() throws MerodeException {
        MerodeLogger.log("Checking Property.mecrproperty...");
        MerodeLogger.logln("passed");
    }

	//--- o/e ---
    public void check_meendproperty() throws MerodeException {
        MerodeLogger.log("Checking Property.meendproperty()...");

        if(hasLivingDependents()){
            throw new MerodeException ("Precondition violation in ending method \"meendproperty()\" in class Property:\nObject has living dependents " + livingDependents);
        }
        MerodeLogger.logln("passed");
    }
   

	//--- o/dpnds ---
    public void check_mecrdeviceresult() throws MerodeException {
        MerodeLogger.log("Checking Property.mecrdeviceresult()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_meenddeviceresult() throws MerodeException {
        MerodeLogger.log("Checking Property.meenddeviceresult()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_mecrpropertyoutcome() throws MerodeException {
        MerodeLogger.log("Checking Property.mecrpropertyoutcome()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_meendpropertyoutcome() throws MerodeException {
        MerodeLogger.log("Checking Property.meendpropertyoutcome()...");
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
