package com.github.omaraouini.quickcrud.base.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * base dto class used to define the common attributes of all dtos
 * that need to be used in the application <br>
 * you either extend this class or {@link BaseDto} depending on your needs <br>
 * this class is auditable and has the following attributes:
 * <ul>
 *     <li>id: the unique identifier of the dto (from {@link BaseDto})</li>
 *     <li>createdAt: the date when the dto was created</li>
 *     <li>updatedAt: the date when the dto was last updated</li>
 *     <li>createdBy: the user who created the dto</li>
 *     <li>updatedBy: the user who last updated the dto</li>
 * </ul> <br>
 * example usage:
 * <pre>
 *     {@code
 *     @SuperBuilder
 *     @Getter
 *     @Setter
 *     @NoArgsConstructor
 *     @AllArgsConstructor
 *     public class PersonDto extends BaseDtoAuditable<Integer> {
 *     private String name;
 *     private String surname;
 *     private Integer age;
 *     }
 *     }
 * </pre>
 *
 * @author aouin
 * Date: 17/03/2023
 * Time: 17:10
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDtoAuditable<I extends Serializable> extends BaseDto<I>{
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String createdBy;
    private String updatedBy;
}
