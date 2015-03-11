/*Spring 2014:

Build an N by M chessboard, each square can be "empty" or "alive." Start with some configuration of squares (you should try a number of various starting  configurations, you may wish to do so randomly). Proceed according to the following rules and observe what happens (do all cells become empty, does it cycle, reach a stable state?) [Choose N, M to be at  least 20].

a) Define adjacent to be N, S, E, W. An empty cell with 3 live neighbors comes alive. A live cell with less than 2 or more than 3 live neighbors becomes empty.

b) Same, but change numbers to 2, 2, 2

c) Optional: Same as a) but change so that neighbors are  N, S, E, W, and diagonal.

Try enough starting configurations and run for enough iterations so that you observe different behaviors.

For each of a, b, c you try, on iteration 1000, inject an "infection" into a randomly chosen live cell. An infected live cell spreads its infection to its live neighbors (who will show as infected on the next iteration) and then dies. Observe how this effects the process.
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color.*;
import javax.swing.JOptionPane;
import javax.swing.JComboBox.*;
import java.applet.*;
import javax.swing.border.*;
import javax.swing.Timer;
import java.util.*;
import java.lang.Math.*;
/* Not needed so far
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
*/


public class Cell extends JButton{
   Cell north=null;
   Cell northwest=null;
   Cell northeast=null;
   Cell south=null;
   Cell southeast=null;
   Cell southwest=null;
   Cell west=null;
   Cell east=null;
   int birthMin=3;
   int chokeMin=3;
   int myX;
   int myY;
   int neigbors;
/* a) Define adjacent to be N, S, E, W. An empty cell with 3 live neighbors comes alive. A live cell with less than 2 or more than 3 live neighbors becomes empty.
   b) Same, but change numbers to 2, 2, 2
*/     
   int lonelyMin=2;
   boolean diagNeighbors=true;
   boolean alive;
   boolean infected;
   boolean nextAlive;
   boolean nextInfected;
   
   int turnCounter;
   Color myColor=Color.GRAY;
   ActionListener cellListener=
      new ActionListener(){
         @Override
               public void actionPerformed(ActionEvent e){
            if (alive==false)
            {
               myColor=Color.YELLOW;
               alive=true;
               setBackground(myColor);
               nextAlive=true;
               /*String me=("I am:"+myX+" , "+myY+"\n"+
                     "my north neigbor is:"+north.myX+" , "+north.myY+ "and is alive"+north.alive+"\n"+                 
                     "my south neigbor is:"+south.myX+" , "+south.myY+"and is alive"+south.alive+"\n"+
                     "my east neigbor is:"+east.myX+" , "+east.myY+"and is alive"+east.alive+"\n"+
                     "my west neigbor is:"+west.myX+" , "+west.myY+"and is alive"+west.alive+"\n"+
                     "I am alive: "+alive+" I am infected: "+ infected+"\n");
               //System.out.println(me);
               */      
            }   
            else if (alive==true&&infected==false)
            {
               myColor=Color.RED;
               infected=true;
               setBackground(myColor);
               //System.out.println("I"+  myX+ " , "+ myY+" am infected");                
            }
            else if (alive==true&&infected==true)
            {
               myColor=Color.GRAY;
               infected=false;
               alive=false;
               nextAlive=false;
               setBackground(myColor);      
            }
               
         }
      };
 
 
 
 
      
