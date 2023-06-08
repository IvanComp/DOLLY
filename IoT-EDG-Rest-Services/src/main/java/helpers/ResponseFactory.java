package helpers;

/** 
 * This class was automatically generated  
 * using a Merode XML model and Apache Velocity
 * 
 * Merode Code Generator 2.0
 * @author Nick Scheynen
 */
 
import dao.Device;
import dao.DeviceImpl;
import dao.Outcome;
import dao.OutcomeImpl;
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
import dao.Propertyoutcome;
import dao.PropertyoutcomeImpl;
import java.util.*;

public abstract class ResponseFactory {

	
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
		result.put("state", device.getState().getName());

		// Add dependents
		HashMap dependents = new HashMap();
		dependents.put("outcome", makeDeviceDepOutcome(device.getOutcome()));
		dependents.put("deviceusage", makeDeviceDepDeviceusage(device.getDeviceusage()));
		// Add dependents to result
		result.put("dependents", dependents);

		// Add masters
		HashMap masters = new HashMap();
		masters.put("platform", makeDeviceMasPlatform(device.getPlatform()));
		// Add masters to result
		result.put("masters", masters);

		// Add events
		result.put("events", makeDeviceEvents(device));
		
		return result;
	}

	// Device Helpers
	private static Collection makeDeviceDepOutcome(Collection outcomes) {
		ArrayList result = new ArrayList();
		for(Iterator<OutcomeImpl> i = outcomes.iterator(); i.hasNext();) {
			OutcomeImpl outcome = i.next();
			HashMap item = new HashMap<String, String>();
			item.put("url", "/outcome/"+outcome.getId());
			result.add(item);
		}
		return result;
	}
	private static Collection makeDeviceDepDeviceusage(Collection deviceusages) {
		ArrayList result = new ArrayList();
		for(Iterator<DeviceusageImpl> i = deviceusages.iterator(); i.hasNext();) {
			DeviceusageImpl deviceusage = i.next();
			HashMap item = new HashMap<String, String>();
			item.put("url", "/deviceusage/"+deviceusage.getId());
			result.add(item);
		}
		return result;
	}

	private static HashMap makeDeviceMasPlatform(Platform platform) {
		HashMap item = new HashMap<String, String>();
		if(platform != null) {
			item.put("url", "/platform/" + platform.getId()); 
		}
		return item;
	}

	private static Collection makeDeviceEvents(Device device) {
		ArrayList events = new ArrayList();
		switch (device.getState().getName()) {
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
	
	// Outcome Responses
	public static Collection makeAllOutcome(Collection data) {
		Collection result = new ArrayList<HashMap>();
		for( Iterator<OutcomeImpl> i = data.iterator(); i.hasNext(); ) {
			HashMap item = makeOutcome(i.next());
			result.add(item);
		}
		return result;
	}
	
	public static HashMap makeOutcome(Outcome outcome) {
		LinkedHashMap result = new LinkedHashMap();
			
		// Fill in attributes
		result.put("id", outcome.getId());
		result.put("name", outcome.getName());
		result.put("state", outcome.getState().getName());

		// Add dependents
		HashMap dependents = new HashMap();
		dependents.put("propertyoutcome", makeOutcomeDepPropertyoutcome(outcome.getPropertyoutcome()));
		// Add dependents to result
		result.put("dependents", dependents);

		// Add masters
		HashMap masters = new HashMap();
		masters.put("device", makeOutcomeMasDevice(outcome.getDevice()));
		// Add masters to result
		result.put("masters", masters);

		// Add events
		result.put("events", makeOutcomeEvents(outcome));
		
		return result;
	}

	// Outcome Helpers
	private static Collection makeOutcomeDepPropertyoutcome(Collection propertyoutcomes) {
		ArrayList result = new ArrayList();
		for(Iterator<PropertyoutcomeImpl> i = propertyoutcomes.iterator(); i.hasNext();) {
			PropertyoutcomeImpl propertyoutcome = i.next();
			HashMap item = new HashMap<String, String>();
			item.put("url", "/propertyoutcome/"+propertyoutcome.getId());
			result.add(item);
		}
		return result;
	}

	private static HashMap makeOutcomeMasDevice(Device device) {
		HashMap item = new HashMap<String, String>();
		if(device != null) {
			item.put("url", "/device/" + device.getId()); 
		}
		return item;
	}

	private static Collection makeOutcomeEvents(Outcome outcome) {
		ArrayList events = new ArrayList();
		switch (outcome.getState().getName()) {
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
		result.put("time", deviceresult.getTime());
		result.put("value", deviceresult.getValue());
		result.put("state", deviceresult.getState().getName());


		// Add masters
		HashMap masters = new HashMap();
		masters.put("propertyoutcome", makeDeviceresultMasPropertyoutcome(deviceresult.getPropertyoutcome()));
		// Add masters to result
		result.put("masters", masters);

		// Add events
		result.put("events", makeDeviceresultEvents(deviceresult));
		
		return result;
	}

	// Deviceresult Helpers

	private static HashMap makeDeviceresultMasPropertyoutcome(Propertyoutcome propertyoutcome) {
		HashMap item = new HashMap<String, String>();
		if(propertyoutcome != null) {
			item.put("url", "/propertyoutcome/" + propertyoutcome.getId()); 
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
		result.put("state", featureofinterest.getState().getName());

		// Add dependents
		HashMap dependents = new HashMap();
		dependents.put("property", makeFeatureofinterestDepProperty(featureofinterest.getProperty()));
		dependents.put("deviceusage", makeFeatureofinterestDepDeviceusage(featureofinterest.getDeviceusage()));
		// Add dependents to result
		result.put("dependents", dependents);


		// Add events
		result.put("events", makeFeatureofinterestEvents(featureofinterest));
		
		return result;
	}

	// Featureofinterest Helpers
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
	private static Collection makeFeatureofinterestDepDeviceusage(Collection deviceusages) {
		ArrayList result = new ArrayList();
		for(Iterator<DeviceusageImpl> i = deviceusages.iterator(); i.hasNext();) {
			DeviceusageImpl deviceusage = i.next();
			HashMap item = new HashMap<String, String>();
			item.put("url", "/deviceusage/"+deviceusage.getId());
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
		result.put("state", platform.getState().getName());

		// Add dependents
		HashMap dependents = new HashMap();
		dependents.put("device", makePlatformDepDevice(platform.getDevice()));
		// Add dependents to result
		result.put("dependents", dependents);


		// Add events
		result.put("events", makePlatformEvents(platform));
		
		return result;
	}

	// Platform Helpers
	private static Collection makePlatformDepDevice(Collection devices) {
		ArrayList result = new ArrayList();
		for(Iterator<DeviceImpl> i = devices.iterator(); i.hasNext();) {
			DeviceImpl device = i.next();
			HashMap item = new HashMap<String, String>();
			item.put("url", "/device/"+device.getId());
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
		result.put("name", property.getName());
		result.put("state", property.getState().getName());

		// Add dependents
		HashMap dependents = new HashMap();
		dependents.put("propertyoutcome", makePropertyDepPropertyoutcome(property.getPropertyoutcome()));
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
	private static Collection makePropertyDepPropertyoutcome(Collection propertyoutcomes) {
		ArrayList result = new ArrayList();
		for(Iterator<PropertyoutcomeImpl> i = propertyoutcomes.iterator(); i.hasNext();) {
			PropertyoutcomeImpl propertyoutcome = i.next();
			HashMap item = new HashMap<String, String>();
			item.put("url", "/propertyoutcome/"+propertyoutcome.getId());
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
		result.put("name", deviceusage.getName());
		result.put("state", deviceusage.getState().getName());

		// Add dependents
		HashMap dependents = new HashMap();
		dependents.put("propertyoutcome", makeDeviceusageDepPropertyoutcome(deviceusage.getPropertyoutcome()));
		// Add dependents to result
		result.put("dependents", dependents);

		// Add masters
		HashMap masters = new HashMap();
		masters.put("featureofinterest", makeDeviceusageMasFeatureofinterest(deviceusage.getFeatureofinterest()));
		masters.put("device", makeDeviceusageMasDevice(deviceusage.getDevice()));
		// Add masters to result
		result.put("masters", masters);

		// Add events
		result.put("events", makeDeviceusageEvents(deviceusage));
		
		return result;
	}

	// Deviceusage Helpers
	private static Collection makeDeviceusageDepPropertyoutcome(Collection propertyoutcomes) {
		ArrayList result = new ArrayList();
		for(Iterator<PropertyoutcomeImpl> i = propertyoutcomes.iterator(); i.hasNext();) {
			PropertyoutcomeImpl propertyoutcome = i.next();
			HashMap item = new HashMap<String, String>();
			item.put("url", "/propertyoutcome/"+propertyoutcome.getId());
			result.add(item);
		}
		return result;
	}

	private static HashMap makeDeviceusageMasFeatureofinterest(Featureofinterest featureofinterest) {
		HashMap item = new HashMap<String, String>();
		if(featureofinterest != null) {
			item.put("url", "/featureofinterest/" + featureofinterest.getId()); 
		}
		return item;
	}
	private static HashMap makeDeviceusageMasDevice(Device device) {
		HashMap item = new HashMap<String, String>();
		if(device != null) {
			item.put("url", "/device/" + device.getId()); 
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
		}
		return events;
	}	
	
	// Propertyoutcome Responses
	public static Collection makeAllPropertyoutcome(Collection data) {
		Collection result = new ArrayList<HashMap>();
		for( Iterator<PropertyoutcomeImpl> i = data.iterator(); i.hasNext(); ) {
			HashMap item = makePropertyoutcome(i.next());
			result.add(item);
		}
		return result;
	}
	
	public static HashMap makePropertyoutcome(Propertyoutcome propertyoutcome) {
		LinkedHashMap result = new LinkedHashMap();
			
		// Fill in attributes
		result.put("id", propertyoutcome.getId());
		result.put("name", propertyoutcome.getName());
		result.put("state", propertyoutcome.getState().getName());

		// Add dependents
		HashMap dependents = new HashMap();
		dependents.put("deviceresult", makePropertyoutcomeDepDeviceresult(propertyoutcome.getDeviceresult()));
		// Add dependents to result
		result.put("dependents", dependents);

		// Add masters
		HashMap masters = new HashMap();
		masters.put("outcome", makePropertyoutcomeMasOutcome(propertyoutcome.getOutcome()));
		masters.put("property", makePropertyoutcomeMasProperty(propertyoutcome.getProperty()));
		masters.put("deviceusage", makePropertyoutcomeMasDeviceusage(propertyoutcome.getDeviceusage()));
		// Add masters to result
		result.put("masters", masters);

		// Add events
		result.put("events", makePropertyoutcomeEvents(propertyoutcome));
		
		return result;
	}

	// Propertyoutcome Helpers
	private static Collection makePropertyoutcomeDepDeviceresult(Collection deviceresults) {
		ArrayList result = new ArrayList();
		for(Iterator<DeviceresultImpl> i = deviceresults.iterator(); i.hasNext();) {
			DeviceresultImpl deviceresult = i.next();
			HashMap item = new HashMap<String, String>();
			item.put("url", "/deviceresult/"+deviceresult.getId());
			result.add(item);
		}
		return result;
	}

	private static HashMap makePropertyoutcomeMasOutcome(Outcome outcome) {
		HashMap item = new HashMap<String, String>();
		if(outcome != null) {
			item.put("url", "/outcome/" + outcome.getId()); 
		}
		return item;
	}
	private static HashMap makePropertyoutcomeMasProperty(Property property) {
		HashMap item = new HashMap<String, String>();
		if(property != null) {
			item.put("url", "/property/" + property.getId()); 
		}
		return item;
	}
	private static HashMap makePropertyoutcomeMasDeviceusage(Deviceusage deviceusage) {
		HashMap item = new HashMap<String, String>();
		if(deviceusage != null) {
			item.put("url", "/deviceusage/" + deviceusage.getId()); 
		}
		return item;
	}

	private static Collection makePropertyoutcomeEvents(Propertyoutcome propertyoutcome) {
		ArrayList events = new ArrayList();
		switch (propertyoutcome.getState().getName()) {
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