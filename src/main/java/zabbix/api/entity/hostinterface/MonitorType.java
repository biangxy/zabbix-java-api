package zabbix.api.entity.hostinterface;

/**
 * Interface type
 * @author zhaohb
 *
 */
public enum MonitorType
{
    /**
     * ZABBIX agent
     */
    ZABBIX_AGENT(1),

    /**
     * SNMP
     */
    SNMP(2),
    /**
     * IPMI
     */
    IPMI(3),
    /**
     * JMX
     */
    JMX(4),
    /**
     * 非法字段
     */
    INVALID(-1);
    
    /**
     * 枚举类型的值
     */
    int mValue;
    
    MonitorType(int value)
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
    public static MonitorType getValueOf(int value)
    {
        switch (value)
        {
            case 1:
                return ZABBIX_AGENT;
            case 2:
                return SNMP;
            case 3:
                return IPMI;
            case 4:
                return JMX;
            default:
                return INVALID;
        }
    }
}
