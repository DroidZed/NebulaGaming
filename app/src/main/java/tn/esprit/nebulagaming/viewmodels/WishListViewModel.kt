package tn.esprit.nebulagaming.viewmodels

import android.content.Context
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import tn.esprit.apimodule.models.Product
import tn.esprit.nebulagaming.utils.HelperFunctions.toastMsg
import tn.esprit.roommodule.dao.UserDao
import tn.esprit.roommodule.dao.WishlistDao
import tn.esprit.roommodule.models.Wishlist
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(
    private val wishlistDao: WishlistDao,
    private val userDao: UserDao
) : DefaultViewModel() {

    fun add(context: Context, prod: Product) = runBlocking {
        try {

            wishlistDao.create(
                Wishlist(
                    name = prod.name,
                    userId = authManager.retrieveUserInfoFromStorage()!!.userId,
                    idProd = prod._id,
                    price = prod.price,
                    image = prod.image
                )
            )
            toastMsg(context, "Added to wishlist !")

        } catch (e: Exception) {
            toastMsg(context, "Item already in list!")
        }
    }

    fun getAll() = runBlocking { userDao.getAll(getUserId()) }
}