import java.util.concurrent.ExecutionException;

public class Driver
{
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        System.out.println("CompletableFuture brief tutorial");

        MyCompletableFutureBasic myCompletableFutureBasic = new MyCompletableFutureBasic();

        //myCompletableFutureBasic.ReturningAlreadyCompletedValue();
        //myCompletableFutureBasic.ReturningVoidByRunAsync();
        //myCompletableFutureBasic.ReturningValueBySupplyAsync();
        //myCompletableFutureBasic.ChainingSupplyAsyncAndThenApply();
        //myCompletableFutureBasic.ChainingSupplyAsyncAndThenApplyByExecutor();

        myCompletableFutureBasic.ChainingSupplyAsyncAndThenAccept();
    }
}
