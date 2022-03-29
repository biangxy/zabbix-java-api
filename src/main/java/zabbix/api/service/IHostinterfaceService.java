package zabbix.api.service;
import java.util.List;

import zabbix.api.domain.base.HostInterface;
import zabbix.api.domain.hostinterface.HostInterfaceGetRequest;
/**
 * @ClassName: IHostinterfaceService
 * @Description: 设备接口接口（该接口可以获得设备ip,port,dns等信息）
 */
public interface IHostinterfaceService
{
    /**
     * @Title: hostInterfaceGet
     * @Description: 获取接口信息（json）
     * @param hostInterfaceGet
     * @return Object
     * @throws
     */
    public Object hostInterfaceGet(HostInterfaceGetRequest hostInterfaceGet);
    /**
     * 
     * @Title: getHostInterfaceBean
     * @Description:  获取接口信息（bean）
     * @param @param hostInterfaceGet
     * @param @return  
     * @return List<HostInterface>
     * @throws
     */
    public List<HostInterface> getHostInterfaceBean(HostInterfaceGetRequest hostInterfaceGet);
}
