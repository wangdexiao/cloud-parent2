 package com.baidu.ueditor;

 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONException;
 import com.alibaba.fastjson.JSONObject;
 import org.apache.commons.io.IOUtils;

 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.UnsupportedEncodingException;
 import java.util.HashMap;
 import java.util.Map;










 public final class ConfigManager
 {
   private final String rootPath;
   private final String originalPath;
   private final String contextPath;
   private static final String configFileName = "config.json";
   private String parentPath = null;
   private JSONObject jsonConfig = null;


   private static final String SCRAWL_FILE_NAME = "scrawl";


   private static final String REMOTE_FILE_NAME = "remote";



   private ConfigManager(String rootPath, String contextPath, String uri) throws FileNotFoundException, IOException {
     rootPath = rootPath.replace("\\", "/");

     this.rootPath = rootPath;
     this.contextPath = contextPath;

     if (contextPath.length() > 0) {
       this.originalPath = String.valueOf(this.rootPath) + uri.substring(contextPath.length());
     } else {
       this.originalPath = String.valueOf(this.rootPath) + uri;
     }

     initEnv();
   }










   public static ConfigManager getInstance(String rootPath, String contextPath, String uri) {
     try {
       return new ConfigManager(rootPath, contextPath, uri);
     } catch (Exception e) {
       return null;
     }
   }



   public boolean valid() {
     return (this.jsonConfig != null);
   }


   public JSONObject getAllConfig() {
     return this.jsonConfig;
   }



   public Map<String, Object> getConfig(int type) throws JSONException {
     Map<String, Object> conf = new HashMap<String, Object>();
     String savePath = null;

     switch (type) {

       case 4:
         conf.put("isBase64", "false");
         conf.put("maxSize", Long.valueOf(this.jsonConfig.getLong("fileMaxSize")));
         conf.put("allowFiles", getArray("fileAllowFiles"));
         conf.put("fieldName", this.jsonConfig.getString("fileFieldName"));
         savePath = this.jsonConfig.getString("filePathFormat");
         break;

       case 1:
         conf.put("isBase64", "false");
         conf.put("maxSize", Long.valueOf(this.jsonConfig.getLong("imageMaxSize")));
         conf.put("allowFiles", getArray("imageAllowFiles"));
         conf.put("fieldName", this.jsonConfig.getString("imageFieldName"));
         savePath = this.jsonConfig.getString("imagePathFormat");
         break;

       case 3:
         conf.put("maxSize", Long.valueOf(this.jsonConfig.getLong("videoMaxSize")));
         conf.put("allowFiles", getArray("videoAllowFiles"));
         conf.put("fieldName", this.jsonConfig.getString("videoFieldName"));
         savePath = this.jsonConfig.getString("videoPathFormat");
         break;

       case 2:
         conf.put("filename", "scrawl");
         conf.put("maxSize", Long.valueOf(this.jsonConfig.getLong("scrawlMaxSize")));
         conf.put("fieldName", this.jsonConfig.getString("scrawlFieldName"));
         conf.put("isBase64", "true");
         savePath = this.jsonConfig.getString("scrawlPathFormat");
         break;

       case 5:
         conf.put("filename", "remote");
         conf.put("filter", getArray("catcherLocalDomain"));
         conf.put("maxSize", Long.valueOf(this.jsonConfig.getLong("catcherMaxSize")));
         conf.put("allowFiles", getArray("catcherAllowFiles"));
         conf.put("fieldName", String.valueOf(this.jsonConfig.getString("catcherFieldName")) + "[]");
         savePath = this.jsonConfig.getString("catcherPathFormat");
         break;

       case 7:
         conf.put("allowFiles", getArray("imageManagerAllowFiles"));
         conf.put("dir", this.jsonConfig.getString("imageManagerListPath"));
         conf.put("count", Integer.valueOf(this.jsonConfig.getInteger("imageManagerListSize")));
         break;

       case 6:
         conf.put("allowFiles", getArray("fileManagerAllowFiles"));
         conf.put("dir", this.jsonConfig.getString("fileManagerListPath"));
         conf.put("count", Integer.valueOf(this.jsonConfig.getInteger("fileManagerListSize")));
         break;
     }


     conf.put("savePath", savePath);
     conf.put("rootPath", this.rootPath);
     conf.put("basePath", this.jsonConfig.get("basePath"));
     conf.put("imageManagerUrlPrefix", this.jsonConfig.get("imageManagerUrlPrefix"));

     return conf;
   }



   private void initEnv() throws FileNotFoundException, IOException {
     File file = new File(this.originalPath);

     if (!file.isAbsolute()) {
       file = new File(file.getAbsolutePath());
     }

     this.parentPath = file.getParent();

     String configContent = getConfigPath();

     try {
       JSONObject jsonConfig = JSONObject.parseObject(configContent);
       this.jsonConfig = jsonConfig;
     } catch (Exception e) {
       this.jsonConfig = null;
     }
   }


   private String getConfigPath() throws IOException {
     //todo 修改配置文件加载目录
//     return this.getClass().getClassLoader().getResourceAsStream("ueditor/config.json").getPath();
     return this.filter(IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("ueditor/config.json")));
   }


   private String[] getArray(String key) throws JSONException {
     JSONArray jsonArray = this.jsonConfig.getJSONArray(key);
     String[] result = new String[jsonArray.size()];

     for (int i = 0, len = jsonArray.size(); i < len; i++) {
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
     }
     catch (UnsupportedEncodingException unsupportedEncodingException) {}



     return filter(builder.toString());
   }




   private String filter(String input) {
     return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");
   }
 }
