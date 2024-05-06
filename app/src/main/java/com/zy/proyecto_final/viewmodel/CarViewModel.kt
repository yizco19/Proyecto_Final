package com.zy.proyecto_final.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zy.proyecto_final.pojo.Car
import com.zy.proyecto_final.pojo.Product
import com.zy.proyecto_final.repository.CarRepository

class CarViewModel: ViewModel() {
    private lateinit var _context: Context

    lateinit var itemsrepository: CarRepository
    private lateinit var _items: MutableLiveData<MutableList<Car>>;

    var selectedcar= Car()

    public val items: LiveData<MutableList<Car>>
        get() = _items
    fun init(c: Context) {
        this._context = c
        _items = MutableLiveData()
        this.itemsrepository = CarRepository(c)
        this._items.value = this.itemsrepository.getAll()
        /*val productList = mutableListOf(
            Product("Producto 1", "Producto 1", 1),
            Product("Producto 2", "Producto 2", 1),
        )
        for (product in productList) {
            add(Car(null, 1, 1,product.name,product.price,8,product.imageUrl))
        }*/
    }

    fun add() {
        val existingCar = _items.value?.find { it.product_id == selectedcar.product_id }
        if (existingCar != null) {
            // El producto ya existe en el carrito, así que incrementamos la cantidad
            existingCar.product_count += 1
            updateAll()
        } else {
            // El producto no existe en el carrito, así que lo agregamos
            _items.value?.add(selectedcar)
            itemsrepository.add(selectedcar)
            updateAll()
        }
    }

    fun getAll():MutableList<Car>{
        return this.itemsrepository.getAll(selectedcar.user_id)
    }


    fun delete(item: Car) {
this.itemsrepository.delete(item)
    }
    fun deleteAll() {
        this.itemsrepository.deleteAll()
    }
    fun update(item: Car) {
        this.selectedcar = item
        this.itemsrepository.update(selectedcar)
    }

    fun updateAll() {
        val values =this._items.value
        this._items.value = this.itemsrepository.getAll()
    }
}