package com.ring.basejavamvp.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class CloseList {

    private ArrayList<ClosesBean> closes;
    private Integer closeMax;//亲密关系上限

    public ArrayList<ClosesBean> getCloses() {
        return closes;
    }

    public void setCloses(ArrayList<ClosesBean> closes) {
        this.closes = closes;
    }

    public Integer getCloseMax() {
        return closeMax;
    }

    public void setCloseMax(Integer closeMax) {
        this.closeMax = closeMax;
    }

    //亲密关系列表
    public static class ClosesBean implements Serializable {
        private String closeDid;
        private String nickName;
        private String typeName;
        private Integer type;
        private long updateDt;
        private long createDt;
        private String head;
        private String score;
        private String headFrame;
        private long expireDate;
        private String typeLong;
        private long id;
        private Integer state;
        private String typeSquare;
        private String did;
        private Integer days;

        public String getCloseDid() {
            return closeDid;
        }

        public void setCloseDid(String closeDid) {
            this.closeDid = closeDid;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public long getUpdateDt() {
            return updateDt;
        }

        public void setUpdateDt(long updateDt) {
            this.updateDt = updateDt;
        }

        public long getCreateDt() {
            return createDt;
        }

        public void setCreateDt(long createDt) {
            this.createDt = createDt;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getHeadFrame() {
            return headFrame;
        }

        public void setHeadFrame(String headFrame) {
            this.headFrame = headFrame;
        }

        public long getExpireDate() {
            return expireDate;
        }

        public void setExpireDate(long expireDate) {
            this.expireDate = expireDate;
        }

        public String getTypeLong() {
            return typeLong;
        }

        public void setTypeLong(String typeLong) {
            this.typeLong = typeLong;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Integer getState() {
            return state;
        }

        public void setState(Integer state) {
            this.state = state;
        }

        public String getTypeSquare() {
            return typeSquare;
        }

        public void setTypeSquare(String typeSquare) {
            this.typeSquare = typeSquare;
        }

        public String getDid() {
            return did;
        }

        public void setDid(String did) {
            this.did = did;
        }

        public Integer getDays() {
            return days;
        }

        public void setDays(Integer days) {
            this.days = days;
        }
    }
}
