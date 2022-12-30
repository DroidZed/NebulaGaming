package tn.esprit.nebulagaming

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import org.eazegraph.lib.charts.BarChart
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.BarModel
import org.eazegraph.lib.models.PieModel
import tn.esprit.nebulagaming.utils.Status
import tn.esprit.nebulagaming.viewmodels.AdminViewModel

@AndroidEntryPoint
class AdminActivityTest : AppCompatActivity() {
    private lateinit var pieChart: PieChart
    private lateinit var pieChart2: PieChart
    private lateinit var piechart3: BarChart
    private lateinit var allCompaniesText: TextView
    private lateinit var allUsersText: TextView
    private lateinit var allMembersText: TextView
    private lateinit var allUsersText2: TextView
    private lateinit var allCatgText3: TextView
    private lateinit var totalpricesText3: TextView
    private lateinit var allProductText3: TextView
    private lateinit var allDisabledText: TextView
    private lateinit var allEnabledText: TextView
    private val Advm: AdminViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_test)

        allCompaniesText = findViewById(R.id.allCompaniesText)
        allUsersText = findViewById(R.id.allUsersText)
        allMembersText = findViewById(R.id.allMembersText)
        pieChart = findViewById(R.id.piechart);
        pieChart2 = findViewById(R.id.piechart2);
        allDisabledText = findViewById(R.id.allDisabledText)
        allEnabledText = findViewById(R.id.allEnabledText)
        allUsersText2 = findViewById(R.id.allUsersText2)
        allCatgText3 = findViewById(R.id.allCatgText3)
        totalpricesText3 = findViewById(R.id.totalpricesText3)
        allProductText3 = findViewById(R.id.allProductText3)
        piechart3 = findViewById(R.id.piechart3)


        Advm.loadCountUsers(this).observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { stats ->
                        Log.e("Stats", stats.countAllUsers!!.toString())

                        allUsersText.text = stats.countAllUsers.toString()
                        allMembersText.text = stats.countAllmembers.toString()
                        allCompaniesText.text = stats.countAllCompanies.toString()

                        pieChart.addPieSlice(
                            PieModel(
                                "All Users",

                                stats.countAllUsers!!.toFloat(),

                                Color.parseColor("#29B6F6")
                            )
                        )

                        pieChart.addPieSlice(
                            PieModel(
                                "All Members",
                                stats.countAllmembers!!.toFloat(),
                                Color.parseColor("#EF5350"),
                            ),


                            )
                        pieChart.addPieSlice(
                            PieModel(
                                "All Companies",
                                stats.countAllCompanies!!.toFloat(),
                                Color.parseColor("#331A6A")
                            )
                        )
                        pieChart.isEnabled = true

                        pieChart.startAnimation()
                    }
                }
                Status.ERROR -> {
                    it.message?.let { message ->
                        Log.e("AdminActivityTest", "An error occured: $message")
                    }
                }
                Status.LOADING -> {
                    Log.i("AdminActivityTest", "Loading...")
                }
            }
        }

        Advm.loadEnableDisableAccount(this).observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { stats ->
                        Log.e("Stats nv", stats.countAllUsers!!.toString())
                        allUsersText2.text = stats.countAllUsers!!.toString()
                        Log.e("Enablers stat", stats.countEnableAccount.toString())
                        allDisabledText.text = stats.countDisableAccount.toString()
                        allEnabledText.text = stats.countEnableAccount.toString()

                        pieChart2.addPieSlice(
                            PieModel(
                                "All  Users",

                                stats.countAllUsers!!.toFloat(),

                                Color.parseColor("#880518")
                            )
                        )
                        pieChart2.addPieSlice(
                            PieModel(
                                "All Disabled",

                                stats.countDisableAccount!!.toFloat(),

                                Color.parseColor("#0E1F32")
                            )
                        )

                        pieChart2.addPieSlice(
                            PieModel(
                                "All Enabled",
                                stats.countEnableAccount!!.toFloat(),
                                Color.parseColor("#452234"),
                            ),
                        )
                        pieChart2.isEnabled = true
                        pieChart2.startAnimation()
                    }


                }
                Status.ERROR -> {
                    it.message?.let { message ->
                        Log.e("AdminActivityTest", "An error occured: $message")
                    }
                }
                Status.LOADING -> {
                    Log.i("AdminActivityTest", "Loading...")
                }
            }
        }

        Advm.loadCategProducts(this).observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { stats ->
                        Log.e("prod nv", stats.allProducts!!.toString())
                        Log.e("sum",stats.getSumQuantityAllProduct!!.toString())
                        allProductText3.text = stats.allProducts!!.toString()
                        allCatgText3.text = stats.getCountAllCategorys!!.toString()
                        totalpricesText3.text = stats.getSumQuantityAllProduct!!.toString()

                        piechart3.addBar(
                            BarModel(
                                "All  Categories",

                                stats.getCountAllCategorys!!.toFloat(),

                                Color.parseColor("#23A894")
                            )
                        )
                        piechart3.addBar(
                            BarModel(
                                "Products Quantity",

                                stats.getSumQuantityAllProduct!!.toFloat(),
                                Color.parseColor("#757316")
                            )
                        )

                        piechart3.addBar(
                            BarModel(
                                "All Products",
                                stats.allProducts!!.toFloat(),
                                Color.parseColor("#273C5A"),
                            ),
                        )
                        piechart3.isEnabled = true
                        piechart3.startAnimation()
                    }
                }
                Status.ERROR -> {
                    it.message?.let { message ->
                        Log.e("AdminActivityTest", "An error occured: $message")
                    }
                }
                Status.LOADING -> {
                    Log.i("AdminActivityTest", "Loading...")
                }

            }
        }
    }
}