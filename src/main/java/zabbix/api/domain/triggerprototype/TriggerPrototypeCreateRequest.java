package zabbix.api.domain.triggerprototype;
import zabbix.api.domain.base.RequestBase;
import zabbix.api.domain.base.TriggerPrototype;
public class TriggerPrototypeCreateRequest extends RequestBase
{
    public TriggerPrototypeCreateRequest()
    {
        super("triggerprototype.create");
    }
    private TriggerPrototypeCreateParams params = new TriggerPrototypeCreateParams();
    public void setParams(TriggerPrototypeCreateParams params)
    {
        this.params = params;
    }
    public TriggerPrototypeCreateParams getParams()
    {
        return params;
    }
    public static class TriggerPrototypeCreateParams extends TriggerPrototype
    {
    }
}
