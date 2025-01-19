package com.github.omaraouini.quickcrud.base.service;

import com.github.omaraouini.quickcrud.base.dto.BaseDto;
import com.github.omaraouini.quickcrud.base.validation.groups.InputValidationGroup;
import com.github.omaraouini.quickcrud.base.validation.groups.PostValidationGroup;
import com.github.omaraouini.quickcrud.base.validation.groups.PutValidationGroup;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.Collection;

/**
 * base crud service interface used to define the common methods of all services
 * that need to be used in the application <br>
 * it will provide CRUD automatically for all entities <br>
 * the post and the put method will validate the input dto using the following groups:
 * <ul>
 *     <li>Default: the default group</li>
 *     <li>InputValidationGroup: the group that contains the input validation constraints</li>
 *     <li>PostValidationGroup: the group that contains the post validation constraints</li>
 *     <li>PutValidationGroup: the group that contains the put validation constraints</li>
 * </ul>
 * you can leverage these groups to define your own validation constraints on the dto class with the help of the
 * jakarta.validation.constraints package <br> <br>
 * this interface has the following methods:
 * <ul>
 *     <li>get: a method that returns an entity by its id</li>
 *     <li>get: a method that returns a list of entities</li>
 *     <li>get: a method that returns a list of entities by example</li>
 *     <li>get: a method that returns a page of entities</li>
 *     <li>get: a method that returns a page of entities by example</li>
 *     <li>post: a method that creates an entity</li>
 *     <li>put: a method that updates an entity by its id</li>
 *     <li>delete: a method that deletes an entity by its id</li>
 * @author aouin
 * Date: 04/03/2023
 * Time: 16:09
 */
@Validated
public interface CrudService<D extends BaseDto<I>, I extends Serializable> {
    D get(I id);
    Collection<D> get();
    Collection<D> get(D exampleDto);
    Page<D> get(Pageable pageable);
    Page<D> get(D exampleDto, Pageable pageable);
    @Transactional
    D post(@Validated({Default.class, InputValidationGroup.class, PostValidationGroup.class}) @Valid D dto);
    @Transactional
    D put(@Validated({Default.class, InputValidationGroup.class, PutValidationGroup.class}) I id, @Valid D dto);
    @Transactional
    D delete(I id);
}
