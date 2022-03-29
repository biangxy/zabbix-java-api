package zabbix.api.entity.item;

/**
 * Type of the item. 
 * @author zhaohb
 *
 */
public enum ItemType
{
    /**
     * Zabbix agent
     */
    ZABBIX_AGENT(0),
    /**
     * SNMPv1 agent
     */
    SNMP_V1_AGENT(1),
    /**
     * Zabbix trapper
     */
    ZABBIX_TRAPPER(2),
    /**
     * simple check
     */
    SIMPLE_CHECK(3),
    /**
     * SNMPv2 agent
     */
    SNMP_V2_AGENT(4),
    /**
     * Zabbix internal
     */
    ZABBIX_INTERNAL(5),
    /**
     * SNMPv3 agent
     */
    SNMP_V3_AGENT(6),
    /**
     * Zabbix agent (active)
     */
    ZABBIX_AGENT_ACTIVE(7),
    /**
     * Zabbix aggregate
     */
    ZABBIX_AGGREGATE(8),
    /**
     * web item;
     */
    WEB_ITEM(9),
    /**
     * external check
     */
    EXTERNAL_CHECK(10),
    /**
     * database monitor
     */
    DATABASSE_MONITOR(11),
    /**
     * IPMI agent
     */
    IPMI_AGENT(12),
    /**
     * SSH agent
     */
    SSH_AGENT(13),
    /**
     * TELNET agent
     */
    TELNET_AGENT(14),
    /**
     * calculated
     */
    CALCULATED(15),
    /**
     * JMX agent
     */
    JMX_AGNET(16),
    /**
     * invalid
     */
    INVALID(-1);
    /**
     * 枚举类型的值
     */
    int mValue;
    
    ItemType(int value)
    {
        mValue = value;
    }
    
    public int value()
    {
        return mValue;
    }
}
