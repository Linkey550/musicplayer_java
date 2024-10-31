package UI;


import MUSICPLAYER.MUSICPLAYER;
import MUSICPLAYER.mp3file;
import MUSICPLAYER.jmp123;

import javax.sound.sampled.LineEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import  MUSICPLAYER.wav;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static MUSICPLAYER.MUSICPLAYER.play;
import static MUSICPLAYER.wav.resume;


public class SWINGUI {
    //程序入口

     public static int nowmusic;
        public static void Nowmusic(int number) {
            nowmusic = number ;
        }

    public static int maxmusic;
    public static void Maxmusic(int number) {
        maxmusic = number ;
    }
    public static void Start(File file, wav player) {
        File[] musiclistuser = {};

        MUSICPLAYER TEST = new MUSICPLAYER();
        //创建窗口
        JFrame frame = new JFrame("Music player");   //Swing中的窗口叫做JFrame，对应的就是AWT中的Frame
        //它实际上就是Frame的子类，所以说我们之前怎么用的，现在怎么用就行了
        frame.setSize(500, 800);//窗口大小

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置直接结束程序
        frame.setLayout(null);//初始化窗口设置
        //设置面板
        GridLayout layout = new GridLayout();   //先设置整个窗口的布局
        frame.setLayout(new GridLayout());
        //第一面板

        JPanel firstPanel = new JPanel();
       // firstPanel.setBackground(java.awt.Color.RED);
        GridBagConstraints firstPanelConstraints = new GridBagConstraints();
        firstPanelConstraints.gridx = 0;
        firstPanelConstraints.gridy = 0;
        firstPanelConstraints.weightx = 1.0;
        firstPanelConstraints.weighty = 1.0;
        firstPanelConstraints.fill = GridBagConstraints.BOTH;
        frame.add(firstPanel, firstPanelConstraints);
        firstPanel.setLayout(new GridBagLayout());


        // 创建上方大面板

        JPanel topPanel = new JPanel();
       // topPanel.setBackground(java.awt.Color.RED);
        GridBagConstraints topConstraints = new GridBagConstraints();
        topConstraints.gridx = 0;
        topConstraints.gridy = 0;
        topConstraints.weightx = 1.0;
        topConstraints.weighty = 0.1;
        topConstraints.fill = GridBagConstraints.BOTH;
        firstPanel.add(topPanel, topConstraints);


        // 创建中间大面板
        JPanel centerPanel = new JPanel();
       // centerPanel.setBackground(java.awt.Color.GREEN);
        GridBagConstraints centerConstraints = new GridBagConstraints();
        centerConstraints.gridx = 0;
        centerConstraints.gridy = 1;
        centerConstraints.weightx = 1.0;
        centerConstraints.weighty = 0.87;
        centerConstraints.fill = GridBagConstraints.BOTH;
        firstPanel.add(centerPanel, centerConstraints);

        GridLayout centerlayout = new GridLayout();   //先设置整个窗口的布局
        centerlayout.setRows(3);     //设置行数为2，一会就会分成两行了
        centerPanel.setLayout(centerlayout);
        //音乐信息表示
        JPanel centertop = new JPanel();     //接着我们创建一下上半部分的面板和下半部分的面板
      //  centertop.setBackground(Color.PINK);   //添加一个背景颜色方便区分
        centerPanel.add(centertop);
        //专辑封面区
        JPanel center2 = new JPanel();     //接着我们创建一下上半部分的面板和下半部分的面板
      // center2.setBackground(Color.red);   //添加一个背景颜色方便区分
        centerPanel.add(center2);
        SWINGUI.insertImageToPanel(center2, "src/image/music.png", 200, 200);

        JPanel centerbottom = new JPanel();
       // centerbottom.setBackground(Color.ORANGE);
        centerPanel.add(centerbottom);
        //生成歌曲名字区
        JLabel MusicInformation = new JLabel("MUSIC NAME");
        centerbottom.add(MusicInformation, BorderLayout.NORTH);

        // 创建下方小面板
        JPanel bottomPanel = new JPanel();
      //  bottomPanel.setBackground(java.awt.Color.BLUE);
        GridBagConstraints bottomConstraints = new GridBagConstraints();
        bottomConstraints.gridx = 0;
        bottomConstraints.gridy = 2;
        bottomConstraints.weightx = 1.0;
        bottomConstraints.weighty = 0.03;
        bottomConstraints.fill = GridBagConstraints.BOTH;
        firstPanel.add(bottomPanel, bottomConstraints);
        bottomPanel.setLayout(new FlowLayout());//设置底部格式为流式

        //生成按钮
        JButton play = new JButton("播放");
        // 上一首按钮
        JButton before = new JButton("上一首");
        bottomPanel.add(before);
        before.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(0<nowmusic&&nowmusic<=maxmusic-1) {
                    nowmusic = nowmusic - 1;
                    player.stop();
                    player.play(mp3file.get_file(file, nowmusic), player, file, play, MusicInformation);
                    File nowmusicfile = mp3file.get_file(file, nowmusic);
                    MusicInformation.setText(nowmusicfile.getName());
                    play.setText("暂停");
                }
                else if (nowmusic<=0) {
                    nowmusic =maxmusic-1;
                    player.stop();
                    player.play(mp3file.get_file(file, nowmusic), player, file, play, MusicInformation);
                    File nowmusicfile = mp3file.get_file(file, nowmusic);
                    MusicInformation.setText(nowmusicfile.getName());
                    play.setText("暂停");
                }
            }
        });
        //播放/按钮

        bottomPanel.add(play);
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(player.isPaused==false&&player.isPlaying==false){

                  player.play(mp3file.get_file(file,nowmusic),player,file,play,MusicInformation);
                  File nowmusicfile=mp3file.get_file(file,nowmusic);
                  MusicInformation.setText(nowmusicfile.getName());
                  play.setText("暂停");
                }
                else if (player.isPaused==false&&player.isPlaying==true) {
                    player.pause();
                    play.setText("播放");
                }
                else if (player.isPaused==true&&player.isPlaying==true) {
                    player.resume();
                    play.setText("暂停");
                }

            }
        });

        //下一首按钮
        JButton next = new JButton("下一首");
        bottomPanel.add(next);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nowmusic<maxmusic-1) {
                    nowmusic = nowmusic + 1;
                    player.stop();
                    player.play(mp3file.get_file(file, nowmusic), player, file, play, MusicInformation);
                    File nowmusicfile = mp3file.get_file(file, nowmusic);
                    MusicInformation.setText(nowmusicfile.getName());
                    play.setText("暂停");
                }
                else if (nowmusic>=maxmusic-1) {
                    nowmusic =0;
                    player.stop();
                    player.play(mp3file.get_file(file, nowmusic), player, file, play, MusicInformation);
                    File nowmusicfile = mp3file.get_file(file, nowmusic);
                    MusicInformation.setText(nowmusicfile.getName());
                    play.setText("暂停");
                }
            }
        });

        //第二面板
        JPanel SecondPanel = new JPanel();
        //SecondPanel.setBackground(java.awt.Color.RED);
        GridBagConstraints SecondPanelConstraints = new GridBagConstraints();
        SecondPanelConstraints.gridx = 0;
        SecondPanelConstraints.gridy = 0;
        SecondPanelConstraints.weightx = 1.0;
        SecondPanelConstraints.weighty = 1.0;
        SecondPanelConstraints.fill = GridBagConstraints.BOTH;
        frame.add(SecondPanel, SecondPanelConstraints);
        SecondPanel.setLayout(new GridBagLayout());

        //创建滚动面板
        ScrollPane musiclist = new ScrollPane();// 设置滚动面板的约束条件
        GridBagConstraints listConstraints = new GridBagConstraints();
        listConstraints.gridx = 0;
        listConstraints.gridy = 0;
        listConstraints.weightx = 1.0;
        listConstraints.weighty = 1.0;
        listConstraints.fill = GridBagConstraints.BOTH;

        // 向SecondPanel添加滚动面板
        SecondPanel.add(musiclist, listConstraints);

        //创建列表
        List list = new List();
        //io.read(music,list,"src/MuiscLisk");
        addlist(file, list);

        musiclist.add(list);


        //生成多面板
        JTabbedPane pane = new JTabbedPane();
        pane.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        pane.addTab("state", firstPanel);
        pane.addTab("music list", SecondPanel);
        frame.add(pane);
        //pane.setLayout(new FlowLayout());


        //pane.addTab("二号", );
        frame.setVisible(true);//可视化


        list.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
//                Thread test=new Thread(Runnable{
//
//
//                })
                    player.stop();
                    File nowmusicfile=mp3file.get_file(file,(int)itemEvent.getItem());
                    MusicInformation.setText(nowmusicfile.getName());
                    player.play(mp3file.get_file(file,(int)itemEvent.getItem()),player,file,play,MusicInformation);
                     Nowmusic((int)itemEvent.getItem());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                player.pause();
                play.setText("播放");
            }
        });

    }

    //按钮创建方法





    //遍历文件加入列表
    public static void addlist(File file, List list) {
        File[] filebuff = file.listFiles();
        int musicmax=0;
        for (File f : filebuff) {
            if (f.isDirectory()) {
                addlist(f, list);
            }
            if (f.isFile()) {
                list.add(f.getName());
            }
            musicmax=musicmax+1;
        }
        Maxmusic(musicmax);
       // System.out.println(musicmax);
    }


    //引用图片
    public static void insertImageToPanel(JPanel panel, String imagePath, int width, int height) {
        //图片
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        ImageIcon scaledImageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(scaledImageIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);


        panel.setLayout(new BorderLayout());
        panel.add(imageLabel, BorderLayout.CENTER);
    }



}



