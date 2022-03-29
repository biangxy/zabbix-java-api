package zabbix.api.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import zabbix.api.domain.base.Host;
import zabbix.api.domain.base.HostGroup;
import zabbix.api.domain.base.HostInterface;
import zabbix.api.domain.base.Maintenance;
import zabbix.api.domain.base.Template;
import zabbix.api.domain.host.HostCreateRequest;
import zabbix.api.domain.host.HostDeleteRequest;
import zabbix.api.domain.host.HostExistsRequest;
import zabbix.api.domain.host.HostGetRequest;
import zabbix.api.domain.host.HostGetobjectsRequest;
import zabbix.api.domain.host.HostIsreadableRequest;
import zabbix.api.domain.host.HostIswritableRequest;
import zabbix.api.domain.host.HostMassaddRequest;
import zabbix.api.domain.host.HostMassremoveRequest;
import zabbix.api.domain.host.HostMassupdateRequest;
import zabbix.api.domain.host.HostUpdateRequest;
import zabbix.api.service.IHostService;
import zabbix.api.util.HttpExecuter;

/**
 * @ClassName: HostServiceImpl
 * @Description: 设备接口实现
 * @author zhaohb
 */

public class HostServiceImpl implements IHostService
{
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(HostServiceImpl.class);
    
    public Object create(HostCreateRequest create)
    {
        Object result = null;
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(create);
            LOG.debug("host create response:{}", respObj);
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
            LOG.info("host create catch exception:{}", e);
            return null;
        }
        return result;
    }
    
    
    @Override
    public String createHost(Host host, HostInterface hostInterface, HostGroup hostgroup, Template template)
    {
//        String result = "";
        HostCreateRequest create = new HostCreateRequest();
        if (host!=null)
        {
            if (host.getHost()!=null&&!"".equals(host.getHost()))
            {
                create.getParams().setHost(host.getHost());
            }
            if (host.getAvailable()!=null)
            {
                create.getParams().setAvailable(host.getAvailable());
            }
            if (host.getDisable_until()!=null&&!"".equals(host.getDisable_until()))
            {
                create.getParams().setDisable_until(host.getDisable_until());
            }
            if (host.getError()!=null)
            {
                create.getParams().setError(host.getError());
            }
            if (host.getErrors_from()!=null&&!"".equals(host.getErrors_from()))
            {
                create.getParams().setErrors_from(host.getErrors_from());
            }
            if (host.getIpmi_authtype()!=null)
            {
                create.getParams().setIpmi_authtype(host.getIpmi_authtype());
            }
            if (host.getIpmi_available()!=null)
            {
                create.getParams().setIpmi_available(host.getIpmi_available());
            }
            if (host.getIpmi_disable_until()!=null&&!"".equals(host.getIpmi_disable_until()))
            {
                create.getParams().setIpmi_disable_until(host.getIpmi_disable_until());
            }
            if (host.getIpmi_error()!=null&&!"".equals(host.getIpmi_error()))
            {
                create.getParams().setIpmi_error(host.getIpmi_error());
            }
            if (host.getIpmi_errors_from()!=null&&!"".equals(host.getIpmi_errors_from()))
            {
                create.getParams().setIpmi_errors_from(host.getIpmi_errors_from());
            }
            if (host.getIpmi_password()!=null&&!"".equals(host.getIpmi_password()))
            {
                create.getParams().setIpmi_password(host.getIpmi_password());
            }
            if (host.getIpmi_privilege()!=null)
            {
                create.getParams().setIpmi_privilege(host.getIpmi_privilege());
            }
            if (host.getIpmi_username()!=null&&!"".equals(host.getIpmi_username()))
            {
                create.getParams().setIpmi_username(host.getIpmi_username());
            }
            if (host.getJmx_available()!=null)
            {
                create.getParams().setJmx_available(host.getJmx_available());
            }
            if (host.getJmx_disable_until()!=null&&!"".equals(host.getJmx_disable_until()))
            {
                create.getParams().setJmx_disable_until(host.getJmx_disable_until());
            }
            if (host.getJmx_error()!=null&&!"".equals(host.getJmx_error()))
            {
                create.getParams().setJmx_error(host.getJmx_error());
            }
            if (host.getJmx_errors_from()!=null&&!"".equals(host.getJmx_errors_from()))
            {
                create.getParams().setJmx_errors_from(host.getJmx_errors_from());
            }
            if (host.getMaintenance_from()!=null&&!"".equals(host.getMaintenance_from()))
            {
                create.getParams().setMaintenance_from(host.getMaintenance_from());
            }
            if (host.getMaintenance_status()!=null)
            {
                create.getParams().setMaintenance_status(host.getMaintenance_status());
            }
            if (host.getMaintenance_type()!=null)
            {
                create.getParams().setMaintenance_type(host.getMaintenance_type());
            }
            if (host.getMaintenanceid()!=null&&!"".equals(host.getMaintenanceid()))
            {
                create.getParams().setMaintenanceid(host.getMaintenanceid());
            }
            if (host.getName()!=null&&!"".equals(host.getName()))
            {
                create.getParams().setName(host.getName());
            }
            if (host.getProxy_hostid()!=null&&!"".equals(host.getProxy_hostid()))
            {
                create.getParams().setProxy_hostid(host.getProxy_hostid());
            }
            if (host.getSnmp_available()!=null)
            {
                create.getParams().setSnmp_available(host.getSnmp_available());
            }
            if (host.getSnmp_disable_until()!=null&&!"".equals(host.getSnmp_disable_until()))
            {
                create.getParams().setSnmp_disable_until(host.getSnmp_disable_until());
            }
            if (host.getSnmp_error()!=null&&!"".equals(host.getSnmp_error()))
            {
                create.getParams().setSnmp_error(host.getSnmp_error());
            }
            if (host.getSnmp_errors_from()!=null&&!"".equals(host.getSnmp_errors_from()))
            {
                create.getParams().setSnmp_errors_from(host.getSnmp_errors_from());
            }
            if (host.getStatus()!=null&&!"".equals(host.getStatus()))
            {
                create.getParams().setStatus(host.getStatus());
            }
            if (hostInterface!=null&&hostInterface.getIp()!=null&&!"".equals(hostInterface.getIp()))
            {
                HostInterface hif = new HostInterface();
                if (hostInterface.getType()!=null)
                {
                    hif.setType(hostInterface.getType());
                }
                    hif.setIp(hostInterface.getIp());
                if (hostInterface.getDns()!=null)
                {
                    hif.setDns(hostInterface.getDns());
                }
                if (hostInterface.getUseip()!=null)
                {
                    hif.setUseip(hostInterface.getUseip());
                }
                if (hostInterface.getMain()!=null)
                {
                    hif.setMain(hostInterface.getMain());
                }
                if (hostInterface.getPort()!=null&&!"".equals(hostInterface.getPort()))
                {
                    hif.setPort(hostInterface.getPort());
                }
                create.getParams().getInterfaces().add(hif);
            }
            else
            {
                return "Error,HostInterface is required";
            }
            if (hostgroup!=null)
            {
                HostGroup group = new HostGroup();
                if (hostgroup.getGroupid()!=null&&!"".equals(hostgroup.getGroupid()))
                {
                    group.setGroupid(hostgroup.getGroupid());
                }
                if (hostgroup.getInternal()!=null)
                {
                    group.setInternal(hostgroup.getInternal());
                }
                if (hostgroup.getName()!=null&&!"".equals(hostgroup.getName()))
                {
                    group.setName(hostgroup.getName());
                }
                create.getParams().getGroups().add(group);
            }
            else
            {
                return "Error,HostGroup is required";
            }
            if (template!=null)
            {
                Template mytemplate = new Template();
                if (template.getTemplateid()!=null&&!"".equals(template.getTemplateid()))
                {
                    mytemplate.setTemplateid(template.getTemplateid());
                }
                create.getParams().getTemplates().add(mytemplate);
            }
            else
            {
                return "Error,Template is required";
            }
            JSONObject object = (JSONObject) create(create);
            if (object.has("result"))
            {
                return "success";
            }
            else
            {
                try
                {
                    return "Error,"+object.getString("error");
                }
                catch (Exception e)
                {
                    LOG.info("create host catch exception:{}", e);
                    return "error, " + e.getMessage();
                }
            }
//            return "success";
        }
        else
        {
            return "Error,host is required";
        }
    }
    
    public Object delete(HostDeleteRequest delete)
    {
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(delete);
            LOG.debug("delete host response:{}", respObj);
            if (respObj!=null && !respObj.equals(""))
            {
//                Object result = null;
//                if (respObj.has("result")) {
//                    result = respObj.get("result");
//                }
//                 else if (respObj.has("error")) {
//                    result = respObj.get("error");
//                }
            }
            else
            {
                respObj = new JSONObject();
                respObj.put("errorInfoByMyself", "moreItemAndNotDelte");        
            }
            return respObj;
        }
        catch (Exception e)
        {
            LOG.info("delete host catch exception:{}", e);
            return null;
        }
    }
    public Object exists(HostExistsRequest exists)
    {
        Object result = null;
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(exists);
            LOG.debug("host exist response:{}", respObj);
            System.out.println(respObj);
            if (respObj.has("result"))
            {
                result = respObj.get("result");
            }
             else if (respObj.has("error")) {
                result = respObj.get("error");
            }
        }
         catch (Exception e)
        {
            LOG.info("host exist catch exception:{}", e);
            return null;
        }
        return result;
    }
    public Object getobjects(HostGetobjectsRequest getobjects)
    {
        Object result = null;
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(getobjects);
            LOG.debug("get host object response:{}", respObj);
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
            LOG.info("get host object catch exception:{}", e);
            return null;
        }
        return result;
    }
    
    /*
     * Title: get
     * Description: 获得设备信息（json）
     * @param get
     * @return Object
     * @see zabbix.api.service.IHostService#get(zabbix.api.domain.host.HostGetRequest)
     */
    public Object get(HostGetRequest get)
    {
        Object result = null;
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(get);
            LOG.debug("get host response:{}", respObj);
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
            LOG.info("get host catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    
    /*
     * Title: getHostToBean
     * Description: 获得设备信息（bean）
     * @param get
     * @return List<Host>
     * @see zabbix.api.service.IHostService#getHostToBean(zabbix.api.domain.host.HostGetRequest)
     */
    @Override
    public List<Host> getHostToBean(HostGetRequest get)
    {
        List<Host> hosts =null;
        JSONObject result= (JSONObject) this.get(get);
        if (result.has("result"))
        {
            try
            {
                JSONArray array = result.getJSONArray("result");
                if (array!=null&&array.length()>0)
                {
                    hosts =new ArrayList<Host>();
                    for (int i = 0; i<array.length();i++)
                    {
                        JSONObject jsonObject = array.getJSONObject(i);
                        Host host = new Host();
                        host.setHostid(jsonObject.getString("hostid"));
                        host.setHost(jsonObject.getString("host"));
                        host.setName(jsonObject.getString("name"));
                        host.setStatus(jsonObject.getInt("status"));
                        host.setAvailable(jsonObject.getInt("available"));
                        host.setDisable_until(jsonObject.getString("disable_until"));
                        host.setError(jsonObject.getString("error"));
                        host.setErrors_from(jsonObject.getString("errors_from"));
                        host.setIpmi_authtype(jsonObject.getInt("ipmi_authtype"));
                        host.setIpmi_available(jsonObject.getInt("ipmi_available"));
                        host.setIpmi_disable_until(jsonObject.getString("ipmi_disable_until"));
                        host.setIpmi_error(jsonObject.getString("ipmi_error"));
                        host.setIpmi_errors_from(jsonObject.getString("ipmi_errors_from"));
                        host.setIpmi_password(jsonObject.getString("ipmi_password"));
                        host.setIpmi_privilege(jsonObject.getInt("ipmi_privilege"));
                        host.setIpmi_username(jsonObject.getString("ipmi_username"));
                        host.setJmx_available(jsonObject.getInt("jmx_available"));
                        host.setJmx_disable_until(jsonObject.getString("jmx_disable_until"));
                        host.setJmx_error(jsonObject.getString("jmx_error"));
                        host.setJmx_errors_from(jsonObject.getString("jmx_errors_from"));
                        host.setMaintenance_from(jsonObject.getString("maintenance_from"));
                        host.setMaintenance_status(jsonObject.getInt("maintenance_status"));
                        host.setMaintenance_type(jsonObject.getInt("maintenance_type"));
                        host.setMaintenanceid(jsonObject.getString("maintenanceid"));
                        JSONArray maintenances = jsonObject.getJSONArray("maintenances");
                        List<Maintenance> maintenanceslist = new ArrayList<Maintenance>();
                        if (maintenances!=null&&maintenances.length()>0)
                        {
                            for (int j=0;j<maintenances.length();j++)
                            {
                                JSONObject mainjson = maintenances.getJSONObject(j);
                                Maintenance maintenance = new Maintenance();
                                maintenance.setActive_since(mainjson.getString("active_since"));
                                maintenance.setActive_till(mainjson.getString("active_till"));
                                maintenance.setDescription(mainjson.getString("description"));
                                maintenance.setMaintenance_type(mainjson.getInt("maintenance_type"));
                                maintenance.setMaintenanceid(mainjson.getString("maintenanceid"));
                                maintenance.setName(mainjson.getString("name"));
                                maintenanceslist.add(maintenance);
                            }
                        }
                        host.setMaintenances(maintenanceslist);
                        host.setProxy_hostid(jsonObject.getString("proxy_hostid"));
                        host.setSnmp_available(jsonObject.getInt("snmp_available"));
                        host.setSnmp_disable_until(jsonObject.getString("snmp_disable_until"));
                        host.setSnmp_error(jsonObject.getString("snmp_error"));
                        host.setSnmp_errors_from(jsonObject.getString("snmp_errors_from"));
                        hosts.add(host);
                    }
                }
            }
            catch (Exception e)
            {
                LOG.info("params hostObject to beans catch exception:{}", e);
                return null;
            }
            return hosts;
        }
        else if (result.has("error"))
        {
            return null;
        }
        return hosts;
    }
    
    public Object isreadable(HostIsreadableRequest isreadable)
    {
        Object result = null;
        JSONObject respObj =null;
        try {
            respObj = HttpExecuter.post(isreadable);
            LOG.debug("host isreadable response:{}", respObj);
            System.out.println(respObj);
            if (respObj.has("result")) {
                result = respObj.get("result");
            }
             else if (respObj.has("error")) {
                result = respObj.get("error");
            }
        }
        catch (Exception e)
        {
            LOG.info("host isreadable catch exception:{}", e);
            return null;
        }
        return result;
    }
    public Object iswritable(HostIswritableRequest iswritable)
    {
        Object result = null;
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(iswritable);
            LOG.debug("host iswritable response:{}", respObj);
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
            LOG.info("host iswritable catch exception:{}", e);
            return null;
        }
        return result;
    }

    public Object massadd(HostMassaddRequest massadd)
    {
        Object result = null;
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(massadd);
            LOG.debug("host mass add response:{}", respObj);
            System.out.println(respObj);
            if (respObj.has("result")) {
                result = respObj.get("result");
            }
             else if (respObj.has("error")) {
                result = respObj.get("error");
            }
        }
        catch (Exception e)
        {
            LOG.info("host mass add catch exception:{}", e);
            return null;
        }
        return result;
    }

    public Object massremove(HostMassremoveRequest massremove)
    {
        Object result = null;
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(massremove);
            LOG.debug("host mass remove response:{}", respObj);
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
            LOG.info("host mass remove catch exception:{}", e);
            return null;
        }
        return result;
    }

    public Object massupdate(HostMassupdateRequest massupdate)
    {
        Object result = null;
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(massupdate);
            LOG.debug("host mass update response:{}", respObj);
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
            LOG.info("host mass update catch exception:{}", e);
            return null;
        }
        return result;
    }

    public Object update(HostUpdateRequest update)
    {
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(update);
            LOG.debug("host update response:{}", respObj);
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
            LOG.info("host update catch exception:{}", e);
            return null;
        }
        return respObj;
    }

}
