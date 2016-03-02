package cn.figo.mydemo.utils;

/**
 * 一些系统常量的定义
 * @author gx
 */
public class Const {

    /**
     * @Fields DEBUG_LOG : 是否开启调试打印
     */
    public static final boolean DEBUG_LOG = true;

    public static final class SharedPreferences {
        /**
         * 用户信息
         */
        public static final String USER = "user";

        /**
         * 用户头像
         */
        public static final String NEW_AVATAR_FILE = "new_avata_File";

        /**
         * 地理位置信息
         */
        public static final String LOCATION = "location";

        public static final String HOME = "home";

    }

    //网络访问返回数据的格式定义
    public static String response_status = "status";
    public static String response_info = "info";
    public static String response_data = "data";

    /**
     * 热线电话
     */
    public static final String HOT_LINE = "hot_line";

    /**
     * 首次打开软件
     */
    public final static String First_loading = "First_loading";

    /**
     * 软件版本
     */
    public final static String APP_Version = "APP_Version";

}
