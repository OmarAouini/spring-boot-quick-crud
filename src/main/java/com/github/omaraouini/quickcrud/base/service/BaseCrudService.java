package com.github.omaraouini.quickcrud.base.service;

import com.github.omaraouini.quickcrud.base.entity.BaseEntity;
import com.github.omaraouini.quickcrud.base.mapping.BaseMapper;
import com.github.omaraouini.quickcrud.base.dto.BaseDto;
import com.github.omaraouini.quickcrud.base.exception.ResourceNotFoundException;
import com.github.omaraouini.quickcrud.base.repository.BaseRepository;
import com.github.omaraouini.quickcrud.base.repository.specification.ExampleSpecification;
import com.github.omaraouini.quickcrud.base.utils.ValidationMsgConst;
import com.github.omaraouini.quickcrud.base.utils.Translator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Collection;

/**
 * base crud service class used to define the common methods of all services
 * that need to be used in the application <br>
 * it will provide CRUD automatically for all entities <br>
 * this class has the following methods:
 * <ul>
 *     <li>get: a method that returns an entity by its id</li>
 *     <li>get: a method that returns a list of entities</li>
 *     <li>get: a method that returns a list of entities by example</li>
 *     <li>get: a method that returns a page of entities</li>
 *     <li>get: a method that returns a page of entities by example</li>
 *     <li>post: a method that creates an entity</li>
 *     <li>put: a method that updates an entity by its id</li>
 *     <li>delete: a method that deletes an entity by its id</li>
 *     <li>example usage:
 *     <pre>
 *         {@code
 *         @Service
 *         public class PersonService extends BaseCrudService<Person, PersonDto, Integer, PersonRepository> {
 *         public PersonService(PersonRepository repository, PersonMapper mapper) {
 *         super(repository, mapper);
 *         }
 *         }
 *         }
 * </pre>
 *
 * @author aouin
 * Date: 04/03/2023
 * Time: 16:21
 */
@Slf4j
public abstract class BaseCrudService<E extends BaseEntity<I>, D extends BaseDto<I>, I extends Serializable, R extends BaseRepository<E,I>> implements CrudService<D,I> {

    protected final R repository;
    private final BaseMapper<E,D,I> mapper;

    protected BaseCrudService(R repository, BaseMapper<E, D, I> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public D get(I id) {
        return this.repository.findById(id)
                .map(this.mapper::mapFrom)
                .orElseThrow(() -> new ResourceNotFoundException(Translator.toLocale(ValidationMsgConst.ENTITY_NOT_FOUND)));
    }

    @Override
    public Collection<D> get() {
        return this.repository.findAll()
                .stream()
                .map(this.mapper::mapFrom)
                .toList();
    }

    @Override
    public Collection<D> get(D exampleDto) {
        return this.repository.findAll(new ExampleSpecification<>(Example.of(this.mapper.mapFrom(exampleDto))))
                .stream()
                .map(this.mapper::mapFrom)
                .toList();
    }

    @Override
    public Page<D> get(Pageable pageable) {
        return this.repository.findAll(pageable)
                .map(this.mapper::mapFrom);
    }

    @Override
    public Page<D> get(D exampleDto, Pageable pageable) {
        return this.repository.findAll(Example.of(this.mapper.mapFrom(exampleDto)), pageable)
                .map(this.mapper::mapFrom);
    }

    @Override
    public D post(D dto) {
        return this.mapper.mapFrom(this.repository.saveAndFlush(this.mapper.mapFrom(dto)));
    }

    @Override
    public D put(I id, D dto) {
        if (!this.repository.existsById(id))
            throw new ResourceNotFoundException(Translator.toLocale(ValidationMsgConst.ENTITY_NOT_FOUND));

        return this.mapper.mapFrom(this.repository.saveAndFlush(this.mapper.mapFrom(dto)));
    }

    @Override
    public D delete(I id) {
        E found = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Translator.toLocale(ValidationMsgConst.ENTITY_NOT_FOUND)));
        this.repository.deleteById(id);
        return this.mapper.mapFrom(found);
    }
}
