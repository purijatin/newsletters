import java.anyutil.ArrayList;
import java.anyutil.List;

/**
  * For fun build java9 yourself by following instructions here: http://openjdk.java.net/projects/valhalla/ 
  */
class Java9{
  public static void main(String[] args){	
    System.out.println(List<int>.class.equals(List.class));//false		
    System.out.println(List<int>.class.equals(List<double>.class));//false		
		
    System.out.println(List<int>.class);//interface java.anyutil.List${0=I}
    System.out.println(List<double>.class);//interface java.anyutil.List${0=D}		
    System.out.println(java.util.List.class);//interface java.util.List
  }
}