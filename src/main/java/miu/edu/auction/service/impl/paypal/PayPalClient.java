package miu.edu.auction.service.impl.paypal;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

public class PayPalClient {
    /**
     * Setting up PayPal SDK environment with PayPal Access credentials. For demo
     * purpose, we are using SandboxEnvironment. In production this will be
     * LiveEnvironment.
     */
    private PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
            "ATvF82Mzj_F84wlt-OFe6SeO7nNdi44BMegOdDDdGX6qJ4fLIToHmcjkZ087l4Dta_b8_Pe82giWQh7Y", //PAYPAL_CLIENT_ID
            "EOhvdI5OUqtEqLhyyCSRGn5SKAA9X76o7bPvpEDl3wuS6xcc8ijYuOVV2OoVKmYMUqC7e0HEAdYgOEIU"); //PAYPAL_CLIENT_SECRET

    /**
     * PayPal HTTP client instance with environment which has access credentials
     * context. This can be used invoke PayPal API's provided the credentials have
     * the access to do so.
     */
    PayPalHttpClient client = new PayPalHttpClient(environment);

    /**
     * Method to get client object
     *
     * @return PayPalHttpClient client
     */
    public PayPalHttpClient client() {
        return this.client;
    }

    /**
     * Method to pretty print a response
     *
     * @param jo  JSONObject
     * @param pre prefix (default="")
     * @return String pretty printed JSON
     */
    public String prettyPrint(JSONObject jo, String pre) {
        Iterator<?> keys = jo.keys();
        StringBuilder pretty = new StringBuilder();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            pretty.append(String.format("%s%s: ", pre, StringUtils.capitalize(key)));
            if (jo.get(key) instanceof JSONObject) {
                pretty.append(prettyPrint(jo.getJSONObject(key), pre + "\t"));
            } else if (jo.get(key) instanceof JSONArray) {
                int sno = 1;
                for (Object jsonObject : jo.getJSONArray(key)) {
                    pretty.append(String.format("\n%s\t%d:\n", pre, sno++));
                    pretty.append(prettyPrint((JSONObject) jsonObject, pre + "\t\t"));
                }
            } else {
                pretty.append(String.format("%s\n", jo.getString(key)));
            }
        }
        return pretty.toString();
    }
}
