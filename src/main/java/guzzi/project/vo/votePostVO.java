package guzzi.project.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

//짜긴 했는데 일단 사용 안할듯 setter 사용 불가능 하기 때문에
@Data
public class votePostVO {
    @JsonProperty
    private Number USER_ID;
    @JsonProperty
    private Number VOTE_ID;
    @JsonProperty
    private Timestamp CREATE_AT;
    @JsonProperty
    private String CONTENT;
    @JsonProperty
    private String FIRST_ANSWER;
    @JsonProperty
    private String SECOND_ANSWER;
}
