package zabbix.api.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * Zabbix登录请求
 * @author zhaohb
 *
 */
public class LoginRequest
{
    private String username;
    
    private String passowrd;
    
    public LoginRequest(String username, String password)
    {
        this.username = username;
        this.passowrd = password;
    }
    
    private JSONObject LoginRequest(String username, String password)
    {
        JSONObject request = new JSONObject();
        request.put("jsonrpc", "2.0");
        request.put("method", "user.login");
        request.put("id", "1");
        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", username);
        jsonObject.put("password", password);
        request.put("params", jsonObject);
        return request;
    }
    
    public String login(String zbxIp, String zbxPort, String username, String password)
    {
        try {
            JSONObject request = LoginRequest(username, password);
            
            HttpPut post = new HttpPut(constructionUrl(zbxIp, zbxPort));
            CloseableHttpClient client = HttpClients.createDefault();
            client = HttpClients.createDefault();
            post.setHeader("Content-Type", "application/json-rpc");
            InputStreamEntity entity;
            entity = new InputStreamEntity(fromString(request.toJSONString()));
            post.setEntity(entity);
            CloseableHttpResponse response = client.execute(post);
            String sessionId = "";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    private InputStream fromString(String str) throws UnsupportedEncodingException
    {
        byte[] bytes = str.getBytes("UTF-8");
        return new ByteArrayInputStream(bytes);
    }

    private String constructionUrl(String zbxIp, String zbxPort)
    {
        return "http://" + zbxIp + ":" + zbxPort + "/zabbix/api_jsonrpc.php";
    }
    
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassowrd()
    {
        return passowrd;
    }

    public void setPassowrd(String passowrd)
    {
        this.passowrd = passowrd;
    }
}
