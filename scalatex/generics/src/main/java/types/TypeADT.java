package types;
import java.util.List;

/**
 *
 * <p>A simple class which stores the following :
 * <li>The Raw type of Class</li>
 * <li>The List of parametrized types</li>
 * </p>
 *<p>For example in case of an object declared as:
 * <pre>
 *     {@code
 *      HashMap<String, List<Integer>> map = new HashMap<>();
 *     }
 * </pre>
 * </p>
 * The information about above object can be stored in this class, where {@link TypeADT#getRawType()} would return {@code Class<HashMap>} and {@link TypeADT#getParameters()} would return a list of
 * {@link com.types.TypeADT}. This list would be of size-2 and calling {@code getRawType} on them would return:
 *  <p><li>{@code Class<String>}</li>
 *  <li>{@code Class<List>}. Calling {@code getParameters().get(0).getRawType} on this second object would return {@code Class<Integer>}</li>
 * </p>
 *
 * @author Jatin
 *
 */
public class TypeADT {
    private final Class<?> reify;
    private final List<TypeADT> parametrized;

    TypeADT(Class<?> reify, List<TypeADT> parametrized) {
        this.reify = reify;
        this.parametrized = parametrized;
    }

    public Class<?> getRawType() {
        return reify;
    }

    public List<TypeADT> getParameters() {
        return parametrized;
    }
}
