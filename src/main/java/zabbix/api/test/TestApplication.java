package zabbix.api.test;

import java.util.List;

import zabbix.api.domain.application.ApplicationGetRequest;
import zabbix.api.domain.base.Application;
import zabbix.api.service.IApplicationService;
import zabbix.api.service.impl.ApplicationServiceImpl;
import zabbix.api.util.Util;

public class TestApplication
{
    private static IApplicationService applicationService = new ApplicationServiceImpl();
    
    static
    {
        // 登录
        new Util().login();
    }
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        // JSONObject result = (JSONObject) testGet();
        System.out.println(testGettoBean());
    }

    public static Object testGet()
    {
        ApplicationGetRequest get = new ApplicationGetRequest();
        get.getParams().setOutput("extend");
        get.getParams().getHostids().add("10715");
        get.getParams().getFilter().getName().add("CPU");
        get.getParams().setSelectItems("extend");
        return applicationService.get(get);
    }

    public static List<Application> testGettoBean()
    {
        ApplicationGetRequest get = new ApplicationGetRequest();
        get.getParams().setOutput("extend");
        get.getParams().getHostids().add("10715");
        get.getParams().getFilter().getName().add("CPU");
        get.getParams().setSelectItems("extend");
        return applicationService.getApplicationToBean(get);
    }

}
