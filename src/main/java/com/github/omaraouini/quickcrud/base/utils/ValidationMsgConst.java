package com.github.omaraouini.quickcrud.base.utils;

/**
 * validation messages constants class used to define the common validation messages
 * that need to be used in the application , example usage on the validation annotations: <br>
 * <pre>
 *     {@code
 *     @Size(max = 50, message = ValidationMsgConst.FIELD_MAX_CHARS)
 *     @NotEmpty(message = ValidationMsgConst.FIELD_NOT_EMPTY)
 *     private String name;
 *     }
 * </pre>
 * @author aouin
 * Date: 04/03/2023
 * Time: 17:06
 */
public class ValidationMsgConst {
    private ValidationMsgConst() {}

    //general messages
    public static final String ENTITY_NOT_FOUND = "entity-not-found";
    public static final String VALIDATION_ERRORS = "validation-errors";
    public static final String FIELD_MAX_CHARS = "field-max-chars";
    public static final String FIELD_NOT_EMPTY = "field-notEmpty";
    public static final String FIELD_MIN_MAX_VALUE = "field-min-max-value";

}
