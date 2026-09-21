package week34.mazeProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapFile {
    public char[][] getMap() {
        return map;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    private char[][] map;
    private final int numRows;
    private final int numCols;
    private final int startRow;
    private final int startCol;

    public MapFile(String fileName){
        //load file into scanner
        Scanner inFile = null;
        try {
            inFile = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            IO.println("File not found, exiting now");
            e.printStackTrace();
            System.exit(1);
        }

        // read header
        numRows = inFile.nextInt();
        numCols = inFile.nextInt();
        startRow = inFile.nextInt();
        startCol = inFile.nextInt();

        // read map
        map = new char[numRows][numCols];
        inFile.nextLine();
        int row = 0;
        while(inFile.hasNext()){
            String line = inFile.next();
            for(int col = 0; col<numCols; col++){
                map[row][col]=line.charAt(col);
            }
            row++;
        }
    }

    public String toString() {
        String out = "Map - "+numRows+"x"+numCols+":\n";
        for(int r=0; r<numRows; r++) {
            for(int c=0; c<numCols; c++) {
                out=out+(map[r][c]);
            }
            out=out+"\n";
        }
        return out;
    }


    // test MapFile class
    public static void main(String[] args) {
        MapFile maze = new MapFile("maze.txt");
        IO.print(maze.toString());
        IO.print("Starting at (" + maze.getStartRow()+", "+ maze.getStartCol() + ")");
    }
}
