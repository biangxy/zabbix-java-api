package zabbix.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zabbix.api.domain.application.ApplicationCreateRequest;
import zabbix.api.domain.application.ApplicationGetRequest;
import zabbix.api.domain.base.Application;
import zabbix.api.domain.base.Host;
import zabbix.api.domain.base.Item;
import zabbix.api.service.IApplicationService;
import zabbix.api.util.HttpExecuter;

public class ApplicationServiceImpl implements IApplicationService
{
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationServiceImpl.class);
    @Override
    public Object get(ApplicationGetRequest get)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(get);
            LOG.debug("get application get response:{}", respObj);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return respObj;
    }

    @Override
    public List<Application> getApplicationToBean(ApplicationGetRequest get)
    {
        List<Application> applications = null;
        JSONObject result = (JSONObject) this.get(get);
        if (result.has("result"))
        {
            applications = new ArrayList<Application>();
            try
            {
                JSONArray array = result.getJSONArray("result");
                for (int i = 0; i < array.length(); i++)
                {
                    JSONObject applicationObject = array.getJSONObject(i);
                    Application application = new Application();
                    application.setApplicationid(applicationObject.getString("applicationid"));
                    application.setHostid(applicationObject.getString("hostid"));
                    application.setName(applicationObject.getString("name"));
                    JSONArray templateArray = applicationObject.getJSONArray("templateids");
                    List<String> templateids = new ArrayList<String>();
                    if (templateArray.length() > 0)
                    {
                        for (int j = 0; j < templateArray.length(); j++)
                        {
                            templateids.add(templateArray.getString(j));
                        }
                    }

                    // event.setValue_changed(eventobject.getInt("value_changed"));
                    if (applicationObject.has("hosts"))
                    {
                        JSONArray hostsarray = applicationObject.getJSONArray("hosts");
                        if ((hostsarray != null) && (hostsarray.length() > 0))
                        {
                            for (int j = 0; j < hostsarray.length(); j++)
                            {
                                JSONObject hostObject = hostsarray.getJSONObject(j);
                                Host host = new Host();
                                host.setHostid(hostObject.getString("hostid"));
                                if ("extend".equals(get.getParams().getSelectHosts()))
                                {
                                    host.setHost(hostObject.getString("host"));
                                    host.setName(hostObject.getString("name"));
                                    host.setStatus(hostObject.getInt("status"));
                                    host.setAvailable(hostObject.getInt("available"));
                                }
                                application.getHosts().add(host);
                            }
                        }
                    }
                    if (applicationObject.has("items"))
                    {
                        JSONArray itemarray = applicationObject.getJSONArray("items");
                        for (int k = 0; k < itemarray.length(); k++)
                        {
                            JSONObject itemObject = itemarray.getJSONObject(k);
                            Item item = new Item();
                            item.setItemid(itemObject.getString("itemid"));
                            item.setLastns(itemObject.getInt("lastns"));
                            item.setLastvalue(itemObject.getString("lastvalue"));
                            if (itemObject.getString("name").contains("$"))
                            {
                                item.setName(replaceStr(itemObject.getString("name"), itemObject.getString("key_")));
                            }
                            else
                            {
                                item.setName(itemObject.getString("name"));
                            }
                            application.getItems().add(item);
                        }
                    }
                    applications.add(application);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                return applications;
            }
        }
        else if (result.has("error"))
        {
            return applications;
        }
        return applications;
    }

    private String replaceStr(String str, String key)
    {
        try
        {
            String[] infoStr = null;
            if (key.contains("["))
            { // 包含[]，说明有$符号产生
                infoStr = key.substring(key.indexOf("[") + 1, key.lastIndexOf("]")).split(",");
            }
            for (int i = 1; i <= infoStr.length; i++)
            {
                str = str.replace("$" + i, infoStr[i - 1]);
            }
        }
        catch (Exception e)
        {
            LOG.info("application replace String catch exception:{}", e);
        }
        return str;
    }

    @Override
    public Object create(ApplicationCreateRequest create)
    {
        JSONObject result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(create);
            LOG.debug("get application get response:{}", respObj);
            if (respObj.has("result"))
            {
                result = respObj.getJSONObject("result");
            }
            else
            {
                result = respObj.getJSONObject("error");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
