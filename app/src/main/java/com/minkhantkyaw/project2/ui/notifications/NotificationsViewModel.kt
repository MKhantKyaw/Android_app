package com.minkhantkyaw.project2.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "ဤ app သည် ပြည်ထောင်စုသမ္မတမြန်မာနိုင်ငံတော် ဗဟိုဘဏ်မှ ထုတ်ထားသော api မှ data များကို အသုံးပြုထားခြင်းဖြစ်ပါသည်။" +
                "data များမှာနေ့စဥ်သုံးစွဲနေကျဖြစ်သော ဝယ်‌ဈေးရောင်းဈေး များမပါရှိပါ။ ဗဟိုဘဏ်မှ လဲလှယ်ပေးသောနှုန်းထားများသာပါရှိပါသည်။"
    }
    val text: LiveData<String> = _text

    private val _text1 = MutableLiveData<String>().apply {
        value = "MMK = Myanmar Kyat \n" +
                "USD = US Dollar \n‌‌" +
                "SGD = Singapore Dollar \n" +
                "EUR = Euro \n" +
                "THB = Thai Baht \n" +
                "JPY = Japan Yen \n" +
                "CNY = China Yuan \n"
    }
    val text1: LiveData<String> = _text1
}