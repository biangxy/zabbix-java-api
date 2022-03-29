package zabbix.api.entry;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ZabbixHelper
{
    public static String getHostNameForLiunx()
    {
        try
        {
            return (InetAddress.getLocalHost()).getHostName();
        }
        catch (UnknownHostException uhe)
        {
            String host = uhe.getMessage(); // host = "hostname: hostname"  
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
}
