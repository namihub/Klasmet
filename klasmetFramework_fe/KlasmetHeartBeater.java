package klasmetFramework_fe;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author nami
 */
final class KlasmetHeartBeater {

    private final Timer timer;
    private final KlasmetTcpStreamer tcpStreamer;
    private final String heartBeatMessage;

    protected KlasmetHeartBeater(KlasmetTcpStreamer tcp_streamer, String heartBeatMessage) {
        timer = new Timer();
        this.tcpStreamer = tcp_streamer;
        this.heartBeatMessage = heartBeatMessage;

    }

    protected void startHearBeating(int seconds) {
        timer.schedule(new RemindTask(), seconds, seconds);
    }

    final class RemindTask extends TimerTask {

        @Override
        public void run() {
            KlasmetMessagingHeartBeatMsg heartBeat = new KlasmetMessagingHeartBeatMsg(heartBeatMessage);
            try {
                if (tcpStreamer.write(heartBeat)) {
                } else {//broken connection
                    timer.cancel();
                }
            } catch (IOException ex) {
                timer.cancel();
            }
        }
    }
}
