package org.example;

import java.util.Objects;

public abstract class Media implements MediaInfo {
    private int borrowTime;
    private double feeCharge;
    private boolean isBorrowed;
    private long borrower;
    private String topic;
    private int callNumber;
    public static int callCount;

    public Media(int borrowTime, double feeCharge, String topic) {
        this.borrowTime = borrowTime;
        this.feeCharge = feeCharge;
        this.topic = topic;
        this.isBorrowed = false;
        this.borrower = 0;
        this.callNumber = callCount++;
    }

    @Override
    public String displayInfo() {
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Media media = (Media) o;
        return borrowTime == media.borrowTime && Double.compare(feeCharge, media.feeCharge) == 0 && isBorrowed == media.isBorrowed && borrower == media.borrower && callNumber == media.callNumber && Objects.equals(topic, media.topic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowTime, feeCharge, isBorrowed, borrower, topic, callNumber);
    }

    @Override
    public String toString() {
        return "Media{" +
                "borrowTime=" + borrowTime +
                ", feeCharge=" + feeCharge +
                ", isBorrowed=" + isBorrowed +
                ", borrower=" + borrower +
                ", topic='" + topic + '\'' +
                ", callNumber=" + callNumber +
                '}';
    }

    public int getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(int callNumber) {
        this.callNumber = callNumber;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public long getBorrower() {
        return borrower;
    }

    public void setBorrower(long borrower) {
        this.borrower = borrower;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public double getFeeCharge() {
        return feeCharge;
    }

    public void setFeeCharge(double feeCharge) {
        this.feeCharge = feeCharge;
    }

    public int getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(int borrowTime) {
        this.borrowTime = borrowTime;
    }
}
