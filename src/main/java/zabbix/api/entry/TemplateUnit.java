package zabbix.api.entry;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zabbix.api.domain.base.HostGroup;
import zabbix.api.domain.base.Template;
import zabbix.api.domain.template.TemplateCreateRequest;
import zabbix.api.domain.template.TemplateGetRequest;
import zabbix.api.service.ITemplateService;
import zabbix.api.service.impl.TemplateServiceImpl;

public class TemplateUnit
{
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(TemplateUnit.class);
    
    private static final String TEMPLATE_PREFIX = "jmx_auto_";
    
    private static final String TEMPLATE_TAIL = "_template";
    
    private static String templateId = null;
    
    private static ITemplateService templateService = new TemplateServiceImpl();
    
    /**
     * 创建模板
     * @param groupId       群组ID
     * @param name          服务名
     * @return              templateId
     */
    public String buildTemplate(String groupId, String name)
    {
        if (checkTemplateExist(name))
        {
            return getchTemplateId(name);
        }
        else
        {
            return createTemplate(groupId, name);
        }
    }
    
    /**
     * 创建模板
     * @param groupId       群组id
     * @param hostId        hostId
     * @param name          模板名
     * @return template ID
     */
    private String createTemplate(String groupId, String name)
    {
        int loop = 3;
        long sleepInternal = 5 * 1000;
        TemplateCreateRequest create = new TemplateCreateRequest();
        
        while (loop > 0)
        {
            try
            {
                create.getParams().setHost(constructTemplateName(name));
                create.getParams().setName(constructTemplateName(name));
                List<HostGroup> hgList = new ArrayList<>();
                HostGroup hg = new HostGroup();
                hg.setGroupid(groupId);
                hgList.add(hg);
                create.getParams().getGroups().add(hg);
                create.getParams().setGroups(hgList);
                JSONObject resp = (JSONObject)templateService.create(create);
                LOG.debug("create template response:{}", resp);
                
                String templateId = resp.getJSONArray("templateids").getString(0);
                TemplateUnit.templateId = templateId;
                return templateId;
            }
            catch (Exception e)
            {
                LOG.info("create template fail. try again...");
                loop--;
            }
            try {
                Thread.sleep(sleepInternal);
            }
            catch (InterruptedException e)
            {
                LOG.info("sleep catch exception:{}", e);
            }
        }
        return null;
    }

    /**
     * 校验模板是否存在
     * @param name      服务名
     * @return  true:存在  false：不存在
     */
    private boolean checkTemplateExist(String name)
    {
        TemplateGetRequest get = new TemplateGetRequest();
        get.getParams().setOutput("extend");
        List<String> hosts = new ArrayList<>();
        hosts.add(constructTemplateName(name));
        get.getParams().getFilter().setHost(hosts);
        JSONObject resp = (JSONObject)templateService.get(get);
        LOG.debug("check template exist response:{}", resp);
        JSONArray arr = new JSONArray(resp.get("result").toString());
        return arr.length() > 0? true : false;
    }
    
    private String getchTemplateId(String name)
    {
        TemplateGetRequest get = new TemplateGetRequest();
        get.getParams().setOutput("extend");
        List<String> hosts = new ArrayList<>();
        hosts.add(constructTemplateName(name));
        get.getParams().getFilter().setHost(hosts);
        List<Template> templates = templateService.getTemplateToBean(get);
        LOG.debug("check template exist response:{}", templates.toString());
        templateId = templates.get(0).getTemplateid();
        return templateId;
    }

    
    /**
     * 组装template名
     * @param name  服务名
     * @return      模板名
     */
    private String constructTemplateName(String name)
    {
        return TEMPLATE_PREFIX + name.toLowerCase() + TEMPLATE_TAIL;
    }

    public String getTemplateId()
    {
        return templateId;
    }

    public void setTemplateId(String templateId)
    {
        TemplateUnit.templateId = templateId;
    }
}
