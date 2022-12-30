package tn.esprit.nebulagaming.viewmodels

import android.content.Context
import android.widget.Spinner
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor() : DefaultViewModel() {

    //on click button new product
    fun handleSaveProduct(
        context: Context,
        inputs: List<TextInputEditText>,
        textLayouts: List<TextInputLayout>,
        category: Spinner
    ) {

    }

}