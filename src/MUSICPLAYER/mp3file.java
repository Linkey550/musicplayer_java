package MUSICPLAYER;

import java.io.File;

public class mp3file {
    public static void mains(String[] args) {
        File file=new File("src/MuiscLisk");
        get_file(file,4);
    }
    public static File get_file(File file,int number) {
        File[] files = file.listFiles();
        int i = 0;
        File now_music = null;
        for (File f : files) {
            if (i == number) {
                now_music = f;
            }
            i++;
        }

        return now_music;
    }
}
