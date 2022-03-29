package zabbix.api.service;
import java.util.List;

import zabbix.api.domain.action.ActionCreateRequest;
import zabbix.api.domain.action.ActionDeleteRequest;
import zabbix.api.domain.action.ActionExistsRequest;
import zabbix.api.domain.action.ActionGetRequest;
import zabbix.api.domain.action.ActionUpdateRequest;
import zabbix.api.domain.base.Action;
/**
 * @ClassName: IActionService
 * @Description: 动作接口
 * @author zhaohb
 */
public interface IActionService
{
    /**
     * @Title: get
     * @Description: 动作获取（json）
     * @param get
     * @return Object
     * @throws
     */
    public Object get(ActionGetRequest get);
    
    /**
     * @Title: getActionBean
     * @Description: 动作的获取（bean）
     * @param get
     * @return List<Action>
     * @throws
     */
    public List<Action> getActionBean(ActionGetRequest get);
    
    public Object create(ActionCreateRequest create);
    public Object delete(ActionDeleteRequest delete);
    public Object exists(ActionExistsRequest exists);
    public Object update(ActionUpdateRequest update);

    boolean addAction(Action action);
    boolean updateAction(Action action);
}
