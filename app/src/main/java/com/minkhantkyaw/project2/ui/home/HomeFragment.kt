package com.minkhantkyaw.project2.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.minkhantkyaw.project2.R
import com.minkhantkyaw.project2.data.CurrencyApiService
import com.minkhantkyaw.project2.data.response.ConnectivityInterceptorImpl
import com.minkhantkyaw.project2.data.response.CurrencyNetworkDataSourceImpl
import com.minkhantkyaw.project2.data.response.network.CurrencyApiResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val apiService = CurrencyApiService(ConnectivityInterceptorImpl(this.context!!))
        val currencyNetworkDataSource = CurrencyNetworkDataSourceImpl(apiService)

        currencyNetworkDataSource.downloadedCurrency.observe(viewLifecycleOwner, Observer {
            setCurrency(it)
            setFlags()

        })

        CoroutineScope(Dispatchers.Main).launch {
            currencyNetworkDataSource.fetchCurrency()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setCurrency(temp : CurrencyApiResponse){
        usa_text.text = temp.rates.USD + " Kyats"
        singapore_text.text = temp.rates.SGD + " Kyats"
        thai_text.text = temp.rates.THB + " Kyats"
        euro_text.text = temp.rates.EUR + " Kyats"
        japan_text.text = temp.rates.JPY + " Kyats"
    }

    private fun setImages(flag: String, id: ImageView) {
        Picasso.get()
            .load("https://www.countryflags.io/$flag/flat/64.png")
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_broken_image)
            .into(id)
    }

    private fun setFlags(){
        setImages("mm", base_id)
        setImages("us", usa_Id)
        setImages("sg", singapore_Id)
        setImages("th", thai_Id)
        setImages("eu", euro_Id)
        setImages("jp", japan_Id)
    }


}
