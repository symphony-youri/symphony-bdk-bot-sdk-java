package com.symphony.ms.bot.sdk.internal.event.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.events.UserLeftRoom;

/**
 * Symphony User left room event
 *
 * @author Marcus Secato
 *
 */
@Data
@NoArgsConstructor
public class UserLeftRoomEvent extends BaseEvent {

  private String userDisplayName;
  private String roomName;

  public UserLeftRoomEvent(UserLeftRoom event) {
    this.streamId = event.getStream().getStreamId();
    this.userId = event.getAffectedUser().getUserId().toString();
    this.userDisplayName = event.getAffectedUser().getDisplayName();
    this.roomName = event.getStream().getRoomName();
  }

}