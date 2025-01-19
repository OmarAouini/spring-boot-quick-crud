package com.github.omaraouini.quickcrud.base.validation.groups;


/**
 * put validation group interface used to define the common put validation group
 * that need to be used in the application <br>
 * this group is used to validate the put of the application, so it will trigger in ONLY the PUT operations <br>
 * example usage on the validation annotations: <br>
 * <pre>
 *     {@code
 *     @Size(max = 50, message = ValidationMsgConst.FIELD_MAX_CHARS, groups = PutValidationGroup.class)
 *     @NotEmpty(message = ValidationMsgConst.FIELD_NOT_EMPTY, groups = PutValidationGroup.class)
 *     private String name;
 *     }
 * </pre>
 * you can still use the default group by not specifying the group attribute
 * or use multiple groups by specifying multiple groups in the groups attribute depending on your needs, eg: <br>
 * <pre>
 *     {@code
 *     @Size(max = 50, message = ValidationMsgConst.FIELD_MAX_CHARS, groups = {PutValidationGroup.class, AnotherGroup.class})
 *     @NotEmpty(message = ValidationMsgConst.FIELD_NOT_EMPTY, groups = {PutValidationGroup.class, AnotherGroup.class})
 *     private String name;
 *     }
 *     </pre>
 * @author aouin
 * Date: 04/03/2023
 * Time: 18:02
 */
public interface PutValidationGroup {
}
