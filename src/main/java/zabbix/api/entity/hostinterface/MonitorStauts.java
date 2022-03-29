package zabbix.api.entity.hostinterface;

/**
 * Status and function of the host. 
 * @author zhaohb
 *
 */
public enum MonitorStauts
{
    /**
     * connect using host DNS name
     */
    MONITORED(0),
    /**
     * connect using host IP address
     */
    UNMONITOR(1),
    /**
     * 非法字段
     */
    INVALID(-1);
    
    /**
     * 枚举类型的值
     */
    int mValue;
    
    MonitorStauts(int value)
    {
        mValue = value;
    }
    
    public int value()
    {
        return mValue;
    }
    
    /**
     * 将字符串全称转换为枚举值
     * 
     * @param value
     *            监控类型
     * @return 监控类型
     */
    public static MonitorStauts getValueOf(int value)
    {
        switch (value)
        {
            case 0:
                return MONITORED;
            case 1:
                return UNMONITOR;
            default:
                return INVALID;
        }
    }
}
