/**
 * The Application program analyzes position relation of two rectangles from input file, and output
 * position result to file.
 * @author  Jiawei Zhu
 * @version 1.0
 * @since   2020-03-18
 */
package Nuvalence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import Nuvalence.Rectangle;

public class Application {
	/**
	 * This method is used to read input file, and parse rectangle coordinates into a
	 * list of Rectangle entities.
	 * @param path This is the input file path.
	 * @return List<Rectangle> recs This is the list of Rectangle entities parsed from the input file.
	 * @throws IOException
	 */
	private static List<Rectangle> readFile(String path) throws IOException {
		if (path == null || path.isEmpty()) {
			return new ArrayList<Rectangle>();
		}
		
		FileInputStream fis = new FileInputStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		String line = null;
		List<String> lines = new ArrayList<>();
		while ((line = br.readLine()) != null) {
			lines.add(line);
		}
		
		br.close();
		
		//parse Rectangle entities
		List<Rectangle> recs = parseRectangles(lines);
				
		return recs;
	}	
	
	
	/**
	 * This method is used to parse Rectangle entities based on string coordinates.
	 * @param input This is the list of each rectangle's string coordinates (left 
	 * bottom coordinate {x1, y1} and right top coordinate {x2, y2}).
	 * @return List<Rectangle> recs This is the list of Rectangle entities parsed from
	 * input string coordinates.
	 */
	private static List<Rectangle> parseRectangles(List<String> input) {
		List<Rectangle> recs = new ArrayList<>();
		
		if (input == null || input.size() == 0 || input.get(0) == null 
				|| input.get(0).isEmpty()) {
			return recs;
		}
		
		String[] tokens = null;
		for (String line: input) {
			tokens = line.split(" ");
			
			int id = Integer.parseInt(tokens[0]);
			int x1 = Integer.parseInt(tokens[1]);
			int y1 = Integer.parseInt(tokens[2]);
			int x2 = Integer.parseInt(tokens[3]);
			int y2 = Integer.parseInt(tokens[4]);
			Point leftBotPoint = new Point(x1, y1);
			Point rightTopPoint = new Point(x2, y2);
			
			//use chaining to build Rectangle entity
			Rectangle rec = new Rectangle.RectangleBuilder()
					.id(id)
					.leftBotPoint(leftBotPoint)
					.rightTopPoint(rightTopPoint)
					.build();
			
			recs.add(rec);
		}
		
		return recs;
	}
	
	
	/**
	 * This method compares the position relation of input Rectangle entities.
	 * @param recs This is the list of input Rectangle entities.
	 * @return String result This is the position relation of input Rectangle entities 
	 * ("Containment", "Intersection", "Adjacency", "No Containment/Intersection/Adjacency").
	 */
	private static String comparePosition(List<Rectangle> recs) {
		String result = "";
		
		if (recs == null || recs.size() < 2) {
			throw new IllegalArgumentException("Input Rectangle list is invalid");
		}		
		
		result = recs.get(0).comparePosition(recs.get(1));
		
		return result;
	}
	
	
	/**
	 * This method is used to output position relation of Rectangle entities to file
	 * @param path This is output file path.
	 * @param result This is the position relation.
	 * @param recs This is the list of input Rectangle entities.
	 * @return Nothing
	 * @throws IOException
	 */
	private static void outputFile(String path, String result, List<Rectangle> recs) throws IOException {
		File file = new File(path);
		
		if (!file.exists()) {
			file.createNewFile();
		}
		
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		String output = "The position relation of two rectangles is: " + result + "\n";
		
		bw.write(output);
		
		if (result.equals("Intersection")) {	
			bw.write("\n");
			
			List<Point> intersectPoints = recs.get(0).getIntersectPoints();
			for (int i = 0; i < intersectPoints.size(); i++) {
				int num = i + 1;
				bw.write("Coordinate of intersection point " + num + ": " + intersectPoints.get(i).getX() + ", " + intersectPoints.get(i).getY() + "\n");
			}
			
			bw.write("\n");
			
			for (Rectangle rec: recs) {
				bw.write("Number of intersecting lines in rectangle " + rec.getId() + ": " + rec.getNumIntersectLines() + "\n");
			}
		}
		
		
		
		bw.close();
	}
	
	
	/**
	 * This is the main method to read input rectangle file, compare and output
	 * position relation to file 
	 * @param args This specifies the input file path and output file path
	 */
	public static void main(String[] args) {		
		//read input file and parse Rectangle entities
//		String inPath = "input/input.txt";
		String inPath = args[0];
		String outPath = args[1];
		
		List<Rectangle> recs = new ArrayList<>();		
		try {
			recs = readFile(inPath);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		//check number of input Rectangle entities
		if (recs == null || recs.size() < 2) {
			throw new IllegalArgumentException("Invalid number of input Rectangle entities");
		}
		
		//compare position of input rectangles
		String result = comparePosition(recs);

		//output position relation to file
//		String outPath = "output/output.txt";
		try {
			outputFile(outPath, result, recs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(result);
	}
}
