package com.example.easyLister
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity ()
{
	private lateinit var easyListerAdapter : EasyListerAdapter
	override fun onCreate ( savedInstanceState : Bundle? )
	{
		super . onCreate ( savedInstanceState )
		setContentView ( R . layout . activity_main )
		easyListerAdapter = EasyListerAdapter ( mutableListOf () )
		rvEasyListerItems . adapter = easyListerAdapter
		rvEasyListerItems . layoutManager = LinearLayoutManager ( this )
		btnAddEasyLister . setOnClickListener
		{
			val easyListerTitle = etEasyListerTitle . text . toString ()
			if ( easyListerTitle . isNotEmpty () )
			{
				val easylister = EasyLister ( easyListerTitle )
				easyListerAdapter . addEasyLister ( easylister )
				etEasyListerTitle . text . clear ()
			}
		}
		btnDeleteDoneEasyListers . setOnClickListener
		{
			easyListerAdapter . deleteDoneEasyListers ()
		}
	}
}
