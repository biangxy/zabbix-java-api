package zabbix.api.service;
import java.util.List;

import zabbix.api.domain.base.Trigger;
import zabbix.api.domain.trigger.TriggerAdddependenciesRequest;
import zabbix.api.domain.trigger.TriggerCreateRequest;
import zabbix.api.domain.trigger.TriggerDeleteRequest;
import zabbix.api.domain.trigger.TriggerDeletedependenciesRequest;
import zabbix.api.domain.trigger.TriggerExistsRequest;
import zabbix.api.domain.trigger.TriggerGetRequest;
import zabbix.api.domain.trigger.TriggerGetobjectsRequest;
import zabbix.api.domain.trigger.TriggerIsreadableRequest;
import zabbix.api.domain.trigger.TriggerIswritableRequest;
import zabbix.api.domain.trigger.TriggerUpdateRequest;
public interface ITriggerService
{
    public Object adddependencies(TriggerAdddependenciesRequest adddependencies);
    public Object create(TriggerCreateRequest create);
    public Object addTrigger(Trigger trigger);
    public Object deletedependencies(TriggerDeletedependenciesRequest deletedependencies);
    public Object delete(TriggerDeleteRequest delete);
    public Object exists(TriggerExistsRequest exists);
    public Object getobjects(TriggerGetobjectsRequest getobjects);
    public Object get(TriggerGetRequest get);
    public Object isreadable(TriggerIsreadableRequest isreadable);
    public Object iswritable(TriggerIswritableRequest iswritable);
    public Object update(TriggerUpdateRequest update);
    public List<Trigger> getTriggerBean(TriggerGetRequest get);
    
}