   Cell(){
   
      setBackground(myColor); 
      
      addActionListener(cellListener);
            
   
      
      //ToDo add action listener for mouse clicks to set alive for true
      //ToDo
   }
////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////methods//////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////
   void checkNext()
   {//begin check nect method
      neigbors=0;
            //Check if a neigbor is infected
      if (alive&&!infected)
      {         
         try {
            if (north.infected) nextInfected=true;} 
         catch (Exception e) {}
         try {
            if (south.infected) nextInfected=true;} 
         catch (Exception e) {}
         try {
            if (east.infected) nextInfected=true;}  
         catch (Exception e) {}
         try {
            if (west.infected) nextInfected=true;}  
         catch (Exception e) {}
         if (diagNeighbors)
         {
            try {
               if (northeast.infected)
                  nextInfected=true;
            }  
            catch (Exception e) {}
            try {
               if (northwest.infected) 
                  nextInfected=true;
            }  
            catch (Exception e) {}
         
            try {
               if (southwest.infected) nextInfected=true;}  
            catch (Exception e) {}
            try {
               if (southeast.infected) nextInfected=true;}  
            catch (Exception e) {}
         }
      }//end checking for infection
      
      try {
         if (north.alive)
            neigbors++;
      }  
      catch (Exception e) {}
      
      try {
         if (south.alive) 
            neigbors++;
      }  
      catch (Exception e) {}
     
      try {
         if (east.alive) 
            neigbors++;
      }  
      catch (Exception e) {}
      
      try {
         if (west.alive) 
            neigbors++;
      }  
      catch (Exception e) {}
      
      if (diagNeighbors)
      {
         try {
            if (northeast.alive) 
               neigbors++;
         }  
         catch (Exception e) {}
         
         try {
            if (northwest.alive) 
               neigbors++;
         }  
         catch (Exception e) {}
         
         try {
            if (southwest.alive) 
               neigbors++;
         }  
         catch (Exception e) {}
         
         try {
            if (southeast.alive) 
               neigbors++;
         }  
         catch (Exception e) {}
      }
      
      if (neigbors>0)
         //System.out.println(myX+" "+myY+"has "+neigbors+"neigbors.\n");
      
         if (infected) 
         {
            nextAlive=false; 
            return;
         }
      
   
   /*<TODO check logic for < or <equals conditions*/
      if ((!alive)&&(neigbors==birthMin))  
      {
         nextAlive=true;
         //System.out.println("I "+ myX+ " , "+ myY+" will be born");
      }
      if (alive&&(neigbors>chokeMin))
      {
         nextAlive=false;
         //System.out.println("I"+  myX+ " , "+ myY+" will be crowded out. I have"+ neigbors+"neigbors");               
         return;
      }//fix this only happens after alive
      if(alive&&(neigbors>=lonelyMin))
      {
         nextAlive=true; 
         if (alive)
         {
            //System.out.println("I"+ myX+ " , "+ myY+ "will survive" );
         }
         return;
      }
      
      
      if (neigbors<lonelyMin)
      {
         nextAlive=false; 
         if (alive)
         {
            //System.out.println("I"+ myX+ " , "+ myY+ "will die of lonliness crowded out. I have "+neigbors+"neigbors" );
         }
         return;
      }
   
   /* a) Define adjacent to be N, S, E, W. An empty cell with 3 live neighbors comes alive. A live cell with less than 2 or more than 3 live neighbors becomes empty.
   b) Same, but change numbers to 2, 2, 2*/     
   }////end checkNext method
/////////////////////////////////////////////////////////////////////////////////////////   
   public void update(){
      alive=nextAlive;
         
         
      if (infected) 
      {  
         infected=false; 
         nextInfected=false;
         nextAlive=false;//the cell dies
         myColor=Color.GRAY;
         setBackground(Color.GRAY);
         alive=false;
         repaint();
      }
      
      if (nextInfected)
      {
         infected=true;
         nextAlive=false;
         myColor=Color.RED;
         setBackground(Color.RED);  
      }
      
      if (infected) 
      {
         setBackground(Color.RED);
      
      }
      else if (nextAlive)
      {
         setBackground(Color.YELLOW);
         myColor=Color.YELLOW;
         alive=true;      
      }
      else 
      {
         alive=false;
         setBackground(Color.GRAY);
         myColor=Color.GRAY;
      }
      repaint();
   }/// end update method
   public String toString()
   {
   
      String me=("I am:"+myX+" , "+myY+" "+
                     "my north neigbor is:"+north.myX+" , "+north.myY+" "+                 
                     "my south neigbor is:"+south.myX+" , "+south.myY+" "+
                     "my east neigbor is:"+east.myX+" , "+east.myY+" "+
                     "my west neigbor is:"+west.myX+" , "+west.myY+" "+
                     "I am alive: "+alive+" I am infected: "+ infected+" ");
      return (me);
   }                  

     
   
}///////////End Class Cell   
             
//GAMEBOARD class////////////////////////////////////////////////             
class Gameboard extends JFrame{
   int x=200;
   int y=200;
   Cell[][] myArray=new Cell[x][y];
   int delay=20;
   int count=0;
   int turnCounter=0;
   boolean complete=true;
   JPanel controlPanel3=new JPanel();
   String infectInsertedFlag=" Infected cell not yet inserted.                        ";
   JTextField turnCountFlag= new JTextField ("Turn #: "+turnCounter+infectInsertedFlag); 
   JPanel controlPanel=new JPanel();
   ActionListener timerListener = 
      new ActionListener(){
      
      
         
         public void actionPerformed(ActionEvent e)
         {
            if(complete)
            {
               complete=false;
               step();
            }
            
               
         }      
         
         
      
      };

   public Timer timer= new Timer(delay, timerListener);
   
   

/*Control panel subcomponents*/

   JButton start=new JButton("Start");
                        
   JButton stop=new JButton("Stop");
            
   JButton reset=new JButton("Reset");
   JButton step=new JButton("Step");
            
