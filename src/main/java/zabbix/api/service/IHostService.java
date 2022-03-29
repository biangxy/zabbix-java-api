package zabbix.api.service;
import java.util.List;

import zabbix.api.domain.base.Host;
import zabbix.api.domain.base.HostGroup;
import zabbix.api.domain.base.HostInterface;
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
/**
 * @ClassName: IHostService
 * @Description: 设备接口
 */
public interface IHostService
{
    public Object create(HostCreateRequest create);
    /**
     * @Title: get
     * @Description: 获取设备信息（json）
     * @param get
     * @return Object
     * @throws
     */
    public Object get(HostGetRequest get);
    
    /**
     * @Title: getHostToBean
     * @Description: 获取设备信息（bean）
     * @param get
     * @return List<Host>
     * @throws
     */
    public List<Host> getHostToBean(HostGetRequest get);
    String createHost(Host host, HostInterface hostInterface,
            HostGroup hostgroup, Template template);
    
    public Object delete(HostDeleteRequest delete);
    public Object exists(HostExistsRequest exists);
    public Object getobjects(HostGetobjectsRequest getobjects);
    public Object isreadable(HostIsreadableRequest isreadable);
    public Object iswritable(HostIswritableRequest iswritable);
    public Object massadd(HostMassaddRequest massadd);
    public Object massremove(HostMassremoveRequest massremove);
    public Object massupdate(HostMassupdateRequest massupdate);
    public Object update(HostUpdateRequest update);
    
}
