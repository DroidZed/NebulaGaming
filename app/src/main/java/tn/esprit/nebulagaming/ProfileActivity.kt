package tn.esprit.nebulagaming

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.button.MaterialButton
import tn.esprit.nebulagaming.fragments.PostsFragment
import tn.esprit.nebulagaming.fragments.RankFragment
import tn.esprit.nebulagaming.fragments.WishlistFragment

class ProfileActivity : AppCompatActivity() {
    private lateinit var fragContainer: FragmentContainerView
    private lateinit var fg1: PostsFragment
    private lateinit var fg2: RankFragment
    private lateinit var fg3: WishlistFragment
    private lateinit var btnforum: MaterialButton
    private lateinit var btninfo: MaterialButton
    private lateinit var btnwhishlistprof: MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        fragContainer = findViewById(R.id.fragcontainer)
        fg1 = PostsFragment()
        fg2 = RankFragment()
        fg3 = WishlistFragment()
        btnforum = findViewById(R.id.forum)
        btninfo = findViewById(R.id.info)
        btnwhishlistprof = findViewById(R.id.whishlistprof)
        supportFragmentManager.beginTransaction().add(R.id.fragcontainer, fg1).commit()

        btnforum.setOnClickListener {
            navigate(fg1)
        }
        btninfo.setOnClickListener {
            navigate(fg2)
        }
        btnwhishlistprof.setOnClickListener {
            navigate(fg3)
        }
    }


    private fun navigate(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragcontainer, fragment)
            .commit()
    }
}



