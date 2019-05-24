package io.smallrye.reactive.messaging.camel.outgoing;

import org.apache.camel.Exchange;
import org.apache.camel.component.reactive.streams.api.CamelReactiveStreamsService;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.reactivestreams.Publisher;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class BeanWithCamelRoute {

  @Inject
  private CamelReactiveStreamsService camel;

  private List<String> values = new ArrayList<>();

  @Incoming("sink")
  public CompletionStage<Void> sink(String value) {
    values.add(value);
    return CompletableFuture.completedFuture(null);
  }

  @Incoming("camel")
  @Outgoing("sink")
  public String extract(Exchange exchange) {
    return exchange.getIn().getBody(String.class);
  }

  @Outgoing("camel")
  public Publisher<Exchange> source() {
    return camel.from("seda:camel");
  }

  public List<String> values() {
    return values;
  }
}
