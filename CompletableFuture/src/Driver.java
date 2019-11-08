import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Driver
{
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        System.out.println("CompletableFuture brief tutorial");

        MyCompletableFuture myCompletableFuture = new MyCompletableFuture();

        //myCompletableFuture.completedFuture1();
        //myCompletableFuture.completedFuture2();
        myCompletableFuture.completedFuture3();
    }
}
