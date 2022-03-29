package zabbix.api.service;
import java.util.List;

import zabbix.api.domain.base.TriggerPrototype;
import zabbix.api.domain.triggerprototype.TriggerPrototypeCreateRequest;
import zabbix.api.domain.triggerprototype.TriggerPrototypeDeleteRequest;
import zabbix.api.domain.triggerprototype.TriggerPrototypeGetRequest;
import zabbix.api.domain.triggerprototype.TriggerPrototypeUpdateRequest;


public interface ITriggerprototypeService
{
    public Object triggerPrototypeCreate(TriggerPrototypeCreateRequest triggerPrototypeCreate);

    public Object addTriggerPrototype(TriggerPrototype triggerPrototype);
    
    public Object triggerPrototypeDelete(TriggerPrototypeDeleteRequest triggerPrototypeDelete);
    
    public Object triggerPrototypeGet(TriggerPrototypeGetRequest triggerPrototypeGet);
    
    public Object triggerPrototypeUpdate(TriggerPrototypeUpdateRequest triggerPrototypeUpdate);
    
    public List<TriggerPrototype> triggerPrototypeGetToBean(TriggerPrototypeGetRequest triggerPrototypeGet);
}
