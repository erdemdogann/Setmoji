package com.example.setmoji

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.setmoji.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private val movieList = listOf("titanic", "aladdin")
    private var level = 0
    private var name = 0
    private var firstImage = 0
    private var secondImage = 1
    private var thirdImage = 2
    private var image1 = 0
    private var image2 = 1
    private var image3 = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()
        setupGame()
    }

    private fun initListener() {
        binding.button.setOnClickListener { checkAnswer() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.ana_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.ana_menu) {
            startActivity(Intent(this, MainActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun changeImages() {
        if (level + 1 == 3) finish()
        level += 1
        image1 = firstImage + (level * 3)
        image2 = secondImage + (level * 3)
        image3 = thirdImage + (level * 3)
        name += 1
    }

    private fun setupGame() {
        val emoList = resources.obtainTypedArray(R.array.emoList)

        binding.apply {
            imageView.setImageResource(emoList.getResourceId(image1,-1))
            imageView1.setImageResource(emoList.getResourceId(image2,-1))
            imageView2.setImageResource(emoList.getResourceId(image3,-1))
        }
    }

    private fun checkAnswer() {
        val answer = binding.film.text.toString()

        if (answer == movieList[name]) {
            AlertDialog.Builder(this)
                .setMessage(getString(R.string.success_message))
                .setTitle(getString(R.string.success_title))
                .setPositiveButton(getString(R.string.positive_button_text)) { _, _ ->
                    changeImages()
                    setupGame()
                }
                .create().show()
        } else {
            Toast.makeText(this, getString(R.string.try_again), Toast.LENGTH_LONG).show()
        }
    }
}