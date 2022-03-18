package com.nityen.accountvault

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nityen.accountvault.adapter.OnItemClickedListener
import com.nityen.accountvault.adapter.RecyclerViewAdapter
import com.nityen.accountvault.databinding.FragmentFirstBinding
import com.nityen.accountvault.model.AccountViewModel
import com.nityen.accountvault.model.Accounts
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), OnItemClickedListener {


    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<Accounts>

    private lateinit var dialog:Dialog

    private val v by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(AccountViewModel::class.java)
    }


    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()


            dialog = Dialog(requireContext()).apply {
                this.setContentView(R.layout.insert_dialog)
                this.window?.attributes?.width = WindowManager.LayoutParams.FILL_PARENT

                val name = this.findViewById<EditText>(R.id.upedit_name)
                val email = this.findViewById<EditText>(R.id.upedit_accid)
                val pass = this.findViewById<EditText>(R.id.upedit_pass)



                this.findViewById<Button>(R.id.save_btn).setOnClickListener {
                    val items = Accounts(name.text.toString(),email.text.toString(),pass.text.toString())

                    GlobalScope.launch {
                        v.insert(items)
                    }
                    dialog.dismiss()
                }
            }
            dialog.show()







        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        v.allData.observe(viewLifecycleOwner, Observer {
            dataList = it as ArrayList<Accounts>
            recyclerView.adapter = RecyclerViewAdapter(requireContext(), dataList, this)

        })
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(position: Int) {
        Log.d(TAG, "onItemClicked: $position")
    }


    override fun onDeleteClicked(list: ArrayList<Accounts>, position: Int) {
        val alertDialog = AlertDialog.Builder(requireContext()).apply {
            this.setTitle("Alert!")
            this.setMessage("Do You Want to Delete?")

            setPositiveButton("yes") { dialogInterface, which ->
                GlobalScope.launch {
                    v.delete(list[position])
                }
                Toast.makeText(context, "SuccessFully Deleted", Toast.LENGTH_SHORT).show()

            }
        }
        alertDialog.show()

    }

    override fun onEditClicked(list: ArrayList<Accounts>, position: Int) {
        val item = list[position]
        val bundle = Bundle()
        Log.d("car", "onEditClicked: ${item.account_name}")
        bundle.putString("name",item.account_name)
        bundle.putString("email",item.account_id)
        bundle.putString("pass",item.account_pass)
        bundle.putInt("id",item.id)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
        Toast.makeText(context, "edited", Toast.LENGTH_SHORT).show()
    }
}