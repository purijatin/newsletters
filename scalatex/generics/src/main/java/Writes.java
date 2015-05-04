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
        //end
    }

    public void covariant(){
        List<? extends Animal> lls1 = new ArrayList<Animal>();
        //lls1.add(new Animal());
        //lls1.add(new Dog());
        //lls1.add(new Object());
        //end

        List<? extends Dog> lls2 = new ArrayList<Dog>();
        //lls2.add(new Animal()); Animal does not <: (? extends Dog)
        //lls2.add(new Dog());
        //lls2.add(new Object()); Object does not <: (? extends Dog)

        List<? extends Object> lls3 = new ArrayList<Object>();
        //lls3.add(new Animal()); // Animal <: (? extends Animal)
        //lls3.add(new Dog());
        //lls3.add(new Object());
        //end

    }

    public void teasers(){
        List<? super Dog> ls4 = new ArrayList<Animal>();//legal
        ls4.add(new Dog());
        ls4.add(new Canine());
        //ls4.add(new Animal());

        //List<? super Animal> ls5 = new ArrayList<Dog>(); //error
        //List<? extends Dog> ls4 = new ArrayList<Animal>();//error

        List<? extends Animal> ls5 = new ArrayList<Dog>();
        //ls5.add(new Animal()); How can `t` be subtype of (? extends Animal)
        //end
    }
}
