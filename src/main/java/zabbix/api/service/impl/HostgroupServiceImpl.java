package zabbix.api.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import zabbix.api.domain.base.HostGroup;
import zabbix.api.domain.hostgroup.HostGroupCreateRequest;
import zabbix.api.domain.hostgroup.HostGroupDeleteRequest;
import zabbix.api.domain.hostgroup.HostGroupExistsRequest;
import zabbix.api.domain.hostgroup.HostGroupGetRequest;
import zabbix.api.domain.hostgroup.HostGroupGetobjectsRequest;
import zabbix.api.domain.hostgroup.HostGroupIsreadableRequest;
import zabbix.api.domain.hostgroup.HostGroupIswritableRequest;
import zabbix.api.domain.hostgroup.HostGroupMassaddRequest;
import zabbix.api.domain.hostgroup.HostGroupMassremoveRequest;
import zabbix.api.domain.hostgroup.HostGroupMassupdateRequest;
import zabbix.api.domain.hostgroup.HostGroupUpdateRequest;
import zabbix.api.service.IHostgroupService;
import zabbix.api.util.HttpExecuter;
/**
 * @ClassName: HostgroupServiceImpl
 * @Description: 设备组接口实现
 * @author zhaohb
 */
public class HostgroupServiceImpl implements IHostgroupService
{
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(HostgroupServiceImpl.class);

    public Object hostGroupCreate(HostGroupCreateRequest hostGroupCreate)
    {
        Object result = null;
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(hostGroupCreate);
            LOG.debug("host group create get response:{}", respObj);
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
         catch (Exception e)
        {
            LOG.info("host group create catch exception:{}", e);
            return null;
        }
    }

    public Object hostGroupDelete(HostGroupDeleteRequest hostGroupDelete)
    {
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(hostGroupDelete);
            LOG.debug("host group delete get response:{}", respObj);
//             Object result = null;
//            if (respObj.has("result"))
//             {
//                result = respObj.get("result");
//            }
//             else if (respObj.has("error"))
//             {
//                result = respObj.get("error");
//            }
        }
         catch (Exception e)
        {
            LOG.info("host group delete catch exception:{}", e);
        }
        return respObj;
    }

    public Object hostGroupExists(HostGroupExistsRequest hostGroupExists)
    {
        Object result = null;
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(hostGroupExists);
            LOG.debug("host group delete get response:{}", respObj);
            System.out.println(respObj);
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
         catch (Exception e)
        {
             LOG.info("host group exists catch exception:{}", e);
             return null;
        }
    }

    public Object hostGroupGetobjects(HostGroupGetobjectsRequest hostGroupGetobjects){
        Object result = null;
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(hostGroupGetobjects);
            LOG.debug("host group getObject response:{}", respObj);
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
         catch (Exception e)
        {
            LOG.info("host group getObject catch exception:{}", e);
            return null;
        }
    }
    
    /*
     * Title: hostGroupGet
     * Description: 获得设备组信息（json）
     * @param hostGroupGet
     * @return Object
     * @see zabbix.api.service.IHostgroupService#hostGroupGet(zabbix.api.domain.hostgroup.HostGroupGetRequest)
     */
    public Object hostGroupGet(HostGroupGetRequest hostGroupGet){
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(hostGroupGet);
            LOG.debug("get host group response:{}", respObj);
            if (respObj.has("result"))
            {
                result = respObj.get("result");
            }
             else if (respObj.has("error"))
            {
                result = respObj.get("error");
            }
            return respObj;
        }
         catch (Exception e)
        {
            LOG.info("get host group catch exception:{}", e);
            return null;
        }
    }
    
    /*
     * Title: getHostGroupBean
     * Description: 获得设备组信息（bean）
     * @param hostGroupGet
     * @return List<HostGroup>
     * @see zabbix.api.service.IHostgroupService#getHostGroupBean(zabbix.api.domain.hostgroup.HostGroupGetRequest)
     */
    @Override
    public List<HostGroup> getHostGroupBean(HostGroupGetRequest hostGroupGet)
    {
        JSONObject result = (JSONObject) this.hostGroupGet(hostGroupGet);
        List<HostGroup> hostGroups = null;
        if (result.has("result"))
        {
            try
            {
                JSONArray array = result.getJSONArray("result");
                if (array!=null&&array.length()>0)
                {
                    hostGroups = new ArrayList<HostGroup>();
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject object = array.getJSONObject(i);
                        HostGroup group = new HostGroup();
                        group.setGroupid(object.getString("groupid"));
                        group.setInternal(object.getInt("internal"));
                        group.setName(object.getString("name"));
                        hostGroups.add(group);
                    }
                }
            }
            catch (Exception e)
            {
                LOG.info("params host group to bean catch exception:{}", e);
                return null;
            }
            return hostGroups;
        }
         else if (result.has("error"))
        {
             return null;
        }
        return hostGroups;
    }
    
    public Object hostGroupIsreadable(HostGroupIsreadableRequest hostGroupIsreadable)
    {
        Object result = null;
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(hostGroupIsreadable);
            LOG.debug("host group isreadable response:{}", respObj);
            if (respObj.has("result")) {
                result = respObj.get("result");
            }
             else if (respObj.has("error"))
            {
                result = respObj.get("error");
            }
        }
         catch (Exception e)
        {
            LOG.info("host group isreadable catch exception:{}", e);
            return null;
        }
        return result;
    }

    public Object hostGroupIswritable(HostGroupIswritableRequest hostGroupIswritable)
    {
        Object result = null;
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(hostGroupIswritable);
            System.out.println(respObj);
            LOG.debug("host group iswritable response:{}", respObj);
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
            LOG.info("host group iswritable catch exception:{}", e);
            return null;
        }
        return result;
    }

    public Object hostGroupMassadd(HostGroupMassaddRequest hostGroupMassadd)
    {
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(hostGroupMassadd);
            LOG.debug("host group mass add response:{}", respObj);
//            Object result = null;
//            if (respObj.has("result"))
//            {
//                result = respObj.get("result");
//            }
//             else if (respObj.has("error")) 
//            {
//                result = respObj.get("error");
//            }
        }
        catch (Exception e)
        {
            LOG.info("host group mass add catch exception:{}", e);
            return null;
        }
        return respObj;
    }

    public Object hostGroupMassremove(HostGroupMassremoveRequest hostGroupMassremove)
    {
        Object result = null;
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(hostGroupMassremove);
            LOG.debug("host group mass remove response:{}", respObj);
            System.out.println(respObj);
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
            LOG.info("host group mass remove catch exception:{}", e);
            return null;
        }
        return result;
    }

    public Object hostGroupMassupdate(HostGroupMassupdateRequest hostGroupMassupdate)
    {
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(hostGroupMassupdate);
            LOG.debug("host group mass update response:{}", respObj);
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
            LOG.info("host group mass update catch exception:{}", e);
            return null;
        }
        return respObj;
    }

    public Object hostGroupUpdate(HostGroupUpdateRequest hostGroupUpdate)
    {
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(hostGroupUpdate);
            LOG.debug("host group update response:{}", respObj);
            System.out.println(respObj);
//            Object result = null;
//            if (respObj.has("result"))
//            {
//                result = respObj.get("result");
//            }
//             else if (respObj.has("error"))
//            {
//                result = respObj.get("error");
//            }
        }
        catch (Exception e)
        {
            LOG.info("host group update catch exception:{}", e);
            return null;
        }
        return respObj;
    }
}
