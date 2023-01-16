package people;
import java.util.ArrayList;
import java.util.List;

import interfaceForProject.Func1;


public class FuncUtils {

    public static <X> List<X> filter(List<X> list, Func1<X, Boolean> filterFunction) {
        ArrayList<X> result = new ArrayList<>();
        for(X item: list) {
            if (filterFunction.apply(item)) {
                result.add(item);
            }
        }
        return result;
    }

}
