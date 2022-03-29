package zabbix.api.entry;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zabbix.api.domain.base.HostGroup;
import zabbix.api.domain.hostgroup.HostGroupCreateRequest;
import zabbix.api.domain.hostgroup.HostGroupGetRequest;
import zabbix.api.domain.hostgroup.HostGroupGetRequest.HostGroupGetParams.HostGroupFilter;
import zabbix.api.service.IHostgroupService;
import zabbix.api.service.impl.HostgroupServiceImpl;

public class HostGroupUnit
{
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(HostGroupUnit.class);
    
    /**
     * host group前缀
     */
    private final static String CLUSTER_PRIFEX = "MSTP_AUTO_";
    
    /**
     * host group后缀
     */
    private final static String CLISTER_TAIL = "_CLUSTER";
    
    /**
     * hostGroup实例
     */
    private static IHostgroupService hostgroupService = new HostgroupServiceImpl();
    
    /**
     * 创建host群组，如果未存在则新建 如果已存在则查询群组ID
     * @param serverName        服务名
     * @return          群组id 失败返回null
     */
    public String buildHostGroup(String serverName)
    {
        String hostGroupName = constructClusterName(serverName);
        
        if (checkHostGroupExist(hostGroupName))
        {
            return getHostGroupId(hostGroupName);
        }
        else
        {
            return createHostGroup(hostGroupName);
        }
    }
    
    /**
     * 创建群组
     * @param hostGroupName 群组名
     * @return  群组Id
     */
    private String createHostGroup(String hostGroupName)
    {
        HostGroupCreateRequest hostGroupCreate = new HostGroupCreateRequest();
        
        HostGroup hostGroup = new HostGroup();
        hostGroup.setName(hostGroupName);
        hostGroupCreate.getParams().add(hostGroup);
        JSONObject resp = (JSONObject)hostgroupService.hostGroupCreate(hostGroupCreate);
        LOG.debug("create host group get response:{}", resp);
        return resp.getJSONArray("groupids").getString(0);
    }
    
    /**
     * 获取已存在群组的groupId
     * @param hostGroupName 群组名
     * @return  群组Id
     */
    private String getHostGroupId(String hostGroupName)
    {
        HostGroupGetRequest hostGroupGet = new HostGroupGetRequest();
        hostGroupGet.getParams().setOutput("extend");
        // 设置查询过滤条件
        List<String> names = new ArrayList<>();
        names.add(hostGroupName);
        HostGroupFilter filter = new HostGroupFilter();
        filter.setName(names);
        hostGroupGet.getParams().setFilter(filter);
        List<HostGroup> groups = hostgroupService.getHostGroupBean(hostGroupGet);
        LOG.debug("get host group id get response:{}", groups);
        return groups.get(0).getGroupid();
    }

    /**
     * 校验群组名是否存在
     * @param hostGroupName 群组集群名
     * @return true：存在 false：不存在
     */
    private boolean checkHostGroupExist(String hostGroupName)
    {
        HostGroupGetRequest hostGroupGet = new HostGroupGetRequest();
        hostGroupGet.getParams().setOutput("extend");
        
        // 设置查询过滤条件
        List<String> names = new ArrayList<>();
        names.add(hostGroupName);
        HostGroupFilter filter = new HostGroupFilter();
        filter.setName(names);
        hostGroupGet.getParams().setFilter(filter);
        
        List<HostGroup> list = hostgroupService.getHostGroupBean(hostGroupGet);
        LOG.debug("get host group get response:{}", list);
        if (list == null)
        {
            return false;
        }
        else
        {
            return list.size() > 0 ? true : false;
        }
    }
    
    /**
     * 拼装host群组集群名
     * @param serverName    服务名
     * @return          群组名
     */
    private String constructClusterName(String serverName)
    {
        return CLUSTER_PRIFEX + serverName.toUpperCase() + CLISTER_TAIL;
    }
}
