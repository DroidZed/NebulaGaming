package tn.esprit.nebulagaming

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.imageview.ShapeableImageView
import tn.esprit.nebulagaming.data.ParcelablePostData
import tn.esprit.nebulagaming.fragments.CommentFragment
import tn.esprit.shared.Consts.POST_DATA

class DetailPostActivity : AppCompatActivity() {

    private lateinit var fragContainer: FragmentContainerView

    private lateinit var imgprofpost: ShapeableImageView
    private lateinit var imgPost: ShapeableImageView
    private lateinit var usernamepost: TextView
    private lateinit var datepost: TextView
    private lateinit var textpost: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_post)

        fragContainer = findViewById(R.id.fragmentContainerComment)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerComment, CommentFragment()).commit()

        imgprofpost = findViewById(R.id.photoprofdetailuser)
        imgPost = findViewById(R.id.detphotoforum)
        usernamepost = findViewById(R.id.usernamedetpost)
        datepost = findViewById(R.id.datepostdet)
        textpost = findViewById(R.id.textPostdetai)

        val postValue = intent.getParcelableExtra<ParcelablePostData>(POST_DATA)

        imgprofpost.setImageResource(postValue?.postUserPhoto!!)
        imgPost.setImageResource(postValue.postPhoto)
        usernamepost.text = postValue.postUser
        datepost.text = postValue.postDate
        textpost.text = postValue.postText
    }
}