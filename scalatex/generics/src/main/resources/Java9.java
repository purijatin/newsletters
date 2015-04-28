import java.util.*;

class Java9{
	public static void main(String[] args){
		//start
		List<int> ls1 = new ArrayList<int>();
		System.out.println(List<int>.class.equals(List.class));//false		
		System.out.println(List<int>.class.equals(List<double>.class));//false		
		
		System.out.println(List<int>.class);
		//end
	}
}