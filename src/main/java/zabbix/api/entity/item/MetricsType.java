package zabbix.api.entity.item;

/**
 * 映射zabbix中item字段中的类型
 * @author zhaohb
 *
 */
public enum MetricsType
{
    /**
     * gauge
     */
    GAUGE("value"),
    /**
     * counter
     */
    COUNTER("Count"),
    /**
     * timer
     */
    TIMER("Count"),
    /**
     * meter
     */
    METER("Count");
    /**
     * 枚举值
     */
    String value;
    /**
     * 构造函数
     * @param value value
     */
    MetricsType(String value)
    {
        this.value = value;
    }
    
    public String value()
    {
        return value;
    }
}
