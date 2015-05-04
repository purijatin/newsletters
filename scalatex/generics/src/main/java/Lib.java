import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class Lib {
    public void go(){
        //Collections.max()
        ArrayList<Animal> strings = new ArrayList<>();
        Stream<String> s;
        strings.forEach((Object o) -> System.out.print(true));
        Class<String> stringClass = String.class;
    }

    public void foreach(){
        ArrayList<Animal> fe = new ArrayList<>();
        fe.forEach((Object o) -> System.out.println(o));
        //end
    }

    public void addAll(){
        ArrayList<Animal> addA = new ArrayList<>();
        ArrayList<Dog> b = new ArrayList<>();
        addA.addAll(b);
        //fine to add dogs to a list of animals
        //end
    }
}

class AddAll<E>{
    public <T extends E> boolean addAll(Collection<T> c){
        return false;
    }
    public void go(){
        AddAll<Animal> a = new AddAll<>();
        a.addAll(new ArrayList<Dog>());
    }
}

class Min{
    public static <T extends Comparator<T>> T min1(Collection<T> elem)
        //end
        {
        return null;
    }

    static <T extends Comparator<? super T>> T min2(Collection<? extends T> elem)
        //end
    {
        return null;
    }
}

class Copy{
    static <T, E extends T> void copy(List<T> dest, List<E> src)
            //end
    {
        Collections.copy(dest,src);
    }

    static <T> void copy2(List<? super T> dest, List<? extends T> src){
        //end
        Collections.copy(dest,src);
    }
}

class Claz{
    static void getCla(){
        Dog a = new Dog();
        Class<? extends Dog> aa = a.getClass();
        Animal b = a;
        Class<? extends Animal> bb = b.getClass();
        //end
        Function1 f = new Function1();
        Function<? super Dog, ? extends Animal> function = f.andThen2((Dog x) -> new Animal(),
                (Object x) -> new Animal());
    }
}
class Function1{
    public <A,B,C> Function<A,C> andThen(Function<A,B> first, Function<B,C> later){
        return first.andThen(later);
    }

    public <A,B,C> Function<? super A,? extends C> andThen2(Function<? super A,? extends B> first,
                                                            Function<? super B,? extends C> later){
        return first.andThen(later);
    }
}