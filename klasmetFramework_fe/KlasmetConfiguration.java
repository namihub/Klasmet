package klasmetFramework_fe;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 *
 * @author nami
 */
public final class KlasmetConfiguration {
    // START Configuration params

    private String server_ip = null;
    private int heartBeat = 0;
    private int server_tcp_port = 0;
    private int server_ftp_port = 0;
    private int server_udp_port = 0;
    private String separator = "/";
    private String osType = "linux";
    private String pathToSaveDownloadedFiles = null;
    private String runningDirectory = "";

    // END Configuration params
    public KlasmetConfiguration() {

        String osType = System.getProperty("os.name");
        if (osType.contains("Windows") || osType.contains("windows")) {
            setOsType("windows");
        }
    }

    public boolean readConfiguration() {

        try {
            String confRawPath = KlasmetConfiguration.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            String confPath = confRawPath.substring(0, confRawPath.substring(0, confRawPath.length() - 1).lastIndexOf(getSeparator()));// + separator + "config" + separator + "client.cfg";
            setRunningDirectory(confPath + getSeparator());
            if (confPath.endsWith("/lib")) {
                confPath = confPath.substring(0, confPath.length() - "/lib".length()) + getSeparator() + "config" + getSeparator() + "client.cfg";
            } else {
                confPath = confPath + getSeparator() + "config" + getSeparator() + "client.cfg";
            }
            FileInputStream in = new FileInputStream(confPath);
            BufferedReader ins = new BufferedReader(new InputStreamReader(in));
            int records = 0;
            while (ins.ready() == true) {
                records++;
                String line = ins.readLine();
                if (line.startsWith("l_path_to_save_downloaded_files=")) {
                    this.setPathToSaveDownloadedFiles(line.substring("l_path_to_save_downloaded_files=".length()).trim());
                } else if (line.startsWith("s_udp_port=")) {
                    this.setServer_udp_port((int) java.lang.Integer.valueOf(line.substring("s_udp_port=".length()).trim()));
                } else if (line.startsWith("s_tcp_port=")) {
                    this.setServer_tcp_port((int) java.lang.Integer.valueOf(line.substring("s_tcp_port=".length()).trim()));
                } else if (line.startsWith("s_ftp_port=")) {
                    this.setServer_ftp_port((int) java.lang.Integer.valueOf(line.substring("s_ftp_port=".length()).trim()));
                } else if (line.startsWith("server_ip=")) {
                    this.setServer_ip(java.lang.String.valueOf(line.substring("server_ip=".length()).trim()));
                } else if (line.startsWith("heartBeat=")) {
                    this.setHeartBeat((int) java.lang.Integer.valueOf(line.substring("heartBeat=".length()).trim()));
                }
            }
            try {
                ins.close();
                in.close();
            } catch (Exception e) {
            }
            return records >= 1;
        } catch (Exception e) {
            return false;
        }
    }

    public String getPathToSaveDownloadedFiles() {
        return pathToSaveDownloadedFiles;
    }

    protected void setPathToSaveDownloadedFiles(String pathToSaveDownloadedFiles) {
        this.pathToSaveDownloadedFiles = pathToSaveDownloadedFiles;
    }

    /**
     * @return the server_ip
     */
    protected String getServer_ip() {
        return server_ip;
    }

    /**
     * @param server_ip the server_ip to set
     */
    protected void setServer_ip(String server_ip) {
        this.server_ip = server_ip;
    }

    /**
     * @return the heartBeat
     */
    protected int getHeartBeat() {
        return heartBeat;
    }

    /**
     * @param heartBeat the heartBeat to set
     */
    protected void setHeartBeat(int heartBeat) {
        this.heartBeat = heartBeat;
    }

    /**
     * @return the server_tcp_port
     */
    protected int getServer_tcp_port() {
        return server_tcp_port;
    }

    /**
     * @param server_tcp_port the server_tcp_port to set
     */
    protected void setServer_tcp_port(int server_tcp_port) {
        this.server_tcp_port = server_tcp_port;
    }

    protected int getServer_ftp_port() {
        return server_ftp_port;
    }

    protected void setServer_ftp_port(int server_ftp_port) {
        this.server_ftp_port = server_ftp_port;
    }

    /**
     * @return the server_udp_port
     */
    protected int getServer_udp_port() {
        return server_udp_port;
    }

    /**
     * @param server_udp_port the server_udp_port to set
     */
    protected void setServer_udp_port(int server_udp_port) {
        this.server_udp_port = server_udp_port;
    }

    /**
     * @return the runningDirectory
     */
    public String getRunningDirectory() {
        return runningDirectory;
    }

    /**
     * @param runningDirectory the runningDirectory to set
     */
    public void setRunningDirectory(String runningDirectory) {
        this.runningDirectory = runningDirectory;
    }

    /**
     * @return the separator
     */
    public String getSeparator() {
        return separator;
    }

    /**
     * @param separator the separator to set
     */
    public void setSeparator(String separator) {
        this.separator = separator;
    }

    /**
     * @return the osType
     */
    public String getOsType() {
        return osType;
    }

    /**
     * @param osType the osType to set
     */
    public void setOsType(String osType) {
        this.osType = osType;
    }

}
