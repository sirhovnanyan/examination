package exam.utils;

public class IOUtils {
    public IOUtils() {
        throw new UnsupportedOperationException();
    }

    public static void closeAll(AutoCloseable... closeables) {
        if (closeables == null || closeables.length == 0) {
            return;
        }

        for (AutoCloseable closeable : closeables) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

}
