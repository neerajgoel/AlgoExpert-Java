package VeryHard;

import java.util.*;

/*
 O(n^2) - Runtime Complexity
 O(n) - Spacetime Complexity
*/
class MinimumAreaRectangle_01 {

  class Point{
    int x;
    int y;
    Point(){}
    Point(int _x, int _y){
      this.x = _x;
      this.y = _y;
    }
    @Override
    public String toString(){
      return x + "\t" + y;
    }
  }

  private boolean contains(Map<Integer, Set<Integer>> coordinates, Point p){
    if( !coordinates.containsKey(p.x) )
      return false;
    return coordinates.get(p.x).contains(p.y);
  }
  
  public int minimumAreaRectangle(int[][] points) {
    if( points.length <= 0 )
      return 0;
    Map<Integer, Set<Integer>> coordinates = new HashMap<>();
    int x, y;
    for(int[] point : points){
      x = point[0];
      y = point[1];
      if( !coordinates.containsKey(x) ){
        coordinates.put(x, new HashSet<>());
      }
      coordinates.get(x).add(y);
    }

    Point p1, p2, p3, p4;
    int minArea = Integer.MAX_VALUE;
    p1 = p2 = new Point( points[0][0], points[0][1] );
    for(int i=0 ; i<points.length ; i++){
      p1 = new Point(points[i][0], points[i][1]);
      for(int j=i+1 ; j<points.length ; j++){
        p2 = new Point(points[j][0], points[j][1]);
        // check if p2 can be diagonal co-ordinate of p1
        if( p1.x == p2.x || p1.y == p2.y)
          continue;
        p3 = new Point(p1.x, p2.y);
        p4 = new Point(p2.x, p1.y);
        
        int dx = Math.abs( p2.x - p3.x );
        int dy = Math.abs( p1.y - p3.y );
        if( contains(coordinates, p3) && contains(coordinates, p4) ){
          if( minArea > dx*dy ){
            // calc area
            System.out.println("p1 : " + p1);
            System.out.println("p2 : " + p2);
            System.out.println("p3 : " + p3);
            System.out.println("p4 : " + p4);
            minArea = dx*dy;
            System.out.println("minArea : " + minArea);
            System.out.println();
          }
        }
      }
    }
    System.out.println("minArea : " + minArea);
    int res = minArea == Integer.MAX_VALUE ? 0 : minArea;
    System.out.println("res : " + res);
    return res;
  }
}
