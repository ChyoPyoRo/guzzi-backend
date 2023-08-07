package guzzi.project.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ErrorResponse{
    private ErrorCode status;
    private String statusMessage;
}