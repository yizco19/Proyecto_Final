package com.zy.proyecto_final.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zy.proyecto_final.R

class OrdersFragment : Fragment() {
    private var view: View? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val orderStatus = arguments?.getString("orderStatus")
        view = inflater.inflate(R.layout.fragment_orders, container, false)

        return view
    }
    companion object {
        fun newInstance(orderStatus: String): OrdersFragment {
            val fragment = OrdersFragment()
            val args = Bundle()
            args.putString("orderStatus", orderStatus)
            fragment.arguments = args
            return fragment
        }
    }
}