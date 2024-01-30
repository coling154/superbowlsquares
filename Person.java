//person.java
import java.util.ArrayList;

public class Person {
    //chang this to change the amount of money per square
    private final double DPS = 15.0;
    private String name;
    private double money;
    private int numSquares;
    private ArrayList<int[]> squares = new ArrayList<int[]>();
    public Person(String name, double money){
        this.name = name;
        this.money = money;
        this.numSquares = (int)Math.floor(money / DPS);
    }
    //default constructor
    public Person(){
        name = null;
        money = 0;
        numSquares = 0;
    } 

    //getters
    public String getName(){
        return name;
    }
    public double getMoney(){
        return money;
    }
    public int getNumSquares(){
        return numSquares;
    }
    @SuppressWarnings("unchecked")
    public ArrayList<int[]> getSquares(){
        return (ArrayList<int[]>) squares.clone();
    }

    //methods
    // format [col, row, quarter]
    public void addToSquare(int[] square){
        squares.add(square.clone());
    }
    @Override
    public String toString(){
        String toRet =  "Name: " + name + ", numSquares: " + numSquares;
        return toRet;
    }

}