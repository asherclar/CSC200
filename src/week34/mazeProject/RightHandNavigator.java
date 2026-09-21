package week34.mazeProject;

public class RightHandNavigator {
    public static boolean solve(MapFile mazeReader, int row, int col, int direction){
        //"direction" solver is facing: 0 is right, 1 is down, 2 is left, 3 is up
        char[][] maze = mazeReader.getMap();

        //base case: solved
        if(maze[row][col]=='E'){return true;}

        //mark spot as visited
        maze[row][col]='P';

        //load values to each side, if at edge of map, treat beyond that as a wall
        char down = row+1<maze.length ? maze[row+1][col] : '+';
        char up = row>0 ? maze[row-1][col] : '+';
        char right = col+1<maze[row].length ? maze[row][col+1] : '+';
        char left = col>0 ? maze[row][col-1] : '+';

        //if next to exit, go to exit
        if(down=='E')return solve(mazeReader, row+1, col, 1);
        if(up=='E')return solve(mazeReader, row-1, col, 3);
        if(right=='E')return solve(mazeReader, row, col+1, 0);
        if(left=='E')return solve(mazeReader, row, col-1, 2);

        //right-hand-on-wall algorithm
        if (direction == 0) { // initially facing right
            if(down!='+'){
                return solve(mazeReader, row+1, col, 1);
            } else if (right!='+'){
                return solve(mazeReader, row, col+1, 0);
            } else if (up!='+'){
                return solve(mazeReader, row-1, col, 3);
            } else if (left!='+'){
                return solve(mazeReader, row, col-1, 2);
            } else return false;
        } else if (direction == 1) { // initially facing down
            if (left!='+'){
                return solve(mazeReader, row, col-1, 2);
            } else if(down!='+'){
                return solve(mazeReader, row+1, col, 1);
            } else if (right!='+'){
                return solve(mazeReader, row, col+1, 0);
            } else if (up!='+') {
                return solve(mazeReader, row - 1, col, 3);
            } else return false;
        } else if (direction == 2) { // initially facing left
            if (up!='+') {
                return solve(mazeReader, row - 1, col, 3);
            } else if (left!='+'){
                return solve(mazeReader, row, col-1, 2);
            } else if(down!='+'){
                return solve(mazeReader, row+1, col, 1);
            } else if (right!='+'){
                return solve(mazeReader, row, col+1, 0);
            } else return false;
        } else { // initially facing up
            if (right!='+'){
                return solve(mazeReader, row, col+1, 0);
            } else if (up!='+') {
                return solve(mazeReader, row - 1, col, 3);
            } else if (left!='+'){
                return solve(mazeReader, row, col-1, 2);
            } else if(down!='+'){
                return solve(mazeReader, row+1, col, 1);
            } else return false;
        }

    }

    public static void main(String[] args){
        MapFile mazeReader = new MapFile("maze.txt");
        IO.print("Solveable using right-hand-on-wall: "+ solve(mazeReader, mazeReader.getStartRow(), mazeReader.getStartCol(), 0));
        IO.println();
        IO.print(mazeReader);
    }
}
