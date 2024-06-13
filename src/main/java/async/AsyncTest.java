package async;

import io.vavr.collection.List;
import io.vavr.*;
import io.vavr.control.Option;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * You should complete the function in this class
 */
class AsyncTest {

  private static List<Enterprise> enterprises = List.of(
      new Enterprise("ent_1", "Google", "ceo_2"),
      new Enterprise("ent_2", "Facebook", "ceo_1")
  );

  private static List<Ceo> ceos = List.of(
      new Ceo("ceo_1", "Mark"),
      new Ceo("ceo_2", "Sundar"),
      new Ceo("ceo_3", "Bill")
  );

  private static ExecutorService executor = Executors.newCachedThreadPool();

  public static CompletableFuture<Option<Ceo>> getCeoById(String ceo_id) {
    return CompletableFuture.supplyAsync(() -> {
      return ceos.find(c -> c.getId().equals(ceo_id));
    }, executor);
  }


  public static CompletableFuture<Option<Enterprise>> getEnterpriseByCeoId(String ceo_id) {
    return CompletableFuture.supplyAsync(() -> {
      return enterprises.find(e -> e.getCeoId().equals(ceo_id));
    }, executor);
  }

  public static CompletableFuture<Tuple2<Option<Ceo>, Option<Enterprise>>> getCEOAndEnterprise(String ceo_id) {
    CompletableFuture<Option<Ceo>> c = getCeoById(ceo_id);
    CompletableFuture<Option<Enterprise>> e = getEnterpriseByCeoId(ceo_id);
    return c.thenCombine(e, (ceo, entreprise) -> Tuple.of(ceo, entreprise));
  }

}
