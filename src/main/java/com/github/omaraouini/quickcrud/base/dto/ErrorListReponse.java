package com.github.omaraouini.quickcrud.base.dto;

import lombok.*;

import java.util.List;

/**
 * error list response class used to define the common attributes of all error list responses
 * that need to be sent to the client <br>
 * this class has the following attributes:
 * <ul>
 *     <li>message: the error message</li>
 *     <li>content: the list of errors</li>
 *     </ul> <br>
 * @author aouin
 * Date: 04/03/2023
 * Time: 17:43
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorListReponse {
    private String message;
    List<ErrorResponse> content;
}
