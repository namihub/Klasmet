package klasmetFramework_be;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nami
 */
public final class KlasmetMessagingAuthenticationMsg extends KlasmetMessagingMessenger {

    private static final String key = KlasmetMessagingKey.KEY_AUTHENTICATION_RESULT;
    private final boolean authenticated;

    public KlasmetMessagingAuthenticationMsg(boolean authenticationResult) {
        this.authenticated = authenticationResult;
    }

    @SuppressWarnings("unchecked")
    public static String toJsonString(KlasmetMessagingAuthenticationMsg klasmetAuthenticationMessage) {
        JSONObject JSONObjCommand = new JSONObject();
        JSONObjCommand.put(KlasmetMessagingKey.KEY, getKey());
        JSONObjCommand.put(KlasmetMessagingKey.AUTHENTICATION_RESULT, klasmetAuthenticationMessage.isAuthenticated());
        return JSONObjCommand.toJSONString();
    }

    public static KlasmetMessagingAuthenticationMsg toObject(String JSONResponse) throws ParseException {
        JSONParser requestParser = new JSONParser();
        JSONObject requestParsedJSONObj = (JSONObject) requestParser.parse(JSONResponse);
        boolean authenticated = (Boolean) requestParsedJSONObj.get(KlasmetMessagingKey.AUTHENTICATION_RESULT);
        return (new KlasmetMessagingAuthenticationMsg(authenticated));
    }

    /**
     * @return the KEY
     */
    public static String getKey() {
        return key;
    }

    /**
     * @return the authenticated
     */
    public boolean isAuthenticated() {
        return authenticated;
    }
}
