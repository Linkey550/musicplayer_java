package MUSICPLAYER;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static UI.SWINGUI.maxmusic;
import static UI.SWINGUI.nowmusic;

public class wav {
    public static final int pause=0;
    public static final int play=1;
    public static final int next=2;
    public static final int before=3;
    public static final int off=4;
    public static final int resume=5;
    public  int musicflag=pause;
    public void AudioFlag(int flag) {
        musicflag = flag ;
    }
    private Clip clip;
//    private MusicPlayerListener listener;
   public static boolean isPaused = false;

    public static boolean isPlaying = false;
    public static boolean isPlayingend = false;
    public void play(String path) {
        try {
            File musicFile = new File(path);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
//            if (listener != null) {
//                clip.addLineListener(listener);
//            }
            clip.start();

            // 监听控制台输入，根据用户输入执行不同的操作
            Scanner scanner = new Scanner(System.in);
            String input;
            while (true) {
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("p")) {
                    pause();
                } else if (input.equalsIgnoreCase("r")) {
                    resume();
                } else if (input.equalsIgnoreCase("q")) {
                    stop();
                    break;
                }
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public void play(File file, wav player, File ALLfile, JButton play,JLabel MusicInformation) {
        try {
            File musicFile = file;
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            isPaused=false;
            isPlaying = true;
            isPlayingend=false;
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent lineEvent) {
                    if (lineEvent.getType() == LineEvent.Type.STOP&&player.isPlaying==true&&player.isPaused==false)
                    {
                        File nowmusicfile;
                        System.out.println("音乐播放结束");
//
                        if(nowmusic<maxmusic-1)
                        {
                            nowmusic=nowmusic+1;

                            player.play(mp3file.get_file(ALLfile,nowmusic),player,ALLfile,play,MusicInformation);
                            nowmusicfile=mp3file.get_file(ALLfile,nowmusic);
                            play.setText("暂停");
                            MusicInformation.setText(nowmusicfile.getName());
                        }
                        else if (nowmusic>=maxmusic-1){
                            nowmusic=0;

                            player.play(mp3file.get_file(ALLfile,nowmusic),player,ALLfile,play,MusicInformation);
                            nowmusicfile=mp3file.get_file(ALLfile,nowmusic);
                            play.setText("暂停");
                            MusicInformation.setText(nowmusicfile.getName());
                        }
                        // 在这里执行音乐播放结束后的操作
                    }
                }

            });
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public void pause() {
        if (clip != null && clip.isRunning()) {
            isPaused = true;
            isPlaying = true;
            isPlayingend=false;
            clip.stop();

            System.out.println("音乐已暂停");
        }
    }

    public void resume() {
        if (clip != null && isPaused) {
            isPaused = false;
            isPlaying = true;
            isPlayingend=false;
            clip.start();

            System.out.println("音乐已恢复播放");
        }
    }

    public void stop() {
        if (clip != null) {
            isPaused = false;
            isPlaying = false;
            isPlayingend=true;
            clip.stop();
            clip.close();

            System.out.println("音乐已停止");
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public static void main(String[] args) {
       // wav player = new wav();
       // player.play("src/MuiscLisk/ヨルシカ-雲と幽霊.wav");
    }

   // public abstract void update(LineEvent event);
//    public class MusicPlayerListener implements LineListener {
//        @Override
//        public void update(LineEvent event) {
//            if (event.getType() == LineEvent.Type.START) {
//                System.out.println("音乐播放开始");
//            } else if (event.getType() == LineEvent.Type.STOP) {
//                System.out.println("音乐播放结束");
//            } else if (event.getType() == LineEvent.Type.CLOSE) {
//                System.out.println("音乐播放关闭");
//            }
//        }
//    }

}


