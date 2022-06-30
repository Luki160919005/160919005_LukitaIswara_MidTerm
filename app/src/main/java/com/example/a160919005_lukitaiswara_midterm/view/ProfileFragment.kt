package com.example.a160919005_lukitaiswara_midterm.view

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.a160919005_lukitaiswara_midterm.R
import com.example.a160919005_lukitaiswara_midterm.util.loadImage2
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlin.math.pow
import kotlin.math.sqrt


class ProfileFragment : Fragment(), SensorEventListener {

    private var sensorManager: SensorManager?= null
    private var accelerometerReading = FloatArray(3)
    private var previousV:Float ?=null
    private var stepCounter:Int =0
    private var magneticReading = FloatArray(3)

    var sm: SensorManager? = null
    var list: List<*>? = null

    var sel: SensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
        override fun onSensorChanged(sensorEvent: SensorEvent) {
            val values = sensorEvent.values
            if (sensorEvent.values[1] < 7) {
                txtMsg.setText(
                    """x: ${values[0]} m/s²
                    y: ${values[1]} m/s²
                    z: ${values[2]} m/s²"""
                )
                //getWindow().getDecorView().setBackgroundColor(Color.WHITE)
            } else {
                txtMsg.setText(
                    """x: ${values[0]} m/s²
                    y: ${values[1]} m/s²
                    z: ${values[2]} m/s²"""
                )
                //getWindow().getDecorView().setBackgroundColor(Color.RED)
            }
        }
    }





    override fun onResume() {
        super.onResume()

        val accelerometerSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)



        if(accelerometerSensor != null){
            sensorManager?.registerListener(this,accelerometerSensor, SensorManager.SENSOR_DELAY_GAME)
        }
        else{
            Toast.makeText(context, "Accelerometer is not detected\"", Toast.LENGTH_LONG).show()
            //Toast.makeText(this,"Accelerometer is not detected", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment\

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        if(arguments != null){
            val name = ProfileFragmentArgs.fromBundle(requireArguments()).profileUsername
            val picture = ProfileFragmentArgs.fromBundle(requireArguments()).profilePicture
            val profileEmail = ProfileFragmentArgs.fromBundle(requireArguments()).profileEmail
            val profileAddress = ProfileFragmentArgs.fromBundle(requireArguments()).profileAddress
            val profileAge = ProfileFragmentArgs.fromBundle(requireArguments()).profileAge
            val profileGender = ProfileFragmentArgs.fromBundle(requireArguments()).profileGender

            textProfileUsername.text = "Name: $name"
            textAddress.text = "Address: $profileAddress"
            textEmail.text = "Email: $profileEmail"
            textAge.text = "Age: $profileAge"
            textGender.text = "Gender: $profileGender"
            imageProfile.loadImage2(picture.toString())

        }


        //list = sm!!.getSensorList(Sensor.TYPE_ACCELEROMETER) //Type Sensor



    }

    override fun onSensorChanged(event: SensorEvent?) {

        if(event!!.sensor.type == Sensor.TYPE_ACCELEROMETER){
            accelerometerReading = event.values
            var ax = event.values[0]
            var ay = event.values[1]
            var az = event.values[2]

            var v = sqrt(ax.pow(2) + ay.pow(2)+az.pow(2))

            if(previousV != null){
                var diff =v - previousV!!

                if(diff > 6){
                    stepCounter++
                    txtMsg.text = "$stepCounter steps"
                }
            }

            previousV = v
            txtMsg.text = "ax: $ax, ay: $ay; az: $az"
        }






    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }

}