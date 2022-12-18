package tn.esprit.nebulagaming

import android.content.SharedPreferences
import android.os.Bundle
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.adapters.ChatRoomAdapter
import tn.esprit.nebulagaming.data.ChatBubble
import tn.esprit.nebulagaming.utils.hideKeyboard
import tn.esprit.nebulagaming.utils.on
import tn.esprit.nebulagaming.viewmodels.ChatRoomViewModel
import tn.esprit.shared.Consts.APP_PREFS

@AndroidEntryPoint
class ChatRoomActivity : AppCompatActivity() {

    private lateinit var chatRoomAdapter: ChatRoomAdapter

    private val chatRoomVM: ChatRoomViewModel by viewModels()

    private lateinit var chatRV: RecyclerView
    private lateinit var sendBtn: Button
    private lateinit var textZoneL: TextInputLayout
    private lateinit var textZoneE: EditText

    private lateinit var topBarChat: Toolbar

    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        chatRV = findViewById(R.id.chatRV)
        sendBtn = findViewById(R.id.sendBtn)
        textZoneL = findViewById(R.id.textZoneL)
        textZoneE = findViewById(R.id.textZoneE)
        topBarChat = findViewById(R.id.topBarChat)

        val otherName = intent.getStringExtra("OTHER_USERNAME")

        setSupportActionBar(topBarChat)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        topBarChat.setNavigationOnClickListener {
            finish()
        }

        topBarChat.title = otherName

        sharedPrefs = getSharedPreferences(APP_PREFS, MODE_PRIVATE)


        val items = mutableListOf(
            ChatBubble(
                senderName = "Aymen",
                message = "hello there",
                time = "1 min ago.",
                senderId = chatRoomVM.retrieveConnectedUserId()!!,
                receiverId = ""
            ),
            ChatBubble(
                senderName = otherName!!,
                message = "hii !!",
                time = "Now.",
                senderId = "",
                receiverId = ""
            ),
        )

        chatRoomAdapter = ChatRoomAdapter(items)

        chatRV.adapter = chatRoomAdapter
        chatRV.layoutManager = LinearLayoutManager(this)

        sendBtn.setOnClickListener {

            if (textZoneE.text.isNotEmpty()) {

                items.add(
                    ChatBubble(
                        senderName = otherName,
                        message = textZoneE.text.toString().trim(),
                        time = listOf("Now", "Yesterday", "1 min ago.").random(),
                        senderId = listOf(chatRoomVM.retrieveConnectedUserId()!!, "").random(),
                        receiverId = ""
                    )
                )
                chatRoomAdapter.notifyItemInserted(items.size - 1)
                chatRV.scrollToPosition(items.size - 1)
                textZoneE.apply {
                    text.clear()
                }
            }
        }

        textZoneE.on(IME_ACTION_DONE) {
            textZoneE.apply {
                clearFocus()
                hideKeyboard()
            }
        }

    }
}