   JButton random=new JButton("Random");
   String[] sizes={"Big", "Medium", "Small", "Tiny"};
   JComboBox sizeBox=new JComboBox(sizes);
   String[] conditionals={"Birth=3 Lonely<2 Choke>3", "Birth=2 Lonely<2 Choke>2"};
   JComboBox conditionalBox=new JComboBox(conditionals);
   String[] speeds={"Slow", "Medium", "Fast","Furious"};
   JComboBox speedBox=new JComboBox(speeds);
   JPanel cellPanel=new JPanel();
   JCheckBox diagonalToggal=new JCheckBox("Diagonal Neigbors", true);
   
            



   
   

/////////////Constructor/////////////////////////////////////
   Gameboard(int xInput, int yInput)
   {//begin constructor
      x=xInput;
      y=yInput;
      ////////////initialize cells
      for (int i=0; i<x; i++)
      {
         for (int j=0; j<y; j++)
         {   
            myArray[i][j]= new Cell();
            myArray[i][j].myX=i;
            myArray[i][j].myY=j;
         }         
      }   
      
      //initialize neigbors south east and west for all non border cells
      for (int i=1; i<x-1; i++)
      {
         for (int j=1; j<y-1; j++){
            myArray[i][j].west=myArray[i-1][j];
            myArray[i][j].east=myArray[i+1][j];
            myArray[i][j].north=myArray[i][j-1];
            myArray[i][j].south=myArray[i][j+1];
            myArray[i][j].northwest=myArray[i-1][j-1];
            myArray[i][j].northeast=myArray[i+1][j-1];
            myArray[i][j].southeast=myArray[i+1][j+1];
            myArray[i][j].southwest=myArray[i-1][j+1];
         }
      }
      
     //initilize left side rows
      int i=0;
      int j=0;
      for ( j=1; j<y-1; j++)
      {
           // myArray[i][j].west=myArray[i-1][j]
         myArray[i][j].east=myArray[i+1][j];
         myArray[i][j].north=myArray[i][j-1];
         myArray[i][j].south=myArray[i][j+1];
           // myArray[i][j].northwest=myArray[i-1][j+1]
         myArray[i][j].northeast=myArray[i+1][j+1];
         myArray[i][j].southeast=myArray[i+1][j-1];
           // myArray[i][j].southwest=myArray[i-1][j-1]
      }
      //initialize right side rows   
      i=x-1;
      for (j=1; j<y-1; j++)
      {
         myArray[i][j].west=myArray[i-1][j];
            //myArray[i][j].east=myArray[i+1][j]
         myArray[i][j].north=myArray[i][j-1];
         myArray[i][j].south=myArray[i][j+1];
         myArray[i][j].northwest=myArray[i-1][j+1];
           // myArray[i][j].northeast=myArray[i+1][j+1]
           // myArray[i][j].southeast=myArray[i+1][j-1]
         myArray[i][j].southwest=myArray[i-1][j-1];
      }
            
       //initialize the top
      for (i=1; i<x-1; i++)
      {
         j=0; 
         myArray[i][j].west=myArray[i-1][j];
         myArray[i][j].east=myArray[i+1][j];
           // myArray[i][j].north=myArray[i][j-1]
         myArray[i][j].south=myArray[i][j+1];
           // myArray[i][j].northwest=myArray[i-1][j+1]
           // myArray[i][j].northeast=myArray[i+1][j+1]
         myArray[i][j].southeast=myArray[i+1][j+1];
         myArray[i][j].southwest=myArray[i-1][j+1];
      }
   
     //initialize the bottom  
      for (i=1; i<x-1; i++)
      {
         j=y-1;
         myArray[i][j].west=myArray[i-1][j];
         myArray[i][j].east=myArray[i+1][j];
         myArray[i][j].north=myArray[i][j-1];
            //myArray[i][j].south=myArray[i][j+1]
         myArray[i][j].northwest=myArray[i-1][j-1];
         myArray[i][j].northeast=myArray[i+1][j-1];
            //myArray[i][j].southeast=myArray[i+1][j-1]
            //myArray[i][j].southwest=myArray[i-1][j-1]          
      }
       
       //top left corner
      i=0;
      j=0; 
           // myArray[i][j].west=myArray[i-1][j]
      myArray[i][j].east=myArray[i+1][j];
            //myArray[i][j].north=myArray[i][j-1]
      myArray[i][j].south=myArray[i][j+1];
            //myArray[i][j].northwest=myArray[i-1][j+1]
            //myArray[i][j].northeast=myArray[i+1][j+1]
      myArray[i][j].southeast=myArray[i+1][j+1];
      //myArray[i][j].southwest=myArray[i-1][j+1];
            
       //top right corner
      i=x-1;
      j=0; 
      myArray[i][j].west=myArray[i-1][j];
            //myArray[i][j].east=myArray[i+1][j];
            //myArray[i][j].north=myArray[i][j-1]
      myArray[i][j].south=myArray[i][j+1];
            //myArray[i][j].northwest=myArray[i-1][j+1];
            //myArray[i][j].northeast=myArray[i-1][j+1];
            // myArray[i][j].southeast=myArray[i+1][j-1]
      myArray[i][j].southwest=myArray[i-1][j+1];
   
         //bottom left         
      i=0;
      j=y-1; 
            //myArray[i][j].west=myArray[i-1][j]
      myArray[i][j].east=myArray[i+1][j];
      myArray[i][j].north=myArray[i][j-1];
            //myArray[i][j].south=myArray[i][j+1]
            //myArray[i][j].northwest=myArray[i-1][j+1];
      myArray[i][j].northeast=myArray[i+1][j-1];
            //myArray[i][j].southeast=myArray[i+1][j-1]
            //myArray[i][j].southwest=myArray[i-1][j-1]
   
   
         //bottom right
      i=x-1;
      j=y-1; 
      myArray[i][j].west=myArray[i-1][j];
            //myArray[i][j].east=myArray[i+1][j]
      myArray[i][j].north=myArray[i][j-1];
            //myArray[i][j].south=myArray[i][j+1]
      myArray[i][j].northwest=myArray[i-1][j-1];
            //myArray[i][j].northeast=myArray[i+1][j+1];
            //myArray[i][j].southeast=myArray[i+1][j-1]
            //myArray[i][j].southwest=myArray[i-1][j-1]
   
      /*for (i=0;i<x+1;i++){
         for (j=0;j<y+1;i++){
            myArray[i][j].x=i;
            myArray[i][j].y=j;
         }
      }   
   */
   ///////////////////////set up the control panel//////////////////////////////////////
      start.addActionListener(
            new ActionListener() {
               @Override
                  public void actionPerformed(ActionEvent e){
                  timer.start();}
            });
           
      stop.addActionListener(
            new ActionListener() {
               @Override
                  public void actionPerformed(ActionEvent e){
                  timer.stop();
                  repaint();
               }
            });
   
      reset.addActionListener(
            new ActionListener() {
               @Override
                public void actionPerformed(ActionEvent e){
                  timer.stop();
                  for (int i=0; i<x; i++)
                  {
                     for (int j=0; j<y; j++)
                     {
                        //System.out.println("I:"+i+" J: "+j);
                        myArray[i][j].alive=false;
                        myArray[i][j].infected=false;
                        myArray[i][j].nextAlive=false;
                        myArray[i][j].nextInfected=false;
                        myArray[i][j].setBackground(Color.GRAY);
                        myArray[i][j].myColor=Color.GRAY;
                        myArray[i][j].repaint();
                        turnCounter=0;                            
                     }
                  }                            
               
               }
            });
   
      step.addActionListener(
            new ActionListener() {
               @Override
                  public void actionPerformed(ActionEvent e){
                  step();
               }         
            
            });
   
   
   
   
   
      random.addActionListener(
            new ActionListener() {
               @Override
                  public void actionPerformed(ActionEvent e){
                  timer.stop();
                  int coin;
                  int oneinten;
                  for (int i=0; i<x; i++)
                  {
                     for (int j=0; j<y; j++)
                     {
                        coin=(int)(Math.random()*5);
                        if (coin>3)
                        {
                           myArray[i][j].alive=true;
                           myArray[i][j].myColor=Color.YELLOW;
                           myArray[i][j].setBackground(Color.YELLOW);
                           myArray[i][j].repaint();
                           
                              //<Todo> add random function based on addinfected beingchecked
                        
                              /*oneinten=(int)(Math.random()*10);
                              if (oneinten>8)                                 
                                 myArray[i][j].infected=true;
                              repaint();*/
                        } 
                        else myArray[i][j].alive=false;   
                     }
                  }
                  repaint();                            
               }
            });//end Reset actionlistener
   
      sizeBox.setSelectedIndex(3); 
      sizeBox.addActionListener(
            new ActionListener() 
            {//begin listner
               @Override
                  public void actionPerformed(ActionEvent e)
               {//begin event
                  clear();
                  switch (sizeBox.getSelectedIndex())
                  //need to add resizing and repopulating method
                  {
                     case  1: x=80;//Big
                        y=80;
                        repaint();
                        break;
                     case  2: x=60;//
                        y=60;
                        repaint();
                        break;
                     case  3: x=40;
                        y=40;
                        repaint();
                        break;
                     case  4: x=20;
                        y=20;
                        repaint();
                        break;
                  }
               }//end event
            });//end listener
            
      diagonalToggal.addActionListener(
            new ActionListener() 
            {//begin listner
               @Override
                  public void actionPerformed(ActionEvent e)
               {//begin event
                  boolean wasRunning=timer.isRunning();
                  timer.stop();
                  boolean selected=diagonalToggal.isSelected();
                  //need to add resizing and repopulating method
                  {
                     for(int k=0; k<x; k++)
                     {
                        for(int l=0; l<y; l++)
                        {
                           myArray[k][l].diagNeighbors=selected;
                        }
                     }
                  }
                  if (wasRunning) timer.start();
               }//end event
            });//end listener
   
      
      
            
            
   
      conditionalBox.setSelectedIndex(0);
      conditionalBox.addActionListener(
            new ActionListener()
            {//begin action listener
               @Override
                  public void actionPerformed(ActionEvent e)
               {
                  clear();
                  switch (conditionalBox.getSelectedIndex())
                  {
                              //set parms (livemin, choke, lonely) 
                     case  0: setParms(3, 2, 2);
                        repaint();
                        break;
                     case  1: setParms(2, 2, 2);
                        repaint();
                        break;
                                                   /*case  4: setParms(   );
                                       repaint();
                                       break;*/
                  }            
               }
            });//end listner      
   
      speedBox.setSelectedIndex(2);
      speedBox.addActionListener(
            new ActionListener()
            {
               @Override
                  public void actionPerformed(ActionEvent e)
               {
                  clear();
                  switch (speedBox.getSelectedIndex())
                  {
                     //"Slow", "Medium", "Fast","Furious"
                     case  1: delay=1500;
                        break;
                     case  2: delay=500;
                        break;
                     case  3: delay=20;
                        break;
                     case  4: delay=1;
                        break;
                  }            
               }
            });
   
   
      cellPanel.setLayout(new GridLayout(x,y));
      for ( i=0; i<x; i++)
      {
         for ( j=0; j<x; j++)
         {
            cellPanel.add((JButton) myArray[i][j]);                            
         }
               
      }
   ////populate the controlPanel
      controlPanel.setLayout(new GridLayout(3,0));
      JPanel controlPanel1=new JPanel();
      JPanel controlPanel2=new JPanel();
      // Move to  attributes JPanel controlPanel3=new JPanel();
      controlPanel1.add(step);
      controlPanel1.add(start);
      controlPanel1.add(stop);
      controlPanel1.add(reset);
      controlPanel1.add(random);
      controlPanel1.add(sizeBox);
      controlPanel2.add(conditionalBox);
      controlPanel2.add(speedBox);
      controlPanel2.add(diagonalToggal);
      controlPanel3.add(turnCountFlag);
      controlPanel.add(controlPanel1);
      controlPanel.add(controlPanel2);    
      controlPanel.add(controlPanel3);
   
   
      setLayout(new BorderLayout());
      add(controlPanel, BorderLayout.NORTH);
      add(cellPanel, BorderLayout.CENTER);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      setPreferredSize(new Dimension(600,600));
      setMinimumSize(new Dimension(400,400));
      pack();
   }//end constructor
//////////////////////////////////////////////////////////////////////
//////Gameboard methods /////////////////////////////////////////
/////////////////////////////////////////////////////////////////        
   public void clear(){
      timer.stop();
      for (int i=0; i<x; i++)
         for (int j=0; j<y; j++)
         {
            myArray[i][j].alive=false;
            myArray[i][j].infected=false;
            myArray[i][j].nextAlive=false;
            myArray[i][j].nextInfected=false;
            myArray[i][j].setBackground(Color.GRAY);
            myArray[i][j].myColor=Color.GRAY;
            myArray[i][j].repaint();
            turnCounter=0;
            repaint(); 
            
                                       
         }
      repaint();          
   }
   
