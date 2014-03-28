
public class IsEvenFunctor implements UnaryPred<Integer> {
	public boolean pred(Integer x) { return x % 2 == 0; }
}
