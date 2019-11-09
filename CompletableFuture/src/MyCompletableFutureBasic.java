
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyCompletableFutureBasic
{
    public void ReturningAlreadyCompletedValue() throws ExecutionException, InterruptedException
    {
        //1: A new CompletableFuture is returned that is already completed with the given value.

        String str = "1) A new CompletableFuture is returned that is already completed with the given value.";
        CompletableFuture<String> cf = CompletableFuture.completedFuture(str);

        if (cf.isDone())
        {
            System.out.println("Value:: " + cf.get());
        }
    }

    public void ReturningVoidByRunAsync() throws ExecutionException, InterruptedException
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

    public void ReturningValueBySupplyAsync() throws ExecutionException, InterruptedException
    {
        //3: runAsync() is fine for running asynchronous computations but it doesn't return value.
        // If you want to return a new CompletableFuture with a value then you can use supplyAsync(Supplier<U> supplier) method.
        // Here U is the type of value obtained by calling the given Supplier.

        String str = "3.1: runAsync() is fine for running asynchronous computations but it doesn't return value. " +
                "If you want to return a new CompletableFuture with a value then you can use supplyAsync(Supplier<U> supplier) method. " +
                "Here U is the type of value obtained by calling the given Supplier.";

        CompletableFuture cf = CompletableFuture.supplyAsync(()->
        {
            System.out.println(str);
            return str;
        });

        System.out.println("3.2:: " + cf.get());
    }

    public void ChainingSupplyAsyncAndThenApply() throws ExecutionException, InterruptedException
    {
        //4: Adding a new stage for creating chain. Here thenApply(Function<? super T,? extends U> fn) method is used.
        // The current stage (thenApply() method) is executed with previous stage's result as the argument to the supplied function and it returns a new CompletionStage.

        String str = "4.1: Here thenApply(Function<? super T,? extends U> fn) method is used. " +
                "The current stage (thenApply() method) is executed with previous stage's result as the argument to " +
                "the supplied function and it returns a new CompletionStage.";

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(()->
        {
            System.out.println(str);
            return str;
            
        }).thenApply(v->v.toUpperCase());

        System.out.println("4.2:: " + cf.get());
    }

    public void ChainingSupplyAsyncAndThenApplyByExecutor() throws ExecutionException, InterruptedException
    {
        //5: Using the Async variant of the method where an Executor is passed.
        // Note that with the Async variant, method is asynchronously executed in a separate thread obtained from the Executor or from the ForkJoinPool.commonPool() based on the Async variant used.
        String str = "5.1: Using the Async variant of the method where an Executor is passed. " +
                "Note that with the Async variant, method is asynchronously executed in a separate thread obtained from the " +
                "Executor or from the ForkJoinPool.commonPool() based on the Async variant used.";

        ExecutorService es = Executors.newFixedThreadPool(2);

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(()->
        {
            System.out.println(str);
            return str;
        }).thenApplyAsync(v->v.toUpperCase(), es);

        System.out.println("5.2:: " + cf.get());
    }

    public void ChainingSupplyAsyncAndThenAccept() throws ExecutionException, InterruptedException
    {
        //6: Using thenAccept() method if there is no value to return from the stage.
        // There is also thenRun() method which doesn’t return value and takes Runnable as argument.

        String str = "6.1: Using thenAccept() method if there is no value to return from the stage. " +
                "There is also thenRun() method which doesn’t return value and takes Runnable as argument.";

        ExecutorService es = Executors.newFixedThreadPool(2);

        CompletableFuture<Void> cf = CompletableFuture.supplyAsync(()->
        {
            System.out.println(str);
            return str;
        }).thenAccept(v->{System.out.println( v.toUpperCase());});

        System.out.println("6.2:: " + cf.get());
    }

}
