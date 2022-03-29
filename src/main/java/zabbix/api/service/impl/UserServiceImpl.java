package zabbix.api.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import zabbix.api.domain.base.Media;
import zabbix.api.domain.base.User;
import zabbix.api.domain.base.UserGroup;
import zabbix.api.domain.user.UserAddmediaRequest;
import zabbix.api.domain.user.UserAuthenticateRequest;
import zabbix.api.domain.user.UserCreateRequest;
import zabbix.api.domain.user.UserCreateRequest.UserCreateParams;
import zabbix.api.domain.user.UserDeleteMediaRequest;
import zabbix.api.domain.user.UserDeleteRequest;
import zabbix.api.domain.user.UserGetRequest;
import zabbix.api.domain.user.UserIsreadableRequest;
import zabbix.api.domain.user.UserIswritableRequest;
import zabbix.api.domain.user.UserLoginRequest;
import zabbix.api.domain.user.UserLogoutRequest;
import zabbix.api.domain.user.UserUpdateProfileRequest;
import zabbix.api.domain.user.UserUpdateRequest;
import zabbix.api.domain.user.UserUpdateRequest.UserUpdateParams;
import zabbix.api.domain.user.UserUpdatemediaRequest;
import zabbix.api.service.IUserService;
import zabbix.api.util.HttpExecuter;

/**
 * @ClassName: UserServiceImpl
 * @Description: 用户接口实现
 * @author zhaohb
 */
