package zabbix.api.test;

/**
 * 网络信息
 * @author zhaohb
 *
 */
public class NetWorkInfos
{
    
    private String netportname;            //网口名称
    
    private String Incomingtraffic;        //传入流量
    
    private String Outgoingtraffic;        //传出流量
    
    public String getNetportname()
    {
        return netportname;
    }
    public void setNetportname(String netportname)
    {
        this.netportname = netportname;
    }
    public String getIncomingtraffic()
    {
        return Incomingtraffic;
    }
    public void setIncomingtraffic(String incomingtraffic)
    {
        Incomingtraffic = incomingtraffic;
    }
    public String getOutgoingtraffic()
    {
        return Outgoingtraffic;
    }
    public void setOutgoingtraffic(String outgoingtraffic)
    {
        Outgoingtraffic = outgoingtraffic;
    }

}
