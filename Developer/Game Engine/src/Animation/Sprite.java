package Animation;

import Others.Rectangle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Sprite extends Rectangle {

    protected double initialX;                   //The initial X position of the sprite
    protected double initialY;                   //The initial Y position of the sprite

    protected ArrayList<Animation> animation;    //The animation of the sprite
    protected ArrayList<String>    action;       //The actions per animation of the sprite
    protected ArrayList<Integer>   length;       //The number of images for each animation
    protected ArrayList<Integer>   duration;     //The duration per animation of the sprite

    protected String               imgFilesPath; //The file path where all the images for the animation are located
    protected String               imgFormat;    //The format of the images for animation

    protected boolean moveRight;
    protected boolean moveLeft;
    protected boolean moveUp;
    protected boolean moveDown;

    protected double Vxi;                        //The initial velocity of the sprite in the x direction
    protected double Vxf;                        //The final velocity of the sprite in the x direction

    protected double Vyi;                        //The initial velocity of the sprite in the y direction
    protected double Vyf;                        //The final velocity of the sprite in the y direction

    protected double ax;                         //The acceleration of the sprite in the x direction
    protected double ay;                         //The acceleration of the sprite in the y direction

    protected double imageScale;                //The scale for the images.


    public Sprite(double x,double y,int width,int height,String imgFilesPath){
        super(x,y,width,height);

        initialX = x;
        initialY = y;

        this.imgFilesPath = imgFilesPath;

        animation = new ArrayList<>();
        action    = new ArrayList<>();
        length    = new ArrayList<>();
        duration  = new ArrayList<>();

        moveRight = false;
        moveLeft  = false;
        moveDown  = false;
        moveUp    = false;

        imgFormat = ".png";

        Vxi = 0;
        Vxf = 0;

        Vyi = 0;
        Vyf = 0;

        ax  = 0;
        ay  = 0;

        imageScale = 1;
    }

    public abstract void processInput();
    public abstract void update();
    public abstract void draw(Graphics g);


    public void loadAnimation(){
        for(int i = 0; i<action.size(); i++){
            Animation an = new Animation(imgFilesPath + action.get(i),
                    imgFormat, length.get(i), duration.get(i));
            animation.add(an);
        }
    }

    public void setAction(String[] obj){
        for(int i = 0; i<obj.length; i++)
            action.add(obj[i]);
    }

    public void setAction(ArrayList<String> obj){
        for(int i = 0; i<obj.size(); i++)
            action.add(obj.get(i));
    }

    public void addAction(String str){
        action.add(str);
    }

    public void setLength(int[] obj){
        for(int i = 0; i<obj.length; i++)
            length.add(obj[i]);
    }

    public void setLength(ArrayList<Integer> obj){
        for(int i = 0; i<obj.size(); i++)
            length.add(obj.get(i));
    }

    public void addLength(int num){
        length.add(num);
    }

    public void setDuration(int[] obj){
        for(int i = 0; i<obj.length; i++)
            duration.add(obj[i]);
    }

    public void setDuration(ArrayList<Integer> obj){
        for(int i = 0; i<obj.size(); i++)
            duration.add(obj.get(i));
    }

    public void addDuration(int num){
        duration.add(num);
    }

    public void setImgFilesPath(String str){
        imgFilesPath = str;
    }

    public BufferedImage getNextImg(int i){
        return animation.get(i).nextImage();
    }

    public boolean animationOver(int i){
        return animation.get(i).isAnimationOver();
    }

    public void reset(int i){
        animation.get(i).reset();
    }

    public double getInitialX() {
        return initialX;
    }

    public void setInitialX(double initialX) {
        this.initialX = initialX;
    }

    public double getInitialY() {
        return initialY;
    }

    public void setInitialY(double initialY) {
        this.initialY = initialY;
    }

    public String getImgFilesPath() {
        return imgFilesPath;
    }

    public String getImgFormat() {
        return imgFormat;
    }

    public void setImgFormat(String imgFormat) {
        this.imgFormat = imgFormat;
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public boolean isMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public boolean isMoveUp() {
        return moveUp;
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    public boolean isMoveDown() {
        return moveDown;
    }

    public void setMoveDown(boolean moveDown) {
        this.moveDown = moveDown;
    }

    public double getVxi() {
        return Vxi;
    }

    public void setVxi(double vxi) {
        Vxi = vxi;
    }

    public double getVxf() {
        return Vxf;
    }

    public void setVxf(double vxf) {
        Vxf = vxf;
    }

    public double getVyi() {
        return Vyi;
    }

    public void setVyi(double vyi) {
        Vyi = vyi;
    }

    public double getVyf() {
        return Vyf;
    }

    public void setVyf(double vyf) {
        Vyf = vyf;
    }

    public double getAx() {
        return ax;
    }

    public void setAx(double ax) {
        this.ax = ax;
    }

    public double getAy() {
        return ay;
    }

    public void setAy(double ay) {
        this.ay = ay;
    }

    public double getImageScale() {
        return imageScale;
    }

    public void setImageScale(double imageScale) {
        this.imageScale = imageScale;
    }
}
