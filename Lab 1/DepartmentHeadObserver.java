public class DepartmentHeadObserver implements Observer {
    @Override
    public void update(String message) {
        System.out.println("Department Head notified: " + message);
    }
}
