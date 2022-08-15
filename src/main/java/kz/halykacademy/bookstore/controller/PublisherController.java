package kz.halykacademy.bookstore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.halykacademy.bookstore.dto.PublisherDto;
import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.service.api.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@Controller
@Api(description="Издатели APIs", tags = "Издатели")
@RequestMapping("/v1/api/publisher")
public class PublisherController {
    private final PublisherService publisherService;

    @GetMapping("/get-all")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ApiOperation(value = "Список всех издателей", notes = "Список всех издателей")
    public ResponseEntity<List<Publisher>> getAll(){
        return ResponseEntity.ok(publisherService.getAll());
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Добавить нового издателя", notes = "Добавить нового издателя")
    public ResponseEntity<Publisher> createPublisher(@RequestBody PublisherDto publisherDto){
        return ResponseEntity.ok(publisherService.createPublisher(publisherDto));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Удалить издателя", notes = "Удалить издателя")
    public ResponseEntity<Void> deletePublisherById(@PathVariable Long id){
        publisherService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-by-name/{name}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ApiOperation(value = "Поиск издателей по имени", notes = "Поиск издателей по имени")
    public ResponseEntity<List<Publisher>> getListByName(@PathVariable String name){
        return ResponseEntity.ok(publisherService.getByName(name));

    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ApiOperation(value = "Поиск издателя по айди", notes = "Поиск издателя по айди")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable Long id){
        return ResponseEntity.ok(publisherService.getById(id));
    }

}
