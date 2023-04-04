/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package parschedule;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.concurrent.atmoic.AtomicInteger;

public class AppTest {
    private int taskCount;
    public static Duration diff(Calendar first, Calendar second) {
        return Duration.ofMillis(second.getTimeInMillis() - first.getTimeInMillis());
    }

    @Test
    public void simpleTimeOffsetTest() {
        assertEquals(TimeOffsetParser.parseTimeOffset("10s"), Duration.ofSeconds(10));
        assertEquals(TimeOffsetParser.parseTimeOffset("5m10s"), Duration.ofSeconds(310));
        assertEquals(TimeOffsetParser.parseTimeOffset("1d"), Duration.ofDays(1));
    }

    @Test
    public void simpleTimeSetTest() {
        Calendar threePM = Calendar.getInstance();
        threePM.set(Calendar.HOUR_OF_DAY, 15);
        threePM.set(Calendar.MINUTE, 0);
        threePM.set(Calendar.SECOND, 0);
        assertEquals(TimeOffsetParser.parseTimeOffset("3:00pm").getSeconds(), diff(Calendar.getInstance(), threePM).getSeconds());
    }

     @Test
    public void scheduleTaskTest() throws InterruptedException{
        PersistentScheduler scheduler = new PersistentScheduler();
        AtomicInteger counter = new AtomicInteger(0);

        Runnable firstTask = () ->{
            System.out.println("Task1: Do something.");
            counter.incrementAndGet();
        };

        Runnable secondTask = () ->{
            System.out.println("Task2: Do something else.");
            counter.incrementAndGet();
        };
        scheduler.scheduleTask(firstTask, 2);
        scheduler.scheduleTask(secondTask,2);

        Thread.sleep(3000); //Wait for tasks to finish

        assertEquals(2, counter.get(), "All tasks were executed.");


    }


    }
@Test
    public void persistentSchedulerTest(){
       PersistentScheduler scheduler = new PersistentScheduler();
       String fileName = "";
       Runnable task = () -> {
            System.out.println("Test to be deleted from scheduler.");
       };
       scheduler.scheduleTask(task, 2);
       
       File file = new File(fileName);
       scheduler.saveToFile(fileName);
       assertEquals("Task.txt", file, "The file exists.");

    }
}

