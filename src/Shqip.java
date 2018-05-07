/**
 * __Main Class
 * @author __Naisila Puka, Krisela Skënderi, Ana Peçini, Franc Gripshi___
 * @version __01/03/2018__
 */
import java.io.*;
public class Shqip 
{ 
  public static Ride[] readFile(String input){
    String fileName = input;
    String line = null;
    int vehicles = 0;
    int ridesNo = 0;
    int steps = 0;
    int startX= 0;
    int startY= 0;
    int finishX= 0;
    int finishY= 0;
    int sTime= 0;
    int fTime= 0;
    Ride []allRide = null;
    int count = 0;
    
    
    try {
            // FileReader reads text files in the default encoding.
      FileReader fileReader = 
      new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
      BufferedReader bufferedReader = 
      new BufferedReader(fileReader);
      
      line = bufferedReader.readLine();
      String[] splitStr = line.split("\\s+");
      vehicles = Integer.parseInt(splitStr[2]);
      ridesNo = Integer.parseInt(splitStr[3]);
      steps = Integer.parseInt(splitStr[5]);
      
      allRide = new Ride[ridesNo];

      while((line = bufferedReader.readLine()) != null) {
        splitStr = line.split("\\s+");
        startX = Integer.parseInt(splitStr[0]);
        startY = Integer.parseInt(splitStr[1]);
        finishX = Integer.parseInt(splitStr[2]);
        finishY = Integer.parseInt(splitStr[3]);
        sTime = Integer.parseInt(splitStr[4]);
        fTime = Integer.parseInt(splitStr[5]);
        Ride aRide = new Ride(startX,startY,finishX,finishY, sTime, fTime, vehicles,  steps);
        allRide[count] = aRide;
        count++;
      }   

            // Always close files.
      bufferedReader.close();         
    }
    catch(FileNotFoundException ex) {
      System.out.println(
        "Unable to open file '" + 
        fileName + "'");                
    }
    catch(IOException ex) {
      System.out.println(
        "Error reading file '" 
        + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
    }
    return allRide;
  }
  public static boolean available(int x1, int y1, int x2, int y2, int fTime, int currentTime)
  {
    int distanceTime = Math.abs(x1-x2) + Math.abs(y1-y2);
    int availableTime = fTime - currentTime;
    int isAv = availableTime - distanceTime;
    if(isAv < 0)
      return false;
    return true;
  }
  
  public static int bestOne(Ride[] arr, int currentTime, int x1, int y1)
  {
    int best = 0;
    Ride theBest = arr[0];
    int index = 0;
    for(int i = 0; i < arr.length; i++)
    {
      if(arr[i]!=null)
      {
        if(arr[i].getsTime() - currentTime > 0)
        {
          if(arr[i].getsTime() - currentTime - (Math.abs(x1-arr[i].getStartX()) + Math.abs(y1-arr[i].getStartY())) > 0)
          {
            theBest = arr[i];
            index = i;
            break;
          }      
        }
      }
    }
    return index;
  }
  
  public static void main(String [] args)
  {
    Ride[] allRide = readFile("a_example.in");// indexes of all available routes
    
    int F = allRide[0].getVehicles();
    int N = allRide.length;
    int TIME = allRide[0].getSteps();
    int startingX = 0;
    int startingY = 0;

    int[][] allVehicles = new int[F][N];
    
    for(int d = 0; d < F; d++)
    {
      for(int s = 0; s < N; s++)
        allVehicles[d][s] = -1;
    }
    
    
    int avaiableIndex = 0; //for each vehicle
    Ride[] availableRideArray;   // for each vehicle

    for( int f = 0; f < F; f++)
    { // for each vehicle
      
      int currentTime = 0; 
      startingX = 0;
      startingY = 0;
      int availableIndex = 0;
      
      while(currentTime < TIME)
      {
        availableRideArray = new Ride[N]; 
        
        for(int i = 0; i < N; i++)
        {
          if(allRide[i].isAvailable())
          {
            if(available(startingX, startingY, allRide[i].getFinishX(), allRide[i].getFinishY(), allRide[i].getfTime(), currentTime))
              availableRideArray[i] = allRide[i];
          }
        }
        
        int theBest = bestOne(availableRideArray, currentTime, startingX, startingY);
        allRide[theBest].setAvailable(false);
        
        allVehicles[f][availableIndex] = theBest;
        
        availableIndex++;
        
        startingX = allRide[theBest].getFinishX();
        startingY = allRide[theBest].getFinishY();
        
        if(allRide[theBest].getsTime() - (currentTime + Math.abs(startingX-allRide[theBest].getStartX()) + Math.abs(startingY-allRide[theBest].getStartY())) > 0)
        {
          currentTime += allRide[theBest].getsTime() - (currentTime + Math.abs(startingX-allRide[theBest].getStartX()) + Math.abs(startingY-allRide[theBest].getStartY()));
        } 
        
        currentTime += Math.abs(startingX-allRide[theBest].getFinishX()) + Math.abs(startingY-allRide[theBest].getFinishY());
      }
    }
    
    
    for(int j = 0; j < F; j++)
    {
      if(allVehicles[j][0] == -1)
      {
        System.out.println("0");
      }
      else
      {
        int count = 0;
        for(int c=0; c<N; c++)
        {
          if(allVehicles[j][c] != -1)
            count++;
          else
            break;
        }
        System.out.print(count + " ");
        for(int c=0; c<N; c++)
        {
          if(allVehicles[j][c] != -1)
            System.out.print(allVehicles[j][c] + " ");
          else
          {
            System.out.println("");
            break;
          }
          
        }
      }
    }
  }
}
