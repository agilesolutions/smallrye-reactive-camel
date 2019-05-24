package io.smallrye.reactive.messaging.camel.outgoing;

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
public class BeanWithTypedCamelPublisher {

  @Inject
  private CamelReactiveStreamsService camel;

  private List<String> values = new ArrayList<>();

  @Incoming("sink")
  public CompletionStage<Void> sink(String value) {
    values.add(value);
    return CompletableFuture.completedFuture(null);
  }

  @Outgoing("sink")
  public Publisher<String> source() {
    return camel.from("seda:camel", String.class);
  }

  public List<String> values() {
    return values;
  }

}
