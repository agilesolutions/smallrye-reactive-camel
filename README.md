# CDI for SE with SmallRye Reactive Messaging with Camel

This repository contains one application based on *Weld SE*.
Those are CDI containers (think dependency injection) readily usable in a Java SE environment.
For example, small apps or 12 factor apps.

## Why and What?

Think what you will about the enterprise Java as a whole, some of their technologies are really neat.
Also, since they are more and more usable without any heavy frameworks or boilerplate or configuration,
they are a real contender in Java SE space.

Dependency injection allows you to decouple the components of your app,
so you don't need to spend any time thinking about constructing and wiring them up.
CDI also takes care of lifecycle management of those components,
so you get safe singletons without even thinking about multi-threading issues.
CDI even has a neat event system.
All this with next to no annotations or configuration.

## How it works

This project contains a `bean-archive`, which is just a JAR really,
that contains CDI compatible beans, which are just POJOs really.
This JAR forms the actual application.

## How to run

```bash
mvn compile exec:java -Dexec.mainClass=io.smallrye.reactive.messaging.quickstart.QuickStart
```

## Read

- [Content based routing with Camel Java DSL](https://tomd.xyz/articles/camel-choice-when/)
- [SmallRye Reactive Messaging](https://smallrye.io/smallrye-reactive-messaging/)
- [GITHUB on SmallRye all you need to build EE Java apps](https://github.com/smallrye)
- [Quarkus and messaging over Kafka](https://quarkus.io/guides/kafka-guide)