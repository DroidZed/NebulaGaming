package tn.esprit.nebulagaming

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.apimodule.models.QuizModel
import tn.esprit.nebulagaming.utils.HelperFunctions.toastMsg
import tn.esprit.nebulagaming.utils.Status
import tn.esprit.nebulagaming.viewmodels.QuizViewModel
import tn.esprit.shared.Consts.INTENT_QUIZ_ID

@AndroidEntryPoint
class QuizActivity : AppCompatActivity() {

    private val quizViewModel: QuizViewModel by viewModels()

    private lateinit var quizTopBar: Toolbar

    private lateinit var quizQuestionText: TextView

    private lateinit var quizRadioGrp: RadioGroup

    private lateinit var quizCheckboxes: LinearLayout

    private lateinit var cancelBtnQ: Button

    private lateinit var submitBtnQ: Button

    private var remainingTries: Int = 0

    private lateinit var correctAnswers: List<String>

    private var quizType: String = ""

    private lateinit var rad1: RadioButton
    private lateinit var rad2: RadioButton
    private lateinit var rad3: RadioButton

    private lateinit var cbx1: CheckBox
    private lateinit var cbx2: CheckBox
    private lateinit var cbx3: CheckBox

    lateinit var checkBoxes: List<CheckBox>
    lateinit var rads: List<RadioButton>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        quizTopBar = findViewById(R.id.quizTopBar)

        setSupportActionBar(quizTopBar)

        quizTopBar.setNavigationOnClickListener { finish() }

        quizQuestionText = findViewById(R.id.quizQuestionText)
        quizRadioGrp = findViewById(R.id.quizRadioGrp)
        quizCheckboxes = findViewById(R.id.quizCheckboxes)
        cancelBtnQ = findViewById(R.id.cancelBtnQ)
        submitBtnQ = findViewById(R.id.submitBtnQ)

        rad1 = findViewById(R.id.rad1)
        rad2 = findViewById(R.id.rad2)
        rad3 = findViewById(R.id.rad3)

        cbx1 = findViewById(R.id.cbx1)
        cbx2 = findViewById(R.id.cbx2)
        cbx3 = findViewById(R.id.cbx3)

        checkBoxes = listOf(
            cbx1,
            cbx2,
            cbx3
        )
        rads = listOf(
            rad1,
            rad2,
            rad3
        )

        val quizId = intent.getStringExtra(INTENT_QUIZ_ID)

        fetchQuiz(quizId)

        cancelBtnQ.setOnClickListener {

            when (quizType) {
                "U" -> rads.forEach { it.isChecked = false }

                "M" -> {
                    checkBoxes.forEach { it.isChecked = false }
                }
            }
        }

        submitBtnQ.setOnClickListener {

            when (quizType) {
                "U" -> {
                    val selected = rads.first { it.isChecked }.text!!

                    if (!correctAnswers.contains(selected) && remainingTries > 0) {

                        remainingTries--

                        toastMsg(this, "Wrong answer ! You've got $remainingTries tries left")

                        if (remainingTries <= 0) {

                            toastMsg(this, "No more tries left !")
                            submitBtnQ.isClickable = false
                            submitBtnQ.isActivated = false

                          //  quizViewModel.answerQuiz(this, body)
                        }
                    }
                }

                "M" -> {

                }
            }
        }
    }

    private fun fetchQuiz(quizId: String?) {

        if (quizId == null) exitFailed()
        else {

            quizViewModel.fetchQuiz(this, quizId).observe(this) { resource ->

                resource?.let { rs ->

                    when (rs.status) {

                        Status.LOADING -> {}

                        Status.SUCCESS -> rs.data.apply {
                            setupUi(this!!)
                        }

                        Status.ERROR -> exitFailed()
                    }
                }
            }
        }
    }

    private fun exitFailed() {
        toastMsg(this, "Unable to retrieve quiz at the moment, please check later...")
        finish()
    }

    private fun setupUi(quizModel: QuizModel) {

        remainingTries = quizModel.numberOfTries

        correctAnswers =
            quizModel.answers.filter { it.isCorrect!! }.map { it.choice }

        quizType = quizModel.type

        val answers = quizModel.answers
        quizQuestionText.text = quizModel.question

        when (quizModel.type) {
            "M" -> {

                quizCheckboxes.let { chbx ->
                    chbx.visibility = View.VISIBLE
                    for (i in 0..answers.size) {
                        checkBoxes[i].text = answers[i].choice
                    }
                }
            }
            "U" -> {

                quizRadioGrp.let { rad ->
                    rad.visibility = View.VISIBLE
                    for (i in 0..answers.size) {
                        rads[i].text = answers[i].choice
                    }
                }
            }
        }
    }
}