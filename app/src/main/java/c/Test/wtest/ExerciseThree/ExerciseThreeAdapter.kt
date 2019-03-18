package c.Test.wtest.ExerciseThree

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.view.LayoutInflater
import android.view.ViewGroup
import c.Test.wtest.R

class ExerciseThreeAdapter (private val context: Context, private val values: Int)
    : RecyclerView.Adapter<ResultViewHolderThree> (){

    private var validate: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolderThree {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_exercise3, parent, false)
        return ResultViewHolderThree(itemView)
    }

    override fun getItemCount(): Int {
        return values
    }

    override fun onBindViewHolder(holder: ResultViewHolderThree, position: Int) {

        when (validate) {
            0 -> {
                holder.typeText.text = "text"
                holder.editText.inputType = InputType.TYPE_CLASS_TEXT
                validate = 1
            }
            1 -> {
                holder.typeText.text = "number"
                holder.editText.inputType = InputType.TYPE_CLASS_NUMBER
                validate = 2
            }
            2 -> {
                holder.typeText.text = "UPCASE"
                holder.editText.inputType = InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS
                validate = 0
            }
        }
    }
}