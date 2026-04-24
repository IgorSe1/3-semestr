import java.util.Comparator;

public class ReverseComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer a, Integer b) {
        return b - a;
    }
}
