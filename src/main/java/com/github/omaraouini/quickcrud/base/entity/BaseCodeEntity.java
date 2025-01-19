package com.github.omaraouini.quickcrud.base.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * abstract entity class
 * used for value entities that have a code and a description <br>
 * this class has the following attributes:
 * <ul>
 *     <li>code: the unique code of the entity</li>
 *     <li>description: the description of the entity</li>
 *     <li>id: the unique identifier of the entity (from {@link BaseEntity})</li>
 *     <li>deleted: a flag to indicate if the entity is deleted or not (from {@link BaseEntity})</li>
 * example usage:
 * <pre>
 * {@code
 * @Entity
 * @Table(name = "value_codes")
 * @SQLDelete(sql = "UPDATE value_codes SET deleted = true WHERE id=?")
 * @Where(clause = "deleted=false")
 * @SuperBuilder
 * @Getter
 * @Setter
 * @NoArgsConstructor
 * @AllArgsConstructor
 * public class ValueCodes extends BaseCodeEntity<Integer> {
 *
 * }
 * }
 * * </pre>
 * @author aouin
 * Date: 18/01/2025
 * Time: 12:30
 */
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseCodeEntity extends BaseEntity<Integer> {
    private String code;
    private String description;
}
