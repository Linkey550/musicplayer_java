import MUSICPLAYER.mp3file;
import UI.SWINGUI;

//import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;

import MUSICPLAYER.wav;

import javax.sound.sampled.LineEvent;

import static MUSICPLAYER.wav.isPlayingend;
import static UI.SWINGUI.maxmusic;
import static UI.SWINGUI.nowmusic;

public class Main {

    public  static final int pause=0;
    public static final int play=1;
    public static final int next=2;
    public static final int before=3;
    public static final int off=4;

    static File audioFile;

    public static int musicflag=pause;
    public static int musicnumber;


    public void AudioFlag(int flag) {
        musicflag = flag ;
    }
//    public static int nowmusic;
//    public void Nowmusic(int number) {
//        nowmusic = number ;
//    }
    public static void main(String[] args) throws InterruptedException, FileNotFoundException, JavaLayerException {


        File usermusiclisk =new File("src/MuiscLisk");
        wav player = new wav();

        SWINGUI user=new SWINGUI();

        user.Start(usermusiclisk,player);
      //  System.out.println(maxmusic);

//        while (true)
//        {
//            System.out.println(nowmusic);
//
//        }
//        while (true) {
//            JLayer player=new JLayer(new FileInputStream("src/MuiscLisk/ヨルシカ - 雲と幽霊.mp3"));
//           if(musicflag==play)
//           {
//               //player.decodeFrame();
//
//           }
//        }




    }
}