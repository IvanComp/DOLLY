package helpers;

/** 
 * This class was automatically generated  
 * using a Merode XML model and Apache Velocity
 * 
 * Merode Code Generator 2.0
 * @author Nick Scheynen
 */
 
import dao.Registereddevice;
import dao.RegistereddeviceImpl;
import dao.Procedure;
import dao.ProcedureImpl;
import dao.Deviceresult;
import dao.DeviceresultImpl;
import dao.Featureofinterest;
import dao.FeatureofinterestImpl;
import dao.Platform;
import dao.PlatformImpl;
import dao.Property;
import dao.PropertyImpl;
import dao.Deviceusage;
import dao.DeviceusageImpl;
import dao.Platformdeployment;
import dao.PlatformdeploymentImpl;
import dao.Device;
import dao.DeviceImpl;
import java.util.*;

public abstract class ResponseFactory {

	
	// Registereddevice Responses
	public static Collection makeAllRegistereddevice(Collection data) {
		Collection result = new ArrayList<HashMap>();
		for( Iterator<RegistereddeviceImpl> i = data.iterator(); i.hasNext(); ) {
			HashMap item = makeRegistereddevice(i.next());
			result.add(item);
		}
		return result;
	}
	
	public static HashMap makeRegistereddevice(Registereddevice registereddevice) {
		LinkedHashMap result = new LinkedHashMap();
			
		// Fill in attributes
		result.put("id", registereddevice.getId());
		result.put("devicename", registereddevice.getDevicename());
		result.put("platformname", registereddevice.getPlatformname());
		result.put("starttime", registereddevice.getStarttime());
		result.put("state", registereddevice.getState().getName());

		// Add dependents
		HashMap dependents = new HashMap();
		dependents.put("deviceusage", makeRegistereddeviceDepDeviceusage(registereddevice.getDeviceusage()));
		// Add dependents to result
		result.put("dependents", dependents);

		// Add masters
		HashMap masters = new HashMap();
		masters.put("platform", makeRegistereddeviceMasPlatform(registereddevice.getPlatform()));
		masters.put("device", makeRegistereddeviceMasDevice(registereddevice.getDevice()));
		// Add masters to result
		result.put("masters", masters);

		// Add events
		result.put("events", makeRegistereddeviceEvents(registereddevice));
		
		return result;
	}

	// Registereddevice Helpers
	private static Collection makeRegistereddeviceDepDeviceusage(Collection deviceusages) {
		ArrayList result = new ArrayList();
		for(Iterator<DeviceusageImpl> i = deviceusages.iterator(); i.hasNext();) {
			DeviceusageImpl deviceusage = i.next();
			HashMap item = new HashMap<String, String>();
			item.put("url", "/deviceusage/"+deviceusage.getId());
			result.add(item);
		}
		return result;
	}

	private static HashMap makeRegistereddeviceMasPlatform(Platform platform) {
		HashMap item = new HashMap<String, String>();
		if(platform != null) {
			item.put("url", "/platform/" + platform.getId()); 
		}
		return item;
	}
	private static HashMap makeRegistereddeviceMasDevice(Device device) {
		HashMap item = new HashMap<String, String>();
		if(device != null) {
			item.put("url", "/device/" + device.getId()); 
		}
		return item;
	}

	private static Collection makeRegistereddeviceEvents(Registereddevice registereddevice) {
		ArrayList events = new ArrayList();
		switch (registereddevice.getState().getName()) {
			case "allocated":
				break;
			case "ended":
				break;
			case "deployed":
				break;
			case "exists":
				break;
		}
		return events;
	}	
	
	// Procedure Responses
	public static Collection makeAllProcedure(Collection data) {
		Collection result = new ArrayList<HashMap>();
		for( Iterator<ProcedureImpl> i = data.iterator(); i.hasNext(); ) {
			HashMap item = makeProcedure(i.next());
			result.add(item);
		}
		return result;
	}
	
