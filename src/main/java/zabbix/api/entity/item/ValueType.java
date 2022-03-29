package zabbix.api.entity.item;

/**
 * Type of information of the item.
 * @author zhaohb
 *
 */
public enum ValueType
{
    /**
     * numeric float
     */
    NUMERIC_FLOAT(0),
    /**
     * character
     */
    CHARACTER(1),
    /**
     * log
     */
    LOG(2),
    /**
     * numeric unsigned
     */
    NUMERIC_UNSIGNED(3),
    /**
     * text
     */
    TEXT(4),
    /**
     * invalid
     */
    INVALID(-1);
    /**
     * 枚举类型的值
     */
    int mValue;
    
    ValueType(int value)
    {
        mValue = value;
    }
    
    public int value()
    {
        return mValue;
    }
}
