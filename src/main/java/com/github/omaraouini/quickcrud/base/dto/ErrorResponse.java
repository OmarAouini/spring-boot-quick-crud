package com.github.omaraouini.quickcrud.base.dto;

import lombok.*;

/**
 * error response class used to define the common attributes of all error responses
 * that need to be sent to the client <br>
 * this class has the following attributes:
 * <ul>
 *     <li>message: the error message</li>
 *     <li>path: the path where the error occurred</li>
 *     </ul> <br>
 * @author aouin
 * Date: 04/03/2023
 * Time: 17:37
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private String message;
    private String path;
}
