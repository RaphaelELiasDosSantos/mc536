package valueObjects;

public class Pair<T, J> {
	private T t;
	private J j;
	
	public Pair(T t, J j){
		this.t = t;
		this.j = j;
	}
	
	public T getfirst(){
		return this.t;
	}
	
	public J getSecond(){
		return this.j;
	}
}
