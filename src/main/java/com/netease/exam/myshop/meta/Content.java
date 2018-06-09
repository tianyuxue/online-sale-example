package com.netease.exam.myshop.meta;


public class Content
{
    private Integer contentId;

    private Double price;

    private String title;

    private String icon;

    private String contentAbstract;

    private String text;

    public Content(){ }

    public Content(Integer contentId, Double price, String title, String icon, String contentAbstract, String text) {
        this.contentId = contentId;
        this.price = price;
        this.title = title;
        this.icon = icon;
        this.contentAbstract = contentAbstract;
        this.text = text;
    }

    public Integer getContentId() {
        return contentId;
    }

    public Double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getIcon() {
        return icon;
    }

    public String getContentAbstract() {
        return contentAbstract;
    }

    public String getText() {
        return text;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setContentAbstract(String contentAbstract) {
        this.contentAbstract = contentAbstract;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Content{" +
                "contentId=" + contentId +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", contentAbstract='" + contentAbstract + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
