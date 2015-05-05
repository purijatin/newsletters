class KnowType<T>{
	private final Class<T> claz;
	public KnowType(Class<T> claz){
		this.claz = claz;
	}

	public Class<T> getTypeInfo(){
		return claz;
	}
}
//end
class KnowArray<T>{
    T[] arr;
    KnowArray(Class<T> claz){
        this.arr = (T[]) java.lang.reflect.Array.newInstance(claz,10);
    }
    public T[] getArr(){
        return arr;
    }
}
//end