package zabbix.api.entry;

import java.util.ArrayList;
import java.util.List;

import zabbix.api.domain.base.HostInterface;
import zabbix.api.domain.hostinterface.HostInterfaceGetRequest;
import zabbix.api.entity.hostinterface.MonitorType;
import zabbix.api.service.IHostinterfaceService;
import zabbix.api.service.impl.HostinterfaceServiceImpl;

/**
 * hostInterface查询接口
 * @author zhaohb
 *
 */
public class InterfaceUnit
{
    private static IHostinterfaceService hostinterfaceService = new HostinterfaceServiceImpl();
    
    private static String mJmxInterfaceId = null;
    
    public String queryJmxInterface(String hostId)
    {
        HostInterfaceGetRequest get = new HostInterfaceGetRequest();
        List<String> hosts = new ArrayList<>();
        get.getParams().setOutput("extend");
        hosts.add(hostId);
        get.getParams().setHostids(hosts);
        List<HostInterface> resp = hostinterfaceService.getHostInterfaceBean(get);
        for (HostInterface inter : resp)
        {
            if (MonitorType.JMX.value() == inter.getType())
            {
                mJmxInterfaceId = inter.getInterfaceid();
                return mJmxInterfaceId;
            }
        }
        return null;
    }

    public static String getJmxInterfaceId()
    {
        return mJmxInterfaceId;
    }

    public static void setJmxInterfaceId(String jmxInterfaceId)
    {
        InterfaceUnit.mJmxInterfaceId = jmxInterfaceId;
    }
    
}
