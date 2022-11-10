package uz.softex.payload;

import uz.softex.enums.Status;

/**
 * A Projection for the {@link uz.softex.entity.Application} entity
 */
public interface MyApplicationInfoProjection {
    Long getApplication_id();
    String getCategory_name();

    Status getStatus_name();
}