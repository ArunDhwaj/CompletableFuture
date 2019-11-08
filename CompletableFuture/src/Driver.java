import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Driver
{
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        System.out.println("CompletableFuture brief tutorial");
        completedFuture1();
        completedFuture2();
    }

    private static void completedFuture1() throws ExecutionException, InterruptedException
    {
        //1: A new CompletableFuture is returned that is already completed with the given value.

        String str = "1) A new CompletableFuture is returned that is already completed with the given value.";
        CompletableFuture<String> cf = CompletableFuture.completedFuture(str);

        if (cf.isDone())
        {
            System.out.println("Value:: " + cf.get());
        }
    }

    private static void completedFuture2() throws ExecutionException, InterruptedException
    {
        //2: Running an asynchronous task using runAsync(Runnable runnable) method.
        // This method returns a CompletableFuture<Void>.

        String str = "2: Running an asynchronous task using runAsync(Runnable runnable) method. This method returns a CompletableFuture<Void>.";


        CompletableFuture<Void> cf = CompletableFuture.runAsync(()->
        {
            System.out.println("Task executing asynchronously");
            System.out.println(str);
        });

        System.out.println("Value- " + cf.get());
    }
}
