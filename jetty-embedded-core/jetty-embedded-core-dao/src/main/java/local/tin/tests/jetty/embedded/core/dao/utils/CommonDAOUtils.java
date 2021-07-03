package local.tin.tests.jetty.embedded.core.dao.utils;

import java.sql.Timestamp;
import java.util.Date;

public class CommonDAOUtils {
    
    private CommonDAOUtils() {
    }
    
    public static CommonDAOUtils getInstance() {
        return DAOUtilsHolder.INSTANCE;
    }
    
    private static class DAOUtilsHolder {

        private static final CommonDAOUtils INSTANCE = new CommonDAOUtils();
    }
    
    public Timestamp getTimeStampFromDate(Date date) {
        if (date == null) {
            return null;
        }
        return new Timestamp(date.getTime());
    }
    
    public java.sql.Date getSQLDateFromDate(Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }   

}