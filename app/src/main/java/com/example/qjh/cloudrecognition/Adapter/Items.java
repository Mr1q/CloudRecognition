package com.example.qjh.cloudrecognition.Adapter;

public class Items  {
    public  String Msg; //具体地理位置
    public  int ImageId; //图片
    double lon ;//经度
    double lat ;//纬度

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Items(String Msg, int Imageid,double lon,double lat)
    {
        this.lat=lat;
        this.lon=lon;
        this.Msg=Msg;
        this.ImageId=Imageid;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }
}
