package com.example.session1314_mlinfirebase

import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions

class ObjDetection: ImageAnalysis.Analyzer{
    val options = ObjectDetectorOptions.Builder()
        .setDetectorMode(ObjectDetectorOptions.STREAM_MODE)
        .enableClassification()  // Optional
        .build()
    val objectDetector = ObjectDetection.getClient(options)
    @OptIn(ExperimentalGetImage::class) override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            objectDetector.process(image)
                .addOnFailureListener { e ->
                    Log.d("Hasil", e.toString())
                }
                .addOnSuccessListener {detectedObjects ->
                    for (detectedObject in detectedObjects){
                        val boundingBox = detectedObject.boundingBox
                        val trackingId = detectedObject.trackingId
                        val label = detectedObject.labels
                        for (lbl in label){
                            val cls = lbl.text
                            val conf = lbl.confidence
                            Log.d("Hasil", cls.toString()+conf.toString())
                        }
                    }
                }
                .addOnCompleteListener{
                    imageProxy.close()
                }
        }
    }

}