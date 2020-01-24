package game;

public interface Observable {

    /**
     * Register an observer.
     * @param observer the observer to register.
     */
    void registerObserver(Observer observer);

    /**
     * deregister an observer.
     * @param observer the observer to deregister.
     */
    void deregisterObserver(Observer observer);

    /**
     * Notify each observer that is available.
     */
    void notifyObservers();
}
