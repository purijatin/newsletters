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
    }
}
class Function1{
    public static <A,B,C> Function<A,C> andThen(Function<A,B> first,
                                                Function<B,C> later){
        return first.andThen(later);
    }
    //end

    private static void andThenFail(){
        Function<Animal, Dog> f = (Animal a) -> new Dog(); //Animal => Dog
        Function<Object, Animal> g = (Object a) -> new Animal();//Object => Animal
        //Their composition should give: Animal => Animal. But
        //Function1.andThen(f,g); error
    }
    //end

    public static  <A,B,C> Function<A,C> andThen2
                                        (Function<? super A,? extends B> first,
                                        Function<? super B,? extends C> later){
        return (A t) -> later.apply(first.apply(t));
    }
    //end

    public void fog(){
        Function<Animal, Dog> f = (Animal a) -> new Dog();
        Function<Object, Animal> g = (Object a) -> new Animal();
        Function<? super Animal, ? extends Animal> function = andThen2(f, g);
        Animal apply = function.apply(new Animal());
        Function<Animal, Dog> h = (Animal a) -> new Dog();
        andThen2(andThen2(f,g), h);
    }
    //end
}