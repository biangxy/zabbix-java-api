package zabbix.api.util;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class ZabbixLoginUtil
{
    public static String url = "";
    private static String loginName = "zhaohb";
    private static String password = "Biangxy11235813";

    /**
     * 登录json
     * @return
     */
    private static String loginJson(String loginName, String password)
    {
        JSONStringer js = new JSONStringer();
        try
        {
            js.object();
            js.key("jsonrpc").value("2.0");
            js.key("method").value("user.login");
            js.key("id").value(1);
            // js.key("auth").value(FormatData.auth);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user", loginName);
            jsonObject.put("password", password);
            js.key("params").value(jsonObject);

            js.endObject();
        }
        catch (JSONException e)
        {
            return null;
        }
        return js.toString();
    }

    public static HttpClient login()
    {
        try
        {
            HttpPut put = new HttpPut(FormatData.API_URL);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            put.setHeader("Content-Type", "application/json-rpc");
            String jsonrpc = loginJson(loginName, password);
            JSONObject jsonObj = new JSONObject(jsonrpc);
            InputStreamEntity entity = new InputStreamEntity(FormatData.fromString(jsonObj.toString()));
            put.setEntity(entity);
            CloseableHttpResponse response = httpClient.execute(put);
            JSONObject obj = new JSONObject(EntityUtils.toString(response.getEntity()));
            String sessionId = "";

            if (obj.has("result")) {
                sessionId = obj.getString("result");

                FormatData.auth = sessionId; //
                System.out.println(put);
                System.out.println(sessionId);
                return httpClient;
            } else if (obj.has("error")) {
                sessionId = obj.getJSONObject("error").getString("data");
                throw new Exception(sessionId);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args)
    {
         HttpClient client = login();
        String filePath = System.getProperty("user.dir");
        System.out.println(filePath);
    }
}
