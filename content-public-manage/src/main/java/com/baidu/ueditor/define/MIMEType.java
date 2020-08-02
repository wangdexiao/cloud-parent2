 package com.baidu.ueditor.define;

 import java.util.HashMap;
 import java.util.Map;

 public class MIMEType
 {
   public static final Map<String, String> types = new HashMap<String, String>()
     {
         {
             this.put("image/gif", ".gif");
             this.put("image/jpeg", ".jpg");
             this.put("image/jpg", ".jpg");
             this.put("image/png", ".png");
             this.put("image/bmp", ".bmp");
         }
     };




   public static String getSuffix(String mime) {
     return types.get(mime);
   }
 }

