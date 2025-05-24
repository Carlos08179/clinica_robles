
package com.example.clinicaroblesapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clinicaroblesapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var specialtyAdapter: SpecialtyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuración inicial de la Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // 1. Configuración del RecyclerView (LISTA DE ESPECIALIDADES)
        binding.recyclerViewSpecialties.layoutManager = LinearLayoutManager(this)
        specialtyAdapter = SpecialtyAdapter(loadSpecialtiesData()) { specialty ->

            val intent = Intent(this, SpecialtyDetailActivity::class.java).apply {
                putExtra("specialty", specialty)
            }
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
        binding.recyclerViewSpecialties.adapter = specialtyAdapter

        // 2. Configuración del BottomNavigationView (MENÚ INFERIOR)
        //
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Ya estamos en home, no hacemos nada
                    true
                }
                R.id.nav_doctors -> {
                    startActivity(Intent(this, DoctorsActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    true
                }
                R.id.nav_contact -> {
                    startActivity(Intent(this, ContactActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    true
                }
                else -> false
            }
        }
    }

    // Función existente que carga los datos
    private fun loadSpecialtiesData(): List<Specialty> {
        return listOf(
            Specialty(
                "Cardiología",
                "Dr. Roberto Chavesta Bernal",
                "Horario: Lunes a Viernes 9am - 5pm",
                R.drawable.cardiologia
            ),
            Specialty(
                "Dermatología",
                "Dr. Jaime Vega Chávez\nDr. Italo Vegas Jabanille",
                "Horario: Lunes a Sábado 8am - 8pm",
                R.drawable.dermatologia
            ),
            Specialty(
                "Ginecología",
                "Dr. José Luis Espinoza Decena\nDra. Villissa Paerdes Padilla",
                "Horario: Lunes a Viernes 7am - 7pm",
                R.drawable.ginecologia
            ),
            Specialty(
                "Medicina General",
                "Dr. Elvis Laveriano Novos\nDra. María Cristina Saavedra",
                "Horario: Lunes a Domingo 8am - 10pm",
                R.drawable.medicinageneral
            ),
            Specialty(
                "Odontología",
                "Dr. Pedro Cipriano Alegre\nDra. Carolina Acuña Calderón",
                "Horario: Lunes a Sábado 9am - 6pm",
                R.drawable.odontologia
            ),
            Specialty(
                "Pediatría",
                "Dr. Marcos Vásquez Tantas\nDr. Jaime Cabrera Pereña",
                "Horario: 24 horas",
                R.drawable.pediatria
        )
        )
    }
}