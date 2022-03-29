package zabbix.api.service;
import java.util.List;

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
/**
 * @ClassName: IHostgroupService
 * @Description: 设备组接口
 * @author zhaohb
 */
public interface IHostgroupService
{
    /**
     * 
     * @Title: hostGroupGet
     * @Description: 获取设备组信息（json）
     * @param hostGroupGet
     * @return Object
     * @throws
     */
    public Object hostGroupGet(HostGroupGetRequest hostGroupGet);
    
    /**
     * 
     * @Title: getHostGroupBean
     * @Description: 获取设备组信息（bean）
     * @param hostGroupGet
     * @return List<HostGroup>
     * @throws
     */
    public List<HostGroup> getHostGroupBean(HostGroupGetRequest hostGroupGet);
    
    
    public Object hostGroupCreate(HostGroupCreateRequest hostGroupCreate);
    public Object hostGroupDelete(HostGroupDeleteRequest hostGroupDelete);
    public Object hostGroupExists(HostGroupExistsRequest hostGroupExists);
    public Object hostGroupGetobjects(HostGroupGetobjectsRequest hostGroupGetobjects);
    public Object hostGroupIsreadable(HostGroupIsreadableRequest hostGroupIsreadable);
    public Object hostGroupIswritable(HostGroupIswritableRequest hostGroupIswritable);
    public Object hostGroupMassadd(HostGroupMassaddRequest hostGroupMassadd);
    public Object hostGroupMassremove(HostGroupMassremoveRequest hostGroupMassremove);
    public Object hostGroupMassupdate(HostGroupMassupdateRequest hostGroupMassupdate);
    public Object hostGroupUpdate(HostGroupUpdateRequest hostGroupUpdate);
}
