package com.example.dgsdemo.datafetcher;

import java.util.List;
import java.util.stream.Collectors;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

@DgsComponent
public class ShowsDataFetcher {

    private final List<Show> shows = List.of(
            new Show("Stranger Things", 2016, "Sci-Fi"),
            new Show("Breaking Bad", 2008, "Drama"),
            new Show("The Witcher", 2019, "Fantasy"),
            new Show("Game of Thrones", 2011, "Fantasy"),
            new Show("The Mandalorian", 2019, "Sci-Fi")
    );

    @DgsQuery
    public List<Show> shows(@InputArgument String titleFilter, @InputArgument String genreFilter) {
//        if(titleFilter == null) {
//            return shows;
//        }
//
//        return shows.stream().filter(s -> s.title().contains(titleFilter)).collect(Collectors.toList());
        return shows.stream()
                .filter(show -> (titleFilter == null || show.title().contains(titleFilter)) &&
                        (genreFilter == null || show.genre().equalsIgnoreCase(genreFilter)))
                .collect(Collectors.toList());
    }
}

record Show(String title, int releaseYear, String genre) {}