   public void updateTurnCounter()
   {
      turnCounter++;
      if (turnCounter==999) //<toDO> bring back to 100
      {
         timer.stop();
         while (true)
         {
            if (complete)
            {
               insertVirus();
               break;
            }
            else
            {
               step();
            }
         }         
         timer.start();
      }
      
      turnCountFlag.setText("Turn: "+turnCounter+infectInsertedFlag);
      turnCountFlag.repaint();
      controlPanel3.repaint();
      controlPanel.repaint(); 
      //System.out.println(turnCounter);  
      
   }
   
   public void insertVirus()
   {
      boolean infectionInserted=false;
      int xInfection;
      int yInfection;
      while(!infectionInserted)
      {   
         xInfection=(int)(Math.random()*x);
         yInfection=(int)(Math.random()*y);
         if (myArray[xInfection][yInfection].alive==true)
         {
            myArray[xInfection][yInfection].infected=true;
            myArray[xInfection][yInfection].myColor=Color.RED;
            myArray[xInfection][yInfection].repaint();
            infectInsertedFlag=" Infection inserted on turn "+turnCounter+" at "+xInfection+" , "+yInfection;
            infectionInserted=true;
         }
         
      }
      repaint();
      timer.stop();//troubleshooting
   }//end insertVirus method
   
   
   
   
   public void  step()
   {
      for(int k=0; k<x; k++)
      {
         for(int l=0; l<y; l++)
         {
            myArray[k][l].checkNext();
         }
      }
      for(int k=0; k<x; k++)
      {
         for(int l=0; l<y; l++)
         {
            myArray[k][l].update();
         }
      }
      updateTurnCounter();
                  
      complete=true;
   }

