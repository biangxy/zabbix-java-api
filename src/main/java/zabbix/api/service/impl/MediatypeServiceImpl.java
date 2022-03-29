package zabbix.api.service.impl;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import zabbix.api.domain.mediatype.MediaTypeCreateRequest;
import zabbix.api.domain.mediatype.MediaTypeDeleteRequest;
import zabbix.api.domain.mediatype.MediaTypeGetRequest;
import zabbix.api.domain.mediatype.MediaTypeUpdateRequest;
import zabbix.api.service.IMediatypeService;
import zabbix.api.util.HttpExecuter;
public class MediatypeServiceImpl implements IMediatypeService
{
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(MediatypeServiceImpl.class);
    public Object mediaTypeCreate(MediaTypeCreateRequest mediaTypeCreate)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(mediaTypeCreate);
            LOG.debug("create media type response:{}", respObj);
//            Object result = null;
//            if (respObj.has("result")) {
//                result =respObj.get("result");
//            }
//             else if (respObj.has("error")) {
//                result =respObj.get("error");
//            }
        }
        catch (Exception e)
        {
            LOG.info("create media type catch exception:{}", e);
            return null;
        }
        return respObj;
    }

    public Object mediaTypeDelete(MediaTypeDeleteRequest mediaTypeDelete)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(mediaTypeDelete);
            LOG.debug("delete media type response:{}", respObj);
//            Object result = null;
//            if (respObj.has("result")) {
//                result = respObj.get("result");
//            }
//             else if (respObj.has("error")) {
//                result = respObj.get("error");
//            }
        }
        catch (Exception e)
        {
            LOG.info("delete media type catch exception:{}", e);
            return null;
        }
        return respObj;
    }

    public Object mediaTypeGet(MediaTypeGetRequest mediaTypeGet)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(mediaTypeGet);
            LOG.debug("get media type response:{}", respObj);
//            Object result = null;
//            if (respObj.has("result")) {
//                result = respObj.get("result");
//            }
//             else if (respObj.has("error")) {
//                result = respObj.get("error");
//            }
        }
        catch (Exception e)
        {
            LOG.info("get media type catch exception:{}", e);
            return null;
        }
        return respObj;
    }
    public Object mediaTypeUpdate(MediaTypeUpdateRequest mediaTypeUpdate)
    {
        JSONObject respObj = null;
        try
        {
            respObj = HttpExecuter.post(mediaTypeUpdate);
            LOG.debug("update media type response:{}", respObj);
//            Object result = null;
//            if (respObj.has("result")) {
//                result = respObj.get("result");
//            }
//             else if (respObj.has("error")) {
//                result = respObj.get("error");
//            }
        }
        catch (Exception e)
        {
            LOG.info("update media type catch exception:{}", e);
            return null;
        }
        return respObj;
    }
}