public class UserServiceImpl implements IUserService
{
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    
    public Object addmedia(UserAddmediaRequest addmedia)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(addmedia);
            LOG.debug("user add media response:{}", respObj);
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
            LOG.info("user add media catch exception:{}", e);
            return null;
        }
        return result;
    }
    public Object authenticate(UserAuthenticateRequest authenticate)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(authenticate);
            LOG.debug("user authenticate response:{}", respObj);
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
            LOG.info("user authenticate catch exception:{}", e);
            return null;
        }
        return result;
    }
    
    public boolean addUser(User user)
    {
        boolean result = false;
        String alias = user.getAlias();
        String name = user.getName();
        String password = user.getPasswd();
        List<UserGroup> grouplist = user.getGroups();
        List<Media> medias = user.getMideas();
        UserCreateRequest createRequest = new UserCreateRequest();
        UserCreateParams params = createRequest.getParams();
        if (grouplist!= null&&grouplist.size()>0)
        {
            if (password != null && !"".equals(password) && alias!= null && !"".equals(alias))
            {
                params.setAlias(alias);
                params.setPasswd(password);
                params.setName(name);
                params.setUsrgrps(grouplist);
                params.setUser_medias(medias);
                JSONObject  object = (JSONObject) create(createRequest);
                if (object.has("result"))
                {
                    result = true;
                }
                else if (object.has("error"))
                {
                    result = false;
                }
            }
        }
        return result;
    }
    
    public Object create(UserCreateRequest create)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(create);
            LOG.debug("create user response:{}", respObj);
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
            LOG.info("create user catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    
    
    public Object deleteMedia(UserDeleteMediaRequest deleteMedia)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(deleteMedia);
            LOG.debug("delete user media response:{}", respObj);
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
            LOG.info("delete user media catch exception:{}", e);
            return null;
        }
        return result;
    }
    
    public boolean deleteUser(User user)
    {
        boolean result = false;
        if (user != null && user.getUserid() != null && !"".equals(user.getUserid()))
        {
            UserDeleteRequest delete = new UserDeleteRequest();
            delete.getParams().add(user);
            JSONObject object = (JSONObject)delete(delete);
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
    
    public Object delete(UserDeleteRequest delete)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(delete);
            LOG.debug("delete user response:{}", respObj);
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
            LOG.info("delete user catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    
    public Object isreadable(UserIsreadableRequest isreadable)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(isreadable);
            LOG.debug("is user readable response:{}", respObj);
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
            LOG.info("is user readable response:{}", e);
            return null;
        }
        return result;
    }
    
    public Object iswritable(UserIswritableRequest iswritable)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(iswritable);
            LOG.debug("is user writable response:{}", respObj);
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
            LOG.info("is user writable catch exception:{}", e);
            return null;
        }
        return result;
    }
    
    /*
     * Title: get
     * Description: 获得设备信息（json）
     * @param get
     * @return Object
     * @see zabbix.api.service.IUserService#get(zabbix.api.domain.user.UserGetRequest)
     */
    @Override
    public Object get(UserGetRequest get)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(get);
            LOG.debug("get user info response:{}", respObj);
        }
        catch (Exception e)
        {
            LOG.info("get user info catch exception:{}", e);
            return null;
        }
        return respObj;
    }

    public List<User> getUser(User user)
    {
        UserGetRequest get = new UserGetRequest();
        get.getParams().setOutput("extend");
        get.getParams().setSelectMedias("extend");
        List<String> userids = new ArrayList<String>();
        userids.add(user.getUserid());
        get.getParams().setUserids(userids);
//        get.getParams().setSelectMediatypes("extend");
        get.getParams().setSelectUsrgrps("extend");
        List<User> users = getUserBean(get);
        return users;
    }
    
    /*
     * Title: getUserBean
     * Description: 获取用户信息（bean）
     * @param get
     * @return
     * @see zabbix.api.service.IUserService#getUserBean(zabbix.api.domain.user.UserGetRequest)
     */
    @Override
    public List<User> getUserBean(UserGetRequest get)
    {
        JSONObject result = (JSONObject) this.get(get);
        List<User> users =new ArrayList<User>();
        if (result.has("result"))
        {
            try
            {
                JSONArray array = result.getJSONArray("result");
                if (array!= null&&array.length()>0)
                {
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject userjson = array.getJSONObject(i);
                        User user =new User(); 
                        user.setAlias(userjson.getString("alias"));
                        user.setAttempt_clock(userjson.getString("attempt_clock"));
                        user.setAttempt_failed(userjson.getInt("attempt_failed"));
                        user.setAttempt_ip(userjson.getString("attempt_ip"));
                        user.setAutologin(userjson.getInt("autologin"));
                        user.setAutologout(userjson.getInt("autologout"));
                        user.setLang(userjson.getString("lang"));
                        user.setName(userjson.getString("name"));
                        if (userjson.has("passwd"))
                        {
                            user.setPasswd(userjson.getString("passwd"));
                        }
                        user.setRefresh(userjson.getInt("refresh"));
                        user.setRows_per_page(userjson.getInt("rows_per_page"));
                        user.setSurname(userjson.getString("surname"));
                        user.setTheme(userjson.getString("theme"));
                        user.setType(userjson.getInt("type"));
                        user.setUrl(userjson.getString("url"));
                        user.setUserid(userjson.getString("userid"));
                        if (userjson.has("medias"))
                        {
                            JSONArray medias = userjson.getJSONArray("medias");
                            List<Media> list = new ArrayList<Media>();
                            for (int j = 0; j < medias.length(); j++)
                            {
                                JSONObject object = (JSONObject) medias.get(i);
                                Media media = new Media();
                                media.setActive(object.getString("active"));
                                media.setMediaid(object.getString("mediaid"));
                                media.setMediatypeid(object.getString("mediatypeid"));
                                media.setPeriod(object.getString("period"));
                                media.setSendto(object.getString("sendto"));
                                media.setSeverity(object.getString("severity"));
                                list.add(media);
                            }
                            user.setMideas(list);
                        }
                        if (userjson.has("usrgrps"))
                        {
                            JSONArray usrgrps = userjson.getJSONArray("usrgrps");
                            List<UserGroup> list = new ArrayList<UserGroup>();
                            for (int j = 0; j < usrgrps.length(); j++)
                            {
                                JSONObject object = (JSONObject) usrgrps.get(i);
                                UserGroup group = new UserGroup();
                                group.setUsrgrpid(object.getString("usrgrpid"));
                                group.setUsers_status(object.getInt("users_status"));
                                group.setName(object.getString("name"));
                                group.setGui_access(object.getInt("gui_access"));
                                group.setDebug_mode(object.getInt("debug_mode"));
                                list.add(group);
                            }
                            user.setGroups(list);
                        }
                        users.add(user);
                    }
                }
            }
            catch (Exception e)
            {
                LOG.info("params user info to beans catch exception:{}", e);
                return users;
            }
        }
        else if (result.has("error"))
        {
            return users;
        }
        return users;
    }
    
    /*
     * Title: login
     * Description: 用户登录
     * @param login
     * @return Object
     * @see zabbix.api.service.IUserService#login(zabbix.api.domain.user.UserLoginRequest)
     */
    @Override
    public Object login(UserLoginRequest login)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(login);
            LOG.debug("user login response:{}", respObj);
        }
        catch (Exception e)
        {
            LOG.info("user login catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    
    /*
     * Title: logout
     * Description: 用户退出
     * @param logout
     * @return Object
     * @see zabbix.api.service.IUserService#logout(zabbix.api.domain.user.UserLogoutRequest)
     */
    @Override
    public Object logout(UserLogoutRequest logout)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(logout);
            LOG.debug("user logout response:{}", respObj);
        }
        catch (Exception e)
        {
            LOG.info("user logout catch exception:{}", e);
            return null;
        }
        return respObj;
    }

    public Object updatemedia(UserUpdatemediaRequest updatemedia)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(updatemedia);
            LOG.debug("update user media response:{}", respObj);
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
            LOG.info("update user media catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    
    public Object updateProfile(UserUpdateProfileRequest updateProfile)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(updateProfile);
            LOG.debug("update user profile response:{}", respObj);
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
            LOG.info("update user profile catch exception:{}", e);
            return null;
        }
        return result;
    }
    
    public boolean updateUser(User user)
    {
        boolean result = false;
        String alias = user.getAlias();
        String name = user.getName();
        String password = user.getPasswd();
        String userid = user.getUserid();
        List<UserGroup> groups = user.getGroups();
        List<String> grouplist = new ArrayList<String>();
        if (groups!= null&&groups.size()>0)
        {
            for (int i = 0; i < groups.size(); i++)
            {
                grouplist.add(groups.get(i).getUsrgrpid()+"");
            }
            List<Media> medias = user.getMideas();
            List<Media> addmedias = new ArrayList<Media>();
            List<Media> updatemedias = new ArrayList<Media>();
            UserUpdateRequest updateRequest = new UserUpdateRequest();
            UserUpdateParams params = updateRequest.getParams();
            if (grouplist!= null&&grouplist.size()>0)
            {
                if (userid != null && !"".equals(userid) && password!= null && !"".equals(password) && alias!= null && !"".equals(alias))
                {
                    params.setUserid(userid);
                    params.setAlias(alias);
                    params.setPasswd(password);
                    params.setName(name);
                    params.setUsrgrps(grouplist);
//                    params.setMideas(medias);
                    UserUpdatemediaRequest updatemedia = new UserUpdatemediaRequest();
                    UserAddmediaRequest addmedia =  new UserAddmediaRequest();
                    if (medias!= null&&medias.size()>0)
                    {
                        User u = new User();
                        u.setUserid(userid);
                        for (int i = 0; i < medias.size(); i++)
                        {
                            Media media = medias.get(i);
                            if (media.getMediaid()!= null&&!"".equals(media.getMediaid()))
                            {
                                updatemedias.add(media);
                            }
                            else
                            {
                                addmedias.add(media);
                            }
                        }
                        if (updatemedias.size()>0)
                        {
                            updatemedia.getParams().getMedias().addAll(updatemedias);
                            updatemedia.getParams().getUsers().add(u);
                            JSONObject jsonObject = (JSONObject) updatemedia(updatemedia);
                        }
                        if (addmedias.size()>0)
                        {
                            addmedia.getParams().getMedias().addAll(addmedias);
                            addmedia.getParams().getUsers().add(u);
                            JSONObject jsonObject1 =  (JSONObject) addmedia(addmedia);
                        }
                    }
                    JSONObject  object = (JSONObject) update(updateRequest);
                    if (object.has("result"))
                    {
                        result = true;
                    }
                    else if (object.has("error"))
                    {
                        result = false;
                    }
                }
            }
        }
        return result;
    }
    
    public Object update(UserUpdateRequest update)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(update);
            LOG.debug("update user response:{}", respObj);
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
            LOG.info("update user catch exception:{}", e);
            return null;
        }
        return result;
    }
}
