package zabbix.api.test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zabbix.api.domain.base.HostGroup;
import zabbix.api.domain.base.HostInterface;
import zabbix.api.domain.host.HostCreateRequest;
import zabbix.api.domain.hostgroup.HostGroupCreateRequest;
import zabbix.api.domain.hostgroup.HostGroupDeleteRequest;
import zabbix.api.domain.hostgroup.HostGroupGetRequest;
import zabbix.api.domain.usergroup.UserGroupCreateRequest;
import zabbix.api.domain.usergroup.UserGroupDeleteRequest;
import zabbix.api.domain.usergroup.UserGroupExistsRequest;
import zabbix.api.entity.hostinterface.MonitorType;
import zabbix.api.service.IHostService;
import zabbix.api.service.IHostgroupService;
import zabbix.api.service.IUsergroupService;
import zabbix.api.service.impl.HostServiceImpl;
import zabbix.api.service.impl.HostgroupServiceImpl;
import zabbix.api.service.impl.UsergroupServiceImpl;
import zabbix.api.util.Util;

public class AllInOneTest
{
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(AllInOneTest.class);

    public static void main(String[] args)
    {
        HttpClient client = new Util().login();
        
        // hostGroup测试
        IHostgroupService hostgroupService = new HostgroupServiceImpl();
        
        // 数据准备
        HostGroupCreateRequest hostGroupCreate = new HostGroupCreateRequest();
        
        HostGroup hostGroup = new HostGroup();
        hostGroup.setName("test_zabbix_ds_api");
        hostGroupCreate.getParams().add(hostGroup);
        JSONObject rs = (JSONObject) hostgroupService.hostGroupCreate(hostGroupCreate);
        LOG.debug("hostGroup create response:{}", rs.toString());

        // 数据准备
        HostGroupGetRequest hostGroupGet = new HostGroupGetRequest();
        hostGroupGet.getParams().setOutput("extend");
        List<String> groupIdList = new ArrayList<>();
        groupIdList.add("47");
        hostGroupGet.getParams().setGroupids(groupIdList);
        hostgroupService.hostGroupGet(hostGroupGet);
        
//        HostGroupExistsRequest request = new HostGroupExistsRequest();
//        List<String> nameList = new ArrayList<>();
//        List<String> idList = new ArrayList<>();
//        nameList.add("test_zabbix_api");
//        idList.add("33");
//        request.getParams().setName(nameList);
//        request.getParams().setNode("33");
//        request.getParams().setNodeids(idList);
//        Object ret = hostgroupService.hostGroupExists(request);
//        LOG.debug("hostGroup exist response:{}", ret.toString());
        
        // 添加主机到群组数据准备
        IHostService hostService = new HostServiceImpl();
        HostCreateRequest create = new HostCreateRequest();
        create.getParams().setHost(getHostNameForLiunx());
        create.getParams().setName(getHostNameForLiunx());
        hostGroup = new HostGroup();
        hostGroup.setGroupid("47");
        create.getParams().getGroups().add(hostGroup);
        HostInterface hostInterface = new HostInterface();
        hostInterface.setType(MonitorType.JMX.value());
        hostInterface.setIp("1.1.1.1");
        hostInterface.setDns("www.basu.com");
        hostInterface.setUseip(1);
        hostInterface.setMain(1);
        hostInterface.setPort("10050");
        create.getParams().getInterfaces().add(hostInterface);
        create.getParams().setStatus(1);
        hostService.create(create);
        
        // 数据准备
        HostGroupDeleteRequest hostGroupDelete = new HostGroupDeleteRequest();
        hostGroupDelete.getParams().add("18");
        hostgroupService.hostGroupDelete(hostGroupDelete);
        
        String userGroupName = "testGroup15";
        IUsergroupService usergroupService = new UsergroupServiceImpl();
        
        // 数据准备
        UserGroupCreateRequest userGroupCreate = new UserGroupCreateRequest();
        userGroupCreate.getParams().setName(userGroupName);
        Object result = usergroupService.userGroupCreate(userGroupCreate);
        LOG.debug("create response:{}", result.toString());
        
        String groupId = ((JSONObject) result).get("usrgrpids").toString();
        
        // 数据准备
        UserGroupExistsRequest userGroupExists = new UserGroupExistsRequest();
        List<String> ids = new ArrayList<>();
        ids.add(groupId);
        userGroupExists.getParams().setName(ids);
        result = usergroupService.userGroupExists(userGroupExists);
        LOG.debug("exist response:{}", result.toString());
        
        // 数据准备
        UserGroupDeleteRequest userGroupDelete = new UserGroupDeleteRequest();
        userGroupDelete.getParams().add(groupId);
        result = usergroupService.userGroupDelete(userGroupDelete);
        LOG.debug("delete response:{}", result.toString());
        
        
    }
    
    public static String getHostNameForLiunx()
    {
        try
        {
            return (InetAddress.getLocalHost()).getHostName();
        }
        catch (UnknownHostException uhe)
        {
            String host = uhe.getMessage(); // host = "hostname: hostname"  
            if (host != null)
            {
                int colon = host.indexOf(':');  
                if (colon > 0)
                {
                    return host.substring(0, colon);  
                }
            }
            return "UnknownHost";  
        }
    }

}
