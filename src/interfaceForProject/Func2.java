package interfaceForProject;

/**
 * Интерфейс работает с 3 параметрами неопределенного типа
 * @param <A> первый параметр
 * @param <B> второй параметр
 * @param <R> третий параметр, возвращаемый
 */
public interface Func2 <A,B,R>{
    R apply(A arg1,B arg2);
}
