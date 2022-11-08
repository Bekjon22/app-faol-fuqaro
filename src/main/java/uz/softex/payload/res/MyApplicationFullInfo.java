package uz.softex.payload.res;

import lombok.Getter;
import lombok.Setter;
import uz.softex.enums.Status;

import java.sql.Timestamp;

/**
 * @author Bekjon Bakhromov
 * @since 03.11.2022
 */
@Getter
@Setter
public class MyApplicationFullInfo {

    private String text;

    private String categoryName;

    private String destination;

    private Timestamp submittedDate;

    private Status status;

    private String performerName;

    private String position;

    private Timestamp resultDate;



}
