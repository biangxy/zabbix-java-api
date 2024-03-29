package zabbix.api.domain.base;
/**
 * @ClassName: ActionOperationCommand
 * @Description: 动作触发命令操作
 * @author zhaohb
 */
public class ActionOperationCommand
{
    /**
     * 操作编号
     */
    private String operationid;
    /**
     * 要运行的命令
     */
    private String command;
    /**
     * 操作命令的类型（0 - custom script;    1 - IPMI;2 - SSH;
     * 3 - Telnet;4 - global script.  ）
     */
    private Integer type;
    /**
     * SSH身份验证方法（0 - password;1 - public key. ）
     */
    private Integer authtype;
    /**
     * 自定义脚本执行的目标
     */
    private Integer execute_on;
    /**
     * 密码
     */
    private String password;
    /**
     * 端口
     */
    private String port;
    /**
     * 私钥
     */
    private String privatekey;
    /**
     * 公共密钥
     */
    private String publickey;
    /**
     * 全局脚本的脚本编号
     */
    private String scriptid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 
     */
    
    public void setOperationid(String operationid)
    {
        this.operationid = operationid;
    }
    public String getOperationid()
    {
        return operationid;
    }
    public void setCommand(String command)
    {
        this.command = command;
    }
    public String getCommand()
    {
        return command;
    }
    public void setType(Integer type)
    {
        this.type = type;
    }
    public Integer getType()
    {
        return type;
    }
    public void setAuthtype(Integer authtype)
    {
        this.authtype = authtype;
    }
    public Integer getAuthtype()
    {
        return authtype;
    }
    public void setExecute_on(Integer execute_on)
    {
        this.execute_on = execute_on;
    }
    public Integer getExecute_on()
    {
        return execute_on;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPort(String port)
    {
        this.port = port;
    }
    public String getPort()
    {
        return port;
    }
    public void setPrivatekey(String privatekey)
    {
        this.privatekey = privatekey;
    }
    public String getPrivatekey()
    {
        return privatekey;
    }
    public void setPublickey(String publickey)
    {
        this.publickey = publickey;
    }
    public String getPublickey()
    {
        return publickey;
    }
    public void setScriptid(String scriptid)
    {
        this.scriptid = scriptid;
    }
    public String getScriptid()
    {
        return scriptid;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getUsername()
    {
        return username;
    }
}
