package com.valeriygulin.mailsenderjavafx2.model;


import java.util.List;
import java.util.Objects;

public class Mail {
    private List<String> listAddresses;
    private String address;
    private String text;

    public Mail(String address, String text) {
        this.address = address;
        this.text = text;
    }

    public Mail(List<String> listAddresses, String text) {
        this.listAddresses = listAddresses;
        this.text = text;
    }

    public List<String> getListAddresses() {
        return listAddresses;
    }

    public void setListAddresses(List<String> listAddresses) {
        this.listAddresses = listAddresses;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mail mail = (Mail) o;
        return Objects.equals(listAddresses, mail.listAddresses) && Objects.equals(text, mail.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listAddresses, text);
    }

    @Override
    public String
    toString() {
        return "Mail{" +
                "listAddresses=" + listAddresses +
                ", address='" + address + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
