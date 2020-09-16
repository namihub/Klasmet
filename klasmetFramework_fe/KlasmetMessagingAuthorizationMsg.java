package klasmetFramework_fe;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nami
 */
final class KlasmetMessagingAuthorizationMsg extends KlasmetMessagingMessenger {

    private static final String key = KlasmetMessagingKey.KEY_AUTHORIZATION_RESULT;
    private boolean authorized;

    protected KlasmetMessagingAuthorizationMsg(boolean authorizationResult) {
        this.authorized = authorizationResult;
    }

    @SuppressWarnings("unchecked")
    protected static String toJsonString(KlasmetMessagingAuthorizationMsg klasmetAuthorizationMessage) {
        JSONObject JSONObjCommand = new JSONObject();
        JSONObjCommand.put(KlasmetMessagingKey.KEY, getKey());
        JSONObjCommand.put(KlasmetMessagingKey.AUTHORIZATION_RESULT, klasmetAuthorizationMessage.isAuthorized());
        return JSONObjCommand.toJSONString();
    }

    protected static KlasmetMessagingAuthorizationMsg toObject(String JSONResponse) throws ParseException {
        JSONParser requestParser = new JSONParser();
        JSONObject requestParsedJSONObj = (JSONObject) requestParser.parse(JSONResponse);
        boolean authorized = (Boolean) requestParsedJSONObj.get(KlasmetMessagingKey.AUTHORIZATION_RESULT);
        return (new KlasmetMessagingAuthorizationMsg(authorized));
    }

    /**
     * @return the KEY
     */
    protected static String getKey() {
        return key;
    }

    /**
     * @return the authorized
     */
    protected boolean isAuthorized() {
        return authorized;
    }

    /**
     * @param authorized the authorized to set
     */
    protected void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    

}
