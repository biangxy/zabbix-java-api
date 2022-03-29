package zabbix.api.service.impl;
import java.util.ArrayList;
import java.util.List;




import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zabbix.api.domain.base.User;
import zabbix.api.domain.base.UserGroup;
import zabbix.api.domain.usergroup.UserGroupCreateRequest;
import zabbix.api.domain.usergroup.UserGroupDeleteRequest;
import zabbix.api.domain.usergroup.UserGroupExistsRequest;
import zabbix.api.domain.usergroup.UserGroupGetRequest;
import zabbix.api.domain.usergroup.UserGroupGetobjectsRequest;
import zabbix.api.domain.usergroup.UserGroupIsreadableRequest;
import zabbix.api.domain.usergroup.UserGroupIswritableRequest;
import zabbix.api.domain.usergroup.UserGroupMassaddRequest;
import zabbix.api.domain.usergroup.UserGroupMassupdateRequest;
import zabbix.api.domain.usergroup.UserGroupUpdateRequest;
import zabbix.api.service.IUsergroupService;
import zabbix.api.util.HttpExecuter;

/**
 * userGroup功能实现
 * @author zhaohb
 *
 */
public class UsergroupServiceImpl implements IUsergroupService
{
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(UsergroupServiceImpl.class);
    
    public Object userGroupCreate(UserGroupCreateRequest userGroupCreate)
    {
        Object result = null;
        try
        {
            JSONObject respObj = null;
            respObj = HttpExecuter.post(userGroupCreate);
            LOG.debug("create user group response:{}", respObj);
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
            LOG.info("create user group catch exception:{}", e);
            return null;
        }
        return result;
    }
    
    public boolean addUserGroup(UserGroup group)
    {
        boolean result = false;
        UserGroupCreateRequest userGroupCreate = new UserGroupCreateRequest();
        userGroupCreate.getParams().setName(group.getName());
        JSONObject jsonObject = (JSONObject) userGroupCreate(userGroupCreate);
        if (jsonObject.has("result"))
        {
            result = true;
        }
        else if (jsonObject.has("error"))
        {
            result = false;
        }
        return result;
    }
    
    public Object userGroupDelete(UserGroupDeleteRequest userGroupDelete)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(userGroupDelete);
            LOG.debug("delete user group response:{}", respObj);
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
            LOG.info("delete user group catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    
    public boolean deleteUserGroup(UserGroup group)
    {
        boolean result = false;
        if (group != null && group.getUsrgrpid() != null&& !"".equals(group.getUsrgrpid()))
        {
            // 数据准备
            UserGroupDeleteRequest userGroupDelete = new UserGroupDeleteRequest();
            userGroupDelete.getParams().add(group.getUsrgrpid());
            JSONObject object = (JSONObject) userGroupDelete(userGroupDelete);
            if (object.has("result"))
            {
                result = true;
            }
            else if (object.has("error"))
            {
                 result = false;
            }
        }
        return result;
    }
    
    public Object userGroupExists(UserGroupExistsRequest userGroupExists)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(userGroupExists);
            LOG.debug("user group exist response:{}", respObj);
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
            LOG.info("user group exist catch exception:{}", e);
            return null;
        }
        return result;
    }
    public Object userGroupGetobjects(UserGroupGetobjectsRequest userGroupGetobjects)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(userGroupGetobjects);
            LOG.debug("get user group object response:{}", respObj);
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
            LOG.info("get user group object catch expception:{}", e);
            return null;
        }
        return result;
    }
    
    public Object userGroupGet(UserGroupGetRequest userGroupGet)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(userGroupGet);
            LOG.debug("get user group response:{}", respObj);
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
            LOG.info("get user group catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    
    public Object userGroupIsreadable(UserGroupIsreadableRequest userGroupIsreadable)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(userGroupIsreadable);
            LOG.debug("is user group readable response:{}", respObj);
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
            LOG.info("is user group readable catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    
    public Object userGroupIswritable(UserGroupIswritableRequest userGroupIswritable)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(userGroupIswritable);
            LOG.debug("is user group writable response:{}", respObj);
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
            LOG.info("is user group writable catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    public Object userGroupMassadd(UserGroupMassaddRequest userGroupMassadd)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(userGroupMassadd);
            LOG.debug("mass add user group response:{}", respObj);
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
            LOG.info("mass add user group catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    
    public Object userGroupMassupdate(UserGroupMassupdateRequest userGroupMassupdate)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(userGroupMassupdate);
            LOG.debug("mass update user group response:{}", respObj);
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
            LOG.info("mass update user group catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    
    public Object userGroupUpdate(UserGroupUpdateRequest userGroupUpdate)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(userGroupUpdate);
            LOG.debug("update user group response:{}", respObj);
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
            LOG.info("update user group catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    
    public boolean updateUserGroup(UserGroup group)
    {
        boolean result = false;
        // 数据准备
        UserGroupUpdateRequest userGroupUpdate = new UserGroupUpdateRequest();
        userGroupUpdate.getParams().setUsrgrpid("30");
        userGroupUpdate.getParams().setName("testGroup_test");
//        List<Permission> rights = new ArrayList<Permission>();
//        Permission permission = new Permission();
//        permission.setId("16");
//        permission.setPermission(3);
//        rights.add(permission);
//        permission = new Permission();
//        permission.setId("15");
//        permission.setPermission(3);
//        rights.add(permission);
//        userGroupUpdate.getParams().setRights(rights);
        List<String> ids = new ArrayList<String>();
        List<User> users = group.getUsers();
        if (users!= null&&users.size()>0)
        {
            for (User user : users)
            {
                ids.add(user.getUserid());
            }
        }
        userGroupUpdate.getParams().setUserids(ids);
        JSONObject object = (JSONObject) userGroupUpdate(userGroupUpdate);
        if (object.has("result"))
        {
            result = true;
        }
        else if (object.has("error"))
        {
            result = false;
        }
        return result;
    }
}
