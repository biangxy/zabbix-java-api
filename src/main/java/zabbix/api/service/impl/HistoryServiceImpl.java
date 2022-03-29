package zabbix.api.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zabbix.api.domain.base.IntegerHistory;
import zabbix.api.domain.history.HistoryGetRequest;
import zabbix.api.service.IHistoryService;
import zabbix.api.util.HttpExecuter;
/**
 * @ClassName: HistoryServiceImpl
 * @Description: 历史信息实现
 * @author zhaohb
 */
public class HistoryServiceImpl implements IHistoryService
{
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(HistoryServiceImpl.class);
    
    /*
     * Title: get
     * Description: 获取历史信息（json）
     * @param get
     * @return Object
     * @see zabbix.api.service.IHistoryService#get(zabbix.api.domain.history.HistoryGetRequest)
     */
    public Object get(HistoryGetRequest get)
    {
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(get);
            LOG.debug("json response:{}", respObj);
            return respObj;
        }
         catch (Exception e)
        {
            LOG.info("get history get response:{}", e);
            return null;
        }
    }
    
    /*
     * Title: getHistoryToBean
     * Description: 获取历史信息（bean）
     * @param get
     * @return List<IntegerHistory>
     * @see zabbix.api.service.IHistoryService#getHistoryToBean(zabbix.api.domain.history.HistoryGetRequest)
     */
    @Override
    public List<IntegerHistory> getHistoryToBean(HistoryGetRequest get,int type)
    {
        JSONObject result = (JSONObject) this.get(get);
        List<IntegerHistory> histories = null;
        if (result.has("result"))
        {
            try
            {
                JSONArray array = result.getJSONArray("result");
                if(array!=null&&array.length()>0){
                    histories = new ArrayList<IntegerHistory>();
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject object = array.getJSONObject(i);
                        IntegerHistory history = new IntegerHistory();
                        history.setClock(object.getString("clock"));
                        history.setItemid(object.getString("itemid"));
                        history.setNs(object.getInt("ns"));
                        history.setValue(object.getString("value"));
                        history.setType(type);
                        histories.add(history);
                        
                    }
                }
            }
            catch (Exception e)
            {
                LOG.info("params history response to beans catch excrption:{}", e);
                return null;
            }
            return histories;
        }
        else if (result.has("error"))
        {
            return null;
        }
        return histories;
    }
}
