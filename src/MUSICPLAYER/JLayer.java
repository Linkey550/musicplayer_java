package MUSICPLAYER;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
public class JLayer extends AdvancedPlayer {
    public static final int pause=0;
    public static final int play=1;
    public static final int next=2;
    public static final int before=3;
    public static final int off=4;

    static File audioFile;
    public  int musicflag=pause;
    public void AudioFlag(int flag) {
        musicflag = flag ;
    }
    public JLayer(InputStream arg0) throws JavaLayerException {// 依然采用父类构造方法
        super(arg0);
    }

    public void quickPlay() throws JavaLayerException {// 自定义的播放方法
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            //if
            decodeFrame();// 播放一帧
        }
//        for (int i = 0; i < Integer.MAX_VALUE; i++) {
//            if(musicflag==play)
//            {
//                try {
//                    decodeFrame();// 播放一帧
//                } catch (JavaLayerException e) {
//                    e.printStackTrace();
//                }
//
//            }
//            else if(musicflag==pause)
//            {
//                    i=i-1;
//
//            }
//            else if(musicflag==off)
//            {
//                this.close();
//
//            }
//        }
    }

    public static void main(String[] args) throws FileNotFoundException, JavaLayerException, InterruptedException {
        boolean running=true;
        JLayer myplay = new JLayer(new FileInputStream("src/MuiscLisk/ヨルシカ - 雲と幽霊.mp3"));
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 在线程的代码中检查标志变量，决定是否继续执行任务


                try {
                    myplay.quickPlay();
                } catch (JavaLayerException e) {
                    throw new RuntimeException(e);
                }
                //myplay.AudioFlag(play);


            }
        });

        // 启动线程
        thread.start();
        //Thread.sleep(1000);
      //  myplay.AudioFlag(pause);

    }
}