
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MyCompletableFuture
{
    public void completedFuture1() throws ExecutionException, InterruptedException
    {
        //1: A new CompletableFuture is returned that is already completed with the given value.

        String str = "1) A new CompletableFuture is returned that is already completed with the given value.";
        CompletableFuture<String> cf = CompletableFuture.completedFuture(str);

        if (cf.isDone())
        {
            System.out.println("Value:: " + cf.get());
        }
    }

    public void completedFuture2() throws ExecutionException, InterruptedException
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

    public void completedFuture3() throws ExecutionException, InterruptedException
    {
        //3: runAsync() is fine for running asynchronous computations but it doesn't return value.
        // If you want to return a new CompletableFuture with a value then you can use supplyAsync(Supplier<U> supplier) method.
        // Here U is the type of value obtained by calling the given Supplier.

        String str = "3.1: runAsync() is fine for running asynchronous computations but it doesn't return value. If you want to return a new CompletableFuture with a value then you can use supplyAsync(Supplier<U> supplier) method. Here U is the type of value obtained by calling the given Supplier.";

        CompletableFuture cf = CompletableFuture.supplyAsync(()->
        {
            System.out.println(str);
            return str;
        });

        System.out.println("3.2:: " + cf.get());
    }
}
