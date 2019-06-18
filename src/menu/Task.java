package menu;

/**
 * The interface Task.
 *
 * @param <T> the type parameter
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public interface Task<T> {
    /**
     * Run t.
     *
     * @return some val.
     */
    T run();
}
