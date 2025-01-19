package com.github.omaraouini.quickcrud.base.repository.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * this class is used to define the search specification that will be used in the search query,
 * useful when you want to search for entities using multiple criteria
 * it has the following attributes:
 * <ul>
 *     <li>criteriaList: a list of search criteria that will be used in the search</li>
 *     <li>add: a method that adds a search criteria to the list</li>
 *     <li>toPredicate: a method that converts the search criteria to a predicate</li>
 * </ul>
 *
 *  example usage with a repository with a default method:
 *  <pre>
 *      {@code
 *      public interface PersonRepository extends JpaRepository<Person, Integer> {
 *      default List<Person> findNameEqualTAndAgeGreaterThan(String name, Integer age) {
 *      GenericSearchSpecification<Person> specification = new GenericSearchSpecification<>();
 *      specification.add(new SearchFieldCriteria("name", name, SearchOperation.EQUAL));
 *      specification.add(new SearchFieldCriteria("age", age, SearchOperation.GREATER_THAN));
 *      return this.findAll(specification);
 *      }
 *      }
 *      }
 * </pre>
 *
 * @author aouin
 * Date: 17/03/2023
 * Time: 17:24
 */
public class GenericSearchSpecification<E> implements Specification<E> {
    private final List<SearchFieldCriteria> criteriaList;

    public GenericSearchSpecification() {
        this.criteriaList = new ArrayList<>();
    }

    public void add(SearchFieldCriteria searchFieldCriteria) {
        this.criteriaList.add(searchFieldCriteria);
    }

    @Override
    public Predicate toPredicate(@NonNull Root<E> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        for (SearchFieldCriteria criteria : this.criteriaList) {
            switch (criteria.getSearchOperation()) {
                case GREATER_THAN -> predicates.add(criteriaBuilder.greaterThan(root.get(criteria.getKey()), criteria.getValue()));
                case LESS_THAN -> predicates.add(criteriaBuilder.lessThan(root.get(criteria.getKey()), criteria.getValue()));
                case GREATER_THAN_EQUAL -> predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue()));
                case LESS_THAN_EQUAL -> predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue()));
                case NOT_EQUAL -> predicates.add(criteriaBuilder.notEqual(root.get(criteria.getKey()), criteria.getValue()));
                case EQUAL -> predicates.add(criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue()));
                case MATCH, LIKE -> predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toLowerCase() + "%"));
                case MATCH_END -> predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(criteria.getKey())), criteria.getValue().toLowerCase() + "%"));
                case MATCH_START -> predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toLowerCase()));
                case IN -> predicates.add(criteriaBuilder.in(root.get(criteria.getKey())).value(criteria.getValue()));
                case NOT_IN -> predicates.add(criteriaBuilder.not(root.get(criteria.getKey()).in(criteria.getValue())));
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

