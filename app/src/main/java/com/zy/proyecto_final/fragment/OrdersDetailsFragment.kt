package com.zy.proyecto_final.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zy.proyecto_final.R
import com.zy.proyecto_final.databinding.FragmentOrderDetailsBinding
import com.zy.proyecto_final.fragments.ProductsFragment
import com.zy.proyecto_final.recyclerviewadapter.OrderDataRecyclerViewAdapter
import com.zy.proyecto_final.recyclerviewadapter.OrdersRecyclerViewAdapter
import com.zy.proyecto_final.viewmodel.OrderViewModel
import com.zy.proyecto_final.viewmodel.UserViewModel

class OrdersDetailsFragment : Fragment() {
    private val viewmodel: OrderViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()

    private  lateinit var binding: FragmentOrderDetailsBinding
    private var view:View?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_details, container, false)
        binding.lifecycleOwner = this
        // Obteniendo el ID de la orden
        val orderId = arguments!!.getString("orderId")

        binding.titleOrder.setNavigationOnClickListener {
            //replace fragment
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, ProductsFragment())?.commit()

        }
        var order = viewmodel.getOrder(orderId!!.toLong())

        binding.phone  = order.phone
        binding.address = order.address
        binding.payment =order.payment
        binding.total = order.total
        binding.titleOrder.title = order.orderStatus
        var time =order.time
        var date = order.date
        binding.date = date
        binding.time = time
        val recyclerView = view?.findViewById<RecyclerView>(R.id.listado)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        this.viewmodel.items.value?.let {
            recyclerView?.adapter = OrderDataRecyclerViewAdapter(it.toMutableList())
        }
        
        
        
        return binding.root


    }

    companion object {
        fun newInstance() = OrdersDetailsFragment()
    }
}