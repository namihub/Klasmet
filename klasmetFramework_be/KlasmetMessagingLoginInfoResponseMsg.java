package klasmetFramework_be;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nami
 */
public final class KlasmetMessagingLoginInfoResponseMsg extends KlasmetMessagingMessenger {

    private static final String key = KlasmetMessagingKey.KEY_LOGIN_INFO_RESPONSE;
    private String username;
    private String password;

    public KlasmetMessagingLoginInfoResponseMsg(String username, String password) {
        if (username == null) {
            this.username = "";
        } else {
            this.username = username;
        }

        if (password == null) {
            this.password = "";
        } else {
            this.password = password;
        }
    }

    @SuppressWarnings("unchecked")
    public static String toJsonString(KlasmetMessagingLoginInfoResponseMsg klasmetLoginInfoResponseMessage) {
        JSONObject JSONObjCommand = new JSONObject();
        JSONObjCommand.put(KlasmetMessagingKey.KEY, getKey());
        JSONObjCommand.put(KlasmetMessagingKey.LOGIN_USERNAME, klasmetLoginInfoResponseMessage.getUsername());
        JSONObjCommand.put(KlasmetMessagingKey.LOGIN_PASSWORD, klasmetLoginInfoResponseMessage.getPassword());
        return JSONObjCommand.toJSONString();
    }

    public static KlasmetMessagingLoginInfoResponseMsg toObject(String JSONResponse) throws ParseException {
        JSONParser requestParser = new JSONParser();
        JSONObject requestParsedJSONObj = (JSONObject) requestParser.parse(JSONResponse);
        String username = requestParsedJSONObj.get(KlasmetMessagingKey.LOGIN_USERNAME).toString();
        String password = requestParsedJSONObj.get(KlasmetMessagingKey.LOGIN_PASSWORD).toString();
        return (new KlasmetMessagingLoginInfoResponseMsg(username, password));
    }

    /**
     * @return the KEY
     */
    public static String getKey() {
        return key;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
