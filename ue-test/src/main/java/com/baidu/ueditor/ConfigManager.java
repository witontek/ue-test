package com.baidu.ueditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ls.demon.xx.BaseModel;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.ueditor.define.ActionMap;

/**
 * 配置管理器
 * @author hancong03@baidu.com
 *
 */
public final class ConfigManager extends BaseModel {
    /**  */
    private static final long    serialVersionUID      = 4824425650007883508L;

    /**
    * Logger for this class
    */
    private static final Logger  logger                = LoggerFactory
                                                           .getLogger(ConfigManager.class);

    private final String         rootPath;
    private final String         originalPath;
    private final String         contextPath;
    private String               reqPath;

    private static final String  configFileName        = "config.json";
    private String               parentPath            = null;
    private JSONObject           jsonConfig            = null;
    // 涂鸦上传filename定义
    private final static String  SCRAWL_FILE_NAME      = "scrawl";
    // 远程图片抓取filename定义
    private final static String  REMOTE_FILE_NAME      = "remote";

    private static ConfigManager ConfigManagerInstance = null;

    /*
     * 通过一个给定的路径构建一个配置管理器， 该管理器要求地址路径所在目录下必须存在config.properties文件
     */
    private ConfigManager(String rootPath, String contextPath, String uri, String reqPath)
                                                                                          throws FileNotFoundException,
                                                                                          IOException {

        rootPath = rootPath.replace("\\", "/");

        this.contextPath = contextPath;
        this.rootPath = rootPath;
        this.originalPath = this.rootPath + uri;
        this.reqPath = reqPath;

        this.initEnv();

        logger.info("ConfigManager init {}", this);
    }

    /**
     * 配置管理器构造工厂
     * @param rootPath 服务器根路径
     * @param contextPath 服务器所在项目路径
     * @param uri 当前访问的uri
     * @param reqPath TODO
     * @return 配置管理器实例或者null
     */
    public static ConfigManager getInstance(String rootPath, String contextPath, String uri,
                                            String reqPath) {
        logger.info("rootPath={}, contextPath={}, uri={}, reqPath={}", rootPath, contextPath, uri,
            reqPath);

        if (ConfigManagerInstance == null) {
            try {
                ConfigManagerInstance = new ConfigManager(rootPath, contextPath, uri, reqPath);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return null;
            }
        }
        return ConfigManagerInstance;
    }

    // 验证配置文件加载是否正确
    public boolean valid() {
        return this.jsonConfig != null;
    }

    public JSONObject getAllConfig() {

        return this.jsonConfig;

    }

    public Map<String, Object> getConfig(int type) {

        Map<String, Object> conf = new HashMap<String, Object>();
        String savePath = null;

        switch (type) {

            case ActionMap.UPLOAD_FILE:
                conf.put("isBase64", "false");
                conf.put("maxSize", this.jsonConfig.getLong("fileMaxSize"));
                conf.put("allowFiles", this.getArray("fileAllowFiles"));
                conf.put("fieldName", this.jsonConfig.getString("fileFieldName"));
                savePath = this.jsonConfig.getString("filePathFormat");
                break;

            case ActionMap.UPLOAD_IMAGE:
                conf.put("isBase64", "false");
                conf.put("maxSize", this.jsonConfig.getLong("imageMaxSize"));
                conf.put("allowFiles", this.getArray("imageAllowFiles"));
                conf.put("fieldName", this.jsonConfig.getString("imageFieldName"));
                savePath = this.jsonConfig.getString("imagePathFormat");
                break;

            case ActionMap.UPLOAD_VIDEO:
                conf.put("maxSize", this.jsonConfig.getLong("videoMaxSize"));
                conf.put("allowFiles", this.getArray("videoAllowFiles"));
                conf.put("fieldName", this.jsonConfig.getString("videoFieldName"));
                savePath = this.jsonConfig.getString("videoPathFormat");
                break;

            case ActionMap.UPLOAD_SCRAWL:
                conf.put("filename", ConfigManager.SCRAWL_FILE_NAME);
                conf.put("maxSize", this.jsonConfig.getLong("scrawlMaxSize"));
                conf.put("fieldName", this.jsonConfig.getString("scrawlFieldName"));
                conf.put("isBase64", "true");
                savePath = this.jsonConfig.getString("scrawlPathFormat");
                break;

            case ActionMap.CATCH_IMAGE:
                conf.put("filename", ConfigManager.REMOTE_FILE_NAME);
                conf.put("filter", this.getArray("catcherLocalDomain"));
                conf.put("maxSize", this.jsonConfig.getLong("catcherMaxSize"));
                conf.put("allowFiles", this.getArray("catcherAllowFiles"));
                conf.put("fieldName", this.jsonConfig.getString("catcherFieldName") + "[]");
                savePath = this.jsonConfig.getString("catcherPathFormat");
                break;

            case ActionMap.LIST_IMAGE:
                conf.put("allowFiles", this.getArray("imageManagerAllowFiles"));
                conf.put("dir", this.jsonConfig.getString("imageManagerListPath"));
                conf.put("count", this.jsonConfig.getInt("imageManagerListSize"));
                break;

            case ActionMap.LIST_FILE:
                conf.put("allowFiles", this.getArray("fileManagerAllowFiles"));
                conf.put("dir", this.jsonConfig.getString("fileManagerListPath"));
                conf.put("count", this.jsonConfig.getInt("fileManagerListSize"));
                break;

        }

        conf.put("savePath", savePath);
        conf.put("rootPath", this.rootPath);

        return conf;

    }

