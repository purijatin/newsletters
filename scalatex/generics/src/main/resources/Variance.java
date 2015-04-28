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
}