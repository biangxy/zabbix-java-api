package zabbix.api.entry;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import zabbix.api.domain.application.ApplicationCreateRequest;
import zabbix.api.domain.application.ApplicationGetRequest;
import zabbix.api.domain.base.Application;
import zabbix.api.service.IApplicationService;
import zabbix.api.service.impl.ApplicationServiceImpl;

public class ApplicationUnit
{
    private static String applicationId = null;
    
    private final String applicationTail = "性能监控";
    
    private static IApplicationService applicationService = new ApplicationServiceImpl();
    
    public String buildApplication(String templateId, String name, String hostId)
    {
        if (CheckApplicationExist(hostId, name))
        {
            return getchApplicationId(hostId, name);
        }
        else
        {
            return createApplication(templateId, name, hostId);
        }
    }
    
    private String createApplication(String templateId, String name, String hostId)
    {
        try
        {
            IApplicationService applicationService = new ApplicationServiceImpl();
            ApplicationCreateRequest request = new ApplicationCreateRequest();
            request.getParams().setName(new String((name + applicationTail).getBytes("UTF-8")));
            request.getParams().setHostid(templateId);
//          request.getParams().getTemplateids().add(templateId);
            JSONObject resp = (JSONObject)applicationService.create(request);
            applicationId = resp.getJSONArray("applicationids").get(0).toString();
        }
        catch (Exception e)
        {
            return null;
        }
        return applicationId;
    }
    
    private String getchApplicationId(String hostId, String name)
    {
        ApplicationGetRequest get = new ApplicationGetRequest();
        get.getParams().setOutput("extend");
        get.getParams().getHostids().add(hostId);
        get.getParams().getFilter().getName().add(name  + applicationTail);
        get.getParams().setSelectItems("extend");
        try
        {
            List<Application> applications = applicationService.getApplicationToBean(get);
            applicationId = applications.get(0).getApplicationid();
            return applicationId;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    private boolean CheckApplicationExist(String hostId, String name)
    {
        ApplicationGetRequest get = new ApplicationGetRequest();
        get.getParams().setOutput("extend");
        get.getParams().getHostids().add(hostId);
        get.getParams().getFilter().getName().add(name.toUpperCase()  + applicationTail);
        get.getParams().setSelectItems("extend");
        JSONObject resp = (JSONObject)applicationService.get(get);
        JSONArray arr = new JSONArray(resp.get("result").toString());
        return arr.length() > 0 ? true : false;
    }

    public static String getApplicationId()
    {
        return applicationId;
    }

    public static void setApplicationId(String applicationId)
    {
        ApplicationUnit.applicationId = applicationId;
    }
}
