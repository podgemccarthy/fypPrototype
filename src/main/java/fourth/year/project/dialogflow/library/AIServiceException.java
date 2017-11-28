package fourth.year.project.dialogflow.library;

import fourth.year.project.dialogflow.library.model.AIResponse;
import fourth.year.project.dialogflow.library.util.StringUtils;


/**  no editing should be done in here         */
public class AIServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	
    private final AIResponse aiResponse;

    public AIServiceException() {
        aiResponse = null;
    }

    public AIServiceException(final String detailMessage, final Throwable throwable) {
        super(detailMessage, throwable);
        aiResponse = null;
    }

    public AIServiceException(final String detailMessage) {
        super(detailMessage);
        aiResponse = null;

    }

    public AIServiceException(final AIResponse aiResponse) {
        super();
        this.aiResponse = aiResponse;
    }

    public AIResponse getResponse() {
        return aiResponse;
    }

    @Override
    public String getMessage() {
        if ((aiResponse != null) && (aiResponse.getStatus() != null)) {

            final String errorDetails = aiResponse.getStatus().getErrorDetails();
            if (!StringUtils.isEmpty(errorDetails)) {
                return errorDetails;
            }
        }
        return super.getMessage();
    }
}
