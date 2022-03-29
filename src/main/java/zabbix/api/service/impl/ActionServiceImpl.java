package zabbix.api.service.impl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zabbix.api.domain.action.ActionCreateRequest;
import zabbix.api.domain.action.ActionCreateRequest.ActionCreateParams;
import zabbix.api.domain.action.ActionDeleteRequest;
import zabbix.api.domain.action.ActionExistsRequest;
import zabbix.api.domain.action.ActionGetRequest;
import zabbix.api.domain.action.ActionUpdateRequest;
import zabbix.api.domain.action.ActionUpdateRequest.ActionUpdateParams;
import zabbix.api.domain.base.Action;
import zabbix.api.domain.base.ActionCondition;
import zabbix.api.domain.base.ActionOperation;
import zabbix.api.domain.base.ActionOperationCommand;
import zabbix.api.domain.base.ActionOperationCondition;
import zabbix.api.domain.base.ActionOperationMessage;
import zabbix.api.domain.base.ActionOperationSyn;
import zabbix.api.service.IActionService;
import zabbix.api.util.HttpExecuter;

/**
 * @ClassName: ActionServiceImpl
 * @Description: 动作接口实现
 * @author zhaohb
 *
 */
public class ActionServiceImpl implements IActionService
{
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(ActionServiceImpl.class);
            
    public Object create(ActionCreateRequest create)
    {
        try
        {
            JSONObject respObj = HttpExecuter.post(create);
            System.out.println(respObj);
            LOG.debug("create action get response :{}", respObj);
            return respObj;
//            Object result = null;
//            if (respObj.has("result"))
//            {
//                result = respObj.get("result");
//            }
//             else if (respObj.has("error"))
//            {
//                result = respObj.get("error");
//            }
        }
         catch (Exception e)
        {
            LOG.info("create action catch exception:{}", e);
            return null;
        }
    }
    
    @Override
    public boolean addAction(Action action)
    {
        boolean result = false;
        ActionCreateRequest create = new ActionCreateRequest();
        ActionCreateParams params = create.getParams();
        if (action!=null)
        {
            List<ActionOperation> actionOperations = action.getOperations();
            if (actionOperations!=null&&actionOperations.size()>0){
                String actionName = action.getName();
                Integer evaltype = action.getEvaltype();
                Integer eventSourse = action.getEventsource();
                Integer escperiod = action.getEsc_period();
                String dshortdata = action.getDef_shortdata();
                String dlongdata = action.getDef_longdata() ;
                Integer status = action.getStatus();
                String rshortdata = action.getR_shortdata();
                String rlongdata = action.getR_longdata();
                Integer remasg = action.getRecovery_msg();
                if (actionName!=null&&!"".equals(actionName)&&evaltype!=null&&eventSourse!=null&&escperiod!=null){
                    params.setName(actionName);
                    params.setEsc_period(escperiod);
                    params.setEvaltype(evaltype);
                    params.setEventsource(eventSourse);
                    params.setDef_shortdata(dshortdata);
                    params.setDef_longdata(dlongdata);
                    params.setStatus(status);
                    params.setR_shortdata(rshortdata);
                    params.setR_longdata(rlongdata);
                    params.setRecovery_msg(remasg);
                    params.setOperations(actionOperations);
                    params.setConditions(action.getConditions());
////                    for(int i=0;i<actionOperations.size();i++){
////                        ActionOperation actionOperation =actionOperations.get(i);
////                        actionOperation.getEsc_period();
////                        actionOperation.getEsc_step_from();
////                        actionOperation.getEsc_step_to();
////                        actionOperation.getEvaltype();
////                        params.seto
////                        ActionOperationCommand actionOperationCommand= actionOperation.getOpcommand();
////                        List<ActionOperationSyn> operationSyns = actionOperation.getOpcommand_grp();
////                    }
//                    ActionOperation ao = new ActionOperation();
//                    ao.setOperationtype(0);
//                    ao.setEsc_period(0);
//                    ao.setEsc_step_from(1);
//                    ao.setEsc_step_to(2);
//                    ao.setEvaltype(0);
//                    ActionOperationSyn aos = new ActionOperationSyn();
//                    aos.setUsrgrpid("17");
//                    ao.getOpmessage_grp().add(aos);
//                    ActionOperationMessage aom = new ActionOperationMessage();
//                    aom.setDefault_msg(1);
//                    aom.setMediatypeid("1");
//                    ao.setOpmessage(aom);
//                    params.getOperations().add(ao);
//                    ActionCondition ac = new ActionCondition();
//                    ac.setConditiontype(3);
//                    ac.setOperator(0);
//                    ac.setValue("200");
//                    params.getConditions().add(ac);
                    JSONObject respObj = (JSONObject) create(create);
                    if (respObj.has("result"))
                    {
                        result = true;
                    }
                     else if (respObj.has("error"))
                     {
                        result = false;
                    }
                }
            }
        }
        return result;
    }
    
