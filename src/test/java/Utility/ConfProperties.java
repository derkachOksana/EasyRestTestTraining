package Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ConfProperties {
    private static FileInputStream fileInputStream;
    private static Properties props;

    static  {
        try {
            fileInputStream = new FileInputStream("src/test/resources/Conf.properties");
            props = new Properties();
            props.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                }
                catch (IOException e)   {
                    e.printStackTrace();
                }
        }
    }
    public static String getProperty(String key) {
        return props.getProperty(key);
    }

    public static String getCurrentDateTime()   {
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss");
        Date date = new Date();
        return format.format(date);
    }
}
