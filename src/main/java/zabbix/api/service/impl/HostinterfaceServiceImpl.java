package zabbix.api.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zabbix.api.domain.base.HostInterface;
import zabbix.api.domain.hostinterface.HostInterfaceGetRequest;
import zabbix.api.service.IHostinterfaceService;
import zabbix.api.util.HttpExecuter;
/**
 * @ClassName: HostinterfaceServiceImpl
 * @Description: 设备接口接口实现
 * @author zhaohb
 */
public class HostinterfaceServiceImpl implements IHostinterfaceService 
{
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(HostinterfaceServiceImpl.class);
    /*
     * Title: hostInterfaceGet
     * Description: 获得设备接口信息（json）
     * @param hostInterfaceGet
     * @return Object
     * @see zabbix.api.service.IHostinterfaceService#hostInterfaceGet(zabbix.api.domain.hostinterface.HostInterfaceGetRequest)
     */
    public Object hostInterfaceGet(HostInterfaceGetRequest hostInterfaceGet)
    {
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(hostInterfaceGet);
            LOG.debug("get host interface response:{}", respObj);
        }
         catch (Exception e)
        {
             LOG.info("get host interface catch exception:{}", e);
             return null;
        }
        return respObj;
    }
    
    /*
     * Title: getHostInterfaceBean
     * Description:  获得设备接口信息（bean）
     * @param hostInterfaceGet
     * @return List<HostInterface>
     * @see zabbix.api.service.IHostinterfaceService#getHostInterfaceBean(zabbix.api.domain.hostinterface.HostInterfaceGetRequest)
     */
    @Override
    public List<HostInterface> getHostInterfaceBean(HostInterfaceGetRequest hostInterfaceGet)
    {
        List<HostInterface> interfaces = null;
        JSONObject result = (JSONObject) this.hostInterfaceGet(hostInterfaceGet);
        if (result!=null)
        {
            if (result.has("result"))
            {
                try
                {
                    JSONArray array = result.getJSONArray("result");
                    if (array!=null&&array.length()>0)
                    {
                        interfaces = new ArrayList<HostInterface>();
                        for (int i=0;i<array.length();i++)
                        {
                            HostInterface hostInterface = new HostInterface();
                            JSONObject object = array.getJSONObject(i);
                            hostInterface.setDns(object.getString("dns"));
                            hostInterface.setHostid(object.getString("hostid"));
                            hostInterface.setInterfaceid(object.getString("interfaceid"));
                            hostInterface.setIp(object.getString("ip"));
                            hostInterface.setMain(object.getInt("main"));
                            hostInterface.setPort(object.getString("port"));
                            hostInterface.setType(object.getInt("type"));
                            hostInterface.setUseip(object.getInt("useip"));
                            interfaces.add(hostInterface);
                        }
                    }
                }
                catch (Exception e)
                {
                    LOG.info("params host interface to beans catch exception:{}", e);
                    return null;
                }
             return interfaces;
            }
            else if (result.has("error"))
            {
                return null;
            }
        }
        return interfaces;
    }
}