    /**
     * Get rootPath from request,if not,find it from conf map.
     * @param request
     * @param conf
     * @return
     * @author Ternence
     * @create 2015年1月31日
     */
    public static String getRootPath(HttpServletRequest request, Map<String, Object> conf) {
        Object rootPath = request.getAttribute("rootPath");
        if (rootPath != null) {
            return rootPath + "" + File.separatorChar;
        } else {
            return conf.get("rootPath") + "";
        }
    }

    private void initEnv() throws FileNotFoundException, IOException {

        File file = new File(this.originalPath);

        if (!file.isAbsolute()) {
            file = new File(file.getAbsolutePath());
        }

        this.parentPath = file.getParent();

        String configContent = this.readFile(this.getConfigPath());

        try {
            JSONObject jsonConfig = new JSONObject(configContent);

            {
                String imageUrlPrefix = jsonConfig.optString("imageUrlPrefix");
                if (!StringUtils.startsWithIgnoreCase(imageUrlPrefix, "http://")
                    && !StringUtils.startsWithIgnoreCase(imageUrlPrefix, "https://")) {
                    if (StringUtils.startsWith(imageUrlPrefix, "/")) {
                        jsonConfig.put("imageUrlPrefix", this.reqPath + imageUrlPrefix);
                    } else {
                        jsonConfig.put("imageUrlPrefix", this.reqPath + "/" + imageUrlPrefix);
                    }
                }

                String imageManagerUrlPrefix = jsonConfig.optString("imageManagerUrlPrefix");
                if (!StringUtils.startsWithIgnoreCase(imageManagerUrlPrefix, "http://")
                    && !StringUtils.startsWithIgnoreCase(imageManagerUrlPrefix, "https://")) {
                    if (StringUtils.startsWith(imageManagerUrlPrefix, "/")) {
                        jsonConfig.put("imageManagerUrlPrefix", this.reqPath
                                                                + imageManagerUrlPrefix);
                    } else {
                        jsonConfig.put("imageManagerUrlPrefix", this.reqPath + "/"
                                                                + imageManagerUrlPrefix);
                    }
                }
            }

            this.jsonConfig = jsonConfig;
        } catch (Exception e) {
            this.jsonConfig = null;
        }

    }

    private String getConfigPath() {
        String path = this.getClass().getResource("/").getPath() + ConfigManager.configFileName;
        if (new File(path).exists()) {
            return path;
        } else {
            return this.parentPath + File.separator + ConfigManager.configFileName;
        }
    }

    private String[] getArray(String key) {

        JSONArray jsonArray = this.jsonConfig.getJSONArray(key);
        String[] result = new String[jsonArray.length()];

        for (int i = 0, len = jsonArray.length(); i < len; i++) {
            result[i] = jsonArray.getString(i);
        }

        return result;

    }

    private String readFile(String path) throws IOException {

        StringBuilder builder = new StringBuilder();

        try {

            InputStreamReader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
            BufferedReader bfReader = new BufferedReader(reader);

            String tmpContent = null;

            while ((tmpContent = bfReader.readLine()) != null) {
                builder.append(tmpContent);
            }

            bfReader.close();

        } catch (UnsupportedEncodingException e) {
            // 忽略
        }

        return this.filter(builder.toString());

    }

    // 过滤输入字符串, 剔除多行注释以及替换掉反斜杠
    private String filter(String input) {

        return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");

    }

    /**
     * Getter method for property <tt>parentPath</tt>.
     * 
     * @return property value of parentPath
     */
    public String getParentPath() {
        return parentPath;
    }

    /**
     * Setter method for property <tt>parentPath</tt>.
     * 
     * @param parentPath value to be assigned to property parentPath
     */
    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    /**
     * Getter method for property <tt>jsonConfig</tt>.
     * 
     * @return property value of jsonConfig
     */
    public JSONObject getJsonConfig() {
        return jsonConfig;
    }

    /**
     * Setter method for property <tt>jsonConfig</tt>.
     * 
     * @param jsonConfig value to be assigned to property jsonConfig
     */
    public void setJsonConfig(JSONObject jsonConfig) {
        this.jsonConfig = jsonConfig;
    }

    /**
     * Getter method for property <tt>rootPath</tt>.
     * 
     * @return property value of rootPath
     */
    public String getRootPath() {
        return rootPath;
    }

    /**
     * Getter method for property <tt>originalPath</tt>.
     * 
     * @return property value of originalPath
     */
    public String getOriginalPath() {
        return originalPath;
    }

    /**
     * Getter method for property <tt>contextPath</tt>.
     * 
     * @return property value of contextPath
     */
    public String getContextPath() {
        return contextPath;
    }

}
