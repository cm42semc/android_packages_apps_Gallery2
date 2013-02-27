/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.gallery3d.common;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.hardware.Camera;
import android.os.Build;
import android.provider.MediaStore.MediaColumns;
import android.view.View;

import java.lang.reflect.Field;

public class ApiHelper {

public static final int VERSION_CODE = 14;
    public static interface VERSION_CODES {
        // These value are copied from Build.VERSION_CODES
        public static final int GINGERBREAD_MR1 = 10;
        public static final int HONEYCOMB = 11;
        public static final int HONEYCOMB_MR1 = 12;
        public static final int HONEYCOMB_MR2 = 13;
        public static final int ICE_CREAM_SANDWICH = 14;
        public static final int ICE_CREAM_SANDWICH_MR1 = 15;
        public static final int JELLY_BEAN = 16;
        public static final int JELLY_BEAN_MR1 = 17;
    }

    public static final boolean USE_888_PIXEL_FORMAT =
            VERSION_CODE >= VERSION_CODES.JELLY_BEAN;

    public static final boolean ENABLE_PHOTO_EDITOR =
            VERSION_CODE >= VERSION_CODES.ICE_CREAM_SANDWICH;

    public static final boolean HAS_VIEW_SYSTEM_UI_FLAG_LAYOUT_STABLE =
            hasField(View.class, "SYSTEM_UI_FLAG_LAYOUT_STABLE");

    public static final boolean HAS_VIEW_SYSTEM_UI_FLAG_HIDE_NAVIGATION =
            hasField(View.class, "SYSTEM_UI_FLAG_HIDE_NAVIGATION");

    public static final boolean HAS_MEDIA_COLUMNS_WIDTH_AND_HEIGHT =
            hasField(MediaColumns.class, "WIDTH");

    public static final boolean HAS_REUSING_BITMAP_IN_BITMAP_REGION_DECODER =
            VERSION_CODE >= VERSION_CODES.JELLY_BEAN;

    public static final boolean HAS_REUSING_BITMAP_IN_BITMAP_FACTORY =
            VERSION_CODE >= VERSION_CODES.HONEYCOMB;

    public static final boolean HAS_SET_BEAM_PUSH_URIS =
            VERSION_CODE >= VERSION_CODES.JELLY_BEAN;

    public static final boolean HAS_SET_DEFALT_BUFFER_SIZE = hasMethod(
            "android.graphics.SurfaceTexture", "setDefaultBufferSize",
            int.class, int.class);

    public static final boolean HAS_RELEASE_SURFACE_TEXTURE = hasMethod(
            "android.graphics.SurfaceTexture", "release");

    public static boolean HAS_SURFACE_TEXTURE = false;

    public static final boolean HAS_MTP =
            VERSION_CODE >= VERSION_CODES.HONEYCOMB_MR1;

    public static final boolean HAS_AUTO_FOCUS_MOVE_CALLBACK =
            VERSION_CODE >= VERSION_CODES.JELLY_BEAN;

    public static final boolean HAS_REMOTE_VIEWS_SERVICE =
            VERSION_CODE >= VERSION_CODES.HONEYCOMB;

    public static final boolean HAS_INTENT_EXTRA_LOCAL_ONLY =
            VERSION_CODE >= VERSION_CODES.HONEYCOMB;

    public static final boolean HAS_SET_SYSTEM_UI_VISIBILITY =
            hasMethod(View.class, "setSystemUiVisibility", int.class);

    public static final boolean HAS_FACE_DETECTION;
    static {
        boolean hasFaceDetection = false;
        try {
            Class<?> listenerClass = Class.forName(
                    "android.hardware.Camera$FaceDetectionListener");
            hasFaceDetection =
                    hasMethod(Camera.class, "setFaceDetectionListener", listenerClass) &&
                    hasMethod(Camera.class, "startFaceDetection") &&
                    hasMethod(Camera.class, "stopFaceDetection") &&
                    hasMethod(Camera.Parameters.class, "getMaxNumDetectedFaces");
        } catch (Throwable t) {
        }
        HAS_FACE_DETECTION = hasFaceDetection;
    }

