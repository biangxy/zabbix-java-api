package zabbix.api.test;
import junit.framework.TestCase;
import zabbix.api.domain.media.MediaGetRequest;
import zabbix.api.service.IMediaService;
import zabbix.api.service.impl.MediaServiceImpl;
import zabbix.api.util.Util;
public class TestMedia extends TestCase
{
    private static  IMediaService mediaService = new MediaServiceImpl();
    
    static
    {
         //登录 
         new Util().login();
     }

    public void testGet()
    {
        //数据准备 
        MediaGetRequest get = new MediaGetRequest();
        
        get.getParams().setOutput("extend");
        
        mediaService.get(get);
    }
}