package zabbix.api.reporter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zabbix.api.util.ZabbixData;

public class Reporter
{
    /**
     * 日志句柄
     */
    private final static Logger LOG = LoggerFactory.getLogger(Reporter.class);

    /**
     * 单条上报zabbix数据接口
     * @param zbxServerIP   Zabbix服务器IP地址
     * @param data          需要上报的zabbix数据
     */
    public static void SendData(String zbxServerIP, ZabbixData data)
    {
        
        try {
            Socket sc = new Socket(zbxServerIP, 10051); //默认端口：10051
            PrintWriter out = new PrintWriter(new OutputStreamWriter(sc.getOutputStream(), "UTF-8"));
            
            String report = buildJsonString(data);
            writeMessage(sc.getOutputStream(),report.getBytes());
            
            out.flush();
            sc.close();
        }
        catch (Exception e)
        {
            LOG.error("Report zabbix data fail.",e);
            e.printStackTrace();
        }
    }
    
    /**
     * 多条上报zabbix数据接口
     * @param zbxServerIP   Zabbix服务器IP地址
     * @param dataList      需要上报的zabbix数据
     */
    public static void SendData(String zbxServerIP, List<ZabbixData> dataList)
    {
        
        try {
            Socket sc = new Socket(zbxServerIP, 10051); //默认端口：10051
            PrintWriter out = new PrintWriter(new OutputStreamWriter(sc.getOutputStream(), "UTF-8"));
            
            String report = buildJsonString(dataList);
            writeMessage(sc.getOutputStream(),report.getBytes());
            
            out.flush();
            sc.close();
        }
        catch (Exception e)
        {
            LOG.error("Report zabbix data fail.",e);
            e.printStackTrace();
        }
    }
    
    /**
     * 组建Zabbix固定格式的Json字符串
     * @return  zabbix上报数据
     */
    private static String buildJsonString(ZabbixData data)
    {
        return  "{"
                +"\"request\":\"sender data\",\n"
                +"\"data\":[\n"
                +"{\n"
                +"\"host\":\"" + data.getHost() + "\",\n"
                +"\"key\":\"" + data.getItem() + "\",\n"
                +"\"value\":\"" + data.getValue().replace("\\", "\\\\") + "\"}]}\n";
    }

    /**
     * 构建zabbix上报数据JSON串
     * @param dataList      数据列表
     * @return zabbix上报数据格式
     */
    private static String buildJsonString(List<ZabbixData> dataList)
    {
        String JsonStr = "";
        
        for(ZabbixData data : dataList)
        {
            JsonStr = JsonStr
                    + "{\n"
                    + "\"host\":\"" + data.getHost() + "\",\n"
                    + "\"key\":\"" + data.getItem() + "\",\n"
                    + "\"value\":\"" + data.getValue().replace("\\", "\\\\") + "\"\n},\n";
        }
        JsonStr = JsonStr.substring(0,JsonStr.length()-2); 
        return jsonHead() + JsonStr + jsonTail();
    }
    
    /**
     * 发送ZBXD头信息
     * @param out       outputStrean
     * @param data      数据
     * @throws IOException  IO异常
     */
    private static void writeMessage(OutputStream out, byte[] data) throws IOException
    {
        int length = data.length;
        
        out.write(new byte[]{
                'Z','B','X','D',
                '\1',
                (byte)(length & 0xFF),
                (byte)((length >> 8) & 0x00FF),
                (byte)((length >> 16) & 0x0000FF),
                (byte)((length >> 24) & 0x000000FF),
                '\0','\0','\0','\0'});
        
        out.write(data);
    }
    
    /**
     * zabbix JSON头
     * @return  JSON头
     */
    private static String jsonHead()
    {
        return "{\n"
                +"\"request\":\"sender data\",\n"
                +"\"data\":[\n";
    }
    
    /**
     * zabbix JSON尾
     * @return  JSON尾
     */
    private static String jsonTail()
    {
        return "\n]\n" + "}";
    }
}
