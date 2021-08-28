package main;

import object.KeyInput;
import object.Loader;
import world.Camera;
import tool.Test;
import render.Renderer;
import world.World;
import world.entity.Player;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel{

    public static final String NAME = "Myth";

    public static final String VERSION = "0.1";

    public static final int SCALE = 2;

    public static Game game;
    public Graphics2D g2d;
    public static int width=1200,height=800, render_fps = 60, tick_fps=30;
    public static double ticktime = (float)1000/(float)tick_fps;
    public static double rendertime = (float)1000/(float) render_fps;
    public static Renderer renderer;

    private static World world;

    public static Player player;

    public static KeyInput keyboard;

    private static JFrame w;

    private static long lrt;

    public static boolean rendering = false, error=false, debug=false;


    public static void main(String[] args) {

        if(java.lang.Runtime.version().feature()<8) {
            error(new Exception("[UPDATE Java] You need at least Java 8 to run this game, you have: Java "+java.lang.Runtime.version().feature()));
        } else {
            w = new JFrame();
            w.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().width*0.5-width*0.5),(int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.5-height*0.5));
            w.setSize(width,height);
            w.setTitle(NAME+" v"+VERSION);
            w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //w.setResizable(false);
            //w.setIconImage(Toolkit.getDefaultToolkit().getImage("./resource/icon.png"));

            game = new Game();
            w.add(game);
            keyboard = new KeyInput();
            w.addKeyListener(keyboard);

            w.setVisible(true);
            game.init();
            game.run();
        }

    }

    public static void error(Exception e) {
        error=true;
        JFrame j = new JFrame();
        j.setTitle(NAME+" v"+VERSION+"Error! :(");
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setIconImage(Toolkit.getDefaultToolkit().getImage("./resource/icon.png"));
        String msg = e.getMessage();
        if(msg==null) {
            msg="Internal game error ("+e.getStackTrace()[1].getFileName()+":"+e.getStackTrace()[1].getLineNumber()+")";
        }
        msg+=System.lineSeparator()+" [If this error persists, download a trusted version of the game]";
        JLabel l = new JLabel(msg,SwingConstants.CENTER);
        j.setLocation(400,400);
        j.setSize(30+msg.length()*8,80);
        j.add(l);
        j.setVisible(true);
        e.printStackTrace();
    }

    public static void alert(String msg) {
        JFrame j = new JFrame();
        j.setTitle("");
        j.setIconImage(Toolkit.getDefaultToolkit().getImage("./resource/icon.png"));
        JLabel l = new JLabel(msg,SwingConstants.CENTER);
        j.setLocation(400,400);
        j.setSize(30+msg.length()*8,80);
        j.add(l);
        j.setVisible(true);
    }


    public void init() {
        player = new Player(0,0,4,"player");
        Camera camera = new Camera(player);
        renderer = new Renderer(SCALE, camera);
        Loader.loadResources(renderer);
        world = Test.genWorld(player, camera);
        world.add(player);
    }

    public void run() {
        long l_ttime, l_rtime;
        try {
            l_rtime = 0;
            while(!error) {
                l_ttime = System.currentTimeMillis();
                tick();
                if(!rendering && System.currentTimeMillis()-l_rtime>=rendertime) {
                    l_rtime = System.currentTimeMillis();
                    new Thread(new Runnable(){
                        public void run(){
                            repaint();
                        }
                    }).start();
                    l_rtime = System.currentTimeMillis();
                }
                while(System.currentTimeMillis()-l_ttime<ticktime) {
                    Thread.sleep(5);
                }
            }
            while(error) {
                Thread.sleep(1000);
            }
        } catch(Exception e) {
            error(e);
        }
    }



    public void tick() {
        world.tick();
    }

    public void paint(Graphics g) {
        rendering = true;
        super.paintComponent(g);
        if(renderer!=null) {
            renderer.setGraphics((Graphics2D) g);
            renderer.renderBackground();
            try {
                world.render(renderer);
            } catch (Exception e) {
                //
            }
        }
        rendering = false;
    }
}
