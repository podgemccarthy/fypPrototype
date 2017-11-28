package fourth.year.project.dialogflow.library;

import java.util.TimeZone;
import java.util.UUID;

/**
 * Builds {@link AIServiceContext} to be used in {@link AIDataService} no editing should be done in here 
 */
public class AIServiceContextBuilder {

  private String sessionId;
  private TimeZone timeZone;

  /**
   * Default builder constructor. Does not initialise any building options.
   */
  public AIServiceContextBuilder() {}

  /**
   * @return Current sessionId value or <code>null</code> if value was not defined
   */
  public String getSessionId() {
    return sessionId;
  }

  /**
   * Replace current session id with given value
   * 
   * @param sessionId Unique string session id. Cannot be <code>null</code>
   * 
   * @throws IllegalArgumentException Thrown if sessionId parameter value is null
   */
  public AIServiceContextBuilder setSessionId(final String sessionId) {
    if (sessionId == null) {
      throw new IllegalArgumentException("sessionId cannot be null");
    }
    this.sessionId = sessionId;
    return this;
  }

  /**
   * Replace current session id with some new random value
   */
  public AIServiceContextBuilder generateSessionId() {
    this.sessionId = createRandomSessionId();
    return this;
  }
  

  /**
   * @return Current time zone value or <code>null</code> if value was not defined
   */
  public TimeZone getTimeZone() {
    return timeZone;
  }

  /**
   * Replace current time zone with given value
   * 
   * @param timeZone Time zone value. May be <code>null</code>
   */
  public AIServiceContextBuilder setTimeZone(final TimeZone timeZone) {
    this.timeZone = timeZone;
    return this;
  }

  /**
   * Use {@link AIServiceContextBuilder}.setTimeZone method insted
   */
  @Deprecated()
  public AIServiceContextBuilder setSessionId(final TimeZone timeZone) {
    this.timeZone = timeZone;
    return this;
  }

  /**
   * Build new context instance
   * 
   * @throws IllegalStateException Thrown if session id was not defined
   */
  public AIServiceContext build() {
    if (sessionId == null) {
      throw new IllegalStateException("Session id is undefined");
    }
    return new PlainAIServiceContext(sessionId, timeZone);
  }

  public static AIServiceContext buildFromSessionId(String sessionId) {
    return new AIServiceContextBuilder().setSessionId(sessionId).build();
  }

  private static String createRandomSessionId() {
    return UUID.randomUUID().toString();
  }

  private static class PlainAIServiceContext implements AIServiceContext {

    private final String sessionId;
    private final TimeZone timeZone;

    public PlainAIServiceContext(String sessionId, TimeZone timeZone) {
      assert sessionId != null;
      this.sessionId = sessionId;
      this.timeZone = timeZone;
    }

    @Override
    public String getSessionId() {
      return sessionId;
    }

    @Override
    public TimeZone getTimeZone() {
      return timeZone;
    }
  }
}