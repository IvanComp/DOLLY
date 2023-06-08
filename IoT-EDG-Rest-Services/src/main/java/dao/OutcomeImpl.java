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
 *    discriminator-value="OutcomeImpl"
 */
public class OutcomeImpl extends Outcome {

	private String livingDependents = "";

	// constructor
    public OutcomeImpl() {
    }

    // ---------------- business methods  ----------------------
    // concrete business methods that were declared
    // abstract in class Outcome ...

	//--- o/c ---  
    public void mecroutcome(java.lang.String Name) {
        MerodeLogger.logln("Executing Outcome.mecroutcome (O/C)");
        setName (Name);
    }


		//--- o/e ---
	public void meendoutcome() {
        MerodeLogger.logln("Executing Outcome.meendoutcome() (O/E)");
    }
	
		//--- o/dpnd ---
    public void mecrdeviceresult() {
        MerodeLogger.logln("Executing Outcome.mecrdeviceresult() (A/M)");
    }

		//--- o/dpnd ---
    public void meenddeviceresult() {
        MerodeLogger.logln("Executing Outcome.meenddeviceresult() (A/M)");
    }

		//--- o/dpnd ---
    public void mecrpropertyoutcome() {
        MerodeLogger.logln("Executing Outcome.mecrpropertyoutcome() (A/M)");
    }

		//--- o/dpnd ---
    public void meendpropertyoutcome() {
        MerodeLogger.logln("Executing Outcome.meendpropertyoutcome() (A/M)");
    }

	

    // ---------- precondition of business methods  -----------
    
	//--- o/e --- 
    public void check_mecroutcome() throws MerodeException {
        MerodeLogger.log("Checking Outcome.mecroutcome...");
        MerodeLogger.logln("passed");
    }

	//--- o/e ---
    public void check_meendoutcome() throws MerodeException {
        MerodeLogger.log("Checking Outcome.meendoutcome()...");

        if(hasLivingDependents()){
            throw new MerodeException ("Precondition violation in ending method \"meendoutcome()\" in class Outcome:\nObject has living dependents " + livingDependents);
        }
        MerodeLogger.logln("passed");
    }
   

	//--- o/dpnds ---
    public void check_mecrdeviceresult() throws MerodeException {
        MerodeLogger.log("Checking Outcome.mecrdeviceresult()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_meenddeviceresult() throws MerodeException {
        MerodeLogger.log("Checking Outcome.meenddeviceresult()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_mecrpropertyoutcome() throws MerodeException {
        MerodeLogger.log("Checking Outcome.mecrpropertyoutcome()...");
        MerodeLogger.logln("passed");
    }
	//--- o/dpnds ---
    public void check_meendpropertyoutcome() throws MerodeException {
        MerodeLogger.log("Checking Outcome.meendpropertyoutcome()...");
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
