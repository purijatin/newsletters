import java.util.ArrayList;
import java.util.List;

public class Writes {
    public void contravariant(){
        List<? super Animal> ls1 = new ArrayList<Animal>();
        ls1.add(new Animal());
        ls1.add(new Dog());
        //ls1.add(new Object());
        //end

        List<? super Dog> ls2 = new ArrayList<Dog>();
        //ls2.add(new Animal()); Animal does not <: (? super Dog)
        ls2.add(new Dog());
        //ls2.add(new Object()); Object does not <: (? super Dog)

        List<? super Object> ls3 = new ArrayList<Object>();
        ls3.add(new Animal()); // Animal <: (? super Animal)
        ls3.add(new Dog());
        ls3.add(new Object());

        List<? super Dog> ls4 = new ArrayList<Animal>();
        //Let `t` be type of which elements can be legally added to above list
        //`t` <: (Animal). Because in reality it is a list of animals
        //`t` should be able to  <: (? super Dog). Because it is also a list of (? super Dog)
        //From above, elements only less than or equal to Dog allowed
        ls4.add(new Dog());
        ls4.add(new Canine());
        //ls4.add(new Animal());

        List<Animal> ls5 = new ArrayList<Animal>();
        ls5.add(new Cat());
        List<? super Dog> l = ls5;

        //List<? super Animal> ls5 = new ArrayList<Dog>(); //error
        //Let `t` be type of which elements can be legally added to above list
        //`t` should <: (Dog). Because in reality it is a list of dogs
        //`t` should be able to <: (? super Animal). Because it is also a list of (? super Animal)
        //This is not possible, hence compiler gives error
        //end
    }

    public void covariant(){
        List<? extends Animal> ls1 = new ArrayList<Animal>();
        //ls1.add(new Animal());
        //ls1.add(new Dog());
        //ls1.add(new Object());
        //end

        List<? extends Dog> ls2 = new ArrayList<Dog>();
        //ls2.add(new Animal()); Animal does not <: (? extends Dog)
        //ls2.add(new Dog());
        //ls2.add(new Object()); Object does not <: (? extends Dog)

        List<? extends Object> ls3 = new ArrayList<Object>();
        //ls3.add(new Animal()); // Animal <: (? extends Animal)
        //ls3.add(new Dog());
        //ls3.add(new Object());

        //List<? extends Dog> ls4 = new ArrayList<Animal>();
        //Let `t` be type of which elements can be legally added to above list
        //`t` should <: (Animal). Because in reality it is a list of animals
        //`t` should be able to <: (? extends Dog). Because it is also a list of (? extends Dog)
        // No such `t` exists that can be <: (? extends Dog).


        List<? extends Animal> ls5 = new ArrayList<Dog>();
        //Let `t` be type of which elements can be legally added to above list
        //`t` should <: (Dog). Because in reality it is a list of dogs
        //`t` should <: (? extends Animal). Because it is also a list of (? extends Animal)
        //ls5.add(new Animal()); How can `t` be subtype of (? extends Animal)
        //end

    }
}
