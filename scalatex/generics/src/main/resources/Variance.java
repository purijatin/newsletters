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
}