    public static final boolean HAS_GET_CAMERA_DISABLED =
            hasMethod(DevicePolicyManager.class, "getCameraDisabled", ComponentName.class);

    public static final boolean HAS_MEDIA_ACTION_SOUND =
            VERSION_CODE >= VERSION_CODES.JELLY_BEAN;

    public static final boolean HAS_OLD_PANORAMA =
            VERSION_CODE >= VERSION_CODES.ICE_CREAM_SANDWICH;

    public static final boolean HAS_TIME_LAPSE_RECORDING =
            VERSION_CODE >= VERSION_CODES.HONEYCOMB;

    public static final boolean HAS_ZOOM_WHEN_RECORDING =
            VERSION_CODE >= VERSION_CODES.ICE_CREAM_SANDWICH;

    public static final boolean HAS_CAMERA_FOCUS_AREA =
            VERSION_CODE >= VERSION_CODES.ICE_CREAM_SANDWICH;

    public static final boolean HAS_CAMERA_METERING_AREA =
            VERSION_CODE >= VERSION_CODES.ICE_CREAM_SANDWICH;

    public static final boolean HAS_FINE_RESOLUTION_QUALITY_LEVELS =
            VERSION_CODE >= VERSION_CODES.HONEYCOMB;

    public static final boolean HAS_MOTION_EVENT_TRANSFORM =
            VERSION_CODE >= VERSION_CODES.HONEYCOMB;

    public static final boolean HAS_EFFECTS_RECORDING = false;

    // "Background" filter does not have "context" input port in jelly bean.
    public static final boolean HAS_EFFECTS_RECORDING_CONTEXT_INPUT =
            VERSION_CODE >= VERSION_CODES.JELLY_BEAN_MR1;

    public static final boolean HAS_GET_SUPPORTED_VIDEO_SIZE =
            VERSION_CODE >= VERSION_CODES.HONEYCOMB;

    public static final boolean HAS_SET_ICON_ATTRIBUTE =
            VERSION_CODE >= VERSION_CODES.HONEYCOMB;

    public static final boolean HAS_MEDIA_PROVIDER_FILES_TABLE =
            VERSION_CODE >= VERSION_CODES.HONEYCOMB;

    public static final boolean HAS_SURFACE_TEXTURE_RECORDING =
            VERSION_CODE >= VERSION_CODES.JELLY_BEAN;

    public static final boolean HAS_ACTION_BAR =
            VERSION_CODE >= VERSION_CODES.HONEYCOMB;

    // Ex: View.setTranslationX.
    public static final boolean HAS_VIEW_TRANSFORM_PROPERTIES =
            VERSION_CODE >= VERSION_CODES.HONEYCOMB;

    public static final boolean HAS_CAMERA_HDR =
            VERSION_CODE >= VERSION_CODES.JELLY_BEAN_MR1;

    public static final boolean HAS_OPTIONS_IN_MUTABLE =
            VERSION_CODE >= VERSION_CODES.HONEYCOMB;

    public static final boolean CAN_START_PREVIEW_IN_JPEG_CALLBACK =
            VERSION_CODE >= VERSION_CODES.ICE_CREAM_SANDWICH;

    public static final boolean HAS_VIEW_PROPERTY_ANIMATOR =
            VERSION_CODE >= VERSION_CODES.HONEYCOMB_MR1;

    public static final boolean HAS_POST_ON_ANIMATION =
            VERSION_CODE >= VERSION_CODES.JELLY_BEAN;

    public static int getIntFieldIfExists(Class<?> klass, String fieldName,
            Class<?> obj, int defaultVal) {
        try {
            Field f = klass.getDeclaredField(fieldName);
            return f.getInt(obj);
        } catch (Exception e) {
            return defaultVal;
        }
    }

    private static boolean hasField(Class<?> klass, String fieldName) {
        try {
            klass.getDeclaredField(fieldName);
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    private static boolean hasMethod(String className, String methodName,
            Class<?>... parameterTypes) {
        try {
            Class<?> klass = Class.forName(className);
            klass.getDeclaredMethod(methodName, parameterTypes);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private static boolean hasMethod(
            Class<?> klass, String methodName, Class<?> ... paramTypes) {
        try {
            klass.getDeclaredMethod(methodName, paramTypes);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }
}
