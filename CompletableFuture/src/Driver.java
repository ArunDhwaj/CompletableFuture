import java.util.concurrent.ExecutionException;

public class Driver
{
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        System.out.println("CompletableFuture brief tutorial: Basic");
        MyCompletableFutureBasic myCompletableFutureBasic = new MyCompletableFutureBasic();

        //myCompletableFutureBasic.ReturningAlreadyCompletedValue();
        //myCompletableFutureBasic.ReturningVoidByRunAsync();
        //myCompletableFutureBasic.ReturningValueBySupplyAsync();
        //myCompletableFutureBasic.ChainingSupplyAsyncAndThenApply();
        //myCompletableFutureBasic.ChainingSupplyAsyncAndThenApplyByExecutor();
        //myCompletableFutureBasic.ChainingSupplyAsyncAndThenAccept();

        //==============================================================

        System.out.println("CompletableFuture brief tutorial: Advanced");
        MyCompletableFutureAdvanced cfa = new MyCompletableFutureAdvanced();

        //cfa.ThenApply();
        //cfa.ThenCompose();
        //cfa.ThenCombine();
        //cfa.HandleExceptionWithHandle();
        //cfa.HandleExceptionWithHandle();
        cfa.HandleExceptionWithWhenComplete();
    }
}
