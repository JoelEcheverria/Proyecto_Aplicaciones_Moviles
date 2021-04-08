package com.example.hotelmitaddelmundo.ui.reservas;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hotelmitaddelmundo.R;
import com.example.hotelmitaddelmundo.login;

import java.util.Calendar;

public class reservasFragment extends Fragment implements View.OnClickListener {

    private reservasViewModel reservasViewModel;
    private RadioButton rd1, rd2, rd3;
    private RadioGroup rdGp;
    private TextView tvEntrada, tvSalida,tvnHabitaciones,tvnAdultos,btnReserva, tvpxn,tvTotal;
    private ImageView btnEntrada, btnSalida;
    private Spinner sphabitaciones, sptipohabitacion;
    String[] nHabitaciones = {"  1  ","  2  ","  3  ","  4  "};
    String[] tipoHabitaciones = {"HABITACIÓN ESTANDARD","HABITACIÓN SUPERIOR","HABITACIÓN EJECUTIVE","JUNIOR SUITE"};

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        reservasViewModel =new ViewModelProvider(this).get(reservasViewModel.class);
        View root = inflater.inflate(R.layout.activity_reservas, container, false);


        tvEntrada = (TextView)root.findViewById(R.id.tvEntrada);
        tvSalida = (TextView)root.findViewById(R.id.tvSalida);
        tvnHabitaciones = (TextView)root.findViewById(R.id.tvnHabitaciones);
        tvnAdultos = (TextView)root.findViewById(R.id.tvnAdultos);
        btnEntrada = (ImageView) root.findViewById(R.id.btnEntrada);
        btnSalida = (ImageView) root.findViewById(R.id.btnSalida);
        sphabitaciones = (Spinner) root.findViewById(R.id.sphabitaciones);
        sptipohabitacion = (Spinner) root.findViewById(R.id.sptipohabitacion);
        btnReserva = (TextView)root.findViewById(R.id.btnReserva);
        tvpxn = (TextView)root.findViewById(R.id.tvpxn);
        tvTotal = (TextView)root.findViewById(R.id.tvTotal);
        rd1 = (RadioButton)root.findViewById(R.id.rd1);
        rd2 = (RadioButton)root.findViewById(R.id.rd2);
        rd3 = (RadioButton)root.findViewById(R.id.rd3);
        rdGp = (RadioGroup) root.findViewById(R.id.rdGp);
        rd1.setChecked(true);


        // tvnHabitaciones.setText("Habitaciones  \n diponibles");
       // tvnAdultos.setText("Adultos \n por habitacion");



