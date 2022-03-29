package zabbix.api.entry;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zabbix.api.domain.base.HostGroup;
import zabbix.api.domain.base.HostInterface;
import zabbix.api.domain.base.Template;
import zabbix.api.domain.host.HostCreateRequest;
import zabbix.api.domain.host.HostGetRequest;
import zabbix.api.domain.host.HostGetRequest.HostGetParams.HostFilter;
import zabbix.api.domain.host.HostUpdateRequest;
import zabbix.api.entity.hostinterface.Main;
import zabbix.api.entity.hostinterface.MonitorStauts;
import zabbix.api.entity.hostinterface.MonitorType;
import zabbix.api.entity.hostinterface.UseIp;
import zabbix.api.service.IHostService;
import zabbix.api.service.impl.HostServiceImpl;

public class HostUnit
{
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(HostUnit.class);
    
    private static String hostId = null;
    
    /**
     * host操作实例
     */
    private static IHostService hostService = new HostServiceImpl();
    
    /**
     * 添加host并关联模板
     * @param groupId       群组Id
     * @param templateId    模板Id
     * @return true:创建成功 false：创建失败
     */
    public String addHost(String groupId, String templateId, String jmxPort)
    {
        try
        {
            if (checkHostExist())
            {
                return updateHost(groupId, templateId);
            }
            else
            {
                return createHost(groupId, templateId, jmxPort);
            }
        }
        catch (Exception e)
        {
            LOG.info("add Host fail.", e);
        }
        return null;
    }
    
    /**
     * 创建host
     * @param groupId       群组Id
     * @param templateId    模板Id
     */
    private String createHost(String groupId, String templateId, String jmxPort)
    {
        HostCreateRequest create = new HostCreateRequest();
        create.getParams().setHost(getHostNameForLiunx());
        create.getParams().setName(getHostNameForLiunx());
        HostGroup hostGroup = new HostGroup();
        hostGroup.setGroupid(groupId);
        create.getParams().getGroups().add(hostGroup);
        List<Template> templates = new ArrayList<>();
        Template template = new Template();
        template.setTemplateid(templateId);
        templates.add(template);
        create.getParams().setTemplates(templates);
//        create.getParams().setTemplateid(Integer.parseInt(templateId));
        
        HostInterface hostJmxInterface = new HostInterface();
        hostJmxInterface.setType(MonitorType.JMX.value());
        hostJmxInterface.setIp(extractIpV4Addr());
        hostJmxInterface.setDns("dns.sinosun.com");
        hostJmxInterface.setUseip(UseIp.USE_HOST_IP_ADRESS.value());
        hostJmxInterface.setMain(Main.DEFAULT.value());
        hostJmxInterface.setPort(jmxPort);
        create.getParams().getInterfaces().add(hostJmxInterface);
        create.getParams().setStatus(MonitorStauts.MONITORED.value());
        JSONObject resp = (JSONObject)hostService.create(create);
        LOG.debug("create host get response:{}", resp);
        hostId = resp.getJSONArray("hostids").get(0).toString();
        return hostId;
    }
    
    /**
     * 获取可用的iPhoneV地址
     * @return  ipV4地址
     */
    private String extractIpV4Addr()
    {
        List<String> ipv4List = getLocalIpV4Adress();
        if (ipv4List.contains("127.0.0.1"))
        {
            ipv4List.remove("127.0.0.1");
        }
        return ipv4List.get(0);
    }
    
    /**
     * 
     * @param groupId
     * @param templateId
     */
    private String updateHost(String groupId, String templateId)
    {
        HostUpdateRequest update = new HostUpdateRequest();
        hostId = acquireHostId();
        update.getParams().setHostid(hostId);
        HostGroup hostGroup = new HostGroup();
        hostGroup.setGroupid(groupId);
        update.getParams().getGroups().add(hostGroup);
        update.getParams().setTemplateid(Integer.parseInt(templateId));
        
        Object resp = hostService.update(update);
        JSONObject result = new JSONObject(resp.toString());
        if (result.has("result"))
        {
            LOG.debug("update host success result:{}", result.get("result"));
            return hostId;
        }
        else
        {
            LOG.info("update host fail with error:{}", result.get("result"));
            return null;
        }
    }
    
    /**
     * 检查host是否存在
     * @return  true：存在 false：不存在
     */
    private boolean checkHostExist()
    {
        HostGetRequest get = new HostGetRequest();
        get.getParams().setOutput("hostid");
        HostFilter filter = new HostFilter();
        List<String> hostList = new ArrayList<>();
        hostList.add(getHostNameForLiunx());
        filter.setHost(hostList);
        get.getParams().setFilter(filter);
        try
        {
            JSONObject resp = (JSONObject)hostService.get(get);
            JSONArray array = resp.getJSONArray("result");
            array.getJSONObject(0).getString("hostid");
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
    /**
     * 从zabbix查询hostId
     * @return      hostId
     */
    private String acquireHostId()
    {
        HostGetRequest get = new HostGetRequest();
        get.getParams().setOutput("hostid");
        HostFilter filter = new HostFilter();
        List<String> hostList = new ArrayList<>();
        hostList.add(getHostNameForLiunx());
        filter.setHost(hostList);
        get.getParams().setFilter(filter);
        try
        {
            JSONObject resp = (JSONObject)hostService.get(get);
            JSONArray array = resp.getJSONArray("result");
            String hostId = array.getJSONObject(0).getString("hostid");
            return hostId;
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    /**
     * 获取本机hostname
     * @return hostName
     */
    private String getHostNameForLiunx()
    {
        try
        {
            return (InetAddress.getLocalHost()).getHostName();  
        }
        catch (UnknownHostException e)
        {  
            String host = e.getMessage();
            if (host != null)
            {
                int colon = host.indexOf(':');
                if (colon > 0)
                {
                    return host.substring(0, colon);
                }
            }
            return "UnknownHost";
        }
    }
    
    /**
     * 获取本机IPv4地址
     * @return      IPv4地址
     */
    private List<String> getLocalIpV4Adress()
    {
        List<String> adressList = new ArrayList<>();
        try
        {
            Enumeration<NetworkInterface> interfaces = null;
            interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements())
            {  
                NetworkInterface ni = interfaces.nextElement();
                Enumeration<InetAddress> addresss = ni.getInetAddresses();
                while (addresss.hasMoreElements())
                    {
                        InetAddress nextElement = addresss.nextElement();
                        LOG.debug("local IPv4 adress:{}", nextElement.getHostAddress());
                        if (nextElement instanceof Inet4Address)
                        {  
                            String hostAddress = nextElement.getHostAddress();
                            adressList.add(hostAddress);
                        }  
                        else if (nextElement instanceof Inet6Address)  
                        {  
                            LOG.debug("local IPv6 adress:{}", nextElement.getHostAddress());
                        }  
                    }
                }
        }
        catch (Exception e)
        {
            LOG.debug("get local ip adress catch exception:{}", e);
            return null;
        }
        return adressList;
    }

    public static String getHostId()
    {
        return hostId;
    }

    public static void setHostId(String hostId)
    {
        HostUnit.hostId = hostId;
    }
}
