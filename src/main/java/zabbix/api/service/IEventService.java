package zabbix.api.service;
import java.util.List;

import zabbix.api.domain.base.Event;
import zabbix.api.domain.event.EventGetRequest;
/**
 * @ClassName: IEventService
 * @Description: event接口
 * @author zhaohb
 */
public interface IEventService
{
    /**
     * @Title: get
     * @Description: 获得event信息（json格式）
     * @param get
     * @return Object
     * @throws
     */
    public Object get(EventGetRequest get);

    /**
     * @Title: getEventToBean
     * @Description: 获取event的信息（bean）
     * @param get
     * @return List<Event>
     * @throws
     */
    public List<Event> getEventToBean(EventGetRequest get);
}
