package librarysystem.utils;

import java.io.File;
import java.io.IOException;

public final class FileUtil {

    private FileUtil() {
    }

    public static boolean mkdirs(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return file.mkdirs();
        }
        return false;
    }

    @SuppressWarnings("unused")
    public static boolean createNewFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            return file.createNewFile();
        }
        return false;
    }

}
