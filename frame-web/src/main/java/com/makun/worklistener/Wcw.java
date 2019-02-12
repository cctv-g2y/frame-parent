package com.makun.worklistener;

import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @说明:王成文的监听
 * @author makun
 */
public class Wcw implements TaskListener {

    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateTask delegateTask) {
        String eventName = delegateTask.getEventName();
        if ("create".endsWith(eventName)) {
            delegateTask.setVariable("inputUser", "0ee035ff27f540f78a3c8ec515a22db1");//王成文
            Map<String, Object> variables = delegateTask.getVariables();
            System.out.println(variables + "create");
            System.out.println("create=========");
        } else if ("assignment".endsWith(eventName)) {
            System.out.println("assignment========");
        } else if ("complete".endsWith(eventName)) {
            System.out.println("complete===========");
        } else if ("delete".endsWith(eventName)) {
            System.out.println("delete=============");
        }
    }

}
