package zabbix.api.entity.hostinterface;

/**
 * Whether the connection should be made via IP. 
 * @author zhaohb
 *
 */
public enum UseIp
{
    /**
     * connect using host DNS name
     */
    USE_HOST_DNS_NAME(0),
    /**
     * connect using host IP address
     */
    USE_HOST_IP_ADRESS(1),
    /**
     * 非法字段
     */
    INVALID(-1);
    
    /**
     * 枚举类型的值
     */
    int mValue;
    
    UseIp(int value)
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
    public static UseIp getValueOf(int value)
    {
        switch (value)
        {
            case 0:
                return USE_HOST_DNS_NAME;
            case 1:
                return USE_HOST_IP_ADRESS;
            default:
                return INVALID;
        }
    }
}
