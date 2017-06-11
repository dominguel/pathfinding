package executables;

import java.io.IOException;
import java.util.Stack;
import resources.Modelizer;

public class FloodFill {

	private static boolean[][] maze;
	//WALLS ARE FALSE

	public static void main(String[] args) throws IOException {

		maze = Modelizer.simpleMaze("C:\\Users\\lucas\\Desktop\\mazes\\10kx.png");
		int entrance = 0;
		for(int xj = 0; xj < maze[0].length; xj++) {
			if(maze[0][xj]) {
				entrance = xj;
				break;
			}
		}

		long start = System.currentTimeMillis();
		boolean done = solve(0, entrance);
		long time = System.currentTimeMillis() - start;

		if(done) {
			System.out.println("Path found.");
			System.out.println("No nodes created.");
			System.out.println("Time to solve: " + time + "ms.");
		} else {
			System.out.println("Computed for " + time + " ms. No path found.");
		}
	}

	//recursiveness simpler, but incrementation method prevents stack overflow
	public static boolean solve(int yi, int xj) {

		Stack<int[]> q = new Stack<int[]>();
		int[] start = {yi, xj};
		q.push(start);
		int[] current;
		while(!q.empty()) {

			current = q.pop();

			//if wall, continue, having removed square from stack
			if(!maze[current[0]][current[1]]) {
				continue;
			}

			//if end is reached, and clear(see line 40), return true
			if(current[0] == maze.length-1 /*&& current[1] == maze[current[0]].length-1*/) {
				return true;

				//if clear but not end, fill and add neighbors to stack
			} else {

				maze[current[0]][current[1]] = false;
				int[] u = new int[2];
				int[] d = new int[2];
				int[] r = new int[2];
				int[] l = new int[2];

				//if going up is possible, queue action
				if(current[0] > 0) {
					u[0] = current[0]-1;
					u[1] = current[1];
					q.push(u);
				}

				//repeat downwards
				if(current[0] < maze.length-1) {
					d[0] = current[0]+1;
					d[1] = current[1];
					q.push(d);
				}

				if(current[1] > 0) {
					l[0] = current[0];
					l[1] = current[1]-1;
					q.push(l);
				}

				if(current[1] < maze[current[0]].length-1) {
					r[0] = current[0];
					r[1] = current[1]+1;
					q.push(r);
				}
			}
		}
		//all paths explored, clear end never reached
		return false;
	}
}