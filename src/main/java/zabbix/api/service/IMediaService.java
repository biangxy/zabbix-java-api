package zabbix.api.service;
import zabbix.api.domain.media.MediaGetRequest;
public interface IMediaService
{
    public Object get(MediaGetRequest get);
}
