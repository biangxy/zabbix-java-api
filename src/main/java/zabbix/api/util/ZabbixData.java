package zabbix.api.util;

/**
 * Created with Eclipse.
 * @author Zhaohb
 */

public class ZabbixData
{

    /**
     * 本机Hostname
     */
    public String host;
    
    /**
     * KPI指标
     */
    public String item;
    
    /**
     * KPI值
     */
    public String value;

    /**
     * 获取Hostname
     * @return
     */
    public String getHost()
    {
        return host;
    }

    /**
     * 设置Hostname
     * @param host
     */
    public void setHost(String host)
    {
        this.host = host;
    }

    /**
     * 获取KPI指标
     * @return
     */
    public String getItem()
    {
        return item;
    }

    /**
     * 设置KPI指标
     * @param item
     */
    public void setItem(String item)
    {
        this.item = item;
    }

    /**
     * 获取KPI值
     * @return
     */
    public String getValue()
    {
        return value;
    }

    /**
     * 设置KPI值
     * @param value
     */
    public void setValue(String value)
    {
        this.value = value;
    }  
}