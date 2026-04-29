package insurance.insuranceCommon.Event;

import java.time.LocalDateTime;
import java.util.UUID;

public interface BaseEvent {
    UUID getEventId();
    LocalDateTime getCreatedAt();
}