package zabbix.api.service;
import java.util.List;

import zabbix.api.domain.base.Item;
import zabbix.api.domain.item.ItemCreateRequest;
import zabbix.api.domain.item.ItemDeleteRequest;
import zabbix.api.domain.item.ItemExistsRequest;
import zabbix.api.domain.item.ItemGetRequest;
import zabbix.api.domain.item.ItemGetobjectsRequest;
import zabbix.api.domain.item.ItemIsreadableRequest;
import zabbix.api.domain.item.ItemIswritableRequest;
import zabbix.api.domain.item.ItemUpdateRequest;
/**
 * @ClassName: IItemService
 * @Description: 监控项接口
 */
public interface IItemService
{
    public Object create(ItemCreateRequest create);
    public Object delete(ItemDeleteRequest delete);
    public Object exists(ItemExistsRequest exists);
    public Object getobjects(ItemGetobjectsRequest getobjects);
    /**
     * @Title: get
     * @Description: 获取监控项信息（json）
     * @param get
     * @return Object
     * @throws
     */
    public Object get(ItemGetRequest get);
    
    /**
     * @Title: getItemToBean
     * @Description: 获取监控项信息（bean）
     * @param get
     * @return List<Item>
     * @throws
     */
    public List<Item> getItemToBean(ItemGetRequest get);
    public Object isreadable(ItemIsreadableRequest isreadable);
    public Object iswritable(ItemIswritableRequest iswritable);
    public Object update(ItemUpdateRequest update);
}