	public static HashMap makeProcedure(Procedure procedure) {
		LinkedHashMap result = new LinkedHashMap();
			
		// Fill in attributes
		result.put("id", procedure.getId());
		result.put("devicename", procedure.getDevicename());
		result.put("description", procedure.getDescription());
		result.put("state", procedure.getState().getName());

		// Add dependents
		HashMap dependents = new HashMap();
		dependents.put("deviceusage", makeProcedureDepDeviceusage(procedure.getDeviceusage()));
		// Add dependents to result
		result.put("dependents", dependents);

		// Add masters
		HashMap masters = new HashMap();
		masters.put("device", makeProcedureMasDevice(procedure.getDevice()));
		// Add masters to result
		result.put("masters", masters);

		// Add events
		result.put("events", makeProcedureEvents(procedure));
		
		return result;
	}

	// Procedure Helpers
	private static Collection makeProcedureDepDeviceusage(Collection deviceusages) {
		ArrayList result = new ArrayList();
		for(Iterator<DeviceusageImpl> i = deviceusages.iterator(); i.hasNext();) {
			DeviceusageImpl deviceusage = i.next();
			HashMap item = new HashMap<String, String>();
			item.put("url", "/deviceusage/"+deviceusage.getId());
			result.add(item);
		}
		return result;
	}

	private static HashMap makeProcedureMasDevice(Device device) {
		HashMap item = new HashMap<String, String>();
		if(device != null) {
			item.put("url", "/device/" + device.getId()); 
		}
		return item;
	}

	private static Collection makeProcedureEvents(Procedure procedure) {
		ArrayList events = new ArrayList();
		switch (procedure.getState().getName()) {
			case "allocated":
				break;
			case "exists":
				break;
			case "ended":
				break;
		}
		return events;
	}	
	
	// Deviceresult Responses
	public static Collection makeAllDeviceresult(Collection data) {
		Collection result = new ArrayList<HashMap>();
		for( Iterator<DeviceresultImpl> i = data.iterator(); i.hasNext(); ) {
			HashMap item = makeDeviceresult(i.next());
			result.add(item);
		}
		return result;
	}
	
	public static HashMap makeDeviceresult(Deviceresult deviceresult) {
		LinkedHashMap result = new LinkedHashMap();
			
		// Fill in attributes
		result.put("id", deviceresult.getId());
		result.put("value", deviceresult.getValue());
		result.put("unit", deviceresult.getUnit());
		result.put("producedby", deviceresult.getProducedby());
		result.put("observedproperty", deviceresult.getObservedproperty());
		result.put("starttime", deviceresult.getStarttime());
		result.put("endtime", deviceresult.getEndtime());
		result.put("state", deviceresult.getState().getName());


		// Add masters
		HashMap masters = new HashMap();
		masters.put("deviceusage", makeDeviceresultMasDeviceusage(deviceresult.getDeviceusage()));
		// Add masters to result
		result.put("masters", masters);

		// Add events
		result.put("events", makeDeviceresultEvents(deviceresult));
		
		return result;
	}

	// Deviceresult Helpers

	private static HashMap makeDeviceresultMasDeviceusage(Deviceusage deviceusage) {
		HashMap item = new HashMap<String, String>();
		if(deviceusage != null) {
			item.put("url", "/deviceusage/" + deviceusage.getId()); 
		}
		return item;
	}

	private static Collection makeDeviceresultEvents(Deviceresult deviceresult) {
		ArrayList events = new ArrayList();
		switch (deviceresult.getState().getName()) {
			case "allocated":
				break;
			case "exists":
				break;
			case "ended":
				break;
		}
		return events;
	}	
	
	// Featureofinterest Responses
	public static Collection makeAllFeatureofinterest(Collection data) {
		Collection result = new ArrayList<HashMap>();
		for( Iterator<FeatureofinterestImpl> i = data.iterator(); i.hasNext(); ) {
			HashMap item = makeFeatureofinterest(i.next());
			result.add(item);
		}
		return result;
	}
	
