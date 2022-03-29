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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Util
{
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(Util.class);
    
//    public static final String url = "http://192.168.3.211/allmoniapi/api_jsonrpc.php";
//    public static final String url = "http://192.168.153.163/zabbix/api_jsonrpc.php";
    public static final String url = "http://10.1.5.25/zabbix/api_jsonrpc.php";
    private static String loginName = "zhaohb";
    private static String password = "Biangxy";
    
    static
    {
        FormatData.API_URL = url;
    }
    
    /**
     * 构造函数
     * @param pwd           密码
     * @param loginName     用户名
     * @param url           zabbix URL
     */
    public Util(String loginName, String pwd, String url, String auth)
    {
        Util.password = pwd;
        Util.loginName = loginName;
        FormatData.API_URL = url;
        FormatData.auth = auth;
    }
    
    public Util()
    {
        
    }
    
    public Util(String concatUrl, String auth)
    {
        FormatData.API_URL = concatUrl;
        FormatData.auth = auth;
    }

    /**
     * 登录json
     * 
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
    public HttpClient login()
    {
        try
        {
            String jsonrpc = loginJson(loginName, password);
            JSONObject jsonObj = new JSONObject(jsonrpc);
            
            HttpPut post = new HttpPut(FormatData.API_URL);
            CloseableHttpClient client = HttpClients.createDefault();
            client = HttpClients.createDefault();
            post.setHeader("Content-Type", "application/json-rpc");
//            InputStreamEntity entity;
//            entity = new InputStreamEntity(FormatData.fromString((new Gson()).toJson(jsonrpc)));
            InputStreamEntity entity = new InputStreamEntity(FormatData.fromString(jsonObj.toString()));
            post.setEntity(entity);
            CloseableHttpResponse response = client.execute(post);
            JSONObject respObj = new JSONObject(EntityUtils.toString(response.getEntity()));
            String sessionId = "";

            if (respObj.has("result"))
            {
                sessionId = respObj.getString("result");
                
                FormatData.auth = sessionId;
                // init();

                // IUserGroupService ius = new UserGroupServiceImpl();
                // ius.getAllUserGroup(); 
                // IHostGroupService ihs = new HostGroupServiceImpl();
                // ihs.getAllHostGroup(); // 
                LOG.debug("response:{}, sessionId:{}", respObj, sessionId);
                
                return client;
            }
            else if (respObj.has("error"))
            {
                sessionId = respObj.getJSONObject("error").getString("data");
                throw new Exception(sessionId);
            }
        }
        catch (Exception e)
        {
            LOG.info("login catch exception:{}", e);
        }
        return null;
    }
    
    public void main(String[] args)
    {
        login();
    }
}
