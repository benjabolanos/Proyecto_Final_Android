package uabc.ic.benjaminbolanos.proyectofinalandroid

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class TopPlayerDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog{
        return AlertDialog.Builder(requireContext())
            .setMessage("Boton picado")
            .setPositiveButton("Ok"){_,_ ->}
            .create()
    }


    companion object{
        const val TAG = "TopPlayerDialog"
    }
}