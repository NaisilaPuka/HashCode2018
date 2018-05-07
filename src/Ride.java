/**
 * __Ride Class
 * @author __Naisila Puka, Krisela Skënderi, Ana Peçini, Franc Gripshi___
 * @version __01/03/2018__
 */
public class Ride{
  private int startX;
  private int startY;
  private int finishX;
  private int finishY;
  private int sTime;
  private int fTime;
  private int vehicles;
  private int steps;
  private boolean available;
  
  public Ride(int startX,int startY,int finishX, int finishY, int sTime, int fTime,int vehicles, int steps){
    this.startX = startX;
    this.startY = startY;
    this.finishX = finishX;
    this.finishY = finishY;
    this.sTime = sTime;
    this.fTime = fTime;
    this.available = true;
  }
  
  public int getStartX(){
    return startX;
  }
  
  public int getStartY(){
    return startY;
  }
  
  public int getFinishX(){
    return finishX;
  }
  
  public int getFinishY(){
    return finishY;
  }
  
  public int getsTime(){
    return sTime;
  }
  
  public int getfTime(){
    return fTime;
  }
  
  public int getVehicles(){
    return vehicles;
  }
  
  public int getSteps(){
    return steps;
  }
  
  public void setAvailable(boolean bln){
    this.available = bln;
  }
  
  public boolean isAvailable(){
    return available;
  }
}