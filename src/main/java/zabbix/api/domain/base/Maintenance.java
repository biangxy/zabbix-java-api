package zabbix.api.domain.base;

/**
 * @ClassName: Maintenance
 * @Description: 维护
 * @author zhaohb
 */
public class Maintenance
{
    /**
     * 维护编号
     */
    private String maintenanceid;
    
    /**
     * 维护名称
     */
    private String name;
    
    /**
     * 维护开始时间（Default: current time）
     */
    private String active_since;
    
    /**
     * 维护停止时间（Default: the next day）
     */
    private String active_till;
    
    /**
     * 描述
     */
    private String description;
    
    /**
     * 维护类型
     * （Possible values:0 - (default) with data collection;
     * 1 - without data collection. ）
     */
    private Integer maintenance_type;
    
    public void setMaintenanceid(String maintenanceid)
    {
        this.maintenanceid = maintenanceid;
    }
    public String getMaintenanceid()
    {
        return maintenanceid;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    public void setActive_since(String active_since)
    {
        this.active_since = active_since;
    }
    public String getActive_since()
    {
        return active_since;
    }
    public void setActive_till(String active_till)
    {
        this.active_till = active_till;
    }
    public String getActive_till()
    {
        return active_till;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getDescription()
    {
        return description;
    }
    public void setMaintenance_type(Integer maintenance_type)
    {
        this.maintenance_type = maintenance_type;
    }
    public Integer getMaintenance_type()
    {
        return maintenance_type;
    }
}
