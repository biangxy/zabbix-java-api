package zabbix.api.domain.base;
/**
 * @ClassName: ActionCondition
 * @Description: 动作触发条件
 * @author zhaohb
 */
public class ActionCondition
{
    /**
     * type                             operator                    value
     * 15         Application           =, like, not like             Name of the application. 
     * 1         Host                  =, <>                         Host ID. 
     * 0         Host group            =, <>                            Host group ID. 
     * 16         Maintenance status    in, not in 
     * 13         Host template           =, <>                         Linked template ID.
     * 2         Trigger               =, <>                         Trigger ID.  
     * 3         Trigger name           like, not like                 Trigger name. 
     * 5         Trigger value           =                             0 - (default) OK; 1 - problem. 
     * 4         Trigger severity       =, <>, >=, <=                 0 - (default) not classified;1 - information;
     *                                                                    2 - warning;3 - average;4 - high;5 - disaster.
     * 操作符号和对应的数值：=(0);<>(1);like(2);not like(3);in(4);>=(5);<=(6);not in(7)
     */
    /**
     * 条件编号
     */
    private String conditionid;
    /**
     * 触发条件类型
     */
    private Integer conditiontype;
    /**
     * 值
     */
    private String value;
    /**
     * 动作编号
     */
    private String actionid;
    /**
     * 条件操作符
     */
    private Integer operator;
    
    public void setConditionid(String conditionid)
    {
        this.conditionid = conditionid;
    }
    public String getConditionid()
    {
        return conditionid;
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
    public void setActionid(String actionid)
    {
        this.actionid = actionid;
    }
    public String getActionid()
    {
        return actionid;
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
