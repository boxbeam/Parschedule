import java.io.File;

public class PersistentScheduler {
    private List<Task> tasks = new ArrayList<>();
    private String filePath;


    public PersistentScheduler(String filePath) {
        this.filePath = filePath;
    }

    public void scheduleTask(Runnable task, long  delaySeconds) {

    }

    public void saveToFile(){

    }

    public static PersistentScheduler loadFromFile(String filePath) {
        return null;
    }
}



