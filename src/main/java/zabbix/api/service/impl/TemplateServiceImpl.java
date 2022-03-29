package zabbix.api.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zabbix.api.domain.base.Template;
import zabbix.api.domain.template.TemplateCreateRequest;
import zabbix.api.domain.template.TemplateDeleteRequest;
import zabbix.api.domain.template.TemplateExistsRequest;
import zabbix.api.domain.template.TemplateGetRequest;
import zabbix.api.domain.template.TemplateGetobjectsRequest;
import zabbix.api.domain.template.TemplateIsreadableRequest;
import zabbix.api.domain.template.TemplateIswritableRequest;
import zabbix.api.domain.template.TemplateMassaddRequest;
import zabbix.api.domain.template.TemplateMassremoveRequest;
import zabbix.api.domain.template.TemplateMassupdateRequest;
import zabbix.api.domain.template.TemplateUpdateRequest;
import zabbix.api.service.ITemplateService;
import zabbix.api.util.HttpExecuter;
/**
 * 模板操作实现
 * @author zhaohb
 *
 */
public class TemplateServiceImpl implements ITemplateService
{
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(TemplateServiceImpl.class);
    
    public Object create(TemplateCreateRequest create)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(create);
            LOG.debug("create template response:{}", respObj);
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
            LOG.info("create template catch exception:{}", e);
            return null;
        }
        return result;
    }

    public Object delete(TemplateDeleteRequest delete)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(delete);
            LOG.debug("delete template response:{}", respObj);
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
            e.printStackTrace();
        }
        return result;
    }

    public Object exists(TemplateExistsRequest exists)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(exists);
            LOG.debug("template exist response:{}", respObj);
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
            LOG.info("template exist catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    
    public Object getobjects(TemplateGetobjectsRequest getobjects)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(getobjects);
            LOG.debug("get template object response:{}", respObj);
            if (respObj.has("result")) {
                result = respObj.get("result");
            }
             else if (respObj.has("error")) {
                result = respObj.get("error");
            }
        }
        catch (Exception e)
        {
            LOG.info("get template object catch exception:{}", e);
            return null;
        }
        return result;
    }

    public Object get(TemplateGetRequest get)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(get);
            LOG.debug("get template response:{}", respObj);
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
            LOG.info("get template catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    
    @Override
    public List<Template> getTemplateToBean(TemplateGetRequest get)
    {
        List<Template> templates = null;
        JSONObject result= (JSONObject) this.get(get);
        if (result.has("result"))
        {
            JSONArray array;
            try
            {
                array = result.getJSONArray("result");
                if (array != null&&array.length()>0)
                {
                    templates = new ArrayList<Template>();
                    for (int i = 0; i<array.length();i++)
                    {
                        JSONObject jsonObject = array.getJSONObject(i);
                        Template template = new Template();
                        template.setTemplateid(jsonObject.getString("templateid"));
                        template.setHost(jsonObject.getString("host"));
                        template.setName(jsonObject.getString("name"));
                        templates.add(template);
                    }
                }
            }
            catch (Exception e)
            {
                LOG.info("params template to beans catch exception:{}", e);
                return null;
            }
        }
        else if (result.has("error"))
        {
            return null;
        }
        return templates;
    }
    
    public Object isreadable(TemplateIsreadableRequest isreadable)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(isreadable);
            LOG.debug("is template readable response:{}", respObj);
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
            LOG.info("is template readable catch exception:{}", e);
            return null;
        }
        return result;
    }
    
    public Object iswritable(TemplateIswritableRequest iswritable)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(iswritable);
            LOG.debug("is template writable response:{}", respObj);
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
            LOG.info("is template writable catch exception:{}", e);
            return null;
        }
        return result;
    }
    
    public Object massadd(TemplateMassaddRequest massadd)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(massadd);
            LOG.debug("mass add template response:{}", respObj);
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
            LOG.info("mass add template catch exception:{}", e);
            return null;
        }
        return result;
    }
    
    public Object massremove(TemplateMassremoveRequest massremove)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(massremove);
            LOG.debug("mass remove template response:{}", respObj);
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
            LOG.info("mass remove template catch exception:{}", e);
            return null;
        }
        return result;
    }
    
    public Object massupdate(TemplateMassupdateRequest massupdate)
    {
        Object result = null;
        JSONObject respObj = null;
        try {
            respObj = HttpExecuter.post(massupdate);
            LOG.debug("mass update template response:{}", respObj);
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
            LOG.info("mass update template catch exception:{}", e);
            return null;
        }
        return result;
    }
    
    public Object update(TemplateUpdateRequest update)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(update);
            LOG.debug("update template response:{}", respObj);
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
            LOG.info("update template catch exception:{}", e);
            return null;
        }
        return result;
    }
}
