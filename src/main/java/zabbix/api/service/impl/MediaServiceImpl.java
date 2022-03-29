package zabbix.api.service.impl;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import zabbix.api.domain.media.MediaGetRequest;
import zabbix.api.service.IMediaService;
import zabbix.api.util.HttpExecuter;
/**
 * 媒体类型接口实现
 * @author zhaohb
 *
 */
public class MediaServiceImpl implements IMediaService
{
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(MediaServiceImpl.class);
    
    public Object get(MediaGetRequest get)
    {
        Object result = null;
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(get);
            LOG.debug("get media response:{}", respObj);
            if (respObj.has("result"))
            {
                result = respObj.get("result");
            }
            else if (respObj.has("error"))
            {
                result = respObj.get("error");
            }
        }
        catch (Exception e)
        {
            LOG.info("get media catch exception:{}", e);
            return null;
        }
        return result;
    }
}
