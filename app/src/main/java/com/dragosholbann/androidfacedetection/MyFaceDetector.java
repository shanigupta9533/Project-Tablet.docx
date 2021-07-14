package com.dragosholbann.androidfacedetection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.SparseArray;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;

import java.io.ByteArrayOutputStream;

public class MyFaceDetector extends Detector<Face> {
    private Detector<Face> mDelegate;
    private static Bitmap TempBitmap;
    MyFaceDetector(Detector<Face> delegate) {
        mDelegate = delegate;
    }

    public SparseArray<Face> detect(Frame frame) {
        YuvImage yuvImage = new YuvImage(frame.getGrayscaleImageData().array(), ImageFormat.NV21, frame.getMetadata().getWidth(), frame.getMetadata().getHeight(), null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, frame.getMetadata().getWidth(), frame.getMetadata().getHeight()), 100, byteArrayOutputStream);
        byte[] jpegArray = byteArrayOutputStream.toByteArray();
        TempBitmap = BitmapFactory.decodeByteArray(jpegArray, 0, jpegArray.length);

        //TempBitmap is a Bitmap version of a frame which is currently captured by your CameraSource in real-time
        //So you can process this TempBitmap in your own purposes adding extra code here

        return mDelegate.detect(frame);
    }

    public boolean isOperational() {
        return mDelegate.isOperational();
    }

    public boolean setFocus(int id) {
        return mDelegate.setFocus(id);
    }
     Bitmap getbit() {
        return TempBitmap;
    }
}