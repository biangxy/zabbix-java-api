package zabbix.api.domain.base;

/**
 * @ClassName: HostInterface
 * @Description: 接口，存放设备的ip，端口，和dns的实体类
 * @author zhaohb
 */
public class HostInterface
{
    
    /**
     * 接口编号
     */
    private String interfaceid;
    /**
     * dns地址
     */
    private String dns;
    /**
     * 设备编号
     */
    private String hostid;
    /**
     * ip地址
     */
    private String ip;
    /**
     * 配置多个ip的先后顺序
     */
    private Integer main; 
    /**
     * 端口
     */
    private String port;
    /**
     * 监控方式的类型
     */
    private Integer type;
    /**
     * 是否使用ip
     */
    private Integer useip;
    
    public void setInterfaceid(String interfaceid)
    {
        this.interfaceid = interfaceid;
    }
    public String getInterfaceid()
    {
        return interfaceid;
    }
    public void setDns(String dns)
    {
        this.dns = dns;
    }
    public String getDns()
    {
        return dns;
    }
    public void setHostid(String hostid)
    {
        this.hostid = hostid;
    }
    public String getHostid()
    {
        return hostid;
    }
    public void setIp(String ip)
    {
        this.ip = ip;
    }
    public String getIp()
    {
        return ip;
    }
    public void setMain(Integer main)
    {
        this.main = main;
    }
    public Integer getMain()
    {
        return main;
    }
    public void setPort(String port)
    {
        this.port = port;
    }
    public String getPort()
    {
        return port;
    }
    public void setType(Integer type)
    {
        this.type = type;
    }
    public Integer getType()
    {
        return type;
    }
    public void setUseip(Integer useip)
    {
        this.useip = useip;
    }
    public Integer getUseip()
    {
        return useip;
    }
}
