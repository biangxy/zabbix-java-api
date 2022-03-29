package zabbix.api.domain.base;
/**
 * @ClassName: ActionOperationCondition
 * @Description: 触发动作的命令操作
 * @author zhaohb
 */
public class ActionOperationCondition
{
    /**
     * 条件编号
     */
    private String opconditionid;
    /**
     * 触发条件类型
     */
    private Integer conditiontype;
    /**
     * 值
     */
    private String value;
    /**
     * 操作编号
     */
    private String operationid;
    /**
     * 条件操作符
     */
    private Integer operator;
    
    public void setOpconditionid(String opconditionid)
    {
        this.opconditionid = opconditionid;
    }
    public String getOpconditionid()
    {
        return opconditionid;
    }
    public void setConditiontype(Integer conditiontype)
    {
        this.conditiontype = conditiontype;
    }
    public Integer getConditiontype()
    {
        return conditiontype;
    }
    public void setValue(String value)
    {
        this.value = value;
    }
    public String getValue()
    {
        return value;
    }
    public void setOperationid(String operationid)
    {
        this.operationid = operationid;
    }
    public String getOperationid()
    {
        return operationid;
    }
    public void setOperator(Integer operator)
    {
        this.operator = operator;
    }
    public Integer getOperator()
    {
        return operator;
    }
}
