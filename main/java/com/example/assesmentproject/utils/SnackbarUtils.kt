import android.content.Context
import android.view.View
import android.widget.TextView
import com.example.assesmentproject.R
import com.google.android.material.snackbar.Snackbar

object SnackbarUtils {

     fun showSnackBar(title: String, it: View, context: Context)
    {
        val snackBar = Snackbar.make(
            it, title,
            Snackbar.LENGTH_LONG
        ).setAction("Action", null)
        snackBar.setActionTextColor(context.resources.getColor(R.color.white))
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(context.resources.getColor(R.color.blue))
        val textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(context.resources.getColor(R.color.white))
        snackBar.show()
    }
}