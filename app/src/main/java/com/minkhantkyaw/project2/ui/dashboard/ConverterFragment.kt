package com.minkhantkyaw.project2.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.minkhantkyaw.project2.R
import com.minkhantkyaw.project2.data.CurrencyApiService
import com.minkhantkyaw.project2.data.response.ConnectivityInterceptorImpl
import com.minkhantkyaw.project2.data.response.CurrencyNetworkDataSourceImpl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_converter.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConverterFragment : Fragment(), AdapterView.OnItemSelectedListener{

    private lateinit var converterViewModel: ConverterViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        converterViewModel = ViewModelProviders.of(this).get(ConverterViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_converter, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterCreate()

        val apiService = CurrencyApiService(ConnectivityInterceptorImpl(this.context!!))
        val currencyNetworkDataSource = CurrencyNetworkDataSourceImpl(apiService)

        currencyNetworkDataSource.downloadedCurrency.observe(viewLifecycleOwner, Observer {
            setImages("us", currency_to_id)
            setImages("mm", currency_from_id)
        })

        CoroutineScope(Dispatchers.Main).launch {
            currencyNetworkDataSource.fetchCurrency()
        }
        buttonFun()
    }

    private fun buttonFun() {
        convert_id.setOnClickListener{
            val currency = editText.text.toString()
            if(currency.isNullOrBlank()){
                Toast.makeText(context, "No Value Inserted", LENGTH_SHORT).show()
            }
            else {
                val apiService = CurrencyApiService(ConnectivityInterceptorImpl(this.context!!))
                val currencyNetworkDataSource = CurrencyNetworkDataSourceImpl(apiService)

                currencyNetworkDataSource.downloadedCurrency.observe(viewLifecycleOwner, Observer {
                    if(choose_currency_id.selectedItem == "USD") {
                        val temp = it.rates.USD.replace(",","")

                        val ans = temp.toBigDecimal().toLong() * currency.toLong()

                        converted_currency_id.text = ans.toString()
                    }
                    else if(choose_currency_id.selectedItem == "SGD") {
                        val temp = it.rates.SGD.replace(",","")

                        val ans = temp.toBigDecimal().toLong() * currency.toLong()

                        converted_currency_id.text = ans.toString()
                    }

                    else if(choose_currency_id.selectedItem == "EUR") {
                        val temp = it.rates.EUR.replace(",","")

                        val ans = temp.toBigDecimal().toLong() * currency.toLong()

                        converted_currency_id.text = ans.toString()
                    }

                    else if(choose_currency_id.selectedItem == "THB") {
                        val temp = it.rates.THB.replace(",","")

                        val ans = temp.toBigDecimal().toLong() * currency.toLong()

                        converted_currency_id.text = ans.toString()
                    }

                    else if(choose_currency_id.selectedItem == "CNY") {
                        val temp = it.rates.CNY.replace(",","")

                        val ans = temp.toBigDecimal().toLong() * currency.toLong()

                        converted_currency_id.text = ans.toString()
                    }

                    else if(choose_currency_id.selectedItem == "AUD") {
                        val temp = it.rates.AUD.replace(",","")

                        val ans = temp.toBigDecimal().toLong() * currency.toLong()

                        converted_currency_id.text = ans.toString()
                    }

                })

                CoroutineScope(Dispatchers.Main).launch {
                    currencyNetworkDataSource.fetchCurrency()
                }

            }
        }
    }

    private fun setImages(flag: String, id: ImageView) {
        Picasso.get()
            .load("https://www.countryflags.io/$flag/flat/64.png")
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_broken_image)
            .into(id)
    }

    private fun adapterCreate() {
        ArrayAdapter.createFromResource(
            this.context!!,
            R.array.currency_name,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            choose_currency_id.adapter = adapter
        }
        choose_currency_id.onItemSelectedListener = this
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val apiService = CurrencyApiService(ConnectivityInterceptorImpl(this.context!!))
        val currencyNetworkDataSource = CurrencyNetworkDataSourceImpl(apiService)
        currencyNetworkDataSource.downloadedCurrency.observe(viewLifecycleOwner, Observer {
            when(position) {
                0 -> {
                    setImages("us", currency_to_id)
                }

                1-> {
                    setImages("sg", currency_to_id)
                }

                2 -> {
                    setImages("eu", currency_to_id)
                }

                3 -> {
                    setImages("th", currency_to_id)
                }

                4 -> {
                    setImages("au", currency_to_id)
                }

                5 -> {
                    setImages("cn", currency_to_id)
                }
            }
        })

        CoroutineScope(Dispatchers.Main).launch {
            currencyNetworkDataSource.fetchCurrency()
        }
    }

}


