package tn.esprit.nebulagaming

import android.content.SharedPreferences
import android.os.Bundle
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import tn.esprit.nebulagaming.adapters.ChatRoomAdapter
import tn.esprit.nebulagaming.data.ChatBubble
import tn.esprit.nebulagaming.utils.hideKeyboard
import tn.esprit.nebulagaming.utils.on
import tn.esprit.shared.Consts.APP_PREFS
import tn.esprit.shared.Consts.U_ID_KEY

class ChatRoomActivity : AppCompatActivity() {

    private lateinit var chatRoomAdapter: ChatRoomAdapter

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

        setSupportActionBar(topBarChat)

        sharedPrefs = getSharedPreferences(APP_PREFS, MODE_PRIVATE)

        val userId = sharedPrefs.getString(U_ID_KEY, "")

        val items = mutableListOf(
            ChatBubble(
                message = "hello there",
                time = "1 min ago.",
                senderId = userId!!,
                receiverId = ""
            ),
            ChatBubble(
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
                        message = textZoneE.text.toString(),
                        time = "Now",
                        senderId = userId,
                        receiverId = ""
                    )
                )
                chatRoomAdapter.notifyItemInserted(items.size - 1)
                chatRV.scrollToPosition(items.size - 1)
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