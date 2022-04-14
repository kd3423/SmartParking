package com.example.smartparking;

import android.graphics.Bitmap;

import net.glxn.qrgen.android.QRCode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Qrcode {
    String userId;
    String pId;
    Date entryTime;
    Bitmap qrcode;

    public Qrcode(String userId, Date entryTime, String pId){
        this.userId = userId;
        this.entryTime = entryTime;
        this.pId = pId;
        generateQrCode();
    }

    private void generateQrCode(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        
        String dateStr = dateFormat.format(this.entryTime);
        this.qrcode = QRCode.from(this.userId+","+dateStr+","+this.pId).bitmap();
    }

    public Bitmap getQrCode(){
        return this.qrcode;
    }
}
