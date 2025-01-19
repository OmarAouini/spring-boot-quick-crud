package com.github.omaraouini.quickcrud.base.entity;

import com.github.omaraouini.quickcrud.base.security.SecurityUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * base entity class used to define the common attributes of all entities
 * that need to be saved in the database <br>
 * you either extend this class or {@link BaseEntity} depending on your needs <br>
 * this class is auditable and has the following attributes:
 * <ul>
 *     <li>createdAt: the date when the entity was created</li>
 *     <li>updatedAt: the date when the entity was last updated</li>
 *     <li>createdBy: the user who created the entity</li>
 *     <li>updatedBy: the user who last updated the entity</li>
 *     <li>id: the unique identifier of the entity (from {@link BaseEntity})</li>
 *     <li>deleted: a flag to indicate if the entity is deleted or not (from {@link BaseEntity})</li>
 *     <li>prePersist: a method that sets the createdAt and createdBy attributes before persisting the entity</li>
 *     <li>preUpdate: a method that sets the updatedAt and updatedBy attributes before updating the entity</li> <br>
 * example usage using a person entity:
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
 * public class Person extends BaseEntityAuditable<Integer> {
 *     private String name;
 *     private String surname;
 *     private Integer age;
 * }
 * }
 * </pre>
 * @author aouin
 * Date: 17/03/2023
 * Time: 16:09
 */
@Audited
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntityAuditable<I extends Serializable> extends BaseEntity<I> {
    @CreatedDate
    private LocalDate createdAt;
    @LastModifiedDate
    private LocalDate updatedAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String updatedBy;

    @PrePersist
    private void prePersist() {
        this.setCreatedAt(LocalDate.now());
        this.setCreatedBy(SecurityUtils.getPrincipalName());
    }

    @PreUpdate
    private void preUpdate() {
        this.setUpdatedAt(LocalDate.now());
        this.setUpdatedBy(SecurityUtils.getPrincipalName());

    }
}
