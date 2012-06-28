package Risk;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

  public class MapPanel extends JPanel {
        private Image map;
        PixelGrabber pg;
        RiskGame risk;
        private Image army;
        private Image shield;
        public int armies;

      public MapPanel(){

          try{
                  this.map =ImageIO.read(getClass().getResourceAsStream("resources/map.jpg"));
                  this.army=ImageIO.read(getClass().getResourceAsStream("resources/army.gif"));
                  this.shield=ImageIO.read(getClass().getResourceAsStream("resources/shield.gif"));
                 map = map.getScaledInstance(1000, 550, Image.SCALE_SMOOTH);
          }catch(Exception e){
            throw new RuntimeException(e);
        }

      }

     public MapPanel(RiskGame r){
         this();
         risk = r;
      }

       public void refresh(){
          repaint();
      }



     public void selectCountrybyColor(int x, int y){

     }

    @Override
    public  void paintComponent(Graphics g){
       int playerIndex;
       int loc[] = new int[2];
       int align;
       String armies = "";
       int state = risk.getState();


       super.paintComponents(g);
       g.drawImage(map, 0, 0, null);
       g.setColor(Color.black);
    
       int i = risk.numOfTerroitories();
       for (int c = 0; c < i; c ++){
           loc = risk.drawMap(c);
           g.drawArc(loc[0], loc[1], 30, 30, 0, 360);
       }

       for (int c = 0; c < i; c++){
           playerIndex = risk.getOwnership(c);
           Player p = risk.getCurrentPlayer();
           int num = risk.numOfArmiesOnTerritory(c);
           if(num > 9)
               align = -3;
           else
               align = 0;
           armies = Integer.toString(num);
           if (armies.equals("0"))
               armies = "";
         //  System.out.println(playerIndex + " index = " + c); //Debugging
           if (playerIndex == 0)
               g.setColor(Color.red);
            if (playerIndex == 1)
               g.setColor(Color.blue);
            if (playerIndex == 2)
               g.setColor(Color.yellow);
            if (playerIndex == 3)
               g.setColor(Color.green);
            if (playerIndex == 4)
               g.setColor(Color.pink);
            if (playerIndex == 5)
               g.setColor(Color.orange);
           if(playerIndex == -1)
               g.setColor(Color.white);
           
           loc = risk.fillDrawMap(c, playerIndex);
           g.fillArc(loc[0], loc[1], 30, 30, 0, 360);
           g.setColor(Color.black);
           g.drawString(armies, loc[0]+10+align, loc[1]+20);

         }//end draw cilcles and armies
/***********************************************************

              Attack Window is here


/************************************************************/


           String s = "";
           if(state == 0)
               s = "New Game";
           if(state == 1)
               s = "Initial Reinforce";
           if(state == 2)
               s = "Active Game";
           if(state == 3)
               s = "Turn Bonus";
           if(state == 4)
               s = "Reinforce";
           if(state == 5)
               s = "Trade Cards";
           if(state == 6)
               s = "Start turn";
           if(state == 7)
               s = "Attack";
           if(state == 8)
               s = "Attacking";
           if(state == 9)
               s = "Attack_Phase";
           if(state == 10)
               s = "Battling";
           if(state == 11)
               s = "Capture";

           g.drawString("Current State: "+s, 10, 10);

/****************************************************
 *
 *                       Capture
 *
 *
 ****************************************************/

           if(state == RiskGame.CAPTURE){

               int max = risk.aTerritory.getArmies();
               int min = risk.attNum;

               if(risk.defNum < min)
                   risk.defNum = min;
               //risk.defNum  Store the armies to move here in RiskUI

               g.setColor(Color.black);
               Font h2 = new Font("Arial",Font.BOLD,36);
               Font h3 = new Font("Arial",Font.BOLD,20);
               g.fillRect(250, 100, 500, 300);//Draw main window

               g.setColor(Color.white);
               g.drawString("How many armies to move?", 430, 200);

               g.setFont(h2);
               g.setColor(Color.red);
               g.drawString("Occupy Territory", 360, 160);
               g.drawString(Integer.toString(risk.defNum), 490, 305); //ARMIES

               g.drawRect(600, 230, 50, 27); //max
               g.drawRect(520, 230, 50, 27); //inc
               g.drawRect(440, 230, 50, 27); //dec
               g.drawRect(360, 230, 50, 27); //min



               g.setFont(new Font("Arial", Font.BOLD,26));
               g.drawString("MOVE", 465, 350);


               g.setFont(h3);
               g.setColor(Color.white);

               g.drawRect(460, 325, 85, 30); //move box

               g.drawString("MAX", 605, 250);
               g.drawString("INC", 528, 250);
               g.drawString("DEC", 445, 250);
               g.drawString("MIN", 365, 250);
           }//end capture


/**************************************************************
 *
 *                      Fortify
 *
 *
 ************************************************************/


           if(state == RiskGame.FORTIFY_PHASE){
               int max = risk.aTerritory.getArmies();
               int min = 0;


               //risk.defNum  Store the armies to move here in RiskUI

               g.setColor(Color.black);
               Font h2 = new Font("Arial",Font.BOLD,36);
               Font h3 = new Font("Arial",Font.BOLD,20);
               g.fillRect(250, 100, 500, 300);//Draw main window

               g.setColor(Color.white);
               g.drawString("How many armies to move?", 430, 200);

               g.setFont(h2);
               g.setColor(Color.red);
               g.drawString("Fortify Territory", 360, 160);
               g.drawString(Integer.toString(risk.defNum), 490, 305); //ARMIES

               g.drawRect(600, 230, 50, 27); //max
               g.drawRect(520, 230, 50, 27); //inc
               g.drawRect(440, 230, 50, 27); //dec
               g.drawRect(360, 230, 50, 27); //min



               g.setFont(new Font("Arial", Font.BOLD,26));
               g.drawString("MOVE", 465, 350);


               g.setFont(h3);
               g.setColor(Color.white);

               g.drawRect(460, 325, 85, 30); //move box

               g.drawString("MAX", 605, 250);
               g.drawString("INC", 528, 250);
               g.drawString("DEC", 445, 250);
               g.drawString("MIN", 365, 250);
             }//end capture



    /**********************************************
     * 
     *            Card Menu
     * 
     *********************************************/


           if(state == RiskGame.TRADE_CARDS){
             int num = risk.curPlayer.getCard().size();
             Vector<Card> hand = risk.curPlayer.getCard();
             g.setColor(Color.black);
             g.fillRect(250, 100, 500, 300);//Draw main window


             g.setColor(Color.white);
             Font names = new Font("Arial",Font.BOLD,36);
             Font f1 = new Font("Arial",Font.BOLD,15);
             g.setFont(names);

            g.drawString("Trade Cards", 400, 160);
            if(num < 3){
                
            g.drawRect(475, 350, 50, 30);//exit box
            g.setFont(f1);
            g.drawString("You dont have enough cards", 400, 320);
            g.drawString("Exit", 485, 370);
                
            }

            if(num > 2){

               g.setFont(f1);
                int temp;

                for(int c = 0;c < num; c++){
                g.drawString(risk.getCountryName(hand.elementAt(c).territory)
                        + " value = " +
                        risk.curPlayer.getCard().elementAt(c-1).value,
                        350, 250+(c*30));

                  if(c < num-1){
                      temp = risk.curPlayer.getCard().elementAt(c-1).value;
                  if(temp == risk.curPlayer.getCard().elementAt(c).value)
                      risk.attNum++;
                  }


                }

                g.drawRect(475, 350, 50, 30);//exit box
                g.drawString("Exit", 485, 370);
            }






           }//end card menu




           if((state == RiskGame.ATTACK
           || state == RiskGame.ATTACKING
           || state == RiskGame.ATTACK_PHASE)
           && risk.aTerritory != null){
               g.drawString("Attacking with "+ risk.aTerritory.getName(),10,460);
           }
           
          if(state == RiskGame.ATTACK_PHASE){
              // This Paint the attack "popup" window
              int att = risk.aTerritory.getArmies();
               int def = risk.dTerritory.getArmies();
          // int att = 2;
          // int def = 3;

               g.setColor(Color.black);
               g.fillRect(250, 100, 500, 300);//Draw main window

               g.setColor(Color.white);
               Font names = new Font("Arial",Font.BOLD,36);
               Font f1 = new Font("Arial",Font.BOLD,15);
               g.setFont(names);
               g.drawString(risk.curPlayer.getName(),270,145); //Player Attacking
               g.drawString(risk.defender.getName(),600,145);
               g.setColor(Color.red);
               g.drawString(Integer.toString(att), 300, 250);
               g.drawString(Integer.toString(def), 660, 250);
               g.setFont(f1);

/************************************************************
 *
 *              Attacker
 *
 *
 * *********************************************************/


               if(risk.active == risk.curPlayer){
                   g.drawString("How many armies to attack with?",390,180);
                   g.drawImage(army, 300, 280, this);
                   g.setColor(Color.white);

                   if(att > 3){
                    g.fillRect(420, 250, 40, 40);
                    g.fillRect(480, 250, 40, 40);
                    g.fillRect(540, 250, 40, 40);
                    g.setColor(Color.black);
                    //die 1
                    g.fillArc(435, 265, 10, 10, 0, 360);
                    //die 2
                    g.fillArc(485, 255, 10, 10, 0, 360);
                    g.fillArc(505, 275, 10, 10, 0, 360);
                    //die3
                    g.fillArc(565, 255, 10, 10, 0, 360);
                    g.fillArc(555, 265, 10, 10, 0, 360);
                    g.fillArc(545, 275, 10, 10, 0, 360);


                   }
                   if(att == 3){
                       g.fillRect(460,250,40,40);
                       g.fillRect(510,250,40,40);
                       g.setColor(Color.black);
                       g.fillArc(475, 265, 10, 10, 0, 360);
                       g.fillArc(515, 255, 10, 10, 0, 360);
                       g.fillArc(535, 275, 10, 10, 0, 360);

                   }
                   if(att == 2){
                        g.fillRect(480,250,40,40);
                        g.setColor(Color.black);
                        g.fillArc(495, 265, 10, 10, 0, 360);
                   }
               }//end attttacker painting


 /************************************************************
 *
 *              Defender
 *
 * *********************************************************/

               if(risk.active == risk.defender){
                   g.drawString("How many armies to defend with?",390,180);
                   g.drawImage(shield, 630, 280, this);
                   g.setColor(Color.white);
                   if(def > 1 && risk.attNum > 1){
                       g.fillRect(460,250,40,40);
                       g.fillRect(510,250,40,40);
                       g.setColor(Color.BLACK);
                       g.fillArc(475, 265, 10, 10, 0, 360);
                       g.fillArc(515, 255, 10, 10, 0, 360);
                       g.fillArc(535, 275, 10, 10, 0, 360);
                   }
                   else{
                        g.fillRect(480,250,40,40);
                        g.setColor(Color.black);
                        g.fillArc(495, 265, 10, 10, 0, 360);
                   }

               }//end defennnder painting
           
       }


    }

  

 }