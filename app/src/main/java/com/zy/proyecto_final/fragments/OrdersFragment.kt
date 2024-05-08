package com.zy.proyecto_final.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zy.proyecto_final.R
import com.zy.proyecto_final.fragment.OrdersDetailsFragment
import com.zy.proyecto_final.pojo.Order
import com.zy.proyecto_final.pojo.Product
import com.zy.proyecto_final.recyclerviewadapter.OrdersRecyclerViewAdapter
import com.zy.proyecto_final.viewmodel.OrderViewModel


class OrdersFragment : Fragment() {
    private val viewmodel : OrderViewModel by activityViewModels()
    private var view: View? = null

    var detail_click: ((Int, Product) -> Unit)? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val orderStatus = arguments?.getString("orderStatus")
        view = inflater.inflate(R.layout.fragment_orders, container, false)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.listado)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        this.viewmodel.items.value?.let {
            recyclerView?.adapter = OrdersRecyclerViewAdapter(it.toMutableList(), orderStatus!!)

        }
        (view?.findViewById<RecyclerView>(R.id.listado)!!.adapter as OrdersRecyclerViewAdapter).detail_click =
            { position: Int, item: Order ->
                run {
                    this.viewmodel.selectedorder = item
                   this.detail_click?.let { it -> it(position, item) }
                    var fm: FragmentManager = parentFragmentManager
                    val args = Bundle()
                    args.putLong("orderId", item.id); // Suponiendo que orderId es el ID de la orden.
                    val fragment = OrdersDetailsFragment.newInstance()
                    fragment.setArguments(args);
                    // pasa el id de la orden
                    fm.beginTransaction().replace(R.id.fragmentContainerView, fragment).commit();
                }

                return view
            }
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