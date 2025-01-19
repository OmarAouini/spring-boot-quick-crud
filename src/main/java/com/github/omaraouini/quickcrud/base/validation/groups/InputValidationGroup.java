package com.github.omaraouini.quickcrud.base.validation.groups;

/**
 * input validation group interface used to define the common input validation group
 * that need to be used in the application <br>
 * this group is used to validate the input of the application, so it will trigger in ALL the input operations <br>
 * example usage on the validation annotations: <br>
 * <pre>
 *     {@code
 *     @Size(max = 50, message = ValidationMsgConst.FIELD_MAX_CHARS, groups = InputValidationGroup.class)
 *     @NotEmpty(message = ValidationMsgConst.FIELD_NOT_EMPTY, groups = InputValidationGroup.class)
 *     private String name;
 *     }
 * </pre>
 * you can still use the default group by not specifying the group attribute
 * or use multiple groups by specifying multiple groups in the groups attribute depending on your needs, eg: <br>
 * <pre>
 *     {@code
 *     @Size(max = 50, message = ValidationMsgConst.FIELD_MAX_CHARS, groups = {InputValidationGroup.class, AnotherGroup.class})
 *     @NotEmpty(message = ValidationMsgConst.FIELD_NOT_EMPTY, groups = {InputValidationGroup.class, AnotherGroup.class})
 *     private String name;
 *     }
 *     </pre>
 * @author aouin
 * Date: 04/03/2023
 * Time: 18:02
 */
public interface InputValidationGroup {
}
