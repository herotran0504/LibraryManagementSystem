package librarysystem.util;

import java.io.*;
import java.util.Properties;

import static librarysystem.util.Const.MEMBER_PROPERTY_KEY;
import static librarysystem.util.FileOperation.STORAGE_DIR;

public class IdManager {

    public static final String OUTPUT_DIR = STORAGE_DIR + "id.properties";

    public static String getNextID(String key) {
        Properties prop = new Properties();
        InputStream input = null;
        String newValue = null;
        try {
            input = new FileInputStream(OUTPUT_DIR);
            // load a properties file
            prop.load(input);

            // get the property value and print it out
            final int safeKeyInt = getSafeKeyInt(prop);
            newValue = String.valueOf(safeKeyInt + 1);
            updateID(MEMBER_PROPERTY_KEY, safeKeyInt);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
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
                    e.printStackTrace();
                }
            }

        }
        System.out.println("New::" + prop.getProperty(MEMBER_PROPERTY_KEY));
    }

    private static int getSafeKeyInt(Properties prop) {
        try {
            return Integer.parseInt(prop.getProperty(MEMBER_PROPERTY_KEY));
        } catch (Exception e) {
            return 0;
        }
    }

}
