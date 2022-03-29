package zabbix.api.test;

/**
 * @ClassName: DiskInfos
 * @Description: 文件系统磁盘监控信息
 * @author zhaohb
 */
public class DiskInfos
{
    /**
     * 盘符
     */
    private String diskname;
    /**
     * 空闲大小
     */
    private String freediskspace;
    /**
     * 空闲百分比
     */
    private String freediskspacepercentage;
    /**
     * 总大小
     */
    private String totaldiskspace;
    /**
     * 已使用大小
     */
    private String useddiskspace;
    public String getDiskname()
    {
        return diskname;
    }
    public void setDiskname(String diskname)
    {
        this.diskname = diskname;
    }
    public String getFreediskspace()
    {
        return freediskspace;
    }
    public void setFreediskspace(String freediskspace)
    {
        this.freediskspace = freediskspace;
    }
    public String getFreediskspacepercentage()
    {
        return freediskspacepercentage;
    }
    public void setFreediskspacepercentage(String freediskspacepercentage)
    {
        this.freediskspacepercentage = freediskspacepercentage;
    }
    public String getTotaldiskspace()
    {
        return totaldiskspace;
    }
    public void setTotaldiskspace(String totaldiskspace)
    {
        this.totaldiskspace = totaldiskspace;
    }
    public String getUseddiskspace()
    {
        return useddiskspace;
    }
    public void setUseddiskspace(String useddiskspace)
    {
        this.useddiskspace = useddiskspace;
    }
    
    
}
