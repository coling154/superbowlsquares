//main for superbowl Squares

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Main {
    public Person[][][] game = new Person[4][10][10];
    Random rand = new Random();
    public Person[] everyone;
    public static void main(String[] args) {
        File inFile = new File("Data/input.csv");
        Main mainInstance = new Main();
        Person[] everyone = mainInstance.readIn(inFile);

        for(Person p : everyone){
            System.out.println(p);
            mainInstance.addAllSquare(p);
        }
        //mainInstance.addSquareMan(p3, 0, 0, 0);
        //mainInstance.addSquareMan(p2, 0, 0, 0);
        String fileName = "output.csv";
        File csv = new File("Data/" + fileName);
        mainInstance.writeToCsv(csv);
    }
    // format [col, row, quarter]
    public void addSquare(Person p, int q){
        int[] index = new int[3];
        
        index[0] = q;
        index[2] = rand .nextInt(10);
        index[1] = rand.nextInt(10);
        while(!checkIsEmpty(index)){
            index[2] = rand .nextInt(10);
            index[1] = rand.nextInt(10);
            index[0] = q;
        }
        game[index[0]][index[1]][index[2]] = p;
        System.out.println("Added " + p.getName() + " col: " + index[1] + ", row: " + index[2] +", Q: " + index[0]);
        p.addToSquare(index);
    }

    public void addSquareMan(Person p, int col, int row, int q){
        int[] index = {q, col, row};
        if(checkIsEmpty(index)){
            System.out.println("Added " + p.getName() + " col: " + index[1] + ", row: " + index[2] +", Q: " + index[0]);
            game[index[0]][index[1]][index[2]] = p;
            p.addToSquare(index);
        }
        else{
            System.out.println("Cannot add " + p.getName() + " col: " + index[1] + ", row: " + index[2] +", Q: " + index[0] + " already exists");
        }
    }

    public void addAllSquare(Person p){
        int numSquares = p.getNumSquares();
        int q;
        for(int i = 0; i < numSquares; i++){
            q = rand.nextInt(4);
            addSquare(p, q);
        }
    }
    //returns true if empty
    public boolean checkIsEmpty(int[] index){
        Person p1 = game[index[0]][index[1]][index[2]];
        if(p1 == null){
            return true;
        }
        else{
            return false;
        }
    }
    public void writeToCsv(File file){
        File f = file;
        Person p1;
        try(FileWriter writer = new FileWriter(f)){
            for(Person[][] array2D : game){
                for(Person[] array1D : array2D){
                    for(int i = 0; i < array1D.length; i++){
                        p1 = array1D[i];
                        String name;
                        try{name = p1.getName();}
                        catch(NullPointerException e){
                            name = " ";
                        }
                        writer.append(name);
                        if (i < array1D.length - 1) {
                            writer.append(",");
                        }
                    }
                    writer.append("\n");
                }
                writer.append("\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Person[] readIn(File file){
        File f = file;
        String line;
        ArrayList<Person> everyone = new ArrayList<Person>();
        try(BufferedReader br = new BufferedReader(new FileReader(f))){
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // assuming a comma as the delimiter
                everyone.add(new Person(values[0], Double.parseDouble(values[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Person[] people = everyone.toArray(new Person[0]);
        return people;
    }
}