        btnEntrada.setOnClickListener(this);
        btnSalida.setOnClickListener(this);
        btnReserva.setOnClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_item_reservas,nHabitaciones);
        sphabitaciones.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_item_reservas,tipoHabitaciones);
        sptipohabitacion.setAdapter(adapter2);



        rdGp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (!tvEntrada.getText().toString().equals("Fecha Entrada") && !tvSalida.getText().toString().equals("Fecha Salida")) {
                tvTotal.setText(imprimirPrecioNoche()+"$");
                tvpxn.setText(""+costoNoche()+"$");
                }
            }
        });
        sptipohabitacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!tvEntrada.getText().toString().equals("Fecha Entrada") && !tvSalida.getText().toString().equals("Fecha Salida")) {
                tvTotal.setText(imprimirPrecioNoche()+"$");
                tvpxn.setText(""+costoNoche()+"$");

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sphabitaciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!tvEntrada.getText().toString().equals("Fecha Entrada") && !tvSalida.getText().toString().equals("Fecha Salida")) {
                tvTotal.setText(imprimirPrecioNoche()+"$");
                tvpxn.setText(""+costoNoche()+"$");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        reservasViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(getActivity(), "bienvenido a reservas", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }

    //Log.e(this.getTag(),entrada);

    String fecha ="";
    String entrada="";
    String salida="";
    int milisE ,milisS;

    public int adxhab(){
          int isCheked =rdGp.getCheckedRadioButtonId();
         switch (isCheked){
             case R.id.rd1:
                 return 1;
             case R.id.rd2:
                 return 2;
             case R.id.rd3:
                 return 3;
         }
         return isCheked;
    }

    public int numeHabi(){
        int selec = sphabitaciones.getSelectedItemPosition();
        return selec+1;
    }

    public String tipoHb(){
        String tipoH = sptipohabitacion.getSelectedItem().toString();
        return tipoH;
    }
//costo de la noche por el tipo de habitacion
    public int costoNoche(){
        int pagar=0;
        if (adxhab() == 1 && tipoHb().equals("HABITACIÓN ESTANDARD")) {
            pagar = 30;
        }else if (adxhab() == 2 && tipoHb().equals("HABITACIÓN ESTANDARD") || adxhab()==3 || adxhab() == 1 && tipoHb().equals("HABITACIÓN SUPERIOR")) {
            pagar=40;
        }else if (adxhab() == 2 && tipoHb().equals("HABITACIÓN SUPERIOR") || adxhab()==3 || adxhab() == 1 && tipoHb().equals("HABITACIÓN EJECUTIVE")) {
            pagar=50;
        }else if (adxhab() == 2 && tipoHb().equals("HABITACIÓN EJECUTIVE") || adxhab()==3 || adxhab() == 1 && tipoHb().equals("JUNIOR SUITE")) {
            pagar=60;
        }else if (adxhab() == 2 && tipoHb().equals("JUNIOR SUITE") || adxhab()==3 ) {
            pagar=70;
        }
        return pagar;
    }



    public void calendarioE(int de,int me, int ae){
         entrada = de+"/"+me+"/"+"/"+ae;
         Calendar cEntrada =Calendar.getInstance();
         cEntrada.set(ae, me - 1, de);
         milisE = (int) cEntrada.getTimeInMillis();


    }
    public void caledarioS(int ds,int ms, int as){
        salida = ds+"/"+ms+"/"+"/"+as;
        Calendar cSalida =Calendar.getInstance();
        cSalida.set(as, ms - 1, ds);
        milisS= (int) cSalida.getTimeInMillis();
    }

    public String imprimirPrecioNoche(){
          int formato= 24*60*60*1000;
                int noches = Math.round((milisS-milisE)/formato);
                int calcular = noches*numeHabi()*costoNoche();

                String TotalaPagar = ""+calcular;
                Log.e(this.getTag(), "Total a pagar: " + TotalaPagar);
                return TotalaPagar;
    }


//time=1617138086650
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        Log.e(this.getTag(), ""+cal.getTimeInMillis());
        switch (v.getId()) {
            case R.id.btnEntrada:
                DatePickerDialog calendario = new DatePickerDialog(this.getContext(), /*R.style.ThemeOverlay_AppCompat_Light,*/
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                int m = month + 1;
                                fecha = dayOfMonth + "/" + m + "/" + year;
                                tvEntrada.setText(fecha);
                                calendarioE(dayOfMonth, month, year);
                /*Format formatter = new SimpleDateFormat("dd/MM/yyyy");
                String s = formatter.format(cal.getTime());*/
                            }
                        }, anio, mes, dia);
                calendario.show();


                break;

            case R.id.btnSalida:
                DatePickerDialog calendar = new DatePickerDialog(this.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                int m = month + 1;
                                fecha = dayOfMonth + "/" + m + "/" + year;
                                caledarioS(dayOfMonth, month, year);
                                tvSalida.setText(fecha);
                            }
                        }, anio, mes, dia);
                calendar.show();
                break;
            case R.id.btnReserva:
                int formato= 24*60*60*1000;
                int noches = Math.round((milisS-milisE)/formato);


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setIcon(R.mipmap.ic_launcher)
                        .setTitle("¿RESERVAR "+tipoHb()+"?")
                         .setMessage("\nEntrada: "+entrada+
                                   "\nSalida: "+salida+
                                    "\nAdultos: "+adxhab()+
                                    "\nHabitaciones: "+numeHabi()+
                                    "\nNoches: "+noches+
                                    "\nTotal a pagar: "+imprimirPrecioNoche())
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(),"Reservado", Toast.LENGTH_LONG).show();
                            }
                        }).
                        setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();



                break;

        }

    }
}