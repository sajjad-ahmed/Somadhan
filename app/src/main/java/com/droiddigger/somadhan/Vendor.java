package com.droiddigger.somadhan;

/**
 * Created by Sajjad Ahmed on 5/14/2017.
 */

public class Vendor {
    public String name;
    public String id;
    public String address;
    public String image;
    public String category;
    public String contactno;
    public String lat;
    public String lon;
    public String tag;
    public String ratedby;
    public String rating;
    public String verification;

    public Vendor(String name, String id, String address, String image, String category, String contactno, String lat, String lon, String tag, String ratedby, String rating, String verification) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.image = image;
        this.category = category;
        this.contactno = contactno;
        this.lat = lat;
        this.lon = lon;
        this.tag = tag;
        this.ratedby = ratedby;
        this.rating = rating;
        this.verification = verification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getRatedby() {
        return ratedby;
    }

    public void setRatedby(String ratedby) {
        this.ratedby = ratedby;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

}
