package ufrn.br.meuslivros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import ufrn.br.meuslivros.databinding.ActivityListarlivrosBinding
import ufrn.br.meuslivros.databinding.ActivityMainBinding

class Listarlivros : AppCompatActivity() {

    private lateinit var binding: ActivityListarlivrosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_listarlivros)


    }
}