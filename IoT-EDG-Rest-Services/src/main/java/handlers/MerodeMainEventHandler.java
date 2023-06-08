/** 
 * This class was automatically generated  
 * using a Merode XML model and Apache Velocity
 * 
 * Merode Code Generator 2.0
 * @author MERODE Team-members - adapted by Nick Scheynen
 */
package handlers;

import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import dao.MerodeException;
import dao.MerodeLogger;

import dao.Device;
import dao.DeviceFactory;
import dao.Outcome;
import dao.OutcomeFactory;
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
import dao.Propertyoutcome;
import dao.PropertyoutcomeFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class MerodeMainEventHandler {

	// --------------- Merode Logger -------------------------

	public java.lang.String flushLog() {
		return MerodeLogger.flush();
	}

	// ---------------- business methods ----------------------

	public String mecrdevice(
		java.lang.String platformId, 
		java.lang.String Name)
			throws Exception {
		
		boolean noMultiplePropagationDetected = true;
		MerodeLogger.logln("--> Executing event mecrdevice");
		Session session = null;
		crResult handled = new crResult(true, "");
		try {
			session = getSession();
			handled = handleMecrdevice(session, 
				platformId,  
			     Name
			    );
			    
			noMultiplePropagationDetected = handled.getNoMultiplePropagationDetected();
			//persisting to the database
			session.beginTransaction().commit();
		} catch (MerodeException e) {
			// _ctx.setRollbackOnly();
			session.connection().rollback();
			throw e;
		} catch (Throwable th) {
			session.connection().rollback();
			throw new Exception("MerodeMainEventHandlerBean.mecrdevice: "
					+ th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("MerodeMainEventHandlerBean.mecrdevice: "
							+ he.getMessage());
				}
			}
		}
		return handled.getID();
	}


	public void meenddevice(java.lang.String deviceId) throws Exception {
		MerodeLogger.logln("--> Executing event meenddevice");
		Session session = null;
		try {
			session = getSession();
			handleMeenddevice(session, deviceId);
			//persisting to the database
			session.beginTransaction().commit();
		} catch (MerodeException e) {
			// _ctx.setRollbackOnly();
			session.connection().rollback();
			throw e;
		}

		catch (Throwable th) {
			session.connection().rollback();
			throw new Exception("MerodeMainEventHandlerBean.meenddevice: "
					+ th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("MerodeMainEventHandlerBean.meenddevice: "
							+ he.getMessage());
				}
			}
		}
	}

	

	public String mecroutcome(
		java.lang.String deviceId, 
		java.lang.String Name)
			throws Exception {
		
		boolean noMultiplePropagationDetected = true;
		MerodeLogger.logln("--> Executing event mecroutcome");
		Session session = null;
		crResult handled = new crResult(true, "");
		try {
			session = getSession();
			handled = handleMecroutcome(session, 
				deviceId,  
			     Name
			    );
			    
			noMultiplePropagationDetected = handled.getNoMultiplePropagationDetected();
			//persisting to the database
			session.beginTransaction().commit();
		} catch (MerodeException e) {
			// _ctx.setRollbackOnly();
			session.connection().rollback();
			throw e;
		} catch (Throwable th) {
			session.connection().rollback();
			throw new Exception("MerodeMainEventHandlerBean.mecroutcome: "
					+ th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("MerodeMainEventHandlerBean.mecroutcome: "
							+ he.getMessage());
				}
			}
		}
		return handled.getID();
	}


	public void meendoutcome(java.lang.String outcomeId) throws Exception {
		MerodeLogger.logln("--> Executing event meendoutcome");
		Session session = null;
		try {
			session = getSession();
			handleMeendoutcome(session, outcomeId);
			//persisting to the database
			session.beginTransaction().commit();
		} catch (MerodeException e) {
			// _ctx.setRollbackOnly();
			session.connection().rollback();
			throw e;
		}

		catch (Throwable th) {
			session.connection().rollback();
			throw new Exception("MerodeMainEventHandlerBean.meendoutcome: "
					+ th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("MerodeMainEventHandlerBean.meendoutcome: "
							+ he.getMessage());
				}
			}
		}
	}

	

	public String mecrdeviceresult(
		java.lang.String propertyoutcomeId, 
		 java.lang.String Time,
		java.lang.String Value)
			throws Exception {
		
		boolean noMultiplePropagationDetected = true;
		MerodeLogger.logln("--> Executing event mecrdeviceresult");
		Session session = null;
		crResult handled = new crResult(true, "");
		try {
			session = getSession();
			handled = handleMecrdeviceresult(session, 
				propertyoutcomeId,  
			     Time, Value
			    );
			    
			noMultiplePropagationDetected = handled.getNoMultiplePropagationDetected();
			//persisting to the database
			session.beginTransaction().commit();
		} catch (MerodeException e) {
			// _ctx.setRollbackOnly();
			session.connection().rollback();
			throw e;
		} catch (Throwable th) {
			session.connection().rollback();
			throw new Exception("MerodeMainEventHandlerBean.mecrdeviceresult: "
					+ th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("MerodeMainEventHandlerBean.mecrdeviceresult: "
							+ he.getMessage());
				}
			}
		}
		return handled.getID();
	}


	public void meenddeviceresult(java.lang.String deviceresultId) throws Exception {
		MerodeLogger.logln("--> Executing event meenddeviceresult");
		Session session = null;
		try {
			session = getSession();
			handleMeenddeviceresult(session, deviceresultId);
			//persisting to the database
			session.beginTransaction().commit();
		} catch (MerodeException e) {
			// _ctx.setRollbackOnly();
			session.connection().rollback();
			throw e;
		}

		catch (Throwable th) {
			session.connection().rollback();
			throw new Exception("MerodeMainEventHandlerBean.meenddeviceresult: "
					+ th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("MerodeMainEventHandlerBean.meenddeviceresult: "
							+ he.getMessage());
				}
			}
		}
	}

	

	public String mecrfeatureofinterest(
		java.lang.String Name)
			throws Exception {
		
		boolean noMultiplePropagationDetected = true;
		MerodeLogger.logln("--> Executing event mecrfeatureofinterest");
		Session session = null;
		crResult handled = new crResult(true, "");
		try {
			session = getSession();
			handled = handleMecrfeatureofinterest(session, 
			     Name
			    );
			    
			noMultiplePropagationDetected = handled.getNoMultiplePropagationDetected();
			//persisting to the database
			session.beginTransaction().commit();
		} catch (MerodeException e) {
			// _ctx.setRollbackOnly();
			session.connection().rollback();
			throw e;
		} catch (Throwable th) {
			session.connection().rollback();
			throw new Exception("MerodeMainEventHandlerBean.mecrfeatureofinterest: "
					+ th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("MerodeMainEventHandlerBean.mecrfeatureofinterest: "
							+ he.getMessage());
				}
			}
		}
		return handled.getID();
	}


	public void meendfeatureofinterest(java.lang.String featureofinterestId) throws Exception {
		MerodeLogger.logln("--> Executing event meendfeatureofinterest");
		Session session = null;
		try {
			session = getSession();
			handleMeendfeatureofinterest(session, featureofinterestId);
			//persisting to the database
			session.beginTransaction().commit();
		} catch (MerodeException e) {
			// _ctx.setRollbackOnly();
			session.connection().rollback();
			throw e;
		}

		catch (Throwable th) {
			session.connection().rollback();
			throw new Exception("MerodeMainEventHandlerBean.meendfeatureofinterest: "
					+ th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("MerodeMainEventHandlerBean.meendfeatureofinterest: "
							+ he.getMessage());
				}
			}
		}
	}

	

	public String mecrplatform(
		java.lang.String Name)
			throws Exception {
		
		boolean noMultiplePropagationDetected = true;
		MerodeLogger.logln("--> Executing event mecrplatform");
		Session session = null;
		crResult handled = new crResult(true, "");
		try {
			session = getSession();
			handled = handleMecrplatform(session, 
			     Name
			    );
			    
			noMultiplePropagationDetected = handled.getNoMultiplePropagationDetected();
			//persisting to the database
			session.beginTransaction().commit();
		} catch (MerodeException e) {
			// _ctx.setRollbackOnly();
			session.connection().rollback();
			throw e;
		} catch (Throwable th) {
			session.connection().rollback();
			throw new Exception("MerodeMainEventHandlerBean.mecrplatform: "
					+ th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("MerodeMainEventHandlerBean.mecrplatform: "
							+ he.getMessage());
				}
			}
		}
		return handled.getID();
	}


	public void meendplatform(java.lang.String platformId) throws Exception {
		MerodeLogger.logln("--> Executing event meendplatform");
		Session session = null;
		try {
			session = getSession();
			handleMeendplatform(session, platformId);
			//persisting to the database
			session.beginTransaction().commit();
		} catch (MerodeException e) {
			// _ctx.setRollbackOnly();
			session.connection().rollback();
			throw e;
		}

		catch (Throwable th) {
			session.connection().rollback();
			throw new Exception("MerodeMainEventHandlerBean.meendplatform: "
					+ th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("MerodeMainEventHandlerBean.meendplatform: "
							+ he.getMessage());
				}
			}
		}
	}

	

	public String mecrproperty(
		java.lang.String featureofinterestId, 
		java.lang.String Name)
			throws Exception {
		
		boolean noMultiplePropagationDetected = true;
		MerodeLogger.logln("--> Executing event mecrproperty");
		Session session = null;
		crResult handled = new crResult(true, "");
		try {
			session = getSession();
			handled = handleMecrproperty(session, 
				featureofinterestId,  
			     Name
			    );
			    
			noMultiplePropagationDetected = handled.getNoMultiplePropagationDetected();
			//persisting to the database
			session.beginTransaction().commit();
		} catch (MerodeException e) {
			// _ctx.setRollbackOnly();
			session.connection().rollback();
			throw e;
		} catch (Throwable th) {
			session.connection().rollback();
			throw new Exception("MerodeMainEventHandlerBean.mecrproperty: "
					+ th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("MerodeMainEventHandlerBean.mecrproperty: "
							+ he.getMessage());
				}
			}
		}
		return handled.getID();
	}


	public void meendproperty(java.lang.String propertyId) throws Exception {
		MerodeLogger.logln("--> Executing event meendproperty");
		Session session = null;
		try {
			session = getSession();
			handleMeendproperty(session, propertyId);
			//persisting to the database
			session.beginTransaction().commit();
		} catch (MerodeException e) {
			// _ctx.setRollbackOnly();
			session.connection().rollback();
			throw e;
		}

		catch (Throwable th) {
			session.connection().rollback();
			throw new Exception("MerodeMainEventHandlerBean.meendproperty: "
					+ th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("MerodeMainEventHandlerBean.meendproperty: "
							+ he.getMessage());
				}
			}
		}
	}

	

	public String mecrdeviceusage(
		java.lang.String featureofinterestId, 
		java.lang.String deviceId, 
		java.lang.String Name)
			throws Exception {
		
		boolean noMultiplePropagationDetected = true;
		MerodeLogger.logln("--> Executing event mecrdeviceusage");
		Session session = null;
		crResult handled = new crResult(true, "");
		try {
			session = getSession();
			handled = handleMecrdeviceusage(session, 
				featureofinterestId,  
				deviceId,  
			     Name
			    );
			    
			noMultiplePropagationDetected = handled.getNoMultiplePropagationDetected();
			//persisting to the database
			session.beginTransaction().commit();
		} catch (MerodeException e) {
			// _ctx.setRollbackOnly();
			session.connection().rollback();
			throw e;
		} catch (Throwable th) {
			session.connection().rollback();
			throw new Exception("MerodeMainEventHandlerBean.mecrdeviceusage: "
					+ th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("MerodeMainEventHandlerBean.mecrdeviceusage: "
							+ he.getMessage());
				}
			}
		}
		return handled.getID();
	}


	public void meenddeviceusage(java.lang.String deviceusageId) throws Exception {
		MerodeLogger.logln("--> Executing event meenddeviceusage");
		Session session = null;
		try {
			session = getSession();
			handleMeenddeviceusage(session, deviceusageId);
			//persisting to the database
			session.beginTransaction().commit();
		} catch (MerodeException e) {
			// _ctx.setRollbackOnly();
			session.connection().rollback();
			throw e;
		}

		catch (Throwable th) {
			session.connection().rollback();
			throw new Exception("MerodeMainEventHandlerBean.meenddeviceusage: "
					+ th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("MerodeMainEventHandlerBean.meenddeviceusage: "
							+ he.getMessage());
				}
			}
		}
	}
	public void deviceundeployment(java.lang.String deviceusageId) throws Exception {
		MerodeLogger.logln("--> Executing event deviceundeployment");
		Session session = null;
		try {
			session = getSession();
			handleDeviceundeployment(session, deviceusageId);
			//persisting to the database
			session.beginTransaction().commit();
		} catch (MerodeException e) {
			// _ctx.setRollbackOnly();
			session.connection().rollback();
			throw e;
		}

		catch (Throwable th) {
			session.connection().rollback();
			throw new Exception("MerodeMainEventHandlerBean.deviceundeployment: "
					+ th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("MerodeMainEventHandlerBean.deviceundeployment: "
							+ he.getMessage());
				}
			}
		}
	}







	public void devicedeployment(
		java.lang.String deviceusageId, java.lang.String Name)

			throws Exception {
		MerodeLogger.logln("--> Executing event devicedeployment");
		Session session = null;
		try {
			session = getSession();
			handleDevicedeployment(session, deviceusageId,
			     Name );
			//persisting to the database
			session.beginTransaction().commit();
		} catch (MerodeException e) {
			// _ctx.setRollbackOnly();
			session.connection().rollback();
			throw e;
		}

		catch (Throwable th) {
			session.connection().rollback();
			throw new Exception("MerodeMainEventHandlerBean.devicedeployment: "
					+ th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("MerodeMainEventHandlerBean.devicedeployment: "
							+ he.getMessage());
				}
			}
		}
	}
	

	public String mecrpropertyoutcome(
		java.lang.String outcomeId, 
		java.lang.String propertyId, 
		java.lang.String deviceusageId, 
		java.lang.String Name)
			throws Exception {
		
		boolean noMultiplePropagationDetected = true;
		MerodeLogger.logln("--> Executing event mecrpropertyoutcome");
		Session session = null;
		crResult handled = new crResult(true, "");
		try {
			session = getSession();
			handled = handleMecrpropertyoutcome(session, 
				outcomeId,  
				propertyId,  
				deviceusageId,  
			     Name
			    );
			    
			noMultiplePropagationDetected = handled.getNoMultiplePropagationDetected();
			//persisting to the database
			session.beginTransaction().commit();
		} catch (MerodeException e) {
			// _ctx.setRollbackOnly();
			session.connection().rollback();
			throw e;
		} catch (Throwable th) {
			session.connection().rollback();
			throw new Exception("MerodeMainEventHandlerBean.mecrpropertyoutcome: "
					+ th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("MerodeMainEventHandlerBean.mecrpropertyoutcome: "
							+ he.getMessage());
				}
			}
		}
		return handled.getID();
	}


	public void meendpropertyoutcome(java.lang.String propertyoutcomeId) throws Exception {
		MerodeLogger.logln("--> Executing event meendpropertyoutcome");
		Session session = null;
		try {
			session = getSession();
			handleMeendpropertyoutcome(session, propertyoutcomeId);
			//persisting to the database
			session.beginTransaction().commit();
		} catch (MerodeException e) {
			// _ctx.setRollbackOnly();
			session.connection().rollback();
			throw e;
		}

		catch (Throwable th) {
			session.connection().rollback();
			throw new Exception("MerodeMainEventHandlerBean.meendpropertyoutcome: "
					+ th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("MerodeMainEventHandlerBean.meendpropertyoutcome: "
							+ he.getMessage());
				}
			}
		}
	}

	

	// ---------------- search methods -------------------------

    //search for all instances
	public java.util.Collection getAllDevice() throws Exception {
		Session session = null;
		try {
			session = getSession();
			Collection result = DeviceFactory.getAllObjects(session);
			return result;
		} catch (Throwable th) {
			throw new Exception("getAllDevice : " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("getAllDevice : " + he.getMessage());
				}
			}
		}

	}

	//searching by given attribute
	public java.util.Collection searchDeviceByName(java.lang.String Name)
			throws Exception {
		Session session = null;
		try {
			session = getSession();
			Collection result = DeviceFactory.findByName(session, Name);
			return result;
		} catch (Throwable th) {
			throw new Exception("searchDeviceByName: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("searchDeviceByName: "
							+ he.getMessage());
				}
			}
		}

	}

	//searching by PK
	public Device searchDeviceById(java.lang.String id) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Device result = DeviceFactory.findByPrimaryKey(session, id);
			return result;
		} catch (Throwable th) {
			throw new Exception("searchDeviceById: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("searchDeviceById: " + he.getMessage());
				}
			}
		}

	}

    //search for all instances
	public java.util.Collection getAllOutcome() throws Exception {
		Session session = null;
		try {
			session = getSession();
			Collection result = OutcomeFactory.getAllObjects(session);
			return result;
		} catch (Throwable th) {
			throw new Exception("getAllOutcome : " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("getAllOutcome : " + he.getMessage());
				}
			}
		}

	}

	//searching by given attribute
	public java.util.Collection searchOutcomeByName(java.lang.String Name)
			throws Exception {
		Session session = null;
		try {
			session = getSession();
			Collection result = OutcomeFactory.findByName(session, Name);
			return result;
		} catch (Throwable th) {
			throw new Exception("searchOutcomeByName: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("searchOutcomeByName: "
							+ he.getMessage());
				}
			}
		}

	}

	//searching by PK
	public Outcome searchOutcomeById(java.lang.String id) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Outcome result = OutcomeFactory.findByPrimaryKey(session, id);
			return result;
		} catch (Throwable th) {
			throw new Exception("searchOutcomeById: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("searchOutcomeById: " + he.getMessage());
				}
			}
		}

	}

    //search for all instances
	public java.util.Collection getAllDeviceresult() throws Exception {
		Session session = null;
		try {
			session = getSession();
			Collection result = DeviceresultFactory.getAllObjects(session);
			return result;
		} catch (Throwable th) {
			throw new Exception("getAllDeviceresult : " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("getAllDeviceresult : " + he.getMessage());
				}
			}
		}

	}

	//searching by given attribute
	public java.util.Collection searchDeviceresultByTime(java.lang.String Time)
			throws Exception {
		Session session = null;
		try {
			session = getSession();
			Collection result = DeviceresultFactory.findByTime(session, Time);
			return result;
		} catch (Throwable th) {
			throw new Exception("searchDeviceresultByTime: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("searchDeviceresultByTime: "
							+ he.getMessage());
				}
			}
		}

	}

	//searching by given attribute
	public java.util.Collection searchDeviceresultByValue(java.lang.String Value)
			throws Exception {
		Session session = null;
		try {
			session = getSession();
			Collection result = DeviceresultFactory.findByValue(session, Value);
			return result;
		} catch (Throwable th) {
			throw new Exception("searchDeviceresultByValue: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("searchDeviceresultByValue: "
							+ he.getMessage());
				}
			}
		}

	}

	//searching by PK
	public Deviceresult searchDeviceresultById(java.lang.String id) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Deviceresult result = DeviceresultFactory.findByPrimaryKey(session, id);
			return result;
		} catch (Throwable th) {
			throw new Exception("searchDeviceresultById: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("searchDeviceresultById: " + he.getMessage());
				}
			}
		}

	}

    //search for all instances
	public java.util.Collection getAllFeatureofinterest() throws Exception {
		Session session = null;
		try {
			session = getSession();
			Collection result = FeatureofinterestFactory.getAllObjects(session);
			return result;
		} catch (Throwable th) {
			throw new Exception("getAllFeatureofinterest : " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("getAllFeatureofinterest : " + he.getMessage());
				}
			}
		}

	}

	//searching by given attribute
	public java.util.Collection searchFeatureofinterestByName(java.lang.String Name)
			throws Exception {
		Session session = null;
		try {
			session = getSession();
			Collection result = FeatureofinterestFactory.findByName(session, Name);
			return result;
		} catch (Throwable th) {
			throw new Exception("searchFeatureofinterestByName: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("searchFeatureofinterestByName: "
							+ he.getMessage());
				}
			}
		}

	}

	//searching by PK
	public Featureofinterest searchFeatureofinterestById(java.lang.String id) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Featureofinterest result = FeatureofinterestFactory.findByPrimaryKey(session, id);
			return result;
		} catch (Throwable th) {
			throw new Exception("searchFeatureofinterestById: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("searchFeatureofinterestById: " + he.getMessage());
				}
			}
		}

	}

    //search for all instances
	public java.util.Collection getAllPlatform() throws Exception {
		Session session = null;
		try {
			session = getSession();
			Collection result = PlatformFactory.getAllObjects(session);
			return result;
		} catch (Throwable th) {
			throw new Exception("getAllPlatform : " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("getAllPlatform : " + he.getMessage());
				}
			}
		}

	}

	//searching by given attribute
	public java.util.Collection searchPlatformByName(java.lang.String Name)
			throws Exception {
		Session session = null;
		try {
			session = getSession();
			Collection result = PlatformFactory.findByName(session, Name);
			return result;
		} catch (Throwable th) {
			throw new Exception("searchPlatformByName: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("searchPlatformByName: "
							+ he.getMessage());
				}
			}
		}

	}

	//searching by PK
	public Platform searchPlatformById(java.lang.String id) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Platform result = PlatformFactory.findByPrimaryKey(session, id);
			return result;
		} catch (Throwable th) {
			throw new Exception("searchPlatformById: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("searchPlatformById: " + he.getMessage());
				}
			}
		}

	}

    //search for all instances
	public java.util.Collection getAllProperty() throws Exception {
		Session session = null;
		try {
			session = getSession();
			Collection result = PropertyFactory.getAllObjects(session);
			return result;
		} catch (Throwable th) {
			throw new Exception("getAllProperty : " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("getAllProperty : " + he.getMessage());
				}
			}
		}

	}

	//searching by given attribute
	public java.util.Collection searchPropertyByName(java.lang.String Name)
			throws Exception {
		Session session = null;
		try {
			session = getSession();
			Collection result = PropertyFactory.findByName(session, Name);
			return result;
		} catch (Throwable th) {
			throw new Exception("searchPropertyByName: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("searchPropertyByName: "
							+ he.getMessage());
				}
			}
		}

	}

	//searching by PK
	public Property searchPropertyById(java.lang.String id) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Property result = PropertyFactory.findByPrimaryKey(session, id);
			return result;
		} catch (Throwable th) {
			throw new Exception("searchPropertyById: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("searchPropertyById: " + he.getMessage());
				}
			}
		}

	}

    //search for all instances
	public java.util.Collection getAllDeviceusage() throws Exception {
		Session session = null;
		try {
			session = getSession();
			Collection result = DeviceusageFactory.getAllObjects(session);
			return result;
		} catch (Throwable th) {
			throw new Exception("getAllDeviceusage : " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("getAllDeviceusage : " + he.getMessage());
				}
			}
		}

	}

	//searching by given attribute
	public java.util.Collection searchDeviceusageByName(java.lang.String Name)
			throws Exception {
		Session session = null;
		try {
			session = getSession();
			Collection result = DeviceusageFactory.findByName(session, Name);
			return result;
		} catch (Throwable th) {
			throw new Exception("searchDeviceusageByName: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("searchDeviceusageByName: "
							+ he.getMessage());
				}
			}
		}

	}

	//searching by PK
	public Deviceusage searchDeviceusageById(java.lang.String id) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Deviceusage result = DeviceusageFactory.findByPrimaryKey(session, id);
			return result;
		} catch (Throwable th) {
			throw new Exception("searchDeviceusageById: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("searchDeviceusageById: " + he.getMessage());
				}
			}
		}

	}

    //search for all instances
	public java.util.Collection getAllPropertyoutcome() throws Exception {
		Session session = null;
		try {
			session = getSession();
			Collection result = PropertyoutcomeFactory.getAllObjects(session);
			return result;
		} catch (Throwable th) {
			throw new Exception("getAllPropertyoutcome : " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("getAllPropertyoutcome : " + he.getMessage());
				}
			}
		}

	}

	//searching by given attribute
	public java.util.Collection searchPropertyoutcomeByName(java.lang.String Name)
			throws Exception {
		Session session = null;
		try {
			session = getSession();
			Collection result = PropertyoutcomeFactory.findByName(session, Name);
			return result;
		} catch (Throwable th) {
			throw new Exception("searchPropertyoutcomeByName: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("searchPropertyoutcomeByName: "
							+ he.getMessage());
				}
			}
		}

	}

	//searching by PK
	public Propertyoutcome searchPropertyoutcomeById(java.lang.String id) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Propertyoutcome result = PropertyoutcomeFactory.findByPrimaryKey(session, id);
			return result;
		} catch (Throwable th) {
			throw new Exception("searchPropertyoutcomeById: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("searchPropertyoutcomeById: " + he.getMessage());
				}
			}
		}

	}
	
	// ---------------- handler methods ------------------------



	protected crResult handleMecrdevice(org.hibernate.Session sess,
		java.lang.String platformId, 
		java.lang.String Name)
			throws dao.MerodeException, org.hibernate.HibernateException {
			
		boolean noMultiplePropagationDetected = true;
		
		Platform platform = null;
		// inheritance support: one of the instances is correct, either supertype or subtype
		try {
			 platform = PlatformFactory.findByPrimaryKey(sess, platformId);
		} catch (Exception _exception){
			System.out.println ("not an instance");
		}


		// create object
		MerodeLogger.logln("Creating Device object");
		Device device = DeviceFactory.create(sess);

		// save object in database
		sess.save(device);
		// check user and state preconditions
		device.check_mecrdevice();
		device.getState().check_mecrdevice();
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.PlatformImpl".equals(platform.getClass().getName())) {
			platform.getState().check_mecrdevice();	
			platform.check_mecrdevice();		
		} 
		else {
		}
		
		// register connections


		platform.attachDevice(device);
		device.setPlatform(platform);
		// execute creating method
		device.mecrdevice( Name);
		device.getState().mecrdevice(sess, device);

		// propagation and state modifications
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.PlatformImpl".equals(platform.getClass().getName())) {
			platform.getState().mecrdevice(sess, platform);	
			platform.mecrdevice();
		} 
		else {
		}
		
		crResult result = new crResult(noMultiplePropagationDetected, device.getId());
		return result;
	}

	protected void handleMeenddevice(org.hibernate.Session sess,
			java.lang.String deviceId) throws dao.MerodeException,
				org.hibernate.HibernateException {
		Device device = DeviceFactory.findByPrimaryKey(sess, deviceId);
		Platform platform = device.getPlatform();

		// check user and state preconditions
		device.check_meenddevice();
		device.getState().check_meenddevice();
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.PlatformImpl".equals(platform.getClass().getName())) {
			platform.getState().check_meenddevice();	
			platform.check_meenddevice();		
		} 
		else {
		}

		// execute end object
		device.meenddevice();
		device.getState().meenddevice(sess, device);

		// propagation and state modifications
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.PlatformImpl".equals(platform.getClass().getName())) {
			platform.getState().meenddevice(sess, platform);	
			platform.meenddevice();
		} 
		else {
		}

	}










	protected crResult handleMecroutcome(org.hibernate.Session sess,
		java.lang.String deviceId, 
		java.lang.String Name)
			throws dao.MerodeException, org.hibernate.HibernateException {
			
		boolean noMultiplePropagationDetected = true;
		
		Device device = null;
		// inheritance support: one of the instances is correct, either supertype or subtype
		try {
			 device = DeviceFactory.findByPrimaryKey(sess, deviceId);
		} catch (Exception _exception){
			System.out.println ("not an instance");
		}


		// create object
		MerodeLogger.logln("Creating Outcome object");
		Outcome outcome = OutcomeFactory.create(sess);

		// save object in database
		sess.save(outcome);
		// check user and state preconditions
		outcome.check_mecroutcome();
		outcome.getState().check_mecroutcome();
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.DeviceImpl".equals(device.getClass().getName())) {
			device.getState().check_mecroutcome();	
			device.check_mecroutcome();		
		} 
		else {
		}
		device.getPlatform().getState().check_mecroutcome();
		device.getPlatform().check_mecroutcome();
		
		// register connections


		device.attachOutcome(outcome);
		outcome.setDevice(device);
		// execute creating method
		outcome.mecroutcome( Name);
		outcome.getState().mecroutcome(sess, outcome);

		// propagation and state modifications
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.DeviceImpl".equals(device.getClass().getName())) {
			device.getState().mecroutcome(sess, device);	
			device.mecroutcome();
		} 
		else {
		}
		device.getPlatform().getState().mecroutcome(sess, device.getPlatform());
		device.getPlatform().mecroutcome();
		
		crResult result = new crResult(noMultiplePropagationDetected, outcome.getId());
		return result;
	}

	protected void handleMeendoutcome(org.hibernate.Session sess,
			java.lang.String outcomeId) throws dao.MerodeException,
				org.hibernate.HibernateException {
		Outcome outcome = OutcomeFactory.findByPrimaryKey(sess, outcomeId);
		Device device = outcome.getDevice();

		// check user and state preconditions
		outcome.check_meendoutcome();
		outcome.getState().check_meendoutcome();
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.DeviceImpl".equals(device.getClass().getName())) {
			device.getState().check_meendoutcome();	
			device.check_meendoutcome();		
		} 
		else {
		}
		device.getPlatform().getState().check_meendoutcome();
		device.getPlatform().check_meendoutcome();

		// execute end object
		outcome.meendoutcome();
		outcome.getState().meendoutcome(sess, outcome);

		// propagation and state modifications
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.DeviceImpl".equals(device.getClass().getName())) {
			device.getState().meendoutcome(sess, device);	
			device.meendoutcome();
		} 
		else {
		}
		device.getPlatform().getState().meendoutcome(sess, device.getPlatform());
		device.getPlatform().meendoutcome();

	}










	protected crResult handleMecrdeviceresult(org.hibernate.Session sess,
		java.lang.String propertyoutcomeId, 
		 java.lang.String Time,
		java.lang.String Value)
			throws dao.MerodeException, org.hibernate.HibernateException {
			
		boolean noMultiplePropagationDetected = true;
		
		Propertyoutcome propertyoutcome = null;
		// inheritance support: one of the instances is correct, either supertype or subtype
		try {
			 propertyoutcome = PropertyoutcomeFactory.findByPrimaryKey(sess, propertyoutcomeId);
		} catch (Exception _exception){
			System.out.println ("not an instance");
		}


		// create object
		MerodeLogger.logln("Creating Deviceresult object");
		Deviceresult deviceresult = DeviceresultFactory.create(sess);

		// save object in database
		sess.save(deviceresult);
		// check user and state preconditions
		deviceresult.check_mecrdeviceresult();
		deviceresult.getState().check_mecrdeviceresult();
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.PropertyoutcomeImpl".equals(propertyoutcome.getClass().getName())) {
			propertyoutcome.getState().check_mecrdeviceresult();	
			propertyoutcome.check_mecrdeviceresult();		
		} 
		else {
		}
		propertyoutcome.getOutcome().getDevice().getPlatform().getState().check_mecrdeviceresult();
		propertyoutcome.getOutcome().getDevice().getPlatform().check_mecrdeviceresult();
		propertyoutcome.getDeviceusage().getDevice().getState().check_mecrdeviceresult();
		propertyoutcome.getDeviceusage().getDevice().check_mecrdeviceresult();
		propertyoutcome.getOutcome().getState().check_mecrdeviceresult();
		propertyoutcome.getOutcome().check_mecrdeviceresult();
		propertyoutcome.getDeviceusage().getState().check_mecrdeviceresult();
		propertyoutcome.getDeviceusage().check_mecrdeviceresult();
		propertyoutcome.getDeviceusage().getFeatureofinterest().getState().check_mecrdeviceresult();
		propertyoutcome.getDeviceusage().getFeatureofinterest().check_mecrdeviceresult();
		propertyoutcome.getProperty().getFeatureofinterest().getState().check_mecrdeviceresult();
		propertyoutcome.getProperty().getFeatureofinterest().check_mecrdeviceresult();
		propertyoutcome.getOutcome().getDevice().getState().check_mecrdeviceresult();
		propertyoutcome.getOutcome().getDevice().check_mecrdeviceresult();
		propertyoutcome.getProperty().getState().check_mecrdeviceresult();
		propertyoutcome.getProperty().check_mecrdeviceresult();
		propertyoutcome.getDeviceusage().getDevice().getPlatform().getState().check_mecrdeviceresult();
		propertyoutcome.getDeviceusage().getDevice().getPlatform().check_mecrdeviceresult();
		
		// register connections


		propertyoutcome.attachDeviceresult(deviceresult);
		deviceresult.setPropertyoutcome(propertyoutcome);
		// execute creating method
		deviceresult.mecrdeviceresult( Time, Value);
		deviceresult.getState().mecrdeviceresult(sess, deviceresult);

		// propagation and state modifications
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.PropertyoutcomeImpl".equals(propertyoutcome.getClass().getName())) {
			propertyoutcome.getState().mecrdeviceresult(sess, propertyoutcome);	
			propertyoutcome.mecrdeviceresult();
		} 
		else {
		}
		propertyoutcome.getOutcome().getDevice().getPlatform().getState().mecrdeviceresult(sess, propertyoutcome.getOutcome().getDevice().getPlatform());
		propertyoutcome.getOutcome().getDevice().getPlatform().mecrdeviceresult();
		propertyoutcome.getDeviceusage().getDevice().getState().mecrdeviceresult(sess, propertyoutcome.getDeviceusage().getDevice());
		propertyoutcome.getDeviceusage().getDevice().mecrdeviceresult();
		propertyoutcome.getOutcome().getState().mecrdeviceresult(sess, propertyoutcome.getOutcome());
		propertyoutcome.getOutcome().mecrdeviceresult();
		propertyoutcome.getDeviceusage().getState().mecrdeviceresult(sess, propertyoutcome.getDeviceusage());
		propertyoutcome.getDeviceusage().mecrdeviceresult();
		propertyoutcome.getDeviceusage().getFeatureofinterest().getState().mecrdeviceresult(sess, propertyoutcome.getDeviceusage().getFeatureofinterest());
		propertyoutcome.getDeviceusage().getFeatureofinterest().mecrdeviceresult();
		propertyoutcome.getProperty().getFeatureofinterest().getState().mecrdeviceresult(sess, propertyoutcome.getProperty().getFeatureofinterest());
		propertyoutcome.getProperty().getFeatureofinterest().mecrdeviceresult();
		propertyoutcome.getOutcome().getDevice().getState().mecrdeviceresult(sess, propertyoutcome.getOutcome().getDevice());
		propertyoutcome.getOutcome().getDevice().mecrdeviceresult();
		propertyoutcome.getProperty().getState().mecrdeviceresult(sess, propertyoutcome.getProperty());
		propertyoutcome.getProperty().mecrdeviceresult();
		propertyoutcome.getDeviceusage().getDevice().getPlatform().getState().mecrdeviceresult(sess, propertyoutcome.getDeviceusage().getDevice().getPlatform());
		propertyoutcome.getDeviceusage().getDevice().getPlatform().mecrdeviceresult();
		
		crResult result = new crResult(noMultiplePropagationDetected, deviceresult.getId());
		return result;
	}

	protected void handleMeenddeviceresult(org.hibernate.Session sess,
			java.lang.String deviceresultId) throws dao.MerodeException,
				org.hibernate.HibernateException {
		Deviceresult deviceresult = DeviceresultFactory.findByPrimaryKey(sess, deviceresultId);
		Propertyoutcome propertyoutcome = deviceresult.getPropertyoutcome();

		// check user and state preconditions
		deviceresult.check_meenddeviceresult();
		deviceresult.getState().check_meenddeviceresult();
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.PropertyoutcomeImpl".equals(propertyoutcome.getClass().getName())) {
			propertyoutcome.getState().check_meenddeviceresult();	
			propertyoutcome.check_meenddeviceresult();		
		} 
		else {
		}
		propertyoutcome.getOutcome().getDevice().getPlatform().getState().check_meenddeviceresult();
		propertyoutcome.getOutcome().getDevice().getPlatform().check_meenddeviceresult();
		propertyoutcome.getDeviceusage().getDevice().getState().check_meenddeviceresult();
		propertyoutcome.getDeviceusage().getDevice().check_meenddeviceresult();
		propertyoutcome.getOutcome().getState().check_meenddeviceresult();
		propertyoutcome.getOutcome().check_meenddeviceresult();
		propertyoutcome.getDeviceusage().getState().check_meenddeviceresult();
		propertyoutcome.getDeviceusage().check_meenddeviceresult();
		propertyoutcome.getDeviceusage().getFeatureofinterest().getState().check_meenddeviceresult();
		propertyoutcome.getDeviceusage().getFeatureofinterest().check_meenddeviceresult();
		propertyoutcome.getProperty().getFeatureofinterest().getState().check_meenddeviceresult();
		propertyoutcome.getProperty().getFeatureofinterest().check_meenddeviceresult();
		propertyoutcome.getOutcome().getDevice().getState().check_meenddeviceresult();
		propertyoutcome.getOutcome().getDevice().check_meenddeviceresult();
		propertyoutcome.getProperty().getState().check_meenddeviceresult();
		propertyoutcome.getProperty().check_meenddeviceresult();
		propertyoutcome.getDeviceusage().getDevice().getPlatform().getState().check_meenddeviceresult();
		propertyoutcome.getDeviceusage().getDevice().getPlatform().check_meenddeviceresult();

		// execute end object
		deviceresult.meenddeviceresult();
		deviceresult.getState().meenddeviceresult(sess, deviceresult);

		// propagation and state modifications
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.PropertyoutcomeImpl".equals(propertyoutcome.getClass().getName())) {
			propertyoutcome.getState().meenddeviceresult(sess, propertyoutcome);	
			propertyoutcome.meenddeviceresult();
		} 
		else {
		}
		propertyoutcome.getOutcome().getDevice().getPlatform().getState().meenddeviceresult(sess, propertyoutcome.getOutcome().getDevice().getPlatform());
		propertyoutcome.getOutcome().getDevice().getPlatform().meenddeviceresult();
		propertyoutcome.getDeviceusage().getDevice().getState().meenddeviceresult(sess, propertyoutcome.getDeviceusage().getDevice());
		propertyoutcome.getDeviceusage().getDevice().meenddeviceresult();
		propertyoutcome.getOutcome().getState().meenddeviceresult(sess, propertyoutcome.getOutcome());
		propertyoutcome.getOutcome().meenddeviceresult();
		propertyoutcome.getDeviceusage().getState().meenddeviceresult(sess, propertyoutcome.getDeviceusage());
		propertyoutcome.getDeviceusage().meenddeviceresult();
		propertyoutcome.getDeviceusage().getFeatureofinterest().getState().meenddeviceresult(sess, propertyoutcome.getDeviceusage().getFeatureofinterest());
		propertyoutcome.getDeviceusage().getFeatureofinterest().meenddeviceresult();
		propertyoutcome.getProperty().getFeatureofinterest().getState().meenddeviceresult(sess, propertyoutcome.getProperty().getFeatureofinterest());
		propertyoutcome.getProperty().getFeatureofinterest().meenddeviceresult();
		propertyoutcome.getOutcome().getDevice().getState().meenddeviceresult(sess, propertyoutcome.getOutcome().getDevice());
		propertyoutcome.getOutcome().getDevice().meenddeviceresult();
		propertyoutcome.getProperty().getState().meenddeviceresult(sess, propertyoutcome.getProperty());
		propertyoutcome.getProperty().meenddeviceresult();
		propertyoutcome.getDeviceusage().getDevice().getPlatform().getState().meenddeviceresult(sess, propertyoutcome.getDeviceusage().getDevice().getPlatform());
		propertyoutcome.getDeviceusage().getDevice().getPlatform().meenddeviceresult();

	}










	protected crResult handleMecrfeatureofinterest(org.hibernate.Session sess,
		java.lang.String Name)
			throws dao.MerodeException, org.hibernate.HibernateException {
			
		boolean noMultiplePropagationDetected = true;
		

		// create object
		MerodeLogger.logln("Creating Featureofinterest object");
		Featureofinterest featureofinterest = FeatureofinterestFactory.create(sess);

		// save object in database
		sess.save(featureofinterest);
		// check user and state preconditions
		featureofinterest.check_mecrfeatureofinterest();
		featureofinterest.getState().check_mecrfeatureofinterest();
		
		// register connections


		// execute creating method
		featureofinterest.mecrfeatureofinterest( Name);
		featureofinterest.getState().mecrfeatureofinterest(sess, featureofinterest);

		// propagation and state modifications
		
		crResult result = new crResult(noMultiplePropagationDetected, featureofinterest.getId());
		return result;
	}

	protected void handleMeendfeatureofinterest(org.hibernate.Session sess,
			java.lang.String featureofinterestId) throws dao.MerodeException,
				org.hibernate.HibernateException {
		Featureofinterest featureofinterest = FeatureofinterestFactory.findByPrimaryKey(sess, featureofinterestId);

		// check user and state preconditions
		featureofinterest.check_meendfeatureofinterest();
		featureofinterest.getState().check_meendfeatureofinterest();

		// execute end object
		featureofinterest.meendfeatureofinterest();
		featureofinterest.getState().meendfeatureofinterest(sess, featureofinterest);

		// propagation and state modifications

	}










	protected crResult handleMecrplatform(org.hibernate.Session sess,
		java.lang.String Name)
			throws dao.MerodeException, org.hibernate.HibernateException {
			
		boolean noMultiplePropagationDetected = true;
		

		// create object
		MerodeLogger.logln("Creating Platform object");
		Platform platform = PlatformFactory.create(sess);

		// save object in database
		sess.save(platform);
		// check user and state preconditions
		platform.check_mecrplatform();
		platform.getState().check_mecrplatform();
		
		// register connections


		// execute creating method
		platform.mecrplatform( Name);
		platform.getState().mecrplatform(sess, platform);

		// propagation and state modifications
		
		crResult result = new crResult(noMultiplePropagationDetected, platform.getId());
		return result;
	}

	protected void handleMeendplatform(org.hibernate.Session sess,
			java.lang.String platformId) throws dao.MerodeException,
				org.hibernate.HibernateException {
		Platform platform = PlatformFactory.findByPrimaryKey(sess, platformId);

		// check user and state preconditions
		platform.check_meendplatform();
		platform.getState().check_meendplatform();

		// execute end object
		platform.meendplatform();
		platform.getState().meendplatform(sess, platform);

		// propagation and state modifications

	}










	protected crResult handleMecrproperty(org.hibernate.Session sess,
		java.lang.String featureofinterestId, 
		java.lang.String Name)
			throws dao.MerodeException, org.hibernate.HibernateException {
			
		boolean noMultiplePropagationDetected = true;
		
		Featureofinterest featureofinterest = null;
		// inheritance support: one of the instances is correct, either supertype or subtype
		try {
			 featureofinterest = FeatureofinterestFactory.findByPrimaryKey(sess, featureofinterestId);
		} catch (Exception _exception){
			System.out.println ("not an instance");
		}


		// create object
		MerodeLogger.logln("Creating Property object");
		Property property = PropertyFactory.create(sess);

		// save object in database
		sess.save(property);
		// check user and state preconditions
		property.check_mecrproperty();
		property.getState().check_mecrproperty();
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.FeatureofinterestImpl".equals(featureofinterest.getClass().getName())) {
			featureofinterest.getState().check_mecrproperty();	
			featureofinterest.check_mecrproperty();		
		} 
		else {
		}
		
		// register connections


		featureofinterest.attachProperty(property);
		property.setFeatureofinterest(featureofinterest);
		// execute creating method
		property.mecrproperty( Name);
		property.getState().mecrproperty(sess, property);

		// propagation and state modifications
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.FeatureofinterestImpl".equals(featureofinterest.getClass().getName())) {
			featureofinterest.getState().mecrproperty(sess, featureofinterest);	
			featureofinterest.mecrproperty();
		} 
		else {
		}
		
		crResult result = new crResult(noMultiplePropagationDetected, property.getId());
		return result;
	}

	protected void handleMeendproperty(org.hibernate.Session sess,
			java.lang.String propertyId) throws dao.MerodeException,
				org.hibernate.HibernateException {
		Property property = PropertyFactory.findByPrimaryKey(sess, propertyId);
		Featureofinterest featureofinterest = property.getFeatureofinterest();

		// check user and state preconditions
		property.check_meendproperty();
		property.getState().check_meendproperty();
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.FeatureofinterestImpl".equals(featureofinterest.getClass().getName())) {
			featureofinterest.getState().check_meendproperty();	
			featureofinterest.check_meendproperty();		
		} 
		else {
		}

		// execute end object
		property.meendproperty();
		property.getState().meendproperty(sess, property);

		// propagation and state modifications
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.FeatureofinterestImpl".equals(featureofinterest.getClass().getName())) {
			featureofinterest.getState().meendproperty(sess, featureofinterest);	
			featureofinterest.meendproperty();
		} 
		else {
		}

	}










	protected crResult handleMecrdeviceusage(org.hibernate.Session sess,
		java.lang.String featureofinterestId, 
		java.lang.String deviceId, 
		java.lang.String Name)
			throws dao.MerodeException, org.hibernate.HibernateException {
			
		boolean noMultiplePropagationDetected = true;
		
		Featureofinterest featureofinterest = null;
		// inheritance support: one of the instances is correct, either supertype or subtype
		try {
			 featureofinterest = FeatureofinterestFactory.findByPrimaryKey(sess, featureofinterestId);
		} catch (Exception _exception){
			System.out.println ("not an instance");
		}

		Device device = null;
		// inheritance support: one of the instances is correct, either supertype or subtype
		try {
			 device = DeviceFactory.findByPrimaryKey(sess, deviceId);
		} catch (Exception _exception){
			System.out.println ("not an instance");
		}


		// create object
		MerodeLogger.logln("Creating Deviceusage object");
		Deviceusage deviceusage = DeviceusageFactory.create(sess);

		// save object in database
		sess.save(deviceusage);
		// check user and state preconditions
		deviceusage.check_mecrdeviceusage();
		deviceusage.getState().check_mecrdeviceusage();
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.FeatureofinterestImpl".equals(featureofinterest.getClass().getName())) {
			featureofinterest.getState().check_mecrdeviceusage();	
			featureofinterest.check_mecrdeviceusage();		
		} 
		else {
		}
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.DeviceImpl".equals(device.getClass().getName())) {
			device.getState().check_mecrdeviceusage();	
			device.check_mecrdeviceusage();		
		} 
		else {
		}
		device.getPlatform().getState().check_mecrdeviceusage();
		device.getPlatform().check_mecrdeviceusage();
		
		// register connections


		featureofinterest.attachDeviceusage(deviceusage);
		deviceusage.setFeatureofinterest(featureofinterest);
		device.attachDeviceusage(deviceusage);
		deviceusage.setDevice(device);
		// execute creating method
		deviceusage.mecrdeviceusage( Name);
		deviceusage.getState().mecrdeviceusage(sess, deviceusage);

		// propagation and state modifications
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.FeatureofinterestImpl".equals(featureofinterest.getClass().getName())) {
			featureofinterest.getState().mecrdeviceusage(sess, featureofinterest);	
			featureofinterest.mecrdeviceusage();
		} 
		else {
		}
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.DeviceImpl".equals(device.getClass().getName())) {
			device.getState().mecrdeviceusage(sess, device);	
			device.mecrdeviceusage();
		} 
		else {
		}
		device.getPlatform().getState().mecrdeviceusage(sess, device.getPlatform());
		device.getPlatform().mecrdeviceusage();
		
		crResult result = new crResult(noMultiplePropagationDetected, deviceusage.getId());
		return result;
	}

	protected void handleMeenddeviceusage(org.hibernate.Session sess,
			java.lang.String deviceusageId) throws dao.MerodeException,
				org.hibernate.HibernateException {
		Deviceusage deviceusage = DeviceusageFactory.findByPrimaryKey(sess, deviceusageId);
		Featureofinterest featureofinterest = deviceusage.getFeatureofinterest();
		Device device = deviceusage.getDevice();

		// check user and state preconditions
		deviceusage.check_meenddeviceusage();
		deviceusage.getState().check_meenddeviceusage();
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.FeatureofinterestImpl".equals(featureofinterest.getClass().getName())) {
			featureofinterest.getState().check_meenddeviceusage();	
			featureofinterest.check_meenddeviceusage();		
		} 
		else {
		}
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.DeviceImpl".equals(device.getClass().getName())) {
			device.getState().check_meenddeviceusage();	
			device.check_meenddeviceusage();		
		} 
		else {
		}
		device.getPlatform().getState().check_meenddeviceusage();
		device.getPlatform().check_meenddeviceusage();

		// execute end object
		deviceusage.meenddeviceusage();
		deviceusage.getState().meenddeviceusage(sess, deviceusage);

		// propagation and state modifications
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.FeatureofinterestImpl".equals(featureofinterest.getClass().getName())) {
			featureofinterest.getState().meenddeviceusage(sess, featureofinterest);	
			featureofinterest.meenddeviceusage();
		} 
		else {
		}
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.DeviceImpl".equals(device.getClass().getName())) {
			device.getState().meenddeviceusage(sess, device);	
			device.meenddeviceusage();
		} 
		else {
		}
		device.getPlatform().getState().meenddeviceusage(sess, device.getPlatform());
		device.getPlatform().meenddeviceusage();

	}
	protected void handleDeviceundeployment(org.hibernate.Session sess,
			java.lang.String deviceusageId) throws dao.MerodeException,
				org.hibernate.HibernateException {
		Deviceusage deviceusage = DeviceusageFactory.findByPrimaryKey(sess, deviceusageId);
		Featureofinterest featureofinterest = deviceusage.getFeatureofinterest();
		Device device = deviceusage.getDevice();

		// check user and state preconditions
		deviceusage.check_deviceundeployment();
		deviceusage.getState().check_deviceundeployment();
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.FeatureofinterestImpl".equals(featureofinterest.getClass().getName())) {
			featureofinterest.getState().check_deviceundeployment();	
			featureofinterest.check_deviceundeployment();		
		} 
		else {
		}
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.DeviceImpl".equals(device.getClass().getName())) {
			device.getState().check_deviceundeployment();	
			device.check_deviceundeployment();		
		} 
		else {
		}
		device.getPlatform().getState().check_deviceundeployment();
		device.getPlatform().check_deviceundeployment();

		// execute end object
		deviceusage.deviceundeployment();
		deviceusage.getState().deviceundeployment(sess, deviceusage);

		// propagation and state modifications
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.FeatureofinterestImpl".equals(featureofinterest.getClass().getName())) {
			featureofinterest.getState().deviceundeployment(sess, featureofinterest);	
			featureofinterest.deviceundeployment();
		} 
		else {
		}
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.DeviceImpl".equals(device.getClass().getName())) {
			device.getState().deviceundeployment(sess, device);	
			device.deviceundeployment();
		} 
		else {
		}
		device.getPlatform().getState().deviceundeployment(sess, device.getPlatform());
		device.getPlatform().deviceundeployment();

	}

	protected void handleDevicedeployment(org.hibernate.Session sess,
		java.lang.String deviceusageId, java.lang.String Name)
			throws dao.MerodeException, org.hibernate.HibernateException {

				Deviceusage deviceusage = null;
		// inheritance support: one of the instances is correct, either supertype or subtype
		try {
			 deviceusage = DeviceusageFactory.findByPrimaryKey(sess, deviceusageId);
		} catch (Exception _excption){
			System.out.println ("not an instance");
		}

		Featureofinterest featureofinterest = deviceusage.getFeatureofinterest();
		Device device = deviceusage.getDevice();
		// check user and state preconditions
		deviceusage.check_devicedeployment();
		deviceusage.getState().check_devicedeployment();
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.FeatureofinterestImpl".equals(featureofinterest.getClass().getName())) {
			featureofinterest.getState().check_devicedeployment();	
			featureofinterest.check_devicedeployment();		
		} 
		else {
		}
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.DeviceImpl".equals(device.getClass().getName())) {
			device.getState().check_devicedeployment();	
			device.check_devicedeployment();		
		} 
		else {
		}
		device.getPlatform().getState().check_devicedeployment();
		device.getPlatform().check_devicedeployment();


		// execute modifying method
		deviceusage.devicedeployment( Name);
		deviceusage.getState().devicedeployment(sess, deviceusage);

		// propagation and state modifications
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.FeatureofinterestImpl".equals(featureofinterest.getClass().getName())) {
			featureofinterest.getState().devicedeployment(sess, featureofinterest);	
			featureofinterest.devicedeployment();
		} 
		else {
		}
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.DeviceImpl".equals(device.getClass().getName())) {
			device.getState().devicedeployment(sess, device);	
			device.devicedeployment();
		} 
		else {
		}
		device.getPlatform().getState().devicedeployment(sess, device.getPlatform());
		device.getPlatform().devicedeployment();

	}









	protected crResult handleMecrpropertyoutcome(org.hibernate.Session sess,
		java.lang.String outcomeId, 
		java.lang.String propertyId, 
		java.lang.String deviceusageId, 
		java.lang.String Name)
			throws dao.MerodeException, org.hibernate.HibernateException {
			
		boolean noMultiplePropagationDetected = true;
		
		Outcome outcome = null;
		// inheritance support: one of the instances is correct, either supertype or subtype
		try {
			 outcome = OutcomeFactory.findByPrimaryKey(sess, outcomeId);
		} catch (Exception _exception){
			System.out.println ("not an instance");
		}

		Property property = null;
		// inheritance support: one of the instances is correct, either supertype or subtype
		try {
			 property = PropertyFactory.findByPrimaryKey(sess, propertyId);
		} catch (Exception _exception){
			System.out.println ("not an instance");
		}

		Deviceusage deviceusage = null;
		// inheritance support: one of the instances is correct, either supertype or subtype
		try {
			 deviceusage = DeviceusageFactory.findByPrimaryKey(sess, deviceusageId);
		} catch (Exception _exception){
			System.out.println ("not an instance");
		}


		// -------- multiple propagation check (1) ---------
        if (!(outcome.getDevice().getId()
				.equals(deviceusage.getDevice().getId()))){
			
        	MerodeLogger.logln(" check for multipropagation constraint...failed");
        	ImageIcon icon = new ImageIcon(this.getClass().getResource(
					"teacher.png"));

			JOptionPane.showOptionDialog(
					null,
					"<html><b>WARNING: Constraint violation</b><br><br>"
					+ "Make sure that the two paths lead<br>"
					+ " to the same master objects.<br><br>"
					+ "<font color='green'><b>"
					+ "FIRST PATH</b><br>" + "outcome.getDevice()"
					+ "</font><br><br><font color='blue'><b>"
					+ "SECOND PATH</b><br>" + "deviceusage.getDevice()"
					+ "</font><br></html>",
					"Multipropagation Constraint Violation",
					JOptionPane.DEFAULT_OPTION, 
					JOptionPane.WARNING_MESSAGE, 
					icon,
					null, 
					null
			);
			
			return new crResult(false, null);

 		}
		// ----------------------------------------------
		// -------- multiple propagation check (2) ---------
        if (!(property.getFeatureofinterest().getId()
				.equals(deviceusage.getFeatureofinterest().getId()))){
			
        	MerodeLogger.logln(" check for multipropagation constraint...failed");
        	ImageIcon icon = new ImageIcon(this.getClass().getResource(
					"teacher.png"));

			JOptionPane.showOptionDialog(
					null,
					"<html><b>WARNING: Constraint violation</b><br><br>"
					+ "Make sure that the two paths lead<br>"
					+ " to the same master objects.<br><br>"
					+ "<font color='green'><b>"
					+ "FIRST PATH</b><br>" + "property.getFeatureofinterest()"
					+ "</font><br><br><font color='blue'><b>"
					+ "SECOND PATH</b><br>" + "deviceusage.getFeatureofinterest()"
					+ "</font><br></html>",
					"Multipropagation Constraint Violation",
					JOptionPane.DEFAULT_OPTION, 
					JOptionPane.WARNING_MESSAGE, 
					icon,
					null, 
					null
			);
			
			return new crResult(false, null);

 		}
		// ----------------------------------------------
		// create object
		MerodeLogger.logln("Creating Propertyoutcome object");
		Propertyoutcome propertyoutcome = PropertyoutcomeFactory.create(sess);

		// save object in database
		sess.save(propertyoutcome);
		// check user and state preconditions
		propertyoutcome.check_mecrpropertyoutcome();
		propertyoutcome.getState().check_mecrpropertyoutcome();
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.OutcomeImpl".equals(outcome.getClass().getName())) {
			outcome.getState().check_mecrpropertyoutcome();	
			outcome.check_mecrpropertyoutcome();		
		} 
		else {
		}
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.PropertyImpl".equals(property.getClass().getName())) {
			property.getState().check_mecrpropertyoutcome();	
			property.check_mecrpropertyoutcome();		
		} 
		else {
		}
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.DeviceusageImpl".equals(deviceusage.getClass().getName())) {
			deviceusage.getState().check_mecrpropertyoutcome();	
			deviceusage.check_mecrpropertyoutcome();		
		} 
		else {
		}
		property.getFeatureofinterest().getState().check_mecrpropertyoutcome();
		property.getFeatureofinterest().check_mecrpropertyoutcome();
		outcome.getDevice().getState().check_mecrpropertyoutcome();
		outcome.getDevice().check_mecrpropertyoutcome();
		outcome.getDevice().getPlatform().getState().check_mecrpropertyoutcome();
		outcome.getDevice().getPlatform().check_mecrpropertyoutcome();
		deviceusage.getDevice().getState().check_mecrpropertyoutcome();
		deviceusage.getDevice().check_mecrpropertyoutcome();
		deviceusage.getDevice().getPlatform().getState().check_mecrpropertyoutcome();
		deviceusage.getDevice().getPlatform().check_mecrpropertyoutcome();
		deviceusage.getFeatureofinterest().getState().check_mecrpropertyoutcome();
		deviceusage.getFeatureofinterest().check_mecrpropertyoutcome();
		
		// register connections


		outcome.attachPropertyoutcome(propertyoutcome);
		propertyoutcome.setOutcome(outcome);
		property.attachPropertyoutcome(propertyoutcome);
		propertyoutcome.setProperty(property);
		deviceusage.attachPropertyoutcome(propertyoutcome);
		propertyoutcome.setDeviceusage(deviceusage);
		// execute creating method
		propertyoutcome.mecrpropertyoutcome( Name);
		propertyoutcome.getState().mecrpropertyoutcome(sess, propertyoutcome);

		// propagation and state modifications
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.OutcomeImpl".equals(outcome.getClass().getName())) {
			outcome.getState().mecrpropertyoutcome(sess, outcome);	
			outcome.mecrpropertyoutcome();
		} 
		else {
		}
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.PropertyImpl".equals(property.getClass().getName())) {
			property.getState().mecrpropertyoutcome(sess, property);	
			property.mecrpropertyoutcome();
		} 
		else {
		}
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.DeviceusageImpl".equals(deviceusage.getClass().getName())) {
			deviceusage.getState().mecrpropertyoutcome(sess, deviceusage);	
			deviceusage.mecrpropertyoutcome();
		} 
		else {
		}
		property.getFeatureofinterest().getState().mecrpropertyoutcome(sess, property.getFeatureofinterest());
		property.getFeatureofinterest().mecrpropertyoutcome();
		outcome.getDevice().getState().mecrpropertyoutcome(sess, outcome.getDevice());
		outcome.getDevice().mecrpropertyoutcome();
		outcome.getDevice().getPlatform().getState().mecrpropertyoutcome(sess, outcome.getDevice().getPlatform());
		outcome.getDevice().getPlatform().mecrpropertyoutcome();
		deviceusage.getDevice().getState().mecrpropertyoutcome(sess, deviceusage.getDevice());
		deviceusage.getDevice().mecrpropertyoutcome();
		deviceusage.getDevice().getPlatform().getState().mecrpropertyoutcome(sess, deviceusage.getDevice().getPlatform());
		deviceusage.getDevice().getPlatform().mecrpropertyoutcome();
		deviceusage.getFeatureofinterest().getState().mecrpropertyoutcome(sess, deviceusage.getFeatureofinterest());
		deviceusage.getFeatureofinterest().mecrpropertyoutcome();
		
		crResult result = new crResult(noMultiplePropagationDetected, propertyoutcome.getId());
		return result;
	}

	protected void handleMeendpropertyoutcome(org.hibernate.Session sess,
			java.lang.String propertyoutcomeId) throws dao.MerodeException,
				org.hibernate.HibernateException {
		Propertyoutcome propertyoutcome = PropertyoutcomeFactory.findByPrimaryKey(sess, propertyoutcomeId);
		Outcome outcome = propertyoutcome.getOutcome();
		Property property = propertyoutcome.getProperty();
		Deviceusage deviceusage = propertyoutcome.getDeviceusage();

		// check user and state preconditions
		propertyoutcome.check_meendpropertyoutcome();
		propertyoutcome.getState().check_meendpropertyoutcome();
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.OutcomeImpl".equals(outcome.getClass().getName())) {
			outcome.getState().check_meendpropertyoutcome();	
			outcome.check_meendpropertyoutcome();		
		} 
		else {
		}
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.PropertyImpl".equals(property.getClass().getName())) {
			property.getState().check_meendpropertyoutcome();	
			property.check_meendpropertyoutcome();		
		} 
		else {
		}
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.DeviceusageImpl".equals(deviceusage.getClass().getName())) {
			deviceusage.getState().check_meendpropertyoutcome();	
			deviceusage.check_meendpropertyoutcome();		
		} 
		else {
		}
		property.getFeatureofinterest().getState().check_meendpropertyoutcome();
		property.getFeatureofinterest().check_meendpropertyoutcome();
		outcome.getDevice().getState().check_meendpropertyoutcome();
		outcome.getDevice().check_meendpropertyoutcome();
		outcome.getDevice().getPlatform().getState().check_meendpropertyoutcome();
		outcome.getDevice().getPlatform().check_meendpropertyoutcome();
		deviceusage.getDevice().getState().check_meendpropertyoutcome();
		deviceusage.getDevice().check_meendpropertyoutcome();
		deviceusage.getDevice().getPlatform().getState().check_meendpropertyoutcome();
		deviceusage.getDevice().getPlatform().check_meendpropertyoutcome();
		deviceusage.getFeatureofinterest().getState().check_meendpropertyoutcome();
		deviceusage.getFeatureofinterest().check_meendpropertyoutcome();

		// execute end object
		propertyoutcome.meendpropertyoutcome();
		propertyoutcome.getState().meendpropertyoutcome(sess, propertyoutcome);

		// propagation and state modifications
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.OutcomeImpl".equals(outcome.getClass().getName())) {
			outcome.getState().meendpropertyoutcome(sess, outcome);	
			outcome.meendpropertyoutcome();
		} 
		else {
		}
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.PropertyImpl".equals(property.getClass().getName())) {
			property.getState().meendpropertyoutcome(sess, property);	
			property.meendpropertyoutcome();
		} 
		else {
		}
		// inheritance support: one of the instances is correct, either supertype or subtype
		if ("dao.DeviceusageImpl".equals(deviceusage.getClass().getName())) {
			deviceusage.getState().meendpropertyoutcome(sess, deviceusage);	
			deviceusage.meendpropertyoutcome();
		} 
		else {
		}
		property.getFeatureofinterest().getState().meendpropertyoutcome(sess, property.getFeatureofinterest());
		property.getFeatureofinterest().meendpropertyoutcome();
		outcome.getDevice().getState().meendpropertyoutcome(sess, outcome.getDevice());
		outcome.getDevice().meendpropertyoutcome();
		outcome.getDevice().getPlatform().getState().meendpropertyoutcome(sess, outcome.getDevice().getPlatform());
		outcome.getDevice().getPlatform().meendpropertyoutcome();
		deviceusage.getDevice().getState().meendpropertyoutcome(sess, deviceusage.getDevice());
		deviceusage.getDevice().meendpropertyoutcome();
		deviceusage.getDevice().getPlatform().getState().meendpropertyoutcome(sess, deviceusage.getDevice().getPlatform());
		deviceusage.getDevice().getPlatform().meendpropertyoutcome();
		deviceusage.getFeatureofinterest().getState().meendpropertyoutcome(sess, deviceusage.getFeatureofinterest());
		deviceusage.getFeatureofinterest().meendpropertyoutcome();

	}










	// ---------------- update and delete methods ------------------
	
	
	public void deleteDevice(java.lang.String id) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Device result = DeviceFactory.findByPrimaryKey(session, id);
			session.delete(result);
			session.beginTransaction().commit();
		} catch (Throwable th) {
			throw new Exception("deleteDevice: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("deleteDevice: " + he.getMessage());
				}
			}
		}
	}
	
	
	public void updateDevice(java.lang.String id, 
				java.lang.String Name) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Device result = DeviceFactory.findByPrimaryKey(session, id);
			result.setName(Name);
			session.save(result);
			session.beginTransaction().commit();
		} catch (Throwable th) {
			throw new Exception("updateDevice: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("updateDevice: " + he.getMessage());
				}
			}
		}
	}
	
	public void deleteOutcome(java.lang.String id) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Outcome result = OutcomeFactory.findByPrimaryKey(session, id);
			session.delete(result);
			session.beginTransaction().commit();
		} catch (Throwable th) {
			throw new Exception("deleteOutcome: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("deleteOutcome: " + he.getMessage());
				}
			}
		}
	}
	
	
	public void updateOutcome(java.lang.String id, 
				java.lang.String Name) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Outcome result = OutcomeFactory.findByPrimaryKey(session, id);
			result.setName(Name);
			session.save(result);
			session.beginTransaction().commit();
		} catch (Throwable th) {
			throw new Exception("updateOutcome: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("updateOutcome: " + he.getMessage());
				}
			}
		}
	}
	
	public void deleteDeviceresult(java.lang.String id) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Deviceresult result = DeviceresultFactory.findByPrimaryKey(session, id);
			session.delete(result);
			session.beginTransaction().commit();
		} catch (Throwable th) {
			throw new Exception("deleteDeviceresult: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("deleteDeviceresult: " + he.getMessage());
				}
			}
		}
	}
	
	
	public void updateDeviceresult(java.lang.String id, 
				 java.lang.String Time,
		java.lang.String Value) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Deviceresult result = DeviceresultFactory.findByPrimaryKey(session, id);
			result.setTime(Time);
			result.setValue(Value);
			session.save(result);
			session.beginTransaction().commit();
		} catch (Throwable th) {
			throw new Exception("updateDeviceresult: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("updateDeviceresult: " + he.getMessage());
				}
			}
		}
	}
	
	public void deleteFeatureofinterest(java.lang.String id) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Featureofinterest result = FeatureofinterestFactory.findByPrimaryKey(session, id);
			session.delete(result);
			session.beginTransaction().commit();
		} catch (Throwable th) {
			throw new Exception("deleteFeatureofinterest: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("deleteFeatureofinterest: " + he.getMessage());
				}
			}
		}
	}
	
	
	public void updateFeatureofinterest(java.lang.String id, 
				java.lang.String Name) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Featureofinterest result = FeatureofinterestFactory.findByPrimaryKey(session, id);
			result.setName(Name);
			session.save(result);
			session.beginTransaction().commit();
		} catch (Throwable th) {
			throw new Exception("updateFeatureofinterest: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("updateFeatureofinterest: " + he.getMessage());
				}
			}
		}
	}
	
	public void deletePlatform(java.lang.String id) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Platform result = PlatformFactory.findByPrimaryKey(session, id);
			session.delete(result);
			session.beginTransaction().commit();
		} catch (Throwable th) {
			throw new Exception("deletePlatform: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("deletePlatform: " + he.getMessage());
				}
			}
		}
	}
	
	
	public void updatePlatform(java.lang.String id, 
				java.lang.String Name) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Platform result = PlatformFactory.findByPrimaryKey(session, id);
			result.setName(Name);
			session.save(result);
			session.beginTransaction().commit();
		} catch (Throwable th) {
			throw new Exception("updatePlatform: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("updatePlatform: " + he.getMessage());
				}
			}
		}
	}
	
	public void deleteProperty(java.lang.String id) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Property result = PropertyFactory.findByPrimaryKey(session, id);
			session.delete(result);
			session.beginTransaction().commit();
		} catch (Throwable th) {
			throw new Exception("deleteProperty: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("deleteProperty: " + he.getMessage());
				}
			}
		}
	}
	
	
	public void updateProperty(java.lang.String id, 
				java.lang.String Name) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Property result = PropertyFactory.findByPrimaryKey(session, id);
			result.setName(Name);
			session.save(result);
			session.beginTransaction().commit();
		} catch (Throwable th) {
			throw new Exception("updateProperty: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("updateProperty: " + he.getMessage());
				}
			}
		}
	}
	
	public void deleteDeviceusage(java.lang.String id) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Deviceusage result = DeviceusageFactory.findByPrimaryKey(session, id);
			session.delete(result);
			session.beginTransaction().commit();
		} catch (Throwable th) {
			throw new Exception("deleteDeviceusage: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("deleteDeviceusage: " + he.getMessage());
				}
			}
		}
	}
	
	
	public void updateDeviceusage(java.lang.String id, 
				java.lang.String Name) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Deviceusage result = DeviceusageFactory.findByPrimaryKey(session, id);
			result.setName(Name);
			session.save(result);
			session.beginTransaction().commit();
		} catch (Throwable th) {
			throw new Exception("updateDeviceusage: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("updateDeviceusage: " + he.getMessage());
				}
			}
		}
	}
	
	public void deletePropertyoutcome(java.lang.String id) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Propertyoutcome result = PropertyoutcomeFactory.findByPrimaryKey(session, id);
			session.delete(result);
			session.beginTransaction().commit();
		} catch (Throwable th) {
			throw new Exception("deletePropertyoutcome: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("deletePropertyoutcome: " + he.getMessage());
				}
			}
		}
	}
	
	
	public void updatePropertyoutcome(java.lang.String id, 
				java.lang.String Name) throws Exception {
		Session session = null;
		try {
			session = getSession();
			Propertyoutcome result = PropertyoutcomeFactory.findByPrimaryKey(session, id);
			result.setName(Name);
			session.save(result);
			session.beginTransaction().commit();
		} catch (Throwable th) {
			throw new Exception("updatePropertyoutcome: " + th.toString());
		} finally {
			if (session != null) {
				try {
					//session.flush();
					session.close();
				} catch (HibernateException he) {
					throw new Exception("updatePropertyoutcome: " + he.getMessage());
				}
			}
		}
	}
	// ------------------------------------------------------------
	
	// ---------------- Hibernate helpers -------------------------

	private SessionFactory _sessionFactory = null;

	private SessionFactory getSessionFactory() throws HibernateException {
		if (_sessionFactory == null) {
			_sessionFactory = new Configuration().configure()
					.buildSessionFactory();
		}
		return _sessionFactory;
	}

	private Session getSession() throws HibernateException {
		return getSessionFactory().openSession();
	}

	// ------------------------------------------------------------

}

final class crResult {
	private final boolean noMultiplePropagationDetected;
    private final String id;

    public crResult(boolean nmpd, String id) {
        this.noMultiplePropagationDetected = nmpd;
        this.id = id;
    }

    public boolean getNoMultiplePropagationDetected() {
        return noMultiplePropagationDetected;
    }

    public String getID() {
        return id;
    }
}
