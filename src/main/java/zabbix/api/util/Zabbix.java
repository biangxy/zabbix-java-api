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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * zabbix登录操作类
 * @author zhaohb
 *
 */
public class Zabbix
{
    /**
     * 登录用户名
     */
    private String username;
    /**
     * 登录密码
     */
    private String passowrd;
    
    /**     module
    public static void main(String[] args)
    {
        String auth = new Zabbix().login("10.1.5.25", "80", "zhaohb", "Biangxy11235813", true);
                

    }
    **/
    
    /**
     * 构造函数
     */
    public Zabbix()
    {
        
    }

    /**
     * 构建登录请求JSON字符串
     * @param username      用户名
     * @param password      密码
     * @return  JSONObject
     */
    private JSONObject buildLoginJson(String username, String password)
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
    
    /**
     * zabbix登录
     * @param zbxIp         zabbix服务器地址
     * @param zbxPort       服务器端口
     * @param username      用户名
     * @param password      密码
     * @return  auth信息
     */
    public String login(String zbxIp, String zbxPort, String username, String password, boolean enable)
    {
        if (enable)
        {
            try
            {
                JSONObject request = buildLoginJson(username, password);
                
                HttpPut post = new HttpPut(constructionUrl(zbxIp, zbxPort));
                CloseableHttpClient client = HttpClients.createDefault();
                client = HttpClients.createDefault();
                post.setHeader("Content-Type", "application/json-rpc");
                InputStreamEntity entity;
                entity = new InputStreamEntity(fromString(request.toJSONString()));
                post.setEntity(entity);
                CloseableHttpResponse response = client.execute(post);
                JSONObject respObj = JSON.parseObject(EntityUtils.toString(response.getEntity()));
                return respObj.getString("result").toString();
            }
            catch (Exception e)
            {
                // TODO 添加异常处理或者抛出异常
                e.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * 转义编码
     * @param format            待转义字符串
     * @return      inputStream
     * @throws UnsupportedEncodingException     异常
     */
    private InputStream fromString(String format) throws UnsupportedEncodingException
    {
        byte[] bytes = format.getBytes("UTF-8");
        return new ByteArrayInputStream(bytes);
    }

    /**
     * 拼接zabbix服务器URL
     * @param zbxIp     zabbix地址
     * @param zbxPort   zabbix端口
     * @return  url
     */
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
