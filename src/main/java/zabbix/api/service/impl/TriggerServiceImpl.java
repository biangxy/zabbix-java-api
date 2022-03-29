package zabbix.api.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import zabbix.api.domain.base.Trigger;
import zabbix.api.domain.trigger.TriggerAdddependenciesRequest;
import zabbix.api.domain.trigger.TriggerCreateRequest;
import zabbix.api.domain.trigger.TriggerCreateRequest.TriggerCreateParams;
import zabbix.api.domain.trigger.TriggerDeleteRequest;
import zabbix.api.domain.trigger.TriggerDeletedependenciesRequest;
import zabbix.api.domain.trigger.TriggerExistsRequest;
import zabbix.api.domain.trigger.TriggerGetRequest;
import zabbix.api.domain.trigger.TriggerGetobjectsRequest;
import zabbix.api.domain.trigger.TriggerIsreadableRequest;
import zabbix.api.domain.trigger.TriggerIswritableRequest;
import zabbix.api.domain.trigger.TriggerUpdateRequest;
import zabbix.api.service.ITriggerService;
import zabbix.api.util.HttpExecuter;

/**
 * trigger功能实现
 * @author zhaohb
 *
 */
public class TriggerServiceImpl implements ITriggerService
{
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(TriggerServiceImpl.class);
    
    public Object adddependencies(TriggerAdddependenciesRequest adddependencies)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(adddependencies);
            LOG.debug("add dependence response:{}", respObj);
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
            LOG.info("add dependence catch exception:{}", e);
            return null;
        }
        return result;
    }
    
    public Object create(TriggerCreateRequest create)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(create);
            LOG.debug("create dependence response:{}", respObj);
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
            LOG.info("create dependence catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    
    public Object addTrigger(Trigger trigger)
    {
        Object object = null;
        String desc = trigger.getDescription();
        String expr = trigger.getExpression();
        int prior = trigger.getPriority();
        int status = trigger.getStatus();
        if (desc != null&&!"".equals(desc) && expr!= null && !"".equals(expr))
        {
            TriggerCreateRequest createRequest = new TriggerCreateRequest();
            TriggerCreateParams params = createRequest.getParams();
            params.setDescription(desc);
            params.setExpression(expr);
            params.setStatus(status);
            params.setPriority(prior);
            object = this.create(createRequest);
        }
        return object;
    }
    
    public Object deletedependencies(TriggerDeletedependenciesRequest deletedependencies)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(deletedependencies);
            LOG.debug("delete dependence response:{}", respObj);
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
            LOG.info("delete dependence catch exception:{}", e);
            return null;
        }
        return result;
    }
    
    public Object delete(TriggerDeleteRequest delete)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(delete);
            LOG.debug("delete trigger response:{}", respObj);
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
            LOG.info("delete trigger catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    
    public Object exists(TriggerExistsRequest exists)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(exists);
            LOG.debug("exist trigger response:{}", respObj);
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
            LOG.info("exist trigger catch exception:{}", e);
            return null;
        }
        return result;
    }
    public Object getobjects(TriggerGetobjectsRequest getobjects)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(getobjects);
            LOG.debug("get trigger object response:{}", respObj);
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
            LOG.info("get trigger object catch exception:{}", e);
            return null;
        }
        return result;
    }
    
    public Object get(TriggerGetRequest get)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(get);
            LOG.debug("get trigger response:{}", respObj);
            
        }
        catch (Exception e)
        {
            LOG.info("get trigger catch exception:{}", e);
            return null;
        }
        return respObj;
    }

    public Object isreadable(TriggerIsreadableRequest isreadable)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(isreadable);
            LOG.debug("is trigger readable response:{}", respObj);
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
            LOG.info("is trigger readable catch exception:{}", e);
            return null;
        }
        return result;
    }
    
    public Object iswritable(TriggerIswritableRequest iswritable)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(iswritable);
            LOG.debug("is trigger writable response:{}", respObj);
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
            LOG.info("is trigger writable catch exception:{}", e);
            return null;
        }
        return result;
    }
    public Object update(TriggerUpdateRequest update)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(update);
            LOG.debug("trigger update response:{}", respObj);
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
            LOG.info("trigger update catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    
    @Override
    public List<Trigger> getTriggerBean(TriggerGetRequest get)
    {
        JSONObject result = (JSONObject) this.get(get);
        List<Trigger> triggers = null;
        if (result.has("result"))
        {
            try
            {
                JSONArray array = result.getJSONArray("result");
                if (array!= null&&array.length()>0)
                {
                    triggers = new ArrayList<Trigger>();
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject triggerjson = array.getJSONObject(i);
                        Trigger trigger =new Trigger();
                        trigger.setComments(triggerjson.getString("comments"));
                        trigger.setDescription(triggerjson.getString("description"));
                        trigger.setError(triggerjson.getString("error"));
                        trigger.setExpression(triggerjson.getString("expression"));
                        trigger.setFlags(triggerjson.getInt("flags"));
                        trigger.setLastchange(triggerjson.getString("lastchange"));
                        trigger.setPriority(triggerjson.getInt("priority"));
                        trigger.setStatus(triggerjson.getInt("status"));
                        trigger.setTemplateid(triggerjson.getString("templateid"));
                        trigger.setTriggerid(triggerjson.getString("triggerid"));
                        trigger.setType(triggerjson.getInt("type"));
                        trigger.setUrl(triggerjson.getString("url"));
                        trigger.setValue(triggerjson.getInt("value"));
                        trigger.setValue_flags(triggerjson.getInt("value_flag"));
                        triggers.add(trigger);
                    }
                }
            }
            catch (Exception e)
            {
                LOG.info("params trigger to beans catch exception:{}", e);
                return triggers;
            }
        }
        else if (result.has("error"))
        {
            return triggers;
        }
        return triggers;
    }
}
