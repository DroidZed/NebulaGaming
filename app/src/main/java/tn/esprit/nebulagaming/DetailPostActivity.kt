package tn.esprit.nebulagaming

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.imageview.ShapeableImageView
import tn.esprit.nebulagaming.fragments.CommentFragment

class DetailPostActivity : AppCompatActivity() {
    private lateinit var fragContainer: FragmentContainerView
    private lateinit var fg1: CommentFragment
    private lateinit var imgprofpost: ShapeableImageView
    private lateinit var imgpost: ShapeableImageView
    private lateinit var usernamepost: TextView
    private lateinit var datepost: TextView
    private lateinit var textpost: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_post)

        fragContainer = findViewById(R.id.fragmentContainercommnet)

        fg1 = CommentFragment()

        supportFragmentManager.beginTransaction().add(R.id.fragmentContainercommnet, fg1).commit()
        imgprofpost = findViewById(R.id.photoprofdetailuser)
        imgpost = findViewById(R.id.detphotoforum)
        usernamepost = findViewById(R.id.usernamedetpost)
        datepost = findViewById(R.id.datepostdet)
        textpost = findViewById(R.id.textPostdetai)

        val bundle = intent.extras
        if (bundle != null) {
            val imgprof = bundle.getInt("postUserPhoto")
            val img = bundle.getInt("postPhoto")
            val username = bundle.getString("postUser")
            val date = bundle.getString("postDate")
            val text = bundle.getString("postText")
            imgprofpost.setImageResource(imgprof)
            imgpost.setImageResource(img)
            usernamepost.text = username
            datepost.text = date
            textpost.text = text
        }

    }
}