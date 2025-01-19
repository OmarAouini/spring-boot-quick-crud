package com.github.omaraouini.quickcrud.base.repository.specification;

/**
 * these values are used to define the search operation that will be used in the search criteria
 * @author aouin
 * Date: 17/03/2023
 * Time: 17:21
 */
public enum SearchOperation {
    GREATER_THAN,
    LESS_THAN,
    GREATER_THAN_EQUAL,
    LESS_THAN_EQUAL,
    NOT_EQUAL,
    EQUAL,
    MATCH, LIKE,
    MATCH_START,
    MATCH_END,
    IN,
    NOT_IN
}
