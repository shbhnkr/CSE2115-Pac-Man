package game;

public interface Observable {

    /**
     * Register an observer.
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * deregister an observer.
     * @param observer
     */
    void deregisterObserver(Observer observer);

    /**
     * Notify each observer that is available.
     */
    void notifyObservers();
}
