package io.smallrye.reactive.messaging.camel;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class MySink {

  private List<String> list = new ArrayList<>();

  @Incoming("data")
  public void consume(String content) {
    list.add(content);
  }

  @Produces
  public Config myConfig() {
    String prefix = "smallrye.messaging.source.data.";
    Map<String, Object> config = new HashMap<>();
    config.putIfAbsent(prefix +  "name", "foo-out");
    config.put(prefix + "type", Camel.class.getName());
    return new MapBasedConfig(config);
  }

  public List<String> list() {
    return list;
  }
}
