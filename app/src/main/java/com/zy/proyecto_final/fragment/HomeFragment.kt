package com.zy.proyecto_final.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView.ScaleType
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.zy.proyecto_final.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val imageSlider = view?.findViewById<ImageSlider>(R.id.imageSlider)
        val slideModels = ArrayList<SlideModel>()

        slideModels.add(SlideModel(R.drawable.oferta1,ScaleTypes.FIT));
        slideModels.add(SlideModel(R.drawable.oferta2,ScaleTypes.FIT));
        slideModels.add(SlideModel(R.drawable.oferta3,ScaleTypes.FIT));

        imageSlider?.setImageList(slideModels, ScaleTypes.FIT);

        return inflater.inflate(R.layout.fragment_home, container, false)
    }


}