	public static HashMap makeFeatureofinterest(Featureofinterest featureofinterest) {
		LinkedHashMap result = new LinkedHashMap();
			
		// Fill in attributes
		result.put("id", featureofinterest.getId());
		result.put("name", featureofinterest.getName());
		result.put("description", featureofinterest.getDescription());
		result.put("state", featureofinterest.getState().getName());

		// Add dependents
		HashMap dependents = new HashMap();
		dependents.put("property", makeFeatureofinterestDepProperty(featureofinterest.getProperty()));
		dependents.put("platformdeployment", makeFeatureofinterestDepPlatformdeployment(featureofinterest.getPlatformdeployment()));
		// Add dependents to result
		result.put("dependents", dependents);


		// Add events
		result.put("events", makeFeatureofinterestEvents(featureofinterest));
		
		return result;
	}

	// Featureofinterest Helpers
	private static Collection makeFeatureofinterestDepPlatformdeployment(Collection platformdeployments) {
		ArrayList result = new ArrayList();
		for(Iterator<PlatformdeploymentImpl> i = platformdeployments.iterator(); i.hasNext();) {
			PlatformdeploymentImpl platformdeployment = i.next();
			HashMap item = new HashMap<String, String>();
			item.put("url", "/platformdeployment/"+platformdeployment.getId());
			result.add(item);
		}
		return result;
	}
	private static Collection makeFeatureofinterestDepProperty(Collection propertys) {
		ArrayList result = new ArrayList();
		for(Iterator<PropertyImpl> i = propertys.iterator(); i.hasNext();) {
			PropertyImpl property = i.next();
			HashMap item = new HashMap<String, String>();
			item.put("url", "/property/"+property.getId());
			result.add(item);
		}
		return result;
	}


	private static Collection makeFeatureofinterestEvents(Featureofinterest featureofinterest) {
		ArrayList events = new ArrayList();
		switch (featureofinterest.getState().getName()) {
			case "allocated":
				break;
			case "exists":
				break;
			case "ended":
				break;
		}
		return events;
	}	
	
	// Platform Responses
	public static Collection makeAllPlatform(Collection data) {
		Collection result = new ArrayList<HashMap>();
		for( Iterator<PlatformImpl> i = data.iterator(); i.hasNext(); ) {
			HashMap item = makePlatform(i.next());
			result.add(item);
		}
		return result;
	}
	
	public static HashMap makePlatform(Platform platform) {
		LinkedHashMap result = new LinkedHashMap();
			
		// Fill in attributes
		result.put("id", platform.getId());
		result.put("name", platform.getName());
		result.put("description", platform.getDescription());
		result.put("hostedby", platform.getHostedby());
		result.put("state", platform.getState().getName());

		// Add dependents
		HashMap dependents = new HashMap();
		dependents.put("registereddevice", makePlatformDepRegistereddevice(platform.getRegistereddevice()));
		dependents.put("platformdeployment", makePlatformDepPlatformdeployment(platform.getPlatformdeployment()));
		// Add dependents to result
		result.put("dependents", dependents);


		// Add events
		result.put("events", makePlatformEvents(platform));
		
		return result;
	}

	// Platform Helpers
	private static Collection makePlatformDepPlatformdeployment(Collection platformdeployments) {
		ArrayList result = new ArrayList();
		for(Iterator<PlatformdeploymentImpl> i = platformdeployments.iterator(); i.hasNext();) {
			PlatformdeploymentImpl platformdeployment = i.next();
			HashMap item = new HashMap<String, String>();
			item.put("url", "/platformdeployment/"+platformdeployment.getId());
			result.add(item);
		}
		return result;
	}
	private static Collection makePlatformDepRegistereddevice(Collection registereddevices) {
		ArrayList result = new ArrayList();
		for(Iterator<RegistereddeviceImpl> i = registereddevices.iterator(); i.hasNext();) {
			RegistereddeviceImpl registereddevice = i.next();
			HashMap item = new HashMap<String, String>();
			item.put("url", "/registereddevice/"+registereddevice.getId());
			result.add(item);
		}
		return result;
	}


