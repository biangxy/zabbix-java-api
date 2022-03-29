package zabbix.api.service;
import zabbix.api.domain.mediatype.MediaTypeCreateRequest;
import zabbix.api.domain.mediatype.MediaTypeDeleteRequest;
import zabbix.api.domain.mediatype.MediaTypeGetRequest;
import zabbix.api.domain.mediatype.MediaTypeUpdateRequest;
public interface IMediatypeService
{
    public Object mediaTypeCreate(MediaTypeCreateRequest mediaTypeCreate);
    public Object mediaTypeDelete(MediaTypeDeleteRequest mediaTypeDelete);
    public Object mediaTypeGet(MediaTypeGetRequest mediaTypeGet);
    public Object mediaTypeUpdate(MediaTypeUpdateRequest mediaTypeUpdate);
}
