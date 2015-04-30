package types;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * CaptureType is useful to capture the generic type of a parametrized class. For example:
 * <pre>
 * {@code
 *
 * CaptureType<String> t1 = new CaptureType<String>() {};
 * t1.getRawType().equals(String.class); //true
 *
 * CaptureType<List<List<String>>> t1 = new CaptureType<List<List<String>>>() {};
 * t1.getParamADT().getRawType().equals(List.class);//true
 * }
 * </pre>
 * A more complex example:
 * <pre>
 * {@code
 *
 * class X<T> extends CaptureType<T>{}
 *
 * class Y<A, B> extends X<B> {}
 *
 * class Z<Q> extends Y<Q, Map<Integer, List<List<List<Integer>>>>> {}
 *
 * Z<String> z = new Z<>();
 * TypeADT param = z.getParamADT();
 * equals(param.getRawType(), Map.class);
 * List<TypeADT> parameters = param.getParameters();
 * equals(parameters.get(0).getRawType(), Integer.class);
 * equals(parameters.get(1).getRawType(), List.class);
 * equals(parameters.get(1).getParameters().get(0).getRawType(), List.class);
 * equals(parameters.get(1).getParameters().get(0).getParameters().get(0).getRawType(), List.class);
 * equals(parameters.get(1).getParameters().get(0).getParameters().get(0).getParameters().get(0).getRawType(), Integer.class);
 * }
 * </pre>
 * <p>
 * This class is only useful to obtain one parametrized type. But a similar strategy can be used to obtain any number of generic types.
 * </p>
 * This class captures information using Reflection. It captures using the <a href="http://gafter.blogspot.in/2006/12/super-type-tokens.html">super token</a>.
 * So in cases like:
 * <pre>
 *     {@code
 *
 *   class Sample<T> extends CaptureType<T>{
 *       List<T> t = new ArrayList<T>();
 *   }
 *   public void run(){
 *       Sample<String> sample = new Sample<String>();
 *   }
 *   }
 * </pre>
 * It is impossible to obtain the type information of {@code sample} and also {@code sample.t} as it is erased. In such cases {@code claz.getRawType();} will thrown an exception. {@code CaptureType} class will only work,
 * if the type is mentioned while declaring the Class (at any point in hierarchy). For example in:
 * <pre>
 *     {@code
 *
 *     class A<Z> extends CaptureType<Z>{}
 *     class B<E,T> extends A<T>{}
 *     class C <X,Y,Z> extends B<X, String>
 *     }
 * </pre>
 * It is possible to obtain the type {@code String}, because it is mentioned at class level and can be mapped by following hierarchy. So `Z` in {@code A} is {@code String}
 * </p>
 * <p>Note: Above, it would had been possible to obtain type information of {@code sample} object had it been an instance variable rather an local variable</p>
 *
 * @param <T> Any type parameter
 * @author Jatin
 */
public abstract class CaptureType<T> {
    /**
     * {@link java.lang.reflect.Type} object of the corresponding generic type. This method is useful to obtain every kind of information (including annotations) of the generic type.
     *
     * @return Type object. null if type could not be obtained (This happens in case of generic type whose information cant be obtained using Reflection). Please refer documentation of {@link types.CaptureType}
     */
    public Type getTypeParam() {
        Class<?> bottom = getClass();
        Map<TypeVariable<?>, Type> reifyMap = new LinkedHashMap<>();

        for (; ; ) {
            Type genericSuper = bottom.getGenericSuperclass();
            if (!(genericSuper instanceof Class)) {
                ParameterizedType generic = (ParameterizedType) genericSuper;
                Class<?> actualClaz = (Class<?>) generic.getRawType();
                TypeVariable<? extends Class<?>>[] typeParameters = actualClaz.getTypeParameters();
                Type[] reified = generic.getActualTypeArguments();
                assert (typeParameters.length != 0);
                for (int i = 0; i < typeParameters.length; i++) {
                    reifyMap.put(typeParameters[i], reified[i]);
                }
            }

            if (bottom.getSuperclass().equals(CaptureType.class)) {
                bottom = bottom.getSuperclass();
                break;
            }
            bottom = bottom.getSuperclass();
        }

        TypeVariable<?> var = bottom.getTypeParameters()[0];
        while (true) {
            Type type = reifyMap.get(var);
            if (type instanceof TypeVariable) {
                var = (TypeVariable<?>) type;
            } else {
                return type;
            }
        }
    }

    /**
     * Returns the raw type of the generic type.
     * <p>For example in case of {@code CaptureType<String>}, it would return {@code Class<String>}</p>
     * For more comprehensive examples, go through javadocs of {@link types.CaptureType}
     *
     * @return Class object
     * @throws java.lang.RuntimeException If the type information cant be obtained. Refer documentation of {@link types.CaptureType}
     * @see types.CaptureType
     */
    public Class<T> getRawType() {
        Type typeParam = getTypeParam();
        if (typeParam != null)
            return getClass(typeParam);
        else throw new RuntimeException("Could not obtain type information");
    }


    /**
     * Gets the {@link java.lang.Class} object of the argument type.
     * <p>If the type is an {@link java.lang.reflect.ParameterizedType}, then it returns its {@link java.lang.reflect.ParameterizedType#getRawType()}</p>
     *
     * @param type The type
     * @param <A>  type of class object expected
     * @return The Class<A> object of the type
     * @throws java.lang.RuntimeException If the type is a {@link java.lang.reflect.TypeVariable}. In such cases, it is impossible to obtain the Class object
     */
    public static <A> Class<A> getClass(Type type) {
        if (type instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) type).getGenericComponentType();
            Class<?> componentClass = getClass(componentType);
            if (componentClass != null) {
                return (Class<A>) Array.newInstance(componentClass, 0).getClass();
            } else throw new UnsupportedOperationException("Unknown class: " + type.getClass());
        } else if (type instanceof Class) {
            Class claz = (Class) type;
            return claz;
        } else if (type instanceof ParameterizedType) {
            return getClass(((ParameterizedType) type).getRawType());
        } else if (type instanceof TypeVariable) {
            throw new RuntimeException("The type signature is erased. The type class cant be known by using reflection");
        } else throw new UnsupportedOperationException("Unknown class: " + type.getClass());
    }

    /**
     * This method is the preferred method of usage in case of complex generic types.
     * <p>It returns {@link types.TypeADT} object which contains nested information of the type parameters</p>
     *
     * @return TypeADT object
     * @throws java.lang.RuntimeException If the type information cant be obtained. Refer documentation of {@link types.CaptureType}
     */
    public TypeADT getParamADT() {
        return recursiveADT(getTypeParam());
    }

    private TypeADT recursiveADT(Type type) {
        if (type instanceof Class) {
            return new TypeADT((Class<?>) type, null);
        } else if (type instanceof ParameterizedType) {
            ArrayList<TypeADT> generic = new ArrayList<>();
            ParameterizedType type1 = (ParameterizedType) type;
            return new TypeADT((Class<?>) type1.getRawType(),
                    Arrays.stream(type1.getActualTypeArguments()).map(x -> recursiveADT(x)).collect(Collectors.toList()));
        } else throw new UnsupportedOperationException();
    }

}