	private static Collection makePlatformEvents(Platform platform) {
		ArrayList events = new ArrayList();
		switch (platform.getState().getName()) {
			case "allocated":
				break;
			case "exists":
				break;
			case "ended":
				break;
		}
		return events;
	}	
	
	// Property Responses
	public static Collection makeAllProperty(Collection data) {
		Collection result = new ArrayList<HashMap>();
		for( Iterator<PropertyImpl> i = data.iterator(); i.hasNext(); ) {
			HashMap item = makeProperty(i.next());
			result.add(item);
		}
		return result;
	}
	
	public static HashMap makeProperty(Property property) {
		LinkedHashMap result = new LinkedHashMap();
			
		// Fill in attributes
		result.put("id", property.getId());
		result.put("featureofinterestname", property.getFeatureofinterestname());
		result.put("description", property.getDescription());
		result.put("state", property.getState().getName());

		// Add dependents
		HashMap dependents = new HashMap();
		dependents.put("deviceusage", makePropertyDepDeviceusage(property.getDeviceusage()));
		// Add dependents to result
		result.put("dependents", dependents);

		// Add masters
		HashMap masters = new HashMap();
		masters.put("featureofinterest", makePropertyMasFeatureofinterest(property.getFeatureofinterest()));
		// Add masters to result
		result.put("masters", masters);

		// Add events
		result.put("events", makePropertyEvents(property));
		
		return result;
	}

	// Property Helpers
	private static Collection makePropertyDepDeviceusage(Collection deviceusages) {
		ArrayList result = new ArrayList();
		for(Iterator<DeviceusageImpl> i = deviceusages.iterator(); i.hasNext();) {
			DeviceusageImpl deviceusage = i.next();
			HashMap item = new HashMap<String, String>();
			item.put("url", "/deviceusage/"+deviceusage.getId());
			result.add(item);
		}
		return result;
	}

	private static HashMap makePropertyMasFeatureofinterest(Featureofinterest featureofinterest) {
		HashMap item = new HashMap<String, String>();
		if(featureofinterest != null) {
			item.put("url", "/featureofinterest/" + featureofinterest.getId()); 
		}
		return item;
	}

	private static Collection makePropertyEvents(Property property) {
		ArrayList events = new ArrayList();
		switch (property.getState().getName()) {
			case "allocated":
				break;
			case "exists":
				break;
			case "ended":
				break;
		}
		return events;
	}	
	
	// Deviceusage Responses
	public static Collection makeAllDeviceusage(Collection data) {
		Collection result = new ArrayList<HashMap>();
		for( Iterator<DeviceusageImpl> i = data.iterator(); i.hasNext(); ) {
			HashMap item = makeDeviceusage(i.next());
			result.add(item);
		}
		return result;
	}
	
	public static HashMap makeDeviceusage(Deviceusage deviceusage) {
		LinkedHashMap result = new LinkedHashMap();
			
		// Fill in attributes
		result.put("id", deviceusage.getId());
		result.put("usagetype", deviceusage.getUsagetype());
		result.put("starttime", deviceusage.getStarttime());
		result.put("endtime", deviceusage.getEndtime());
		result.put("state", deviceusage.getState().getName());

		// Add dependents
		HashMap dependents = new HashMap();
		dependents.put("deviceresult", makeDeviceusageDepDeviceresult(deviceusage.getDeviceresult()));
		// Add dependents to result
		result.put("dependents", dependents);

		// Add masters
		HashMap masters = new HashMap();
		masters.put("platformdeployment", makeDeviceusageMasPlatformdeployment(deviceusage.getPlatformdeployment()));
		masters.put("registereddevice", makeDeviceusageMasRegistereddevice(deviceusage.getRegistereddevice()));
		masters.put("procedure", makeDeviceusageMasProcedure(deviceusage.getProcedure()));
		masters.put("property", makeDeviceusageMasProperty(deviceusage.getProperty()));
		// Add masters to result
		result.put("masters", masters);

		// Add events
		result.put("events", makeDeviceusageEvents(deviceusage));
		
		return result;
	}

