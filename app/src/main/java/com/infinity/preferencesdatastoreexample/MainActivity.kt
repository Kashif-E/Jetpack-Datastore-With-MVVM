package com.infinity.preferencesdatastoreexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.infinity.preferencesdatastoreexample.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var dataStoreManager: DataStoreManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataStoreManager = DataStoreManager(this@MainActivity)

        binding.save.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                dataStoreManager.savetoDataStore(
                        phonebook = Phonebook(name = binding.tvvname.text.toString(),
                                phone = binding.tvphonr.text.toString(),
                                address = binding.tvAddress.text.toString())
                )
            }
        }

        binding.get.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO
            ) {
                dataStoreManager.getFromDataStore().catch { e ->
                    e.printStackTrace()
                }.collect {
                    withContext(Dispatchers.Main) {
                        binding.tvcphonr.setText(it.phone)
                        binding.tvxAddress.setText(it.address)
                        binding.tvxname.setText(it.name)

                    }

                }


            }

        }
    }
}