package zabbix.api.domain.hostgroup;
import java.util.*;

import zabbix.api.domain.base.Host;
import zabbix.api.domain.base.HostGroup;
import zabbix.api.domain.base.RequestBase;
import zabbix.api.domain.base.Template;
public class HostGroupMassupdateRequest extends RequestBase
{
    public HostGroupMassupdateRequest()
    {
        super("hostgroup.massupdate");
    }
    private HostGroupMassupdateParams params = new HostGroupMassupdateParams();
    public void setParams(HostGroupMassupdateParams params)
    {
        this.params = params;
    }
    public HostGroupMassupdateParams getParams()
    {
        return params;
    }
    public static class HostGroupMassupdateParams extends HostGroup
    {
        private List<HostGroup> groups;
        private List<Host> hosts;
        private List<Template> templates;
        public void setGroups(List<HostGroup> groups)
        {
            this.groups = groups;
        }
        public List<HostGroup> getGroups()
        {
             if (groups == null)
             {
                groups = new ArrayList<HostGroup>();
                return groups;
             }
             return groups;
        }
        public void setHosts(List<Host> hosts)
        {
            this.hosts = hosts;
        }
        public List<Host> getHosts()
        {
             if (hosts == null)
             {
                hosts = new ArrayList<Host>();
                return hosts;
             }
             return hosts;
        }
        public void setTemplates(List<Template> templates)
        {
            this.templates = templates;
        }
        public List<Template> getTemplates()
        {
             if (templates == null)
             {
                templates = new ArrayList<Template>();
                return templates;
             }
             return templates;
        }
    }
}
