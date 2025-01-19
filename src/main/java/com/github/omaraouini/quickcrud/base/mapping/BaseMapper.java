package com.github.omaraouini.quickcrud.base.mapping;

import com.github.omaraouini.quickcrud.base.dto.BaseDto;
import com.github.omaraouini.quickcrud.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * base mapper interface used to define the common methods of all mappers
 * that need to be used in the application <br>
 * this interface has the following methods:
 * <ul>
 *     <li>mapFrom: a method that maps an entity to a dto</li>
 *     <li>mapFrom: a method that maps a dto to an entity</li>
 *     <li>mapFromEntities: a method that maps a list of entities to a list of dtos</li>
 *     <li>mapFromDtos: a method that maps a list of dtos to a list of entities</li>
 * example usage (this example use a builder mapping, but you are free to use any mapping method you want, eg: mapstruct):
 *     <pre>
 *         {@code
 *         public class PersonMapper implements BaseMapper<Person, PersonDto, Integer> {
 *         @Override
 *         public PersonDto mapFrom(Person entity) {
 *         return PersonDto.builder()
 *         .id(entity.getId())
 *         .name(entity.getName())
 *         .surname(entity.getSurname())
 *         .age(entity.getAge())
 *         .build();
 *         }
 *         @Override
 *         public Person mapFrom(PersonDto dto) {
 *         return Person.builder()
 *         .id(dto.getId())
 *         .name(dto.getName())
 *         .surname(dto.getSurname())
 *         .age(dto.getAge())
 *         .build();
 *         }
 *         }
 *         }
 * </pre>
 *
 * @author aouin
 * Date: 04/03/2023
 * Time: 16:28
 */
public interface BaseMapper<E extends BaseEntity<I>,D extends BaseDto<I>,I extends Serializable> {

    D mapFrom(E entity);
    E mapFrom(D dto);

    default List<D> mapFromEntities(List<E> entities) {
        return entities.stream().map(this::mapFrom).toList();
    }

    default List<E> mapFromDtos(List<D> dtos) {
        return dtos.stream().map(this::mapFrom).toList();
    }
}
