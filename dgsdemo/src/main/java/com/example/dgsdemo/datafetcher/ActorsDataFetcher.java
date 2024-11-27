package com.example.dgsdemo.datafetcher;

import java.util.List;
import java.util.stream.Collectors;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
@DgsComponent
public class ActorsDataFetcher {
    private final List<Actor> actors = List.of(
            new Actor("Millie Bobby Brown", 19, List.of("Stranger Things")),
            new Actor("Bryan Cranston", 67, List.of("Breaking Bad")),
            new Actor("Henry Cavill", 40, List.of("The Witcher")),
            new Actor("Emilia Clarke", 37, List.of("Game of Thrones")),
            new Actor("Pedro Pascal", 49, List.of("The Mandalorian"))
    );

    @DgsQuery
    public List<Actor> actors(@InputArgument Integer ageFilter, @InputArgument String nameFilter) {
        return actors.stream()
                .filter(actor -> (ageFilter == null || actor.age() == ageFilter) &&
                        (nameFilter == null || actor.name().toLowerCase().contains(nameFilter.toLowerCase())))
                .collect(Collectors.toList());
    }
}

record Actor(String name, int age, List<String> shows) {}
