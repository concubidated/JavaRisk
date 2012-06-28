package Risk;

import java.util.Vector;


public class Player {
	
	private String name;
	private int index;
	private Vector<Territory> occupiedTerritories;
	//Cards might be here
	private Vector<Card> cards;
	private int armies;
	//private int territoriesCaptured; //Same as occupiedTerritories.size()
	
	Player(String nm, int i) {
		name = nm;
		index = i;
		occupiedTerritories = new Vector<Territory>();
                cards = new Vector<Card>();
	}
	
	public int getPlayerIndex(){
		return index;
	}
	
	public String getName(){
		return name;
	}
	
	public int getNumberOfArmies(){
		return armies;
	}
	
/*	public Vector getCards(){
		return cardsOwned;
	} */


	public Vector<Territory> getOccupiedTerritories(){
		return occupiedTerritories;
	}

	public int numOfTerritories(){
		return occupiedTerritories.size();
	}
	
	public void occupyTerritory(Territory t){
		occupiedTerritories.add(t);
	}
	
	public void looseTerritory(Territory t){
		occupiedTerritories.remove(t);
		occupiedTerritories.trimToSize();
	}

        public void setCard(Card c){
            cards.add(c);
        }

        public Vector<Card> getCard(){
            return cards;
        }
	
	public void addArmies(int a){
		armies += a;
	}

        public void addArmy(){
            armies++;
        }

        public void looseArmies(int a){
            armies -= a;
        }
        
        public void looseArmy(){
            armies--;
        }
	
	
}
