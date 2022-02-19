package de.malte.badsheet

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import de.malte.badsheet.classes.Match
import de.malte.badsheet.databinding.ActivitySignatureBinding
import de.malte.badsheet.utility.SharedPref
import java.io.File
import java.io.FileOutputStream

class SignatureActivity : AppCompatActivity()
{
    private lateinit var binding: ActivitySignatureBinding
    private lateinit var match: Match
    private lateinit var teamname: String

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivitySignatureBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        teamname = intent.getStringExtra("TEAM")!!
        title = getString(R.string.signature) + " " + teamname

        match = SharedPref().GetMatch(this)
        SharedPref().SaveSharedPreference(this, match, "MATCH")
    }

    override fun onStop()
    {
        super.onStop()
        saveSignature()
    }


    /**
     * Clear Signature View
     */
    fun clearSignature()
    {
        binding.signatureView.clearSignature()
    }

    /**
     * Save Signature
     */
    private fun saveSignature()
    {
        // set the file name of your choice
        val filename: String
        if (teamname == getString(R.string.hometeam))
        {
            filename = "sign_home.png"
        }
        else
        {
            filename = "sign_away.png"
        }
        saveImage(binding.signatureView.signature, filename)
    }

    /**
     * save the signature to an sd card directory
     * @param signature bitmap
     */
    private fun saveImage(signature: Bitmap, filename: String)
    {

        // the directory where the signature will be saved
        val myDir = File("$filesDir/signs")

        // make the directory if it does not exist yet
        if (!myDir.exists())
        {
            val isCreated = myDir.mkdirs()
            if (!isCreated)
            {
                Toast.makeText(this, getString(R.string.no_permission), Toast.LENGTH_LONG).show()
            }
        }

        // in our case, we delete the previous file, you can remove this
        val file = File(myDir, filename)
        if (file.exists())
        {
            file.delete()
        }
        try
        {
            // save the signature
            val out = FileOutputStream(file)
            signature.compress(Bitmap.CompressFormat.PNG, 90, out)
            out.flush()
            out.close()

            Toast.makeText(this, getString(R.string.sign_saved), Toast.LENGTH_LONG).show()
        }
        catch (e: Exception)
        {
            e.printStackTrace()
        }
    }
}