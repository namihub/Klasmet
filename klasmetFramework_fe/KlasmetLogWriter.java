package klasmetFramework_fe;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author nami
 */
public final class KlasmetLogWriter {

    private String separator = "/";

    protected KlasmetLogWriter() {
        String osType = System.getProperty("os.name");
        switch (osType) {
            case "Windows":
                separator = "\\";
                break;
        }
    }

    public synchronized void writeLog(int logLevel, String logger, String log) {//0 for operation log, 1 for debug log
        try {
            Date date = new Date();
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            String logRawPath = KlasmetLogWriter.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            String logPath = logRawPath.substring(0, logRawPath.substring(0, logRawPath.length() - 1).lastIndexOf(separator));// + separator + "log" + separator;
            if (logPath.endsWith("/lib")) {
                logPath = logPath.substring(0, logPath.length() - "/lib".length()) + separator + "log" + separator;
            } else {
                logPath = logPath + separator + "log" + separator;
            }
            String logType = "---";
            if (logLevel == 0) {
                logType = "INFO";
            } else if (logLevel == 1) {
                logType = "ERROR";
            }

            try (FileWriter fileOut = new FileWriter(logPath + "emerux.log", true)) {
                fileOut.append("=====" + logType + "=====" + timeStamp + "\n");
                fileOut.append("message from module :: " + logger + "\n");
                fileOut.append(log + "\n");
            }
        } catch (Exception e) {
            System.out.println("LogWriter.writeLog(): \n" + e.toString());
        }
    }
}
