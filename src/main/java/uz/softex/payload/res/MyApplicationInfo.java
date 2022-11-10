package uz.softex.payload.res;

import lombok.Getter;
import lombok.Setter;
import uz.softex.enums.Status;

@Getter
@Setter
public class MyApplicationInfo {

    private Long applicationId;

    private String categoryName;

    private Status statusName;
}