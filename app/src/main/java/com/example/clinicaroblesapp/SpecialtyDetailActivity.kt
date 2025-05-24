package com.example.clinicaroblesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.clinicaroblesapp.databinding.ActivitySpecialtyDetailBinding

class SpecialtyDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySpecialtyDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpecialtyDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar botón de retroceso
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Obtener los datos de la especialidad
        val specialty = intent.getParcelableExtra<Specialty>("specialty")

        specialty?.let {
            binding.detailIcon.setImageResource(it.iconResId)
            binding.detailTitle.text = it.name

            binding.detailDoctor.text = formatDoctorsList(it.doctorName)

            binding.detailSchedule.text = ": ${it.schedule}"
        }
    }

    private fun formatDoctorsList(doctorsString: String): String {
        return if (doctorsString.contains("\n")) {
            // Si ya tiene saltos de línea (viene de loadSpecialtiesData())
            "Doctores:\n${doctorsString.replace(", ", "\n")}"
        } else {
            // Para casos con un solo doctor
            "Doctor: $doctorsString"
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}