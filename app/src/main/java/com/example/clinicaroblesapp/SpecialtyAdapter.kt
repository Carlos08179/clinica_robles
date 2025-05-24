package com.example.clinicaroblesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicaroblesapp.databinding.ItemSpecialtyCardBinding

class SpecialtyAdapter(
    private val specialties: List<Specialty>,
    private val onItemClick: (Specialty) -> Unit
) : RecyclerView.Adapter<SpecialtyAdapter.SpecialtyViewHolder>() {

    inner class SpecialtyViewHolder(val binding: ItemSpecialtyCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialtyViewHolder {
        val binding = ItemSpecialtyCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SpecialtyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpecialtyViewHolder, position: Int) {
        val specialty = specialties[position]

        with(holder.binding) {
            // 1. Ícono de la especialidad
            imageViewSpecialtyIcon.setImageResource(specialty.iconResId)

            // 2. Textos de la tarjeta
            textViewSpecialtyName.text = specialty.name

            //  Formatear lista de doctores
            textViewDoctorName.text = formatDoctorsList(specialty.doctorName)

            // 3. Configurar horario
            configureScheduleView(specialty.schedule)

            // 4. Click en la tarjeta
            root.setOnClickListener { onItemClick(specialty) }
        }
    }

    // función para formatear doctores
    private fun formatDoctorsList(doctorsString: String): String {
        return if (doctorsString.contains("\n")) {
            val doctors = doctorsString.split("\n")
            doctors.joinToString("\n") { "• $it" } // Viñetas para cada doctor
        } else {
            "• $doctorsString" // Viñeta para único doctor
        }
    }

    // función para configurar el horario
    private fun ItemSpecialtyCardBinding.configureScheduleView(schedule: String) {
        textViewSchedule.text = schedule
        textViewSchedule.background = null

        if (schedule.contains("Cerrado")) {
            textViewSchedule.setTextColor(root.context.getColor(R.color.red_closed))
            textViewSchedule.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_circle_closed, 0, 0, 0
            )
        } else {
            textViewSchedule.setTextColor(root.context.getColor(R.color.green_open))
            textViewSchedule.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_circle_open, 0, 0, 0
            )
        }

    }

    override fun getItemCount() = specialties.size
}