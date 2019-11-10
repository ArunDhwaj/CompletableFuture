
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyCompletableFutureAdvanced
{
    /*
    1) Difference between thenApply() and thenCompose() methods in CompletableFuture
    ==================================================================================
    The two methods thenApply() and thenCompose() have a very little difference and it often confusing.

    i)thenApply(): Returns a new CompletionStage where the type of the result is based on the argument to the supplied
    function of thenApply() method.

    ii) thenCompose(): Returns a new CompletionStage where the type of the result is same as the type of the previous stage.
    For getting the difference between thenApply() and thenCompose() methods consider the following code.

    Example: A nested code snippet shown below.
    */

    public void ThenApply() throws ExecutionException, InterruptedException
    {
        CompletableFuture<CompletableFuture<String>> cf = CompletableFuture.supplyAsync(()->
        {
            return "Function: ThenApply";
        }).thenApply(v->{
            String str = v.toUpperCase();
            return CompletableFuture.completedFuture(str);
        });
        System.out.println("Value: " + cf.get().get());
    }

    public void ThenCompose() throws ExecutionException, InterruptedException
    {
        String str = "thenCompose(): Returns a new CompletionStage where the type of the result is same as the type of the " +
                "previous stage.";

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(()->{
            return "Function: ThenCompose" + str;
        }).thenCompose(v-> {
            return CompletableFuture.completedFuture(v.toUpperCase() + str);
        });

        System.out.println("Value: " + cf.get());
    }

    public void ThenCombine() throws ExecutionException, InterruptedException
    {
        /*
        There is a thenCombine() method that can be used if you want to combine two independent
        CompletableFutures in a way that when both of the CompletableFutures finish, you want to
        execute some logic with the results of both.

        thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)- Returns a
        new CompletionStage that, when this and the other given stage both complete normally, is executed with the
        two results as arguments to the supplied function.
        */

        Integer x1 = 10, x2 = 20;

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            return 2*x1;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            return 2*x2;
        });

        CompletableFuture<Integer> result = future1.thenCombine(future2, (x, y) -> x*y);

        System.out.println("Value: " + result.get());
    }

    public void HandleExceptionWithExceptionally() throws ExecutionException, InterruptedException
    {
        /*
        Exception handling with CompletableFuture in Java
        If an exception is thrown at any of the stage with in the chain of CompletionStages the
        execution stops with in that stage and exception is thrown. For exception handling with CompletableFuture there are
        three methods handle, whenComplete and exceptionally.

        Out of these three, two methods handle and whenComplete are executed regardless of exception thrown or not.
        Exception is passed as an argument is these methods which will not be null in case exception is thrown. Using that
        null check you can write your exception handling code.

        Exceptionally supports computation only when the triggering stage throws an exception. This method also gives a
        chance to return a replacement result in case of exception
        */

        System.out.println("1.1: Throwing Exception Example: ");
        String str1 = null;
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() ->
        {
            if (str1 == null)
                throw new IllegalArgumentException("Invalid String value passed " + str1);
            return str1;
        }).exceptionally(exp ->
        {
            //System.out.println("Exception thrown with message - " + exp.getMessage());
            return "Exception thrown with message - " + exp.getMessage();
        });

        System.out.println(cf1.get());

        System.out.println("1.2: Not Throwing Exception Example: ");
        String str2 = "Hello";
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() ->
        {
            if (str2 == null)
            {
                throw new IllegalArgumentException("Invalid String value passed " + str2);
            }
            else
            {
                return str2;
            }
        }).exceptionally(exp ->
        {
            System.out.println("Exception thrown with message - " + exp.getMessage());
            return "Exception catched: ";
        });

        System.out.println("Value: " + cf2.get());
    }

    public void HandleExceptionWithHandle() throws ExecutionException, InterruptedException
    {
        /*
        Out of these three, two methods handle and whenComplete are executed regardless of exception thrown or not.
        Exception is passed as an argument is these methods which will not be null in case exception is thrown. Using that
        null check you can write your exception handling code.
        */

        //String str3 = null;
        String str3 = "HandleExceptionWithHandle";

        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() ->
        {
            if (str3 == null)
            {
                throw new IllegalArgumentException("Invalid String value passed " + str3);
            }
            return str3;
        }).handle((s, exp) ->
        {
            if(exp != null)
            {
                System.out.println("Exception thrown with message - " + exp.getMessage());
                s = "";
            }
            return s;
        });

        System.out.println("Value: " + cf3.get());
    }

    public void HandleExceptionWithWhenComplete() throws ExecutionException, InterruptedException
    {
        /*
        Method whenComplete preserves the result of the triggering stage instead of computing a new one.
        */

        //String str4 = null;
        String str4 = "HandleExceptionWithWhenComplete";

        CompletableFuture<String> cf4 = CompletableFuture.supplyAsync(() ->
        {
            if (str4 == null)
            {
                throw new IllegalArgumentException("Invalid String value passed " + str4);
            }
            return str4;
        }).whenComplete((str, exp) ->
        {
            System.out.println("In whenComplete block");

            if(exp != null)
            {
                System.out.println("Exception thrown with message - " + exp.getMessage());
                str = "No exception";
            }
            //return str;
        });

        System.out.println("Value4: " + cf4.get());
    }
}
