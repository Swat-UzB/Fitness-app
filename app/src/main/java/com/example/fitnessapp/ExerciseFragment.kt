package com.example.fitnessapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.fitnessapp.databinding.FragmentExerciseBinding


class ExerciseFragment : Fragment() {
    data class Exercise(
        val exerciseType: String,
        val exerciseCount: Int
    )

    private var _binding: FragmentExerciseBinding? = null

    private val binding get() = _binding!!

    private val exercises: MutableList<Exercise> = mutableListOf(
        Exercise("workout_1", 8),
        Exercise("workout_2", 5),
        Exercise("workout_3", 10),
        Exercise("workout_4", 15),
        Exercise("workout_5", 20),
    )
    private lateinit var currentExercise: Exercise
    private var count: Int = 0
    private var exerciseIndex: Int = 0
    private var exerciseSize = Math.min((exercises.size + 1) / 2, 3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExerciseBinding.inflate(inflater, container, false)
        randomizeExercise()
        binding.nextButton.setOnClickListener { view: View ->
            exerciseIndex++
            if (exerciseIndex < exerciseSize) {
                setExercise()
            } else if (exerciseIndex == exerciseSize) {
                view.findNavController().navigate(R.id.action_exerciseFragment3_to_wellDoneFragment)
            }
        }
        binding.exitButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_exerciseFragment3_to_tryAgainFragment))
        return binding.root
    }

    private fun randomizeExercise() {
        exercises.shuffle()
        exerciseIndex = 0
        setExercise()

    }

    private fun setExercise() {
        currentExercise = exercises[exerciseIndex]
        count = currentExercise.exerciseCount
        binding.timeTextView.text = String.format(getString(R.string.str_count_text_view), count)
        binding.gifImageView.setImageResource(
            resources.getIdentifier(
                currentExercise.exerciseType,
                "drawable",
                (activity as AppCompatActivity).packageName
            )

        )
        (activity as AppCompatActivity).supportActionBar?.title = String.format(
            getString(R.string.title_android_fitness_exercise),
            exerciseIndex + 1,
            exerciseSize
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

