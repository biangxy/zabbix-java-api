package zabbix.api.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zabbix.api.domain.base.ItemPrototype;
import zabbix.api.domain.itemprototype.ItemPrototypeCreateRequest;
import zabbix.api.domain.itemprototype.ItemPrototypeDeleteRequest;
import zabbix.api.domain.itemprototype.ItemPrototypeExistsRequest;
import zabbix.api.domain.itemprototype.ItemPrototypeGetRequest;
import zabbix.api.domain.itemprototype.ItemPrototypeIsreadableRequest;
import zabbix.api.domain.itemprototype.ItemPrototypeIswritableRequest;
import zabbix.api.domain.itemprototype.ItemPrototypeUpdateRequest;
import zabbix.api.service.IItemprototypeService;
import zabbix.api.util.HttpExecuter;

/**
 * 监控参数模板类型
 * @author zhaohb
 *
 */
public class ItemprototypeServiceImpl implements IItemprototypeService
{
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(ItemprototypeServiceImpl.class);
    
    public Object itemPrototypeCreate(ItemPrototypeCreateRequest itemPrototypeCreate)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(itemPrototypeCreate);
            LOG.debug("item prototype response:{}", respObj);
//            Object result = null;
//            if (respObj.has("result")) {
//                result = respObj.get("result");
//            }
//             else if (respObj.has("error")) {
//                result = respObj.get("error");
//            }
        }
        catch (Exception e)
        {
            LOG.info("item prototype catch exception:{}", e);
            return null;
        }
        return respObj;
    }

    public Object itemPrototypeDelete(ItemPrototypeDeleteRequest itemPrototypeDelete)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(itemPrototypeDelete);
            LOG.debug("item prototype delete response:{}", respObj);
//            Object result = null;
//            if (respObj.has("result")) {
//                result = respObj.get("result");
//            }
//             else if (respObj.has("error")) {
//                result = respObj.get("error");
//            }
        }
        catch (Exception e)
        {
            LOG.info("item prototype delete catch exception:{}", e);
            return null;
        }
        return respObj;
    }

    public Object itemPrototypeExists(ItemPrototypeExistsRequest itemPrototypeExists)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(itemPrototypeExists);
            LOG.debug("item prototype exists response:{}", respObj);
//            Object result = null;
//            if (respObj.has("result")) {
//                result = respObj.get("result");
//            }
//             else if (respObj.has("error")) {
//                result = respObj.get("error");
//            }
        }
        catch (Exception e)
        {
            LOG.info("item prototype exists catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    
    public Object itemPrototypeGet(ItemPrototypeGetRequest itemPrototypeGet)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(itemPrototypeGet);
            LOG.debug("get item prototype response:{}", respObj);
//            Object result = null;
//            if (respObj.has("result")) {
//                result = respObj.get("result");
//            }
//             else if (respObj.has("error")) {
//                result = respObj.get("error");
//            }
        }
        catch (Exception e)
        {
            LOG.info("get item prototype catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    
    @Override
    public List<ItemPrototype> itemPrototypeGetToBean(ItemPrototypeGetRequest itemPrototypeGet)
    {
        JSONObject result = (JSONObject) this.itemPrototypeGet(itemPrototypeGet);
        List<ItemPrototype> ItemPrototypes= null;
        if (result.has("result"))
        {
            JSONArray array;
            try
            {
                array = result.getJSONArray("result");
                if (array!= null&&array.length()>0)
                {
                    ItemPrototypes =new ArrayList<ItemPrototype>();
                    for(int i = 0; i<array.length();i++)
                    {
                        JSONObject jsonObject = array.getJSONObject(i);
                        ItemPrototype itemPrototype = new ItemPrototype();
                        itemPrototype.setItemid(jsonObject.getString("itemid"));
                        itemPrototype.setDelay(jsonObject.getInt("delay"));
                        itemPrototype.setKey_(jsonObject.getString("key_"));
                        itemPrototype.setName(jsonObject.getString("name"));
                        ItemPrototypes.add(itemPrototype);
                    }
                }
            }
            catch (JSONException e)
            {
                LOG.info("params get item prototype to beans catch exception:{}", e);
                return null;
            }
        }
        return ItemPrototypes;
    }
    
    public Object itemPrototypeIsreadable(ItemPrototypeIsreadableRequest itemPrototypeIsreadable)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(itemPrototypeIsreadable);
            LOG.debug("is item prototype readable response:{}", respObj);
//            Object result = null;
//            if (respObj.has("result")) {
//                result = respObj.get("result");
//            }
//             else if (respObj.has("error")) {
//                result = respObj.get("error");
//            }
        }
        catch (Exception e)
        {
            LOG.info("is item prototype readable catch exception:{}", e);
            return null;
        }
        return respObj;
    }

    public Object itemPrototypeIswritable(ItemPrototypeIswritableRequest itemPrototypeIswritable)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(itemPrototypeIswritable);
            LOG.debug("is item prototype writable response:{}", respObj);
//            Object result = null;
//            if (respObj.has("result")) {
//                result = respObj.get("result");
//            }
//             else if (respObj.has("error")) {
//                result = respObj.get("error");
//            }
        }
        catch (Exception e)
        {
            LOG.info("is item prototype writable catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    public Object itemPrototypeUpdate(ItemPrototypeUpdateRequest itemPrototypeUpdate)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(itemPrototypeUpdate);
            LOG.debug("item prototype update response:{}", respObj);
//            Object result = null;
//            if (respObj.has("result")) {
//                result = respObj.get("result");
//            }
//             else if (respObj.has("error")) {
//                result = respObj.get("error");
//            }
        }
        catch (Exception e)
        {
            LOG.info("item prototype update catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    
    
}
