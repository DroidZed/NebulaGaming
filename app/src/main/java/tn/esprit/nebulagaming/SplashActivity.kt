package tn.esprit.nebulagaming

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var img:ImageView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        img = findViewById(R.id.splnvo)
        val a: Animation = AlphaAnimation(1.00f, 0.00f)

        a.duration = 3000
        a.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                img.visibility = View.VISIBLE

            }

            override fun onAnimationRepeat(animation: Animation?) {
                // TODO Auto-generated method stub
            }

            override fun onAnimationEnd(animation: Animation?) {
                img.visibility = View.GONE

            }
        })

        img.startAnimation(a)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 2000)
    }
}