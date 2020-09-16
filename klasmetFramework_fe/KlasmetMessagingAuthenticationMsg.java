package klasmetFramework_fe;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nami
 */
final class KlasmetMessagingAuthenticationMsg extends KlasmetMessagingMessenger {

    private static final String key = KlasmetMessagingKey.KEY_AUTHENTICATION_RESULT;
    private final boolean authenticated;

    protected KlasmetMessagingAuthenticationMsg(boolean authenticationResult) {
        this.authenticated = authenticationResult;
    }

    @SuppressWarnings("unchecked")
    protected static String toJsonString(KlasmetMessagingAuthenticationMsg klasmetAuthenticationMessage) {
        JSONObject JSONObjCommand = new JSONObject();
        JSONObjCommand.put(KlasmetMessagingKey.KEY, getKey());
        JSONObjCommand.put(KlasmetMessagingKey.AUTHENTICATION_RESULT, klasmetAuthenticationMessage.isAuthenticated());
        return JSONObjCommand.toJSONString();
    }

    protected static KlasmetMessagingAuthenticationMsg toObject(String JSONResponse) throws ParseException {
        JSONParser requestParser = new JSONParser();
        JSONObject requestParsedJSONObj = (JSONObject) requestParser.parse(JSONResponse);
        boolean authenticated = (Boolean) requestParsedJSONObj.get(KlasmetMessagingKey.AUTHENTICATION_RESULT);
        return (new KlasmetMessagingAuthenticationMsg(authenticated));
    }

    /**
     * @return the KEY
     */
    protected static String getKey() {
        return key;
    }

    /**
     * @return the authenticated
     */
    protected boolean isAuthenticated() {
        return authenticated;
    }
}
