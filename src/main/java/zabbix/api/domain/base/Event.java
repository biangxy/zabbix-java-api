package zabbix.api.domain.base;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Event
 * @Description: 事件的实体类
 * @author zhaohb
 */
public class Event
{
    /**
     * 事件编号
     */
    private String eventid;
    /**
     * 是否已确认
     */
    private Integer acknowledged;
    /**
     * 时间
     */
    private Long clock;
    /**
     * 事件创建的毫秒数
     */
    private Integer ns;
    /**
     * 0 - trigger;  1 - discovered host; 2 - discovered service;
     *  3 - auto-registered host
     */
    private Integer object;
    /**
     * 根据object的值对应的ID
     */
    private String objectid;
    /**
     * 事件类型  0 - event created by a trigger; 1 - event created by a discovery rule; 
     * 2 - event created by active agent auto-registration.
     */
    private Integer source;
    /**
     * 值
     */
    private Integer value;
    /**
     * 
     */
    private List<Host> hosts = new ArrayList<Host>();
    /**
     * 
     */
    private List<Trigger> triggers = new ArrayList<Trigger>();
    /**
     * 
     */
    private List<Item> items = new ArrayList<Item>();
    /**
     * 值的变化    2.2.0版本中该属性已删除
     */
//    private Integer value_changed;
    
    
    public void setEventid(String eventid)
    {
        this.eventid = eventid;
    }
    public List<Host> getHosts()
    {
        return hosts;
    }
    public void setHosts(List<Host> hosts)
    {
        this.hosts = hosts;
    }
    public List<Trigger> getTriggers()
    {
        return triggers;
    }
    public void setTriggers(List<Trigger> triggers)
    {
        this.triggers = triggers;
    }
    public List<Item> getItems()
    {
        return items;
    }
    public void setItems(List<Item> items)
    {
        this.items = items;
    }
    public String getEventid()
    {
        return eventid;
    }
    public void setAcknowledged(Integer acknowledged)
    {
        this.acknowledged = acknowledged;
    }
    public Integer getAcknowledged()
    {
        return acknowledged;
    }
    public void setClock(Long clock)
    {
        this.clock = clock;
    }
    public Long getClock()
    {
        return clock;
    }
    public void setNs(Integer ns)
    {
        this.ns = ns;
    }
    public Integer getNs()
    {
        return ns;
    }
    public void setObject(Integer object)
    {
        this.object = object;
    }
    public Integer getObject()
    {
        return object;
    }
    public void setObjectid(String objectid)
    {
        this.objectid = objectid;
    }
    public String getObjectid()
    {
        return objectid;
    }
    public void setSource(Integer source)
    {
        this.source = source;
    }
    public Integer getSource()
    {
        return source;
    }
    public void setValue(Integer value)
    {
        this.value = value;
    }
    public Integer getValue()
    {
        return value;
    }
//    public void setValue_changed(Integer value_changed)
//    {
//        this.value_changed = value_changed;
//    }
//    public Integer getValue_changed()
//    {
//        return value_changed;
//    }
}
