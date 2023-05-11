package ru.neoflex.edu.calculator.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiErrorResponse {
    private String description;
    private String code;
    @JsonProperty(value = "exception_name")
    private String exceptionName;
    @JsonProperty(value = "exception_message")
    private String exceptionMessage;
    private List<String> stacktrace;

    public void addStacktraceItem(String stacktraceItem) {
        if (this.stacktrace == null) {
            this.stacktrace = new ArrayList<>();
        }
        this.stacktrace.add(stacktraceItem);
    }
}
