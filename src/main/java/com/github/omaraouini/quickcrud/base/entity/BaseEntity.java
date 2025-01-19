package com.github.omaraouini.quickcrud.base.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * base entity class used to define the common attributes of all entities
 * that need to be saved in the database <br>
 * you either extend this class or {@link BaseEntityAuditable} depending on your needs <br>
 * this class has the following attributes:
 * <ul>
 *     <li>id: the unique identifier of the entity</li>
 *     <li>deleted: a flag to indicate if the entity is deleted or not</li>
 * </ul> <br>
 * example usage:
 * <pre>
 * {@code
 * @Entity
 * @Table(name = "people")
 * @SQLDelete(sql = "UPDATE people SET deleted = true WHERE id=?")
 * @Where(clause = "deleted=false")
 * @SuperBuilder
 * @Getter
 * @Setter
 * @NoArgsConstructor
 * @AllArgsConstructor
 * public class Person extends BaseEntity<Integer> {
 *     private String name;
 *     private String surname;
 *     private Integer age;
 * }
 * }
 * </pre>
 * @author aouin
 * Date: 04/03/2023
 * Time: 16:23
 */
@MappedSuperclass
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity<I extends Serializable> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private I id;
    private boolean deleted = Boolean.FALSE;
}
