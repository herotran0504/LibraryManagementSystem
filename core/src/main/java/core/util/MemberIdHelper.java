package core.util;

import librarysystem.utils.FileUtil;
import librarysystem.utils.SimpleLogger;

import java.io.*;
import java.util.Properties;

import static librarysystem.utils.FileOperation.STORAGE_DIR;

public class MemberIdHelper {

    public static final String OUTPUT_DIR = STORAGE_DIR + "IDS";

    public static String getNextID(String key) {
        Properties prop = new Properties();
        InputStream input = null;
        String newValue = null;
        try {
            FileUtil.createNewFile(OUTPUT_DIR);
            input = new FileInputStream(OUTPUT_DIR);
            // load a properties file
            prop.load(input);

            // get the property value and print it out
            final int safeKeyInt = getSafeKeyInt(prop, key);
            newValue = String.valueOf(safeKeyInt + 1);
            updateID(key, safeKeyInt + 1);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    SimpleLogger.logError(e);
                }
            }
        }
        return newValue;
    }

    public static void updateID(String key, int newValue) {
        Properties prop = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream(OUTPUT_DIR);

            // set the properties value
            prop.setProperty(key, String.valueOf(newValue));

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    SimpleLogger.logError(e);
                }
            }

        }
        SimpleLogger.logDebug("New::" + prop.getProperty(key));
    }

    private static int getSafeKeyInt(Properties prop, String key) {
        try {
            String id = prop.getProperty(key);
            if(id == null) return 0;
            return Integer.parseInt(id);
        } catch (Exception e) {
            SimpleLogger.logError(e);
            return 0;
        }
    }

}
