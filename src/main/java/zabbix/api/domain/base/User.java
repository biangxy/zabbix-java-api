package zabbix.api.domain.base;

import java.util.List;

/**
 * @ClassName: User
 * @Description: 用户实体
 * @author zhaohb
 */
public class User
{
    /**
     * 用户编号
     */
    private String userid;
    /**
     * 别名
     */
    private String alias;
    /**
     * 密码
     */
    private String passwd;
    /**
     * 最近一次登录失败的时间
     */
    private String attempt_clock;
    /**
     * 登录失败次数
     */
    private Integer attempt_failed;
    /**
     * 登录失败的Ip
     */
    private String attempt_ip;
    /**
     * 是否自动登录（0是禁用，1是启用）
     */
    private Integer autologin;
    /**
     * 会话持续时间（0为永不过期，默认900秒）
     */
    private Integer autologout;
    /**
     * 语言，默认为en_GB
     */
    private String lang;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 自动刷新（默认为30秒）
     */
    private Integer refresh;
    /**
     * 默认显示行数（默认为50）
     */
    private Integer rows_per_page;
    /**
     * 姓
     */
    private String surname;
    /**
     * 主题，Possible values:default - (default) system default;classic
     *  - Classic;originalblue - Original blue;darkblue
     *   - Black & Blue;darkorgange - Dark orange.
     */
    private String theme;
    /**
     * 用户类型（1 - (default) Zabbix user; 
     * 2 - Zabbix admin; 3 - Zabbix super admin. ）
     */
    private Integer type;
    /**
     * 登陆后的页面重定向url
     */
    private String url;
    
    private List<UserGroup> groups;
    private List<Media> mideas;
    
    public List<UserGroup> getGroups()
    {
        return groups;
    }
    public void setGroups(List<UserGroup> groups)
    {
        this.groups = groups;
    }
    public List<Media> getMideas()
    {
        return mideas;
    }
    public void setMideas(List<Media> mideas)
    {
        this.mideas = mideas;
    }
    public String getPasswd()
    {
        return passwd;
    }
    public void setPasswd(String passwd)
    {
        this.passwd = passwd;
    }
    public void setUserid(String userid)
    {
        this.userid = userid;
    }
    public String getUserid()
    {
        return userid;
    }
    public void setAlias(String alias)
    {
        this.alias = alias;
    }
    public String getAlias()
    {
        return alias;
    }
    public void setAttempt_clock(String attempt_clock)
    {
        this.attempt_clock = attempt_clock;
    }
    public String getAttempt_clock()
    {
        return attempt_clock;
    }
    public void setAttempt_failed(Integer attempt_failed)
    {
        this.attempt_failed = attempt_failed;
    }
    public Integer getAttempt_failed()
    {
        return attempt_failed;
    }
    public void setAttempt_ip(String attempt_ip)
    {
        this.attempt_ip = attempt_ip;
    }
    public String getAttempt_ip()
    {
        return attempt_ip;
    }
    public void setAutologin(Integer autologin)
    {
        this.autologin = autologin;
    }
    public Integer getAutologin()
    {
        return autologin;
    }
    public void setAutologout(Integer autologout)
    {
        this.autologout = autologout;
    }
    public Integer getAutologout()
    {
        return autologout;
    }
    public void setLang(String lang)
    {
        this.lang = lang;
    }
    public String getLang()
    {
        return lang;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    public void setRefresh(Integer refresh)
    {
        this.refresh = refresh;
    }
    public Integer getRefresh()
    {
        return refresh;
    }
    public void setRows_per_page(Integer rows_per_page)
    {
        this.rows_per_page = rows_per_page;
    }
    public Integer getRows_per_page()
    {
        return rows_per_page;
    }
    public void setSurname(String surname)
    {
        this.surname = surname;
    }
    public String getSurname()
    {
        return surname;
    }
    public void setTheme(String theme)
    {
        this.theme = theme;
    }
    public String getTheme()
    {
        return theme;
    }
    public void setType(Integer type)
    {
        this.type = type;
    }
    public Integer getType()
    {
        return type;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }
    public String getUrl()
    {
        return url;
    }
}
