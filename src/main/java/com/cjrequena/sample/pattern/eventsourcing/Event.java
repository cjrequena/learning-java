package com.cjrequena.sample.pattern.eventsourcing;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@SuperBuilder
@ToString
@Log4j2
public abstract class Event {

  // Unique id for the specific message. This id is globally unique
  protected UUID eventId;

  // The event offset_txid
  protected long offsetId;

  // The event offset_txid
  protected long offsetTxId;

  // Unique aggregateId for the specific message. This id is globally unique
  protected final UUID aggregateId;

  // The event aggregateVersion.
  protected final long aggregateVersion;

  // Type of message
  protected String eventType;

  // The content type of the event data. Must adhere to RFC 2046 format.
  public String dataContentType;

  // Base64 encoded event payload. Must adhere to RFC4648.
  protected String dataBase64;

  // A URI describing the schema for the event data
  //protected String dataSchema;

  // The time the event occurred
  protected OffsetDateTime time;

//  public AbstractEventEntity mapToEventEntity() {
//    log.info("Mapping to event entity {}", this);
//    return invoke();
//  }
//
//  @SneakyThrows
//  private AbstractEventEntity invoke() {
//    Class<?> parameterType = this.getClass();
//    Method method = this.getClass().getMethod("mapToEventEntity");
//    return (AbstractEventEntity) method.invoke(this);
//  }

}