   public void setParms(int inputBirth, int inputChoke, int inputLonely){                   
      boolean timerWasRunning=timer.isRunning();
      timer.stop();
      for (int i=0; i<x; i++)
      {
         for (int j=0; j<y; j++)
         {
            myArray[i][j].birthMin=inputBirth;
            myArray[i][j].chokeMin=inputChoke;
            myArray[i][j].lonelyMin=inputLonely;
         }
      }
      if (timerWasRunning) timer.start();
   }
   
   /*
   public void resize(int inputX, int inputY)
   {
   //redoes the constructor linking
   x=xInput;
      y=yInput;
      ////////////initialize cells
      for (int i=0; i<x; i++)
      {
         for (int j=0; j<y; j++)
         {   
            myArray[i][j]= new Cell();
            myArray[i][j].myX=i;
            myArray[i][j].myY=j;
         }         
      } 
     }   
      
      //initialize neigbors south east and west for all non border cells
      for (int i=1; i<x-1; i++)
      {
         for (int j=1; j<y-1; j++){
            myArray[i][j].west=myArray[i-1][j];
            myArray[i][j].east=myArray[i+1][j];
            myArray[i][j].north=myArray[i][j-1];
            myArray[i][j].south=myArray[i][j+1];
            myArray[i][j].northwest=myArray[i-1][j-1];
            myArray[i][j].northeast=myArray[i+1][j-1];
            myArray[i][j].southeast=myArray[i+1][j+1];
            myArray[i][j].southwest=myArray[i-1][j+1];
         }
      }
      
     //initilize left side rows
      int i=0;
      int j=0;
      for ( j=1; j<y-1; j++)
      {
           // myArray[i][j].west=myArray[i-1][j]
         myArray[i][j].east=myArray[i+1][j];
         myArray[i][j].north=myArray[i][j-1];
         myArray[i][j].south=myArray[i][j+1];
           // myArray[i][j].northwest=myArray[i-1][j+1]
         myArray[i][j].northeast=myArray[i+1][j+1];
         myArray[i][j].southeast=myArray[i+1][j-1];
           // myArray[i][j].southwest=myArray[i-1][j-1]
      }
      //initialize right side rows   
      i=x-1;
      for (j=1; j<y-1; j++)
      {
         myArray[i][j].west=myArray[i-1][j];
            //myArray[i][j].east=myArray[i+1][j]
         myArray[i][j].north=myArray[i][j-1];
         myArray[i][j].south=myArray[i][j+1];
         myArray[i][j].northwest=myArray[i-1][j+1];
           // myArray[i][j].northeast=myArray[i+1][j+1]
           // myArray[i][j].southeast=myArray[i+1][j-1]
         myArray[i][j].southwest=myArray[i-1][j-1];
      }
            
       //initialize the top
      for (i=1; i<x-1; i++)
      {
         j=0; 
         myArray[i][j].west=myArray[i-1][j];
         myArray[i][j].east=myArray[i+1][j];
           // myArray[i][j].north=myArray[i][j-1]
         myArray[i][j].south=myArray[i][j+1];
           // myArray[i][j].northwest=myArray[i-1][j+1]
           // myArray[i][j].northeast=myArray[i+1][j+1]
         myArray[i][j].southeast=myArray[i+1][j+1];
         myArray[i][j].southwest=myArray[i-1][j+1];
      }
   
     //initialize the bottom  
      for (i=1; i<x-1; i++)
      {
         j=y-1;
         myArray[i][j].west=myArray[i-1][j];
         myArray[i][j].east=myArray[i+1][j];
         myArray[i][j].north=myArray[i][j-1];
            //myArray[i][j].south=myArray[i][j+1]
         myArray[i][j].northwest=myArray[i-1][j-1];
         myArray[i][j].northeast=myArray[i+1][j-1];
            //myArray[i][j].southeast=myArray[i+1][j-1]
            //myArray[i][j].southwest=myArray[i-1][j-1]          
      }
       
       //top left corner
      i=0;
      j=0; 
           // myArray[i][j].west=myArray[i-1][j]
      myArray[i][j].east=myArray[i+1][j];
            //myArray[i][j].north=myArray[i][j-1]
      myArray[i][j].south=myArray[i][j+1];
            //myArray[i][j].northwest=myArray[i-1][j+1]
            //myArray[i][j].northeast=myArray[i+1][j+1]
      myArray[i][j].southeast=myArray[i+1][j+1];
      //myArray[i][j].southwest=myArray[i-1][j+1];
            
       //top right corner
      i=x-1;
      j=0; 
      myArray[i][j].west=myArray[i-1][j];
            //myArray[i][j].east=myArray[i+1][j];
            //myArray[i][j].north=myArray[i][j-1]
      myArray[i][j].south=myArray[i][j+1];
            //myArray[i][j].northwest=myArray[i-1][j+1];
            //myArray[i][j].northeast=myArray[i-1][j+1];
            // myArray[i][j].southeast=myArray[i+1][j-1]
      myArray[i][j].southwest=myArray[i-1][j+1];
   
         //bottom left         
      i=0;
      j=y-1; 
            //myArray[i][j].west=myArray[i-1][j]
      myArray[i][j].east=myArray[i+1][j];
      myArray[i][j].north=myArray[i][j-1];
            //myArray[i][j].south=myArray[i][j+1]
            //myArray[i][j].northwest=myArray[i-1][j+1];
      myArray[i][j].northeast=myArray[i+1][j-1];
            //myArray[i][j].southeast=myArray[i+1][j-1]
            //myArray[i][j].southwest=myArray[i-1][j-1]
   
   
         //bottom right
      i=x-1;
      j=y-1; 
      myArray[i][j].west=myArray[i-1][j];
            //myArray[i][j].east=myArray[i+1][j]
      myArray[i][j].north=myArray[i][j-1];
            //myArray[i][j].south=myArray[i][j+1]
      myArray[i][j].northwest=myArray[i-1][j-1];
            //myArray[i][j].northeast=myArray[i+1][j+1];
            //myArray[i][j].southeast=myArray[i+1][j-1]
            //myArray[i][j].southwest=myArray[i-1][j-1]
   }
   
   */
   /*
   //repopulate the cells
   cellPanel.setLayout(new GridLayout(x,y));
      for ( i=0; i<x; i++)
      {
         for ( j=0; j<x; j++)
         {
            cellPanel.add((JButton) myArray[i][j]);                            
         }
               
      }
      
   
   
   } 
   */

 
 
      
   public static void main(String[] args)
   {//begin main
      Gameboard myGame = new Gameboard(80,80);
         
         
   }//end main      
   
