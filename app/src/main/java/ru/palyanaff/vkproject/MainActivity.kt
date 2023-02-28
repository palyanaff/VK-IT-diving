package ru.palyanaff.vkproject

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.palyanaff.vkproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val groupButton = findViewById<ImageView>(R.id.group_image)
        val message = findViewById<ImageView>(R.id.chat_image)
        val grid = findViewById<ImageView>(R.id.grid_image)

        val micButton : FloatingActionButton = findViewById(R.id.mic_button)
        val camButton : FloatingActionButton = findViewById(R.id.videocam_button)
        val handButton : FloatingActionButton = findViewById(R.id.hand_button)
        val callEndButton : FloatingActionButton = findViewById(R.id.call_end_button)

        val userName : TextView = findViewById(R.id.user_name_you)

        // boolean variables for muting camera and microphone buttons
        var isCamMuted = false
        var isMicMuted = false
        val img : Drawable? = getDrawable(R.drawable.mic_off)

        // set color of FAB (for night theme)
        micButton.setColorFilter(Color.WHITE)
        camButton.setColorFilter(Color.WHITE)
        handButton.setColorFilter(Color.WHITE)
        callEndButton.setColorFilter(Color.WHITE)

        // open group of members
        groupButton.setOnClickListener{
            // TODO: open an user list
            startActivity(Intent(Intent.ACTION_DIAL))
        }

        // mute and unmute camera
        camButton.setOnClickListener {
            if (!isCamMuted) {
                camButton.setImageResource(R.drawable.videocam_off)
                camButton.setColorFilter(Color.BLACK)
                camButton.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE))
            } else {
                camButton.setImageResource(R.drawable.videocam)
                camButton.setColorFilter(Color.WHITE)
                camButton.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.gray)))
            }
            isCamMuted = !isCamMuted
        }

        // mute and unmute microphone
        micButton.setOnClickListener {
            if (!isMicMuted) {
                micButton.setImageResource(R.drawable.mic_off)
                micButton.setColorFilter(Color.BLACK)
                micButton.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE))

                userName.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null, null, img, null)
            } else {
                micButton.setImageResource(R.drawable.mic)
                micButton.setColorFilter(Color.WHITE)
                micButton.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.gray)))
                userName.setCompoundDrawables(null, null, null, null)
            }
            isMicMuted = !isMicMuted
        }

        // show an AlertDialog with hello
        handButton.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setMessage(R.string.dialog_title)
            builder.show()
        }

        // shutdown the application
        callEndButton.setOnClickListener{
            finish()
        }
    }

}