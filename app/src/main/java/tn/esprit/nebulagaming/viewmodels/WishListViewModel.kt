package tn.esprit.nebulagaming.viewmodels

import android.content.Context
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tn.esprit.apimodule.NetworkClient
import tn.esprit.authmodule.repos.UserAuthManager
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(
    private val authManager: UserAuthManager
) : DefaultViewModel() {

    fun saveToWishList(context: Context, prodId: String) {

        val client = NetworkClient(context)

        val service = client.getWishlistService()

        job = CoroutineScope(Dispatchers.IO).launch {

            val res = service.addProductToWishlist(
                authManager.retrieveUserInfoFromStorage()!!.userId,
                prodId
            )

            withContext(Dispatchers.Main) {

                try {
                    if (res.isSuccessful) {
                        onSuccess(res.body()!!.message)
                    } else
                        onError(res)
                } catch (e: Exception) {
                    onError()
                }

            }

        }

    }

}