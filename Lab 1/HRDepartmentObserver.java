
public class HRDepartmentObserver implements Observer {
    @Override
    public void update(String message) {
        System.out.println("HR Department notified: " + message);
    }
}