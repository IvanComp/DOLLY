/** 
 * This class was automatically generated  
 * using a Merode XML model and Apache Velocity
 * 
 * Merode Code Generator 2.0
 * @author Nick Scheynen
 */
 
import handlers.MerodeMainEventHandler;
import dao.Registereddevice;
import dao.RegistereddeviceFactory;
import dao.Procedure;
import dao.ProcedureFactory;
import dao.Deviceresult;
import dao.DeviceresultFactory;
import dao.Featureofinterest;
import dao.FeatureofinterestFactory;
import dao.Platform;
import dao.PlatformFactory;
import dao.Property;
import dao.PropertyFactory;
import dao.Deviceusage;
import dao.DeviceusageFactory;
import dao.Platformdeployment;
import dao.PlatformdeploymentFactory;
import dao.Device;
import dao.DeviceFactory;
import helpers.ResponseFactory;

import com.google.gson.Gson;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import static spark.Spark.*;

public class MerodeApplicationService {

	public static void main(String[] args) {
	
		// Make EventHandler
		MerodeMainEventHandler eh = new MerodeMainEventHandler();
		
		get("/", (request, response) -> {					
			HashMap result = new HashMap<String, ArrayList>();	
			
			// Resources
			ArrayList resources = new ArrayList<HashMap>();
			HashMap registereddevice = new HashMap<String, String>();
			registereddevice.put("name", "Registereddevice");
			registereddevice.put("href", "/registereddevice");
			resources.add(registereddevice);
			HashMap procedure = new HashMap<String, String>();
			procedure.put("name", "Procedure");
			procedure.put("href", "/procedure");
			resources.add(procedure);
			HashMap deviceresult = new HashMap<String, String>();
			deviceresult.put("name", "Deviceresult");
			deviceresult.put("href", "/deviceresult");
			resources.add(deviceresult);
			HashMap featureofinterest = new HashMap<String, String>();
			featureofinterest.put("name", "Featureofinterest");
			featureofinterest.put("href", "/featureofinterest");
			resources.add(featureofinterest);
			HashMap platform = new HashMap<String, String>();
			platform.put("name", "Platform");
			platform.put("href", "/platform");
			resources.add(platform);
			HashMap property = new HashMap<String, String>();
			property.put("name", "Property");
			property.put("href", "/property");
			resources.add(property);
			HashMap deviceusage = new HashMap<String, String>();
			deviceusage.put("name", "Deviceusage");
			deviceusage.put("href", "/deviceusage");
			resources.add(deviceusage);
			HashMap platformdeployment = new HashMap<String, String>();
			platformdeployment.put("name", "Platformdeployment");
			platformdeployment.put("href", "/platformdeployment");
			resources.add(platformdeployment);
			HashMap device = new HashMap<String, String>();
			device.put("name", "Device");
			device.put("href", "/device");
			resources.add(device);

			// Format Data
			result.put("resources", resources);
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
			response.type("application/json");
			return responseContent;
		});

		get("registereddevice", (request, response) -> {
			// Get data
			Collection resultSet = eh.getAllRegistereddevice();
			
			// Make Response
			resultSet = ResponseFactory.makeAllRegistereddevice(resultSet);
			
			// Format data
			Gson gson = new Gson();
			String responseContent = gson.toJson(resultSet);
			
			// Make Response
			response.type("application/json");
			return responseContent;
		});
		
		

		post("registereddevice", (request, response) -> {
			HashMap result = new HashMap<String, ArrayList>();
						
			// Resources
			ArrayList resources = new ArrayList<HashMap>();
			HashMap mecrregistereddevice = new HashMap<String, String>();
			mecrregistereddevice.put("method", "POST");
			mecrregistereddevice.put("href", "/registereddevice/mecrregistereddevice");
			resources.add(mecrregistereddevice);

			// Format Data
			result.put("create_events", resources);
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
			
			response.status(300);
			response.type("application/json");
			return responseContent;
			
		
		});
			
		post("registereddevice/mecrregistereddevice", (request, response) -> {
			// Result
			HashMap result = null;
			
			// Get data from request in HashMap
			Gson gson1 = new Gson();
			Map<String, String> parmap = new HashMap<String, String>();
			parmap = (Map<String, String>) gson1.fromJson(request.body(), parmap.getClass());
			
			// Call EventHandler with request parameters
			String registereddeviceId = "";
			try {
				registereddeviceId = eh.mecrregistereddevice(
					parmap.get("platformId"),
					parmap.get("deviceId"),
					 parmap.get("devicename"), parmap.get("platformname"),parmap.get("starttime")
				);
				response.status(201);
				result = ResponseFactory.makeSuccess("mecrregistereddevice");
				HashMap registereddeviceMap = new HashMap<String, String>();
				registereddeviceMap.put("href", "/registereddevice/"+registereddeviceId);
				result.put("registereddevice", registereddeviceMap);
			} catch(Exception exc) {
				response.status(403);
				result = ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Format Data
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
		
			response.type("application/json");
			return responseContent;
		});		

		get("registereddevice/:id", (request, response) -> {
			// Get Registereddevice ID
			String registereddeviceId = request.params("id");
			
			// Search Registereddevice
			Registereddevice registereddevice = null; 
			try {
				registereddevice = eh.searchRegistereddeviceById(registereddeviceId);
			} catch(Exception exc) {
				return ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Make Response
			HashMap resultSet = ResponseFactory.makeRegistereddevice(registereddevice);
			
			// Format data
			Gson gson = new Gson();
			String responseContent = gson.toJson(resultSet);
			
			// Make Response
			response.type("application/json");
			return responseContent;
		});
		
		
		delete("registereddevice/:id/meendregistereddevice", (request, response) -> {
			// Result
			HashMap result = null;
			
			// Get Registereddevice ID
			String registereddeviceId = request.params("id");
			
			// Execute event
			try {
				eh.meendregistereddevice(registereddeviceId);
				result = ResponseFactory.makeSuccess("meendregistereddevice");
			} catch(Exception exc) {
				response.status(403);
				result = ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Format Data
			Gson gson = new Gson();
			String responseContent = gson.toJson(result);
			
			response.type("application/json");
			return responseContent;
		});

		delete("registereddevice/:id", (request, response) -> {
			HashMap result = new HashMap<String, ArrayList>();
		
			// Get Registereddevice ID
			String registereddeviceId = request.params("id");
			
			// Get data from request in HashMap
			Gson gson1 = new Gson();
			Map<String, String> parmap = new HashMap<String, String>();
			parmap = (Map<String, String>) gson1.fromJson(request.body(), parmap.getClass());
						
			ArrayList resources = new ArrayList<HashMap>();
			HashMap meendregistereddevice = new HashMap<String, String>();
			meendregistereddevice.put("method", "DELETE");
			meendregistereddevice.put("href", "/registereddevice/"+registereddeviceId+"/meendregistereddevice");
			resources.add(meendregistereddevice);

			// Format Data
			result.put("end_events", resources);
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
			
			response.status(300);
			response.type("application/json");
			return responseContent;
		});

   
	
		
		get("procedure", (request, response) -> {
			// Get data
			Collection resultSet = eh.getAllProcedure();
			
			// Make Response
			resultSet = ResponseFactory.makeAllProcedure(resultSet);
			
			// Format data
			Gson gson = new Gson();
			String responseContent = gson.toJson(resultSet);
			
			// Make Response
			response.type("application/json");
			return responseContent;
		});
		
		

		post("procedure", (request, response) -> {
			HashMap result = new HashMap<String, ArrayList>();
						
			// Resources
			ArrayList resources = new ArrayList<HashMap>();
			HashMap mecrprocedure = new HashMap<String, String>();
			mecrprocedure.put("method", "POST");
			mecrprocedure.put("href", "/procedure/mecrprocedure");
			resources.add(mecrprocedure);

			// Format Data
			result.put("create_events", resources);
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
			
			response.status(300);
			response.type("application/json");
			return responseContent;
			
		
		});
			
		post("procedure/mecrprocedure", (request, response) -> {
			// Result
			HashMap result = null;
			
			// Get data from request in HashMap
			Gson gson1 = new Gson();
			Map<String, String> parmap = new HashMap<String, String>();
			parmap = (Map<String, String>) gson1.fromJson(request.body(), parmap.getClass());
			
			// Call EventHandler with request parameters
			String procedureId = "";
			try {
				procedureId = eh.mecrprocedure(
					parmap.get("deviceId"),
					 parmap.get("devicename"),parmap.get("description")
				);
				response.status(201);
				result = ResponseFactory.makeSuccess("mecrprocedure");
				HashMap procedureMap = new HashMap<String, String>();
				procedureMap.put("href", "/procedure/"+procedureId);
				result.put("procedure", procedureMap);
			} catch(Exception exc) {
				response.status(403);
				result = ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Format Data
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
		
			response.type("application/json");
			return responseContent;
		});		

		get("procedure/:id", (request, response) -> {
			// Get Procedure ID
			String procedureId = request.params("id");
			
			// Search Procedure
			Procedure procedure = null; 
			try {
				procedure = eh.searchProcedureById(procedureId);
			} catch(Exception exc) {
				return ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Make Response
			HashMap resultSet = ResponseFactory.makeProcedure(procedure);
			
			// Format data
			Gson gson = new Gson();
			String responseContent = gson.toJson(resultSet);
			
			// Make Response
			response.type("application/json");
			return responseContent;
		});
		
		
		delete("procedure/:id/meendprocedure", (request, response) -> {
			// Result
			HashMap result = null;
			
			// Get Procedure ID
			String procedureId = request.params("id");
			
			// Execute event
			try {
				eh.meendprocedure(procedureId);
				result = ResponseFactory.makeSuccess("meendprocedure");
			} catch(Exception exc) {
				response.status(403);
				result = ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Format Data
			Gson gson = new Gson();
			String responseContent = gson.toJson(result);
			
			response.type("application/json");
			return responseContent;
		});

		delete("procedure/:id", (request, response) -> {
			HashMap result = new HashMap<String, ArrayList>();
		
			// Get Procedure ID
			String procedureId = request.params("id");
			
			// Get data from request in HashMap
			Gson gson1 = new Gson();
			Map<String, String> parmap = new HashMap<String, String>();
			parmap = (Map<String, String>) gson1.fromJson(request.body(), parmap.getClass());
						
			ArrayList resources = new ArrayList<HashMap>();
			HashMap meendprocedure = new HashMap<String, String>();
			meendprocedure.put("method", "DELETE");
			meendprocedure.put("href", "/procedure/"+procedureId+"/meendprocedure");
			resources.add(meendprocedure);

			// Format Data
			result.put("end_events", resources);
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
			
			response.status(300);
			response.type("application/json");
			return responseContent;
		});

   
	
		
		get("deviceresult", (request, response) -> {
			// Get data
			Collection resultSet = eh.getAllDeviceresult();
			
			// Make Response
			resultSet = ResponseFactory.makeAllDeviceresult(resultSet);
			
			// Format data
			Gson gson = new Gson();
			String responseContent = gson.toJson(resultSet);
			
			// Make Response
			response.type("application/json");
			return responseContent;
		});
		
		

		post("deviceresult", (request, response) -> {
			HashMap result = new HashMap<String, ArrayList>();
						
			// Resources
			ArrayList resources = new ArrayList<HashMap>();
			HashMap mecrdeviceresult = new HashMap<String, String>();
			mecrdeviceresult.put("method", "POST");
			mecrdeviceresult.put("href", "/deviceresult/mecrdeviceresult");
			resources.add(mecrdeviceresult);

			// Format Data
			result.put("create_events", resources);
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
			
			response.status(300);
			response.type("application/json");
			return responseContent;
			
		
		});
			
		post("deviceresult/mecrdeviceresult", (request, response) -> {
			// Result
			HashMap result = null;
			
			// Get data from request in HashMap
			Gson gson1 = new Gson();
			Map<String, String> parmap = new HashMap<String, String>();
			parmap = (Map<String, String>) gson1.fromJson(request.body(), parmap.getClass());
			
			// Call EventHandler with request parameters
			String deviceresultId = "";
			try {
				deviceresultId = eh.mecrdeviceresult(
					parmap.get("deviceusageId"),
					 parmap.get("value"), parmap.get("unit"), parmap.get("producedby"), parmap.get("observedproperty"), parmap.get("starttime"),parmap.get("endtime")
				);
				response.status(201);
				result = ResponseFactory.makeSuccess("mecrdeviceresult");
				HashMap deviceresultMap = new HashMap<String, String>();
				deviceresultMap.put("href", "/deviceresult/"+deviceresultId);
				result.put("deviceresult", deviceresultMap);
			} catch(Exception exc) {
				response.status(403);
				result = ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Format Data
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
		
			response.type("application/json");
			return responseContent;
		});		

		get("deviceresult/:id", (request, response) -> {
			// Get Deviceresult ID
			String deviceresultId = request.params("id");
			
			// Search Deviceresult
			Deviceresult deviceresult = null; 
			try {
				deviceresult = eh.searchDeviceresultById(deviceresultId);
			} catch(Exception exc) {
				return ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Make Response
			HashMap resultSet = ResponseFactory.makeDeviceresult(deviceresult);
			
			// Format data
			Gson gson = new Gson();
			String responseContent = gson.toJson(resultSet);
			
			// Make Response
			response.type("application/json");
			return responseContent;
		});
		
		
		delete("deviceresult/:id/meenddeviceresult", (request, response) -> {
			// Result
			HashMap result = null;
			
			// Get Deviceresult ID
			String deviceresultId = request.params("id");
			
			// Execute event
			try {
				eh.meenddeviceresult(deviceresultId);
				result = ResponseFactory.makeSuccess("meenddeviceresult");
			} catch(Exception exc) {
				response.status(403);
				result = ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Format Data
			Gson gson = new Gson();
			String responseContent = gson.toJson(result);
			
			response.type("application/json");
			return responseContent;
		});

		delete("deviceresult/:id", (request, response) -> {
			HashMap result = new HashMap<String, ArrayList>();
		
			// Get Deviceresult ID
			String deviceresultId = request.params("id");
			
			// Get data from request in HashMap
			Gson gson1 = new Gson();
			Map<String, String> parmap = new HashMap<String, String>();
			parmap = (Map<String, String>) gson1.fromJson(request.body(), parmap.getClass());
						
			ArrayList resources = new ArrayList<HashMap>();
			HashMap meenddeviceresult = new HashMap<String, String>();
			meenddeviceresult.put("method", "DELETE");
			meenddeviceresult.put("href", "/deviceresult/"+deviceresultId+"/meenddeviceresult");
			resources.add(meenddeviceresult);

			// Format Data
			result.put("end_events", resources);
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
			
			response.status(300);
			response.type("application/json");
			return responseContent;
		});

   
	
		
		get("featureofinterest", (request, response) -> {
			// Get data
			Collection resultSet = eh.getAllFeatureofinterest();
			
			// Make Response
			resultSet = ResponseFactory.makeAllFeatureofinterest(resultSet);
			
			// Format data
			Gson gson = new Gson();
			String responseContent = gson.toJson(resultSet);
			
			// Make Response
			response.type("application/json");
			return responseContent;
		});
		
		

		post("featureofinterest", (request, response) -> {
			HashMap result = new HashMap<String, ArrayList>();
						
			// Resources
			ArrayList resources = new ArrayList<HashMap>();
			HashMap mecrfeatureofinterest = new HashMap<String, String>();
			mecrfeatureofinterest.put("method", "POST");
			mecrfeatureofinterest.put("href", "/featureofinterest/mecrfeatureofinterest");
			resources.add(mecrfeatureofinterest);

			// Format Data
			result.put("create_events", resources);
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
			
			response.status(300);
			response.type("application/json");
			return responseContent;
			
		
		});
			
		post("featureofinterest/mecrfeatureofinterest", (request, response) -> {
			// Result
			HashMap result = null;
			
			// Get data from request in HashMap
			Gson gson1 = new Gson();
			Map<String, String> parmap = new HashMap<String, String>();
			parmap = (Map<String, String>) gson1.fromJson(request.body(), parmap.getClass());
			
			// Call EventHandler with request parameters
			String featureofinterestId = "";
			try {
				featureofinterestId = eh.mecrfeatureofinterest(
					 parmap.get("name"),parmap.get("description")
				);
				response.status(201);
				result = ResponseFactory.makeSuccess("mecrfeatureofinterest");
				HashMap featureofinterestMap = new HashMap<String, String>();
				featureofinterestMap.put("href", "/featureofinterest/"+featureofinterestId);
				result.put("featureofinterest", featureofinterestMap);
			} catch(Exception exc) {
				response.status(403);
				result = ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Format Data
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
		
			response.type("application/json");
			return responseContent;
		});		

		get("featureofinterest/:id", (request, response) -> {
			// Get Featureofinterest ID
			String featureofinterestId = request.params("id");
			
			// Search Featureofinterest
			Featureofinterest featureofinterest = null; 
			try {
				featureofinterest = eh.searchFeatureofinterestById(featureofinterestId);
			} catch(Exception exc) {
				return ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Make Response
			HashMap resultSet = ResponseFactory.makeFeatureofinterest(featureofinterest);
			
			// Format data
			Gson gson = new Gson();
			String responseContent = gson.toJson(resultSet);
			
			// Make Response
			response.type("application/json");
			return responseContent;
		});
		
		
		delete("featureofinterest/:id/meendfeatureofinterest", (request, response) -> {
			// Result
			HashMap result = null;
			
			// Get Featureofinterest ID
			String featureofinterestId = request.params("id");
			
			// Execute event
			try {
				eh.meendfeatureofinterest(featureofinterestId);
				result = ResponseFactory.makeSuccess("meendfeatureofinterest");
			} catch(Exception exc) {
				response.status(403);
				result = ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Format Data
			Gson gson = new Gson();
			String responseContent = gson.toJson(result);
			
			response.type("application/json");
			return responseContent;
		});

		delete("featureofinterest/:id", (request, response) -> {
			HashMap result = new HashMap<String, ArrayList>();
		
			// Get Featureofinterest ID
			String featureofinterestId = request.params("id");
			
			// Get data from request in HashMap
			Gson gson1 = new Gson();
			Map<String, String> parmap = new HashMap<String, String>();
			parmap = (Map<String, String>) gson1.fromJson(request.body(), parmap.getClass());
						
			ArrayList resources = new ArrayList<HashMap>();
			HashMap meendfeatureofinterest = new HashMap<String, String>();
			meendfeatureofinterest.put("method", "DELETE");
			meendfeatureofinterest.put("href", "/featureofinterest/"+featureofinterestId+"/meendfeatureofinterest");
			resources.add(meendfeatureofinterest);

			// Format Data
			result.put("end_events", resources);
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
			
			response.status(300);
			response.type("application/json");
			return responseContent;
		});

   
	
		
		get("platform", (request, response) -> {
			// Get data
			Collection resultSet = eh.getAllPlatform();
			
			// Make Response
			resultSet = ResponseFactory.makeAllPlatform(resultSet);
			
			// Format data
			Gson gson = new Gson();
			String responseContent = gson.toJson(resultSet);
			
			// Make Response
			response.type("application/json");
			return responseContent;
		});
		
		

		post("platform", (request, response) -> {
			HashMap result = new HashMap<String, ArrayList>();
						
			// Resources
			ArrayList resources = new ArrayList<HashMap>();
			HashMap mecrplatform = new HashMap<String, String>();
			mecrplatform.put("method", "POST");
			mecrplatform.put("href", "/platform/mecrplatform");
			resources.add(mecrplatform);

			// Format Data
			result.put("create_events", resources);
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
			
			response.status(300);
			response.type("application/json");
			return responseContent;
			
		
		});
			
		post("platform/mecrplatform", (request, response) -> {
			// Result
			HashMap result = null;
			
			// Get data from request in HashMap
			Gson gson1 = new Gson();
			Map<String, String> parmap = new HashMap<String, String>();
			parmap = (Map<String, String>) gson1.fromJson(request.body(), parmap.getClass());
			
			// Call EventHandler with request parameters
			String platformId = "";
			try {
				platformId = eh.mecrplatform(
					 parmap.get("name"), parmap.get("description"),parmap.get("hostedby")
				);
				response.status(201);
				result = ResponseFactory.makeSuccess("mecrplatform");
				HashMap platformMap = new HashMap<String, String>();
				platformMap.put("href", "/platform/"+platformId);
				result.put("platform", platformMap);
			} catch(Exception exc) {
				response.status(403);
				result = ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Format Data
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
		
			response.type("application/json");
			return responseContent;
		});		

		get("platform/:id", (request, response) -> {
			// Get Platform ID
			String platformId = request.params("id");
			
			// Search Platform
			Platform platform = null; 
			try {
				platform = eh.searchPlatformById(platformId);
			} catch(Exception exc) {
				return ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Make Response
			HashMap resultSet = ResponseFactory.makePlatform(platform);
			
			// Format data
			Gson gson = new Gson();
			String responseContent = gson.toJson(resultSet);
			
			// Make Response
			response.type("application/json");
			return responseContent;
		});
		
		
		delete("platform/:id/meendplatform", (request, response) -> {
			// Result
			HashMap result = null;
			
			// Get Platform ID
			String platformId = request.params("id");
			
			// Execute event
			try {
				eh.meendplatform(platformId);
				result = ResponseFactory.makeSuccess("meendplatform");
			} catch(Exception exc) {
				response.status(403);
				result = ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Format Data
			Gson gson = new Gson();
			String responseContent = gson.toJson(result);
			
			response.type("application/json");
			return responseContent;
		});

		delete("platform/:id", (request, response) -> {
			HashMap result = new HashMap<String, ArrayList>();
		
			// Get Platform ID
			String platformId = request.params("id");
			
			// Get data from request in HashMap
			Gson gson1 = new Gson();
			Map<String, String> parmap = new HashMap<String, String>();
			parmap = (Map<String, String>) gson1.fromJson(request.body(), parmap.getClass());
						
			ArrayList resources = new ArrayList<HashMap>();
			HashMap meendplatform = new HashMap<String, String>();
			meendplatform.put("method", "DELETE");
			meendplatform.put("href", "/platform/"+platformId+"/meendplatform");
			resources.add(meendplatform);

			// Format Data
			result.put("end_events", resources);
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
			
			response.status(300);
			response.type("application/json");
			return responseContent;
		});

   
	
		
		get("property", (request, response) -> {
			// Get data
			Collection resultSet = eh.getAllProperty();
			
			// Make Response
			resultSet = ResponseFactory.makeAllProperty(resultSet);
			
			// Format data
			Gson gson = new Gson();
			String responseContent = gson.toJson(resultSet);
			
			// Make Response
			response.type("application/json");
			return responseContent;
		});
		
		

		post("property", (request, response) -> {
			HashMap result = new HashMap<String, ArrayList>();
						
			// Resources
			ArrayList resources = new ArrayList<HashMap>();
			HashMap mecrproperty = new HashMap<String, String>();
			mecrproperty.put("method", "POST");
			mecrproperty.put("href", "/property/mecrproperty");
			resources.add(mecrproperty);

			// Format Data
			result.put("create_events", resources);
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
			
			response.status(300);
			response.type("application/json");
			return responseContent;
			
		
		});
			
		post("property/mecrproperty", (request, response) -> {
			// Result
			HashMap result = null;
			
			// Get data from request in HashMap
			Gson gson1 = new Gson();
			Map<String, String> parmap = new HashMap<String, String>();
			parmap = (Map<String, String>) gson1.fromJson(request.body(), parmap.getClass());
			
			// Call EventHandler with request parameters
			String propertyId = "";
			try {
				propertyId = eh.mecrproperty(
					parmap.get("featureofinterestId"),
					 parmap.get("featureofinterestname"),parmap.get("description")
				);
				response.status(201);
				result = ResponseFactory.makeSuccess("mecrproperty");
				HashMap propertyMap = new HashMap<String, String>();
				propertyMap.put("href", "/property/"+propertyId);
				result.put("property", propertyMap);
			} catch(Exception exc) {
				response.status(403);
				result = ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Format Data
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
		
			response.type("application/json");
			return responseContent;
		});		

		get("property/:id", (request, response) -> {
			// Get Property ID
			String propertyId = request.params("id");
			
			// Search Property
			Property property = null; 
			try {
				property = eh.searchPropertyById(propertyId);
			} catch(Exception exc) {
				return ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Make Response
			HashMap resultSet = ResponseFactory.makeProperty(property);
			
			// Format data
			Gson gson = new Gson();
			String responseContent = gson.toJson(resultSet);
			
			// Make Response
			response.type("application/json");
			return responseContent;
		});
		
		
		delete("property/:id/meendproperty", (request, response) -> {
			// Result
			HashMap result = null;
			
			// Get Property ID
			String propertyId = request.params("id");
			
			// Execute event
			try {
				eh.meendproperty(propertyId);
				result = ResponseFactory.makeSuccess("meendproperty");
			} catch(Exception exc) {
				response.status(403);
				result = ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Format Data
			Gson gson = new Gson();
			String responseContent = gson.toJson(result);
			
			response.type("application/json");
			return responseContent;
		});

		delete("property/:id", (request, response) -> {
			HashMap result = new HashMap<String, ArrayList>();
		
			// Get Property ID
			String propertyId = request.params("id");
			
			// Get data from request in HashMap
			Gson gson1 = new Gson();
			Map<String, String> parmap = new HashMap<String, String>();
			parmap = (Map<String, String>) gson1.fromJson(request.body(), parmap.getClass());
						
			ArrayList resources = new ArrayList<HashMap>();
			HashMap meendproperty = new HashMap<String, String>();
			meendproperty.put("method", "DELETE");
			meendproperty.put("href", "/property/"+propertyId+"/meendproperty");
			resources.add(meendproperty);

			// Format Data
			result.put("end_events", resources);
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
			
			response.status(300);
			response.type("application/json");
			return responseContent;
		});

   
	
		
		get("deviceusage", (request, response) -> {
			// Get data
			Collection resultSet = eh.getAllDeviceusage();
			
			// Make Response
			resultSet = ResponseFactory.makeAllDeviceusage(resultSet);
			
			// Format data
			Gson gson = new Gson();
			String responseContent = gson.toJson(resultSet);
			
			// Make Response
			response.type("application/json");
			return responseContent;
		});
		
		

		post("deviceusage", (request, response) -> {
			HashMap result = new HashMap<String, ArrayList>();
						
			// Resources
			ArrayList resources = new ArrayList<HashMap>();
			HashMap mecrdeviceusage = new HashMap<String, String>();
			mecrdeviceusage.put("method", "POST");
			mecrdeviceusage.put("href", "/deviceusage/mecrdeviceusage");
			resources.add(mecrdeviceusage);

			// Format Data
			result.put("create_events", resources);
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
			
			response.status(300);
			response.type("application/json");
			return responseContent;
			
		
		});
			
		post("deviceusage/mecrdeviceusage", (request, response) -> {
			// Result
			HashMap result = null;
			
			// Get data from request in HashMap
			Gson gson1 = new Gson();
			Map<String, String> parmap = new HashMap<String, String>();
			parmap = (Map<String, String>) gson1.fromJson(request.body(), parmap.getClass());
			
			// Call EventHandler with request parameters
			String deviceusageId = "";
			try {
				deviceusageId = eh.mecrdeviceusage(
					parmap.get("platformdeploymentId"),
					parmap.get("registereddeviceId"),
					parmap.get("procedureId"),
					parmap.get("propertyId"),
					 parmap.get("usagetype"), parmap.get("starttime"),parmap.get("endtime")
				);
				response.status(201);
				result = ResponseFactory.makeSuccess("mecrdeviceusage");
				HashMap deviceusageMap = new HashMap<String, String>();
				deviceusageMap.put("href", "/deviceusage/"+deviceusageId);
				result.put("deviceusage", deviceusageMap);
			} catch(Exception exc) {
				response.status(403);
				result = ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Format Data
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
		
			response.type("application/json");
			return responseContent;
		});		

		get("deviceusage/:id", (request, response) -> {
			// Get Deviceusage ID
			String deviceusageId = request.params("id");
			
			// Search Deviceusage
			Deviceusage deviceusage = null; 
			try {
				deviceusage = eh.searchDeviceusageById(deviceusageId);
			} catch(Exception exc) {
				return ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Make Response
			HashMap resultSet = ResponseFactory.makeDeviceusage(deviceusage);
			
			// Format data
			Gson gson = new Gson();
			String responseContent = gson.toJson(resultSet);
			
			// Make Response
			response.type("application/json");
			return responseContent;
		});
		
		
		delete("deviceusage/:id/meenddeviceusage", (request, response) -> {
			// Result
			HashMap result = null;
			
			// Get Deviceusage ID
			String deviceusageId = request.params("id");
			
			// Execute event
			try {
				eh.meenddeviceusage(deviceusageId);
				result = ResponseFactory.makeSuccess("meenddeviceusage");
			} catch(Exception exc) {
				response.status(403);
				result = ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Format Data
			Gson gson = new Gson();
			String responseContent = gson.toJson(result);
			
			response.type("application/json");
			return responseContent;
		});
		
		delete("deviceusage/:id/deviceundeployment", (request, response) -> {
			// Result
			HashMap result = null;
			
			// Get Deviceusage ID
			String deviceusageId = request.params("id");
			
			// Execute event
			try {
				eh.deviceundeployment(deviceusageId);
				result = ResponseFactory.makeSuccess("deviceundeployment");
			} catch(Exception exc) {
				response.status(403);
				result = ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Format Data
			Gson gson = new Gson();
			String responseContent = gson.toJson(result);
			
			response.type("application/json");
			return responseContent;
		});

		delete("deviceusage/:id", (request, response) -> {
			HashMap result = new HashMap<String, ArrayList>();
		
			// Get Deviceusage ID
			String deviceusageId = request.params("id");
			
			// Get data from request in HashMap
			Gson gson1 = new Gson();
			Map<String, String> parmap = new HashMap<String, String>();
			parmap = (Map<String, String>) gson1.fromJson(request.body(), parmap.getClass());
						
			ArrayList resources = new ArrayList<HashMap>();
			HashMap meenddeviceusage = new HashMap<String, String>();
			meenddeviceusage.put("method", "DELETE");
			meenddeviceusage.put("href", "/deviceusage/"+deviceusageId+"/meenddeviceusage");
			resources.add(meenddeviceusage);
			HashMap deviceundeployment = new HashMap<String, String>();
			deviceundeployment.put("method", "DELETE");
			deviceundeployment.put("href", "/deviceusage/"+deviceusageId+"/deviceundeployment");
			resources.add(deviceundeployment);

			// Format Data
			result.put("end_events", resources);
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
			
			response.status(300);
			response.type("application/json");
			return responseContent;
		});

   
		
		patch("deviceusage/:id/devicedeployment", (request, response) -> {
			// Result
			HashMap result = null;
			
			// Get Deviceusage ID
			String deviceusageId = request.params("id");
			Deviceusage deviceusage = eh.searchDeviceusageById(deviceusageId);
			
			// Get data from request in HashMap
			Gson gson = new Gson();
			Map<String, String> parmap = new HashMap<String, String>();
			parmap = (Map<String, String>) gson.fromJson(request.body(), parmap.getClass());
			
			if(!parmap.containsKey("usagetype"))
				parmap.put("usagetype", deviceusage.getUsagetype());
			if(!parmap.containsKey("starttime"))
				parmap.put("starttime", deviceusage.getStarttime());
			if(!parmap.containsKey("endtime"))
				parmap.put("endtime", deviceusage.getEndtime());
			
			// Call EventHandler with request parameters
			try {
				eh.devicedeployment(deviceusageId, parmap.get("usagetype"), parmap.get("starttime"), parmap.get("endtime"));
				result = ResponseFactory.makeSuccess("Modify");
			} catch(Exception exc) {
				response.status(403);
				result = ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Format Data
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
			
			response.type("application/json");
			return responseContent;
			
			
		});
		
		patch("deviceusage/:id/mesetready", (request, response) -> {
			// Result
			HashMap result = null;
			
			// Get Deviceusage ID
			String deviceusageId = request.params("id");
			Deviceusage deviceusage = eh.searchDeviceusageById(deviceusageId);
			
			// Get data from request in HashMap
			Gson gson = new Gson();
			Map<String, String> parmap = new HashMap<String, String>();
			parmap = (Map<String, String>) gson.fromJson(request.body(), parmap.getClass());
			
			if(!parmap.containsKey("usagetype"))
				parmap.put("usagetype", deviceusage.getUsagetype());
			if(!parmap.containsKey("starttime"))
				parmap.put("starttime", deviceusage.getStarttime());
			if(!parmap.containsKey("endtime"))
				parmap.put("endtime", deviceusage.getEndtime());
			
			// Call EventHandler with request parameters
			try {
				eh.mesetready(deviceusageId, parmap.get("usagetype"), parmap.get("starttime"), parmap.get("endtime"));
				result = ResponseFactory.makeSuccess("Modify");
			} catch(Exception exc) {
				response.status(403);
				result = ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Format Data
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
			
			response.type("application/json");
			return responseContent;
			
			
		});
	
		
		get("platformdeployment", (request, response) -> {
			// Get data
			Collection resultSet = eh.getAllPlatformdeployment();
			
			// Make Response
			resultSet = ResponseFactory.makeAllPlatformdeployment(resultSet);
			
			// Format data
			Gson gson = new Gson();
			String responseContent = gson.toJson(resultSet);
			
			// Make Response
			response.type("application/json");
			return responseContent;
		});
		
		

		post("platformdeployment", (request, response) -> {
			HashMap result = new HashMap<String, ArrayList>();
						
			// Resources
			ArrayList resources = new ArrayList<HashMap>();
			HashMap mecrplatformdeployment = new HashMap<String, String>();
			mecrplatformdeployment.put("method", "POST");
			mecrplatformdeployment.put("href", "/platformdeployment/mecrplatformdeployment");
			resources.add(mecrplatformdeployment);

			// Format Data
			result.put("create_events", resources);
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
			
			response.status(300);
			response.type("application/json");
			return responseContent;
			
		
		});
			
		post("platformdeployment/mecrplatformdeployment", (request, response) -> {
			// Result
			HashMap result = null;
			
			// Get data from request in HashMap
			Gson gson1 = new Gson();
			Map<String, String> parmap = new HashMap<String, String>();
			parmap = (Map<String, String>) gson1.fromJson(request.body(), parmap.getClass());
			
			// Call EventHandler with request parameters
			String platformdeploymentId = "";
			try {
				platformdeploymentId = eh.mecrplatformdeployment(
					parmap.get("platformId"),
					parmap.get("featureofinterestId"),
					 parmap.get("platformname"), parmap.get("featureofinterestname"),parmap.get("starttime")
				);
				response.status(201);
				result = ResponseFactory.makeSuccess("mecrplatformdeployment");
				HashMap platformdeploymentMap = new HashMap<String, String>();
				platformdeploymentMap.put("href", "/platformdeployment/"+platformdeploymentId);
				result.put("platformdeployment", platformdeploymentMap);
			} catch(Exception exc) {
				response.status(403);
				result = ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Format Data
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
		
			response.type("application/json");
			return responseContent;
		});		

		get("platformdeployment/:id", (request, response) -> {
			// Get Platformdeployment ID
			String platformdeploymentId = request.params("id");
			
			// Search Platformdeployment
			Platformdeployment platformdeployment = null; 
			try {
				platformdeployment = eh.searchPlatformdeploymentById(platformdeploymentId);
			} catch(Exception exc) {
				return ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Make Response
			HashMap resultSet = ResponseFactory.makePlatformdeployment(platformdeployment);
			
			// Format data
			Gson gson = new Gson();
			String responseContent = gson.toJson(resultSet);
			
			// Make Response
			response.type("application/json");
			return responseContent;
		});
		
		
		delete("platformdeployment/:id/meendplatformdeployment", (request, response) -> {
			// Result
			HashMap result = null;
			
			// Get Platformdeployment ID
			String platformdeploymentId = request.params("id");
			
			// Execute event
			try {
				eh.meendplatformdeployment(platformdeploymentId);
				result = ResponseFactory.makeSuccess("meendplatformdeployment");
			} catch(Exception exc) {
				response.status(403);
				result = ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Format Data
			Gson gson = new Gson();
			String responseContent = gson.toJson(result);
			
			response.type("application/json");
			return responseContent;
		});

		delete("platformdeployment/:id", (request, response) -> {
			HashMap result = new HashMap<String, ArrayList>();
		
			// Get Platformdeployment ID
			String platformdeploymentId = request.params("id");
			
			// Get data from request in HashMap
			Gson gson1 = new Gson();
			Map<String, String> parmap = new HashMap<String, String>();
			parmap = (Map<String, String>) gson1.fromJson(request.body(), parmap.getClass());
						
			ArrayList resources = new ArrayList<HashMap>();
			HashMap meendplatformdeployment = new HashMap<String, String>();
			meendplatformdeployment.put("method", "DELETE");
			meendplatformdeployment.put("href", "/platformdeployment/"+platformdeploymentId+"/meendplatformdeployment");
			resources.add(meendplatformdeployment);

			// Format Data
			result.put("end_events", resources);
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
			
			response.status(300);
			response.type("application/json");
			return responseContent;
		});

   
	
		
		get("device", (request, response) -> {
			// Get data
			Collection resultSet = eh.getAllDevice();
			
			// Make Response
			resultSet = ResponseFactory.makeAllDevice(resultSet);
			
			// Format data
			Gson gson = new Gson();
			String responseContent = gson.toJson(resultSet);
			
			// Make Response
			response.type("application/json");
			return responseContent;
		});
		
		

		post("device", (request, response) -> {
			HashMap result = new HashMap<String, ArrayList>();
						
			// Resources
			ArrayList resources = new ArrayList<HashMap>();
			HashMap mecrdevice = new HashMap<String, String>();
			mecrdevice.put("method", "POST");
			mecrdevice.put("href", "/device/mecrdevice");
			resources.add(mecrdevice);

			// Format Data
			result.put("create_events", resources);
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
			
			response.status(300);
			response.type("application/json");
			return responseContent;
			
		
		});
			
		post("device/mecrdevice", (request, response) -> {
			// Result
			HashMap result = null;
			
			// Get data from request in HashMap
			Gson gson1 = new Gson();
			Map<String, String> parmap = new HashMap<String, String>();
			parmap = (Map<String, String>) gson1.fromJson(request.body(), parmap.getClass());
			
			// Call EventHandler with request parameters
			String deviceId = "";
			try {
				deviceId = eh.mecrdevice(
					 parmap.get("name"), parmap.get("description"),parmap.get("status")
				);
				response.status(201);
				result = ResponseFactory.makeSuccess("mecrdevice");
				HashMap deviceMap = new HashMap<String, String>();
				deviceMap.put("href", "/device/"+deviceId);
				result.put("device", deviceMap);
			} catch(Exception exc) {
				response.status(403);
				result = ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Format Data
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
		
			response.type("application/json");
			return responseContent;
		});		

		get("device/:id", (request, response) -> {
			// Get Device ID
			String deviceId = request.params("id");
			
			// Search Device
			Device device = null; 
			try {
				device = eh.searchDeviceById(deviceId);
			} catch(Exception exc) {
				return ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Make Response
			HashMap resultSet = ResponseFactory.makeDevice(device);
			
			// Format data
			Gson gson = new Gson();
			String responseContent = gson.toJson(resultSet);
			
			// Make Response
			response.type("application/json");
			return responseContent;
		});
		
		
		delete("device/:id/meenddevice", (request, response) -> {
			// Result
			HashMap result = null;
			
			// Get Device ID
			String deviceId = request.params("id");
			
			// Execute event
			try {
				eh.meenddevice(deviceId);
				result = ResponseFactory.makeSuccess("meenddevice");
			} catch(Exception exc) {
				response.status(403);
				result = ResponseFactory.makeFail(exc.getMessage());
			}
			
			// Format Data
			Gson gson = new Gson();
			String responseContent = gson.toJson(result);
			
			response.type("application/json");
			return responseContent;
		});

		delete("device/:id", (request, response) -> {
			HashMap result = new HashMap<String, ArrayList>();
		
			// Get Device ID
			String deviceId = request.params("id");
			
			// Get data from request in HashMap
			Gson gson1 = new Gson();
			Map<String, String> parmap = new HashMap<String, String>();
			parmap = (Map<String, String>) gson1.fromJson(request.body(), parmap.getClass());
						
			ArrayList resources = new ArrayList<HashMap>();
			HashMap meenddevice = new HashMap<String, String>();
			meenddevice.put("method", "DELETE");
			meenddevice.put("href", "/device/"+deviceId+"/meenddevice");
			resources.add(meenddevice);

			// Format Data
			result.put("end_events", resources);
			Gson gson2 = new Gson();
			String responseContent = gson2.toJson(result);
			
			response.status(300);
			response.type("application/json");
			return responseContent;
		});

   
	
		
	
	}
}