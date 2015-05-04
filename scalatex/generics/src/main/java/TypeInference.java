import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by jatin on 5/5/15.
 */
public class TypeInference {
    public static void go(){
        List<String> ls = Collections.emptyList();
        //compiler infers `Collections.emptyList()` to `Collections.<String>emptyList()`
        Collections.sort(Arrays.asList(1,2), (first, second) -> first.compareTo(second));
        //compiler infers `first` and `second` to be `Integer` automatically
        

    }
    public void print(List<String> ls){

    }
}
