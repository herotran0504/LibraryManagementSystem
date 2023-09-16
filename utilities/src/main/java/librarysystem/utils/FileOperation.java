package librarysystem.utils;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FileOperation {

    public enum StorageType {
        BOOKS, MEMBERS, USERS, CHECKOUT
    }

    public static final String SLASH = System.getProperty("file.separator");

    public static final String STORAGE_DIR = System.getProperty("user.dir") + SLASH + "storage" + SLASH;

    public static void saveToStorage(StorageType type, Object ob) {
        ObjectOutputStream out = null;
        try {
            FileUtil.mkdirs(STORAGE_DIR);
            Path path = FileSystems.getDefault().getPath(STORAGE_DIR, type.toString());
            out = new ObjectOutputStream(Files.newOutputStream(path));
            out.writeObject(ob);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public static Object readFromStorage(StorageType type) throws Exception {
        ObjectInputStream in = null;
        Object retVal = null;
        try {
            Path path = FileSystems.getDefault().getPath(STORAGE_DIR, type.toString());

            File file = new File(path.toString());
            if (file.length() != 0) { //Check Empty File Or Not
                in = new ObjectInputStream(Files.newInputStream(path));
                retVal = in.readObject();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                }
            }
        }
        return retVal;
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> readFromStorageAsMap(StorageType type) throws Exception {
        final Map<K, V> map = (Map<K, V>) readFromStorage(type);
        return map == null ? new HashMap<>() : map;
    }

}
