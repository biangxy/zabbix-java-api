package zabbix.api.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import zabbix.api.domain.base.Event;
import zabbix.api.domain.base.Host;
import zabbix.api.domain.base.Item;
import zabbix.api.domain.base.Trigger;
import zabbix.api.domain.event.EventGetRequest;
import zabbix.api.service.IEventService;
import zabbix.api.util.HttpExecuter;
/**
 * @ClassName: EventServiceImpl
 * @Description:事件实现类
 * @author zhaohb
 */
public class EventServiceImpl implements IEventService
{
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(EventServiceImpl.class);
    /*
     * Title: get
     * Description: 获取event信息的json字符串
     * @param get
     * @return Object
     * @see zabbix.api.service.IEventService#get(zabbix.api.domain.event.EventGetRequest)
     */
    @Override
    public Object get(EventGetRequest get)
    {
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(get);
            LOG.debug("get event service response:{}", respObj);
            return respObj;
        }
         catch (Exception e)
        {
            LOG.info("event service catch exception:{}", e);
            return null;
        }
    }
    
    /*
     * Title: getEventToBean
     * Description: 获取event信息（bean）
     * @param get
     * @return List<Event>
     * @see zabbix.api.service.IEventService#getEventToBean(zabbix.api.domain.event.EventGetRequest)
     */
    @Override
    public List<Event> getEventToBean(EventGetRequest get)
    {
        List<Event> events = null;
        JSONObject result = (JSONObject) this.get(get);
        if (result.has("result"))
        {
            events = new ArrayList<Event>();
            try
            {
                JSONArray array = result.getJSONArray("result");
                for(int i =0;i<array.length();i++){
                    JSONObject eventobject = array.getJSONObject(i);
                    Event event = new Event();
                    event.setAcknowledged(eventobject.getInt("acknowledged"));
                    event.setClock(eventobject.getLong("clock"));
                    event.setEventid(eventobject.getString("eventid"));
                    event.setNs(eventobject.getInt("ns"));
                    event.setObject(eventobject.getInt("object"));
                    event.setObjectid(eventobject.getString("objectid"));
                    event.setSource(eventobject.getInt("source"));
                    event.setValue(eventobject.getInt("value"));
//                    event.setValue_changed(eventobject.getInt("value_changed"));
                    if (eventobject.has("hosts"))
                    {
                        JSONArray hostsarray = eventobject.getJSONArray("hosts");
                        if((hostsarray!=null)&&(hostsarray.length()>0))
                        {
                            for(int j=0;j<hostsarray.length();j++)
                            {
                                JSONObject hostObject = hostsarray.getJSONObject(j);
                                Host host = new Host();
                                host.setHostid(hostObject.getString("hostid"));
                                if ("extend".equals(get.getParams().getSelectHosts()))
                                {
                                    host.setHost(hostObject.getString("host"));
                                    host.setName(hostObject.getString("name"));
                                    host.setStatus(hostObject.getInt("status"));
                                    host.setAvailable(hostObject.getInt("available"));
                                }
                                event.getHosts().add(host);
                            }
                        }
                    }
                    if (eventobject.has("triggers"))
                    {
                        JSONArray triggerarray = eventobject.getJSONArray("triggers");
                        if ((triggerarray!=null)&&(triggerarray.length()>0))
                        {
                            for (int m=0;m<triggerarray.length();m++)
                            {
                                JSONObject triggerObject = triggerarray.getJSONObject(m);
                                Trigger trigger =new Trigger();
                                trigger.setComments(triggerObject.getString("comments"));
                                trigger.setDescription(triggerObject.getString("description"));
                                trigger.setError(triggerObject.getString("error"));
                                trigger.setExpression(triggerObject.getString("expression"));
                                trigger.setFlags(triggerObject.getInt("flags"));
                                trigger.setLastchange(triggerObject.getString("lastchange"));
                                trigger.setPriority(triggerObject.getInt("priority"));
                                trigger.setStatus(triggerObject.getInt("status"));
                                trigger.setTemplateid(triggerObject.getString("templateid"));
                                trigger.setTriggerid(triggerObject.getString("triggerid"));
                                trigger.setType(triggerObject.getInt("type"));
                                trigger.setUrl(triggerObject.getString("url"));
                                trigger.setValue(triggerObject.getInt("value"));
                                event.getTriggers().add(trigger);
                            }
                        }
                    }
                    if (eventobject.has("items"))
                    {
                        JSONArray itemarray = eventobject.getJSONArray("items");
                        for (int k = 0; k < itemarray.length(); k++)
                        {
                            JSONObject itemObject = itemarray.getJSONObject(k);
                            Item item = new Item();
                            item.setItemid(itemObject.getString("itemid"));
                            item.setLastns(itemObject.getInt("lastns"));
                            item.setLastvalue(itemObject.getString("lastvalue"));
                            if (itemObject.getString("name").contains("$"))
                            {
                                item.setName(replaceStr(itemObject.getString("name"),itemObject.getString("key_")));
                            }
                            else
                            {
                                item.setName(itemObject.getString("name"));
                            }
                            event.getItems().add(item);
                        }
                    }
                    events.add(event);
                }
            } catch (Exception e)
            {
                LOG.info("params getevent to beans catch exception:{}", e);
                return events;
            }
            return events;
        }
         else if (result.has("error"))
         {
             return events;
         }
        return events;
    }
    
    private String replaceStr(String str, String key)
    {
        try
        {
            String[] infoStr = null;
            if (key.contains("["))
            { // 包含[]，说明有$符号产生
                infoStr = key.substring(key.indexOf("[")+1, key.lastIndexOf("]")).split(",");
            }
            for (int i=1;i<=infoStr.length;i++)
            {
                str = str.replace("$" + i, infoStr[i-1]);
            }
        }catch(Exception e)
        {
            LOG.info("event replace String catch exception:{}", e);
        }
        return str;
}
}