    public Object delete(ActionDeleteRequest delete)
    {
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(delete);
            LOG.debug("delete action get response:{}", respObj);
            return respObj;
//            Object result = null;
//            if (respObj.has("result")) {
//                result = respObj.get("result");
//            }
//             else if (respObj.has("error")) {
//                result = respObj.get("error");
//            }
        }
        catch (Exception e)
        {
            LOG.info("delete action catch exception:{}", e);
            return null;
        }
    }
    public Object exists(ActionExistsRequest exists)
    {
        Object result = null;
        JSONObject respObj =null;
        try
        {
            respObj = HttpExecuter.post(exists);
            LOG.debug("exist action get response:{}", respObj);
            if (respObj.has("result"))
            {
                result = respObj.get("result");
            }
             else if (respObj.has("error"))
             {
                result = respObj.get("error");
            }
        }
         catch (Exception e)
         {
            LOG.info("exist action catch exception:P{}", e);
        }
        return result;
    }
    
    /*
     * Title: get
     * Description: 动作获取（json）
     * @param get
     * @return
     * @see zabbix.api.service.IActionService#get(zabbix.api.domain.action.ActionGetRequest)
     */
    public Object get(ActionGetRequest get)
    {
        JSONObject respObj =null;
        try {
            respObj = HttpExecuter.post(get);
            LOG.debug("exist action get response:{}", respObj);
            return respObj;
        }
         catch (Exception e)
        {
            LOG.info("get action catch excoption:{}", e);
            return null;
        }
    }
    
    /*
     * Title: getActionBean
     * Description: 获取动作（bean）
     * @param get
     * @return
     * @see zabbix.api.service.IActionService#getActionBean(zabbix.api.domain.action.ActionGetRequest)
     */
    @Override
    public List<Action> getActionBean(ActionGetRequest get)
    {
        List<Action> actions = null;
        JSONObject result = (JSONObject) this.get(get);
        if (result.has("result"))
        {
            try
            {
                JSONArray array = result.getJSONArray("result");
                if (array!=null&&array.length()>0)
                {
                    actions = new ArrayList<Action>();
                    for (int i = 0;i<array.length();i++)
                    {
                        Action action = new Action();
                        JSONObject object = array.getJSONObject(i);
                        action.setActionid(object.getString("actionid"));
                        action.setDef_longdata(object.getString("def_longdata"));
                        action.setDef_shortdata(object.getString("def_longdata"));
                        action.setEsc_period(object.getInt("esc_period"));
                        action.setEvaltype(object.getInt("evaltype"));
                        action.setEventsource(object.getInt("eventsource"));
                        action.setName(object.getString("name"));
                        action.setR_longdata(object.getString("r_longdata"));
                        action.setR_shortdata(object.getString("r_shortdata"));
                        action.setRecovery_msg(object.getInt("recovery_msg"));
                        action.setStatus(object.getInt("status"));
                /*******************************operation封装********************************************/
                        JSONObject operations = object.getJSONObject("operations");
                        if (operations != null)
                        {
                            List<ActionOperation> operationlist = new ArrayList<ActionOperation>();
                            Iterator<?> opkeys = operations.keys(); 
                            while(opkeys.hasNext())
                            {
                                ActionOperation actionOperation = new ActionOperation();
                                String opkey = (String) opkeys.next();
                                JSONObject opobj = operations.getJSONObject(opkey);
                                if (opobj != null)
                                {
                                    actionOperation.setOperationid(opobj.getString("operationid"));
                                    actionOperation.setOperationtype(opobj.getInt("operationtype"));
                                    actionOperation.setActionid(opobj.getString("actionid"));
                                    actionOperation.setEsc_period(opobj.getInt("esc_period"));
                                    actionOperation.setEsc_step_from(opobj.getInt("esc_step_from"));
                                    actionOperation.setEsc_step_to(opobj.getInt("esc_step_to"));
                                    actionOperation.setEvaltype(opobj.getInt("evaltype"));
//                                    actionOperation.setOpcommand(opcommand);
                                    if (opobj.has("opcommand"))
                                    {
                                        JSONObject opcommand = opobj.getJSONObject("opcommand");//opcommand
                                        if (opcommand!=null)
                                        {
                                            ActionOperationCommand command = new ActionOperationCommand();
                                            command.setAuthtype(opcommand.getInt("authtype"));
                                            command.setCommand(opcommand.getString("command"));
                                            command.setExecute_on(opcommand.getInt("execute_on"));
                                            command.setOperationid(opcommand.getString("operationid"));
                                            command.setPassword(opcommand.getString("password"));
                                            command.setPort(opcommand.getString("port"));
                                            command.setPrivatekey(opcommand.getString("privatekey"));
                                            command.setPublickey(opcommand.getString("publickey"));
                                            command.setScriptid(opcommand.getString("scriptid"));
                                            command.setType(opcommand.getInt("type"));
                                            command.setUsername(opcommand.getString("username"));
                                            actionOperation.setOpcommand(command);
                                        }
                                    }
                                    if (opobj.has("opcommand_grp"))
                                    {
                                        JSONArray opcommandgrp = opobj.getJSONArray("opcommand_grp");
                                        if (opcommandgrp!=null&&opcommandgrp.length()>0){
                                            List<ActionOperationSyn> opcommgrps = new ArrayList<ActionOperationSyn>();
                                            for (int j=0;j<opcommandgrp.length();j++)
                                            {
                                                ActionOperationSyn mycommgrp = new ActionOperationSyn();
                                                JSONObject commgrpjson = opcommandgrp.getJSONObject(j);
                                                mycommgrp.setOpcommand_grpid(commgrpjson.getString("opcommand_grpid"));
                                                mycommgrp.setOperationid(commgrpjson.getString("operationid"));
                                                mycommgrp.setGroupid(commgrpjson.getString("groupid"));
                                                opcommgrps.add(mycommgrp);
                                            }
                                            actionOperation.setOpcommand_grp(opcommgrps);
                                        }
                                    }
                                    if (opobj.has("opcommand_hst"))
                                    {
                                        JSONArray opmesagehst = opobj.getJSONArray("opcommand_hst");
                                        if (opmesagehst!=null&&opmesagehst.length()>0)
                                        {
                                            List<ActionOperationSyn> opcommhst = new ArrayList<ActionOperationSyn>();
                                            for (int k=0;k<opmesagehst.length();k++)
                                            {
                                                ActionOperationSyn mycommghst = new ActionOperationSyn();
                                                JSONObject commhstjson = opmesagehst.getJSONObject(k);
                                                mycommghst.setOpcommand_hstid(commhstjson.getString("opcommand_hstid"));
                                                mycommghst.setOperationid(commhstjson.getString("operationid"));
                                                mycommghst.setHostid(commhstjson.getString("hostid"));
                                                opcommhst.add(mycommghst);
                                            }
                                            actionOperation.setOpcommand_hst(opcommhst);
                                        }
                                    }
                                    if (opobj.has("opgroup"))
                                    {
                                        JSONArray opgroup = opobj.getJSONArray("opgroup");
                                        if (opgroup!=null&&opgroup.length()>0)
                                        {
                                            List<ActionOperationSyn> opgrouplist = new ArrayList<ActionOperationSyn>();
                                            for (int m=0;m<opgroup.length();m++)
                                            {
                                                JSONObject opgroupjson = opgroup.getJSONObject(m);
                                                ActionOperationSyn myopgroup = new ActionOperationSyn();
                                                myopgroup.setOperationid(opgroupjson.getString("operationid"));
                                                myopgroup.setGroupid(opgroupjson.getString("groupid"));
                                                opgrouplist.add(myopgroup);
                                            }
                                            actionOperation.setOpgroup(opgrouplist);
                                        }
                                    }
                                    if (opobj.has("opmessage"))
                                    {
                                        JSONObject opmessage = opobj.getJSONObject("opmessage");
                                        if (opmessage!=null)
                                        {
                                            ActionOperationMessage message = new ActionOperationMessage();
                                            message.setDefault_msg(opmessage.getInt("default_msg"));
                                            message.setMediatypeid(opmessage.getString("mediatypeid"));
                                            message.setMessage(opmessage.getString("message"));
                                            message.setOperationid(opmessage.getString("operationid"));
                                            message.setSubject(opmessage.getString("subject"));
                                            actionOperation.setOpmessage(message);
                                        }
                                    }
                                    if (opobj.has("opmessage_grp"))
                                    {
                                        JSONArray opmessagegrp = opobj.getJSONArray("opmessage_grp");
                                        if (opmessagegrp!=null&&opmessagegrp.length()>0)
                                        {
                                            List<ActionOperationSyn> opmsggrp = new ArrayList<ActionOperationSyn>();
                                            for (int n=0;n<opmessagegrp.length();n++)
                                            {
                                                JSONObject messagegrp = opmessagegrp.getJSONObject(n);
                                                ActionOperationSyn msggrp = new ActionOperationSyn();
                                                msggrp.setOperationid(messagegrp.getString("operationid"));
                                                msggrp.setUsrgrpid(messagegrp.getString("usrgrpid"));
                                                opmsggrp.add(msggrp);
                                            }
                                            actionOperation.setOpmessage_grp(opmsggrp);
                                        }
                                    }
                                    if (opobj.has("opmessage_usr"))
                                    {
                                        JSONArray opmsgusr = opobj.getJSONArray("opmessage_usr");
                                        if (opmsgusr!=null&&opmsgusr.length()>0)
                                        {
                                            List<ActionOperationSyn> opmsgusrs = new ArrayList<ActionOperationSyn>();
                                            for (int y=0;y<opmsgusr.length();y++)
                                            {
                                                JSONObject opmsgusrjson = opmsgusr.getJSONObject(y);
                                                ActionOperationSyn opmessageusr = new ActionOperationSyn();
                                                opmessageusr.setOperationid(opmsgusrjson.getString("operationid"));
                                                opmessageusr.setUsrgrpid(opmsgusrjson.getString("usrgrpid"));
                                                opmsgusrs.add(opmessageusr);
                                            }
                                            actionOperation.setOpmessage_usr(opmsgusrs);
                                        }
                                    }
                                    if (opobj.has("optemplate"))
                                    {
                                        JSONArray optemplate = opobj.getJSONArray("optemplate");
                                        if (optemplate!=null&&optemplate.length()>0){
                                            List<ActionOperationSyn> optemplatelist = new ArrayList<ActionOperationSyn>();
                                            for (int j=0;j<optemplate.length();j++)
                                            {
                                                JSONObject optemplatejson = optemplate.getJSONObject(j);
                                                ActionOperationSyn optemp = new ActionOperationSyn();
                                                optemp.setOperationid(optemplatejson.getString("operationid"));
                                                optemp.setTemplateid(optemplatejson.getString("templateid"));
                                                optemplatelist.add(optemp);
                                            }
                                            actionOperation.setOptemplate(optemplatelist);
                                        }
                                    }
                                    if (opobj.has("opconditions"))
                                    {
                                        JSONArray opconditions = opobj.getJSONArray("opconditions");//jetrbrains
                                        if (opconditions!=null&&opconditions.length()>0)
                                        {
                                            List<ActionOperationCondition> opconditionslist = new ArrayList<ActionOperationCondition>(); 
                                            for (int j=0;j<opconditions.length();j++)
                                            {
                                                JSONObject conditionjson = opconditions.getJSONObject(j);
                                                ActionOperationCondition operationCondition = new ActionOperationCondition();
                                                operationCondition.setConditiontype(conditionjson.getInt("conditiontype"));
                                                operationCondition.setOpconditionid(conditionjson.getString("opconditionid"));
                                                operationCondition.setOperationid(conditionjson.getString("operationid"));
                                                operationCondition.setOperator(conditionjson.getInt("operator"));
                                                operationCondition.setValue(conditionjson.getString("value"));
                                                opconditionslist.add(operationCondition);
                                            }
                                            actionOperation.setOpconditions(opconditionslist);
                                        }
                                    }
                                }
                            operationlist.add(actionOperation);
                            }
                            action.setOperations(operationlist);
                        }
                        /*******************************************conditions 封装***************************************************/
                        JSONObject conditions = object.getJSONObject("conditions");
                        if (conditions!=null)
                        {
                            List<ActionCondition> conditionlist =new ArrayList<ActionCondition>();
                            Iterator<?> opkeys = conditions.keys(); 
                            while (opkeys.hasNext())
                            {
                                ActionCondition actionCondition = new ActionCondition();
                                String opkey = (String) opkeys.next();
                                JSONObject conditionjson = conditions.getJSONObject(opkey);
                                actionCondition.setActionid(conditionjson.getString("actionid"));
                                actionCondition.setConditionid(conditionjson.getString("conditionid"));
                                actionCondition.setConditiontype(conditionjson.getInt("conditiontype"));
                                actionCondition.setOperator(conditionjson.getInt("operator"));
                                actionCondition.setValue(conditionjson.getString("value"));
                                conditionlist.add(actionCondition);
                            }
                            action.setConditions(conditionlist);
                        }
                        actions.add(action);
                    }
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                return actions; 
            }
            return actions;
        }
         else if (result.has("error")) 
        {
            return actions;
        }
        return actions;
    }
    
    public Object update(ActionUpdateRequest update)
    {
        Object result = null;
        JSONObject respObj =null;
        try
        {
             respObj = HttpExecuter.post(update);
            LOG.debug("update action get response:{}", respObj);
            if (respObj.has("result"))
            {
                result = respObj.get("result");
            }
             else if (respObj.has("error"))
            {
                result = respObj.get("error");
            }
        }
         catch (Exception e)
        {
            LOG.info("update action catch exception:{}", e);
        }
        return result;
    }

    @Override
    public boolean updateAction(Action action)
    {
        boolean result = false;
        ActionUpdateRequest update = new ActionUpdateRequest();
        ActionUpdateParams params = update.getParams();
        if (action!=null)
        {
            List<ActionOperation> actionOperations = action.getOperations();
            String actionid = action.getActionid();
            if (actionid!=null&&!"".equals(actionid))
            {
                if (actionOperations!=null&&actionOperations.size()>0)
                {
                    String actionName = action.getName();
                    Integer evaltype = action.getEvaltype();
                    Integer eventSourse = action.getEventsource();
                    Integer escperiod = action.getEsc_period();
                    String dshortdata = action.getDef_shortdata();
                    String dlongdata = action.getDef_longdata() ;
                    Integer status = action.getStatus();
                    String rshortdata = action.getR_shortdata();
                    String rlongdata = action.getR_longdata();
                    Integer remasg = action.getRecovery_msg();
                    if (actionName!=null&&!"".equals(actionName)&&evaltype!=null&&eventSourse!=null&&escperiod!=null)
                    {
                        params.setActionid(actionid);
                        params.setName(actionName);
                        params.setEsc_period(escperiod);
                        params.setEvaltype(evaltype);
                        params.setEventsource(eventSourse);
                        params.setDef_shortdata(dshortdata);
                        params.setDef_longdata(dlongdata);
                        params.setStatus(status);
                        params.setR_shortdata(rshortdata);
                        params.setR_longdata(rlongdata);
                        params.setRecovery_msg(remasg);
                        params.setOperations(actionOperations);
                        params.setConditions(action.getConditions());
                        JSONObject respObj = (JSONObject) update(update);
                        if (respObj.has("result"))
                        {
                            result = true;
                        }
                        else if (respObj.has("error"))
                        {
                            result = false;
                        }
                    }
                }
            }
        }
        return result;
    }
}
