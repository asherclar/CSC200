package week34.mazeProject;

public class RecursiveBacktrackingNavigator {
    public static boolean solve(MapFile mazeReader, int row, int col) {
        //"direction" solver is facing: 0 is right, 1 is down, 2 is left, 3 is up
        char[][] maze = mazeReader.getMap();

        //base case: solved
        if (maze[row][col] == 'E') {
            return true;
        }

        //mark spot as visited
        maze[row][col] = 'P';

        //load values to each side, if at edge of map, treat beyond that as a wall
        char down = row + 1 < maze.length ? maze[row + 1][col] : '+';
        char up = row > 0 ? maze[row - 1][col] : '+';
        char right = col + 1 < maze[row].length ? maze[row][col + 1] : '+';
        char left = col > 0 ? maze[row][col - 1] : '+';

        //if next to exit, go to exit
        if (right == 'E') return solve(mazeReader, row, col + 1);
        else if (down == 'E') return solve(mazeReader, row + 1, col);
        else if (left == 'E') return solve(mazeReader, row, col - 1);
        else if (up == 'E') return solve(mazeReader, row - 1, col);

        //try new search on open spaces
        if (right == 'O' && solve(mazeReader, row, col+1)) {
            return true;
        }
        if(down == 'O' && solve(mazeReader, row+1, col)) {
            return true;
        }
        if(left == 'O' && solve(mazeReader, row, col-1)) {
            return true;
        }
        if(up == 'O' && solve(mazeReader, row-1, col)) {
            return true;
        }

        return false;
    }
    public static void main(String[] args){
        MapFile mazeReader = new MapFile("maze.txt");
        IO.print("Solveable using recursive backtracking: "+ solve(mazeReader, mazeReader.getStartRow(), mazeReader.getStartCol()));
        IO.println();
        IO.print(mazeReader);
    }




}
