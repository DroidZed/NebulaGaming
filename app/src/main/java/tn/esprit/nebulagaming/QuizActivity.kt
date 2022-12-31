package tn.esprit.nebulagaming

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
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
    private lateinit var nbTriesRem: TextView

    private lateinit var quizRadioGrp: RadioGroup

    private lateinit var quizCheckboxes: LinearLayout

    private lateinit var cancelBtnQ: Button

    private lateinit var submitBtnQ: Button

    private lateinit var loadingBar: ProgressBar

    private var answered = false

    private var remainingTries: Int = 0

    private lateinit var correctAnswers: List<String>

    private lateinit var rad1: RadioButton
    private lateinit var rad2: RadioButton
    private lateinit var rad3: RadioButton

    private lateinit var cbx1: CheckBox
    private lateinit var cbx2: CheckBox
    private lateinit var cbx3: CheckBox

    private lateinit var quizId: String

    private lateinit var checkBoxes: List<CheckBox>
    private lateinit var rads: List<RadioButton>

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
        nbTriesRem = findViewById(R.id.nbTriesRem)
        loadingBar = findViewById(R.id.loadingBar)
        rad1 = findViewById(R.id.rad1)
        rad2 = findViewById(R.id.rad2)
        rad3 = findViewById(R.id.rad3)
        cbx1 = findViewById(R.id.cbx1)
        cbx2 = findViewById(R.id.cbx2)
        cbx3 = findViewById(R.id.cbx3)

        checkBoxes = listOf(cbx1, cbx2, cbx3)

        rads = listOf(rad1, rad2, rad3)

        quizId = intent.getStringExtra(INTENT_QUIZ_ID) ?: ""

        if (quizId.isBlank()) exitFailed()

        fetchQuiz(quizId)

        cancelBtnQ.setOnClickListener { clearQuiz() }

        submitBtnQ.setOnClickListener { answerQuiz(quizId) }
    }

    private fun clearQuiz() {
        if (quizRadioGrp.isVisible) quizRadioGrp.clearCheck()
        else if (quizCheckboxes.isVisible) checkBoxes.forEach { it.isChecked = false }
    }

    private fun answerQuiz(quizId: String) {

        var correct = false

        when {
            quizRadioGrp.isVisible -> {

                when (val checkedRadioButtonId = quizRadioGrp.checkedRadioButtonId) {
                    -1 -> toastMsg(this, "Empty answer !")
                    else -> {
                        val selected = findViewById<RadioButton>(checkedRadioButtonId).text

                        if (!correctAnswers.contains(selected) && remainingTries > 0) {

                            remainingTries--

                            nbTriesRem.text = getString(R.string.remaining_tries, remainingTries)

                            if (remainingTries <= 0) {

                                toastMsg(this, "No more tries left !")
                                submitBtnQ.apply {
                                    isClickable = false
                                    isActivated = false
                                }
                                answered = true
                            }
                        } else {
                            correct = true
                            answered = true
                        }
                    }
                }
            }
            quizCheckboxes.isVisible -> {

                when {
                    checkBoxes.all { !it.isChecked } -> toastMsg(this, "Empty answer !")
                    else -> {

                        val checkText = checkBoxes.filter { it.isChecked }.map { it.text }

                        if (!correctAnswers.containsAll(checkText)) {

                            remainingTries--

                            nbTriesRem.text = getString(R.string.remaining_tries, remainingTries)

                            if (remainingTries <= 0) {

                                toastMsg(this, "No more tries left !")
                                submitBtnQ.apply {
                                    isClickable = false
                                    isActivated = false
                                }
                                answered = true
                            }
                        } else {
                            correct = true
                            answered = true
                        }
                    }
                }

            }
        }

        if (answered) quizViewModel.answerQuiz(this, quizId, correct).observe(this) {
            it?.let { rs ->
                when (rs.status) {

                    Status.LOADING -> {
                        loadingBar.visibility = View.VISIBLE
                    }

                    Status.SUCCESS -> {

                        loadingBar.visibility = View.INVISIBLE

                        toastMsg(this@QuizActivity, rs.data!!.message!!)

                        quizViewModel.deleteNotif(quizId)

                        finish()
                    }
                    Status.ERROR -> {
                        toastMsg(this@QuizActivity, rs.message!!)
                        loadingBar.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    private fun fetchQuiz(quizId: String) {


        quizViewModel.fetchQuiz(this, quizId).observe(this) { resource ->

            resource?.let { rs ->

                when (rs.status) {

                    Status.LOADING -> {
                        loadingBar.visibility = View.VISIBLE
                    }

                    Status.SUCCESS -> rs.data.apply { setupUi(this!!) }

                    Status.ERROR -> exitFailed()
                }
            }
        }
    }

    private fun exitFailed() {
        loadingBar.visibility = View.INVISIBLE
        toastMsg(this, "Unable to retrieve quiz at the moment, please check later...")
        finish()
    }

    private fun setupUi(quizModel: QuizModel) {

        remainingTries = quizModel.numberOfTries
        nbTriesRem.text = getString(R.string.remaining_tries, remainingTries)

        loadingBar.visibility = View.INVISIBLE

        correctAnswers =
            quizModel.answers.filter { it.isCorrect!! }.map { it.choice }

        val answers = quizModel.answers
        quizQuestionText.text = quizModel.question

        when (quizModel.type) {
            "M" -> quizCheckboxes.let { chbx ->
                chbx.visibility = View.VISIBLE
                for (i in 0..answers.size) {
                    checkBoxes[i].text = answers[i].choice
                }
            }
            "U" -> quizRadioGrp.let { rad ->
                rad.visibility = View.VISIBLE
                for (i in 0..answers.size) {
                    rads[i].text = answers[i].choice
                }
            }
        }
    }
}