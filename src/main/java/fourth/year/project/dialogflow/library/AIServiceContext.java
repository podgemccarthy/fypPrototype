package fourth.year.project.dialogflow.library;

import java.util.TimeZone;

/**
 * Contains environment specific information required for service request  no editing should be done in here 
 */
public interface AIServiceContext {
	
	/**
	 * @return Session unique string value.  Never <code>null</code>
	 */
	String getSessionId();
	
	/**
	 * @return Client's time zone. If <code>null</code> then system default time zone
	 * will be assumed.
	 */
	TimeZone getTimeZone();
}