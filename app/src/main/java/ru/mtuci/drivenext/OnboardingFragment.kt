package ru.mtuci.drivenext

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.mtuci.drivenext.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_INDEX = "index"
        fun newInstance(index: Int) = OnboardingFragment().apply {
            arguments = Bundle().apply { putInt(ARG_INDEX, index) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val index = arguments?.getInt(ARG_INDEX) ?: 0

        // картинки и тексты для 3 слайдов
        val images = listOf(
            R.drawable.img_onb_1,
            R.drawable.img_onb_2,
            R.drawable.img_onb_3
        )
        val titles = listOf(
            R.string.onb1_title, R.string.onb2_title, R.string.onb3_title
        )
        val descs = listOf(
            R.string.onb1_desc, R.string.onb2_desc, R.string.onb3_desc
        )

        binding.onboardingImage.setImageResource(images[index])
        binding.titleText.setText(titles[index])
        binding.descText.setText(descs[index])
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}