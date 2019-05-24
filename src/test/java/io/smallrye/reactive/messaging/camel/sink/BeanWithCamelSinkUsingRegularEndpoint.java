package io.smallrye.reactive.messaging.camel.sink;

import io.smallrye.reactive.messaging.camel.Camel;
import io.smallrye.reactive.messaging.camel.MapBasedConfig;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;
import org.reactivestreams.Publisher;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class BeanWithCamelSinkUsingRegularEndpoint {

  @Outgoing("data")
  public Publisher<Message<String>> source() {
    return ReactiveStreams.of("a", "b", "c", "d")
      .map(String::toUpperCase)
      .map(Message::of)
      .buildRs();
  }

  @Produces
  public Config myConfig() {
    String prefix = "mp.messaging.outgoing.data.";
    Map<String, Object> config = new HashMap<>();
    config.putIfAbsent(prefix +  "endpoint-uri", "file:./target?fileName=values.txt&fileExist=append");
    config.put(prefix + "type", Camel.class.getName());
    return new MapBasedConfig(config);
  }


}