	// Deviceusage Helpers
	private static Collection makeDeviceusageDepDeviceresult(Collection deviceresults) {
		ArrayList result = new ArrayList();
		for(Iterator<DeviceresultImpl> i = deviceresults.iterator(); i.hasNext();) {
			DeviceresultImpl deviceresult = i.next();
			HashMap item = new HashMap<String, String>();
			item.put("url", "/deviceresult/"+deviceresult.getId());
			result.add(item);
		}
		return result;
	}

	private static HashMap makeDeviceusageMasPlatformdeployment(Platformdeployment platformdeployment) {
		HashMap item = new HashMap<String, String>();
		if(platformdeployment != null) {
			item.put("url", "/platformdeployment/" + platformdeployment.getId()); 
		}
		return item;
	}
	private static HashMap makeDeviceusageMasRegistereddevice(Registereddevice registereddevice) {
		HashMap item = new HashMap<String, String>();
		if(registereddevice != null) {
			item.put("url", "/registereddevice/" + registereddevice.getId()); 
		}
		return item;
	}
	private static HashMap makeDeviceusageMasProcedure(Procedure procedure) {
		HashMap item = new HashMap<String, String>();
		if(procedure != null) {
			item.put("url", "/procedure/" + procedure.getId()); 
		}
		return item;
	}
	private static HashMap makeDeviceusageMasProperty(Property property) {
		HashMap item = new HashMap<String, String>();
		if(property != null) {
			item.put("url", "/property/" + property.getId()); 
		}
		return item;
	}

	private static Collection makeDeviceusageEvents(Deviceusage deviceusage) {
		ArrayList events = new ArrayList();
		switch (deviceusage.getState().getName()) {
			case "allocated":
				break;
			case "planned":
				break;
			case "ended":
				break;
			case "started":
				break;
			case "ready":
				break;
		}
		return events;
	}	
	
	// Platformdeployment Responses
	public static Collection makeAllPlatformdeployment(Collection data) {
		Collection result = new ArrayList<HashMap>();
		for( Iterator<PlatformdeploymentImpl> i = data.iterator(); i.hasNext(); ) {
			HashMap item = makePlatformdeployment(i.next());
			result.add(item);
		}
		return result;
	}
	
	public static HashMap makePlatformdeployment(Platformdeployment platformdeployment) {
		LinkedHashMap result = new LinkedHashMap();
			
		// Fill in attributes
		result.put("id", platformdeployment.getId());
		result.put("platformname", platformdeployment.getPlatformname());
		result.put("featureofinterestname", platformdeployment.getFeatureofinterestname());
		result.put("starttime", platformdeployment.getStarttime());
		result.put("state", platformdeployment.getState().getName());

		// Add dependents
		HashMap dependents = new HashMap();
		dependents.put("deviceusage", makePlatformdeploymentDepDeviceusage(platformdeployment.getDeviceusage()));
		// Add dependents to result
		result.put("dependents", dependents);

		// Add masters
		HashMap masters = new HashMap();
		masters.put("platform", makePlatformdeploymentMasPlatform(platformdeployment.getPlatform()));
		masters.put("featureofinterest", makePlatformdeploymentMasFeatureofinterest(platformdeployment.getFeatureofinterest()));
		// Add masters to result
		result.put("masters", masters);

		// Add events
		result.put("events", makePlatformdeploymentEvents(platformdeployment));
		
		return result;
	}

