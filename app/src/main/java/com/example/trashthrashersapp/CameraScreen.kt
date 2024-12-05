package com.example.trashthrashersapp

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.trashthrashersapp.ui.theme.CustomOrange
import com.example.trashthrashersapp.ui.theme.CustomRed
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
// extra firebase imports just in case
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import java.io.InputStream


@Composable
fun CameraScreen() {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var uploadedImageUrl by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
    }

    val storageReference: StorageReference = FirebaseStorage.getInstance().reference

    imageUri?.let {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, it)
            bitmap.value = ImageDecoder.decodeBitmap(source)
        }
    }

    fun uploadImage(uri: Uri?) {
        uri?.let {
            val inputStream: InputStream? = context.contentResolver.openInputStream(it)
            val byteArray = inputStream?.readBytes() ?: return

            val fileName = "images/${System.currentTimeMillis()}.jpg"
            val fileRef = storageReference.child(fileName)

            val uploadTask = fileRef.putBytes(byteArray)

            uploadTask.addOnSuccessListener {
                // Get the download URL after successful upload
                fileRef.downloadUrl.addOnSuccessListener { downloadUri ->
                    uploadedImageUrl = downloadUri.toString() // Save URL to state
                    Toast.makeText(context, "Image uploaded successfully!", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(context, "Upload failed: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    TwoColorProfileBackground()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        bitmap.value?.let { btm ->
            Image(
                bitmap = btm.asImageBitmap(),
                contentDescription = "Picked Image",
                modifier = Modifier
                    .size(400.dp)
                    .padding(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { launcher.launch("image/*") },
            colors = ButtonDefaults.buttonColors(
                containerColor = CustomOrange,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text(fontSize = 20.sp, text = "Select Image")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { uploadImage(imageUri) },
            colors = ButtonDefaults.buttonColors(
                containerColor = CustomRed,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text(fontSize = 20.sp, text = "Upload Image")
        }

        Spacer(modifier = Modifier.height(16.dp))

        uploadedImageUrl?.let {
            Text(
                text = "Image URL: $it",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
