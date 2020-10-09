package i30mb1.camera

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : FragmentActivity() {

    lateinit var pictureUri: Uri

    private val takePicture = registerForActivityResult(ActivityResultContracts.TakePicture()) { isImageSaved ->
            if (isImageSaved) iv_photo.setImageURI(pictureUri)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        b_take_photo.setOnClickListener {
            val prefix = "photo-"
            val postfix = System.currentTimeMillis().toString()
            val directory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            pictureUri = Uri.fromFile(File.createTempFile(prefix, postfix, directory))
            takePicture.launch(pictureUri)
        }
    }
}