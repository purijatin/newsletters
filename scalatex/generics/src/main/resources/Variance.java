import java.util.*;

public class Variance{
	public void covariance(){
		Animal a = new Dog(); // Dog <: Animal
		Dog[] dog = new Dog[1];
		Animal[] animals = dog; // Dog[] <: Animal[]
		Object[] objects = dog; // Dog[] <: Animal[]
		//end
	}

	public void invariance(){		
		List<Animal> ls = new ArrayList<Dog>(); // co-variance. Invalid in Java
		List<Dog> ls = new ArrayList<Animal>(); // contra-variance. Invalid in Java
		List<Animal> ls = new ArrayList<Animal>(); // in-variant. valid.
		//end
	}

	public void wild(){
		List<?> ls ;// ls is a list of some unknown type. 
		List<? extends Dog> dog; // dog is a list of elements of some unknown type.
		// In java, all reference types extends Object. So now we know that:
		//	-	This unknown type <: Object
		//	-	Dog <: Thus unknown type
		// What we do not know is, the precise type of the unknown type. It can be anything in between the bounds
		List<? super Dog> dog; 		
		//	 Dog <: This unknown type <: Object
		//end
	}
	public void arrays(){
		Dog[] dogs = new Dog[3];
		Animal[] ob = dogs; //because of array covariance. line-a
		ob[0] = new Cat(); //(1)
		ob[1] = "";//(2)
		ob[2] = SimpleBeanFactoryAwareAspectInstanceFactory
				.getContextBean()
				.getBeanContextServiceProviderBeanInfo();//(3)
		//Thats how folks in java world name classes right?
		//end
	}

	public void genericArrays(){
		ArrayList<String>[] ls = 
			new ArrayList<String>[3];
		//Above is illegal in Java.
		//end
		//If it was allowed, you could do:
		Object[] ob = ls;
		ob[0] = new ArrayList<Animal>();
		//An ArrayStoreExcepton should have been thrown,
		//but the runtime can't detect it
		ArrayList<String> s = ls[0];
		String name = s.get(0); 
		//BOOM! BOOM! ClassCastException
		//s.get(0) will return an Animal and not String		
		//end
	}

}