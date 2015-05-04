import java.util.ArrayList;
import java.util.List;

public class Reads {
    public void covariant(){
        List<? extends Animal> ls1 = new ArrayList<Animal>();
        Animal animal = ls1.get(0);
        Object ob = ls1.get(0);
        //Dog dog = ls1.get(0);
        //end

        List<? extends Dog> ls2 = new ArrayList<Dog>();
        Dog dog1 = ls2.get(0);
        Animal animal1 = ls2.get(0);
        Object ob1 = ls2.get(0);

        List<? extends Object> ls3 = new ArrayList<Object>();
        //Dog dog2 = ls3.get(0);
        //Animal animal2 = ls3.get(0);
        Object ob2 = ls3.get(0);
        //end
    }

    public void contravariance(){
        List<? super Animal> lls1 = new ArrayList<Animal>();
        //Animal animal = lls1.get(0);
        Object ob = lls1.get(0);
        //Dog dog = lls1.get(0);
        //end

        List<? super Dog> lls2 = new ArrayList<Dog>();
        //Dog dog1 = lls2.get(0);
        //Animal animal1 = lls2.get(0);
        Object ob1 = lls2.get(0);

        List<? super Object> lls3 = new ArrayList<Object>();
        //Dog dog2 = lls3.get(0);
        //Animal animal2 = lls3.get(0);
        Object ob2 = lls3.get(0);
        //end
    }
}
