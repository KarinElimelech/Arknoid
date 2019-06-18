package collision;

/**
 * The interface Hit notifier.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public interface HitNotifier {
    /**
     * Add hit listener.
     *
     * @param hl the listener
     */
// Add hl as a listener to hit events.
    void addHitListener(HitListener hl);

    /**
     * Remove hit listener.
     *
     * @param hl the hit listener
     */
// Remove hl from the list of listeners to hit events.
    void removeHitListener(HitListener hl);
}
