package zabbix.api.domain.application;

import java.util.ArrayList;
import java.util.List;

import zabbix.api.domain.base.RequestBase;

/**
 * 创建application
 * @author zhaohb
 *
 */
public class ApplicationCreateRequest extends RequestBase
{
    public ApplicationCreateRequest()
    {
        super("application.create");
    }
    private ApplicationCreateParams params = new ApplicationCreateParams();
    public void setParams(ApplicationCreateParams params)
    {
        this.params = params;
    }
    public ApplicationCreateParams getParams()
    {
        return params;
    }
    
    public static class ApplicationCreateParams
    {
        private String name;
        private String hostid;
        private List<String> templateids;
        private String applicationid;
        public String getName()
        {
            return name;
        }
        public void setName(String name)
        {
            this.name = name;
        }
        public String getHostid()
        {
            return hostid;
        }
        public void setHostid(String hostid)
        {
            this.hostid = hostid;
        }
        public List<String> getTemplateids()
        {
            if (templateids == null)
            {
                templateids = new ArrayList<>();
            }
            return templateids;
        }
        public void setTemplateids(List<String> templateids)
        {
            this.templateids = templateids;
        }
        public String getApplicationid()
        {
            return applicationid;
        }
        public void setApplicationid(String applicationid)
        {
            this.applicationid = applicationid;
        }
        
    }
}
