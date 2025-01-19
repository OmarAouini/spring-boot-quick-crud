package com.github.omaraouini.quickcrud.base.validation.groups;

/**
 * post validation group interface used to define the common post validation group
 * that need to be used in the application <br>
 * this group is used to validate the post of the application, so it will trigger in ONLY the POST operations <br>
 * example usage on the validation annotations: <br>
 * <pre>
 *     {@code
 *     @Size(max = 50, message = ValidationMsgConst.FIELD_MAX_CHARS, groups = PostValidationGroup.class)
 *     @NotEmpty(message = ValidationMsgConst.FIELD_NOT_EMPTY, groups = PostValidationGroup.class)
 *     private String name;
 *     }
 * </pre>
 * you can still use the default group by not specifying the group attribute
 * or use multiple groups by specifying multiple groups in the groups attribute depending on your needs, eg: <br>
 * <pre>
 *     {@code
 *     @Size(max = 50, message = ValidationMsgConst.FIELD_MAX_CHARS, groups = {PostValidationGroup.class, AnotherGroup.class})
 *     @NotEmpty(message = ValidationMsgConst.FIELD_NOT_EMPTY, groups = {PostValidationGroup.class, AnotherGroup.class})
 *     private String name;
 *     }
 *     </pre>
 * @author aouin
 * Date: 04/03/2023
 * Time: 18:02
 */
public interface PostValidationGroup {
}
