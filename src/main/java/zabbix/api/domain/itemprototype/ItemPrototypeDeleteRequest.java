package zabbix.api.domain.itemprototype;
import java.util.*;

import zabbix.api.domain.base.RequestBase;
public class ItemPrototypeDeleteRequest extends RequestBase
{
    public ItemPrototypeDeleteRequest()
    {
        super("itemprototype.delete");
    }
    private List<String> params;
    public void setParams(List<String> params)
    {
        this.params = params;
    }
    public List<String> getParams()
    {
         if (params == null)
         {
            params = new ArrayList<String>();
            return params;
         }
         return params;
    }
}
