package th.co.erp.sme.util;


public class Constant {


    public static String ROLE_LIST = "ROLE_LIST";
    public static String OBJECT_LIST = "OBJECT_LIST";
    public static String DELETED = "DELETED";
    public static String UPDATE = "UPDATE";
    public static String FLAG_Y = "Y";
    public static String FLAG_N = "N";

    public static String PRIVATE = "PRIVATE";
    public static String FRIEND_STR = "FRIEND";
    private Constant() {
        throw new IllegalArgumentException("THIS CLASS IS CONSTANT");
    }

    public static class FRIEND{

        private FRIEND(){
            throw new IllegalArgumentException("THIS CLASS IS CONSTANT");
        }
        public static String STATUS_FRIEND_SUCCESS = "SUCCESS";
        public static String STATUS_FRIEND_PENDING = "PENDING";
        public static String STATUS_FRIEND_CANCEL = "CANCEL";

    }

    public static class IgnoreField {
        private IgnoreField() {
        }

        public static final String UPDATE_BY = "updateBy";
        public static final String UPDATE_DATE ="updateDate";
    }


}
