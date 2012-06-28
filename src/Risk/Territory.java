package Risk;

import java.util.Vector;


public class Territory{
	//Need get X,Y
	protected int id;
        private int x;
        private int y;
	private String name;
	private Player player;
	private int armies;
	private int continent;
	private Vector<Integer> adjacents;
	
	Territory(int i,String nm, int c, int xloc, int yloc){
		id = i;
		name = nm;
		continent = c;
		armies = 0; //Start Empty
		adjacents = new Vector<Integer>();
                x = xloc;
                y = yloc;
                player = new Player(null,-1);
	}
	
	public void setPlayer(Player p){
		player = p;
	}

        public void setAdjacent(Vector<Integer> a){
            adjacents = a;
        }
	
	public void setArmies(int a){
		armies = a;
	}

        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public int getArmies(){
		return armies;
	}
	
	public Vector<Integer> getAdjacents(){
		return adjacents;
	}
	
	public void setContinent(int c) {
		continent = c;
	}

	public int getContinent() {
		return continent;
	}

	public boolean isAdjacent(Territory t){
		return adjacents.contains(t.getId());
	}

        public boolean isOccupied(){
            if (player.getPlayerIndex() == -1)
                return false;
            return true;
        }
	
	public void addArmies(int n){
		armies+=n;
	}

      /*  public boolean territoryChained(Territory a,Territory next){
           if(player != a.getPlayer())
               return false;
            if(a.getPlayer() != next.getPlayer())
                return false;
            else{
               for(int i = 0;i < adjacents.size();i++){
                    if(a.isAdjacent(RiskGame.territories.elementAt(adjacents.elementAt(i))))
                            System.out.println(i+" is adjacent");

               }

            }

                return false;

            }*/

        public void addArmy(){
            armies++;
        }
	
	public void looseArmy(){
		armies--;
	}

        public void looseArmies(int a){
            armies -= a;
        }

        public void printAdjacents(){
            for(int i = 0; i < adjacents.size(); i++){
               System.out.println("Print" + adjacents.elementAt(i));
            }


        }
}
