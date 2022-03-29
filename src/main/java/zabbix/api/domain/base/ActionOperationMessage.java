package zabbix.api.domain.base;
/**
 * @ClassName: ActionOperationMessage
 * @Description: 触发动作的信息发送
 * @author zhaohb
 */
public class ActionOperationMessage
{
    /**
     * 操作编号
     */
    private String operationid;
    /**
     * 是否使用默认动作消息文本和主题（0为使用，1为不使用）
     */
    private Integer default_msg;
    /**
     * 发送信息的媒体类型
     */
    private String mediatypeid;
    /**
     * 信息
     */
    private String message;
    /**
     * 标题
     */
    private String subject;
    
    public void setOperationid(String operationid)
    {
        this.operationid = operationid;
    }
    public String getOperationid()
    {
        return operationid;
    }
    public void setDefault_msg(Integer default_msg)
    {
        this.default_msg = default_msg;
    }
    public Integer getDefault_msg()
    {
        return default_msg;
    }
    public void setMediatypeid(String mediatypeid)
    {
        this.mediatypeid = mediatypeid;
    }
    public String getMediatypeid()
    {
        return mediatypeid;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }
    public String getMessage()
    {
        return message;
    }
    public void setSubject(String subject)
    {
        this.subject = subject;
    }
    public String getSubject()
    {
        return subject;
    }
}
