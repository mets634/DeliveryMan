import com.example.mets634.deliveryman.BuildConfig
import timber.log.Timber

object Logger {
    init { // initiate Timber
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    fun e(msg: String) =
            Timber.e(msg)

    fun w(msg: String) =
            Timber.w(msg)

    fun i(msg: String) =
            Timber.i(msg)

    fun d(msg: String) =
            Timber.d(msg)

    fun v(msg: String) =
            Timber.v(msg)
}