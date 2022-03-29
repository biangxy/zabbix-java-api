package zabbix.api.entity.hostinterface;

/**
 * Whether the interface is used as default on the host.
 * Only one interface of some type can be set as default on a host. 
 * @author zhaohb
 *
 */
public enum Main
{
    /**
     * not default
     */
    NOT_DEFAULT(0),
    /**
     * default
     */
    DEFAULT(1),
    /**
     * 非法字段
     */
    INVALID(-1);
    
    /**
     * 枚举类型的值
     */
    int mValue;
    
    Main(int value)
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
    public static Main getValueOf(int value)
    {
        switch (value)
        {
            case 0:
                return NOT_DEFAULT;
            case 1:
                return DEFAULT;
            default:
                return INVALID;
        }
    }
}
