package com.demoexample.mystreamchat.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.demoexample.mystreamchat.R
import com.demoexample.mystreamchat.databinding.ActivityMainBinding
import com.demoexample.mystreamchat.model.ChatUser
import com.demoexample.mystreamchat.ui.fragment.loginfragment.LoginFragmentDirections
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.name

class MainActivity : AppCompatActivity(){
    private var _binding:ActivityMainBinding?=null
    private val binding  get() = _binding!!

    private  var chatClient=ChatClient.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController


        if (navController.currentDestination?.label.toString().contains("LoginFragment")){
          val chat_client=  chatClient.getCurrentUser()
            if (chat_client!=null){
                val user=ChatUser(chat_client.name,chat_client.id)

                val action=LoginFragmentDirections.actionLoginFragmentToChannelFragment(user)
                navController.navigate(action)
            }
        }

    }



    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}