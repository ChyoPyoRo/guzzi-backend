package guzzi.project.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserVO {
    @JsonProperty
    private Number userid;
    @JsonProperty
    private String id;
    @JsonProperty
    private String password;
    @JsonProperty
    private Date created_At ;

}