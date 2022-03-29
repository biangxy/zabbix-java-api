package zabbix.api.service;

import java.util.List;

import zabbix.api.domain.application.ApplicationCreateRequest;
import zabbix.api.domain.application.ApplicationGetRequest;
import zabbix.api.domain.base.Application;

public interface IApplicationService 
{

    public Object get(ApplicationGetRequest get);
    
    public Object create(ApplicationCreateRequest create);

    List<Application> getApplicationToBean(ApplicationGetRequest get);
}
