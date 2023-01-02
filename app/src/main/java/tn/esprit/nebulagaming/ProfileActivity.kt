package tn.esprit.nebulagaming

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mikhaellopez.circularimageview.CircularImageView
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.adapters.ProfileVP2Adapter
import tn.esprit.nebulagaming.fragments.BookmarksFragment
import tn.esprit.nebulagaming.fragments.PostsFragment
import tn.esprit.nebulagaming.fragments.RankFragment
import tn.esprit.nebulagaming.utils.FileUtils.getFileFromUri
import tn.esprit.nebulagaming.utils.HelperFunctions.toastMsg
import tn.esprit.nebulagaming.utils.HelperFunctions.usePicasso
import tn.esprit.nebulagaming.viewmodels.ProfileViewModel
import tn.esprit.roommodule.models.UserProfile
import tn.esprit.shared.Consts.APP_PREFS
import tn.esprit.shared.Consts.IS_GRANTED_READ_IMAGES
import tn.esprit.shared.Consts.PERMS_REQUEST_CODE
import tn.esprit.shared.Consts.PICK_IMAGE_CODE


@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {

    private lateinit var pViewPager: ViewPager2
    private lateinit var pTabLayout: TabLayout

    private val profileVM: ProfileViewModel by viewModels()

    private lateinit var photoProf: CircularImageView
    private lateinit var numLevel: TextView
    lateinit var nameUser: TextView

    private lateinit var sharedPrefs: SharedPreferences

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_CODE) {
            val contentURI = data?.data
            try {

                val file = getFileFromUri(this, contentURI!!)

                Log.d("FILE", file.absolutePath)

                profileVM.changeProfilePhoto(this, file)
                photoProf.apply {
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    setImageURI(contentURI)
                }


            } catch (e: Exception) {
                toastMsg(this, "Invalid file!")
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        when (requestCode) {
            PERMS_REQUEST_CODE -> {

                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    sharedPrefs.edit().putBoolean(IS_GRANTED_READ_IMAGES, true).apply()
                }
            }

            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        pViewPager = findViewById(R.id.pViewPager)
        pTabLayout = findViewById(R.id.pTabLayout)
        photoProf = findViewById(R.id.photoProf)
        numLevel = findViewById(R.id.numlevel)
        nameUser = findViewById(R.id.nameUser)

        sharedPrefs = getSharedPreferences(APP_PREFS, MODE_PRIVATE)

        requestPermissions(
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            PERMS_REQUEST_CODE
        )

        photoProf.setOnClickListener {
            startActivityForResult(
                Intent(
                    Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI
                ), PICK_IMAGE_CODE
            )
        }

        val fragments: MutableList<Fragment> = mutableListOf(
            RankFragment(),
            BookmarksFragment(),
            PostsFragment()
        )

        val data = profileVM.fetchUserInfoFromDb()

        if (data != null) setUi(data.user!!)

        pViewPager.adapter = ProfileVP2Adapter(this, fragments)

        TabLayoutMediator(pTabLayout, pViewPager) { tab, position ->

            when (position) {
                0 -> tab.apply {
                    text = resources.getString(R.string.profile)
                    icon = ResourcesCompat.getDrawable(resources, R.drawable.name, null)
                }

                1 -> tab.apply {
                    text = resources.getString(R.string.bookmarks)
                    icon = ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.ic_baseline_bookmarks_24,
                        null
                    )
                }
                2 -> tab.apply {
                    text = resources.getString(R.string.posts)
                    icon = ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.ic_baseline_forum_24,
                        null
                    )
                }
            }
        }.attach()
    }

    private fun setUi(user: UserProfile) {

        val userPhoto = when {
            user.photo.contains("gravatar") -> user.photo
            else -> user.photo
        }

        usePicasso(userPhoto, R.drawable.avatar_profile_png_picture, photoProf)

        profileVM.nameUser.postValue(user.name)

        numLevel.text = resources.getString(R.string.levelU, user.level)
    }
}
