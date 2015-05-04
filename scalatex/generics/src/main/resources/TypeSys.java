class City{
    public String getName(){}
}

class Country{
    public String getName(){}
}

static void storeInDb(City city){}
City city;
Country country;
storeInDb(country); // compiler error
//end

def foo(x: { def getName: String }) = println(x.getName)

foo(new City) //work
foo(new Country) //works
//end
