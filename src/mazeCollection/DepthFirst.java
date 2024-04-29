//for shortest path algorithm
package mazeCollection;


import java.nio.file.Path;
import java.util.List;

public class DepthFirst {
    
    public static boolean searchPath(int[][] maze, int x, int y
            , List<Integer> path) {
        
        
        
        if (maze[y][x]==1)
        {
        	return false;
        }
        
        if (maze[y][x] == 9) {
            path.add(x);
            path.add(y);
            return true;
        }
        
        if (maze[y][x] == 0) {
            maze[y][x] = 2;
            
            int dx = -1;  //west  left
            int dy = 0;
            if (searchPath(maze, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);
                return true;
            }

            dx = 1;   //east right
            dy = 0;
            if (searchPath(maze, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);
                return true;
            }

            dx = 0;
            dy = -1;  //down  south
            if (searchPath(maze, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);
                return true;
            }

            dx = 0;
            dy = 1;   //up north
            if (searchPath(maze, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);
                return true;
            }
        }
        return false;
    }
    
  
    
    public static void show_arraylist(List<Integer> path)
    {
    	System.out.println(path);
    	
    }
   
    
    
}


