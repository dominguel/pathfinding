package resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class Modelizer {
	
	/*
	*Only accepts PNG files, upper-left and lower-right most pixels are taken as entrance/exit
	*ALL pixels not perfectly opaque black(0x00FFFFFF) are considered white
	*white = clear path, black = wall
	*/
	
	/*
	* TODO:
	* -nodeModel must add segments into list as well
	* 	-segment id
	* 	-segment source?
	* 	-segment destination?
	* 	-segent weight (length)
	*/
			
	public static boolean[][] simpleMaze(String fileNamePNG) throws IOException {
		
		BufferedImage image = ImageIO.read(new File(fileNamePNG));
		boolean[][] maze = new boolean[image.getHeight()][image.getWidth()];
		
		int pixelVal;
		for(int i = 0; i < image.getHeight(); i++) {
			for(int j = 0; j< image.getWidth(); j++) {
				
				pixelVal = image.getRGB(j, i);
				if((pixelVal & 0x00FFFFFF) == 0) {
					maze[i][j] = false;
				} else {
					maze[i][j] = true;
				}
			}
		}
		
		return maze;
	}
	
	public static Map nodeModel(String fileNamePNG) throws IOException {
		
		BufferedImage image = ImageIO.read(new File(fileNamePNG));
		List<Node> nodes = new ArrayList<Node>();
		List<Segment> segments = new ArrayList<Segment>();
		int nodeCount = 0;
		int u, d, r, l;
		final int BLACK = 0x00FFFFFF;
		
		int pixelVal;
		for(int i = 0; i < image.getHeight(); i++) {
			for(int j = 0; j < image.getWidth(); j++) {
				
				pixelVal = image.getRGB(i, j);
				
				if(i > 0) {
					u = image.getRGB(i--, j);
				} else {
					u = BLACK;
				}
				if(i < image.getHeight()) {
					d = image.getRGB(i++, j);
				} else {
					d = BLACK;
				}
				if(i < image.getWidth()) {
					r = image.getRGB(i, j++);
				} else {
					r = BLACK;
				}
				if(i > 0) {
					l = image.getRGB(i, j--);
				} else {
					l = BLACK;
				}
				
				//is square 'white'?
				if(pixelVal != BLACK) {
					
					//node conditions: junction or dead end, picture bounds are considered walls
					//check for u/d passage
					if(u != BLACK || d != BLACK) {
						//check for r/l passage
						if(r != BLACK || l != BLACK) {
							//node conditions met : junction!
							String id = Integer.toString(nodeCount);
							nodeCount++;
							nodes.add(new Node(id, id, j, i));
						}
					}
					
					//check for dead end
					if(u != BLACK ^ d != BLACK ^ r != BLACK ^ l != BLACK) {
						String id = Integer.toString(nodeCount);
						nodeCount++;
						nodes.add(new Node(id, id, j, i));
					}
				}
			}
		}
		Map maze = new Map(nodes, segments);
		return maze;
	}
}