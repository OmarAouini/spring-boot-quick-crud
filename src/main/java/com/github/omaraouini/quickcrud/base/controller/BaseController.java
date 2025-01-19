package com.github.omaraouini.quickcrud.base.controller;

import com.github.omaraouini.quickcrud.base.dto.BaseDto;
import com.github.omaraouini.quickcrud.base.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * base controller class used to define the common endpoints of all controllers
 * that need to be used in the application <br>
 * it will provide CRUD automatically for all entities <br>
 * this class has the following methods:
 * <ul>
 *     <li>get: a method that returns a page of entities</li>
 *     <li>get: a method that returns an entity by its id</li>
 *     <li>put: a method that updates an entity by its id</li>
 *     <li>post: a method that creates an entity</li>
 *     <li>delete: a method that deletes an entity by its id</li>
 *     <li>example usage:
 *     <pre>
 *         {@code
 *         @RestController
 *         @RequestMapping("/people")
 *         public class PersonController extends BaseController<PersonDto, Integer> {
 *         public PersonController(PersonService personService) {
 *         super(personService);
 *         }
 *         }
 *         }
 * </pre> <br>
 * you can override any method if you want to add more logic to it before or after the service call, example:
 * <pre>
 *     {@code
 *     @Override
 *     public ResponseEntity<PersonDto> post(@RequestBody final PersonDto toCreate) {
 *     toCreate.setCreatedBy(SecurityUtils.getCurrentUser());
 *     return super.post(toCreate);
 *     }
 *     }
 * </pre>
 *
 * @author aouin
 * Date: 04/03/2023
 * Time: 16:51
 */
@Slf4j
public abstract class BaseController<D extends BaseDto<I>, I extends Serializable> {

    private final CrudService<D,I> service;

    protected BaseController(CrudService<D,I> crudService) {
        this.service = crudService;
    }

    @GetMapping
    public ResponseEntity<Page<D>> get(@ModelAttribute final D dto, @PageableDefault(sort = {"id"}, page = 0, value = Integer.MAX_VALUE) final Pageable pageable){
        return ResponseEntity.ok(service.get(dto,pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> get(@PathVariable final I id){
        return ResponseEntity.ok(service.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<D> put(@PathVariable final I id, @RequestBody final D toUpdate){
        return ResponseEntity.ok(service.put(id, toUpdate));
    }

    @PostMapping
    public ResponseEntity<D> post(@RequestBody final D toCreate) {
        return new ResponseEntity<>(service.post(toCreate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable final I id) {
        service.delete(id);
        return ResponseEntity.ok("OK");
    }

}