   /*public static void linker()
   {  
      ////////////initialize cells
      Cell[][] myArray= new Cell[x][y];
      for (int i=0; i<x; i++)
      {
         for (int j=0; j<y; j++)
         {   
            myArray[i][j]= new Cell();
            myArray[i][j].myX=i;
            myArray[i][j].myY=j;
         }         
      }   
      
      //initialize neigbors south east and west for all non border cells
      for (int i=1; i<x-1; i++)
      {
         for (int j=1; j<y-1; j++){
            myArray[i][j].west=myArray[i-1][j];
            myArray[i][j].east=myArray[i+1][j];
            myArray[i][j].north=myArray[i][j-1];
            myArray[i][j].south=myArray[i][j+1];
            myArray[i][j].northwest=myArray[i-1][j-1];
            myArray[i][j].northeast=myArray[i+1][j-1];
            myArray[i][j].southeast=myArray[i+1][j+1];
            myArray[i][j].southwest=myArray[i-1][j+1];
         }
      }
      
     //initilize left side rows
      int i=0;
      int j=0;
      for ( j=1; j<y-1; j++)
      {
           // myArray[i][j].west=myArray[i-1][j]
         myArray[i][j].east=myArray[i+1][j];
         myArray[i][j].north=myArray[i][j-1];
         myArray[i][j].south=myArray[i][j+1];
           // myArray[i][j].northwest=myArray[i-1][j+1]
         myArray[i][j].northeast=myArray[i+1][j+1];
         myArray[i][j].southeast=myArray[i+1][j-1];
           // myArray[i][j].southwest=myArray[i-1][j-1]
      }
      //initialize right side rows   
      i=x-1;
      for (j=1; j<y-1; j++)
      {
         myArray[i][j].west=myArray[i-1][j];
            //myArray[i][j].east=myArray[i+1][j]
         myArray[i][j].north=myArray[i][j-1];
         myArray[i][j].south=myArray[i][j+1];
         myArray[i][j].northwest=myArray[i-1][j+1];
           // myArray[i][j].northeast=myArray[i+1][j+1]
           // myArray[i][j].southeast=myArray[i+1][j-1]
         myArray[i][j].southwest=myArray[i-1][j-1];
      }
            
       //initialize the top
      for (i=1; i<x-1; i++)
      {
         j=0; 
         myArray[i][j].west=myArray[i-1][j];
         myArray[i][j].east=myArray[i+1][j];
           // myArray[i][j].north=myArray[i][j-1]
         myArray[i][j].south=myArray[i][j+1];
           // myArray[i][j].northwest=myArray[i-1][j+1]
           // myArray[i][j].northeast=myArray[i+1][j+1]
         myArray[i][j].southeast=myArray[i+1][j+1];
         myArray[i][j].southwest=myArray[i-1][j+1];
      }
   
     //initialize the bottom  
      for (i=1; i<x-1; i++)
      {
         j=y-1;
         myArray[i][j].west=myArray[i-1][j];
         myArray[i][j].east=myArray[i+1][j];
         myArray[i][j].north=myArray[i][j-1];
            //myArray[i][j].south=myArray[i][j+1]
         myArray[i][j].northwest=myArray[i-1][j-1];
         myArray[i][j].northeast=myArray[i+1][j-1];
            //myArray[i][j].southeast=myArray[i+1][j-1]
            //myArray[i][j].southwest=myArray[i-1][j-1]          
      }
       
       //top left corner
      i=0;
      j=0; 
           // myArray[i][j].west=myArray[i-1][j]
      myArray[i][j].east=myArray[i+1][j];
            //myArray[i][j].north=myArray[i][j-1]
      myArray[i][j].south=myArray[i][j+1];
            //myArray[i][j].northwest=myArray[i-1][j+1]
            //myArray[i][j].northeast=myArray[i+1][j+1]
      myArray[i][j].southeast=myArray[i+1][j+1];
      //myArray[i][j].southwest=myArray[i-1][j+1];
            
       //top right corner
      i=x-1;
      j=0; 
      myArray[i][j].west=myArray[i-1][j];
            //myArray[i][j].east=myArray[i+1][j];
            //myArray[i][j].north=myArray[i][j-1]
      myArray[i][j].south=myArray[i][j+1];
            //myArray[i][j].northwest=myArray[i-1][j+1];
            //myArray[i][j].northeast=myArray[i-1][j+1];
            // myArray[i][j].southeast=myArray[i+1][j-1]
      myArray[i][j].southwest=myArray[i-1][j+1];
   
         //bottom left         
      i=0;
      j=y-1; 
            //myArray[i][j].west=myArray[i-1][j]
      myArray[i][j].east=myArray[i+1][j];
      myArray[i][j].north=myArray[i][j-1];
            //myArray[i][j].south=myArray[i][j+1]
            //myArray[i][j].northwest=myArray[i-1][j+1];
      myArray[i][j].northeast=myArray[i+1][j-1];
            //myArray[i][j].southeast=myArray[i+1][j-1]
            //myArray[i][j].southwest=myArray[i-1][j-1]
   
   
         //bottom right
      i=x-1;
      j=y-1; 
      myArray[i][j].west=myArray[i-1][j];
            //myArray[i][j].east=myArray[i+1][j]
      myArray[i][j].north=myArray[i][j-1];
            //myArray[i][j].south=myArray[i][j+1]
      myArray[i][j].northwest=myArray[i-1][j-1];
            //myArray[i][j].northeast=myArray[i+1][j+1];
            //myArray[i][j].southeast=myArray[i+1][j-1]
            //myArray[i][j].southwest=myArray[i-1][j-1]
   
      /*for (i=0;i<x+1;i++){
         for (j=0;j<y+1;i++){
            myArray[i][j].x=i;
            myArray[i][j].y=j;
         }
      }   
   
   }//end linker
   */
                    
/*
   int birthMin=2;
   int chokeMin=3;
   int lonelyMin=2;
*/                    
                    
       
       
       
       
            
}//end class Gameboard         

/* use of X means moving timer listener into cell panel
class TimerListener implements ActionListener
{
   int i,j;
   boolean updateturn=false;
   @Override
   public void actionPerformed(ActionEvent e)
   {
      if (updateturn)
      {
         for (int i=0; i<super.x; i++)
         {
            for (int j=0; i<super.y; i++)
            {
               super.myArray[i][j].update();
            }
         }  
      }
      else
      {
         for (int i=0; i<super.x; i++)
         {
            for (int j=0; i<super.y; i++)
            {
               super.myArray[i][j].checkNext();
            }
         }   
      }      
      
      
      repaint();
      if (updateturn)updateturn=false;
      else updateturn=true;
   }
}
*/   
   
/*

 class CellBoard extends JPanel{
 
 
}

*/