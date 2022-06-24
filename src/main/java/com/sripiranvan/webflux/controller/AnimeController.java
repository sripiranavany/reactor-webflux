package com.sripiranvan.webflux.controller;

import com.sripiranvan.webflux.model.Anime;
import com.sripiranvan.webflux.service.AnimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("animes")
@Slf4j
@RequiredArgsConstructor
@SecurityScheme(type = SecuritySchemeType.HTTP, scheme = "basic",name = "basic", description = "Basic authentication")
public class AnimeController {

    private final AnimeService animeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    @Operation(summary ="List all animes", tags = {"Anime"}, security = @SecurityRequirement(name = "basic"))
    public Flux<Anime> listAll(){
        return animeService.findAll();
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary ="FindById", tags = {"Anime"}, security = @SecurityRequirement(name = "basic"))
    public Mono<Anime> findById(@PathVariable int id){
        return animeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary ="Create an anime", tags = {"Anime"}, security = @SecurityRequirement(name = "basic"))
    public Mono<Anime> save(@Valid @RequestBody Anime anime){
        return animeService.save(anime);
    }

    @PostMapping("batch")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary ="Create animes", tags = {"Anime"}, security = @SecurityRequirement(name = "basic"))
    public Flux<Anime> batchSave(@RequestBody List<Anime> animes){
        return animeService.saveAll(animes);
    }

    @PutMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary ="Update an anime", tags = {"Anime"}, security = @SecurityRequirement(name = "basic"))
    public Mono<Void> update(@PathVariable int id,@Valid @RequestBody Anime anime){
        return animeService.update(anime.withId(id));
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary ="Delete an anime", tags = {"Anime"}, security = @SecurityRequirement(name = "basic"))
    public Mono<Void> delete(@PathVariable int id){
        return animeService.delete(id);
    }
}
