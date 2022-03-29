package zabbix.api.service;
import java.util.List;

import zabbix.api.domain.base.ItemPrototype;
import zabbix.api.domain.itemprototype.ItemPrototypeCreateRequest;
import zabbix.api.domain.itemprototype.ItemPrototypeDeleteRequest;
import zabbix.api.domain.itemprototype.ItemPrototypeExistsRequest;
import zabbix.api.domain.itemprototype.ItemPrototypeGetRequest;
import zabbix.api.domain.itemprototype.ItemPrototypeIsreadableRequest;
import zabbix.api.domain.itemprototype.ItemPrototypeIswritableRequest;
import zabbix.api.domain.itemprototype.ItemPrototypeUpdateRequest;
public interface IItemprototypeService
{
    public Object itemPrototypeCreate(ItemPrototypeCreateRequest itemPrototypeCreate);
    public Object itemPrototypeDelete(ItemPrototypeDeleteRequest itemPrototypeDelete);
    public Object itemPrototypeExists(ItemPrototypeExistsRequest itemPrototypeExists);
    public Object itemPrototypeGet(ItemPrototypeGetRequest itemPrototypeGet);
    public Object itemPrototypeIsreadable(ItemPrototypeIsreadableRequest itemPrototypeIsreadable);
    public Object itemPrototypeIswritable(ItemPrototypeIswritableRequest itemPrototypeIswritable);
    public Object itemPrototypeUpdate(ItemPrototypeUpdateRequest itemPrototypeUpdate);
    public List<ItemPrototype> itemPrototypeGetToBean(ItemPrototypeGetRequest itemPrototypeGet);
}
