package br.com.coderup.pokemons.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.request.ImageRequestBuilder


object FrescoUtils {

    fun initialize(context: Context) {
        val config = ImagePipelineConfig
                .newBuilder(context)
                .setBitmapsConfig(Bitmap.Config.RGB_565)
                .setDownsampleEnabled(true)
                .build()

        Fresco.initialize(context, config)
    }


    fun getDefaultDraweeController(path: String, width: Int, height: Int): DraweeController {
        val uriPhoto = Uri.parse("http://image.tmdb.org/t/p/w" + width + path)

        return Fresco.newDraweeControllerBuilder()
                .setImageRequest(FrescoUtils.getDefaultImageRequest(uriPhoto, ResizeOptions(width, height)))
                .build()
    }


    fun getDefaultImageRequest(uriPhoto: Uri, resizeOptions: ResizeOptions): ImageRequest {
        return ImageRequestBuilder.newBuilderWithSource(uriPhoto)
                .setResizeOptions(resizeOptions)
                .setProgressiveRenderingEnabled(true)
                .setLocalThumbnailPreviewsEnabled(true)
                .build()
    }

}