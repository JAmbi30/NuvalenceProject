Project name: Rectangle Position Relation Development
Author: Jiawei Zhu
Date: 03/18/2020

Description:
This project outputs the position relation of two rectangles to file. 
There are three position relations: Intersection, Containment, and Adjacency.
When the result is Intersection, the number of intersecting lines and
intersection points of the rectangle are also returned. Each rectangle is 
represented by its left bottom point and right top point. Rectangle entity and
algorithms are implemented using Java.

Test:
Unit tests are conducted via JUnit.

Libraries:
org.junit, java.io, java.util

Assumptions:
 1. Each rectangle is either vertial or horizontal. Tilted rectangle is not 
 taken into consideration for this project.
 2. Touching rectangles at a single point are not considered as any of the 
 three position relations.
 3. Input coordinate values are integer. This value is in the range from
 -2147483648 to 2147483647. 

-----------------------------------------------------------------------------
Instruction:
	To run the program, go to src folder:
		java Nuvalence.Application input_file_path output_file_path
	for example:
		java Nuvalence.Application ../input/input.txt ../output/output.txt