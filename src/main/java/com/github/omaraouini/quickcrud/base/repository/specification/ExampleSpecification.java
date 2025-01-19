package com.github.omaraouini.quickcrud.base.repository.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

/** specification to be used when need to search also by example along with other jpa specification criteria
 * it has the following attributes:
 * <ul>
 *    <li>exampleProbe: the example probe that will be used in the search</li>
 * </ul>
 * example usage:
 * <pre>
 *     {@code
 *     Example<Person> example = Example.of(new Person("john", "doe"), ExampleMatcher.matching().withIgnoreCase());
 *     ExampleSpecification<Person> exampleSpecification = new ExampleSpecification<>(example);
 *     }
 * </pre>
 * @author aouin
 * Date: 17/03/2023
 * Time: 17:42
 */
public class ExampleSpecification<E> implements Specification<E> {

    private final Example<E> exampleProbe;

    public ExampleSpecification(Example<E> exampleProbe) {
        this.exampleProbe = exampleProbe;
    }

    @Override
    public Predicate toPredicate(@NonNull Root<E> root,@NonNull CriteriaQuery<?> query,@NonNull CriteriaBuilder criteriaBuilder) {

        final List<Predicate> predicates = new ArrayList<>();

        final ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues()
                .withIgnoreCase();
        Example<E> fixedExample =Example.of(this.exampleProbe.getProbe(), exampleMatcher);

        predicates.add(QueryByExamplePredicateBuilder.getPredicate(root,criteriaBuilder,fixedExample));

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
