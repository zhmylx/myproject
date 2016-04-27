 package utils;
 
 import java.math.BigDecimal;
 import java.text.DecimalFormat;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.Map;
 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;
 
 public final class Datas
 {
   private static final Log logger = LogFactory.getLog(Datas.class);
   private static final Object[] ZERO_OBJECT_ARRAY = new Object[0];
   private static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("###0");
   private static final DecimalFormat DOUBLE_FORMAT = new DecimalFormat("###0.00");
 
   private static final String[] TRUE_ARRAY = { "y", "yes", "true", "t", "是", "1" };
 
   private static final String[] FALSE_ARRAY = { "n", "no", "false", "f", "否", "0" };
 
   private static Map<Object, String> supportTypeMap = new HashMap();
 
   public static void addSupportType(Object clazz)
   {
     supportTypeMap.put(clazz, "");
   }
 
   public static String zeroToEmpty(int value)
   {
     return (value == 0) ? "" : String.valueOf(value);
   }
 
   public static String zeroToEmpty(double value)
   {
     return (value == 0.0D) ? "" : String.valueOf(value);
   }
 
   public static String nullToEmpty(String str)
   {
     return (str == null) ? "" : str;
   }
 
   public static String emptyToNull(String str)
   {
     if ((str == null) || (str.trim().length() == 0)) {
       return null;
     }
     return str;
   }
 
   public static String dbNullToEmpty(String str)
   {
     if ((str == null) || (str.equalsIgnoreCase("null"))) {
       return "";
     }
     return str;
   }
 
   public static String nullToZero(String str)
   {
     if ((str == null) || (str.trim().length() == 0)) {
       return "0";
     }
     return str;
   }
 
   public static String getBooleanDescribe(String str)
   {
     if (str == null) {
       throw new IllegalArgumentException("argument is null");
     }
     String retValue = null;
     if (str.trim().equals("")) {
       retValue = "";
     }
     for (int i = 0; i < TRUE_ARRAY.length; ++i) {
       if (str.equalsIgnoreCase(TRUE_ARRAY[i])) {
         retValue = "是";
         break;
       }
     }
     for (int i = 0; i < FALSE_ARRAY.length; ++i) {
       if (str.equalsIgnoreCase(FALSE_ARRAY[i])) {
         retValue = "否";
         break;
       }
     }
     if (retValue == null) {
       throw new IllegalArgumentException("argument not in ('y','n','yes','no','true','false','t','f','是','否','1','0','')");
     }
 
     return retValue;
   }
 
   public static boolean getBoolean(String str)
   {
     if (str == null) {
       throw new IllegalArgumentException("argument is null");
     }
     for (int i = 0; i < TRUE_ARRAY.length; ++i) {
       if (str.equalsIgnoreCase(TRUE_ARRAY[i])) {
         return true;
       }
     }
     for (int i = 0; i < FALSE_ARRAY.length; ++i) {
       if (str.equalsIgnoreCase(FALSE_ARRAY[i])) {
         return false;
       }
     }
     if (str.trim().equals("")) {
       return false;
     }
     throw new IllegalArgumentException("argument not in ('y','n','yes','no','true','false','t','f','是','否','1','0','')");
   }
 
   public static String getBooleanDescribe(boolean value)
   {
     if (value) {
       return getBooleanDescribe("true");
     }
     return getBooleanDescribe("false");
   }
 
   public static int compareByValue(String str1, String str2)
   {
     return new BigDecimal(str1).compareTo(new BigDecimal(str2));
   }
 
   public static double round(double value, int scale)
   {
     BigDecimal obj = new BigDecimal(Double.toString(value));
     return obj.divide(BigDecimal.ONE, scale, 4).doubleValue();
   }
 
   public static Integer getInteger(Object object)
   {
     Integer value = null;
     if (object != null) {
       value = Integer.valueOf(object.toString());
     }
     return value;
   }
 
   public static Long getLong(Object object)
   {
     Long value = null;
     if (object != null) {
       value = Long.valueOf(object.toString());
     }
     return value;
   }
 
   public static Double getDouble(Object object)
   {
     Double _double = null;
     if (object != null) {
       _double = new Double(object.toString());
     }
     return _double;
   }
 
   public static String getString(Object object)
   {
     String string = null;
     if (object != null) {
       string = object.toString();
     }
     return string;
   }
 
   public static String join(Object[] arguments)
   {
     return concat(arguments);
   }
 
   public static String getPlainNumber(Integer value) {
     if (value == null) {
       return "";
     }
     return NUMBER_FORMAT.format(value);
   }
 
   public static String getPlainNumber(Long value) {
     if (value == null) {
       return "";
     }
     return NUMBER_FORMAT.format(value);
   }
 
   public static String getPlainNumber(Double value) {
     if (value == null) {
       return "";
     }
     return DOUBLE_FORMAT.format(value);
   }
 
   private static String concat(Object[] sources)
   {
     if (sources == null) {
       return "";
     }
     if (sources.length == 1) {
       return String.valueOf(sources[0]);
     }
     StringBuilder sb = new StringBuilder();
     for (Object o : sources) {
       sb.append(o);
     }
     return sb.toString();
   }
 
   static
   {
     supportTypeMap.put(Integer.TYPE, "");
     supportTypeMap.put(Long.TYPE, "");
     supportTypeMap.put(Double.TYPE, "");
     supportTypeMap.put(Boolean.TYPE, "");
     supportTypeMap.put(Integer.class, "");
     supportTypeMap.put(Long.class, "");
     supportTypeMap.put(Double.class, "");
     supportTypeMap.put(BigDecimal.class, "");
     supportTypeMap.put(String.class, "");
     supportTypeMap.put(Date.class, "");
     supportTypeMap.put(Boolean.class, "");
   }
 }

