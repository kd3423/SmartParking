package com.example.smartparking;

import android.graphics.Bitmap;

import net.glxn.qrgen.android.QRCode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Qrcode {
    String userId;
    String pId;
    String entryTime;
    Bitmap qrcode;

    public Qrcode(String userId, String entryTime, String pId){
        this.userId = userId;
        this.entryTime = entryTime;
        this.pId = pId;
        generateQrCode();
    }

    private void generateQrCode(){
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//
//        String dateStr = dateFormat.format(this.entryTime);
        this.qrcode = QRCode.from(this.userId+","+entryTime+","+this.pId).bitmap();
    }

    public Bitmap getQrCode(){
        return this.qrcode;
    }
}
