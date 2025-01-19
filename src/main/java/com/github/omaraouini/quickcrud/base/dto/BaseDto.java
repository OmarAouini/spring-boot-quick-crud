package com.github.omaraouini.quickcrud.base.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * base dto class used to define the common attributes of all dtos
 * that need to be used in the application <br>
 * this class has the following attributes:
 * <ul>
 *     <li>id: the unique identifier of the dto</li>
 * </ul> <br>
 * example usage:
 * <pre>
 *     {@code
 *     @SuperBuilder
 *     @Getter
 *     @Setter
 *     @NoArgsConstructor
 *     @AllArgsConstructor
 *     public class PersonDto extends BaseDto<Integer> {
 *     private String name;
 *     private String surname;
 *     private Integer age;
 *     }
 *     }
 * </pre>
 *
 * @author aouin
 * Date: 04/03/2023
 * Time: 16:14
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDto<I extends Serializable> {
    private I id;
}
