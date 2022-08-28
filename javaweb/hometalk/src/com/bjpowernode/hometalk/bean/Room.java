package com.bjpowernode.hometalk.bean;

import java.io.Serializable;
import java.util.Objects;


public class Room  implements Serializable {

    private String roomTheme;
    private String roomPW;
    private String roomChars;
    private String houseOwner;

    public Room() {
    }

    public Room(String roomTheme, String roomPW, String roomChars,String houseOwner) {
        this.roomTheme = roomTheme;
        this.roomPW = roomPW;
        this.roomChars = roomChars;
        this.houseOwner = houseOwner;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomTheme='" + roomTheme + '\'' +
                ", roomPW='" + roomPW + '\'' +
                ", roomChars='" + roomChars + '\'' +
                ", houseOwner='" + houseOwner + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return Objects.equals(getRoomTheme(), room.getRoomTheme()) && Objects.equals(getRoomPW(), room.getRoomPW()) && Objects.equals(getRoomChars(), room.getRoomChars()) && Objects.equals(getHouseOwner(), room.getHouseOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomTheme(), getRoomPW(), getRoomChars(), getHouseOwner());
    }

    public String getHouseOwner() {
        return houseOwner;
    }

    public void setHouseOwner(String houseOwner) {
        this.houseOwner = houseOwner;
    }

    public String getRoomTheme() {
        return roomTheme;
    }

    public void setRoomTheme(String roomTheme) {
        this.roomTheme = roomTheme;
    }

    public String getRoomPW() {
        return roomPW;
    }

    public void setRoomPW(String roomPW) {
        this.roomPW = roomPW;
    }

    public String getRoomChars() {
        return roomChars;
    }

    public void setRoomChars(String roomChars) {
        this.roomChars = roomChars;
    }
}