	// Platformdeployment Helpers
	private static Collection makePlatformdeploymentDepDeviceusage(Collection deviceusages) {
		ArrayList result = new ArrayList();
		for(Iterator<DeviceusageImpl> i = deviceusages.iterator(); i.hasNext();) {
			DeviceusageImpl deviceusage = i.next();
			HashMap item = new HashMap<String, String>();
			item.put("url", "/deviceusage/"+deviceusage.getId());
			result.add(item);
		}
		return result;
	}

	private static HashMap makePlatformdeploymentMasPlatform(Platform platform) {
		HashMap item = new HashMap<String, String>();
		if(platform != null) {
			item.put("url", "/platform/" + platform.getId()); 
		}
		return item;
	}
	private static HashMap makePlatformdeploymentMasFeatureofinterest(Featureofinterest featureofinterest) {
		HashMap item = new HashMap<String, String>();
		if(featureofinterest != null) {
			item.put("url", "/featureofinterest/" + featureofinterest.getId()); 
		}
		return item;
	}

	private static Collection makePlatformdeploymentEvents(Platformdeployment platformdeployment) {
		ArrayList events = new ArrayList();
		switch (platformdeployment.getState().getName()) {
			case "allocated":
				break;
			case "exists":
				break;
			case "ended":
				break;
		}
		return events;
	}	
	
	// Device Responses
	public static Collection makeAllDevice(Collection data) {
		Collection result = new ArrayList<HashMap>();
		for( Iterator<DeviceImpl> i = data.iterator(); i.hasNext(); ) {
			HashMap item = makeDevice(i.next());
			result.add(item);
		}
		return result;
	}
	
	public static HashMap makeDevice(Device device) {
		LinkedHashMap result = new LinkedHashMap();
			
		// Fill in attributes
		result.put("id", device.getId());
		result.put("name", device.getName());
		result.put("description", device.getDescription());
		result.put("status", device.getStatus());
		result.put("state", device.getState().getName());

		// Add dependents
		HashMap dependents = new HashMap();
		dependents.put("registereddevice", makeDeviceDepRegistereddevice(device.getRegistereddevice()));
		dependents.put("procedure", makeDeviceDepProcedure(device.getProcedure()));
		// Add dependents to result
		result.put("dependents", dependents);


		// Add events
		result.put("events", makeDeviceEvents(device));
		
		return result;
	}

	// Device Helpers
	private static Collection makeDeviceDepRegistereddevice(Collection registereddevices) {
		ArrayList result = new ArrayList();
		for(Iterator<RegistereddeviceImpl> i = registereddevices.iterator(); i.hasNext();) {
			RegistereddeviceImpl registereddevice = i.next();
			HashMap item = new HashMap<String, String>();
			item.put("url", "/registereddevice/"+registereddevice.getId());
			result.add(item);
		}
		return result;
	}
	private static Collection makeDeviceDepProcedure(Collection procedures) {
		ArrayList result = new ArrayList();
		for(Iterator<ProcedureImpl> i = procedures.iterator(); i.hasNext();) {
			ProcedureImpl procedure = i.next();
			HashMap item = new HashMap<String, String>();
			item.put("url", "/procedure/"+procedure.getId());
			result.add(item);
		}
		return result;
	}


	private static Collection makeDeviceEvents(Device device) {
		ArrayList events = new ArrayList();
		switch (device.getState().getName()) {
			case "allocated":
				break;
			case "exists":
				break;
			case "ended":
				break;
		}
		return events;
	}	
	
	public static HashMap makeSuccess(String event) {
		HashMap result = new HashMap<String, String>();
		result.put("message", "Successfully executed "+event+" event.");
		return result;
	}

	public static HashMap makeFail(String msg) {
		HashMap result = new HashMap<String, String>();
		result.put("error", msg);
		return result;
	}

}