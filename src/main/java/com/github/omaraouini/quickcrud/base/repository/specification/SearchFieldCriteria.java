package com.github.omaraouini.quickcrud.base.repository.specification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * this class is used to define the search criteria that will be used in the search specification
 * it has the following attributes:
 * <ul>
 *     <li>key: the field name that will be used in the search</li>
 *     <li>value: the value that will be used in the search</li>
 *     <li>searchOperation: the operation that will be used in the search</li>
 * </ul>
 * example usage:
 * <pre>
 *     {@code
 *     SearchFieldCriteria searchFieldCriteria = new SearchFieldCriteria("name", "john", SearchOperation.EQUAL);
 *     }
 * </pre>
 *
 * @author aouin
 * Date: 17/03/2023
 * Time: 17:20
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchFieldCriteria {
    private String key;
    private String value;
    private SearchOperation searchOperation;
}
