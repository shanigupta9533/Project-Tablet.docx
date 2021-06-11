package com.dragosholbann.androidfacedetection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.images.Size;
import com.google.android.gms.vision.CameraSource;

import java.io.IOException;

public class CameraPreview extends ViewGroup {
  private static final String TAG = "CameraPreview";

  private Context mContext;
  private SurfaceView mSurfaceView;
  private boolean mStartRequested;
  private boolean mSurfaceAvailable;
  private CameraSource mCameraSource;
  SurfaceHolder holder;
  private GraphicOverlay mOverlay;
  Camera mCamera;
  public CameraPreview(Context context, AttributeSet attrs) {
    super(context, attrs);
    mContext = context;
    mStartRequested = false;
    mSurfaceAvailable = false;

    mSurfaceView = new SurfaceView(context);
    mSurfaceView.getHolder().addCallback(new SurfaceCallback());



    addView(mSurfaceView);
  }

  public void start(CameraSource cameraSource) throws IOException {
    if (cameraSource == null) {
      stop();
    }

    mCameraSource = cameraSource;


    if (mCameraSource != null) {
      mStartRequested = true;
      startIfReady();
    }
    else
      Toast.makeText(mContext, "58 - mCameraSource NULL", Toast.LENGTH_SHORT).show();
  }

  public void start(CameraSource cameraSource, GraphicOverlay overlay) throws IOException {
    mOverlay = overlay;
    start(cameraSource);
  }

  public void stop() {
    if (mCameraSource != null) {
      mCameraSource.stop();
    }
  }

  public void release() {
    if (mCameraSource != null) {
      mCameraSource.release();
      mCameraSource = null;
    }
  }

  @SuppressLint("MissingPermission")
  private void startIfReady() throws IOException {
    if (mStartRequested && mSurfaceAvailable) {
      mCameraSource.start(mSurfaceView.getHolder());
      if (mOverlay != null) {

        Size size = mCameraSource.getPreviewSize();
        int min = Math.min(size.getWidth(), size.getHeight());
        int max = Math.max(size.getWidth(), size.getHeight());

                /*mCamera = Camera.open();
                Camera.Parameters params = mCamera.getParameters();

                params.setExposureCompensation(params.getMaxExposureCompensation());

                if(params.isAutoExposureLockSupported()) {
                    params.setAutoExposureLock(false);
                }

                mCamera.setParameters(params);*/

        //holder.setFormat(PixelFormat.RGB_565);
        if (isPortraitMode()) {
          // Swap width and height sizes when in portrait, since it will be rotated by
          // 90 degrees

          mOverlay.setCameraInfo(min, max, mCameraSource.getCameraFacing());

        } else {
          mOverlay.setCameraInfo(max, min, mCameraSource.getCameraFacing());
        }

        mOverlay.clear();
      }
      mStartRequested = false;
    }
    //else
    //   Toast.makeText(mContext, "mStartRequested && mSurfaceAvailable NULL ", Toast.LENGTH_SHORT).show();
  }

  private class SurfaceCallback implements SurfaceHolder.Callback {
    @Override
    public void surfaceCreated(SurfaceHolder surface) {
      holder = surface;
      mSurfaceAvailable = true;
      try {

        startIfReady();
      } catch (IOException e) {
        Toast.makeText(mContext, "Could not start camera source.", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "Could not start camera source.", e);
      }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surface) {
      mSurfaceAvailable = false;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }
  }

  @Override
  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        /*int width = 1920;
        int height = 1080;*/
    int width = 1270;
    int height = 720;
    if (mCameraSource != null) {

      Size size = mCameraSource.getPreviewSize();
      if (size != null) {
        width = size.getWidth();
        height = size.getHeight();
        Log.e("onLayout ","Width =============== "+width+"Height =============== "+height);

                /*width = 1;
                height = 1;*/
      }
    }
    else
      Toast.makeText(mContext, "162 - mCameraSource NULL", Toast.LENGTH_SHORT).show();

    // Swap width and height sizes when in portrait, since it will be rotated 90 degrees
    if (isPortraitMode()) {
      int tmp = width;
      width = height;
      height = tmp;

            /*width = 1;
            height = 1;*/
    }

    final int layoutWidth = right - left;
    final int layoutHeight = bottom - top;

    // Computes height and width for potentially doing fit width.
    int childWidth = layoutWidth;
    int childHeight = (int)(((float) layoutWidth / (float) width) * height);

    // If height is too tall using fit width, does fit height instead.
    if (childHeight > layoutHeight) {
      childHeight = layoutHeight;
      childWidth = (int)(((float) layoutHeight / (float) height) * width);
    }

    for (int i = 0; i < getChildCount(); ++i) {
      getChildAt(i).layout(0, 0, childWidth, childHeight);
    }

    try {
      startIfReady();
    } catch (IOException e) {
      Log.e(TAG, "Could not start camera source.", e);
    }
  }

  private boolean isPortraitMode() {
    int orientation = mContext.getResources().getConfiguration().orientation;
    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
      return true;
    }
    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
      return false;
    }

    Log.d(TAG, "isPortraitMode returning false by default");
    return false;
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    this.setMeasuredDimension(1, 1);
  }
}
