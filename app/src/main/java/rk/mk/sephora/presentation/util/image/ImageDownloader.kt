package rk.mk.sephora.presentation.util.image

import android.graphics.Bitmap
import io.reactivex.Observable

/**
 * This interface represents the contract for image downloading.
 */
interface ImageDownloader {

    /**
     * This function is the definition used to load the image from a given url.
     *
     * @property url of the image.
     *
     * @return a [Observable] containing a [Bitmap]
     */
    fun loadImage(url: String): Observable<Bitmap>

}