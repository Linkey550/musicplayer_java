package MUSICPLAYER;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class jmp123 {

    static File audioFile;

    public void AudioPlayer(File audiofile) {
        audioFile = audiofile;
    }


    public static void main(String[] args) {
        jmp123 test=new jmp123();
        test.AudioPlayer(new File("src/MuiscLisk/ヨルシカ - 雲と幽霊.mp3"));
        try {
            test.jmp123play();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //System.out.println(test.read());
    }


    public static void jmp123play()throws Exception{

        try {
            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(audioFile));
            Player player = new Player(buffer);//加载
            player.play();//开始播放
            System.out.println(buffer.read());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}



