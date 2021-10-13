package rk.mk.sephora.presentation.util.image

import android.graphics.Bitmap
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import rk.mk.sephora.presentation.util.image.ImageDownloader

/**
 * This class represents an implementation of [ImageDownloader] interface used to download images.
 */
class RxPicasso : ImageDownloader {

    /**
     * This function is the definition used to load the image using [Picasso].
     *
     * @property url of the image.
     *
     * @return a [Observable] containing a [Bitmap]
     */
    override fun loadImage(url: String): Observable<Bitmap> {
        return Observable.fromCallable {
            //Using the Picasso library to download te image
            Picasso.get().load(url).get()
        }
    }

}