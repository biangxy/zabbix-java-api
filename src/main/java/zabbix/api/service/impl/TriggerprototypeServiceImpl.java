package zabbix.api.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import zabbix.api.domain.base.TriggerPrototype;
import zabbix.api.domain.triggerprototype.TriggerPrototypeCreateRequest;
import zabbix.api.domain.triggerprototype.TriggerPrototypeCreateRequest.TriggerPrototypeCreateParams;
import zabbix.api.domain.triggerprototype.TriggerPrototypeDeleteRequest;
import zabbix.api.domain.triggerprototype.TriggerPrototypeGetRequest;
import zabbix.api.domain.triggerprototype.TriggerPrototypeUpdateRequest;
import zabbix.api.service.ITriggerprototypeService;
import zabbix.api.util.HttpExecuter;

/**
 * trigger prototype功能实现
 * @author zhaohb
 *
 */
public class TriggerprototypeServiceImpl implements ITriggerprototypeService
{
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(TriggerprototypeServiceImpl.class);

    public Object triggerPrototypeCreate(TriggerPrototypeCreateRequest triggerPrototypeCreate)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(triggerPrototypeCreate);
            LOG.debug("create trigger prototype response:{}", respObj);
        }
        catch (Exception e)
        {
            LOG.info("create trigger prototype catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    
    public Object addTriggerPrototype(TriggerPrototype triggerPrototype)
    {
        Object object = null;
        String desc = triggerPrototype.getDescription();
        String expr = triggerPrototype.getExpression();
        int prio = triggerPrototype.getPriority();
        int status = triggerPrototype.getStatus();
        if (desc!= null && !"".equals(desc) && expr!= null && !"".equals(expr))
        {
            TriggerPrototypeCreateRequest prototypeCreateRequest = new TriggerPrototypeCreateRequest();
            TriggerPrototypeCreateParams params = prototypeCreateRequest.getParams();
            params.setDescription(desc);
            params.setExpression(expr);
            params.setPriority(prio);
            params.setStatus(status);
            object = this.triggerPrototypeCreate(prototypeCreateRequest);
        }
        return object;
    }
    
    public Object triggerPrototypeDelete(TriggerPrototypeDeleteRequest triggerPrototypeDelete)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(triggerPrototypeDelete);
            LOG.debug("delete trigger prototype response:{}", respObj);
        }
        catch (Exception e)
        {
            LOG.info("delete trigger prototype catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    
    public Object triggerPrototypeGet(TriggerPrototypeGetRequest triggerPrototypeGet)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(triggerPrototypeGet);
            LOG.debug("get trigger prototype response:{}", respObj);
        }
        catch (Exception e)
        {
            LOG.info("get trigger prototype catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    
    @Override
    public List<TriggerPrototype> triggerPrototypeGetToBean(TriggerPrototypeGetRequest triggerPrototypeGet)
    {
        List<TriggerPrototype> triggerPrototypes = null;
        JSONObject result= (JSONObject) this.triggerPrototypeGet(triggerPrototypeGet);
        if (result.has("result"))
        {
            try
            {
                JSONArray array = result.getJSONArray("result");
                if (array!= null&&array.length()>0)
                {
                    triggerPrototypes = new ArrayList<TriggerPrototype>() ;
                    for (int i = 0; i<array.length();i++)
                    {
                        JSONObject jsonObject = array.getJSONObject(i);
                        TriggerPrototype triggerPrototype = new TriggerPrototype();
                        triggerPrototype.setTriggerid(jsonObject.getString("triggerid"));
                        triggerPrototype.setComments(jsonObject.getString("comments"));
                        triggerPrototype.setDescription(jsonObject.getString("description"));
                        triggerPrototype.setExpression(jsonObject.getString("expression"));
                        triggerPrototype.setPriority(jsonObject.getInt("priority"));
                        triggerPrototype.setStatus(jsonObject.getInt("status"));
                        triggerPrototype.setType(jsonObject.getInt("type"));
                        triggerPrototype.setUrl(jsonObject.getString("url"));
                        triggerPrototype.setTemplateid(jsonObject.getString("templateid"));
                        triggerPrototypes.add(triggerPrototype);
                    }
                    return triggerPrototypes;
                }
            }
            catch (Exception e)
            {
                LOG.info("get trigger prototype and params to beans catch exception:{}", e);
                return null;
            }
        }
        return triggerPrototypes;
    }
    
    
    public Object triggerPrototypeUpdate(TriggerPrototypeUpdateRequest triggerPrototypeUpdate)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(triggerPrototypeUpdate);
            LOG.debug("update trigger prototype response:{}", respObj);
            if (respObj.has("result"))
            {
                result = respObj.get("result");
            }
            else if (respObj.has("error"))
            {
                result = respObj.get("error");
            }
        }
        catch (Exception e)
        {
            LOG.info("update trigger prototype catch exception:{}", e);
            return null;
        }
        return result;
    }

}
