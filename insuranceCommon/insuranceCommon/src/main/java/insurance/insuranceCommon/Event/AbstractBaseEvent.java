package insurance.insuranceCommon.Event;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class AbstractBaseEvent implements BaseEvent , Serializable {
    private final UUID eventId = UUID.randomUUID();
    private final LocalDateTime createdAt = LocalDateTime.now();

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
