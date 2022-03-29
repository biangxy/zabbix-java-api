package zabbix.api.test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.json.JSONObject;

import zabbix.api.domain.base.HostGroup;
import zabbix.api.domain.base.HostInterface;
import zabbix.api.domain.base.Template;
import zabbix.api.domain.host.HostCreateRequest;
import zabbix.api.entity.hostinterface.Main;
import zabbix.api.entity.hostinterface.MonitorStauts;
import zabbix.api.entity.hostinterface.MonitorType;
import zabbix.api.entity.hostinterface.UseIp;
import zabbix.api.service.IHostService;
import zabbix.api.service.impl.HostServiceImpl;
import zabbix.api.util.Util;

public class HostTest {
    
    

    public static void main(String[] args)
    {
        String groupId = "47";
        String templateId = "10187";
        
        HttpClient client = new Util("zhaohb", "Biangxy11235813",
                "http://10.1.5.25/zabbix/api_jsonrpc.php", null).login();
        
        IHostService hostService = new HostServiceImpl();
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
        
//        HostInterface hostJmxInterface = new HostInterface();
//        hostJmxInterface.setType(MonitorType.JMX.value());
//        hostJmxInterface.setIp(getLocalIpAdress().get(0));
//        hostJmxInterface.setDns("www.baidu.com");
//        hostJmxInterface.setUseip(UseIp.USE_HOST_IP_ADRESS.value());
//        hostJmxInterface.setMain(Main.NOT_DEFAULT.value());
//        hostJmxInterface.setPort("1099");
//        create.getParams().getInterfaces().add(hostJmxInterface);
//        create.getParams().setStatus(MonitorStauts.UNMONITOR.value());
        
        HostInterface hostAgentInterface = new HostInterface();
        hostAgentInterface.setType(MonitorType.JMX.value());
        hostAgentInterface.setIp(getLocalIpAdress().get(0));
        hostAgentInterface.setDns("www.baidu.com");
        hostAgentInterface.setUseip(UseIp.USE_HOST_IP_ADRESS.value());
        hostAgentInterface.setMain(Main.DEFAULT.value());
        hostAgentInterface.setPort("1099");
        create.getParams().getInterfaces().add(hostAgentInterface);
        create.getParams().setStatus(MonitorStauts.UNMONITOR.value());
        Object resp = hostService.create(create);
        String li = "  ";
//        LOG.debug("create host get response:{}", resp);
        JSONObject result = new JSONObject(resp.toString());
//        return result.getString("hostids").toString();

    }
    
    /**
     * 获取本机hostname
     * @return hostName
     */
    private static String getHostNameForLiunx()
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
    
    private static List<String> getLocalIpAdress()
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
                        String hostAddress = nextElement.getHostAddress();
                        adressList.add(hostAddress);
                    }
                }
        }
        catch (Exception e)
        {
//            LOG.debug("get local ip adress catch exception:{}", e);
            return null;
        }
        return adressList;
    }

}
