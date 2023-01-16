package interfaceForProject;

/**
 * Интерфейс принимающий два неизвестных параметра
 * @param <A> первый параметр
 * @param <R> второй параметр, возвращаемый
 */
public interface Func1<A, R> {
        R apply(A arg);
    }

