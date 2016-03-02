package com.baidu.ueditor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ls.demon.xx.BaseModel;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.ueditor.define.ActionMap;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.hunter.FileManager;
import com.baidu.ueditor.hunter.ImageHunter;
import com.baidu.ueditor.upload.Uploader;

public class ActionEnter extends BaseModel {
    /**  */
    private static final long   serialVersionUID = 3724106949326281841L;

    /**
    * Logger for this class
    */
    private static final Logger logger           = LoggerFactory.getLogger(ActionEnter.class);

    private HttpServletRequest  request          = null;

    private String              rootPath         = null;
    private String              contextPath      = null;
    private String              reqPath          = null;

    private String              actionType       = null;

    private ConfigManager       configManager    = null;

    public ActionEnter(HttpServletRequest request, String rootPath) {
        logger.info("rootPath = {}", rootPath);

        String reqUrl = request.getRequestURL().toString();
        String reqUri = request.getRequestURI();
        String reqX = request.getRequestURL().substring(0, StringUtils.indexOf(reqUrl, reqUri));
        logger.info("{}, {}, {}", reqUrl, reqUri, reqX);

        logger.info("{}", request.getServerName());

        this.request = request;
        this.rootPath = rootPath;
        this.actionType = request.getParameter("action");
        this.contextPath = request.getContextPath();
        this.reqPath = reqX;

        this.configManager = ConfigManager.getInstance(this.rootPath, this.contextPath, request
            .getRequestURI().replace(request.getContextPath(), ""), this.reqPath);

        logger.info("{}", this);
    }

    public String exec() {

        String callbackName = this.request.getParameter("callback");
        logger.info("callbackName = {}", callbackName);

        if (callbackName != null) {

            if (!validCallbackName(callbackName)) {
                return new BaseState(false, AppInfo.ILLEGAL).toJSONString();
            }

            return callbackName + "(" + this.invoke() + ");";

        } else {
            return this.invoke();
        }

    }

    public String invoke() {

        if (actionType == null || !ActionMap.mapping.containsKey(actionType)) {
            return new BaseState(false, AppInfo.INVALID_ACTION).toJSONString();
        }

        if (this.configManager == null || !this.configManager.valid()) {
            return new BaseState(false, AppInfo.CONFIG_ERROR).toJSONString();
        }

        State state = null;

        int actionCode = ActionMap.getType(this.actionType);

        Map<String, Object> conf = null;

        switch (actionCode) {

            case ActionMap.CONFIG:
                return this.configManager.getAllConfig().toString();

            case ActionMap.UPLOAD_IMAGE:
            case ActionMap.UPLOAD_SCRAWL:
            case ActionMap.UPLOAD_VIDEO:
            case ActionMap.UPLOAD_FILE:
                conf = this.configManager.getConfig(actionCode);
                state = new Uploader(request, conf).doExec();
                break;

            case ActionMap.CATCH_IMAGE:
                conf = configManager.getConfig(actionCode);
                String[] list = this.request.getParameterValues((String) conf.get("fieldName"));
                state = new ImageHunter(conf).capture(list);
                break;

            case ActionMap.LIST_IMAGE:
            case ActionMap.LIST_FILE:
                conf = configManager.getConfig(actionCode);
                int start = this.getStartIndex();
                state = new FileManager(conf).listFile(start);
                break;

        }
        logger.info("{}", state.toJSONString());
        return state.toJSONString();

    }

    public int getStartIndex() {

        String start = this.request.getParameter("start");

        try {
            return Integer.parseInt(start);
        } catch (Exception e) {
            return 0;
        }

    }

    /**
     * callback参数验证
     */
    public boolean validCallbackName(String name) {

        if (name.matches("^[a-zA-Z_]+[\\w0-9_]*$")) {
            return true;
        }

        return false;

    }

}
