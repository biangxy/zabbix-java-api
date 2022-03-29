package zabbix.api.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import zabbix.api.domain.base.RequestBase;

public class HttpExecuter
{
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(HttpExecuter.class);

    private static CloseableHttpClient client = HttpClients.createDefault();

    /**
     * Http POST请求
     * @param request   requestBase请求
     * @return          JSON串
     */
    public static JSONObject post(RequestBase request)
    {
        try
        {
            Gson js = new Gson();
            LOG.debug("{} request:{}", request.getMethod(), js.toJson(request));
            
            HttpPost post = new HttpPost(FormatData.API_URL);
//            client = HttpClients.createDefault();
            post.setHeader("Content-Type", "application/json-rpc");
            InputStreamEntity entity = new InputStreamEntity(FormatData.fromString((new Gson()).toJson(request)));
            post.setEntity(entity);
            CloseableHttpResponse response = client.execute(post);
            JSONObject respObj = new JSONObject(EntityUtils.toString(response.getEntity()));
            return respObj;
        }
        catch (Exception e)
        {
            LOG.info(request.getMethod() + " POST request catch exception:{}", e);
        }
        return null;
    }

    /**
     * Http GET请求
     * @param request   requestBase请求
     * @return          JSON串
     */
    public static JSONObject get(RequestBase request)
    {
        try
        {
            Gson js = new Gson();
            LOG.debug("{} request:{}", request.getMethod(), js.toJson(request));
            
            HttpGet get = new HttpGet(FormatData.API_URL);
            client = HttpClients.createDefault();
            get.setHeader("Content-Type", "application/json-rpc");
            CloseableHttpResponse response = client.execute(get);
            JSONObject respObj = new JSONObject(EntityUtils.toString(response.getEntity()));
            return respObj;
        }
        catch (Exception e)
        {
            LOG.info(request.getMethod() + " GET request catch exception:{}", e);
        }
        return null;
    }

    /**
     * Http PUT请求
     * @param request   requestBase请求
     * @return          JSON串
     */
    public static JSONObject put(RequestBase request)
    {
        try
        {
            Gson js = new Gson();
            LOG.debug("{} request:{}", request.getMethod(), js.toJson(request));
            
            HttpPut put = new HttpPut(FormatData.API_URL);
            client = HttpClients.createDefault();
            put.setHeader("Content-Type", "application/json-rpc");
            InputStreamEntity entity;
            entity = new InputStreamEntity(
                    FormatData.fromString((new Gson()).toJson(request)));
            put.setEntity(entity);
            CloseableHttpResponse response = client.execute(put);
            JSONObject respObj = new JSONObject(EntityUtils.toString(response.getEntity()));
            return respObj;
        }
        catch (Exception e)
        {
            LOG.info(request.getMethod() + " PUT request catch exception:{}", e);
        }
        return null;
    }

    /**
     * Http DELETE请求
     * @param request   requestBase请求
     * @return          JSON串
     */
    public static JSONObject delete(RequestBase request)
    {
        try
        {
            Gson js = new Gson();
            LOG.debug("{} request:{}", request.getMethod(), js.toJson(request));
            
            HttpDelete delete = new HttpDelete(FormatData.API_URL);
            client = HttpClients.createDefault();
            delete.setHeader("Content-Type", "application/json-rpc");
            CloseableHttpResponse response = client.execute(delete);
            JSONObject respObj = new JSONObject(EntityUtils.toString(response.getEntity()));
            return respObj;
        }
        catch (Exception e)
        {
            LOG.info(request.getMethod() + " DELETE request catch exception:{}", e);
        }
        return null;
    }

}
