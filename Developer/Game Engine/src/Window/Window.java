package Window;

import Input.KeyboardInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class Window extends JFrame implements  Runnable{

    private static volatile boolean running;
    private static Thread gameThread;
    private static BufferStrategy bufferStrategy;

    private   static GraphicsDevice graphicsDevice;
    private   static DisplayMode currentDisplayMode;
    protected static DisplayMode[] mode = graphicsDevice.getDisplayModes();
    protected static DisplayMode displayMode = mode[0];

    private static Canvas canvas;
    public static int width;
    public static int height;

    protected String title;
    protected Color background;

    public static KeyboardInput keyboard;


    public void start(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        running = true;

        int target = 60;
        double timePerTick = 1.0E9/target;  //Targeted frame
        double delta = 0;                   //Change in time

        long start =  System.nanoTime();
        long last  = start;
        double nsPerFrame;               //Nano seconds per frames

        long timer = 0;
        long fps = 0;

        while(running){

            //Calculate change in time
            start = System.nanoTime();
            nsPerFrame = last-start;

            delta += nsPerFrame/timePerTick;
            timer += nsPerFrame;
            last = start;

            if(delta >= 1){
                processInput();
                updateObject();
                renderFrame();
                fps++;
                delta--;
            }

            if(timer >= 1.0E9){
                System.out.println("FPS: " + fps);
                fps = 0;
                timer = 0;
            }
        }
    }

    protected void processInput(){
        if(keyboard != null)
            keyboard.poll();
    }

    protected void updateObject(){

    }

    protected void renderFrame() {
        do {
            do {
                Graphics g = null;
                try {
                    g = bufferStrategy.getDrawGraphics();
                    g.clearRect(0, 0, getWidth(), getHeight());
                    draw(g);
                } finally {
                    if (g != null) {
                        g.dispose();
                    }
                }
            } while (bufferStrategy.contentsRestored());
            bufferStrategy.show();
        } while (bufferStrategy.contentsLost());
    }

    protected void draw(Graphics g){

    }


    protected void createSizeWindow(){

        //Setup the canvas
        canvas = new Canvas();
        canvas.setSize(width,height);
        canvas.setBackground(background);
        canvas.setIgnoreRepaint(true);

        //Add keyboard listener to the canvas
        keyboard = new KeyboardInput();
        canvas.addKeyListener(keyboard);

        getContentPane().add(canvas);
        setTitle(title);
        setIgnoreRepaint(true);
        setResizable(false);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
    }

    protected void createFullWindow(){
        loadFullScreen();

        keyboard = new KeyboardInput();
        addKeyListener(keyboard);

        //Allow page flipping or double buffering
        createBufferStrategy( 2 );
        bufferStrategy = getBufferStrategy();

        onExit(); //Finished program if user pressed the escape button
    }


    private void loadFullScreen(){
        setIgnoreRepaint(true);
        setUndecorated(true);
        setBackground(background);

        GraphicsEnvironment ge =
                GraphicsEnvironment.getLocalGraphicsEnvironment();

        graphicsDevice = ge.getDefaultScreenDevice();

        currentDisplayMode = graphicsDevice.getDisplayMode();

        if( !graphicsDevice.isFullScreenSupported() ) {
            System.err.println( "ERROR: Not Supported!!!" );
            System.exit( 0 );
        }

        graphicsDevice.setFullScreenWindow(this);

        graphicsDevice.setDisplayMode(displayMode);
    }

    private void onExit(){
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if( e.getKeyCode() == KeyEvent.VK_ESCAPE ) {
                    shutDown();
                }
            }
        });
    }

    protected void shutDown() {
        try {
            running = false;
            gameThread.join();

            graphicsDevice.setDisplayMode( currentDisplayMode );
            graphicsDevice.setFullScreenWindow( null );
        } catch( InterruptedException e ) {
            System.out.println(e.getMessage());
        }
        System.exit( 0 );
    }


}
