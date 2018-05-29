package Others;

import java.awt.*;

public class Rectangle
{
  public double x;
  public double y;
  public int width;
  public int height;

   public Rectangle(double x, double y, int width, int height)
   {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
   }

   public void setLocation(double x, double y)
   {
      this.x = x;
      this.y = y;
   }


   public boolean overlaps(Rectangle r)
   {
      return (x      < r.x + r.width)   &&
             (x + width > r.x       )   &&
             (y      < r.y + r.height)   &&
             (y + height > r.y       );
   }

   public boolean contains(double mx, double my)
   {
      return (mx > x) && (mx < x+ width) && (my > y) && (my < y+ height);
   }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}