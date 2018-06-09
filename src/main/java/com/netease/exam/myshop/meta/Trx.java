package com.netease.exam.myshop.meta;

import java.time.Instant;

public class Trx
{
    private Integer trxId;

    private Integer personId;

    private Integer contentId;

    private Double price;

    private Instant time;

    public Trx() {
    }

    public Trx(Integer trxId, Integer personId, Integer contentId, Double price, Instant time) {
        this.trxId = trxId;
        this.personId = personId;
        this.contentId = contentId;
        this.price = price;
        this.time = time;
    }

    public Integer getTrxId() {
        return trxId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public Integer getContentId() {
        return contentId;
    }

    public Double getPrice() {
        return price;
    }

    public Instant getTime() {
        return time;
    }

    public void setTrxId(Integer trxId) {
        this.trxId = trxId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Trx{" +
                "trxId=" + trxId +
                ", personId=" + personId +
                ", contentId=" + contentId +
                ", price=" + price +
                ", time=" + time +
                '}';
    }
}
