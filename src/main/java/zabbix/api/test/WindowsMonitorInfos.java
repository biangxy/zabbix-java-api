package zabbix.api.test;

import java.util.List;

public class WindowsMonitorInfos
{
    
    //General
    private String systeminformation;        //系统类型信息
    private String systemuptime;            //系统启动时间
    //CPU
    private String cpuusedpercentage;        //cpu使用率 （window中取得是system，linux取得事user）
    private String cpunum;                    //cpu核数
    private String cpuinterrupt;            //cpu中断率
    //Memory
    private String freememory;                   //空闲内存
    private String totalmemory;                //总内存
    private String availablememory;            //可使用的内存
    //Filesystems
    private List<DiskInfos> diskinfos;        //磁盘信息
    //Network interfaces
    private List<NetWorkInfos> netWorkInfos;//网口信息
    //Processes 
    private String  numberofprocesses ;        //进程数
    //swap
    private String freeswapspace;            //空闲交换空间
    private String totalswapspace;            //总交换空间
    public String getSysteminformation()
    {
        return systeminformation;
    }
    public void setSysteminformation(String systeminformation)
    {
        this.systeminformation = systeminformation;
    }
    public String getSystemuptime()
    {
        return systemuptime;
    }
    public void setSystemuptime(String systemuptime)
    {
        this.systemuptime = systemuptime;
    }
    public String getCpuusedpercentage()
    {
        return cpuusedpercentage;
    }
    public void setCpuusedpercentage(String cpuusedpercentage)
    {
        this.cpuusedpercentage = cpuusedpercentage;
    }
    public String getCpunum()
    {
        return cpunum;
    }
    public void setCpunum(String cpunum)
    {
        this.cpunum = cpunum;
    }
    public String getCpuinterrupt()
    {
        return cpuinterrupt;
    }
    public void setCpuinterrupt(String cpuinterrupt)
    {
        this.cpuinterrupt = cpuinterrupt;
    }
    public String getFreememory()
    {
        return freememory;
    }
    public void setFreememory(String freememory)
    {
        this.freememory = freememory;
    }
    public String getTotalmemory()
    {
        return totalmemory;
    }
    public void setTotalmemory(String totalmemory)
    {
        this.totalmemory = totalmemory;
    }
    public String getAvailablememory()
    {
        return availablememory;
    }
    public void setAvailablememory(String availablememory)
    {
        this.availablememory = availablememory;
    }
    public List<DiskInfos> getDiskinfos()
    {
        return diskinfos;
    }
    public void setDiskinfos(List<DiskInfos> diskinfos)
    {
        this.diskinfos = diskinfos;
    }
    public List<NetWorkInfos> getNetWorkInfos()
    {
        return netWorkInfos;
    }
    public void setNetWorkInfos(List<NetWorkInfos> netWorkInfos)
    {
        this.netWorkInfos = netWorkInfos;
    }
    public String getNumberofprocesses()
    {
        return numberofprocesses;
    }
    public void setNumberofprocesses(String numberofprocesses)
    {
        this.numberofprocesses = numberofprocesses;
    }
    public String getFreeswapspace()
    {
        return freeswapspace;
    }
    public void setFreeswapspace(String freeswapspace)
    {
        this.freeswapspace = freeswapspace;
    }
    public String getTotalswapspace()
    {
        return totalswapspace;
    }
    public void setTotalswapspace(String totalswapspace)
    {
        this.totalswapspace = totalswapspace;
    }
    
    

}
