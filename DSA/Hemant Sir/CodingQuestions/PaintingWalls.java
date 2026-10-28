/*
Painting Cost Estimation

Interior wall painting cost  = Rs.18 per sq.ft
Exterior wall painting cost  = Rs.12 per sq.ft

INPUT:
1. Number of interior walls
2. Number of exterior walls
3. Surface area of each interior wall
4. Surface area of each exterior wall

NOTE:
- If number of walls = 0 then skip area input.
- Calculate total painting cost.

APPROACH:
1. Read number of interior & exterior walls
2. Take area inputs using loops
3. Calculate:
      interiorCost = totalInteriorArea * 18
      exteriorCost = totalExteriorArea * 12
4. Print total cost

TIME COMPLEXITY : O(n)
SPACE COMPLEXITY : O(1)

SAMPLE INPUT:
2
3
12.5
15
10
20
10

SAMPLE OUTPUT:
990.0
*/

import java.util.Scanner;

public class PaintingWalls {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int interior = sc.nextInt();
        int exterior = sc.nextInt();

        double inArea = 0, exArea = 0;
        for(int i = 0; i < interior; i++)
            inArea += sc.nextDouble();
        for(int i = 0; i < exterior; i++)
            exArea += sc.nextDouble();

        double totalCost = (inArea * 18) + (exArea * 12);

        System.out.println(totalCost);
    }
}