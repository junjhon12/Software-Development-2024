public class ManagerObserver implements Observer {
    @Override
    public void update(String message) {
        System.out.println("Manager notified: " + message);
    }
}
