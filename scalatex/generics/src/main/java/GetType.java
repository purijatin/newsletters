import java.lang.reflect.*;
import java.util.*;

class Outer{
  //Aim is to capture the type of generic type below. i.e. String
  List<String> ls = new ArrayList<String>();
}
//end

public class GetType {

  public static void main(String[] args) throws Exception {
    Field field = Outer.class.getDeclaredField("ls");
    ParameterizedType p = (ParameterizedType) field.getGenericType();
    Class<?> claz = (Class<?>)p.getActualTypeArguments()[0];
    System.out.println(claz);
    // prints class java.lang.String
    //end
  }
}

