package zabbix.api.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import zabbix.api.domain.base.Item;
import zabbix.api.domain.item.ItemCreateRequest;
import zabbix.api.domain.item.ItemDeleteRequest;
import zabbix.api.domain.item.ItemExistsRequest;
import zabbix.api.domain.item.ItemGetRequest;
import zabbix.api.domain.item.ItemGetobjectsRequest;
import zabbix.api.domain.item.ItemIsreadableRequest;
import zabbix.api.domain.item.ItemIswritableRequest;
import zabbix.api.domain.item.ItemUpdateRequest;
import zabbix.api.service.IItemService;
import zabbix.api.util.HttpExecuter;

/**
 * @ClassName: ItemServiceImpl
 * @Description: 监控项接口实现
 * @author zhaohb
 */
public class ItemServiceImpl implements IItemService 
{
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(ItemServiceImpl.class);
    
    public Object create(ItemCreateRequest create)
    {
        Object result = null;
        
        JSONObject respObj =HttpExecuter.post(create);
        if (respObj.has("result"))
        {
            result = respObj.get("result");
        }
        else if (respObj.has("error"))
        {
            result = respObj.get("error");
        }

        return result;
    }
    public Object delete(ItemDeleteRequest delete)
    {
        Object result = null;
        JSONObject respObj = HttpExecuter.post(delete);
        
        if (respObj.has("result"))
        {
            result = respObj.get("result");
        }
        else if (respObj.has("error"))
        {
            result = respObj.get("error");
        }
        return result;
    }
    
    public Object exists(ItemExistsRequest exists)
    {
        Object result = null;
        JSONObject respObj = HttpExecuter.put(exists);
        if (respObj.has("result"))
        {
            result = respObj.get("result");
        }
        else if (respObj.has("error"))
        {
            result = respObj.get("error");
        }
        return result;
    }

    public Object getobjects(ItemGetobjectsRequest getobjects)
    {
        Object result = null;
        JSONObject respObj = HttpExecuter.post(getobjects);

            if (respObj.has("result"))
            {
                result = respObj.get("result");
            }
            else if (respObj.has("error"))
            {
                result = respObj.get("error");
            }
        return result;
    }
    
    /*
     * Title: get
     * Description: 获取监控项信息（json）
     * @param get
     * @return Object
     * @see zabbix.api.service.IItemService#get(zabbix.api.domain.item.ItemGetRequest)
     */
    public Object get(ItemGetRequest get)
    {
        return HttpExecuter.post(get);
    }
    
    /*
     * Title: getItemToBean
     * Description: 获取监控项信息（bean）
     * @param get
     * @return List<Item>
     * @see zabbix.api.service.IItemService#getItemToBean(zabbix.api.domain.item.ItemGetRequest)
     */
    @Override
    public List<Item> getItemToBean(ItemGetRequest get)
    {
        JSONObject result = (JSONObject) this.get(get);
        List<Item> items = null;
        if (result.has("result"))
        {
            try 
            {
                JSONArray array  = result.getJSONArray("result");
                if(array!=null&&array.length()>0)
                {
                    items = new ArrayList<Item>();
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object = array.getJSONObject(i);
                        Item item = new Item();
                        item.setAuthtype(object.getInt("authtype"));
                        item.setData_type(object.getInt("data_type"));
                        item.setDelay(object.getInt("delay"));
                        item.setDelay_flex(object.getString("delay_flex"));
                        item.setDelta(object.getInt("delta"));
                        if(object.getString("description").contains("$"))
                        {
                            item.setDescription(replaceStr(object.getString("description"),object.getString("key_")));
                        }
                        else
                        {
                            item.setDescription(object.getString("description"));
                        }
                        item.setError(object.getString("error"));
                        item.setFlags(object.getInt("flags"));
                        item.setFormula(object.getInt("formula"));
                        item.setHistory(object.getInt("history"));
                        item.setHostid(object.getString("hostid"));
                        item.setInterfaceid(object.getString("interfaceid"));
                        item.setInventory_link(object.getInt("inventory_link"));
                        item.setIpmi_sensor(object.getString("ipmi_sensor"));
                        item.setItemid(object.getString("itemid"));
                        item.setKey_(object.getString("key_"));
                        item.setLastclock(object.getString("lastclock"));
                        item.setLastns(object.getInt("lastns"));
                        item.setLastvalue(object.getString("lastvalue"));
                        item.setLogtimefmt(object.getString("logtimefmt"));
                        item.setMultiplier(object.getInt("multiplier"));
                        if(object.getString("name").contains("$"))
                        {
                            item.setName(replaceStr(object.getString("name"),object.getString("key_")));
                        }
                        else
                        {
                            item.setName(object.getString("name"));
                        }
                        item.setParams(object.getString("params"));
                        item.setPassword(object.getString("password"));
                        item.setPort(object.getString("port"));
//            item.setPrevorgvalue(object.getString("prevorgvalue"));
                        item.setPrevvalue(object.getString("prevvalue"));
                        item.setPrivatekey(object.getString("privatekey"));
                        item.setPublickey(object.getString("publickey"));
                        item.setSnmp_community(object.getString("snmp_community"));
                        item.setSnmp_oid(object.getString("snmp_oid"));
                        item.setSnmpv3_authpassphrase(object.getString("snmpv3_authpassphrase"));
                        item.setSnmpv3_privpassphrase(object.getString("snmpv3_privpassphrase"));
                        item.setSnmpv3_securitylevel(object.getInt("snmpv3_securitylevel"));
                        item.setSnmpv3_securityname(object.getString("snmpv3_securityname"));
                        item.setStatus(object.getInt("status"));
                        item.setTemplateid(object.getString("templateid"));
                        item.setTrapper_hosts(object.getString("trapper_hosts"));
                        item.setTrends(object.getInt("trends"));
                        item.setType(object.getInt("type"));
                        item.setUnits(object.getString("units"));
                        item.setUsername(object.getString("username"));
                        item.setValue_type(object.getInt("value_type"));
                        item.setValuemapid(object.getString("valuemapid"));
                        items.add(item);
                    }
                }
            }
            catch (JSONException e)
            {
                LOG.info("getItemToBean catch exception:{}", e);
                return null;
            }
            return items;
        }
        else if (result.has("error"))
        {
             return null;
        }
        return items;
    }
    
    public Object isreadable(ItemIsreadableRequest isreadable)
    {
        Object result = null;
        JSONObject rs = HttpExecuter.post(isreadable);
        if (rs.has("result"))
        {
            result = rs.get("result");
        }
        else if (rs.has("error"))
        {
            result = rs.get("error");
        }
        return result;
    }
    
    public Object iswritable(ItemIswritableRequest iswritable)
    {
        Object result = null;
        JSONObject rs = HttpExecuter.post(iswritable);
        if (rs.has("result"))
        {
            result = rs.get("result");
        }
        else if (rs.has("error"))
        {
            result = rs.get("error");
        }
        return result;
    }
    
    public Object update(ItemUpdateRequest update)
    {
        return HttpExecuter.post(update);
    }
    
    private String replaceStr(String str, String key)
    {
            try{
                String[] infoStr = null;
                if (key.contains("["))
                { // 包含[]，说明有$符号产生
                    infoStr = key.substring(key.indexOf("[")+1, key.lastIndexOf("]")).split(",");
                }
                for (int i=1;i<=infoStr.length;i++)
                {
                    str = str.replace("$" + i, infoStr[i-1]);
                }
            }
            catch(Exception e) 
            {
                LOG.info("replaceStr catch exception :{}", e);
            }
            return str;
    }
    

}
