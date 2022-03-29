package zabbix.api.service;
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
public interface IUsergroupService
{
    public Object userGroupCreate(UserGroupCreateRequest userGroupCreate);
    public Object userGroupDelete(UserGroupDeleteRequest userGroupDelete);
    public Object userGroupExists(UserGroupExistsRequest userGroupExists);
    public Object userGroupGetobjects(UserGroupGetobjectsRequest userGroupGetobjects);
    public Object userGroupGet(UserGroupGetRequest userGroupGet);
    public Object userGroupIsreadable(UserGroupIsreadableRequest userGroupIsreadable);
    public Object userGroupIswritable(UserGroupIswritableRequest userGroupIswritable);
    public Object userGroupMassadd(UserGroupMassaddRequest userGroupMassadd);
    public Object userGroupMassupdate(UserGroupMassupdateRequest userGroupMassupdate);
    public Object userGroupUpdate(UserGroupUpdateRequest userGroupUpdate);
}
