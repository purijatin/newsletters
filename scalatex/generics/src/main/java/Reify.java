
public class Reify {
    public void example1(){
        //start
        Dog[] d = new Dog[1];
        d.getClass().equals(Dog[].class);//It knows it is an array
        Object[] ob = d;
        ob[0] = new Animal(); //throws ArrayStoreException. This is because it has information available at runtime regarding dogness of `d`
        //end
    }

    public static void main(String[] args) {
        Reify r = new Reify();
        r.example1();
    }
}
