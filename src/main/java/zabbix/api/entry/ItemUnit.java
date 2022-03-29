package zabbix.api.entry;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zabbix.api.domain.item.ItemCreateRequest;
import zabbix.api.domain.item.ItemGetRequest;
import zabbix.api.domain.item.ItemGetRequest.ItemGetParams.ItemFilter;
import zabbix.api.entity.item.ItemType;
import zabbix.api.entity.item.MetricsType;
import zabbix.api.entity.item.ValueType;
import zabbix.api.service.IItemService;
import zabbix.api.service.impl.ItemServiceImpl;

/**
 * Item
 * @author zhaohb
 *
 */
public class ItemUnit
{
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(ItemUnit.class);
    
    /**
     * key prefix example:jmx[metrics:name=file_share_list_query_req_fail_num,Count]
     */
    private static final String KEY_PREFIX = "jmx[metrics:name=";
    
    /**
     * key tail
     */
    private static final String KEY_TAIL = "]";
    
    /**
     * 数据监控间隔 单位：秒
     */
    private final int MONITOR_INTENAL = 30;
    
    /**
     * item实例
     */
    private static IItemService itemService = new ItemServiceImpl();
    
    /**
     * 在指定application上创建item
     * @param templateId        模板Id
     * @param applicationId     application ID
     * @param key               item关键字
     * @param desc              中文描述
     * @param gauge              item监控类型 ：guage、meter、counter
     * @return                  true：创建成功  false：创建失败
     */
    public boolean createItem(String templateId, String applicationId, String key, String desc, MetricsType gauge)
    {
        if (checkItemExist(applicationId, key, gauge.value()))
        {
            return true;
        }
        else
        {
            ItemCreateRequest create = new ItemCreateRequest();
            
            try
            {
                List<String> applications = new ArrayList<>();
                applications.add(applicationId);
                create.getParams().setApplications(applications);
                
                create.getParams().setName(desc);
                create.getParams().setKey_(constructKey(key, gauge.value()));
//                create.getParams().setHostid(HostUnit.getHostId());
                create.getParams().setHostid(templateId);
                
                String hostInterfaceId = new InterfaceUnit().queryJmxInterface(HostUnit.getHostId());
                create.getParams().setInterfaceid(hostInterfaceId);
                
                create.getParams().setDelay(MONITOR_INTENAL);
                create.getParams().setType(ItemType.JMX_AGNET.value());
                create.getParams().setValue_type(ValueType.NUMERIC_UNSIGNED.value());
                
                JSONObject resp = (JSONObject)itemService.create(create);
                JSONArray arr = resp.getJSONArray("itemids");
                return arr.length() > 0 ? true : false;
            }
            catch (Exception e)
            {
                return false;
            }
        }
    }
    
    /**
     * 根据不同的类型组装不同的监控key
     * @param key           关键字
     * @param type          监控类型
     * @return          key
     */
    private String constructKey(String key, String type)
    {
        return KEY_PREFIX + key + "," + type + KEY_TAIL;
    }

    /**
     * 检查在指定application下是否已经存在item
     * @param application       application
     * @param key               key
     * @param type              jmx监控类型
     * @return  true:存在  false:不存在
     */
    private boolean checkItemExist(String application, String key, String type)
    {
        ItemGetRequest get = new ItemGetRequest();
        get.getParams().setOutput("extend");    // shorten, refer, extend
        String [] hosts = {HostUnit.getHostId()};
        get.getParams().setHostids(hosts);
        get.getParams().setApplication(application);
        get.getParams().getSearch().setKey_(constructKey(key, type));
//        get.getParams().getSearch().setKey_("jmx");
//        get.getParams().getSearch().setName(constructKey(key, type));
        List<String> list = new ArrayList<>();
        list.add(HostUnit.getHostId());
        ItemFilter filter = new ItemFilter();
        filter.setHost(list);
        List<String> keyList = new ArrayList<>();
        keyList.add(key);
        filter.setKey_(keyList);
        get.getParams().setFilter(filter);
        
        String[] sortfield = {"name"};
        get.getParams().setSortfield(sortfield);
        JSONObject resp = (JSONObject)itemService.get(get);
        LOG.debug("check item exists response:{}", resp);
        JSONArray arr = resp.getJSONArray("result");
        return arr.length() > 0 ? true : false;
    }
}
